package cn.com.dubbo.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.service.inter.OrderLogService;
import cn.com.dubbo.service.order.OutStockUpdateService;
import cn.com.dubbo.util.BaseObject;
import cn.com.dubbo.util.FunctionUtil;
import cn.com.dubbo.util.InitUtils;
import cn.com.dubbo.util.JsonUtil;

/**
 * 业务功能
 */
@Controller
@RequestMapping("business")
public class BusinessController {
	
	private Logger logger = Logger.getLogger(BusinessController.class);
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private OutStockUpdateService outStockUpdateService;
	
	@Resource
	private OrderLogService orderLogService;
	
	
	/**
	 * 更新组合码库存信息
	 */
	@RequestMapping(value="stockUp")
	@ResponseBody
	public void OrderReissued(HttpServletRequest request,
			HttpServletResponse response){
		
		BaseObject object = new BaseObject();
		try {
			response.addHeader("Access-Control-Allow-Origin","*");
			response.addHeader("Access-Control-Allow-Methods","*");
			response.addHeader("Access-Control-Max-Age","100");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.addHeader("Access-Control-Allow-Credentials","false");
			
			object.setCode(InitUtils.STATUS_OK);
			String goodsno = request.getParameter("goodsno");
			String amount = request.getParameter("amount");
			String addUserId = request.getParameter("addUserId");
			Map<String, Integer> params = new HashMap<String, Integer>();
			params.put(goodsno, Integer.parseInt(amount));
			String ret = outStockUpdateService.paUpdateStock(params);
			logger.info("向平安推送组合商品库存，条码：" + goodsno + ",数量：" + amount + ",添加人ID："+ addUserId+" ,返回结果：" + ret);
			if(!ret.equals("0")){
				JSONObject obj = new JSONObject().fromObject(ret);
				JSONArray data = obj.getJSONArray("data");
				object.setMsg(data.getJSONObject(0).getString("msg"));
			}else{
				object.setMsg("推送成功！");
			}
			//--------------添加日志------------------------
			/*OrderLogModel log = new OrderLogModel();
			log.setOrderNo(goodsno);
			log.setOrderStateId(1);
			log.setLogContent("向平安推送组合商品库存，条码：" + goodsno + ",数量：" + amount+",返回信息："+ret);
			log.setLogTime(sdf.format(new Date()));
			if(!StringUtil.isBlank(addUserId)){
				log.setAddUserId(Integer.parseInt(addUserId));
			}
			log.setAddTime(sdf.format(new Date()));
			orderLogService.saveOrderLog(log);*/
//			object.setObj(map);
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("手动调用平安，同步库存接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
}
