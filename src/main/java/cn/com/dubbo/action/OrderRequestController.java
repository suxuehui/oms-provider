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

import cn.com.dubbo.service.order.OrderRequestService;
import cn.com.dubbo.util.CheckParamUtil;

/**
 * 第三方调用接口
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-11 下午8:08:48
 */
@Controller
@RequestMapping("/oms")
public class OrderRequestController {

	@Resource
	private OrderRequestService orderRequestService;

	private Logger logger = Logger.getLogger(OrderRequestController.class);

	/**
	 * 第三方传入json字符串进行解析
	 * 
	 * @author 常胜
	 * @date 2016-7-11 下午8:09:18
	 * @param request
	 * @param rows
	 * @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/postorder", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> get(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			byte[] buffer = getRequestPostBytes(request);
			String charEncoding = request.getCharacterEncoding();
			if (charEncoding == null) {
				charEncoding = "UTF-8";
			}
			String str = new String(buffer, charEncoding);
			if (CheckParamUtil.check(str)) {
				map = orderRequestService.insert(str);
			} else {
				map.put("error", "param error");
			}
		} catch (Exception e) {
			map.put("error", "param error");
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
