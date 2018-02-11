package cn.com.dubbo.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.bean.TmpHhr;
import cn.com.dubbo.model.OrderPackage;

@Repository
public interface OrderPackageMapper extends BaseMapper<OrderPackage, Integer> {

	// public void saveOrderDelivery(OrderDelivery orderDelivery);

	/**
	 * @param state
	 * @return
	 */
	public List<OrderPackage> getOrderPackage(String delivery_status);

	/**
	 * @param state
	 * @return
	 */
	public List<OrderPackage> getOrderPackage_36(String delivery_status);

	public void updateOrderPackage(String order_no);

	/**
	 * 根据渠道id查询未同步的物流信息
	 * 
	 * @param mutilChannelId
	 * @return
	 */
	public List<OrderPackage> queryPackageByChannelId(int mutilChannelId);

	/**
	 * 根据时间段查询物流信息
	 * 
	 * @author 常胜
	 * @date 2016-7-14 上午10:07:46
	 * @param map
	 * @return
	 * @return List<OrderPackage>
	 */
	public List<OrderPackage> queryPackageByMap(Map<String, Object> map);

	/**
	 * 根据包裹编号更新包裹
	 * 
	 * @author 常胜
	 * @date 2016-7-19 下午2:37:32
	 * @param orderPackage
	 * @return void
	 */
	public void updateByPackageNo(OrderPackage orderPackage);
	
	
	public OrderPackage findPackageByOrderNo(String orderNo);

	//查询temp_hhr表中的数据,更新物流信息和订单信息
	public List<TmpHhr> queryTmpHhr();
}
