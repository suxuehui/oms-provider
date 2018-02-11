package cn.com.dubbo.service.inter;


/**
 * 库存同步逻辑（oms --> 药医通）
 * 3，将明细信息按商品编码统计数量存入item_batch_index表中之后，同步信息到药医通中间库的逻辑	
	1.存入前需查询可用库存量=（中间库该商品的库存总量-中间库未销账的商品总量）
	（库存总量：e_pstock，查询该表）
	（未销账的商品总量，e_saleorderindex销账状态）
	2.如果统计数量<可用库存：存入表中（）
	存入表时需校验，该任务号对应的哪个批次号，或多个批次号（必须减去还没有销的库存）
	3.如果统计数量>可用库存：不存入表中
 *
 */
public interface StockSynchroService {
	
	//查询商品入库总数
	public void synchro(int multiChannelId);
	
}