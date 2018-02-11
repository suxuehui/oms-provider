package cn.com.dubbo.action;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.service.order.WinRecordService;
import cn.com.dubbo.util.BaseObject;
import cn.com.dubbo.util.BussinessUtil;
import cn.com.dubbo.util.FunctionUtil;
import cn.com.dubbo.util.InitUtils;
import cn.com.dubbo.util.JsonUtil;

import com.alibaba.fastjson.JSON;


/**
 * 导入中奖纪录到数据库中
 * @author hhr
 */
@Controller
@RequestMapping("/import-win-record")
public class ImportWinRecordController  extends BaseAction{
	
	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource 
	private WinRecordService winRecordService;
	
	/**
	 * 导入中奖数据的方法
	 * @author hhr
	 * @param fileName
	 */
	@RequestMapping(value = "/exc", method = RequestMethod.GET)
	public @ResponseBody  String  UpdateGoods69Main(@RequestParam String fileName) throws Exception{
		try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\中奖数据导入文件\\"+fileName); 
			String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
			//批次号
			Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("8");
			
			List<OrderModel> list  = null;
			if ("xls".equals(extension)) {
//				list = read2003Excel(file);
			} else if ("xlsx".equals(extension)) {
				list = read2007Excel(file,multiChannelOrderBatch);
			} 
			System.out.println(JSON.toJSONString(list));
			winRecordService.saveWinRecord(list,multiChannelOrderBatch);
			 return "Y";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "入口异常!";
	}
	
