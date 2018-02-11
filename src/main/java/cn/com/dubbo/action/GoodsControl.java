package cn.com.dubbo.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.service.inter.GoodsService;

import com.alibaba.fastjson.JSON;


//根据商品编码查询库存
@Controller
@RequestMapping("goods")
public class GoodsControl extends BaseAction{
	
	private Logger logger = Logger.getLogger(GoodsControl.class);
	
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 根据商品编码查询库存
	 * @author hhr
	 * @param goodsNo
	 * @return
	 */
	@RequestMapping(value="query-available-stock")
	public @ResponseBody String test(@RequestParam String goodsNo){
		
		Map<String,String> map = new HashMap<String, String>();
		response.addHeader("Access-Control-Allow-Origin","*");
		response.addHeader("Access-Control-Allow-Methods","*");
		response.addHeader("Access-Control-Max-Age","100");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Allow-Credentials","false");
		try { 
			if(null == goodsNo || "".equals(goodsNo)){
				map.put("notes", "商品编码不能为空");
				map.put("status", "error");
				return JSON.toJSONString(map);
			}
			goodsNo = goodsNo.trim();
			String  available_stock = goodsService.findGoodStockNumber(goodsNo);
			map.put("goodNo", goodsNo);
			map.put("available_stock", available_stock);
			map.put("status", "success");
			return  JSON.toJSONString(map); 
		} catch (Exception e) {
			 logger.error("根据商品编码查询库存 失败了....",e);
		}
		map.put("notes", "根据商品编码查询库存 失败了...");
		map.put("status", "error");
		return JSON.toJSONString(map);
		
	}	
	
	 
}
