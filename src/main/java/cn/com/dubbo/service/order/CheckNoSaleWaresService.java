package cn.com.dubbo.service.order;

/**
 * oms 汇总发货数据，并同步到药医通中间库
 */
public interface CheckNoSaleWaresService {
	
	//汇总发货数据，并同步到药医通中间库
	public void findNoSaleWares(int mulId);
	
	public int resetPageNo();

}
