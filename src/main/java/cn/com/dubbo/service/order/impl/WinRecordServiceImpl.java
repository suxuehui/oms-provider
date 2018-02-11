package cn.com.dubbo.service.order.impl;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.PayCode;
import cn.com.dubbo.mapper.ChannelMapper;
import cn.com.dubbo.mapper.GoodsMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.mapper.PaymentLogMapper;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.MultiChannelOrderBatchModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.AreaService;
import cn.com.dubbo.service.inter.PaymentLogService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.service.order.WinRecordService;
import cn.com.dubbo.util.BussinessUtil;
import cn.com.dubbo.util.DataQueue;

@Service("winRecordService")
@Transactional
public class WinRecordServiceImpl implements WinRecordService {

	private DataQueue<OrderModel>  dataQueue = new DataQueue<OrderModel>();
	
	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ChannelMapper channelMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private PaymentLogMapper payLogMapper;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private PaymentLogService payLogService;

	//
	@Override
	public void saveWinRecord(List<OrderModel> list, Long multiChannelOrderBatch) {
		//第一次处理数据
		dealOrderModel(list);
		//处理错误的
		if(!dataQueue.isEmpty()){
			dealDataQueue();
		}
		//保存批次号
		saveMultiChannelOrderBatchModel(list.size(),multiChannelOrderBatch);
	}
	
	//保存批次号
	private void saveMultiChannelOrderBatchModel(int size,Long multiChannelOrderBatch) {
		
		MultiChannelOrderBatchModel multiChannelOrderBatchModel=new MultiChannelOrderBatchModel();
		multiChannelOrderBatchModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
		multiChannelOrderBatchModel.setMultiChannelId(8);
		multiChannelOrderBatchModel.setOrderAmount(size);
		multiChannelOrderBatchModel.setAddTime(sdf.format(new Date()));
		channelMapper.saveChannelOrder(multiChannelOrderBatchModel);
	}

