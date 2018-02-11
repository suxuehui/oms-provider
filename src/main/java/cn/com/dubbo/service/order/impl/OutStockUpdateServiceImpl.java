package cn.com.dubbo.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.constant.ThirdConstant;
import cn.com.dubbo.model.GoodsSaleInfoModel;
import cn.com.dubbo.service.inter.GoodsService;
import cn.com.dubbo.service.order.OutStockUpdateService;
import cn.com.dubbo.util.HttpClientUtils;
import cn.com.dubbo.util.StringUtil;

import com.pajk.openapi.codec.client.RequestEncoder;
import com.pajk.openapi.codec.client.RequestEntity;
import com.pajk.openapi.codec.client.ResponseDecoder;

@Service
public class OutStockUpdateServiceImpl implements OutStockUpdateService {

	
	//平安正式环境的更新库存接口apiId
	private static String apiId_pa = "f7e89760676e25f2d9c5e547b6a21786#PROD";
	
	private final static String merchantId = "1";
	
	private static final Logger logger = Logger.getLogger(OutStockUpdateServiceImpl.class);
	
	@Resource
	private GoodsService goodsService;
	
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void paStockUpdate(){
		
		List<GoodsSaleInfoModel> saleGoods = goodsService.querySaleInfo(4);
		if(null!=saleGoods&&saleGoods.size()>0){
			Map<String, Integer> params = new HashMap<String, Integer>();
			for(GoodsSaleInfoModel good : saleGoods){
				if(!StringUtil.isBlank(good.getGoodsNo_69())&&isLong(good.getGoodsNo_69())){
					if(good.getAvailableStock()<0){
						params.put(good.getGoodsNo_69(), 200);
					}else{
						params.put(good.getGoodsNo_69(), good.getAvailableStock()*5+200);
					}
				}
			}
			//更新库存信息
			if(null!=params&&params.size()>0){
				String retMsg = this.paUpdateStock(params);
				logger.info("同步库存返回结果 ："+retMsg);
				//保存日志记录
			}
		}
	}
	
	/**
	  * 判断字符串是否是整数
	  */
	public static boolean isLong(String value) {
		try {
			Long.parseLong(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * 更新库存
	 */
	public static void main(String[] args) {
		OutStockUpdateServiceImpl impl = new OutStockUpdateServiceImpl();
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("TM160926154029860", 20);
		String tt = impl.paUpdateStock(params);
		System.out.println(tt);
	}
	
	/**
	 * 更新库存信息
	 * TODO 库存值*5+200
	 * @return
	 */
	@Override
	public String paUpdateStock(Map<String, Integer> params){
		
		try {
			RequestEncoder encoder = new RequestEncoder(ThirdConstant.partnerId_pa, ThirdConstant.key_pa, apiId_pa);
			//string 类型参数
			String arg1 = ThirdConstant.sellerId_pa;//合作伙伴集团ID
			
			String arg2 = merchantId;//合作伙伴药房id
			
			//按接口定义的参数顺序放入参数
			encoder.addParameter(arg1);
			encoder.addParameter(arg2);
			encoder.addParameter(params);
			
			//进行加密
			RequestEntity e = encoder.encode();
			String apiName = "updateProductStock";
			//拼装url
			String url = ThirdConstant.baseUrl_pa + ThirdConstant.apiGroup_pa +"/"+ apiName +"?";
			String postURL = url + e.getQueryParams();
//			System.out.println("url: "+postURL);
			String postData = e.getFormParams();

			String text = HttpClientUtils.do_post(postURL, postData);
			
			// 解析返回值(此处使用fastjson 1.2.4解析json字符串，也可使用其他json解析类库)
			Map obj = JSONObject.fromObject(text);
			String objectStr = obj.get("object").toString();
			Integer code =  (Integer)obj.get("code");
			//调用成功
			if(code==0){
			    //解码返回结果
				ResponseDecoder decoder =new ResponseDecoder(ThirdConstant.key_pa);
				decoder.decode(objectStr);
				String resultData = decoder.getData();
				JSONObject object = new JSONObject().fromObject(resultData);
				/**
				 *  错误码：1001,错误信息：{"data":[{"id":"4015630065981","msg":"我方未添加此商品"},{"id":"6955818204130","msg":"库存锁定"},
				 *  {"id":"6930159563092","msg":"我方未添加此商品"},{"id":"6917246009930","msg":"库存锁定"}],"code":1001,"msg":"部分更新失败"}
				 */
				int resultCode = object.getInt("code");
				if(0==resultCode){//返回成功
					return "0";
				}else{
					logger.error("错误码："+resultCode + ",错误信息："+ object);
					return object.toString();
				}
			}else{
				return "调用平安接口失败："+objectStr;
			}
		} catch (Exception e) {
			logger.error("向平安同步库存信息失败:"+e.getMessage(),e);
		}
		return "系统异常";
		
	}
	
	
}
