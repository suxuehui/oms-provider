package cn.com.dubbo.mapper2;

import java.util.List;

import cn.com.dubbo.model2.E_orderstate;
import cn.com.dubbo.model2.E_salesorderbatchno;
import cn.com.dubbo.model2.E_salesorderbatchnoExt;

public interface E_salesorderbatchnoExtMapper extends BaseDaoMapper2<E_salesorderbatchnoExt> {

	List<E_salesorderbatchnoExt> selectList(E_orderstate e_orderstate);

	// 郑立臣添加
	List<E_salesorderbatchnoExt> selectby(E_salesorderbatchno e_salesorderbatchno);

	List<E_salesorderbatchnoExt> selectListByeOrdernumber(E_orderstate e_orderstate);
}