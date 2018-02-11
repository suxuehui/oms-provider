package cn.com.dubbo.service.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.PayCode;
import cn.com.dubbo.mapper.ChannelMapper;
import cn.com.dubbo.mapper.LogisticMapper;
import cn.com.dubbo.mapper.OrderLogMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.mapper.OrderPackageMapper;
import cn.com.dubbo.model.LogisticChannelModel;
import cn.com.dubbo.model.LogisticCompany;
import cn.com.dubbo.model.MultiChannelOrderBatchModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.AreaService;
import cn.com.dubbo.service.order.OrderRequestService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.util.BussinessUtil;
import cn.com.dubbo.util.DateUtil;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.Md5Util;
import cn.com.dubbo.util.StringUtil;

@Service("orderRequestService")
// @Transactional
public class OrderRequestServiceImpl implements OrderRequestService {
	@Autowired
	private ChannelMapper channelMapper;
	@Resource
	private AreaService areaService;
	@Resource
	private LogisticMapper logisticMapper;
	@Resource
	private OrderPackageMapper orderPackageMapper;
	@Autowired
	private OrderService orderService;
	@Resource
	private OrderLogMapper orderLogMapper;
	@Autowired
	private OrderMapper orderMapper;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * json字符串解析入库
	 */
	@Override
	@Transactional
	public Map<String, Object> insert(String str) {
		Map<String, Object> mapJson = new HashMap<String, Object>();
		try {
			JSONObject obj = JSONObject.fromObject(str);
			obj = JSONObject.fromObject(obj.get("root").toString());
			String total = obj.get("totalResults").toString();
			if (Integer.parseInt(total) > 40) {
				mapJson.put("error", "请求数量在30以内");
				return mapJson;
			} else {
				JSONArray trades_JSONArray = JSONArray.fromObject(obj.get("trades").toString());
				Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch();
				int recordNum = channelMapper.isExistChannelOrderBatch(multiChannelOrderBatch);
				if (recordNum <= 0) {
					// 保存multiChannelOrderBatch:渠道订单批次号到表ec_multi_channel_order_batch
					MultiChannelOrderBatchModel multiChannelOrderBatchModel = new MultiChannelOrderBatchModel();
					multiChannelOrderBatchModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
					multiChannelOrderBatchModel.setMultiChannelId(10);
					if (trades_JSONArray != null) {
						multiChannelOrderBatchModel.setOrderAmount(trades_JSONArray.size());
					}
					multiChannelOrderBatchModel.setAddTime(sdf.format(new Date()));
					channelMapper.saveChannelOrder(multiChannelOrderBatchModel);
				}

				if (trades_JSONArray.size() > 0) {
					List<OrderModel> orderModelList = new ArrayList<OrderModel>();

					for (int j = 0; j < trades_JSONArray.size(); j++) { // 遍历 jsonarray 数组，把每一个对象转成 json 对象
						JSONObject trades_JSONObject = trades_JSONArray.getJSONObject(j);
						if (trades_JSONObject.isEmpty()) {
							continue;
						}
						OrderModel orderModel = new OrderModel();
						orderModel.setMultiChannelId(10);
						if (multiChannelOrderBatch.longValue() != 111) {
							orderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
						}
						String payment = trades_JSONObject.getString("payment");// payment:实付金额。 pay_status
						if (StringUtil.isStringNotBlank(payment)) {
							orderModel.setIsPay("Y");
						} else {
							orderModel.setIsPay("N");
						}
						String tid = trades_JSONObject.getString("tid");
						orderModel.setMultiChannelOrderNo(tid);// 渠道订单号
						if (StringUtil.isStringNotBlank(tid)) {
							tid = "CA" + tid;
							orderModel.setOrderNo(tid);
						}
						// createTime是毫秒
						String createTime_UTC = trades_JSONObject.getString("createTime");
						if (StringUtil.isStringNotBlank(createTime_UTC)) {
							Date utc_to_Date = DateUtil.getUTCToDate(createTime_UTC);
							orderModel.setPlatformCreateTime(sdf.format(utc_to_Date));
							orderModel.setCommitTime(sdf.format(utc_to_Date));
						}
						String receiverProvinceName = trades_JSONObject.getString("receiverProvince");
						orderModel.setProvinceName(receiverProvinceName);
						if (StringUtil.isStringNotBlank(receiverProvinceName)) {
							// AreaModel areaModel=areaMapper.findArea(receiverProvinceName);
							orderModel.setProvinceId(areaService.findArea(0, receiverProvinceName));
							// receiverProvinceName根据省会名称，从redis查询省会ID
							// orderModel.setProvinceId(areaModel.getAreaId());
						}
						String receiverCity = trades_JSONObject.getString("receiverCity");
						orderModel.setCityName(receiverCity);
						if (StringUtil.isStringNotBlank(receiverCity)) {
							orderModel.setCityId(areaService.findArea(orderModel.getProvinceId(), receiverCity));
						}
						String receiverDistrict = trades_JSONObject.getString("receiverDistrict");
						orderModel.setAreaName(receiverDistrict);
						if (StringUtil.isStringNotBlank(receiverDistrict)) {
							orderModel.setAreaId(areaService.findArea(orderModel.getCityId(), receiverDistrict));
						}

						String detailedAddress = trades_JSONObject.getString("receiverAddress");
						String tempReceiveAddress = receiverProvinceName.concat(receiverCity).concat(receiverDistrict);
						orderModel.setReceiveAddress(tempReceiveAddress);// 收货地址
						orderModel.setReceiveFullAddress(tempReceiveAddress.concat(detailedAddress));// 完整收货地址
						// 县
						// String county = Jd_User.getCounty();
						// orderModel.setAreaId(areaService.findArea(orderModel.getCityId(), county));

						String receiverName = trades_JSONObject.getString("receiverName");
						if (StringUtil.isStringNotBlank(receiverName)) {
							orderModel.setReceiveUser(receiverName);
						}
						String receiverPhone = trades_JSONObject.getString("receiverPhone");
						if (StringUtil.isStringNotBlank(receiverPhone)) {
							orderModel.setReceiveTel(receiverPhone);
						}
						String receiverMobile = trades_JSONObject.getString("receiverMobile");
						if (StringUtil.isStringNotBlank(receiverMobile)) {
							orderModel.setReceiveMobile(receiverMobile);
						}
						String companyName = trades_JSONObject.getString("logisticsCompany");
						String logisticsCompanyCode = trades_JSONObject.getString("logisticsCompanyCode");
						LogisticChannelModel logisticChannelModel = null;
						if (StringUtil.isStringNotBlank(companyName)) {
							int logisticCompanyId = 0;
							if (companyName.equals("申通")) {
								companyName = "申通快递";
							}
							System.out.println("55555  " + companyName);
							List<LogisticCompany> logisticCompanyList = logisticMapper.findLogisticCompanyId(companyName);
							if (logisticCompanyList != null && logisticCompanyList.size() > 0 && (logisticCompanyList.get(0) != null)) {
								logisticCompanyId = logisticCompanyList.get(0).getLogisticCompanyId();
							}
							if (logisticCompanyId != 0) {
								orderModel.setLogLogisticCompanyId(logisticCompanyId);

								Map<String, Object> map = new HashMap<String, Object>();
								map.put("channelType", "36");
								map.put("logisticId", logisticCompanyId);
								String channel_code = logisticMapper.findThirdCode(map);

								if (StringUtil.isBlank(channel_code)) {
									// 保存信息到表logistic_channel
									logisticChannelModel = new LogisticChannelModel();
									logisticChannelModel.setChannelType("36");
									logisticChannelModel.setChannelCode(logisticsCompanyCode);
									logisticChannelModel.setCompanyName(companyName);
									logisticChannelModel.setLogisticId(logisticCompanyId);
								}
							}
						}

						// orderModel.setLogLogisticCompanyId(1);
						// trades_JSONObject.getString("logisticsCompany");

						// modifyTime是毫秒
						String modifyTime = trades_JSONObject.getString("modifyTime");
						if (StringUtil.isStringNotBlank(modifyTime)) {
							Date utc_to_Date2 = DateUtil.getUTCToDate(modifyTime);
							orderModel.setPlatformEditTime(sdf.format(utc_to_Date2));
						}
						String status = trades_JSONObject.getString("status");
						// if (tid.equalsIgnoreCase("36A1801133782018114101")) {
						if (StringUtil.isStringNotBlank(status)) {
							if (status.equals("WAIT_SELLER_SEND_GOODS") || status.equals("WAIT_BUYER_PAY")) {
								orderModel.setOrderStatus(1);
							}
							if (status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
								orderModel.setOrderStatus(10);
							}
							if (status.equals("TRADE_CLOSED")) {// 交易关闭
								orderModel.setOrderStatus(7);
							}
							if (status.equals("TRADE_BUYER_SIGNED")) {// 买家已签收
								orderModel.setOrderStatus(12);
							}
							if (status.equals("TRADE_FINISHED")) {// 交易成功
								orderModel.setOrderStatus(13);
							}
							// int orderStatus=getCodeValue(status);
							// orderModel.setOrderStatus(orderStatus);
						}
						// }
						orderModel.setOrderType(0);// 订单类型(0.正常订单 1.补发订单)
						String haveCFY = trades_JSONObject.getString("haveCFY");
						if (haveCFY.equals("1")) {
							orderModel.setHaveCfy("Y");
						} else {
							orderModel.setHaveCfy("N");
						}

						String invoice = trades_JSONObject.getString("invoice");
						if (StringUtil.isStringNotBlank(invoice)) {
							JSONObject obj_Invoice = JSONObject.fromObject(invoice);
							orderModel.setInvoiceTitle(obj_Invoice.getString("title"));
							orderModel.setInvoiceContent(obj_Invoice.getString("content"));
						}

						String paymentType = trades_JSONObject.getString("paymentType");
						String paymentType_Code = setPayMethod(paymentType);
						if (StringUtil.isStringNotBlank(paymentType_Code)) {
							orderModel.setPaymentType(paymentType_Code);
						}
						String postFee = trades_JSONObject.getString("postFee");
						if (StringUtil.isStringNotBlank(postFee)) {
							orderModel.setDeliveryFeeOld(new BigDecimal(postFee));
						}
						String venderRemark = trades_JSONObject.getString("venderRemark");
						if (StringUtil.isStringNotBlank(venderRemark)) {
							orderModel.setOrderNotes(venderRemark);
						}
						String customerRemark = trades_JSONObject.getString("customerRemark");
						if (StringUtil.isStringNotBlank(customerRemark)) {
							orderModel.setOrderMsg(customerRemark);
						}
						String tradeFrom = trades_JSONObject.getString("tradeFrom");
						if (StringUtil.isStringNotBlank(tradeFrom)) {
							// TODO OrderModel--fromMedia fromMedia;//来源媒体,存储ios/android/第三方渠道url
							orderModel.setFromMedia(tradeFrom);
						}
						String temp_addTime = sdf.format(new Date());// 添加时间
						orderModel.setAddTime(temp_addTime);// 添加时间
						orderModelList.add(orderModel);
						if (true) { // flag_save_update!=2:不是更新。暂时不需要更新订单明细
							JSONArray tradeDetails_JSONArray = JSONArray.fromObject(trades_JSONObject.get("tradeDetails").toString());
							if (tradeDetails_JSONArray.size() > 0) {
								List<OrderItemModel> orderItemModelList = new ArrayList<OrderItemModel>();
								List<PaymentLogModel> paymentLogList = new ArrayList<PaymentLogModel>();
								BigDecimal deliveryFee = new BigDecimal(0.00000);
								BigDecimal activityDiscountFee = new BigDecimal(0.00000);
								BigDecimal paidFee = new BigDecimal(0.00000);// 支付金额
								for (int k = 0; k < tradeDetails_JSONArray.size(); k++) { // 遍历 jsonarray 数组，把每一个对象转成 json 对象
									JSONObject tradeDetails_JSONObject = tradeDetails_JSONArray.getJSONObject(k);
									tradeDetails_JSONObject.isEmpty();
									if (tradeDetails_JSONObject.isEmpty()) {
										continue;
									}
									// primaryKey-orderItemId title-name num-amount price-price;//商品成交价 cid
									// outer_item_id-goodsNo divide_fee-goodsSumFee;//小计金额 detailsDiscount
									// 商品成交价*商品数量-优惠金额----订单明细之和；再加上订单主表的delivery_fee；等于订单明细的“小计金额”之和
									OrderItemModel orderItemModel = new OrderItemModel();
									orderItemModel.setOrderNo(tid);
									// String primaryKey = tradeDetails_JSONObject.getString("primaryKey");
									// if(StringUtil.isStringNotBlank(primaryKey)){
									// orderItemModel.setOrderItemId(new Long(primaryKey).longValue());
									// }
									String title = tradeDetails_JSONObject.getString("title");
									if (StringUtil.isStringNotBlank(title)) {
										orderItemModel.setName(title);
									}
									String num = tradeDetails_JSONObject.getString("num");
									if (StringUtil.isStringNotBlank(num)) {
										Long n = new Long(num).longValue();
										orderItemModel.setAmount(n);// 商品数量

									}
									String price = tradeDetails_JSONObject.getString("price");
									if (StringUtil.isStringNotBlank(price)) {
										orderItemModel.setOldPrice(new BigDecimal(price));// oldPrice;//商品原价 price;//商品成交价
									}
									String outerItemId = tradeDetails_JSONObject.getString("outerItemId");
									if (StringUtil.isBlank(outerItemId)) {
										outerItemId = "6923146105016";
									}
									if (StringUtil.isStringNotBlank(outerItemId)) {
										orderItemModel.setGoodsNo_69(outerItemId);
									}
									String itemId = tradeDetails_JSONObject.getString("itemId");
									if (StringUtil.isStringNotBlank(itemId)) {
										orderItemModel.setGoodsNo(itemId);
									}
									// 明细类型
									if (!StringUtil.isBlank(outerItemId) && outerItemId.startsWith("TM")) {
										orderItemModel.setGoodsListType(Constant.GOODS_TYPE_GROUP);
									} else {
										orderItemModel.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
									}

									String divideFee = tradeDetails_JSONObject.getString("divideFee");
									if (StringUtil.isStringNotBlank(divideFee)) {
										orderItemModel.setGoodsSumFee(new BigDecimal(divideFee));// 小计金额
									}

									// 以下是订单主表还需要插入的一些信息
									String partPostFee = tradeDetails_JSONObject.getString("partPostFee");
									deliveryFee = MathUtil.add(deliveryFee, new BigDecimal(partPostFee));

									String partDiscount = tradeDetails_JSONObject.getString("partDiscount");
									activityDiscountFee = MathUtil.add(activityDiscountFee, new BigDecimal(partDiscount));
									String detailsDiscount = tradeDetails_JSONObject.getString("detailsDiscount");
									activityDiscountFee = MathUtil.add(activityDiscountFee, new BigDecimal(detailsDiscount));

									String divideFee66 = tradeDetails_JSONObject.getString("divideFee");
									paidFee = MathUtil.add(paidFee, new BigDecimal(divideFee66));

									BigDecimal priceDis = new BigDecimal(0.00000);
									String partDiscount66 = tradeDetails_JSONObject.getString("partDiscount");
									priceDis = MathUtil.add(priceDis, new BigDecimal(partDiscount66));
									String detailsDiscount66 = tradeDetails_JSONObject.getString("detailsDiscount");
									priceDis = MathUtil.add(priceDis, new BigDecimal(detailsDiscount66));
									orderItemModel.setPriceDis(priceDis);// 优惠金额

									orderItemModel.setAddTime(temp_addTime);// 添加时间
									orderItemModelList.add(orderItemModel);

									// 以下是支付日志的
									// List<PaymentLogModel> payLogList = new ArrayList<PaymentLogModel>();
									// PaymentLogModel logModel = null;
									PaymentLogModel logModel = new PaymentLogModel();
									logModel.setBusinessType("ADD");// 业务类型
									logModel.setBusinessId(tid);// 订单号

									logModel.setPaidFee(new BigDecimal(divideFee));
									// utc时间格式转换必须*1000
									logModel.setPaymentTime(sdf.format(new Date(new Long(createTime_UTC).longValue())));
									// logModel.setAddTime(sdf.format(new Date()));

									setPayMethod(paymentType, logModel);
									logModel.setAddTime(temp_addTime);// 添加时间
									paymentLogList.add(logModel);

								}
								orderModel.setDeliveryFeeOld(deliveryFee);// 原运费金额
								orderModel.setDeliveryFee(deliveryFee);// 实际运费金额
								orderModel.setActivityDiscountFee(activityDiscountFee);// 优惠
								BigDecimal paidFee66 = MathUtil.sub(paidFee, deliveryFee);
								orderModel.setPaidFee(paidFee66);// 实际支付的价格
								// BigDecimal skuFee=MathUtil.add(deliveryFee, activityDiscountFee);
								BigDecimal skuFee = MathUtil.add(activityDiscountFee, paidFee);
								skuFee = MathUtil.sub(skuFee, deliveryFee);// 货款金额（不含优惠）--不包含邮费
								orderModel.setOrderFee(paidFee66);// orderFee;//订单金额
								orderModel.setSkuFee(skuFee);// 货款金额（不含优惠）
								orderModelList.add(orderModel);
								if (orderItemModelList.size() > 0) {
									// logisticChannelModel有可能是null
									orderService.saveTrade(orderModel, orderItemModelList, paymentLogList, logisticChannelModel);
								}
							}
							mapJson.put("ok", "success");
						}
					}

				} else {
					try {
						throw new Exception();
					} catch (Exception e) {
						System.out.println("请求360haoyao的接口：查询卖家交易数据,抛出异常，没有成功");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			mapJson.put("error", "unknown exception");
			e.printStackTrace();
		}

		return mapJson;
	}

	private String setPayMethod(String payment_type) {
		if (payment_type.equalsIgnoreCase("A4A")) {
			return new Integer(PayCode.WS_ALI).toString();
		} else if (payment_type.equalsIgnoreCase("A4C")) {
			return new Integer(PayCode.YH_ALI).toString();
		} else if (payment_type.equalsIgnoreCase("A4E")) {
			return new Integer(PayCode.XX_ALI).toString();
		}
		return null;
	}

	private void setPayMethod(String payment_type, PaymentLogModel logModel) {
		/*
		 * 支付方式
		 */
		if (payment_type.equalsIgnoreCase("A4A")) {
			logModel.setOrderPaymentId(PayCode.WS_ALI);
			logModel.setOrderPaymentName("网上支付");
		} else if (payment_type.equalsIgnoreCase("A4C")) {
			logModel.setOrderPaymentId(PayCode.YH_ALI);
			logModel.setOrderPaymentName("银行转账");
		} else if (payment_type.equalsIgnoreCase("A4E")) {
			logModel.setOrderPaymentId(PayCode.XX_ALI);
			logModel.setOrderPaymentName("线下支付");
		}
	}

	/**
	 * 参数验证
	 */
	@Override
	public boolean checkParam(String str) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		JSONObject obj = JSONObject.fromObject(str);
		obj = JSONObject.fromObject(obj.get("root").toString());
		String dbhost = obj.get("dbhost").toString();
		String appkey = obj.get("appkey").toString();
		String timestamp = obj.get("timestamp").toString();
		String venderId = obj.getString("venderId");
		String v = obj.get("v").toString();
		String sign = obj.get("sign").toString();
		if (StringUtil.isStringNotBlank(dbhost) && StringUtil.isStringNotBlank(appkey) && StringUtil.isStringNotBlank(timestamp) && StringUtil.isStringNotBlank(sign) && StringUtil.isStringNotBlank(venderId) && StringUtil.isStringNotBlank(v)) {
			map.put("dbhost", dbhost);
			map.put("appkey", appkey);
			map.put("timestamp", timestamp);
			map.put("venderId", venderId);
			map.put("v", v);
			if (this.checkMd5(map, sign)) {
				long time = Long.parseLong(DateUtil.getDate2UTC(new Date()));
				long n = 60 * 3;
				long tamp = Long.parseLong(timestamp);
				if ((time - tamp) > n) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	public boolean checkMd5(TreeMap<String, String> map, String sign) {
		String str = Md5Util.edbTradeGet(map);
		if (str.equals(sign.toUpperCase())) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getDate2UTC(new Date()));
		System.out.println(new Date().getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String time = new Long(calendar.getTimeInMillis() / 1000).toString();
		System.out.println(time);
	}
}