	//读取Office 2007 excel 
		private   List<OrderModel> read2007Excel(File file,Long multiChannelOrderBatch) throws  Exception {
			// 构造 XSSFWorkbook 对象，strPath 传入文件路径
			XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = xwb.getSheetAt(0);
			XSSFRow row = null;
			XSSFCell cell = null;
			
			List<OrderModel> list = new ArrayList<OrderModel>(); 
			OrderModel orderModel = null;
			OrderItemModel orderItemModel = null;
			
			 
			for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				//忽略第一行
				if(i == sheet.getFirstRowNum()){
					continue;
				}
				orderModel = new OrderModel();
				//设置基础信息
				orderModel.setMultiChannelId(8);
				orderModel.setFromMedia("直播中奖");
				orderModel.setOrderType(0);
				orderModel.setOrderStatus(2);
				orderModel.setSendStatus("N");
				orderModel.setValid("Y");
				Random rand = new Random();
				Long order_head = Math.abs(rand.nextLong());
//				System.out.println(order_head);
				orderModel.setOrderNo("ZB"+order_head);
				orderModel.setMultiChannelOrderNo(order_head.toString());
				orderModel.setLogLogisticCompanyId(58);
				orderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
				orderModel.setIsPay("Y");
				BigDecimal bdZero = new BigDecimal("0");
				orderModel.setDeliveryFeeOld(bdZero);
				orderModel.setDeliveryFee(bdZero);
				orderModel.setSkuFee(bdZero);
				orderModel.setOrderPoints(bdZero);
				orderModel.setOrderVouchers(bdZero);
				orderModel.setActivityDiscountFee(bdZero);
				orderModel.setPaidFee(bdZero);
				orderModel.setOrderPayFee(bdZero);
				orderModel.setOrderFee(bdZero);
				orderModel.setOrderReturnFee(bdZero); 
				orderModel.setOrderRealFee(bdZero);
				orderModel.setSupplyPrice(bdZero);
				orderModel.setCommitTime(sdf.format(new Date()));
				orderModel.setPlatformCreateTime(sdf.format(new Date()));
				orderModel.setAddTime(sdf.format(new Date()));
				orderModel.setHaveCfy("N");
				
				//判断是否为空
				boolean flag = false;
				List<OrderItemModel> itemList = null;
				
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					
					cell = row.getCell(j);
					if (cell == null) {
						continue;
					}
					System.out.println("------------------------------"+i);
					if (j == 0) {
						cell = row.getCell(j);
						if (cell != null) {
							//昵称
							orderModel.setMemberNick(cell.getStringCellValue().trim());
						}
					}
					if (j == 1) {
						cell = row.getCell(j);
						if (cell != null) {
							flag = true;
						}
					}
					
					if (j == 2) {
						cell = row.getCell(j);
						if (cell != null) {
							//姓名
							orderModel.setReceiveUser(cell.getStringCellValue().trim());
						}
					}
					if (j == 3) {
						cell = row.getCell(j);
						if (cell != null) {
							//姓名
							orderModel.setReceiveFullAddress(cell.getStringCellValue().trim());
						}
					}
					if (j == 4) {
						cell = row.getCell(j);
						if (cell != null) {
							//手机
							Double receiveMobile = cell.getNumericCellValue();
							DecimalFormat df = new DecimalFormat("#");
							orderModel.setReceiveMobile(df.format(receiveMobile));
						}
					}
					if (j == 5) {
						cell = row.getCell(j);
						if (cell != null) {
							orderItemModel = new OrderItemModel();
							//组装item
							orderItemModel.setOrderNo("ZB"+order_head);
							orderItemModel.setGoodsType("OTHER");
							orderItemModel.setGoodsListType("GENERAL");
							orderItemModel.setAddTime(sdf.format(new Date())); 
							BigDecimal itemZero = new BigDecimal("0");
							orderItemModel.setSupplyPrice(itemZero);
							orderItemModel.setOldPrice(itemZero);
							orderItemModel.setPrice(itemZero);
							orderItemModel.setPriceDis(itemZero);
							orderItemModel.setGoodsSumFee(itemZero);
							String goods69 = cell.getStringCellValue().replace("'","");
							orderItemModel.setGoodsNo_69(goods69.trim());
						}
					}
					if (j == 6) {
						cell = row.getCell(j);
						if (cell != null) {
							orderItemModel.setName(cell.getStringCellValue().trim());
						}
					}
					if (j == 7) {
						cell = row.getCell(j);
						if (cell != null) {
							orderItemModel.setStandard(cell.getStringCellValue().trim());
						}
					}
					if (j == 8) {
						cell = row.getCell(j);
						if (cell != null) {
							//价格
							String Price = cell.getStringCellValue();
							orderItemModel.setCostPrice(new BigDecimal(Price));
						}
					}
					if (j == 9) {
						cell = row.getCell(j);
						if (cell != null) {
//							double amoutTmp = cell.getNumericCellValue();
//							int amout = (int)amoutTmp;
							//数量
							orderItemModel.setAmount(Long.valueOf("1"));
						}
					}
					if (j == 10) {
						cell = row.getCell(j);
						if (cell != null) {
							orderModel.setOrderNotes(cell.getStringCellValue().trim());
						
						}
					} 
					if (j == 11) {
						cell = row.getCell(j);
						if (cell != null) {
							orderModel.setProvinceName(cell.getStringCellValue().trim());
						}
					} 
					if (j == 12) {
						cell = row.getCell(j);
						if (cell != null) {
							orderModel.setCityName(cell.getStringCellValue().trim());
						}
					} 
					
					if (j == 13) {
						cell = row.getCell(j);
						if (cell != null) {
							orderModel.setAreaName(cell.getStringCellValue().trim());
						}
						itemList = new ArrayList<OrderItemModel>();
						itemList.add(orderItemModel);
					} 
				}
				if(flag && itemList != null){
					orderModel.setItemList(itemList);
					list.add(orderModel);
				}
			}
			return list;
		 }
		
		public static void main(String[] args) throws Exception {
			String str = JSON.toJSONString(new ImportWinRecordController().read2007Excel(new File("C:\\Users\\Administrator\\Desktop\\中奖数据导入文件\\TestExc.xlsx"),1L));
			System.out.println(str);
		}
		
		/**
		 * 展示平安订单信息
		 */
		@RequestMapping(value="pOrderIn")
		@ResponseBody
		public void paOrders(HttpServletRequest request,
				HttpServletResponse response){
			BaseObject object = new BaseObject();
			try {
				object.setCode(InitUtils.STATUS_OK);
				winRecordService.savePDOrder("D:\\拼多多订单(1).xlsx");
			} catch (Exception e) {
				object.setCode(InitUtils.STATUS_300);
				object.setMsg("系统异常，请联系管理员");
				System.out.println(e.getMessage());
//				logger.error("调用处方药审核接口异常："+e.getMessage(),e);
			}
			FunctionUtil.responseIO(JsonUtil.object2json(object), response);
		}
	
		
}
