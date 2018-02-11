package cn.com.dubbo.service.inter;

import java.math.BigDecimal;
import java.util.List;

import cn.com.dubbo.model.EOrderBatchNO;
import cn.com.dubbo.model2.E_salesorderbatchno;
import cn.com.dubbo.model2.E_salesorderdetail;

/**
 * 用来查下药医通中间库的库存接口
 *
 */
public interface OutStockService {
	
	//查询商品入库总数
	public EOrderBatchNO findGoodsTotalNum(String goodsNo);
	
	//查询商品中间库未销账(包含审核失败和未审核的)的商品总量
	public int findNOffStockAmount(String goodsNo);
	
	//查询某商品的库存列表
	public List<EOrderBatchNO> queryStockList(String goodsNo);
	
	//查询库存中间表orderbatchno是否存在
	public int findBatchNo(int pid,String batchno);
	
	//保存中间库存表
	public void saveSalesOrderBatchNo(EOrderBatchNO batch);
	
	//保存e_orderstate
	public void saveOrderState(String eOrdernumber);
	
	//查询指定的任务单号
	public boolean findOrderState(String ordernumber);
	
	//保存e_saleorderindex 批次索引值
	public void saveOrderIndex(int multiChannelId,String eOrdernumber);
	
	//保存订单明细e_salesorderdetail
	public void saveOrderdetail(String eOrdernumber,int productId,int amount,BigDecimal sumPrice);
	
	//批量保存订单明细e_salesorderdetail
	public void saveBatchOrderdetail(List<E_salesorderdetail> saleorderdetails);
	
	/*//查询orderIndex 是否存在
	public boolean findOrderIndex(Integer multiChannelId, String ordernumber);
	*/
	//批量保存数据
	public void batchInsertNo(List<E_salesorderbatchno> recordList);
	
	
}