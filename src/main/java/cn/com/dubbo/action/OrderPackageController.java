package cn.com.dubbo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.mapper.ExceptionInfoMapper;
import cn.com.dubbo.mapper.OrderPackageMapper;
import cn.com.dubbo.model.ExceptionInfo;
import cn.com.dubbo.model.OrderPackage;
import cn.com.dubbo.service.impl.OrderPackageServiceImpl;
import cn.com.dubbo.util.BaseObject;
import cn.com.dubbo.util.FunctionUtil;
import cn.com.dubbo.util.GetNowTime;
import cn.com.dubbo.util.HttpClientUtils;
import cn.com.dubbo.util.InitUtils;
import cn.com.dubbo.util.JsonUtil;

/**
 * @Description: 推送更改状态
 * @author sunxiaoxue
 * @date 2016-8-9 上午9:40:59 
 */
@Controller
@RequestMapping("/SMSpush")
public class OrderPackageController {
	
	@Resource
	ExceptionInfoMapper exceptionInfoMapper;
	@Resource
	private OrderPackageMapper orderPackageMapper;
	@Resource
	private OrderPackageServiceImpl orderPackageServiceImpl;
	private Logger logger = Logger.getLogger(OrderRequestController.class);
	
	private static String url = "http://192.168.100.69:3089/oms-weixin/views/oms/postGoods.do?";
	
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/orderpackage", method = { RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public void getorderpackage(HttpServletRequest request,HttpServletResponse response){
		BaseObject object = new BaseObject();
		try {
			List<OrderPackage> packageList=orderPackageServiceImpl.queryPackageByChannelId(3);
			//判断短信N状态的size
			if(null!=packageList&&packageList.size()>0){
				for(OrderPackage orpace : packageList){
	//				String body = "orerNo="+orpace.getOrder_no()+"&trackingNumber="+orpace.getLogistic_no()+"&logLogisticCompanyName="+orpace.getLogistic_company_name()+"&logLogisticCompanyId="+orpace.getLogistic_company_no()+"&receiveTime="+orpace.getDelivery_time();
					Map<String,Object> params = new HashMap<String,Object>(); 
					params.put("orderNo",Integer.parseInt(orpace.getOrder_no()));
					params.put("trackingNumber",orpace.getLogistic_no());
					params.put("logLogisticCompanyName", orpace.getLogistic_company_name());
					params.put("logLogisticCompanyId", orpace.getLogistic_company_no());
					params.put("receiveTime",orpace.getDelivery_time());
					String result=HttpClientUtils.doPost(url,params);
					JSONObject obj = JSONObject.fromObject(result);
					String str = obj.getString("result");
					if(str.equals("success")){
						orderPackageMapper.updateOrderPackage(params.get("orderNo").toString());
					}
					else if(str.equals("false")){
					ExceptionInfo info =new ExceptionInfo();
					info.setOrder_no(params.get("orderNo").toString());
					info.setDelivery_time(params.get("receiveTime").toString());
					info.setLogistic_company_id(Integer.parseInt(params.get("logLogisticCompanyId").toString()));
					info.setLogistic_company_name(params.get("logLogisticCompanyName").toString());
					info.setLogistic_no(params.get("trackingNumber").toString());
					info.setAdd_time(GetNowTime.getCurrentTime());
					exceptionInfoMapper.insertExceptionInfo(info);
				}
					
				}
			}
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("调用处方药审核接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
}
