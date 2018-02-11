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

import cn.com.dubbo.model.RefundModel;
import cn.com.dubbo.service.order.RefundValidationService;
import cn.com.dubbo.util.StringUtil;

/**
 * 退款验证接口
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-18 上午10:10:50
 */
@Controller
@RequestMapping("/oms")
public class RefundValidationController {

	@Resource
	private RefundValidationService refundValidationService;
	private Logger logger = Logger.getLogger(RefundValidationController.class);

	/**
	 * 退款验证请求入口，并返回json数据
	 * 
	 * @author 常胜
	 * @date 2016-7-18 上午10:11:39
	 * @param request
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/refundvalidation", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> refundvalidation(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			byte[] buffer = getRequestPostBytes(request);
			String charEncoding = request.getCharacterEncoding();
			if (charEncoding == null) {
				charEncoding = "UTF-8";
			}
			String str = new String(buffer, charEncoding);
			if (true) {
				JSONObject obj = JSONObject.fromObject(str);
				obj = JSONObject.fromObject(obj.get("root").toString());
				String startCreated = obj.getString("startCreated").toString();
				String endCreated = obj.getString("endCreated").toString();
				String pageNo = obj.getString("pageNo").toString();
				String pageSize = obj.getString("pageSize").toString();
				String venderId = obj.getString("venderId").toString();
				String orderNo = obj.getString("orderNo").toString();
				if (StringUtil.isStringNotBlank(startCreated) && StringUtil.isStringNotBlank(endCreated) && StringUtil.isStringNotBlank(pageNo) && StringUtil.isStringNotBlank(venderId)) {
					RefundModel ext = refundValidationService.getRefund(startCreated, endCreated, pageNo, pageSize, venderId, orderNo);
					map.put("code", "10000");
					map.put("rows", com.alibaba.fastjson.JSONObject.toJSONString(ext));
				} else {
					map.put("code", "10001");
				}
			} else {
				map.put("code", "10003");
			}
		} catch (Exception e) {
			map.put("code", "10008");
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
}
