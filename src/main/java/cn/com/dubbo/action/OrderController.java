package cn.com.dubbo.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.service.inter.OrderLogService;
import cn.com.dubbo.service.order.OrderService;

/**
 * 作废订单
 * @author hhr
 */
@Controller
@RequestMapping("/order")
public class OrderController  extends BaseAction{
	
//	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource 
	private OrderService orderService;
	
	@Autowired 
	private OrderLogService orderLogService;
	
	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	//作废订单
	@RequestMapping(value = "/order-cannel", method = RequestMethod.GET)
	public @ResponseBody String  orderCannel(@RequestParam String orderNo,@RequestParam Integer userId){
			String smsg = orderService.orderCannel(orderNo, userId, "OMS系统订单作废");
			return smsg;
	}
	
	
	
	/***
	 * 天猫作废,单独报错日志的作废订单接口
	 * @author hhr
	 * @param orderNo
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/tm-order-cannel", method = RequestMethod.GET)
	public @ResponseBody String  tmOrderCannel(@RequestParam String orderNo,@RequestParam Integer userId){
			String smsg = orderService.orderCannel(orderNo, userId, "OMS系统订单作废");
			if(!"操作成功".equals(smsg)){
				//天猫作废不成功,则保存order_log表
				OrderLogModel log = new OrderLogModel();
				log.setOrderNo(orderNo);
				log.setLogContent(smsg);
				log.setLogTime(sdf.format(new Date()));
				log.setAddTime(sdf.format(new Date()));
				log.setOrderStateId(0);
				orderLogService.saveOrderLog(log);
			}
			return smsg;
	}
	 
}
