package cn.com.dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.GoodsMapper;
import cn.com.dubbo.model.GoodsChannelPrice;
import cn.com.dubbo.model.GoodsGroupItemModel;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.GoodsSaleInfoModel;
import cn.com.dubbo.model.GoodsStockInfo;
import cn.com.dubbo.redis.CacheUtil;
import cn.com.dubbo.service.inter.GoodsService;
import cn.com.dubbo.util.StringUtil;


@Service
public class GoodsServiceImpl implements GoodsService{

	@Resource
	private GoodsMapper goodsMapper;
	
	private static final Logger logger = Logger.getLogger(GoodsServiceImpl.class);
	
	@Override
	public GoodsModel findGoodNo(String goodNo){
		try {
			GoodsModel good = CacheUtil.readCache("GoodsModel"+goodNo, new GoodsModel());
			if(good==null||StringUtil.isBlank(good.getGoodsNo())){
				good = goodsMapper.getGoodByGoodNo(goodNo);
				CacheUtil.setTCache("GoodsModel"+goodNo, good, 60*10);
			}
			return good;
		} catch (Exception e) {
			logger.error("查询商品信息错误，商品69码为："+ goodNo +" ,"+e.getMessage(),e);
		}
		return null;
	}
	
	@Override
	public GoodsModel findGood69(String goodNo69){
		try {
			GoodsModel good = CacheUtil.readCache("GoodsModel"+goodNo69, new GoodsModel());
			if(good==null||StringUtil.isBlank(good.getGoodsNo())){
				good = goodsMapper.getGoodByGoodNo69(goodNo69);
				CacheUtil.setTCache("GoodsModel"+goodNo69, good, 60*10);
			}
			return good;
		} catch (Exception e) {
			logger.error("查询商品信息错误，商品69码为："+goodNo69 +" ,"+e.getMessage(),e);
		}
		return null;
	}
	
	@Override
	public List<GoodsGroupItemModel> queryGroupItemByNo69(String goodNo69){
		
		List<GoodsGroupItemModel> groupList = CacheUtil.readCache("GoodsGroupItemModel"+goodNo69, new ArrayList<GoodsGroupItemModel>());
		if(groupList==null||groupList.size()==0){
			groupList = goodsMapper.queryGroupItemByNo69(goodNo69);
			CacheUtil.setTCache("GoodsGroupItemModel"+goodNo69, groupList, 60*10);
		}
		return groupList;
	}
	
	@Override
	public List<GoodsGroupItemModel> queryGroupItemByGoodsNo(String goodsNo){
		
		List<GoodsGroupItemModel> groupList = CacheUtil.readCache("GoodsGroupItemModel"+goodsNo, new ArrayList<GoodsGroupItemModel>());
		if(groupList==null||groupList.size()==0){
			groupList = goodsMapper.queryGroupItemByGoodsNo(goodsNo);
			CacheUtil.setTCache("GoodsGroupItemModel"+goodsNo, groupList, 60*10);
		}
		return groupList;
	}
	
	
	@Override
	public int findGroupNum(String goodNo69){
		
		String nums = CacheUtil.readCache("num"+goodNo69);
		int num = 0;
		if(StringUtil.isBlank(nums)){
			num = goodsMapper.findGroupNum(goodNo69);
			if(num>0){
				CacheUtil.setCache("num"+goodNo69,Integer.toString(num),60*10);
			}
		}else{
			num = Integer.parseInt(nums);
		}
		return num;
	}
	
	@Override
	public GoodsSaleInfoModel findSaleInfoByNo69(String goodNo69){
		try {
			GoodsSaleInfoModel model = CacheUtil.readCache("GoodsSaleInfoModel"+goodNo69, new GoodsSaleInfoModel());
			if(model==null||StringUtil.isBlank(model.getGoodsNo_69())){
				model = goodsMapper.findSaleInfoByNo69(goodNo69);
				CacheUtil.setTCache("GoodsSaleInfoModel"+goodNo69, model, 60*10);
			}
			return model;
		} catch (Exception e) {
			logger.error("更新商品库存失败，商品69码为："+ goodNo69 + " ,错误信息："+e.getMessage(),e);
		}
		return null;
	}
	
	@Override
	public void updateGoodsSaleInfo(GoodsSaleInfoModel saleInfo){
		goodsMapper.updateGoodsSaleInfo(saleInfo);
	}
	
	@Override
	public void updateBatchGoodsSaleInfo(List<GoodsSaleInfoModel> saleInfoList){
		goodsMapper.updateBatchGoodsSaleInfo(saleInfoList);
	}
	
	@Override
	public List<GoodsChannelPrice> queryGoodsByChannel(int multiChannelId){
		List<GoodsChannelPrice> goodsList = goodsMapper.queryGoodsByChannel(multiChannelId);
		return goodsList;
	}
	
	@Override
	public List<GoodsSaleInfoModel> querySaleInfo(int multiChannelId){
		List<GoodsSaleInfoModel> goodsList = goodsMapper.querySaleInfo(multiChannelId);
		return goodsList;
	}

	@Override
	public String findGoodStockNumber(String goodNo) {
		return goodsMapper.findGoodStockNumber(goodNo);
	}
	
	//-----------下面为新增方法---库存备用的方法--------开始-----------
	
	@Override
	public GoodsStockInfo findGoodsStock(String goodsNo) {
		GoodsStockInfo info = null;
		try {
			info = goodsMapper.findGoodsStock(goodsNo);
		} catch (Exception e) {
			logger.error("更新商品库存失败，商品编码为："+ goodsNo + " ,错误信息："+e.getMessage(),e);
		}
		return info;
	}

	@Override
	public void updateGoodsStock(String goodsNo,int usableStock,
			int soldTotalNum,int deliverNum) {
		
		GoodsStockInfo stockInfo = new GoodsStockInfo();
		stockInfo.setGoodsNo(goodsNo);
		if(usableStock!=0){
			stockInfo.setUsableStock(usableStock);
		}
		if(soldTotalNum!=0){
			stockInfo.setSoldTotalNum(soldTotalNum);
		}
		if(deliverNum!=0){
			stockInfo.setDeliverNum(deliverNum);
		}
		goodsMapper.updateGoodsStock(stockInfo);
	}
	
	//-----------下面为新增方法---库存备用的方法--------结束-----------
	
}
