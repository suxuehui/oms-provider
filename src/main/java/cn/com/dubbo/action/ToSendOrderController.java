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
import cn.com.dubbo.service.order.ToSendOrderService;
import cn.com.dubbo.util.StringUtil;

/**
 * 待发货订单接口
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-14 上午10:28:29
 */
@Controller
@RequestMapping("/oms")
public class ToSendOrderController {

	@Resource
	private ToSendOrderService toSendOrderService;

	private Logger logger = Logger.getLogger(ToSendOrderController.class);

	/**
	 * 解析参数返回待发货订单信息
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
	@RequestMapping(value = "/returnOrderListDelivery", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> gettosend(HttpServletRequest request) {
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
				String stockId = obj.getString("stockId").toString();
				String venderId = obj.getString("venderId").toString();
				if (StringUtil.isStringNotBlank(startCreated) && StringUtil.isStringNotBlank(endCreated) && StringUtil.isStringNotBlank(pageNo)) {
					ToSendModel ext = toSendOrderService.getToSend(startCreated, endCreated, pageNo, pageSize, stockId, venderId);
					map.put("code", "10000");
					map.put("rows", ext);
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
