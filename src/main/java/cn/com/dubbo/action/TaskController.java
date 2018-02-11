package cn.com.dubbo.action;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.service.order.OrderSupplementService;
import cn.com.dubbo.service.order.OutStockUpdateService;
import cn.com.dubbo.service.order.PaOrderService;
import cn.com.dubbo.service.order.PsOrderService;
import cn.com.dubbo.util.BaseObject;
import cn.com.dubbo.util.FunctionUtil;
import cn.com.dubbo.util.InitUtils;
import cn.com.dubbo.util.JsonUtil;

/**
 * 业务功能
 */
@Controller
@RequestMapping("task")
public class TaskController {
	
	private Logger logger = Logger.getLogger(TaskController.class);
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private OrderSupplementService supplementService;
	
	@Resource
	private PaOrderService paOrderService;
	
	@Resource
	private PsOrderService psOrderService;
	
	@Resource
	private OutStockUpdateService outStockUpdateService;
	
	/**
	 * 订单审核接口
	 */
	@RequestMapping(value="audit")
	@ResponseBody
	public void audit(HttpServletRequest request,
			HttpServletResponse response){
		BaseObject object = new BaseObject();
		try {
			object.setCode(InitUtils.STATUS_OK);
			//查询的总条目
			String multiChannel = request.getParameter("multiChannel");
			String orderState = request.getParameter("orderState");
			String pageIndex = request.getParameter("pageIndex");
			String ret = supplementService.orderSupplement(multiChannel,Integer.parseInt(orderState),Integer.parseInt(pageIndex));
			logger.info("调用订单审核接口，渠道 "+multiChannel+",审核的状态："+orderState+",第 "+pageIndex+" 页"+",返回信息："+ret);
			object.setObj(ret);
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("手动调用平安，同步库存接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
	/**
	 * 获取指定时间段的，指定渠道的订单数据
	 */
	@RequestMapping(value="orderDown")
	@ResponseBody
	public void orderDown(HttpServletRequest request,
			HttpServletResponse response){
		BaseObject object = new BaseObject();
		try {
			object.setCode(InitUtils.STATUS_OK);
			//查询的总条目
			String multiChannel = request.getParameter("multiChannel");
			String startString = request.getParameter("startString");
			String endString = request.getParameter("endString");
			String orderStatus = request.getParameter("orderStatus");
			
//			System.out.println(new Long((sdf.parse(startString).getTime()/1000)).toString()+",结束时间："+new Long((sdf.parse(endString).getTime()/1000)).toString());
			
			if(multiChannel.equals(ChannelConstant.CHANNEL_PA)){
				//转化成utc格式的时间
				String startTime = new Long((sdf.parse(startString).getTime()/1000)).toString();
				String endTime = new Long((sdf.parse(endString).getTime()/1000)).toString();
				paOrderService.dealOrder(startTime,endTime,true);
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_36)){
				
				
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_JD)){
				
				
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_PS)){
				//转化成utc格式的时间
				String startTime = new Long((sdf.parse(startString).getTime()/1000)).toString();
				String endTime = new Long((sdf.parse(endString).getTime()/1000)).toString();
				psOrderService.dealOrder(startTime,endTime,true);
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_PD)){
				
			}
			
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("手动调用平安，同步库存接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
	
	/**
	 * 向第三方推送库存数据
	 */
	@RequestMapping(value="outStockUpdate")
	@ResponseBody
	public void outStockUpdate(HttpServletRequest request,
			HttpServletResponse response){
		BaseObject object = new BaseObject();
		try {
			object.setCode(InitUtils.STATUS_OK);
			//查询的总条目
			outStockUpdateService.paStockUpdate();
			
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("手动调用平安，同步库存接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
	
	
}
