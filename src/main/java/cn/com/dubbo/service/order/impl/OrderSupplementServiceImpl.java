package cn.com.dubbo.service.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.model.GoodsChannelPrice;
import cn.com.dubbo.model.GoodsGroupItemModel;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.service.inter.GoodsChannelService;
import cn.com.dubbo.service.inter.GoodsService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.service.order.OrderStepService;
import cn.com.dubbo.service.order.OrderSupplementService;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;

@Service
public class OrderSupplementServiceImpl implements OrderSupplementService {

	@Resource
	private GoodsService goodsService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private GoodsChannelService goodsChannelService;
	
	@Resource
	private OrderStepService orderStepService;//审核类
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static int pageSize=50;
	
	private static final Logger logger = Logger.getLogger(OrderSupplementServiceImpl.class);
	
	@Override
	public String orderSupplement(String multiChannel,int orderState,int pageIndex){
		try {
			//查询数据参数
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("orderState", orderState);//
			params.put("pageIndex", pageIndex);//从第0页开始查询
			params.put("pageSize", pageSize);
			if(multiChannel.equals(ChannelConstant.CHANNEL_PA)){
				params.put("multiChannelId", 4);
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_36)){
				params.put("multiChannelId", 3);
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_JD)){
				params.put("multiChannelId", 2);
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_TM)){
				params.put("multiChannelId", 1);
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_PS)){
				params.put("multiChannelId", 5);
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_PD)){
				params.put("multiChannelId", 9);
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_GW)){
				params.put("multiChannelId", 7);
			}
			
			if(orderState==OrderState.STATE_3.getCode()){
				orderService.deleteExamineFailOrder();
			}
			
			return this.pageQueryTemp(multiChannel,params,pageIndex);
		} catch (Exception e) {
			logger.error("订单审核过程异常...,错误： " + e.getMessage(),e);
			return "error";
		}
	}
	
	private String pageQueryTemp(String multiChannel,Map<String,Object> params,int pageIndex){
		
		List<OrderModel> orderList = orderService.pageQueryOrderList(params);
		
		if(null!=orderList&&orderList.size()>0){
			
			List<OrderItemModel> itemList = new ArrayList<OrderItemModel>();
			for(OrderModel orderModel : orderList){
				//拆分补充订单明细数据
				this.updateData(orderModel,itemList);
			}
			//批量保存数据
			this.updateBatchOrderItem(itemList);
			
			//保存成功，进行拆分
			for(OrderModel orderModel : orderList){
				//拆分补充订单明细数据
				this.dealSupplement(orderModel);
			}
			logger.info(multiChannel+" 订单拆分完毕,开始审核第"+ pageIndex +"页....");
			//拆分成功，进行审核
			orderStepService.orderNotRXExamine(orderList);
			return "page";
		}else{
			return "noPage";
		}
	}
	
	@Override
	public void orderDataUpdate(OrderModel orderModel){
		List<OrderItemModel> itemList = new ArrayList<OrderItemModel>();
		//拆分补充订单明细数据
		this.updateData(orderModel,itemList);
		//批量保存数据
		this.updateBatchOrderItem(itemList);
	}
	
	private void updateBatchOrderItem(List<OrderItemModel> itemList){
		DataQueue<OrderItemModel> queue = new DataQueue<OrderItemModel>();
		try {
			orderService.updateBatchOrderItem(itemList);
		} catch (Exception e) {
			if(null!=itemList&&itemList.size()>0){
				for(OrderItemModel model : itemList){
					logger.error("订单保存数据库错误 ，错误订单号："+model.getOrderNo());
					queue.enQueue(model);
				}
			}
			logger.error("订单补充数据更新时错误 ，错误条目："+itemList.size()+" ,错误信息："+e.getMessage(),e);
		}
		
		while(!queue.isEmpty()){
			orderService.updateOrderItem(queue.deQueue());
		}
	}
	
	/*
     * 总页数
     * @params totalCount 信息总条数
     * @return 总页码
     */
    public static int getPageCount(int totalCount,int pageSize) {
    	
        int size = totalCount/pageSize;//总条数/每页显示的条数=总页数
        int mod = totalCount % pageSize;//最后一页的条数
        if(mod != 0)
            size++;
        return totalCount == 0 ? 1 : size;
        
    } 

	/*private static int getStart(int curPage,int pageSize) {
		int start = 0;
		if (curPage < 1) {
		    curPage = 1;
		} else {
		    start = pageSize * (curPage - 1);
		}
		return start;
	}*/
	
	//补充数据
	private void updateData(OrderModel orderModel,List<OrderItemModel> batchList){
		try {
			List<OrderItemModel> itemList = orderService.queryOrderItems(orderModel.getOrderNo());
			//补充数据
			this.updateItemPrice(itemList, orderModel,batchList);
		} catch (Exception e) {
			logger.error("订单数据补充："+e.getMessage(),e);
		}
		
	}
	
	//拆分数据
	@Override
	public void dealSupplement(OrderModel orderModel){
		try {
			List<OrderItemModel> itemList = orderService.queryOrderItems(orderModel.getOrderNo());
			//拆分
			this.doSupplement(itemList,orderModel);
		} catch (Exception e) {
			logger.error("订单数据补充和拆分时报错，订单号："+orderModel.getOrderNo() +" , 错误信息："+e.getMessage(),e);
		}
		
	}
	
	private void updateItemPrice(List<OrderItemModel> itemList,OrderModel orderModel,List<OrderItemModel> batchList){
		
		int temp = 0;//校验是否已经补充 过数据
		if(null!=itemList&&itemList.size()>0){
			//过滤已经补充过的数据
			for(OrderItemModel item : itemList){
				if((!StringUtil.isBlank(item.getStandard()))||Constant.YES.equals(item.getGroupStatus())){
					temp = 1;//补充过
					break;
				}
			}
		}
		
		//售卖总价格
		if(null!=itemList&&itemList.size()==1&&temp==0){
			OrderItemModel tempItem = itemList.get(0);
			if(!StringUtil.isBlank(tempItem.getGoodsNo_69())||!StringUtil.isBlank(tempItem.getGoodsNo())){
				if(Constant.GOODS_TYPE_GENERAL.equals(tempItem.getGoodsListType())){
					GoodsModel model = null;
					if(!StringUtil.isBlank(tempItem.getGoodsNo())){
						model = goodsService.findGoodNo(tempItem.getGoodsNo());
					}else{
						model = goodsService.findGood69(tempItem.getGoodsNo_69());
					}
					if(null==model){
						logger.error("补充订单数据中，该订单号 ："+tempItem.getOrderNo()+" 对应的商品69码： "+ tempItem.getGoodsNo_69() +"在商品基础信息表goods中不存在 ");
					}else{
						tempItem.setGoodsNo(model.getGoodsNo());
						tempItem.setGoodsNo_69(model.getGoodsNo_69());
						tempItem.setStandard(model.getStandard());
						tempItem.setCostPrice(model.getCostPriceAverage());
						//是否是处方药
						if("1".equals(model.getOtcFlag())){//是
							tempItem.setGoodsType(Constant.GOODS_TYPE_RX);
							orderModel.setHaveCfy(Constant.YES);
						}else{
							tempItem.setGoodsType(Constant.GOODS_TYPE_OTHER);
						}
					}
					//查下供货价格
					GoodsChannelPrice goodsChannel = goodsChannelService.findGCPByGoodsNo(orderModel.getMultiChannelId(), tempItem.getGoodsNo());
					if(goodsChannel!=null&&!StringUtil.isBlank(goodsChannel.getGoodsNo())){
						tempItem.setSupplyPrice(goodsChannel.getSupplyPrice());
					}else{
						tempItem.setSupplyPrice(new BigDecimal(0));
					}
				}
				
				if(null==orderModel.getPaidFee()){
					orderModel.setPaidFee(new BigDecimal(0));
				}
				tempItem.setGoodsSumFee(orderModel.getPaidFee());
				tempItem.setPrice(MathUtil.div(orderModel.getPaidFee(), new BigDecimal(tempItem.getAmount()), 4));
				tempItem.setPriceDis(orderModel.getActivityDiscountFee());
				
				//更新订单明细
				batchList.add(tempItem);
			}
		}
		if(null!=itemList&&itemList.size()>1&&temp==0){
			BigDecimal sumSalePrice = BigDecimal.ZERO;
			for(OrderItemModel item : itemList){
				sumSalePrice = MathUtil.add(sumSalePrice, MathUtil.mul(item.getOldPrice(), new BigDecimal(item.getAmount())));
			}
			int i=0;
			//goodsSumFee
			BigDecimal sumFee = BigDecimal.ZERO;
			BigDecimal sumAllFee = BigDecimal.ZERO;
			//priceDis
			BigDecimal priceDis = BigDecimal.ZERO;
			BigDecimal sumDis = BigDecimal.ZERO;
			GoodsModel model = null;
			BigDecimal radio = BigDecimal.ZERO;
			for(OrderItemModel item : itemList){
				
				radio = MathUtil.div(item.getOldPrice(), sumSalePrice, 4);
				if(i!=itemList.size()-1){
					//goodsSumFee
					sumFee = MathUtil.mul(orderModel.getPaidFee(), radio);
					item.setGoodsSumFee(sumFee);
					sumAllFee = MathUtil.add(sumAllFee, sumFee);//累加
					//priceDis
					//京东需要特殊判断
					if(2==orderModel.getMultiChannelId()){
						if(null==item.getPriceDis()||MathUtil.compare(item.getPriceDis(), new BigDecimal("0"))==0){
							priceDis = MathUtil.mul(orderModel.getActivityDiscountFee(), radio);
							sumDis = MathUtil.add(sumDis, priceDis);//累加
							item.setPriceDis(priceDis);
						}
					}else{
						priceDis = MathUtil.mul(orderModel.getActivityDiscountFee(), radio);
						sumDis = MathUtil.add(sumDis, priceDis);//累加
						item.setPriceDis(priceDis);
					}
				}
				
				if(i==itemList.size()-1){
					item.setGoodsSumFee(MathUtil.sub(orderModel.getPaidFee(), sumAllFee));
					if(2==orderModel.getMultiChannelId()){
						if(null==item.getPriceDis()||MathUtil.compare(item.getPriceDis(), new BigDecimal("0"))==0){
							item.setPriceDis(MathUtil.sub(orderModel.getActivityDiscountFee(), sumDis));
						}
					}else{
						item.setPriceDis(MathUtil.sub(orderModel.getActivityDiscountFee(), sumDis));
					}
				}
				
				item.setPrice(MathUtil.div(item.getGoodsSumFee(), new BigDecimal(item.getAmount()), 4));
				
				if(Constant.GOODS_TYPE_GENERAL.equals(item.getGoodsListType())){
					if(!StringUtil.isBlank(item.getGoodsNo())){
						model = goodsService.findGoodNo(item.getGoodsNo());
					}else{
						model = goodsService.findGood69(item.getGoodsNo_69());
					}
					if(null==model){
						logger.error("补充订单数据中，该订单号 ："+item.getOrderNo()+" 对应的商品69码： "+ item.getGoodsNo_69() +"在商品基础信息表goods中不存在 ");
					}else{
						item.setGoodsNo(model.getGoodsNo());
						item.setGoodsNo_69(model.getGoodsNo_69());
						item.setStandard(model.getStandard());
						item.setCostPrice(model.getCostPriceAverage());
						//是否是处方药
						if("1".equals(model.getOtcFlag())){//是
							item.setGoodsType(Constant.GOODS_TYPE_RX);
							orderModel.setHaveCfy(Constant.YES);
						}else{
							item.setGoodsType(Constant.GOODS_TYPE_OTHER);
						}
					}
				}
				
				//查下供货价格
				GoodsChannelPrice goodsChannel = goodsChannelService.findGCPByGoodsNo(orderModel.getMultiChannelId(), item.getGoodsNo());
				if(goodsChannel!=null&&!StringUtil.isBlank(goodsChannel.getGoodsNo())){
					item.setSupplyPrice(goodsChannel.getSupplyPrice());
				}else{
					item.setSupplyPrice(new BigDecimal(0));
				}
				
				batchList.add(item);
				i++;
			}
		}
	}
	
	
	/**
	 * 执行拆分规则
	 */
	@Transactional
	public void doSupplement(List<OrderItemModel> itemList,OrderModel orderModel){
		
		if(null!=itemList && itemList.size()>0){
			for(OrderItemModel item : itemList){
				//平安的
				if(Constant.GOODS_TYPE_GROUP.equals(item.getGoodsListType())
						&&!Constant.YES.equals(item.getGroupStatus())){
					List<GoodsGroupItemModel> groupItemList = null;
					if(!StringUtil.isBlank(item.getGoodsNo())){
						groupItemList = goodsService.queryGroupItemByGoodsNo(item.getGoodsNo());
					}else{
						groupItemList = goodsService.queryGroupItemByNo69(item.getGoodsNo_69());
					}
					if(null!=groupItemList&&groupItemList.size()>0){
						BigDecimal costPrice = BigDecimal.ZERO;
						for(GoodsGroupItemModel groupItem : groupItemList){
							if(null!=groupItem.getCostPriceAverage()){
								costPrice = MathUtil.add(costPrice, MathUtil.mul(groupItem.getCostPriceAverage(), new BigDecimal(groupItem.getAmount())));
							}
							//保存结算价
							if(null!=groupItem.getSupplyPrice()){
								item.setSupplyPrice(groupItem.getSupplyPrice());
							}
						}
						item.setCostPrice(costPrice);
						if(null==item.getGoodsSumFee()){
							item.setGoodsSumFee(new BigDecimal(0));
						}
						
						//拆分组合订单
						List<OrderItemModel> tempList = this.splitGroupItem(item,groupItemList,orderModel);
						//保存订单明细
						this.batchSaveOrderItem(tempList);
						item.setGoodsNo(groupItemList.get(0).getGoodsNo());
						item.setGoodsNo_69(groupItemList.get(0).getGoodsNo_69());
						item.setGroupStatus(Constant.YES);
						//更新订单明细
						orderService.updateOrderItem(item);
					}else{
						logger.error("拆分item组合套餐中，该订单号 ："+item.getOrderNo()+" 对应的商品69码： "+ item.getGoodsNo_69() +"在商品组合信息表goods_group_item中不存在 ");
					}
				}
			}
		}
	}
	
	/**
	 * 拆分组合订单
	 */
	public List<OrderItemModel> splitGroupItem(OrderItemModel itemModel,
			List<GoodsGroupItemModel> groupItemList,OrderModel orderModel){
		
		//批量存入拆分数据
		List<OrderItemModel> tempList = new ArrayList<OrderItemModel>();
		
		//拆分产品新条目的总价格
		BigDecimal oldAllPrice = BigDecimal.ZERO;
		if(null!=groupItemList&&groupItemList.size()>0){
			OrderItemModel tempItem = null;
			BigDecimal oldPrice = BigDecimal.ZERO;//原价
			//原始总价
			BigDecimal oldActualPrice = MathUtil.mul(itemModel.getOldPrice(), new BigDecimal(itemModel.getAmount()));
			
			BigDecimal sumOldPrice = BigDecimal.ZERO;//原价之和
			int i=1;
			BigDecimal costPriceRadio = BigDecimal.ZERO;
			for(GoodsGroupItemModel groupModel : groupItemList){
				tempItem = new OrderItemModel();
				tempItem.setOrderNo(itemModel.getOrderNo());
				tempItem.setTmSource(groupModel.getGoodsNo_69());
				tempItem.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
				if(0==groupModel.getOtcFlag()){//不是
					tempItem.setGoodsType(Constant.GOODS_TYPE_OTHER);//非处方
				}else{
					tempItem.setGoodsType(Constant.GOODS_TYPE_RX);//处方
					orderModel.setHaveCfy(Constant.YES);
				}
				tempItem.setAddTime(sdf.format(new Date()));
				tempItem.setAmount(new Long(groupModel.getAmount()*itemModel.getAmount()));
				tempItem.setGoodsNo(groupModel.getSubGoodsNo());
				tempItem.setGoodsNo_69(groupModel.getSubGoodsNo_69());
				tempItem.setName(groupModel.getName());
				tempItem.setStandard(groupModel.getStandard());
				tempItem.setCostPrice(groupModel.getCostPriceAverage());
				
				//商品原价 按照成本价比例计算
				if(null!=groupModel.getCostPriceAverage()){
					//成本价比例
					if(null!=itemModel.getCostPrice()&&(MathUtil.compare(itemModel.getCostPrice(), new BigDecimal(0))!=0)){
						costPriceRadio = MathUtil.div(groupModel.getCostPriceAverage(), itemModel.getCostPrice(),5);
					}
					if(0==MathUtil.compare(costPriceRadio, new BigDecimal(1))){
						costPriceRadio = new BigDecimal(0);
					}
				}
				
				int comValue = MathUtil.compare(costPriceRadio, BigDecimal.ZERO);
				
				if(comValue>0&&i!=groupItemList.size()){
					oldPrice = MathUtil.mul(oldActualPrice, costPriceRadio);
					sumOldPrice = MathUtil.add(sumOldPrice, oldPrice);
				}
				
				if(i==groupItemList.size()){
					//原价
					tempItem.setOldPrice(MathUtil.div(MathUtil.sub(oldActualPrice, sumOldPrice), 
							new BigDecimal(groupModel.getAmount()*itemModel.getAmount()), 4));
				}else{
					tempItem.setOldPrice(MathUtil.div(oldPrice, new BigDecimal(groupModel.getAmount()*itemModel.getAmount()), 4));
				}
				oldAllPrice = MathUtil.add(oldAllPrice, tempItem.getOldPrice());
				tempItem.setGroupStatus(Constant.YES);
				tempList.add(tempItem);
				i++;
			}
			//分拆实际支付的价格：按照商品的售卖价格比例拆分（oldPrice）
			if(null!=tempList&&tempList.size()>0){
				//goodsSumFee
				BigDecimal sumFee = BigDecimal.ZERO;
				BigDecimal allSumFee = BigDecimal.ZERO;//总价
				//priceDis 优惠价格
				BigDecimal priceDis = BigDecimal.ZERO;
				BigDecimal sumDis = BigDecimal.ZERO;//总价
				
				//supplyPrice 结算价格
				BigDecimal supplyPrice = BigDecimal.ZERO;
				BigDecimal sumSupply = BigDecimal.ZERO;//总价
				
				int j=1;
				BigDecimal ratio = BigDecimal.ZERO;
				for(OrderItemModel item : tempList){
					ratio = MathUtil.div(MathUtil.mul(item.getOldPrice(), new BigDecimal(item.getAmount())), oldAllPrice, 4);
					/*if(MathUtil.compare(ratio, new BigDecimal(0))>0){
					}*/
					if(0==MathUtil.compare(ratio, new BigDecimal(1))){//比例系数等于1，则设置为0
						ratio = new BigDecimal(0);
					}
					
					if(j!=groupItemList.size()){
						sumFee = MathUtil.mul(itemModel.getGoodsSumFee(), ratio);
						allSumFee = MathUtil.add(allSumFee,sumFee);
						
						priceDis = MathUtil.mul(itemModel.getPriceDis(), ratio);
						sumDis = MathUtil.add(sumDis,priceDis);
						
						if(null!=itemModel.getSupplyPrice()){
							supplyPrice = MathUtil.mul(itemModel.getSupplyPrice(), ratio);
							sumSupply = MathUtil.add(sumSupply,supplyPrice);
						}
					}
					
					if(j==groupItemList.size()){//最后一个
						item.setGoodsSumFee(MathUtil.sub(itemModel.getGoodsSumFee(), allSumFee));
						item.setPriceDis(MathUtil.sub(itemModel.getPriceDis(), sumDis));
						
						if(null!=itemModel.getSupplyPrice()){
							item.setSupplyPrice(MathUtil.mul(MathUtil.div(MathUtil.sub(itemModel.getSupplyPrice(), sumSupply), 
									new BigDecimal(item.getAmount()), 4), new BigDecimal(itemModel.getAmount())));
						}
					}else{
						item.setGoodsSumFee(sumFee);
						item.setPriceDis(priceDis);
						
						if(null!=itemModel.getSupplyPrice()){
							item.setSupplyPrice(MathUtil.mul(MathUtil.div(supplyPrice, new BigDecimal(item.getAmount()), 4), 
									new BigDecimal(itemModel.getAmount())));
						}
					}
					item.setPrice(MathUtil.div(item.getGoodsSumFee(), new BigDecimal(item.getAmount()), 4));
					j++;
				}
			}
		}
		return tempList;
	}
	
	//批量保存
	private void batchSaveOrderItem(List<OrderItemModel> itemList){
		try {
			if(null!=itemList && itemList.size()>0){
				orderMapper.saveBatchOrderItem(itemList);
			}
		} catch (Exception e) {
			if(null!=itemList&&itemList.size()>0){
				for(OrderItemModel model : itemList){
					logger.error("拆分item组合套餐批量保存数据库错误 ，错误订单号："+model.getOrderNo());
				}
			}
			logger.error("拆分item组合套餐批量保存数据库错误 ，错误条目："+itemList.size()+" ,错误信息："+e.getMessage(),e);
		}
	}
	
	/**
	 * 天猫补充数据专用
	 * @param tempList
	 * @param groupItemModel
	 * @return
	 */
	@Override
	public List<OrderItemModel> tmData(List<OrderItemModel> tempList,OrderItemModel groupItemModel){
		//分拆实际支付的价格：按照商品的售卖价格比例拆分（oldPrice）
		if(null!=tempList&&tempList.size()>0){
			//goodsSumFee
			BigDecimal sumFee = BigDecimal.ZERO;
			BigDecimal allSumFee = BigDecimal.ZERO;//总价
			//priceDis 优惠价格
			BigDecimal priceDis = BigDecimal.ZERO;
			BigDecimal sumDis = BigDecimal.ZERO;//总价
			
			//oldPrice 售价
			BigDecimal oldPrice = BigDecimal.ZERO;
			BigDecimal sumOldPrice = BigDecimal.ZERO;//总价
			
			//supplyPrice 结算价格
			BigDecimal supplyPrice = BigDecimal.ZERO;
			BigDecimal sumSupply = BigDecimal.ZERO;//总价
			System.out.println(groupItemModel.getOrderNo()+":"+groupItemModel.getGoodsNo_69());
			int j=1;
			BigDecimal ratio = BigDecimal.ZERO;
			BigDecimal costAllPrice = BigDecimal.ZERO;
			int listSize = 0;
			for(OrderItemModel item : tempList){
				if(null!=item.getTmSource()
						&&item.getTmSource().equals(groupItemModel.getGoodsNo_69())){//比对数据
					if(null!=item.getCostPrice()){
						costAllPrice = MathUtil.add(costAllPrice,  MathUtil.mul(item.getCostPrice(), new BigDecimal(item.getAmount())));
						listSize++;
					}
				}
			}
			
			groupItemModel.setCostPrice(MathUtil.div(costAllPrice, new BigDecimal(groupItemModel.getAmount()),4));
			
			orderService.updateOrderItem(groupItemModel);
			
			for(OrderItemModel item : tempList){
				if(null!=item.getTmSource()
						&&item.getTmSource().equals(groupItemModel.getGoodsNo_69())){//比对数据
					if(null!=item.getCostPrice()&&MathUtil.compare(item.getCostPrice(), new BigDecimal(0))>0){
						if(null!=item.getCostPrice()){
							ratio = MathUtil.div(MathUtil.mul(item.getCostPrice(), new BigDecimal(item.getAmount())), costAllPrice, 4);
						}
						if(MathUtil.compare(ratio, new BigDecimal(0))>0){
							
						}
						/*if(0==MathUtil.compare(ratio, new BigDecimal(1))){//比例系数等于1，则设置为0
							ratio = new BigDecimal(0);
						}*/
						
						if(j!=listSize){
							sumFee = MathUtil.mul(groupItemModel.getGoodsSumFee(), ratio);
							allSumFee = MathUtil.add(allSumFee,sumFee);
							
							priceDis = MathUtil.mul(groupItemModel.getPriceDis(), ratio);
							sumDis = MathUtil.add(sumDis,priceDis);
							
							//售价
							oldPrice = MathUtil.mul(groupItemModel.getOldPrice(), ratio);
							sumOldPrice = MathUtil.add(sumOldPrice,oldPrice);
							
							if(null!=groupItemModel.getSupplyPrice()){
								supplyPrice = MathUtil.mul(groupItemModel.getSupplyPrice(), ratio);
								sumSupply = MathUtil.add(sumSupply,supplyPrice);
							}
						}
						
						if(j==listSize){//最后一个
							item.setGoodsSumFee(MathUtil.sub(groupItemModel.getGoodsSumFee(), allSumFee));
							item.setPriceDis(MathUtil.sub(groupItemModel.getPriceDis(), sumDis));
							
							item.setOldPrice(MathUtil.mul(MathUtil.div(MathUtil.sub(groupItemModel.getOldPrice(), sumOldPrice), 
									new BigDecimal(item.getAmount()), 4), new BigDecimal(groupItemModel.getAmount())));
							
							if(null!=groupItemModel.getSupplyPrice()){
								item.setSupplyPrice(MathUtil.mul(MathUtil.div(MathUtil.sub(groupItemModel.getSupplyPrice(), sumSupply), 
										new BigDecimal(item.getAmount()), 4), new BigDecimal(groupItemModel.getAmount())));
							}
							
						}else{
							item.setGoodsSumFee(sumFee);
							item.setPriceDis(priceDis);
							item.setOldPrice(MathUtil.mul(MathUtil.div(oldPrice, new BigDecimal(item.getAmount()), 4), 
									new BigDecimal(groupItemModel.getAmount())));
							
							if(null!=groupItemModel.getSupplyPrice()){
								item.setSupplyPrice(MathUtil.mul(MathUtil.div(supplyPrice, new BigDecimal(item.getAmount()), 4), 
										new BigDecimal(groupItemModel.getAmount())));
							}
						}
						item.setPrice(MathUtil.div(item.getGoodsSumFee(), new BigDecimal(item.getAmount()), 4));
					}
					j++;
				}
			}
			return tempList;
		}
		
		return null;
	}
	
}
