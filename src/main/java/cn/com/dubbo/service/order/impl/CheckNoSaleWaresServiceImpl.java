package cn.com.dubbo.service.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.bean.ItemBatchDetail;
import cn.com.dubbo.bean.ItemBatchIndex;
import cn.com.dubbo.bean.OrderItem;
import cn.com.dubbo.mapper.ItemBatchDetailMapper;
import cn.com.dubbo.mapper.ItemBatchIndexMapper;
import cn.com.dubbo.mapper.OrderItemMapper;
import cn.com.dubbo.service.inter.StockSynchroService;
import cn.com.dubbo.service.order.CheckNoSaleWaresService;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.DateUtil;
import cn.com.dubbo.util.GetNowTime;
/**
 * 查询未销商品
 * @author Qingy.Yao
 *
 */
@Service
public class CheckNoSaleWaresServiceImpl implements CheckNoSaleWaresService{
	
	public final static int pageSize = 100;//每页显示100条数据
	public static int pageNo = 0;
	private static final Logger logger = Logger.getLogger(CheckNoSaleWaresServiceImpl.class);
	
	private DataQueue<OrderItem> orderItemErrorQueue = new DataQueue<OrderItem>();
	
	@Resource
	OrderItemMapper orderItemMapper;
	@Resource
	ItemBatchDetailMapper itemBatchDetailMapper;
	@Resource
	ItemBatchIndexMapper itemBatchIndexMapper;
	
	@Resource
	private StockSynchroService synchroService;
	
	@Override
	public void findNoSaleWares(int mulId) {
		
		logger.info("插入未销商品开始，渠道ID:"+mulId+",时间:"+DateUtil.DateTimeToStr(new Date()));
		//查询未销的正常商品
		pageNo=0;
		findNoSaleNormalWares(mulId);
		//查询未销的补发商品
		pageNo=0;
		findNoSaleReissueWares(mulId);
		logger.info("插入未销商品结束，渠道ID:"+mulId+",时间:"+DateUtil.DateTimeToStr(new Date()));
		
		//处理队列的错误数据
		this.dealOrderItemErrorQueue();
		
		logger.info("汇总未销商品开始，渠道ID:"+mulId+",时间:"+DateUtil.DateTimeToStr(new Date()));
		summary(mulId);
		logger.info("汇总未销商品结束，渠道ID:"+mulId+",时间:"+DateUtil.DateTimeToStr(new Date()));
		
		//同步信息到中间库
		synchroService.synchro(mulId);
		
	}
	

