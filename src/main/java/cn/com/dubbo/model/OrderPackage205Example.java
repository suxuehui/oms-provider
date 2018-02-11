package cn.com.dubbo.model;

import java.util.ArrayList;
import java.util.List;

public class OrderPackage205Example {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public OrderPackage205Example() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andPackageIdIsNull() {
			addCriterion("package_id is null");
			return (Criteria) this;
		}

		public Criteria andPackageIdIsNotNull() {
			addCriterion("package_id is not null");
			return (Criteria) this;
		}

		public Criteria andPackageIdEqualTo(Long value) {
			addCriterion("package_id =", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdNotEqualTo(Long value) {
			addCriterion("package_id <>", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdGreaterThan(Long value) {
			addCriterion("package_id >", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdGreaterThanOrEqualTo(Long value) {
			addCriterion("package_id >=", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdLessThan(Long value) {
			addCriterion("package_id <", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdLessThanOrEqualTo(Long value) {
			addCriterion("package_id <=", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdIn(List<Long> values) {
			addCriterion("package_id in", values, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdNotIn(List<Long> values) {
			addCriterion("package_id not in", values, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdBetween(Long value1, Long value2) {
			addCriterion("package_id between", value1, value2, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdNotBetween(Long value1, Long value2) {
			addCriterion("package_id not between", value1, value2, "packageId");
			return (Criteria) this;
		}

		public Criteria andStockIdIsNull() {
			addCriterion("stock_id is null");
			return (Criteria) this;
		}

		public Criteria andStockIdIsNotNull() {
			addCriterion("stock_id is not null");
			return (Criteria) this;
		}

		public Criteria andStockIdEqualTo(Integer value) {
			addCriterion("stock_id =", value, "stockId");
			return (Criteria) this;
		}

		public Criteria andStockIdNotEqualTo(Integer value) {
			addCriterion("stock_id <>", value, "stockId");
			return (Criteria) this;
		}

		public Criteria andStockIdGreaterThan(Integer value) {
			addCriterion("stock_id >", value, "stockId");
			return (Criteria) this;
		}

		public Criteria andStockIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("stock_id >=", value, "stockId");
			return (Criteria) this;
		}

		public Criteria andStockIdLessThan(Integer value) {
			addCriterion("stock_id <", value, "stockId");
			return (Criteria) this;
		}

		public Criteria andStockIdLessThanOrEqualTo(Integer value) {
			addCriterion("stock_id <=", value, "stockId");
			return (Criteria) this;
		}

		public Criteria andStockIdIn(List<Integer> values) {
			addCriterion("stock_id in", values, "stockId");
			return (Criteria) this;
		}

		public Criteria andStockIdNotIn(List<Integer> values) {
			addCriterion("stock_id not in", values, "stockId");
			return (Criteria) this;
		}

		public Criteria andStockIdBetween(Integer value1, Integer value2) {
			addCriterion("stock_id between", value1, value2, "stockId");
			return (Criteria) this;
		}

		public Criteria andStockIdNotBetween(Integer value1, Integer value2) {
			addCriterion("stock_id not between", value1, value2, "stockId");
			return (Criteria) this;
		}

		public Criteria andOrderNoIsNull() {
			addCriterion("order_no is null");
			return (Criteria) this;
		}

		public Criteria andOrderNoIsNotNull() {
			addCriterion("order_no is not null");
			return (Criteria) this;
		}

		public Criteria andOrderNoEqualTo(String value) {
			addCriterion("order_no =", value, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoNotEqualTo(String value) {
			addCriterion("order_no <>", value, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoGreaterThan(String value) {
			addCriterion("order_no >", value, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
			addCriterion("order_no >=", value, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoLessThan(String value) {
			addCriterion("order_no <", value, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoLessThanOrEqualTo(String value) {
			addCriterion("order_no <=", value, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoLike(String value) {
			addCriterion("order_no like", value, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoNotLike(String value) {
			addCriterion("order_no not like", value, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoIn(List<String> values) {
			addCriterion("order_no in", values, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoNotIn(List<String> values) {
			addCriterion("order_no not in", values, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoBetween(String value1, String value2) {
			addCriterion("order_no between", value1, value2, "orderNo");
			return (Criteria) this;
		}

		public Criteria andOrderNoNotBetween(String value1, String value2) {
			addCriterion("order_no not between", value1, value2, "orderNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoIsNull() {
			addCriterion("package_no is null");
			return (Criteria) this;
		}

		public Criteria andPackageNoIsNotNull() {
			addCriterion("package_no is not null");
			return (Criteria) this;
		}

		public Criteria andPackageNoEqualTo(String value) {
			addCriterion("package_no =", value, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoNotEqualTo(String value) {
			addCriterion("package_no <>", value, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoGreaterThan(String value) {
			addCriterion("package_no >", value, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoGreaterThanOrEqualTo(String value) {
			addCriterion("package_no >=", value, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoLessThan(String value) {
			addCriterion("package_no <", value, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoLessThanOrEqualTo(String value) {
			addCriterion("package_no <=", value, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoLike(String value) {
			addCriterion("package_no like", value, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoNotLike(String value) {
			addCriterion("package_no not like", value, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoIn(List<String> values) {
			addCriterion("package_no in", values, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoNotIn(List<String> values) {
			addCriterion("package_no not in", values, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoBetween(String value1, String value2) {
			addCriterion("package_no between", value1, value2, "packageNo");
			return (Criteria) this;
		}

		public Criteria andPackageNoNotBetween(String value1, String value2) {
			addCriterion("package_no not between", value1, value2, "packageNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdIsNull() {
			addCriterion("logistic_company_id is null");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdIsNotNull() {
			addCriterion("logistic_company_id is not null");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdEqualTo(Integer value) {
			addCriterion("logistic_company_id =", value, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdNotEqualTo(Integer value) {
			addCriterion("logistic_company_id <>", value, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdGreaterThan(Integer value) {
			addCriterion("logistic_company_id >", value, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("logistic_company_id >=", value, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdLessThan(Integer value) {
			addCriterion("logistic_company_id <", value, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdLessThanOrEqualTo(Integer value) {
			addCriterion("logistic_company_id <=", value, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdIn(List<Integer> values) {
			addCriterion("logistic_company_id in", values, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdNotIn(List<Integer> values) {
			addCriterion("logistic_company_id not in", values, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdBetween(Integer value1, Integer value2) {
			addCriterion("logistic_company_id between", value1, value2, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyIdNotBetween(Integer value1, Integer value2) {
			addCriterion("logistic_company_id not between", value1, value2, "logisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameIsNull() {
			addCriterion("logistic_company_name is null");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameIsNotNull() {
			addCriterion("logistic_company_name is not null");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameEqualTo(String value) {
			addCriterion("logistic_company_name =", value, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameNotEqualTo(String value) {
			addCriterion("logistic_company_name <>", value, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameGreaterThan(String value) {
			addCriterion("logistic_company_name >", value, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameGreaterThanOrEqualTo(String value) {
			addCriterion("logistic_company_name >=", value, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameLessThan(String value) {
			addCriterion("logistic_company_name <", value, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameLessThanOrEqualTo(String value) {
			addCriterion("logistic_company_name <=", value, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameLike(String value) {
			addCriterion("logistic_company_name like", value, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameNotLike(String value) {
			addCriterion("logistic_company_name not like", value, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameIn(List<String> values) {
			addCriterion("logistic_company_name in", values, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameNotIn(List<String> values) {
			addCriterion("logistic_company_name not in", values, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameBetween(String value1, String value2) {
			addCriterion("logistic_company_name between", value1, value2, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNameNotBetween(String value1, String value2) {
			addCriterion("logistic_company_name not between", value1, value2, "logisticCompanyName");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoIsNull() {
			addCriterion("logistic_company_no is null");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoIsNotNull() {
			addCriterion("logistic_company_no is not null");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoEqualTo(String value) {
			addCriterion("logistic_company_no =", value, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoNotEqualTo(String value) {
			addCriterion("logistic_company_no <>", value, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoGreaterThan(String value) {
			addCriterion("logistic_company_no >", value, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoGreaterThanOrEqualTo(String value) {
			addCriterion("logistic_company_no >=", value, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoLessThan(String value) {
			addCriterion("logistic_company_no <", value, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoLessThanOrEqualTo(String value) {
			addCriterion("logistic_company_no <=", value, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoLike(String value) {
			addCriterion("logistic_company_no like", value, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoNotLike(String value) {
			addCriterion("logistic_company_no not like", value, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoIn(List<String> values) {
			addCriterion("logistic_company_no in", values, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoNotIn(List<String> values) {
			addCriterion("logistic_company_no not in", values, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoBetween(String value1, String value2) {
			addCriterion("logistic_company_no between", value1, value2, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticCompanyNoNotBetween(String value1, String value2) {
			addCriterion("logistic_company_no not between", value1, value2, "logisticCompanyNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoIsNull() {
			addCriterion("logistic_no is null");
			return (Criteria) this;
		}

		public Criteria andLogisticNoIsNotNull() {
			addCriterion("logistic_no is not null");
			return (Criteria) this;
		}

		public Criteria andLogisticNoEqualTo(String value) {
			addCriterion("logistic_no =", value, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoNotEqualTo(String value) {
			addCriterion("logistic_no <>", value, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoGreaterThan(String value) {
			addCriterion("logistic_no >", value, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoGreaterThanOrEqualTo(String value) {
			addCriterion("logistic_no >=", value, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoLessThan(String value) {
			addCriterion("logistic_no <", value, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoLessThanOrEqualTo(String value) {
			addCriterion("logistic_no <=", value, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoLike(String value) {
			addCriterion("logistic_no like", value, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoNotLike(String value) {
			addCriterion("logistic_no not like", value, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoIn(List<String> values) {
			addCriterion("logistic_no in", values, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoNotIn(List<String> values) {
			addCriterion("logistic_no not in", values, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoBetween(String value1, String value2) {
			addCriterion("logistic_no between", value1, value2, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andLogisticNoNotBetween(String value1, String value2) {
			addCriterion("logistic_no not between", value1, value2, "logisticNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdIsNull() {
			addCriterion("multi_channel_id is null");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdIsNotNull() {
			addCriterion("multi_channel_id is not null");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdEqualTo(Integer value) {
			addCriterion("multi_channel_id =", value, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdNotEqualTo(Integer value) {
			addCriterion("multi_channel_id <>", value, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdGreaterThan(Integer value) {
			addCriterion("multi_channel_id >", value, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("multi_channel_id >=", value, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdLessThan(Integer value) {
			addCriterion("multi_channel_id <", value, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdLessThanOrEqualTo(Integer value) {
			addCriterion("multi_channel_id <=", value, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdIn(List<Integer> values) {
			addCriterion("multi_channel_id in", values, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdNotIn(List<Integer> values) {
			addCriterion("multi_channel_id not in", values, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdBetween(Integer value1, Integer value2) {
			addCriterion("multi_channel_id between", value1, value2, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelIdNotBetween(Integer value1, Integer value2) {
			addCriterion("multi_channel_id not between", value1, value2, "multiChannelId");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusIsNull() {
			addCriterion("delivery_status is null");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusIsNotNull() {
			addCriterion("delivery_status is not null");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusEqualTo(String value) {
			addCriterion("delivery_status =", value, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusNotEqualTo(String value) {
			addCriterion("delivery_status <>", value, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusGreaterThan(String value) {
			addCriterion("delivery_status >", value, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusGreaterThanOrEqualTo(String value) {
			addCriterion("delivery_status >=", value, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusLessThan(String value) {
			addCriterion("delivery_status <", value, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusLessThanOrEqualTo(String value) {
			addCriterion("delivery_status <=", value, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusLike(String value) {
			addCriterion("delivery_status like", value, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusNotLike(String value) {
			addCriterion("delivery_status not like", value, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusIn(List<String> values) {
			addCriterion("delivery_status in", values, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusNotIn(List<String> values) {
			addCriterion("delivery_status not in", values, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusBetween(String value1, String value2) {
			addCriterion("delivery_status between", value1, value2, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andDeliveryStatusNotBetween(String value1, String value2) {
			addCriterion("delivery_status not between", value1, value2, "deliveryStatus");
			return (Criteria) this;
		}

		public Criteria andAddUserIdIsNull() {
			addCriterion("add_user_id is null");
			return (Criteria) this;
		}

		public Criteria andAddUserIdIsNotNull() {
			addCriterion("add_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andAddUserIdEqualTo(Integer value) {
			addCriterion("add_user_id =", value, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddUserIdNotEqualTo(Integer value) {
			addCriterion("add_user_id <>", value, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddUserIdGreaterThan(Integer value) {
			addCriterion("add_user_id >", value, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddUserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("add_user_id >=", value, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddUserIdLessThan(Integer value) {
			addCriterion("add_user_id <", value, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddUserIdLessThanOrEqualTo(Integer value) {
			addCriterion("add_user_id <=", value, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddUserIdIn(List<Integer> values) {
			addCriterion("add_user_id in", values, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddUserIdNotIn(List<Integer> values) {
			addCriterion("add_user_id not in", values, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddUserIdBetween(Integer value1, Integer value2) {
			addCriterion("add_user_id between", value1, value2, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddUserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("add_user_id not between", value1, value2, "addUserId");
			return (Criteria) this;
		}

		public Criteria andAddTimeIsNull() {
			addCriterion("add_time is null");
			return (Criteria) this;
		}

		public Criteria andAddTimeIsNotNull() {
			addCriterion("add_time is not null");
			return (Criteria) this;
		}

		public Criteria andAddTimeEqualTo(String value) {
			addCriterion("add_time =", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotEqualTo(String value) {
			addCriterion("add_time <>", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeGreaterThan(String value) {
			addCriterion("add_time >", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeGreaterThanOrEqualTo(String value) {
			addCriterion("add_time >=", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeLessThan(String value) {
			addCriterion("add_time <", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeLessThanOrEqualTo(String value) {
			addCriterion("add_time <=", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeLike(String value) {
			addCriterion("add_time like", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotLike(String value) {
			addCriterion("add_time not like", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeIn(List<String> values) {
			addCriterion("add_time in", values, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotIn(List<String> values) {
			addCriterion("add_time not in", values, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeBetween(String value1, String value2) {
			addCriterion("add_time between", value1, value2, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotBetween(String value1, String value2) {
			addCriterion("add_time not between", value1, value2, "addTime");
			return (Criteria) this;
		}

		public Criteria andEditUserIdIsNull() {
			addCriterion("edit_user_id is null");
			return (Criteria) this;
		}

		public Criteria andEditUserIdIsNotNull() {
			addCriterion("edit_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andEditUserIdEqualTo(Integer value) {
			addCriterion("edit_user_id =", value, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditUserIdNotEqualTo(Integer value) {
			addCriterion("edit_user_id <>", value, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditUserIdGreaterThan(Integer value) {
			addCriterion("edit_user_id >", value, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditUserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("edit_user_id >=", value, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditUserIdLessThan(Integer value) {
			addCriterion("edit_user_id <", value, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditUserIdLessThanOrEqualTo(Integer value) {
			addCriterion("edit_user_id <=", value, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditUserIdIn(List<Integer> values) {
			addCriterion("edit_user_id in", values, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditUserIdNotIn(List<Integer> values) {
			addCriterion("edit_user_id not in", values, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditUserIdBetween(Integer value1, Integer value2) {
			addCriterion("edit_user_id between", value1, value2, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditUserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("edit_user_id not between", value1, value2, "editUserId");
			return (Criteria) this;
		}

		public Criteria andEditTimeIsNull() {
			addCriterion("edit_time is null");
			return (Criteria) this;
		}

		public Criteria andEditTimeIsNotNull() {
			addCriterion("edit_time is not null");
			return (Criteria) this;
		}

		public Criteria andEditTimeEqualTo(String value) {
			addCriterion("edit_time =", value, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeNotEqualTo(String value) {
			addCriterion("edit_time <>", value, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeGreaterThan(String value) {
			addCriterion("edit_time >", value, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeGreaterThanOrEqualTo(String value) {
			addCriterion("edit_time >=", value, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeLessThan(String value) {
			addCriterion("edit_time <", value, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeLessThanOrEqualTo(String value) {
			addCriterion("edit_time <=", value, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeLike(String value) {
			addCriterion("edit_time like", value, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeNotLike(String value) {
			addCriterion("edit_time not like", value, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeIn(List<String> values) {
			addCriterion("edit_time in", values, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeNotIn(List<String> values) {
			addCriterion("edit_time not in", values, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeBetween(String value1, String value2) {
			addCriterion("edit_time between", value1, value2, "editTime");
			return (Criteria) this;
		}

		public Criteria andEditTimeNotBetween(String value1, String value2) {
			addCriterion("edit_time not between", value1, value2, "editTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeIsNull() {
			addCriterion("delivery_time is null");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeIsNotNull() {
			addCriterion("delivery_time is not null");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeEqualTo(String value) {
			addCriterion("delivery_time =", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeNotEqualTo(String value) {
			addCriterion("delivery_time <>", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeGreaterThan(String value) {
			addCriterion("delivery_time >", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeGreaterThanOrEqualTo(String value) {
			addCriterion("delivery_time >=", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeLessThan(String value) {
			addCriterion("delivery_time <", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeLessThanOrEqualTo(String value) {
			addCriterion("delivery_time <=", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeLike(String value) {
			addCriterion("delivery_time like", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeNotLike(String value) {
			addCriterion("delivery_time not like", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeIn(List<String> values) {
			addCriterion("delivery_time in", values, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeNotIn(List<String> values) {
			addCriterion("delivery_time not in", values, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeBetween(String value1, String value2) {
			addCriterion("delivery_time between", value1, value2, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeNotBetween(String value1, String value2) {
			addCriterion("delivery_time not between", value1, value2, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeIsNull() {
			addCriterion("sync_time is null");
			return (Criteria) this;
		}

		public Criteria andSyncTimeIsNotNull() {
			addCriterion("sync_time is not null");
			return (Criteria) this;
		}

		public Criteria andSyncTimeEqualTo(String value) {
			addCriterion("sync_time =", value, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeNotEqualTo(String value) {
			addCriterion("sync_time <>", value, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeGreaterThan(String value) {
			addCriterion("sync_time >", value, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeGreaterThanOrEqualTo(String value) {
			addCriterion("sync_time >=", value, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeLessThan(String value) {
			addCriterion("sync_time <", value, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeLessThanOrEqualTo(String value) {
			addCriterion("sync_time <=", value, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeLike(String value) {
			addCriterion("sync_time like", value, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeNotLike(String value) {
			addCriterion("sync_time not like", value, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeIn(List<String> values) {
			addCriterion("sync_time in", values, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeNotIn(List<String> values) {
			addCriterion("sync_time not in", values, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeBetween(String value1, String value2) {
			addCriterion("sync_time between", value1, value2, "syncTime");
			return (Criteria) this;
		}

		public Criteria andSyncTimeNotBetween(String value1, String value2) {
			addCriterion("sync_time not between", value1, value2, "syncTime");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}