	//第一次处理数据
	private void dealOrderModel(List<OrderModel> list){
		if(null != list && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				OrderModel orderinfo = list.get(i);
				try {
					if(!"".equals(orderinfo.getReceiveMobile()) && null != orderinfo.getReceiveMobile()){
						orderMapper.saveOrder(orderinfo);
						OrderItemModel item = orderinfo.getItemList().get(0); 
						GoodsModel newGoods = goodsMapper.getGoodByGoodNo69(item.getGoodsNo_69());
						if(null != newGoods){
							item.setGoodsNo(newGoods.getGoodsNo());
						}
						orderMapper.saveOrderItem(item);
						savePayMentLog(item.getOrderNo());
					}
				} catch (Exception e) {
					 e.printStackTrace();
					 dataQueue.enQueue(orderinfo);
				}
			}
		}
	}
	
	//处理错误的
	private void dealDataQueue(){
		for (int i = 0; i < dataQueue.getLength(); i++) {
			OrderModel orderinfo = dataQueue.deQueue();
			try {
				if(!"".equals(orderinfo.getReceiveMobile()) && null != orderinfo.getReceiveMobile()){
					orderMapper.saveOrder(orderinfo);
					OrderItemModel item = orderinfo.getItemList().get(0); 
					GoodsModel newGoods = goodsMapper.getGoodByGoodNo69(item.getGoodsNo_69());
					if(null != newGoods){
						item.setGoodsNo(newGoods.getGoodsNo());
					}
					orderMapper.saveOrderItem(item);
					savePayMentLog(item.getOrderNo());
				}
			} catch (Exception e) {
				 e.printStackTrace();
				 System.out.println("失败队列也失败了....."); 
			}
		}
		
	}
	
	//保存支付信息
	private void savePayMentLog(String orderNo){
		  PaymentLogModel logModel2 = new PaymentLogModel();
    	  logModel2.setBusinessType("ADD");//业务类型
    	  logModel2.setBusinessId(orderNo);//订单号 
    	  //utc时间格式转换必须*1000
    	  logModel2.setPaymentTime(sdf.format(new Date())); 
    	  logModel2.setOrderPaymentId(PayCode.XJ_ALI);
    	  logModel2.setOrderPaymentName("现金");
    	  logModel2.setPaidFee(new BigDecimal("0"));//抵扣券金额
    	  logModel2.setAddTime(sdf.format(new Date()));//添加时间
    	  payLogMapper.savePaymentLog(logModel2);
    	  
	}

	@Override
	public void savePDOrder(String pathName){
		try {
			Map<String,Object> dataMap = this.readPDExcel(new File(pathName));
			List<OrderModel> orderList = (List<OrderModel>) dataMap.get("orderList");
			List<OrderItemModel> orderItemList = (List<OrderItemModel>) dataMap.get("orderItemList");
			List<PaymentLogModel> payLogList = (List<PaymentLogModel>) dataMap.get("paylogList");
			
			//订单处理
			orderService.saveBatchOrder(orderList,null);
			
			orderService.saveBatchOrderItem(orderItemList,null);
			
			payLogService.saveBatchPayLog(payLogList,null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//导入拼多多的订单信息
	private Map<String,Object> readPDExcel(File file) throws  Exception {
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = null;
		XSSFCell cell = null;
		long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("9");
		OrderModel orderModel = null;
		Map<String,Object> retMap = new HashMap<String, Object>();
		List<OrderModel> orderList = new ArrayList<OrderModel>();
		List<OrderItemModel> orderItemList = new ArrayList<OrderItemModel>();
		List<PaymentLogModel> paylogList = new ArrayList<PaymentLogModel>();
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			//忽略第一行
			if(i == sheet.getFirstRowNum()){
				continue;
			}
			orderModel = new OrderModel();
			//设置基础信息
			orderModel.setMultiChannelId(9);
			orderModel.setFromMedia("拼多多");
			orderModel.setOrderType(0);
			orderModel.setOrderStatus(1);
			orderModel.setSendStatus("N");
			orderModel.setValid("Y");
			orderModel.setLogLogisticCompanyId(58);
			orderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
			orderModel.setIsPay("Y");
			orderModel.setPaymentType(Constant.PAYMENT_TYPE_KDFH);
			BigDecimal bdZero = new BigDecimal("0");
			
			orderModel.setDeliveryFeeOld(bdZero);
			orderModel.setDeliveryFee(bdZero);
			orderModel.setOrderPoints(bdZero);
			orderModel.setOrderVouchers(bdZero);
			orderModel.setOrderPayFee(bdZero);
			orderModel.setOrderReturnFee(bdZero); 
			orderModel.setSupplyPrice(bdZero);
			orderModel.setAddTime(sdf.format(new Date()));
			orderModel.setHaveCfy("N");
			
			orderModel.setActivityDiscountFee(bdZero);
			
			orderModel.setCommitTime(sdf.format(new Date()));
			orderModel.setPlatformCreateTime(sdf.format(new Date()));
			
			//判断是否为空
			OrderItemModel orderItemModel = new OrderItemModel();
			PaymentLogModel paylog = new PaymentLogModel();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell != null) {
					String cellString = null;
					if(j==2||j==4||j>16){
						continue;
					}
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cellString = cell.getStringCellValue().trim();
					
					if(j==0){
						orderItemModel.setName(cellString);
					}else if(j==1){
						orderModel.setOrderNo("PD"+cellString);
						orderModel.setMultiChannelOrderNo(cellString);
						orderItemModel.setOrderNo(orderModel.getOrderNo());
						paylog.setBusinessId(orderModel.getOrderNo());
					}else if(j==3){//商品总价
						orderModel.setSkuFee(new BigDecimal(cellString));
						orderItemModel.setOldPrice(orderModel.getSkuFee());
					}else if(j==5){//优惠价格
						orderModel.setActivityDiscountFee(new BigDecimal(cellString));
					}else if(j==6){//实际支付
						orderModel.setPaidFee(new BigDecimal(cellString));
						orderModel.setOrderFee(orderModel.getPaidFee());
						orderModel.setOrderRealFee(orderModel.getPaidFee());
						orderItemModel.setGoodsSumFee(orderModel.getPaidFee());
						
						paylog.setPaidFee(orderModel.getPaidFee());
						paylog.setBusinessType("ADD");
						paylog.setOrderPaymentId(PayCode.XJ_ALI);
						paylog.setOrderPaymentName("现金");
						
					}else if(j==7){//数量
						orderItemModel.setAmount(new Long(cellString));
					}else if(j==8){//收货人
						orderModel.setReceiveUser(cellString);
					}else if(j==9){//手机
						orderModel.setReceiveMobile(cellString);
					}
					int areaId = 0;
					if(j==10){//省
						orderModel.setProvinceName(cellString);
						areaId = areaService.findArea(0, orderModel.getProvinceName());
						orderModel.setProvinceId(areaId);
					}else if(j==11){//市
						orderModel.setCityName(cellString);
						areaId = areaService.findArea(orderModel.getProvinceId(), orderModel.getCityName());
						orderModel.setCityId(areaId);
					}else if(j==12){//区
						orderModel.setAreaName(cellString);
						areaId = areaService.findArea(orderModel.getCityId(), orderModel.getAreaName());
						orderModel.setAreaId(areaId);
					}else if(j==13){//地区明细
						orderModel.setReceiveAddress(orderModel.getProvinceName()+orderModel.getCityName()+orderModel.getAreaName());
						orderModel.setReceiveFullAddress(orderModel.getReceiveAddress()+cellString);
					}else if(j==14){//组团时间
						orderModel.setPlatformCreateTime(cellString);
						orderModel.setCommitTime(orderModel.getPlatformCreateTime());
						orderModel.setAddTime(sdf.format(new Date()));
						orderItemModel.setAddTime(orderModel.getAddTime());
						
						paylog.setPaymentTime(orderModel.getPlatformCreateTime());
						paylog.setAddTime(orderModel.getAddTime());
					}else if(j==15){//商品69码
						orderItemModel.setGoodsNo_69(cellString);
						orderItemModel.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
					}
				}
			}
			//保存数据
			orderList.add(orderModel);
			orderItemList.add(orderItemModel);
			paylogList.add(paylog);
		}
		retMap.put("orderList", orderList);
		retMap.put("orderItemList", orderItemList);
		retMap.put("paylogList", paylogList);
		return retMap;
	}
	
}