	//查询未销的正常商品
	private void findNoSaleNormalWares(int mulId) {
		System.out.println("进入serviceImpl中>>>>>>>>>>>>>>>>>>>>>>>");
		List<OrderItem> list = new ArrayList<OrderItem>();
		try {
			OrderItem orderItem = new OrderItem();
			String add_time = GetNowTime.getCurrentTime();
			orderItem.setAdd_time(add_time);
			orderItem.setMulId(mulId);
			int count = orderItemMapper.checkNosaleWaresCount(orderItem);//查询未销商品总条数
			orderItem.setPageNo(pageNo);
			System.out.println("pageNo="+pageNo);
			orderItem.setPageSize(pageSize);
			String summary = "N";
			list = orderItemMapper.checkNoSaleWares(orderItem);//查询未销商品并存入list中
			if(list!=null && list.size()>0){
				synoDada(list,add_time,count,summary,mulId,0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (null != list && list.size() > 0) {
				for (OrderItem orderItem : list) {
					logger.error("查询未销的【正常】商品错误 ，错误订单号：" + orderItem.getOrder_no());
					orderItemErrorQueue.enQueue(orderItem);
				}
			}
		}
		
	}
	
	//查询未销的补发商品
	private void findNoSaleReissueWares(int mulId) {
		System.out.println("进入serviceImpl中>>>>>>>>>>>>>>>>>>>>>>>");
		List<OrderItem> list = new ArrayList<OrderItem>();
		try {
			OrderItem orderItem = new OrderItem();
			String add_time = GetNowTime.getCurrentTime();
			orderItem.setAdd_time(add_time);
			orderItem.setMulId(mulId);
			int count = orderItemMapper.checkNosaleReissueWaresCount(orderItem);//查询未销商品总条数
			orderItem.setPageNo(pageNo);
			System.out.println("pageNo="+pageNo);
			orderItem.setPageSize(pageSize);
			String summary = "N";
			list = orderItemMapper.checkNoSaleReissueWares(orderItem);//查询未销商品并存入list中
			if(list!=null && list.size()>0){
				synoDada(list,add_time,count,summary,mulId,1);
			}
		} catch (Exception e) {
			logger.error("查询未销的商品异常："+e.getMessage(),e);
			if (null != list && list.size() > 0) {
				for (OrderItem orderItem : list) {
					logger.error("查询未销的【补发】商品错误 ，错误订单号：" + orderItem.getOrder_no());
					orderItemErrorQueue.enQueue(orderItem);
				}
			}
		}
		
	}
	
	//同步数据
	@Transactional
	private void synoDada(List<OrderItem> list,String addTime,int count,String summary,int mulId,int orderType) throws Exception{
		List<ItemBatchDetail> ls = new ArrayList<ItemBatchDetail>();
		
		int totalNum = 0;
		int num = 0;
		if(true){
			for(int i=0;i<list.size();i++){
				ItemBatchDetail itemBatchDetail = new ItemBatchDetail();
				list.get(i).setAdd_time(addTime);
				list.get(i).setSummary(summary);
				list.get(i).setMulId(mulId);
				itemBatchDetail.setOrderItemId(list.get(i).getOrder_item_id());
				itemBatchDetail.setOrderNo(list.get(i).getOrder_no());
				itemBatchDetail.setGoodsNo(list.get(i).getGoods_no());
				itemBatchDetail.setGoodsNo69(list.get(i).getGoods_no_69());
				itemBatchDetail.setCostPrice(list.get(i).getCost_price());
				itemBatchDetail.setGoodName(list.get(i).getName());
				itemBatchDetail.setAddTime(addTime);
				itemBatchDetail.setAmount(list.get(i).getAmount());
				itemBatchDetail.setStockStatus(list.get(i).getStock_status());
				itemBatchDetail.setSummary(summary);
				itemBatchDetail.setMulti_channel_id(mulId);
				ls.add(itemBatchDetail);
//				ls.get(i).getOrderItemId();
			}
			List<String> orderNos = itemBatchDetailMapper.selectRepeatWare(ls);
			List<ItemBatchDetail> tempList = new ArrayList<ItemBatchDetail>();
			if (orderNos != null && orderNos.size() > 0) {				
				for (ItemBatchDetail order : ls) {
					if (!orderNos.contains(Long.toString(order.getOrderItemId()))) {
						tempList.add(order);
					}
				}
				if(tempList !=null && tempList.size()>0){
					totalNum = tempList.size();
					num = itemBatchDetailMapper.saveNoSaleWares(tempList); //将未销商品保存到item_batch_detail表中,并返回插入条数
					System.out.println("查出未销商品，将未销商品插入到数据库中，并成功插入"+num+"条数据>>>>>>>>>>>>>>>>>>>>>");
					if(totalNum != num){
						System.out.println("进入>>>>>>>>插入数据与实际不符");
						for(int m=0;m<tempList.size();m++){
							ItemBatchDetail itemBatchDetail = new ItemBatchDetail();
							itemBatchDetail.setOrderItemId(tempList.get(m).getOrderItemId());
							int cc = itemBatchDetailMapper.queryItemId(itemBatchDetail);//验证那条数据没有存入成功
							if(cc==0){
								itemBatchDetail.setOrderItemId(tempList.get(m).getOrderItemId());
								itemBatchDetail.seteOrdernumber(tempList.get(m).geteOrdernumber());
								itemBatchDetail.setOrderNo(tempList.get(m).getOrderNo());
								itemBatchDetail.setGoodsNo(tempList.get(m).getGoodsNo());
								itemBatchDetail.setGoodsNo69(tempList.get(m).getGoodsNo69());
								itemBatchDetail.setCostPrice(tempList.get(m).getCostPrice());
								itemBatchDetail.setGoodName(tempList.get(m).getGoodName());
								itemBatchDetail.setAmount(tempList.get(m).getAmount());
								itemBatchDetail.setStockStatus(tempList.get(m).getStockStatus());
								itemBatchDetail.setAddTime(addTime);
								itemBatchDetail.setSummary(summary);
								itemBatchDetail.setMulti_channel_id(mulId);
								itemBatchDetailMapper.saveWrongNoSales(itemBatchDetail);//将没有保存的数据重新插入数据库中
							}
						}
					}
				}
			} else{
				num = orderItemMapper.saveNoSaleWares(list); //将未销商品保存到item_batch_detail表中,并返回插入条数
				System.out.println("查出未销商品，将未销商品插入到数据库中，并成功插入"+num+"条数据>>>>>>>>>>>>>>>>>>>>>");
				totalNum = list.size();
				if(totalNum != num){
					for(int m=0;m<list.size();m++){
						ItemBatchDetail itemBatchDetail = new ItemBatchDetail();
						itemBatchDetail.setOrderItemId(list.get(m).getOrder_item_id());
						int cc = itemBatchDetailMapper.queryItemId(itemBatchDetail);//验证那条数据没有存入成功
						if(cc==0){
							itemBatchDetail.setOrderItemId(list.get(m).getOrder_item_id());
							itemBatchDetail.seteOrdernumber(list.get(m).getE_ordernumber());
							itemBatchDetail.setOrderNo(list.get(m).getOrder_no());
							itemBatchDetail.setGoodsNo(list.get(m).getGoods_no());
							itemBatchDetail.setGoodsNo69(list.get(m).getGoods_no_69());
							itemBatchDetail.setCostPrice(list.get(m).getCost_price());
							itemBatchDetail.setGoodName(list.get(m).getName());
							itemBatchDetail.setAmount(list.get(m).getAmount());
							itemBatchDetail.setStockStatus(list.get(m).getStock_status());
							itemBatchDetail.setAddTime(addTime);
							itemBatchDetail.setSummary(summary);
							itemBatchDetail.setMulti_channel_id(mulId);
							itemBatchDetailMapper.saveWrongNoSales(itemBatchDetail);//将没有保存的数据重新插入数据库中
						}
					}
				}	
			}
		}
		pageNo = nextPageNo();
		System.out.println("pageNo="+pageNo);
		if(pageNo < count){
			if(0==orderType){
				this.findNoSaleNormalWares(mulId);
			}else{
				this.findNoSaleReissueWares(mulId);
			}
			
		}
		
	}
	
	//汇总数据并更新
	private void summary(int mulId){
		List<ItemBatchDetail> li = new ArrayList<ItemBatchDetail>();
		List<ItemBatchIndex> ibi = new ArrayList<ItemBatchIndex>();
		System.out.println("未销商品已全部保存>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("查询销量商品>>>>>>>>>>>>>>>>>>>>>>>>>>");
		ItemBatchDetail itemBatchDetail = new ItemBatchDetail();
		String addTime = GetNowTime.getCurrentTime();
		itemBatchDetail.setMulti_channel_id(mulId);
		li = itemBatchDetailMapper.queryGoodsNo(itemBatchDetail);//查出每种商品的订单数量
		
		int billstates = 1;
		if(li != null && li.size()>0){
			ItemBatchIndex ItemBatchIndex=null;
			for(int i=0;i<li.size();i++){
				ItemBatchIndex = new ItemBatchIndex();
				ItemBatchIndex.setAddTime(addTime);
				ItemBatchIndex.setAmount(li.get(i).getAmount().intValue());
				ItemBatchIndex.setBillstates(billstates);
				ItemBatchIndex.setMultiChannelId(mulId);
				ItemBatchIndex.setGoodsno(li.get(i).getGoodsNo());
				ItemBatchIndex.setGoods69(li.get(i).getGoodsNo69());
				ItemBatchIndex.setSum_price(li.get(i).getSum_price());
				ibi.add(ItemBatchIndex);
			}
			for(ItemBatchIndex detail : ibi){
//					itemBatchDetailMapper.saveWaresIntoItem(li.get(index));//将商品订单数量汇总到item_batch_index表中能中
				//保存
				itemBatchIndexMapper.saveWaresIntoItem(detail);
//					itemBatchDetailMapper.updateByGrounpNo(li);
				itemBatchIndexMapper.updateByGrounpNo(detail);
			}
			System.out.println("将商品汇总，查看一共销售"+ibi.size()+"商品>>>>>>>>>>>>>>>>>");
		}
	}
	
	
	
	public static int nextPageNo(){
		return pageNo+100;
	}
	
	
	public static int total(int count){
		int total = count/100;
		int mod = count % 100;
		if(mod != 0){
			total ++;
		}
		return count == 0 ? 1:total;
	}
	@Override
	public int resetPageNo() {
		return this.pageNo=0;
	}
	
	/**
	 * 处理队列中保存错误的商品明细
	 * @author hhr
	 */
	private void dealOrderItemErrorQueue() {
		//如果为空,返回
		if(orderItemErrorQueue.isEmpty()){
			return ;
		}
		
		System.out.println("错误队列中一共的条数:"+orderItemErrorQueue.getLength());
		String addTime = GetNowTime.getCurrentTime();
		
		//开始单条保存
		for (int i = 0; i < orderItemErrorQueue.getLength(); i++) {
			OrderItem orderItem = orderItemErrorQueue.deQueue();
			try {
				
				ItemBatchDetail itemBatchDetail = new ItemBatchDetail();
				itemBatchDetail.setOrderItemId(orderItem.getOrder_item_id());
//				itemBatchDetail.seteOrdernumber(orderItem.getE_ordernumber());
				itemBatchDetail.setOrderNo(orderItem.getOrder_no());
				itemBatchDetail.setGoodsNo(orderItem.getGoods_no());
				itemBatchDetail.setGoodsNo69(orderItem.getGoods_no_69());
				itemBatchDetail.setCostPrice(orderItem.getCost_price());
				itemBatchDetail.setGoodName(orderItem.getName());
				itemBatchDetail.setAddTime(addTime);
				itemBatchDetail.setAmount(orderItem.getAmount());
				itemBatchDetail.setStockStatus(orderItem.getStock_status());
				itemBatchDetail.setSummary(orderItem.getSummary());
				itemBatchDetail.setMulti_channel_id(orderItem.getMulId());
				
				int cc = itemBatchDetailMapper.queryItemId(itemBatchDetail);//验证那条数据没有存入成功
				if(cc==0){
					itemBatchDetailMapper.saveWrongNoSales(itemBatchDetail);//将没有保存的数据重新插入数据库中
				}
			} catch (Exception e) {
				 logger.error("处理队列中保存错误的商品明细也失败了...order_no =  " + orderItem.getOrder_no() ,e);
			}
			
		}
	}
	
}
