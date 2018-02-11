package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.mapper.ItemBatchDetailMapper;
import cn.com.dubbo.mapper.OrderItemMapper;
import cn.com.dubbo.service.order.CheckNoSaleWaresService;
/**
 * 每天凌晨2点查询未销商品
 * @author Qingy.Yao
 *	
 */
@Component
public class CheckNoSaleWaresTask {
	
	private static final Logger logger = Logger.getLogger(CheckNoSaleWaresTask.class);
	
	@Resource
	CheckNoSaleWaresService checkNoSaleWaresService;
	
	@Resource
	private OrderItemMapper orderItemMapper;
	
	@Resource
	private ItemBatchDetailMapper itemDetailMapper;
	
	
	public void execute() {
		try {
			if(!logger.isDebugEnabled()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("调用查询未销商品并保存，定时器开始:"+sdf.format(new Date())+"------------开始--------------");
//				checkNoSaleWaresService.findNoSaleWares(1);
//				checkNoSaleWaresService.findNoSaleWares(2);
//				checkNoSaleWaresService.findNoSaleWares(3);
				checkNoSaleWaresService.findNoSaleWares(4);
				checkNoSaleWaresService.findNoSaleWares(5);
				checkNoSaleWaresService.findNoSaleWares(8);
				System.out.println("调用查询未销商品并保存，定时器结束:"+sdf.format(new Date())+"------------开始--------------");
			}
			
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("校验未销商品是否保存正常，定时器开始:"+sdf.format(new Date())+"------------开始--------------");
			//校验更新的数据是否有漏
			OrderItem orderItem = new OrderItem();
			String add_time = DateUtil.DateToStr(new Date(), "yyyy-MM-dd");
			orderItem.setAdd_time(add_time);
			orderItem.setMulId(1);
			int oldCount = orderItemMapper.checkNosaleWaresCount(orderItem);//查询未销商品总条数
			ItemBatchDetail itemDatail = new ItemBatchDetail();
			itemDatail.setMulti_channel_id(1);
			itemDatail.setAddTime(add_time);
			int newCount = itemDetailMapper.queryBatchDeatiCount(itemDatail);
			if(oldCount-newCount>100){
				checkNoSaleWaresService.findNoSaleWares(1);
			}
			
			System.out.println("校验未销商品是否保存正常，定时器结束:"+sdf.format(new Date())+"------------开始--------------");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
