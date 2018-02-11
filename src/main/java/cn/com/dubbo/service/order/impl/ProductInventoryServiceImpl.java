package cn.com.dubbo.service.order.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.base.BaseData;
import cn.com.dubbo.bean.TmpHhr;
import cn.com.dubbo.mapper.GoodsSaleInfoMapper;
import cn.com.dubbo.mapper.OrderPackageMapper;
import cn.com.dubbo.model.GoodsSaleInfoModel;
import cn.com.dubbo.service.order.ProductInventoryService;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.HttpClientUtils;
import cn.com.dubbo.util.StringUtil;
import cn.com.dubbo.util.TmUtil;

import com.alibaba.fastjson.JSON;


/**
 * 向205数据库同步库存信息 Service
 * @author hhr
 */
@Service("ProductInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService{	
	
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final Logger logger = Logger.getLogger(ProductInventoryServiceImpl.class);
	//--------------------------------------------------------------------------------------

	private String testUrl = BaseData.edbUrl;
	// 申请的appkey
	private String appkey =  BaseData.appkey;
	// 申请的secret
	private  String secret = BaseData.secret;
	// 申请的token
	private String token = BaseData.token;
	// 主帐号
	private String dbhost = BaseData.dbhost;
	// 返回格式 
	private String format = BaseData.format;
	
	DataQueue<GoodsSaleInfoModel> queueErr = new DataQueue<GoodsSaleInfoModel>();
	
	@Resource
	private GoodsSaleInfoMapper goodsSaleInfoMapper;
	
	@Resource
	private OrderPackageMapper orderPackageMapper;
	
	//测试e店宝的数据
	public static void main(String[] args) {
//		System.out.println(new ProductInventoryServiceImpl().queryProductInventory(page_size, page_no, date_type, begin_time, end_time, product_no, standard, bar_code, sort_no, store_id, isuit, iscut_store);
		System.out.println(new ProductInventoryServiceImpl().queryProductInventory(1, 1, "创建日期", "2000-01-01 00:00:00", "2016-11-02 12:12:12","QX0086", null, null, null, "6", "-1", "0"));
	}
    
	/**
     * 查询商品信息
     * @return
     * @author hhr
     */
	@Override
	public String queryProductInventory(Integer page_size,Integer page_no,String date_type,String begin_time,String end_time,String product_no,
			String standard,String bar_code,String sort_no,String store_id,String isuit,String iscut_store) {
		TreeMap<String, String> apiparamsMap = get_EDB_result(page_size, page_no, date_type, begin_time, end_time, product_no, standard, bar_code, sort_no, store_id, isuit, iscut_store);
		StringBuilder param = new StringBuilder(); 
		for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			if(e.getKey()!="appscret" && e.getKey()!="token"){
//				param.append("&").append(e.getKey()).append("=").append(e.getValue());
				if(e.getKey()=="shop_id" || e.getKey()=="wangwang_id" || e.getKey()=="date_type"){
					try {
						param.append("&").append(e.getKey()).append("=").append(TmUtil.encodeUri(e.getValue()));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				}else{
					param.append("&").append(e.getKey()).append("=").append(e.getValue());
				}
			}
		}
		String PostData="";
		PostData=param.toString().substring(1);
		String result="";
//		System.out.println(PostData);
		result=TmUtil.getResult(testUrl,PostData);
		return result;
	}
	

	//传入e店宝的参数map
	private TreeMap<String, String> get_EDB_result(Integer page_size,Integer page_no,String date_type,String begin_time,String end_time,String product_no,
			String standard,String bar_code,String sort_no,String store_id,String isuit,String iscut_store){
		
			TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
			
		 	apiparamsMap.put("method", "edbProductGet");//添加请求参数——接口名称	
	        String fields="product_no,brand_no,brand_name,standard,sort_no,sort_name,bar_code,barCode_remark,proitem_no,product_name,verder_no,ground_date,bookBuild_date,supplier" +
	        		",product_state,is_suit,sale_price,cost,tax_cost,entity_stock,sell_stock,standbook_stock,use_num";        
			apiparamsMap.put("fields", fields);
			apiparamsMap.put("page_size",page_size+""); 
			apiparamsMap.put("page_no", page_no+"");
			if(!"".equals(date_type) && null != date_type){
				apiparamsMap.put("date_type", date_type);//日期类型（建档日期/上架日期）
			}
			if(!"".equals(begin_time) && null != begin_time){
				apiparamsMap.put("begin_time", begin_time);
			}
			if(!"".equals(end_time) && null != end_time){
				apiparamsMap.put("end_time", end_time);
			}
			if(!"".equals(product_no) && null != product_no){
				apiparamsMap.put("product_no", product_no);//产品编号
			}
			if(!"".equals(standard) && null != standard){
				apiparamsMap.put("standard", standard);//规格
			}
			if(!"".equals(bar_code) && null != bar_code){
				apiparamsMap.put("bar_code", bar_code);//条形码
			}
			if(!"".equals(sort_no) && null != sort_no){
				apiparamsMap.put("sort_no", sort_no);//产品分类编号
			}
			if(!"".equals(store_id) && null != store_id){
				apiparamsMap.put("store_id", store_id);//库房ID可传多库房，以逗号隔开。如：1,2,3
			}
//			if(!"".equals(isuit) && null != isuit){
				apiparamsMap.put("isuit", "-1");//是否套装（0：不是1：是默认为0）目前只支持非套装
//			}
			if(!"".equals(iscut_store) && null != iscut_store){
				apiparamsMap.put("iscut_store", iscut_store);//是否累计负库存（0：不计1：累计  默认为0）E店宝界面不计负库存
			}
		
			apiparamsMap.put("dbhost", dbhost);//添加请求参数——主帐号
			apiparamsMap.put("appkey",appkey);//添加请求参数——appkey
	        apiparamsMap.put("format", format);//添加请求参数——返回格式
	        apiparamsMap.put("v", "2.0");//添加请求参数——版本号（目前只提供2.0版本）
	        apiparamsMap.put("slencry","0");//添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
			apiparamsMap.put("ip","172.16.2.28");//添加请求参数——IP地址       172.16.2.28:是正确的      		
			
			apiparamsMap.put("appscret",secret);//添加请求参数——appscret
			apiparamsMap.put("token",token);//添加请求参数——token
			String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
			apiparamsMap.put("timestamp", timestamp);//添加请求参数——时间戳
			
			//获取数字签名
			String sign = TmUtil.md5Signature(apiparamsMap, appkey);//appkey   secret
			apiparamsMap.put("sign", sign);
			
			return apiparamsMap;
	}  

	/**
	 * 同步库存到数据库中
	 * @return
	 * @author hhr
	 */
	@Override
	public String updateGoodsSaleInfo(Integer page_size, Integer page_no,String date_type, String begin_time, String end_time,String product_no, String standard, 
									  String bar_code,String sort_no, String store_id, String isuit, String iscut_store) {
		try {
			String retultStr = queryProductInventory(page_size, page_no, date_type, begin_time, end_time, product_no, standard, bar_code, sort_no, store_id, isuit, iscut_store);
			JSONObject obj = JSONObject.fromObject(retultStr);
			String success_flag = obj.get("Success").toString();
			List<GoodsSaleInfoModel> list = new  ArrayList<GoodsSaleInfoModel>();
			if (StringUtil.isStringNotBlank(success_flag)) {
				obj = JSONObject.fromObject(obj.get("Success").toString());
				int totalResults = new Integer(obj.getString("total_results")).intValue();
				logger.info("从e店宝获取数据成功, 总数 : " +totalResults);
				obj = JSONObject.fromObject(obj.get("items").toString());
				JSONArray trades_JSONArray = JSONArray.fromObject(obj.get("item").toString());
				if (trades_JSONArray.size() > 0) {
					for (int j = 0; j < trades_JSONArray.size(); j++) { // 遍历 jsonarray 数组，把每一个对象转成 json 对象
						JSONObject json_goods_sale = trades_JSONArray.getJSONObject(j);
						if (json_goods_sale.isEmpty()) {
							continue;
						}
						GoodsSaleInfoModel goodsSale  = new GoodsSaleInfoModel(); 
						goodsSale.setAvailableStock((Integer)json_goods_sale.get("sell_stock"));
						goodsSale.setAvailableStockTime(sdf.format(new Date())); 
						goodsSale.setGoodsNo( json_goods_sale.getString("product_no"));
						goodsSale.setGoodsNo_69( json_goods_sale.getString("bar_code"));
						
						list.add(goodsSale);
					}
				}
				if(list.size() > 0){
					this.updateGoodsSaleInfoBase(list);
				}
			 }
			if(null != queueErr && !queueErr.isEmpty()){
				//开始单条保存
				for (int i = 0; i < queueErr.getLength(); i++) {
					GoodsSaleInfoModel item = queueErr.deQueue();
					try {
						GoodsSaleInfoModel goods =  goodsSaleInfoMapper.queryGoodsSaleInfo(item.getGoodsNo());
           				if(null == goods ){
           					//数据库中没有该条信息,insert操作
           					Integer num =  goodsSaleInfoMapper.insertGoodsSaleInfo(item);
           					System.out.println(num);
           				}else{ 
           					//update操作
           					goodsSaleInfoMapper.updateGoodsSaleInfo(item);
           				}
					} catch (Exception e) {
						 logger.error("处理队列中保存错误的商品也失败了...",e);
					}
				}
			}
		 return "Y";
		} catch (Exception e) { 
			logger.error(" 请求E店宝的接口：edbProductGet【查询商品信息】 异常...");
			e.printStackTrace();
		}
		return "N";
	}

	//批量处理库存信息
	private void updateGoodsSaleInfoBase(List<GoodsSaleInfoModel>  list) {
		try {
			//查询
			   if(null!=list && list.size() > 0){
	           	logger.info(sdf.format(new Date()) + "更新库存开始，一共  :"+list.size() + "条");
	           	//返回查询的数量
	           	logger.info("先校验订单是否存在");
	           	Integer productNum = goodsSaleInfoMapper.checkProductInventory(list);
	           		//如果查询的数量和传入的list大小一样,则进行保存
	           		if(productNum == list.size()){
	           			goodsSaleInfoMapper.batchUpdateProductInventory(list);
	           		}else{
	           			//查询e店宝中,本地数据有的不存在,需要校验,进行添加的操作
	           			for (int i = 0; i < list.size(); i++) {
	           				GoodsSaleInfoModel goodsSale = list.get(i);
	           				GoodsSaleInfoModel goods =  goodsSaleInfoMapper.queryGoodsSaleInfo(goodsSale.getGoodsNo());
	           				if(null == goods ){
	           					//数据库中没有该条信息,insert操作
	           					Integer num =  goodsSaleInfoMapper.insertGoodsSaleInfo(goodsSale);
	           					System.out.println(num + "--集合中执行添加操作"+JSON.toJSONString(goodsSale)); 
	           				}else{ 
	           					//update操作
	           					goodsSaleInfoMapper.updateGoodsSaleInfo(goodsSale);
	           				}
						}
	           		}
	           	}
			   System.out.println("顺利执行中...");
		} catch (Exception e) {
			logger.error("批量处理库存信息异常...保存到队列中.....");
			e.printStackTrace();
			if(null != list && list.size() > 0){
				int i = 0;
				for(GoodsSaleInfoModel model : list){
					logger.info("正在加入队列 num:"+(++i));
					queueErr.enQueue(model);
				}
			}
		}
		
	}

	/**
	 * 根据e店宝返回的数据,进行更新库存操作
	 * @param retultStr
	 * @return
	 * @author hhr
	 */
	@Override
	public String updateGoodsSaleInfo(String retultStr) {
		try{
				JSONObject obj = JSONObject.fromObject(retultStr);
				String success_flag = obj.get("Success").toString();
				List<GoodsSaleInfoModel> list = new  ArrayList<GoodsSaleInfoModel>();
				if (StringUtil.isStringNotBlank(success_flag)) {
					obj = JSONObject.fromObject(obj.get("Success").toString());
					int totalResults = new Integer(obj.getString("total_results")).intValue();
					logger.info("从e店宝获取数据成功, 总数 : " +totalResults);
					obj = JSONObject.fromObject(obj.get("items").toString());
					JSONArray trades_JSONArray = JSONArray.fromObject(obj.get("item").toString());
					if (trades_JSONArray.size() > 0) {
						List<Map<String,String>> listGw = new ArrayList<Map<String,String>>(); 
						for (int j = 0; j < trades_JSONArray.size(); j++) { // 遍历 jsonarray 数组，把每一个对象转成 json 对象
							JSONObject json_goods_sale = trades_JSONArray.getJSONObject(j);
							if (json_goods_sale.isEmpty()) {
								continue;
							}
							Integer availableStock = Integer.valueOf(json_goods_sale.get("sell_stock").toString());
							String goodsNo =   json_goods_sale.getString("product_no");
							GoodsSaleInfoModel goodsSale  = new GoodsSaleInfoModel(); 
							goodsSale.setAvailableStock(availableStock);
							goodsSale.setAvailableStockTime(sdf.format(new Date())); 
							goodsSale.setGoodsNo(goodsNo);
							goodsSale.setGoodsNo_69( json_goods_sale.getString("bar_code"));
							
							list.add(goodsSale);
							
							//向官网同步库存信息
							Map<String,String> map = new HashMap<String, String>();
							map.put("goodsNo", goodsNo);
							map.put("actualStock", availableStock+"");
							listGw.add(map);
							
						}
						this.sycGwKuCun(listGw);
					}
					if(list.size() > 0){
						this.updateGoodsSaleInfoBase(list);
					}
				 }
				if(null != queueErr && !queueErr.isEmpty()){
					//开始单条保存
					for (int i = 0; i < queueErr.getLength(); i++) {
						GoodsSaleInfoModel item = queueErr.deQueue();
						try {
							GoodsSaleInfoModel goods =  goodsSaleInfoMapper.queryGoodsSaleInfo(item.getGoodsNo());
		       				if(null == goods ){
		       					//数据库中没有该条信息,insert操作
		       					Integer num =  goodsSaleInfoMapper.insertGoodsSaleInfo(item);
		       					System.out.println(num + "--队列中执行添加操作"+JSON.toJSONString(item)); 
		       				}else{ 
		       					//update操作
		       					goodsSaleInfoMapper.updateGoodsSaleInfo(item);
		       				}
						} catch (Exception e) {
							 logger.error("处理队列中保存错误的商品也失败了...",e);
						}
					}
				}
			 return "Y";
	} catch (Exception e) { 
		logger.error(" 请求E店宝的接口：edbProductGet【查询商品信息】 异常...");
		e.printStackTrace();
	}
	return "N";
	}

	//查询temp_hhr表中的数据,更新物流信息和订单信息
	@Override
	public List<TmpHhr> queryTmpHhr() {
		return orderPackageMapper.queryTmpHhr();
	}
	
	
	//向官网同步库存信息
	private void sycGwKuCun(List<Map<String,String>> list) {
		try {
			String pamaras = "list="+JSON.toJSONString(list);
			String text = HttpClientUtils.do_post(BaseData.GW_PODUCT_NUM, pamaras);
			System.out.println("向官网同步库存信息返回值: "+text); 
		} catch (Exception e) {
			logger.error(" 向官网同步库存信息 异常..."+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
}
