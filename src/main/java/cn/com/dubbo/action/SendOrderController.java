package cn.com.dubbo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.model.ToSendModel;
import cn.com.dubbo.service.order.SendOrderService;
import cn.com.dubbo.service.order.TestService;
import cn.com.dubbo.util.CheckParamUtil;
import cn.com.dubbo.util.StringUtil;

/**
 * 物流信息接口
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-14 上午10:28:29
 */
@Controller
@RequestMapping("/oms")
public class SendOrderController {

	@Resource
	private SendOrderService sendOrderService;
	private TestService testService;
	private Logger logger = Logger.getLogger(SendOrderController.class);

	/**
	 * 解析参数返回物流信息
	 * 
	 * @author 常胜
	 * @date 2016-7-14 下午2:15:28
	 * @param request
	 * @param startCreated
	 * @param endCreated
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/returnOrdersDeliveryInfo", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getLogistics(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			byte[] buffer = getRequestPostBytes(request);
			String charEncoding = request.getCharacterEncoding();
			if (charEncoding == null) {
				charEncoding = "UTF-8";
			}
			String str = new String(buffer, charEncoding);
			if (CheckParamUtil.check(str)) {
				JSONObject obj = JSONObject.fromObject(str);
				obj = JSONObject.fromObject(obj.get("root").toString());
				String startCreated = obj.getString("startCreated").toString();
				String endCreated = obj.getString("endCreated").toString();
				String pageNo = obj.getString("pageNo").toString();
				String pageSize = obj.getString("pageSize").toString();
				String venderId = obj.getString("venderId").toString();
				if (StringUtil.isStringNotBlank(startCreated) && StringUtil.isStringNotBlank(endCreated) && StringUtil.isStringNotBlank(pageNo) && StringUtil.isStringNotBlank(venderId)) {
					ToSendModel ext = sendOrderService.getLogistics(startCreated, endCreated, pageNo, pageSize, venderId);
					map.put("rows", ext);
				} else {
					map.put("error", "param error");
				}
			} else {
				map.put("error", "param error");
			}
		} catch (Exception e) {
			map.put("error", "unknown error");
			e.printStackTrace();
		}
		return map;
	}

	public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
		int contentLength = request.getContentLength();
		/* 当无请求参数时，request.getContentLength()返回-1 */
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {
			int readlen = request.getInputStream().read(buffer, i, contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	@RequestMapping(value = "/update", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			testService.get();
		} catch (Exception e) {
			map.put("error", "unknown error");
			e.printStackTrace();
		}
		return map;
	}

}
