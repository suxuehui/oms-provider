package cn.com.dubbo.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 和业务相关的共同类
 */
public class BussinessUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * 自定义渠道订单批次号
	 * 规则 时间   如果是自营商品传入值为空
	 * @param multiChannel  来源 如 
	 * PA 平安好医生/36 360健康
	 * @return
	 */
	public static long multiChannelOrderBatch(){
		return new Long(sdf.format(new Date()));
	}
	
	/**
	 * 自定义渠道订单批次号
	 * 规则 时间   如果是自营商品传入值为空
	 * @param multiChannel  来源 如 
	 * PA 平安好医生/36 360健康
	 * @return
	 */
	public static long multiChannelOrderBatch(String data){
		return new Long(sdf.format(new Date())+data);
	}
	
	/**
	 * 计算待支付金额(平安的)
	 * 公式=商品的销售价格+原始邮费-实际支付的价格-总的优惠价
	 * @param actualProductFee 商品的销售价格
	 * @param originalPostFee 原始邮费
	 * @param actualTotalFee 实际支付的价格
	 * @param discountFee 总的优惠价
	 * @return
	 */
	public static BigDecimal orderPayFee(BigDecimal actualProductFee,
			BigDecimal originalPostFee,BigDecimal actualTotalFee,BigDecimal discountFee){
		return MathUtil.sub(MathUtil.add(actualProductFee, originalPostFee), 
				MathUtil.add(actualTotalFee, discountFee));
	}

}
