package cn.com.dubbo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.model.RefundModel;
import cn.com.dubbo.service.order.DeliveryService;

/**
 * 发货接口，对方发货后更新订单状态和商品物流信息
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-18 上午9:03:27
 */
@Controller
@RequestMapping("/oms")
public class DeliveryController {

	@Resource
	private DeliveryService deliveryService;
	private Logger logger = Logger.getLogger(DeliveryController.class);

	/**
	 * 解析发货json，并更新发货信息和物流状态
	 * 
	 * @author 常胜
	 * @date 2016-7-18 上午11:04:30
	 * @param request
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/updateOrdersDeliveryStatus", method = { RequestMethod.POST })
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
			if (true) {
				RefundModel refundModel = deliveryService.deliveryUpdate(str);
				if (refundModel.getTotalResults() > 0) {
					map.put("code", "10001");
					map.put("rows", refundModel);
				} else {
					map.put("code", "10000");
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
