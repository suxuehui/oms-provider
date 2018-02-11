package cn.com.dubbo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.util.RegUtils;

/**
 * 第三方平台回调
 */
@Controller
@RequestMapping("platformCallBack")
public class PlatformCallBack {
	
	private Logger logger = Logger.getLogger(PlatformCallBack.class);
	
	@RequestMapping(value="jd")
	@ResponseBody
	public void jd(HttpServletRequest request,
			HttpServletResponse response){
		try {
			logger.info("jd回调返回数据："+RegUtils.reqString(request));
		} catch (Exception e) {
			logger.error("jd回调接口异常："+e.getMessage(),e);
		}
	}
	
}
