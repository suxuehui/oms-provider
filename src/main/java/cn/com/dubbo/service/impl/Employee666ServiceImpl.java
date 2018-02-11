package cn.com.dubbo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.Employee666Mapper;
import cn.com.dubbo.mapper.ExceptionInfoMapper;
import cn.com.dubbo.mapper.GoodsChannelPriceMapper;
import cn.com.dubbo.mapper.GoodsMapper;
import cn.com.dubbo.model.Employee;
import cn.com.dubbo.model.ExceptionInfo;
import cn.com.dubbo.model.GoodsChannelPrice;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.service.inter.Employee666Service;


@Service("employee666Service")
//@Transactional
public class Employee666ServiceImpl implements Employee666Service{

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final Logger logger = Logger.getLogger(Employee666ServiceImpl.class);
	
	@Autowired
	private Employee666Mapper employee666Mapper;
	
	@Autowired
	private ExceptionInfoMapper exceptionInfoMapper;
	
	@Autowired
	private GoodsChannelPriceMapper goodsChannelPriceMapper;
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public Employee login(Employee emp) {
		return this.employee666Mapper.login666(emp);
	}
	
	@Override
	public void saveEmployee666List(List<Employee> employeeList) {
		Employee temEmployee=null;
		int j=0,i=0;
		try {
//			this.employee666Mapper.insertSelective666(employee1);
			for (i = 0; i < employeeList.size(); i++) {
				temEmployee=employeeList.get(i);
				j=i;
				this.employee666Mapper.insertSelective666(temEmployee);
				employeeList.remove(j);
				i=0;
			}
		} catch (Exception e) {
			logger.error(temEmployee+"保存时出错"+" ,错误信息："+e.getMessage(),e);
			//保存记录到数据库
			Integer	orderNo=employeeList.get(j).getEmpid();
	        JSONObject orderModel_JSONObject = JSONObject.fromObject(employeeList.get(j));
	        
	        ExceptionInfo exceptionInfo=new ExceptionInfo();
	        exceptionInfo.setOrder_no(orderNo.toString());
//	        exceptionInfo.setOrder_item_id(110);
//	        exceptionInfo.setContent(orderModel_JSONObject.toString());
//	        exceptionInfo.setExceptionInfos(e.getMessage());

//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        exceptionInfo.setAdd_time(sdf.format(new Date()));
	        exceptionInfoMapper.insertExceptionInfo(exceptionInfo);
	        employeeList.remove(j);
	        i=0;
		}finally{
			if (employeeList.size()==0) {
				System.out.println("订单批量保存:执行完毕") ;
			} else {
				saveEmployee666List(employeeList);
			}
       }	
   }
	
	
	//作为一个通用的方法：插入集合时，遇到异常记录保存下来，同时保证剩余记录的顺利插入
	@Override
	public <T> void saveList(List<T> employeeList){
		T temEmployee=null;
		int j=0,i=0;
		try {
			for (i = 0; i < employeeList.size(); i++) {
				temEmployee=employeeList.get(i);
				j=i;
				this.employee666Mapper.insertSelective666((Employee) temEmployee);//DAO层保存记录的方法
				employeeList.remove(j);
				i=0;
			}
		} catch (Exception e) {
			logger.error(temEmployee+"保存时出错"+" ,错误信息："+e.getMessage(),e);
			//保存记录到数据库
//			Integer	orderNo=employeeList.get(j).getEmpid();
	        JSONObject orderModel_JSONObject = JSONObject.fromObject(employeeList.get(j));
	        
	        ExceptionInfo exceptionInfo=new ExceptionInfo();
//	        exceptionInfo.setOrder_no(orderNo.toString());
//	        exceptionInfo.setOrder_item_id(110);
//	        exceptionInfo.setContent(orderModel_JSONObject.toString());
//	        exceptionInfo.setExceptionInfos(e.getMessage());

//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        exceptionInfo.setAdd_time(sdf.format(new Date()));
	        exceptionInfoMapper.insertExceptionInfo(exceptionInfo);
	        employeeList.remove(j);
	        i=0;
		}finally{
			if (employeeList.size()==0) {
				System.out.println("订单批量保存:执行完毕") ;
			} else {
				saveList(employeeList);
			}
       }	
   }
	
