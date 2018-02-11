package cn.com.dubbo.service.impl;

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

import cn.com.dubbo.mapper2.E_PstockMapper;
import cn.com.dubbo.mapper2.E_orderstateMapper;
import cn.com.dubbo.mapper2.E_saleorderindexMapper;
import cn.com.dubbo.mapper2.E_salesorderbatchnoMapper;
import cn.com.dubbo.mapper2.E_salesorderdetailMapper;
import cn.com.dubbo.model.EOrderBatchNO;
import cn.com.dubbo.model2.E_Pstock;
import cn.com.dubbo.model2.E_orderstate;
import cn.com.dubbo.model2.E_saleorderindex;
import cn.com.dubbo.model2.E_salesorderbatchno;
import cn.com.dubbo.model2.E_salesorderdetail;
import cn.com.dubbo.service.inter.OutStockService;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;


@Service
public class OutStockServiceImpl implements OutStockService{

	private static final Logger logger = Logger.getLogger(OutStockServiceImpl.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource
	private E_PstockMapper pstockMapper;
	
	@Resource
	private E_salesorderbatchnoMapper esalesorderbatchnoMapper;
	
	@Resource
	private E_salesorderdetailMapper esalesorderdetailMapper;
	
	@Resource
	private E_orderstateMapper eorderstateMapper;
	
	@Resource
	private E_saleorderindexMapper esaleorderindexMapper;
	
	@Override
	public EOrderBatchNO findGoodsTotalNum(String goodsNo) {
		//查询商品入库总数
		E_Pstock pstock= pstockMapper.findGoodsTotalNum(goodsNo);
		if(pstock!=null){
			EOrderBatchNO batch = new EOrderBatchNO();
			batch.setProductId(Integer.parseInt(pstock.getProductId()));
			batch.setQuantity(pstock.getQuantity().intValue());
			return batch;
		}
		return null;
	}

	@Override
	public int findNOffStockAmount(String goodsNo) {
		String quantity = pstockMapper.findNOffStock(goodsNo);
		if(StringUtil.isBlank(quantity)){
			return 0;
		}
		return Integer.valueOf(quantity);
	}

	@Override
	public List<EOrderBatchNO> queryStockList(String goodsNo) {
		List<E_Pstock> list = pstockMapper.queryStockList(goodsNo);
		List<EOrderBatchNO> batchList = null;
		if(list!=null&&list.size()>0){
			EOrderBatchNO batch = null;
			batchList = new ArrayList<EOrderBatchNO>();
			for(E_Pstock ps : list){
				batch = new EOrderBatchNO();
				batch.setProductId(Integer.parseInt(ps.getProductId()));
				batch.setBatchno(ps.getBatchno());
				batch.setQuantity(ps.getQuantity().intValue());
				batchList.add(batch);
			}
		}
		
		return batchList;
	}
	
	@Override
	public int findBatchNo(int pid,String batchno){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("productId", pid);
		params.put("batchno", batchno);
		String q = pstockMapper.findBatchNo(params);
		if(StringUtil.isBlank(q)){
			return 0;
		}
		return Integer.parseInt(q);
	}
	
	@Override
	public void saveSalesOrderBatchNo(EOrderBatchNO batch){
		E_salesorderbatchno record = new E_salesorderbatchno();
		record.seteOrdernumber(batch.geteOrderNumber());
		record.setpId(Integer.toString(batch.getProductId()));
		record.setBatchno(batch.getBatchno());
		record.setQuantity(new Long(batch.getQuantity()));
		record.setDeleted(0);
		esalesorderbatchnoMapper.insert(record);
	}
	
	@Override
	public void batchInsertNo(List<E_salesorderbatchno> recordList){
		esalesorderbatchnoMapper.batchInsertNo(recordList);
	}
	
	
	@Override
	public void saveOrderState(String eOrdernumber){
		E_orderstate record = new E_orderstate();
		record.seteOrdernumber(eOrdernumber);
		record.setBillstates(1);
		eorderstateMapper.insert(record);
	}
	
	@Override
	public boolean findOrderState(String ordernumber){
		
		String number = eorderstateMapper.findOrderState(ordernumber);
		if(StringUtil.isBlank(number)){
			return false;
		}
		return true;
	}
	
	@Override
	public void saveOrderIndex(int multiChannelId,String eOrdernumber){
		E_saleorderindex record = new E_saleorderindex();
		record.seteOrdernumber(eOrdernumber);
		record.setcId(Integer.toString(multiChannelId));
		record.setBilldate(sdf.format(new Date()));
		record.setDeleted(0);
		esaleorderindexMapper.insert(record);
	}
	
	
	@Override
	public void saveOrderdetail(String eOrdernumber,int productId,int amount,BigDecimal sumPrice){
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
			esalesorderdetailMapper.insert(record);
		} catch (Exception e) {
			logger.error("参数：eOrdernumber："+eOrdernumber+",productId"+productId+",amount"+amount+",sumPrice"+sumPrice);
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveBatchOrderdetail(List<E_salesorderdetail> saleorderdetails){
		try {
			esalesorderdetailMapper.batchInsert(saleorderdetails);
		} catch (Exception e) {
			logger.error("参数：eOrdernumber："+e.getMessage(),e);
		}
	}
	
	
	
/*	@Override
	public boolean findOrderIndex(Integer multiChannelId, String ordernumber){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("multiChannelId", multiChannelId);
		params.put("ordernumber", ordernumber);
		List<E_saleorderindex> list = esaleorderindexMapper.findOrderIndexList(params);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}*/
	
}
