package cn.com.dubbo.service.order.impl;

import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.com.dubbo.mapper.Employee666Mapper;
import cn.com.dubbo.mapper.OrderDeliveryMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.model.Employee;
import cn.com.dubbo.model.OrderDelivery;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.service.order.TransactionalOrderService;

@Service("transactionalOrderService")
@Transactional
public class TransactionalOrderServiceImpl implements TransactionalOrderService {

	@Resource
	private OrderMapper orderMapper;
	
	private static final Logger logger = Logger.getLogger(TransactionalOrderServiceImpl.class);
	
	@Resource
	private OrderDeliveryMapper orderDeliveryMapper;
	
/*	@Override
	public void saveTrade(OrderModel orderModel,List<OrderItemModel> orderItemModelList,List<PaymentLogModel> paymentLogList,LogisticChannelModel logisticChannelModel){
		
		String str_Exception=null;
		String orderNo=orderModel.getOrderNo();
		try {
			if (orderMapper.isOrderByNo(orderNo)==0) {
				System.err.println(orderModel.getOrderNo()+"    1234561");
				orderMapper.saveOrder(orderModel);
				for (int i = 0; i < orderItemModelList.size(); i++) {
					OrderItemModel tempOrderItemModel=orderItemModelList.get(i);
					orderMapper.saveOrderItem(tempOrderItemModel);
				}
				for (int i = 0; i < paymentLogList.size(); i++) {
					PaymentLogModel paymentLogModel=paymentLogList.get(i);
					payLogMapper.savePaymentLog(paymentLogModel);
				}
				if(logisticChannelModel!=null){
					logisticMapper.saveLogisticChannel(logisticChannelModel);
				}
			}
		} catch (Exception e) {
			logger.error(orderModel.getOrderNo()+"  123456111  "+orderModel.getPaidFee());
			logger.error(orderModel+"保存时出错"+" ,错误信息："+e.getMessage(),e);
		}finally{
			if (str_Exception==null && exceptionInfoList.size()>0) {
				for (int i = 0; i < exceptionInfoList.size(); i++) {
					String tempOrderNo=exceptionInfoList.get(i).getOrder_no();
					orderMapper.deleteOrder(tempOrderNo);
					ExceptionInfo tempExceptionInfo=exceptionInfoList.get(i);
					exceptionInfoMapper.insertExceptionInfo(tempExceptionInfo);
				}
				exceptionInfoList.clear();
			}
			
			if (str_Exception!=null) {
				//保存记录到数据库
		        ExceptionInfo exceptionInfo=new ExceptionInfo();
		        exceptionInfo.setOrder_no(orderNo);
		        exceptionInfo.setExceptionInfos(str_Exception);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        exceptionInfo.setAdd_time(sdf.format(new Date()));
				exceptionInfoList.add(exceptionInfo);
			}
       }	
		System.out.println("saveTrade:执行完毕") ;
   }*/
	
	
	@Override
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public void saveOrderDelivery_updateOrderDeliveryNo_OrderItem_updateOrderStatus(List<OrderDelivery> list_OrderDelivery,List<OrderItemModel> list_OrderItemModel,OrderModel orderModel){
		for (int i = 0; i < list_OrderDelivery.size(); i++) {
			Integer num=orderDeliveryMapper.isExistOrderDelivery(list_OrderDelivery.get(i).getOrder_delivery_no()).intValue();
			if (num<1) {
				orderDeliveryMapper.saveOrderDelivery(list_OrderDelivery.get(i));
			}
		}
		for (int j = 0; j < list_OrderItemModel.size(); j++) {
			orderMapper.updateOrderDeliveryNo_OrderItem(list_OrderItemModel.get(j));
		}
		orderMapper.updateOrderStatus(orderModel);
		System.out.println("saveOrderDelivery_updateOrderDeliveryNo_OrderItem_updateOrderStatus:执行完毕") ;
   }
	
	@Autowired
	private Employee666Mapper employee666Mapper;
	
	@Override
	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public void saveOrderDelivery_updateOrderDeliveryNo_OrderItem_updateOrderStatus66(){
//		try {
			Employee temEmployee=new Employee();
			temEmployee.setEmpid(100);
			employee666Mapper.insertSelective666(temEmployee);
			
			Employee temEmployee22=new Employee();
			temEmployee22.setEmpid(200);
			employee666Mapper.insertSelective777(temEmployee22);
			
			Employee temEmployee33=new Employee();
			temEmployee33.setEmpid(300);
			employee666Mapper.insertSelective888(temEmployee33);
			
			employee666Mapper.insertSelective666(temEmployee);
//		} catch (Exception e) {
//			logger.error("错误："+e.getMessage(),e);
//		}
	}
}
