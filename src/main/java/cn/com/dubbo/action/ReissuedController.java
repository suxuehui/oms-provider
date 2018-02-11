package cn.com.dubbo.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.service.inter.OrderReissuedService;
import cn.com.dubbo.util.BaseObject;
import cn.com.dubbo.util.FunctionUtil;
import cn.com.dubbo.util.InitUtils;
import cn.com.dubbo.util.JsonUtil;
import cn.com.dubbo.util.RegUtils;
import cn.com.dubbo.util.StringUtil;

/**
 * 补发订单功能
 */
@Controller
@RequestMapping("reissued")
public class ReissuedController {
	
	private Logger logger = Logger.getLogger(ReissuedController.class);
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private OrderReissuedService orderReissuedService;
	
	
	/**
	 * 订单补发
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public void OrderReissued(HttpServletRequest request,
			HttpServletResponse response){
		
		BaseObject object = new BaseObject();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			
			response.addHeader("Access-Control-Allow-Origin","*");
			response.addHeader("Access-Control-Allow-Methods","*");
			response.addHeader("Access-Control-Max-Age","100");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.addHeader("Access-Control-Allow-Credentials","false");
			
			object.setCode(InitUtils.STATUS_OK);
			String orders = RegUtils.reqString(request);
			if(StringUtil.isBlank(orders)){
				map.put("error", "传递的值为空！");
			}else{
				map = orderReissuedService.addOrder(orders);
			}
			object.setObj(map);
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("调用处方药审核接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
	
	/**
	 * 补发订单审核
	 * json格式：{"orderNo":"590163020507","addUserId":"22222","operate":"delete pass"}
	 */
	@RequestMapping(value="examine")
	@ResponseBody
	public void examine(HttpServletRequest request,
			HttpServletResponse response){
		
		BaseObject object = new BaseObject();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			
			response.addHeader("Access-Control-Allow-Origin","*");
			response.addHeader("Access-Control-Allow-Methods","*");
			response.addHeader("Access-Control-Max-Age","100");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.addHeader("Access-Control-Allow-Credentials","false");
			
			object.setCode(InitUtils.STATUS_OK);
			String orders = RegUtils.reqString(request);
			if(StringUtil.isBlank(orders)){
				object.setMsg("传递的值为空！");
			}else{
				map = orderReissuedService.examine(orders);
			}
			object.setObj(map);
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("调用处方药审核接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
}
