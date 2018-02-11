package cn.com.dubbo.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dubbo.bean.ext.ItemBatchDetail;
import cn.com.dubbo.bean.ext.ItemBatchDetailExample;
import cn.com.dubbo.bean.ext.ItemBatchIndex;
import cn.com.dubbo.bean.ext.ItemBatchIndexExample;
import cn.com.dubbo.mapper.OrderItemModelMapper;
import cn.com.dubbo.mapper.ext.ItemBatchDetailExtMapper;
import cn.com.dubbo.mapper.ext.ItemBatchIndexExtMapper;
import cn.com.dubbo.mapper2.E_orderstateMapper;
import cn.com.dubbo.mapper2.E_salesorderbatchnoExtMapper;
import cn.com.dubbo.model2.E_orderstate;
import cn.com.dubbo.model2.E_orderstateExample;
import cn.com.dubbo.model2.E_salesorderbatchno;
import cn.com.dubbo.model2.E_salesorderbatchnoExt;
import cn.com.dubbo.service.order.E_orderstateService;
import cn.com.dubbo.util.DataQueue;

/**
 * 获取e_orderstate状态为1的订单
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-8-24 上午11:05:48
 */
@Service("e_orderstateService")
public class E_orderstateServiceImpl implements E_orderstateService {
	@Autowired
	E_orderstateMapper e_orderstateMapper;
	@Autowired
	private E_salesorderbatchnoExtMapper e_salesorderbatchnoExtMapper;
	@Autowired
	private ItemBatchIndexExtMapper itemBatchIndexMapper;
	@Autowired
	private ItemBatchDetailExtMapper itemBatchDetailMapper;
	@Autowired
	OrderItemModelMapper orderItemModelMapper;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger logger = Logger.getLogger(E_orderstateServiceImpl.class);
	private DataQueue<E_salesorderbatchnoExt> oderbatchnoQueue = new DataQueue<E_salesorderbatchnoExt>();

