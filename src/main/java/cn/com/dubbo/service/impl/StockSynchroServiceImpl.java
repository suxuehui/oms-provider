package cn.com.dubbo.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.bean.ItemBatchIndex;
import cn.com.dubbo.model.EOrderBatchNO;
import cn.com.dubbo.model2.E_salesorderbatchno;
import cn.com.dubbo.model2.E_salesorderdetail;
import cn.com.dubbo.service.inter.ItemBatchIndexService;
import cn.com.dubbo.service.inter.OutStockService;
import cn.com.dubbo.service.inter.StockSynchroService;
import cn.com.dubbo.util.DateUtil;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;


@Service
public class StockSynchroServiceImpl implements StockSynchroService{

	private static final Logger logger = Logger.getLogger(StockSynchroServiceImpl.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private OutStockService outStockService;
	
	@Resource
	private ItemBatchIndexService itemBatchIndexService;
	
	@Override
	public void synchro(int multiChannelId) {
		
		try {
			String number = "JY"+DateUtil.DateToStr(new Date(), "yyyyMMddHHmmss");
			
			//开始
			batchSynchro(number, multiChannelId);
			
			
			//查下oms统计的数量
			/*List<ItemBatchIndex> itemIndexList = itemBatchIndexService.querySaleorderList(Integer.toString(multiChannelId));
			if(null!=itemIndexList&&itemIndexList.size()>0){
				logger.info("同步库存更新开始,同步数据 "+itemIndexList.size()+"条");
				int amount = 0;
				int i=0;
				for(ItemBatchIndex batch : itemIndexList){
					//推送到中间库
					pushMedicine(number, batch, amount);
					logger.info("同步库存更新,同步数据 第 "+ i++ +" 条");
				}
				logger.info("同步库存更新结束,同步数据 第 "+i+" 条");
			}*/
			
		} catch (Exception e) {
			logger.info("向药医通中间库同步数据异常：");
			e.printStackTrace();
		}
	}
	
	//批量执行同步数
	@SuppressWarnings("unused")
	public void batchSynchro(String number,int multiChannelId){
		
		try {
			//查下oms统计的数量
			List<ItemBatchIndex> itemIndexList = itemBatchIndexService.querySaleorderList(Integer.toString(multiChannelId));
			if(null!=itemIndexList&&itemIndexList.size()>0){
				logger.info("同步库存更新开始,同步数据 "+itemIndexList.size()+"条");
				int amount = 0;
				int i=0;
				List<E_salesorderbatchno> batchNos=new ArrayList<E_salesorderbatchno>();
				List<ItemBatchIndex> itemBatchIndexs=new ArrayList<ItemBatchIndex>();
				List<E_salesorderdetail> salesorderDeatils=new ArrayList<E_salesorderdetail>();
				
				String ordernumber = "";
				if(multiChannelId==1){
					ordernumber = number +1;
				}else if(multiChannelId==2){
					ordernumber = number +1;
				}else if(multiChannelId==3){
					ordernumber = number +3;
				}else if(multiChannelId==4){
					ordernumber = number +4;
				}else if(multiChannelId==5){
					ordernumber = number +5;
				}else if(multiChannelId==8){
					ordernumber = number +8;
				}
				
				for(ItemBatchIndex batch : itemIndexList){
					//推送到中间库，组装数据
					batchDataCollection(ordernumber, batch, amount,batchNos,itemBatchIndexs,salesorderDeatils);
					logger.info("同步库存组装数据 第 "+ i++ +" 条");
				}
				//保存中间库--------开始---------------
				if(batchNos==null&&batchNos.size()==0){
					new Exception("同步库存查询出的数据为空，请检查渠道："+multiChannelId+" ,日期："+number);
				}
				
				batchSaveData(ordernumber, multiChannelId, batchNos, itemBatchIndexs, salesorderDeatils);
				
				//保存中间库--------结束---------------
				//更新自己数据库
				if(itemBatchIndexs!=null&&itemBatchIndexs.size()>0){
					itemBatchIndexService.batchUpdateBillStatus(itemIndexList);
				}
				logger.info("同步库存更新结束,同步数据 第 "+i+" 条");
			}
		} catch (Exception e) {
			logger.error("像202中间库同步库存异常"+e.getMessage(),e);
		}
	}
	
	//向中间库批量保存数据，作为单独的一个事物
	@Transactional
	public void batchSaveData(String ordernumber,int multiChannelId,List<E_salesorderbatchno> batchNos,List<ItemBatchIndex> itemBatchIndexs,
			List<E_salesorderdetail> salesorderDeatils){
		
		if(batchNos!=null&&batchNos.size()>0){
			outStockService.batchInsertNo(batchNos);
		}
		if(salesorderDeatils!=null&&salesorderDeatils.size()>0){
			outStockService.saveBatchOrderdetail(salesorderDeatils);
		}
		//保存
		boolean bl = outStockService.findOrderState(ordernumber);
		if(!bl){
			//保存订单状态表
			outStockService.saveOrderState(ordernumber);
			outStockService.saveOrderIndex(multiChannelId, ordernumber);
		}
		
	}
	
	
	
	//批量组装数据
	public void batchDataCollection(String ordernumber,ItemBatchIndex batch,int amount,
			List<E_salesorderbatchno> batchNos,List<ItemBatchIndex> itemBatchIndexs,List<E_salesorderdetail> salesorderDeatils){

		
		//查询可用库存（中间库该商品的库存总量-中间库未销账的商品总量）
		EOrderBatchNO batchNo = outStockService.findGoodsTotalNum(batch.getGoodsno());//总数
		if(batchNo!=null){
			//中间库未销账的库存
			int notOffStock = outStockService.findNOffStockAmount(batch.getGoodsno());
			//计算可用库存
			int remainNum = batchNo.getQuantity()-notOffStock;
			amount = batch.getAmount();
			if(amount<=remainNum){
				List<EOrderBatchNO> stockList = outStockService.queryStockList(batch.getGoodsno());
				if(null!=stockList&&stockList.size()>0){
					
					boolean isexist = true;
					
					for(EOrderBatchNO stock :stockList){
						//判断药医通e_salesorderbatchno是否已经存在
						if(!isexist){
							continue;
						}
						int isSaveNum = outStockService.findBatchNo(stock.getProductId(),stock.getBatchno());
						int batchQuantity = 0;
						if(isSaveNum>0){
							batchQuantity = stock.getQuantity()-isSaveNum;
						}else{
							batchQuantity = stock.getQuantity();
						}
						if(batchQuantity>0){
							if(amount>batchQuantity){
								//订单产品数量<批次库存的值#{eOrderNumber},#{productId},#{batchno},#{quantity}
								E_salesorderbatchno record = new E_salesorderbatchno();
								record.seteOrdernumber(ordernumber);
								record.setpId(Integer.toString(stock.getProductId()));
								if(!StringUtil.isBlank(stock.getBatchno())){
									record.setBatchno(stock.getBatchno());
								}
								record.setQuantity(new Long(batchQuantity));
								record.setDeleted(0);
								amount = amount - batchQuantity;
								//save
								batchNos.add(record);
							}else{
								E_salesorderbatchno record = new E_salesorderbatchno();
								record.seteOrdernumber(ordernumber);
								record.setpId(Integer.toString(stock.getProductId()));
								if(!StringUtil.isBlank(stock.getBatchno())){
									record.setBatchno(stock.getBatchno());
								}
								record.setQuantity(new Long(amount));
								record.setDeleted(0);
								//save
								batchNos.add(record);
								isexist = false;
							}
						}
					}
				}
				//更新状态
				batch.setBillstates(5);
				batch.seteOrdernumber(ordernumber);
				itemBatchIndexs.add(batch);
				//保存数据到e_salesorderdetail
				salesorderDeatils = this.savebatchOrderdetail(ordernumber,batchNo.getProductId(),batch.getAmount(),batch.getSum_price(), salesorderDeatils);
			}
		}
	}
	
	
	public static List<E_salesorderdetail> savebatchOrderdetail(String eOrdernumber,int productId,int amount,BigDecimal sumPrice,List<E_salesorderdetail> details){
		try {
			E_salesorderdetail record = new E_salesorderdetail();
			record.seteOrdernumber(eOrdernumber);
			record.setpId(Integer.toString(productId));
			record.setQuantity(new Long(amount));
			if(sumPrice!=null){
				record.setSaleprice(MathUtil.div(sumPrice,new BigDecimal(amount),4));
				record.setTotalmoney(sumPrice);
			}else{
				record.setSaleprice(new BigDecimal(0));
				record.setTotalmoney(new BigDecimal(0));
			}
			record.setDeleted(0);
			details.add(record);
			return details;
		} catch (Exception e) {
			logger.error("参数：eOrdernumber："+eOrdernumber+",productId"+productId+",amount"+amount+",sumPrice"+sumPrice);
		}
		return null;
	}
	
	
	/**
	 * 推送药医通数据
	 * @param number 生成的批号
	 * @param batch 要保存的商品
	 * @param amount 初始化商品数
	 * @param i 计算次数
	 */
	@Transactional
	public void pushMedicine(String number,ItemBatchIndex batch,int amount){

		String ordernumber = "";
		if(batch.getMultiChannelId()==1){
			ordernumber = number +1;
		}else if(batch.getMultiChannelId()==2){
			ordernumber = number +1;
		}else if(batch.getMultiChannelId()==3){
			ordernumber = number +3;
		}else if(batch.getMultiChannelId()==4){
			ordernumber = number +4;
		}else if(batch.getMultiChannelId()==5){
			ordernumber = number +5;
		}
		
		//查询可用库存（中间库该商品的库存总量-中间库未销账的商品总量）
		EOrderBatchNO batchNo = outStockService.findGoodsTotalNum(batch.getGoodsno());//总数
		if(batchNo!=null){
			//中间库未销账的库存
			int notOffStock = outStockService.findNOffStockAmount(batch.getGoodsno());
			
			//计算可用库存
			int remainNum = batchNo.getQuantity()-notOffStock;
			
			amount = batch.getAmount();
			EOrderBatchNO orderBatchNO = null;
			if(amount<=remainNum){
				List<EOrderBatchNO> stockList = outStockService.queryStockList(batch.getGoodsno());
				if(null!=stockList&&stockList.size()>0){
					
					boolean isexist = true;
					
					for(EOrderBatchNO stock :stockList){
						//判断药医通e_salesorderbatchno是否已经存在
						if(!isexist){
							continue;
						}
						int isSaveNum = outStockService.findBatchNo(stock.getProductId(),stock.getBatchno());
						int batchQuantity = 0;
						if(isSaveNum>0){
							batchQuantity = stock.getQuantity()-isSaveNum;
						}else{
							batchQuantity = stock.getQuantity();
						}
						if(batchQuantity>0){
							if(amount>batchQuantity){
								//订单产品数量<批次库存的值#{eOrderNumber},#{productId},#{batchno},#{quantity}
								orderBatchNO = new EOrderBatchNO();
								orderBatchNO.seteOrderNumber(ordernumber);
								orderBatchNO.setProductId(stock.getProductId());
								if(!StringUtil.isBlank(stock.getBatchno())){
									orderBatchNO.setBatchno(stock.getBatchno());
								}
								orderBatchNO.setQuantity(batchQuantity);
								amount = amount - batchQuantity;
								//save
								outStockService.saveSalesOrderBatchNo(orderBatchNO);
							}else{
								orderBatchNO = new EOrderBatchNO();
								orderBatchNO.seteOrderNumber(ordernumber);
								orderBatchNO.setProductId(stock.getProductId());
								if(!StringUtil.isBlank(stock.getBatchno())){
									orderBatchNO.setBatchno(stock.getBatchno());
								}
								orderBatchNO.setQuantity(amount);
								//save
								outStockService.saveSalesOrderBatchNo(orderBatchNO);
								isexist = false;
							}
						}
					}
				}
				//更新状态
				batch.setBillstates(5);
				batch.seteOrdernumber(ordernumber);
				itemBatchIndexService.updateBillStatus(batch);
				//保存数据到e_salesorderdetail
				outStockService.saveOrderdetail(ordernumber,batchNo.getProductId(),batch.getAmount(),batch.getSum_price());
			}
		}
		//保存
		boolean bl = outStockService.findOrderState(ordernumber);
		if(!bl){
			//保存订单状态表
			outStockService.saveOrderState(ordernumber);
			outStockService.saveOrderIndex(batch.getMultiChannelId(), ordernumber);
		}
		
	}
	
}
