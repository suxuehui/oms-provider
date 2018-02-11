package cn.com.dubbo.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.model.GoodsSaleInfoModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderPackage;
import cn.com.dubbo.service.inter.GoodsService;
import cn.com.dubbo.service.inter.OrderPackageService;
import cn.com.dubbo.service.order.JDBusinessService;
import cn.com.dubbo.service.order.JDInterService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.util.StringUtil;

@Service
public class JDBusinessServiceImpl implements JDBusinessService {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final Logger logger = Logger.getLogger(JDBusinessServiceImpl.class);

	@Resource
	private OrderPackageService packageService;
	
	@Resource
	private JDInterService jdInterService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private GoodsService goodsService;
	
	@Override
	public void sopEMSUpdate() {

		List<OrderPackage> packageList = packageService.queryPackageByChannelId(2);
		if(null!=packageList&&packageList.size()>0){
			int i=0;
			List<OrderLogModel> errorLog = new ArrayList<OrderLogModel>();
			for (OrderPackage jd : packageList) {
				this.sendEmsInfo(jd,errorLog);
				if(i%30==0){
					logger.info("向京东同步物流信息，一共："+ packageList.size()+" 条,正在同步第："+i+" 条");
				}
				i++;
			}
		}
		
	}

	
	@Transactional
	private void sendEmsInfo(OrderPackage orderPackage,List<OrderLogModel> errorLog){
		try {
			if(null!=orderPackage){
				
				String retMsg = null;
				if(orderPackage.getOrder_no().startsWith(ChannelConstant.CHANNEL_JD)){
					retMsg = jdInterService.jdSopEMS(orderPackage.getOrder_no().replace(ChannelConstant.CHANNEL_JD, ""), 
							orderPackage.getLogistic_no(), orderPackage.getLogistic_company_no());
				}else if(orderPackage.getOrder_no().contains("BF")){//补发单，不需要调用jd接口
					retMsg="0";
				}else if(orderPackage.getOrder_no().contains("TH")){
					retMsg = jdInterService.jdSopEMS(orderPackage.getOrder_no().replace("TH", ""),orderPackage.getLogistic_no()
							,orderPackage.getLogistic_no());
				}else {
					retMsg = jdInterService.jdSopEMS(orderPackage.getOrder_no(),
							orderPackage.getLogistic_no(), orderPackage.getLogistic_company_no());
				}
				
				if("0".equals(retMsg)){
					//更新订单状态
					OrderModel orderModel = new OrderModel();
					if(orderPackage.getOrder_no().startsWith("BF")||orderPackage.getOrder_no().startsWith("TH")||
							orderPackage.getOrder_no().startsWith(ChannelConstant.CHANNEL_JD)){
						orderModel.setOrderNo(orderPackage.getOrder_no());
					}else{
						orderModel.setOrderNo(ChannelConstant.CHANNEL_JD+orderPackage.getOrder_no());
					}
					orderModel.setOrderStatus(OrderState.STATE_6.getCode());
					orderService.updateOrder(orderModel);
					
					//更新商品状态
					OrderItemModel itemModel = new OrderItemModel();
					itemModel.setOrderNo(orderModel.getOrderNo());
					itemModel.setGoodsStatus(OrderState.STATE_6.getCode());
					orderService.updateOrderItemByOrderNo(itemModel);
					
					//更新物流状态
					packageService.updateOrderPackage(orderPackage.getOrder_no());
					
				}else{
					logger.error("确认发货，同步给京东失败,订单号："+orderPackage.getOrder_no()+" ,错误信息："+retMsg);
				}
			}
		} catch (Exception e) {
			logger.error("确认发货，同步给京东失败,订单号："+orderPackage.getOrder_no()+" ,错误信息："+e.getMessage(),e);
		}
	}
	
	
	@Override
	public void stockUpdate() {
		
		List<GoodsSaleInfoModel> saleGoods = goodsService.querySaleInfo(2);
		if(null!=saleGoods&&saleGoods.size()>0){
			for(GoodsSaleInfoModel good : saleGoods){
				if(!StringUtil.isBlank(good.getGoodsNo_69())){
					jdInterService.stockUpdate(good.getGoodsNo(), Integer.toString(good.getAvailableStock()));
				}
			}
		}
	}
	
	
}