	/**
	 * 获取e_orderstate状态为1的订单
	 */
	@Override
	public void get() {
		// MultipleDataSource.setDataSourceKey("DataSource");

		logger.info("进入方法");
		int pageSize = 10;
		E_orderstateExample example = new E_orderstateExample();
		cn.com.dubbo.model2.E_orderstateExample.Criteria criteria = example.createCriteria();
		criteria.andBillstatesEqualTo(0); // 改动**********************************************************
		// criteria.andEOrdernumberEqualTo("JY201609240330194");
		example.setOffset(pageSize);
		example.setOrderByClause("e_ordernumber desc");
		// 查询总数进行分页
		int recordCount = e_orderstateMapper.countByExample(example);// 总数
		System.out.println("总数============：" + recordCount);
		logger.info("总数：" + recordCount);
		int size = recordCount / pageSize;// 总条数/每页显示的条数=总页数
		int mod = recordCount % pageSize;// 最后一页的条数
		if (mod != 0) {
			size++;
		}
		for (int i = 0; i < size; i++) {
			logger.info("进入循环");
			System.out.println("进入循环");
			int startOffset = i * pageSize;// i * pageSize;
			example.setStartoffset(startOffset);
			// 根据分页参数进行订单查询
			List<E_orderstate> list = e_orderstateMapper.selectByExample(example);
			logger.info("分页条数：" + list.size());
			if (list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					logger.info("从药医通中间库同步到oms销库存第 " + i + " 页数据开始.....");
					List<E_salesorderbatchnoExt> batchnoExt = e_salesorderbatchnoExtMapper.selectList(list.get(j));
					List<E_salesorderbatchnoExt> batchnoEorder = e_salesorderbatchnoExtMapper.selectListByeOrdernumber(list.get(j));
					try {
						// 更新item_batch_detail的e_ordernumber字段
						updateDetaile_ordernumber(batchnoEorder);

						// 进一步分页
						if (batchnoExt.size() > 0) {
							if (batchnoExt.size() > 20) {
								int part = batchnoExt.size() / 20;
								int remainder = batchnoExt.size() % 20;
								if (remainder != 0) {
									part++;
								}
								for (int e = 0; e < part; e++) {
									try {
										List<E_salesorderbatchnoExt> exts = null;
										if (e == (part - 1) && remainder != 0) {
											exts = batchnoExt.subList(0, remainder);
											System.out.println(exts.size());
											getData(exts);
											batchnoExt.subList(0, remainder).clear();
										} else {
											exts = batchnoExt.subList(0, 20);
											System.out.println(exts.size());
											getData(exts);
											batchnoExt.subList(0, 20).clear();
											System.out.println("剩余数量++==" + batchnoExt.size());
										}
									} catch (Exception ex) {
										System.out.println("分页错误：：" + "当前时间是：" + sdf.format(new Date()));
									}
								}
							} else {
								getData(batchnoExt);
							}
						}
					} catch (Exception ex) {
						oderbatchnoQueue.enQueue(batchnoExt.get(j));
					}

				}
			}
			// 重新执行错误数据
			if (oderbatchnoQueue.getLength() > 0) {
				List<E_salesorderbatchnoExt> extList = new ArrayList<E_salesorderbatchnoExt>();
				for (int n = 0; n < oderbatchnoQueue.getLength(); n++) {
					E_salesorderbatchnoExt bean = oderbatchnoQueue.deQueue();
					extList.add(bean);
				}
				if (extList.size() > 20) {
					int pageNo = extList.size() / 20;
					int num = extList.size() % 20;
					if (num != 0) {
						pageNo++;
					}
					for (int z = 0; z < pageNo; z++) {
						try {
							List<E_salesorderbatchnoExt> beans = null;
							if (z == (pageNo - 1) && num != 0) {
								beans = extList.subList(0, num);
								getData(beans);
								extList.subList(0, num).clear();
							} else {
								beans = extList.subList(0, 20);
								getData(beans);
								extList.subList(0, 20).clear();
							}
						} catch (Exception ex) {
							System.out.println("oderbatchnoQueue执行错误：：" + "当前时间是：" + sdf.format(new Date()));
						}
					}
				}
			}
		}
		logger.info("从药医通中间库同步到oms销库存---------结束-------------");
	}

	/**
	 * 根据e_ordernumber获取ItemBatchIndex集合然后 更新item_batch_detail的e_ordernumber字段
	 * 
	 * @author 常胜
	 * @date 2016-9-5 下午12:03:22
	 * @param exts
	 * @return void
	 */
	private void updateDetaile_ordernumber(List<E_salesorderbatchnoExt> exts) {
		DataQueue<ItemBatchDetail> detailErrorQueue = new DataQueue<ItemBatchDetail>();
		if (exts.size() > 0) {
			for (int i = 0; i < exts.size(); i++) {
				ItemBatchIndexExample indexExample = new ItemBatchIndexExample();
				cn.com.dubbo.bean.ext.ItemBatchIndexExample.Criteria itemBatchIndexCriteria = indexExample.createCriteria();
				itemBatchIndexCriteria.andEOrdernumberEqualTo(exts.get(i).geteOrdernumber());
				itemBatchIndexCriteria.andGoodsnoEqualTo(exts.get(i).getSerialNumber());
				// 只查询状态为5的
				itemBatchIndexCriteria.andBillstatesEqualTo(5);
				List<ItemBatchIndex> indexs = itemBatchIndexMapper.selectByExample(indexExample);
				// System.out.println("总条数：：" + indexs.size());
				for (int j = 0; j < indexs.size(); j++) {
					ItemBatchDetail record = new ItemBatchDetail();
					try {
						record.seteOrdernumber(indexs.get(j).geteOrdernumber());
						record.setGoodsNo(indexs.get(j).getGoodsno());
						record.setIndexId((indexs.get(j).getBatchItemId()).intValue());
						ItemBatchDetailExample example = new ItemBatchDetailExample();
						// cn.com.dubbo.bean.ext.ItemBatchDetailExample.Criteria deCriteria = example.createCriteria();
						// deCriteria.andIndexIdEqualTo((indexs.get(j).getBatchItemId()).intValue());
						itemBatchDetailMapper.updateByPojo(record);
					} catch (Exception e) {
						detailErrorQueue.enQueue(record);
					}
				}
			}
			if (detailErrorQueue.getLength() > 0) {
				for (int k = 0; k < detailErrorQueue.getLength(); k++) {
					ItemBatchDetail de = detailErrorQueue.deQueue();
					itemBatchDetailMapper.updateByPojo(de);
				}
			}
		}

	}

	/**
	 * 获取需要更新的数据
	 * 
	 * @author 常胜
	 * @date 2016-8-24 下午1:51:21
	 * @param batchnoExt
	 * @return void
	 * @throws Exception
	 */
	public void getData(List<E_salesorderbatchnoExt> batchnoExt) throws Exception {
		// MultipleDataSource.setDataSourceKey("DataSource2");
		System.out.println("获取数据" + batchnoExt.size());
		List<E_salesorderbatchnoExt> batchnoExt2 = new ArrayList<E_salesorderbatchnoExt>();
		List<ItemBatchIndex> indexs = new ArrayList<ItemBatchIndex>();
		List<ItemBatchDetail> details = new ArrayList<ItemBatchDetail>();
		List<ItemBatchDetail> itemBatchDetail = new ArrayList<ItemBatchDetail>();
		for (E_salesorderbatchnoExt ext : batchnoExt) {
			ItemBatchIndexExample indexExample = new ItemBatchIndexExample();
			cn.com.dubbo.bean.ext.ItemBatchIndexExample.Criteria itemBatchIndexCriteria = indexExample.createCriteria();
			itemBatchIndexCriteria.andEOrdernumberEqualTo(ext.geteOrdernumber());
			itemBatchIndexCriteria.andGoodsnoEqualTo(ext.getSerialNumber());
			// 只查询状态为5的
			itemBatchIndexCriteria.andBillstatesEqualTo(5);
			List<ItemBatchIndex> itemBatchIndex = itemBatchIndexMapper.selectByExample(indexExample);
			System.out.println("未更新数量==" + itemBatchIndex.size());
			if (itemBatchIndex.size() > 0) {
				// 给ItemBatchIndex赋值
				if (ext.getCount() == 1) {
					ItemBatchIndex index = new ItemBatchIndex();
					index.seteOrdernumber(ext.geteOrdernumber());
					if (ext.getQuantity() > 0) {
						index.setAmount(Integer.valueOf(ext.getQuantity() + ""));
					}
					if (StringUtils.isNotBlank(ext.getBarcode())) {
						index.setGoods69(ext.getBarcode());
					}
					if (StringUtils.isNotBlank(ext.getSerialNumber())) {
						index.setGoodsno(ext.getSerialNumber());
					}
					indexs.add(index);
					// 给ItemBatchDetail赋值
					ItemBatchDetail detail = new ItemBatchDetail();
					detail.seteOrdernumber(ext.geteOrdernumber());
					if (StringUtils.isNotBlank(ext.getBarcode())) {
						detail.setGoodsNo69(ext.getBarcode());
					}
					if (StringUtils.isNotBlank(ext.getSerialNumber())) {
						detail.setGoodsNo(ext.getSerialNumber());
					}
					if (StringUtils.isNotBlank(ext.getBatchno())) {
						detail.setGoodsBatch(ext.getBatchno());
					} else {
						detail.setGoodsBatch("");
					}

					detail.setAddUserId(149);
					detail.setStockStatus("Y");

					details.add(detail);
					// 查询ItemBatchDetail的order_item_id
					ItemBatchDetailExample detailExample = new ItemBatchDetailExample();
					cn.com.dubbo.bean.ext.ItemBatchDetailExample.Criteria detailCriteria = detailExample.createCriteria();
					detailCriteria.andEOrdernumberEqualTo(ext.geteOrdernumber());
					detailCriteria.andGoodsNoEqualTo(ext.getSerialNumber());
					List<ItemBatchDetail> itemBatchList = itemBatchDetailMapper.selectByExample(detailExample);
					if (itemBatchList.size() > 0) {
						itemBatchDetail.addAll(itemBatchList);
					}
				} else {
					batchnoExt2.add(ext);
				}
			}
		}
		// 更新count=1的批次
		if (indexs.size() > 0 && details.size() > 0 && itemBatchDetail.size() > 0) {
			update(indexs, details, itemBatchDetail);
		}
		// 更新count>1的批次
		if (batchnoExt2.size() > 0) {
			update2(batchnoExt2);
		}
	}

	/**
	 * 批量更新count<1的批次
	 * 
	 * @author 常胜
	 * @date 2016-8-24 下午2:57:42
	 * @param indexs
	 * @param details
	 * @param itemBatchDetail
	 * @return void
	 */
	public void update(List<ItemBatchIndex> indexs, List<ItemBatchDetail> details, List<ItemBatchDetail> itemBatchDetail) throws Exception {
		// /MultipleDataSource.setDataSourceKey("DataSource2");
		System.out.println("执行更新");

		try {
			if (indexs.size() > 0) {
				itemBatchIndexMapper.updateList(indexs);
			}

		} catch (Exception e) {
			for (ItemBatchIndex itemBatchIndex : indexs) {
				itemBatchIndexMapper.updateOne(itemBatchIndex);
			}
		}
		try {
			if (details.size() > 0) {
				itemBatchDetailMapper.updateList(details);
			}

		} catch (Exception e) {
			for (ItemBatchDetail batchDetail : details) {
				itemBatchDetailMapper.updateOne(batchDetail);
			}
			// e.printStackTrace();
		}
		try {
			if (itemBatchDetail.size() > 0) {
				orderItemModelMapper.updateList(itemBatchDetail);
			}
		} catch (Exception e) {
			for (ItemBatchDetail detail : itemBatchDetail) {
				orderItemModelMapper.updateOne(detail);
			}
		}
	}

	/**
	 * 批量更新count>1的批次
	 * 
	 * @author 常胜
	 * @date 2016-8-25 下午9:09:06
	 * @param batchnoExt2
	 * @return void
	 */
	private void update2(List<E_salesorderbatchnoExt> batchnoExt2) throws Exception {
		// 新的业务逻辑
		// 更新item_batch_index表
		List<ItemBatchIndex> indexs = new ArrayList<ItemBatchIndex>();
		for (E_salesorderbatchnoExt e_salesorderbatchnoExt : batchnoExt2) {
			try {
				ItemBatchIndex index = new ItemBatchIndex();
				index.seteOrdernumber(e_salesorderbatchnoExt.geteOrdernumber());
				if (e_salesorderbatchnoExt.getQuantity() > 0) {
					index.setAmount(Integer.valueOf(e_salesorderbatchnoExt.getQuantity() + ""));
				}
				if (StringUtils.isNotBlank(e_salesorderbatchnoExt.getBarcode())) {
					index.setGoods69(e_salesorderbatchnoExt.getBarcode());
				}
				if (StringUtils.isNotBlank(e_salesorderbatchnoExt.getSerialNumber())) {
					index.setGoodsno(e_salesorderbatchnoExt.getSerialNumber());
				}
				indexs.add(index);
			} catch (Exception e) {
				System.out.println(" 更新item_batch_index表赋值错误");
			}
			if (indexs.size() > 0) {
				// MultipleDataSource.setDataSourceKey("DataSource2");
				try {
					itemBatchIndexMapper.updateList(indexs);
				} catch (Exception ex) {
					for (ItemBatchIndex itemBatchIndex : indexs) {
						itemBatchIndexMapper.updateOne(itemBatchIndex);
					}
				}
			}

			// 更新item_batch_detail和order_item表
			for (int i = 0; i < batchnoExt2.size(); i++) {
				E_salesorderbatchno e_salesorderbatchno = new E_salesorderbatchno();
				e_salesorderbatchno.seteOrdernumber(batchnoExt2.get(i).geteOrdernumber());
				e_salesorderbatchno.setpId(batchnoExt2.get(i).getpId());
				// MultipleDataSource.setDataSourceKey("DataSource");
				List<ItemBatchDetail> itemBatchList = new ArrayList<ItemBatchDetail>();
				List<E_salesorderbatchnoExt> list = e_salesorderbatchnoExtMapper.selectby(e_salesorderbatchno);
				for (int j = 0; j < list.size(); j++) {
					Long quantity = list.get(j).getQuantity();
					ItemBatchDetailExample detailExample = new ItemBatchDetailExample();
					cn.com.dubbo.bean.ext.ItemBatchDetailExample.Criteria detailCriteria = detailExample.createCriteria();
					detailCriteria.andEOrdernumberEqualTo(list.get(j).geteOrdernumber());
					detailCriteria.andGoodsNoEqualTo(list.get(j).getSerialNumber());
					detailCriteria.andStockStatusEqualTo("N");
					// MultipleDataSource.setDataSourceKey("DataSource2");
					itemBatchList = itemBatchDetailMapper.selectByExample(detailExample);
					for (int t = 0; t < itemBatchList.size(); t++) {
						if (quantity > 0) {
							long a = itemBatchList.get(t).getAmount();
							if (a <= quantity) {
								System.out.println(">>>>>>>>>>>>" + a);
								ItemBatchDetail ItemBatchDetail = new ItemBatchDetail();
								// set主键
								ItemBatchDetail.setOrderDataId(itemBatchList.get(t).getOrderDataId());
								// set要更新的字段
								ItemBatchDetail.setStockStatus("Y");
								ItemBatchDetail.setGoodsBatch(list.get(j).getBatchno());
								itemBatchDetailMapper.updateOrder(ItemBatchDetail);
								orderItemModelMapper.updateItem(itemBatchList.get(t));
								quantity = quantity - a;
							}

						}

					}
				}
				if (list.size() != itemBatchList.size() && itemBatchList.size() > 0) {
					for (int j = 0; j < itemBatchList.size(); j++) {
						StringBuilder sb = new StringBuilder();
						for (int j2 = 0; j2 < list.size(); j2++) {
							sb.append(list.get(j2).getBatchno());
							sb.append(",");
						}
						String str = sb.substring(0, sb.length() - 1);
						ItemBatchDetail ItemBatchDetail = new ItemBatchDetail();
						// set主键
						ItemBatchDetail.setOrderDataId(itemBatchList.get(j).getOrderDataId());
						// set要更新的字段
						ItemBatchDetail.setStockStatus("Y");
						ItemBatchDetail.setGoodsBatch(str);
						itemBatchDetailMapper.updateOrder(ItemBatchDetail);
						orderItemModelMapper.updateItem(itemBatchList.get(j));
					}
				}
			}
		}
	}

	/**
	 * 数字验证
	 * 
	 * @author 常胜
	 * @date 2016-7-29 下午6:57:35
	 * @param str
	 * @return
	 * @return boolean
	 */
	public static boolean isInteger(String str) {
		if (StringUtils.isNotBlank(str)) {
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
			return pattern.matcher(str).matches();
		}
		return false;
	}
}
