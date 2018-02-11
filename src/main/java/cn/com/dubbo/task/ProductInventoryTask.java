package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.ProductInventoryService;
import cn.com.dubbo.util.StringUtil;

/**
 * 向205数据库同步库存信息 Task
 * @author hhr
 */
@Component
public class ProductInventoryTask {
	
	private static final Logger logger = Logger.getLogger(ProductInventoryTask.class);
	
	@Resource
	private ProductInventoryService productInventoryService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void execute() {
		try {
			if(!logger.isDebugEnabled()){
				System.out.println("向205数据库同步库存信息，定时器开始:"+sdf.format(new Date())+"------------开始--------------");
				Integer page_size = 60;
				Integer page_no = 1;
				while(true){
					//只更新姚辛庄的库房信息 
					String reult = productInventoryService.queryProductInventory(page_size, page_no, null, null, null, null, null, null, null, "6", null, null);
					JSONObject obj = JSONObject.fromObject(reult);
					String success_flag = obj.get("Success").toString();
					if (StringUtil.isStringNotBlank(success_flag)) {
						obj = JSONObject.fromObject(obj.get("Success").toString());
						int totalResults = new Integer(obj.getString("total_results")).intValue();
						if(0 == totalResults){
							break;
						}
					}
					productInventoryService.updateGoodsSaleInfo(reult);
					page_no++;
					System.out.println("执行中,第"+page_no+"页....."); 
				}
			
				System.out.println("向205数据库同步库存信息，定时器结束:"+sdf.format(new Date())+"------------开始--------------");
			}
			
		} catch (Exception e) {
			System.out.println("向205数据库同步库存信息，定时器是失败了...."+sdf.format(new Date()));
			e.printStackTrace();
		}
	}
	
}
