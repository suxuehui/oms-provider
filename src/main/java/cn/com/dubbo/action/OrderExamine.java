package cn.com.dubbo.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.service.order.OrderStepService;
import cn.com.dubbo.util.BaseObject;
import cn.com.dubbo.util.FunctionUtil;
import cn.com.dubbo.util.InitUtils;
import cn.com.dubbo.util.JsonUtil;
import cn.com.dubbo.util.RegUtils;

/**
 * 第三方（php平台调用）
 *
 */
@Controller
@RequestMapping("examine")
public class OrderExamine {
	
	private Logger logger = Logger.getLogger(OrderExamine.class);
	
	@Resource
	private OrderStepService orderStepService;
	
	/**
	 * 自动审核，可以手动触发
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autoExamine")
	@ResponseBody
	public void autoExamine(HttpServletRequest request,
			HttpServletResponse response){
		BaseObject object = new BaseObject();
		try {
			object.setCode(InitUtils.STATUS_OK);
			orderStepService.orderNotRXExamine(ChannelConstant.CHANNEL_PS,OrderState.STATE_1.getCode());
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("调用处方药审核接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
	
	/**
	 * 执行处方药的批量审核，有php平台手动触发
	 * @param model
	 * @return
	 */
	@RequestMapping(value="examineDrug")
	@ResponseBody
	public void examineDrug(HttpServletRequest request,
			HttpServletResponse response){
		BaseObject object = new BaseObject();
		try {
			String data = RegUtils.reqString(request);
			object.setCode(InitUtils.STATUS_OK);
			JSONObject jsonObj = JSONObject.fromObject(data);
			JSONArray array = jsonObj.getJSONArray("order_no");
			if(null!=array&&array.size()>0){
				List list = (List)JSONArray.toCollection(array, String.class);  
				orderStepService.orderRXExamine(list,12);
			}
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("调用处方药审核接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
	
	/**
	 * 处方药的审核结果更新
	 * @param model
	 * @return
	 */
	@RequestMapping(value="drugExamineUp")
	@ResponseBody
	public void drugExamineUp(HttpServletRequest request,
			HttpServletResponse response){
		BaseObject object = new BaseObject();
		try {
			object.setCode(InitUtils.STATUS_OK);
			String json = request.getParameter("");
			JSONArray array = JSONArray.fromObject(json);
			JSONObject obj  = null;
			if(null!=array&&array.size()>0){
				List<OrderModel> orderList = new ArrayList<OrderModel>();
				OrderModel modle = null;
				for(int i=0;i<array.size();i++){
					modle = new OrderModel();
					obj  = array.getJSONObject(i);
//					modle.setOrderNo(obj);
				}
			}
			orderStepService.drugExamineUp();
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("调用处方药审核接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
}
