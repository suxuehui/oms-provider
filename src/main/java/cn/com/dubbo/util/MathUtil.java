package cn.com.dubbo.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

public class MathUtil {
	
	protected final static Logger _log = Logger.getLogger(MathUtil.class);
	
	/**
	 * 将分转化为元
	 * @param v1
	 * @return
	 */
	public static BigDecimal changeF2Y(BigDecimal v1) {
		return v1.divide(new BigDecimal(100));
    }
	
	/**
	 * 加法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
		DecimalFormat df = new DecimalFormat("0.0000");
        return new BigDecimal(df.format(v1.add(v2)));
    }
	
	/**
	 * 减法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
		DecimalFormat df = new DecimalFormat("0.0000");
		
        return new BigDecimal(df.format(v1.subtract(v2)));
    }
	
	/**
	 * 乘法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
		DecimalFormat df = new DecimalFormat("0.0000");
        return new BigDecimal(df.format(v1.multiply(v2)));
    }
	
	/**
	 * 除法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal div(BigDecimal v1,BigDecimal v2,int len) {
		return v1.divide(v2,len,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * BigDecimal v1,BigDecimal v2,int len
	 * @param v1
	 * @param v2
	 * @return int 1:大于  0：等于  -1小于
	 */
	public static int compare(BigDecimal v1,BigDecimal v2){
		return v1.compareTo(v2);
	}
	
}