	@Override
	public void updateGoodsChannelPriceList(int count){
		int size_num=0;
		GoodsModel goodsModel=null;
		GoodsChannelPrice goodsChannelPriceTemp=null;
		try {
			List<GoodsChannelPrice> goodsChannelPriceList=goodsChannelPriceMapper.queryGoodsByChannel(4);
			size_num=goodsChannelPriceList.size();
			for (int k = count; k < goodsChannelPriceList.size(); k++) {
				goodsChannelPriceTemp=goodsChannelPriceList.get(k);
				
//				if (goodsChannelPriceTemp.getGoodsNo_69().equals("6904579106429") || goodsChannelPriceTemp.getGoodsNo_69().equals("6935691800117")
//						|| goodsChannelPriceTemp.getGoodsNo_69().equals("362211166600") || goodsChannelPriceTemp.getGoodsNo_69().equals("6954967600800")
//						|| goodsChannelPriceTemp.getGoodsNo_69().equals("6954967600718") || goodsChannelPriceTemp.getGoodsNo_69().equals("6954967600909")
//						|| goodsChannelPriceTemp.getGoodsNo_69().equals("6954967600909") || goodsChannelPriceTemp.getGoodsNo_69().equals("6954967600718")
//						
//						|| goodsChannelPriceTemp.getGoodsNo_69().equals("699073711922") || goodsChannelPriceTemp.getGoodsNo_69().equals("6940863603304")
//						|| goodsChannelPriceTemp.getGoodsNo_69().equals("6901294179608") || goodsChannelPriceTemp.getGoodsNo_69().equals("6909717000027")						
//						) {//
//					continue;
//				}
				
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  6904579106429
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  6935691800117
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  362211166600
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  6954967600800
//				
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  6954967600718
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  6954967600909
//				
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  6954967600909
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  6954967600718
//				
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  699073711922
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  6940863603304
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：  6901294179608
//				[cn.com.dubbo.service.impl.Employee666ServiceImpl] - updateGoodsChannelPriceList,错误信息5555：    6909717000027
				
				count++;
				List<GoodsModel> goodsModelList= goodsMapper.getGoodListByGoodNo69(goodsChannelPriceTemp.getGoodsNo_69());
				if (goodsModelList.size()>0) {
					goodsModel=goodsModelList.get(0);
					
					GoodsChannelPrice goodsChannelPrice=new GoodsChannelPrice();
					goodsChannelPrice.setGcpId(goodsChannelPriceTemp.getGcpId());
					goodsChannelPrice.setGoodsId(goodsModel.getGoodsId());
					goodsChannelPrice.setGoodsNo(goodsModel.getGoodsNo());
					goodsChannelPrice.setAddTime(sdf.format(new Date()));
					goodsChannelPriceMapper.updateGoodsChannelPrice(goodsChannelPrice);
				}
				
//				if (goodsModel!=null) {
//					GoodsChannelPrice goodsChannelPrice=new GoodsChannelPrice();
//					goodsChannelPrice.setGcpId(goodsChannelPriceTemp.getGcpId());
//					goodsChannelPrice.setGoodsId(goodsModel.getGoodsId());
//					goodsChannelPrice.setGoodsNo(goodsModel.getGoodsNo());
//					goodsChannelPrice.setAddTime(sdf.format(new Date()));
//					goodsChannelPriceMapper.updateGoodsChannelPrice(goodsChannelPrice);
//					
//				}
			}
		} catch (Exception e) {
//			logger.error("updateGoodsChannelPriceList,错误信息："+e.getMessage(),e);
			logger.error("updateGoodsChannelPriceList,错误信息5555：  "+goodsChannelPriceTemp.getGoodsNo_69());
//			throw new RuntimeException("抛着玩的。") ; // 人为抛出
		}finally{
			if (count<size_num) {
				updateGoodsChannelPriceList(count);
			} else {
				System.out.println("updateGoodsChannelPriceList:该次循环执行完毕") ;
			}
       }
		System.out.println("updateGoodsChannelPriceList:执行完毕") ;
   }
}
