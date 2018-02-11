package cn.com.dubbo.redis;

import org.apache.log4j.Logger;

import cn.com.dubbo.util.SerializeUtil;

/**
 * 客户端调用的<br/>
 * 操作key -- value 的方法
 *
 */
public class CacheUtil {

	private static final Logger log = Logger.getLogger(CacheUtil.class);
	private static final String keyPrefix = "OMS_";//key 前缀
	
	/*
	 * 根据KEY取缓存
	 * */
	@SuppressWarnings({ "unchecked" })
	public static <T> T readCache(String key, Object defaultVal) {
		T obj = null;
		try {
			byte[] cacheVal = JedisUtil.get((keyPrefix+key).getBytes());
			if(cacheVal != null && cacheVal.length > 0){
				return (T)SerializeUtil.unserialize(cacheVal);
			}
		} catch (Exception e) {
			log.error("取缓存异常，key=" + keyPrefix+key, e);
		}
		if(obj == null){
			obj = (T)(defaultVal);
		}
		return obj;
	}
	
	public static String readCache(String key) {
		try {
			return JedisUtil.get(keyPrefix+key);
		} catch (Exception e) {
			log.error("取缓存异常，key=" + keyPrefix+key, e);
		}
		return "";
	}
	
	/**
	 * 存缓存 T(永不过期)
	 * @param key
	 * @param obj
	 */
	public static void setCache(String key, String value){
		try{
			JedisUtil.set(keyPrefix+key,value);
			//永久保存
//			JedisUtil.expire(key, -1);
		} catch(Exception e){
			e.printStackTrace();
			log.error("存缓存异常，key=" + keyPrefix+key, e);
		}
	}
	
	/**
	 * 存缓存 T(存入指定的时间)
	 * @param key
	 * @param obj
	 * @param expiredKey 时间，单位：秒
	 */
	public static void setCache(String key, String value,int expiredKey){
		try{
			JedisUtil.set(keyPrefix+key,value);
			if(expiredKey > 0){
				JedisUtil.expire(keyPrefix+key, expiredKey);
			}
		} catch(Exception e){
			e.printStackTrace();
			log.error("存缓存异常，key=" + keyPrefix+key, e);
		}
	}
	
	/**
	 * 存缓存 T（可设置缓存时间）
	 * @param key
	 * @param obj
	 * @param expiredKey 时间，单位：秒
	 */
	public static <T> void setTCache(String key, T obj, int expiredKey){
		try{
			JedisUtil.set((keyPrefix+key).getBytes(), SerializeUtil.serialize(obj));
			if(expiredKey > 0){
				JedisUtil.expire((keyPrefix+key).getBytes(), expiredKey);
			}
		} catch(Exception e){
			log.error("存缓存异常，key=" + keyPrefix+key, e);
		}
	}
	
	/**
	 * 存缓存 T(永不过期)
	 * @param key
	 * @param obj
	 */
	public static <T> void setCache(String key, T obj){
		try{
			JedisUtil.set((keyPrefix+key).getBytes(), SerializeUtil.serialize(obj));
			//永久保存
//			JedisUtil.expire(key.getBytes(), -1);
		} catch(Exception e){
			log.error("存缓存异常，key=" + keyPrefix+key, e);
		}
	}
	
	/**
	 * 删缓存
	 */
	public static void removeCache(String key){
		try{
			JedisUtil.del(keyPrefix+key);
		} catch(Exception e){
			log.error("删缓存异常，key=" + keyPrefix+key, e);
		}
	}
	
	/**
	 * 删缓存
	 */
	public static void removeObj(byte[] key){
		try{
			JedisUtil.del(keyPrefix+key);
		} catch(Exception e){
			log.error("删缓存异常，key=" + keyPrefix+key, e);
		}
	}

}
