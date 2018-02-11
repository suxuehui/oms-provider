package cn.com.dubbo.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.OrderService;

/**
 * 更换69码(已作废)
 * @author hhr
 */
@Component
public class UpdateOrderGoods69Task {
	
//	private static final Logger logger = Logger.getLogger(UpdateOrderGoods69Task.class);
	
	@Resource
	OrderService orderService;
	
	public void execute() {
//		try { 
//			System.out.println("开始...");
//			PropertiesUtil propertiesUtil = new PropertiesUtil("/config/system.properties");
//			String[] orderNos =  propertiesUtil.getProperty("update_69_number").split(",");
//			String old_order_69 = propertiesUtil.getProperty("old_order_69");
//			String new_order_69 = propertiesUtil.getProperty("new_order_69");
//			String orderNotes = "开始赠品缺货"; 
//			if(null != orderNos && !"".equals(orderNos)){ 
//				for (int i = 0; i < orderNos.length; i++) { 
//					if(null != orderNos[i] && !"".equals(orderNos[i])){
//						String orderNo = "PA" + orderNos[i].trim();
////						orderService.updateOrder69ByOrderNo(orderNo,old_order_69,new_order_69,orderNotes);
//					}
//				}
//			}
//			System.out.println("结束...");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
}
