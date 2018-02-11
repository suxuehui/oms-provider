package cn.com.dubbo.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderInfoModelExample {
	protected String orderByClause;
	protected int offset;
	protected int startoffset;
	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public OrderInfoModelExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getStartoffset() {
		return startoffset;
	}

	public void setStartoffset(int startoffset) {
		this.startoffset = startoffset;
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

		public Criteria andLogLogisticCompanyIdIsNull() {
			addCriterion("log_logistic_company_id is null");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdIsNotNull() {
			addCriterion("log_logistic_company_id is not null");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdEqualTo(Integer value) {
			addCriterion("log_logistic_company_id =", value, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdNotEqualTo(Integer value) {
			addCriterion("log_logistic_company_id <>", value, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdGreaterThan(Integer value) {
			addCriterion("log_logistic_company_id >", value, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("log_logistic_company_id >=", value, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdLessThan(Integer value) {
			addCriterion("log_logistic_company_id <", value, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdLessThanOrEqualTo(Integer value) {
			addCriterion("log_logistic_company_id <=", value, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdIn(List<Integer> values) {
			addCriterion("log_logistic_company_id in", values, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdNotIn(List<Integer> values) {
			addCriterion("log_logistic_company_id not in", values, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdBetween(Integer value1, Integer value2) {
			addCriterion("log_logistic_company_id between", value1, value2, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andLogLogisticCompanyIdNotBetween(Integer value1, Integer value2) {
			addCriterion("log_logistic_company_id not between", value1, value2, "logLogisticCompanyId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdIsNull() {
			addCriterion("multi_channel_order_batch_id is null");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdIsNotNull() {
			addCriterion("multi_channel_order_batch_id is not null");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdEqualTo(Long value) {
			addCriterion("multi_channel_order_batch_id =", value, "multiChannelOrderBatchId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdNotEqualTo(Long value) {
			addCriterion("multi_channel_order_batch_id <>", value, "multiChannelOrderBatchId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdGreaterThan(Long value) {
			addCriterion("multi_channel_order_batch_id >", value, "multiChannelOrderBatchId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdGreaterThanOrEqualTo(Long value) {
			addCriterion("multi_channel_order_batch_id >=", value, "multiChannelOrderBatchId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdLessThan(Long value) {
			addCriterion("multi_channel_order_batch_id <", value, "multiChannelOrderBatchId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdLessThanOrEqualTo(Long value) {
			addCriterion("multi_channel_order_batch_id <=", value, "multiChannelOrderBatchId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdIn(List<Long> values) {
			addCriterion("multi_channel_order_batch_id in", values, "multiChannelOrderBatchId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdNotIn(List<Long> values) {
			addCriterion("multi_channel_order_batch_id not in", values, "multiChannelOrderBatchId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdBetween(Long value1, Long value2) {
			addCriterion("multi_channel_order_batch_id between", value1, value2, "multiChannelOrderBatchId");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderBatchIdNotBetween(Long value1, Long value2) {
			addCriterion("multi_channel_order_batch_id not between", value1, value2, "multiChannelOrderBatchId");
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

		public Criteria andFromMediaIsNull() {
			addCriterion("from_media is null");
			return (Criteria) this;
		}

		public Criteria andFromMediaIsNotNull() {
			addCriterion("from_media is not null");
			return (Criteria) this;
		}

		public Criteria andFromMediaEqualTo(String value) {
			addCriterion("from_media =", value, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaNotEqualTo(String value) {
			addCriterion("from_media <>", value, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaGreaterThan(String value) {
			addCriterion("from_media >", value, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaGreaterThanOrEqualTo(String value) {
			addCriterion("from_media >=", value, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaLessThan(String value) {
			addCriterion("from_media <", value, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaLessThanOrEqualTo(String value) {
			addCriterion("from_media <=", value, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaLike(String value) {
			addCriterion("from_media like", value, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaNotLike(String value) {
			addCriterion("from_media not like", value, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaIn(List<String> values) {
			addCriterion("from_media in", values, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaNotIn(List<String> values) {
			addCriterion("from_media not in", values, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaBetween(String value1, String value2) {
			addCriterion("from_media between", value1, value2, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andFromMediaNotBetween(String value1, String value2) {
			addCriterion("from_media not between", value1, value2, "fromMedia");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoIsNull() {
			addCriterion("multi_channel_order_no is null");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoIsNotNull() {
			addCriterion("multi_channel_order_no is not null");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoEqualTo(String value) {
			addCriterion("multi_channel_order_no =", value, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoNotEqualTo(String value) {
			addCriterion("multi_channel_order_no <>", value, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoGreaterThan(String value) {
			addCriterion("multi_channel_order_no >", value, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoGreaterThanOrEqualTo(String value) {
			addCriterion("multi_channel_order_no >=", value, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoLessThan(String value) {
			addCriterion("multi_channel_order_no <", value, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoLessThanOrEqualTo(String value) {
			addCriterion("multi_channel_order_no <=", value, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoLike(String value) {
			addCriterion("multi_channel_order_no like", value, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoNotLike(String value) {
			addCriterion("multi_channel_order_no not like", value, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoIn(List<String> values) {
			addCriterion("multi_channel_order_no in", values, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoNotIn(List<String> values) {
			addCriterion("multi_channel_order_no not in", values, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoBetween(String value1, String value2) {
			addCriterion("multi_channel_order_no between", value1, value2, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andMultiChannelOrderNoNotBetween(String value1, String value2) {
			addCriterion("multi_channel_order_no not between", value1, value2, "multiChannelOrderNo");
			return (Criteria) this;
		}

		public Criteria andOrderTypeIsNull() {
			addCriterion("order_type is null");
			return (Criteria) this;
		}

		public Criteria andOrderTypeIsNotNull() {
			addCriterion("order_type is not null");
			return (Criteria) this;
		}

		public Criteria andOrderTypeEqualTo(Integer value) {
			addCriterion("order_type =", value, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderTypeNotEqualTo(Integer value) {
			addCriterion("order_type <>", value, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderTypeGreaterThan(Integer value) {
			addCriterion("order_type >", value, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("order_type >=", value, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderTypeLessThan(Integer value) {
			addCriterion("order_type <", value, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderTypeLessThanOrEqualTo(Integer value) {
			addCriterion("order_type <=", value, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderTypeIn(List<Integer> values) {
			addCriterion("order_type in", values, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderTypeNotIn(List<Integer> values) {
			addCriterion("order_type not in", values, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderTypeBetween(Integer value1, Integer value2) {
			addCriterion("order_type between", value1, value2, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("order_type not between", value1, value2, "orderType");
			return (Criteria) this;
		}

		public Criteria andOrderStatusIsNull() {
			addCriterion("order_status is null");
			return (Criteria) this;
		}

		public Criteria andOrderStatusIsNotNull() {
			addCriterion("order_status is not null");
			return (Criteria) this;
		}

		public Criteria andOrderStatusEqualTo(Integer value) {
			addCriterion("order_status =", value, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andOrderStatusNotEqualTo(Integer value) {
			addCriterion("order_status <>", value, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andOrderStatusGreaterThan(Integer value) {
			addCriterion("order_status >", value, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("order_status >=", value, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andOrderStatusLessThan(Integer value) {
			addCriterion("order_status <", value, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
			addCriterion("order_status <=", value, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andOrderStatusIn(List<Integer> values) {
			addCriterion("order_status in", values, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andOrderStatusNotIn(List<Integer> values) {
			addCriterion("order_status not in", values, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
			addCriterion("order_status between", value1, value2, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("order_status not between", value1, value2, "orderStatus");
			return (Criteria) this;
		}

		public Criteria andValidIsNull() {
			addCriterion("valid is null");
			return (Criteria) this;
		}

		public Criteria andValidIsNotNull() {
			addCriterion("valid is not null");
			return (Criteria) this;
		}

		public Criteria andValidEqualTo(String value) {
			addCriterion("valid =", value, "valid");
			return (Criteria) this;
		}

		public Criteria andValidNotEqualTo(String value) {
			addCriterion("valid <>", value, "valid");
			return (Criteria) this;
		}

		public Criteria andValidGreaterThan(String value) {
			addCriterion("valid >", value, "valid");
			return (Criteria) this;
		}

		public Criteria andValidGreaterThanOrEqualTo(String value) {
			addCriterion("valid >=", value, "valid");
			return (Criteria) this;
		}

		public Criteria andValidLessThan(String value) {
			addCriterion("valid <", value, "valid");
			return (Criteria) this;
		}

		public Criteria andValidLessThanOrEqualTo(String value) {
			addCriterion("valid <=", value, "valid");
			return (Criteria) this;
		}

		public Criteria andValidLike(String value) {
			addCriterion("valid like", value, "valid");
			return (Criteria) this;
		}

		public Criteria andValidNotLike(String value) {
			addCriterion("valid not like", value, "valid");
			return (Criteria) this;
		}

		public Criteria andValidIn(List<String> values) {
			addCriterion("valid in", values, "valid");
			return (Criteria) this;
		}

		public Criteria andValidNotIn(List<String> values) {
			addCriterion("valid not in", values, "valid");
			return (Criteria) this;
		}

		public Criteria andValidBetween(String value1, String value2) {
			addCriterion("valid between", value1, value2, "valid");
			return (Criteria) this;
		}

		public Criteria andValidNotBetween(String value1, String value2) {
			addCriterion("valid not between", value1, value2, "valid");
			return (Criteria) this;
		}

		public Criteria andMemberIdIsNull() {
			addCriterion("member_id is null");
			return (Criteria) this;
		}

		public Criteria andMemberIdIsNotNull() {
			addCriterion("member_id is not null");
			return (Criteria) this;
		}

		public Criteria andMemberIdEqualTo(String value) {
			addCriterion("member_id =", value, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdNotEqualTo(String value) {
			addCriterion("member_id <>", value, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdGreaterThan(String value) {
			addCriterion("member_id >", value, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
			addCriterion("member_id >=", value, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdLessThan(String value) {
			addCriterion("member_id <", value, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdLessThanOrEqualTo(String value) {
			addCriterion("member_id <=", value, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdLike(String value) {
			addCriterion("member_id like", value, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdNotLike(String value) {
			addCriterion("member_id not like", value, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdIn(List<String> values) {
			addCriterion("member_id in", values, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdNotIn(List<String> values) {
			addCriterion("member_id not in", values, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdBetween(String value1, String value2) {
			addCriterion("member_id between", value1, value2, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberIdNotBetween(String value1, String value2) {
			addCriterion("member_id not between", value1, value2, "memberId");
			return (Criteria) this;
		}

		public Criteria andMemberNickIsNull() {
			addCriterion("member_nick is null");
			return (Criteria) this;
		}

		public Criteria andMemberNickIsNotNull() {
			addCriterion("member_nick is not null");
			return (Criteria) this;
		}

		public Criteria andMemberNickEqualTo(String value) {
			addCriterion("member_nick =", value, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickNotEqualTo(String value) {
			addCriterion("member_nick <>", value, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickGreaterThan(String value) {
			addCriterion("member_nick >", value, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickGreaterThanOrEqualTo(String value) {
			addCriterion("member_nick >=", value, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickLessThan(String value) {
			addCriterion("member_nick <", value, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickLessThanOrEqualTo(String value) {
			addCriterion("member_nick <=", value, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickLike(String value) {
			addCriterion("member_nick like", value, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickNotLike(String value) {
			addCriterion("member_nick not like", value, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickIn(List<String> values) {
			addCriterion("member_nick in", values, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickNotIn(List<String> values) {
			addCriterion("member_nick not in", values, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickBetween(String value1, String value2) {
			addCriterion("member_nick between", value1, value2, "memberNick");
			return (Criteria) this;
		}

		public Criteria andMemberNickNotBetween(String value1, String value2) {
			addCriterion("member_nick not between", value1, value2, "memberNick");
			return (Criteria) this;
		}

		public Criteria andIsPayIsNull() {
			addCriterion("is_pay is null");
			return (Criteria) this;
		}

		public Criteria andIsPayIsNotNull() {
			addCriterion("is_pay is not null");
			return (Criteria) this;
		}

		public Criteria andIsPayEqualTo(String value) {
			addCriterion("is_pay =", value, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayNotEqualTo(String value) {
			addCriterion("is_pay <>", value, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayGreaterThan(String value) {
			addCriterion("is_pay >", value, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayGreaterThanOrEqualTo(String value) {
			addCriterion("is_pay >=", value, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayLessThan(String value) {
			addCriterion("is_pay <", value, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayLessThanOrEqualTo(String value) {
			addCriterion("is_pay <=", value, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayLike(String value) {
			addCriterion("is_pay like", value, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayNotLike(String value) {
			addCriterion("is_pay not like", value, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayIn(List<String> values) {
			addCriterion("is_pay in", values, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayNotIn(List<String> values) {
			addCriterion("is_pay not in", values, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayBetween(String value1, String value2) {
			addCriterion("is_pay between", value1, value2, "isPay");
			return (Criteria) this;
		}

		public Criteria andIsPayNotBetween(String value1, String value2) {
			addCriterion("is_pay not between", value1, value2, "isPay");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeIsNull() {
			addCriterion("payment_type is null");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeIsNotNull() {
			addCriterion("payment_type is not null");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeEqualTo(String value) {
			addCriterion("payment_type =", value, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeNotEqualTo(String value) {
			addCriterion("payment_type <>", value, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeGreaterThan(String value) {
			addCriterion("payment_type >", value, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeGreaterThanOrEqualTo(String value) {
			addCriterion("payment_type >=", value, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeLessThan(String value) {
			addCriterion("payment_type <", value, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeLessThanOrEqualTo(String value) {
			addCriterion("payment_type <=", value, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeLike(String value) {
			addCriterion("payment_type like", value, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeNotLike(String value) {
			addCriterion("payment_type not like", value, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeIn(List<String> values) {
			addCriterion("payment_type in", values, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeNotIn(List<String> values) {
			addCriterion("payment_type not in", values, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeBetween(String value1, String value2) {
			addCriterion("payment_type between", value1, value2, "paymentType");
			return (Criteria) this;
		}

		public Criteria andPaymentTypeNotBetween(String value1, String value2) {
			addCriterion("payment_type not between", value1, value2, "paymentType");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldIsNull() {
			addCriterion("delivery_fee_old is null");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldIsNotNull() {
			addCriterion("delivery_fee_old is not null");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldEqualTo(BigDecimal value) {
			addCriterion("delivery_fee_old =", value, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldNotEqualTo(BigDecimal value) {
			addCriterion("delivery_fee_old <>", value, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldGreaterThan(BigDecimal value) {
			addCriterion("delivery_fee_old >", value, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("delivery_fee_old >=", value, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldLessThan(BigDecimal value) {
			addCriterion("delivery_fee_old <", value, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldLessThanOrEqualTo(BigDecimal value) {
			addCriterion("delivery_fee_old <=", value, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldIn(List<BigDecimal> values) {
			addCriterion("delivery_fee_old in", values, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldNotIn(List<BigDecimal> values) {
			addCriterion("delivery_fee_old not in", values, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("delivery_fee_old between", value1, value2, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeOldNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("delivery_fee_old not between", value1, value2, "deliveryFeeOld");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeIsNull() {
			addCriterion("delivery_fee is null");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeIsNotNull() {
			addCriterion("delivery_fee is not null");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeEqualTo(BigDecimal value) {
			addCriterion("delivery_fee =", value, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeNotEqualTo(BigDecimal value) {
			addCriterion("delivery_fee <>", value, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeGreaterThan(BigDecimal value) {
			addCriterion("delivery_fee >", value, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("delivery_fee >=", value, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeLessThan(BigDecimal value) {
			addCriterion("delivery_fee <", value, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("delivery_fee <=", value, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeIn(List<BigDecimal> values) {
			addCriterion("delivery_fee in", values, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeNotIn(List<BigDecimal> values) {
			addCriterion("delivery_fee not in", values, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("delivery_fee between", value1, value2, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andDeliveryFeeNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("delivery_fee not between", value1, value2, "deliveryFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeIsNull() {
			addCriterion("sku_fee is null");
			return (Criteria) this;
		}

		public Criteria andSkuFeeIsNotNull() {
			addCriterion("sku_fee is not null");
			return (Criteria) this;
		}

		public Criteria andSkuFeeEqualTo(BigDecimal value) {
			addCriterion("sku_fee =", value, "skuFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeNotEqualTo(BigDecimal value) {
			addCriterion("sku_fee <>", value, "skuFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeGreaterThan(BigDecimal value) {
			addCriterion("sku_fee >", value, "skuFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("sku_fee >=", value, "skuFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeLessThan(BigDecimal value) {
			addCriterion("sku_fee <", value, "skuFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("sku_fee <=", value, "skuFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeIn(List<BigDecimal> values) {
			addCriterion("sku_fee in", values, "skuFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeNotIn(List<BigDecimal> values) {
			addCriterion("sku_fee not in", values, "skuFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("sku_fee between", value1, value2, "skuFee");
			return (Criteria) this;
		}

		public Criteria andSkuFeeNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("sku_fee not between", value1, value2, "skuFee");
			return (Criteria) this;
		}

		public Criteria andOrderPointsIsNull() {
			addCriterion("order_points is null");
			return (Criteria) this;
		}

		public Criteria andOrderPointsIsNotNull() {
			addCriterion("order_points is not null");
			return (Criteria) this;
		}

		public Criteria andOrderPointsEqualTo(BigDecimal value) {
			addCriterion("order_points =", value, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderPointsNotEqualTo(BigDecimal value) {
			addCriterion("order_points <>", value, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderPointsGreaterThan(BigDecimal value) {
			addCriterion("order_points >", value, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderPointsGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("order_points >=", value, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderPointsLessThan(BigDecimal value) {
			addCriterion("order_points <", value, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderPointsLessThanOrEqualTo(BigDecimal value) {
			addCriterion("order_points <=", value, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderPointsIn(List<BigDecimal> values) {
			addCriterion("order_points in", values, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderPointsNotIn(List<BigDecimal> values) {
			addCriterion("order_points not in", values, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderPointsBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_points between", value1, value2, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderPointsNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_points not between", value1, value2, "orderPoints");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersIsNull() {
			addCriterion("order_vouchers is null");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersIsNotNull() {
			addCriterion("order_vouchers is not null");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersEqualTo(BigDecimal value) {
			addCriterion("order_vouchers =", value, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersNotEqualTo(BigDecimal value) {
			addCriterion("order_vouchers <>", value, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersGreaterThan(BigDecimal value) {
			addCriterion("order_vouchers >", value, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("order_vouchers >=", value, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersLessThan(BigDecimal value) {
			addCriterion("order_vouchers <", value, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersLessThanOrEqualTo(BigDecimal value) {
			addCriterion("order_vouchers <=", value, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersIn(List<BigDecimal> values) {
			addCriterion("order_vouchers in", values, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersNotIn(List<BigDecimal> values) {
			addCriterion("order_vouchers not in", values, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_vouchers between", value1, value2, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andOrderVouchersNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_vouchers not between", value1, value2, "orderVouchers");
			return (Criteria) this;
		}

		public Criteria andCouponNoIsNull() {
			addCriterion("coupon_no is null");
			return (Criteria) this;
		}

		public Criteria andCouponNoIsNotNull() {
			addCriterion("coupon_no is not null");
			return (Criteria) this;
		}

		public Criteria andCouponNoEqualTo(String value) {
			addCriterion("coupon_no =", value, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoNotEqualTo(String value) {
			addCriterion("coupon_no <>", value, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoGreaterThan(String value) {
			addCriterion("coupon_no >", value, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoGreaterThanOrEqualTo(String value) {
			addCriterion("coupon_no >=", value, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoLessThan(String value) {
			addCriterion("coupon_no <", value, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoLessThanOrEqualTo(String value) {
			addCriterion("coupon_no <=", value, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoLike(String value) {
			addCriterion("coupon_no like", value, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoNotLike(String value) {
			addCriterion("coupon_no not like", value, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoIn(List<String> values) {
			addCriterion("coupon_no in", values, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoNotIn(List<String> values) {
			addCriterion("coupon_no not in", values, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoBetween(String value1, String value2) {
			addCriterion("coupon_no between", value1, value2, "couponNo");
			return (Criteria) this;
		}

		public Criteria andCouponNoNotBetween(String value1, String value2) {
			addCriterion("coupon_no not between", value1, value2, "couponNo");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeIsNull() {
			addCriterion("activity_discount_fee is null");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeIsNotNull() {
			addCriterion("activity_discount_fee is not null");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeEqualTo(BigDecimal value) {
			addCriterion("activity_discount_fee =", value, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeNotEqualTo(BigDecimal value) {
			addCriterion("activity_discount_fee <>", value, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeGreaterThan(BigDecimal value) {
			addCriterion("activity_discount_fee >", value, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("activity_discount_fee >=", value, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeLessThan(BigDecimal value) {
			addCriterion("activity_discount_fee <", value, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("activity_discount_fee <=", value, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeIn(List<BigDecimal> values) {
			addCriterion("activity_discount_fee in", values, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeNotIn(List<BigDecimal> values) {
			addCriterion("activity_discount_fee not in", values, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("activity_discount_fee between", value1, value2, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andActivityDiscountFeeNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("activity_discount_fee not between", value1, value2, "activityDiscountFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeIsNull() {
			addCriterion("paid_fee is null");
			return (Criteria) this;
		}

		public Criteria andPaidFeeIsNotNull() {
			addCriterion("paid_fee is not null");
			return (Criteria) this;
		}

		public Criteria andPaidFeeEqualTo(BigDecimal value) {
			addCriterion("paid_fee =", value, "paidFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeNotEqualTo(BigDecimal value) {
			addCriterion("paid_fee <>", value, "paidFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeGreaterThan(BigDecimal value) {
			addCriterion("paid_fee >", value, "paidFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("paid_fee >=", value, "paidFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeLessThan(BigDecimal value) {
			addCriterion("paid_fee <", value, "paidFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("paid_fee <=", value, "paidFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeIn(List<BigDecimal> values) {
			addCriterion("paid_fee in", values, "paidFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeNotIn(List<BigDecimal> values) {
			addCriterion("paid_fee not in", values, "paidFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("paid_fee between", value1, value2, "paidFee");
			return (Criteria) this;
		}

		public Criteria andPaidFeeNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("paid_fee not between", value1, value2, "paidFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeIsNull() {
			addCriterion("order_pay_fee is null");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeIsNotNull() {
			addCriterion("order_pay_fee is not null");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeEqualTo(BigDecimal value) {
			addCriterion("order_pay_fee =", value, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeNotEqualTo(BigDecimal value) {
			addCriterion("order_pay_fee <>", value, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeGreaterThan(BigDecimal value) {
			addCriterion("order_pay_fee >", value, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("order_pay_fee >=", value, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeLessThan(BigDecimal value) {
			addCriterion("order_pay_fee <", value, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("order_pay_fee <=", value, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeIn(List<BigDecimal> values) {
			addCriterion("order_pay_fee in", values, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeNotIn(List<BigDecimal> values) {
			addCriterion("order_pay_fee not in", values, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_pay_fee between", value1, value2, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderPayFeeNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_pay_fee not between", value1, value2, "orderPayFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeIsNull() {
			addCriterion("order_fee is null");
			return (Criteria) this;
		}

		public Criteria andOrderFeeIsNotNull() {
			addCriterion("order_fee is not null");
			return (Criteria) this;
		}

		public Criteria andOrderFeeEqualTo(BigDecimal value) {
			addCriterion("order_fee =", value, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeNotEqualTo(BigDecimal value) {
			addCriterion("order_fee <>", value, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeGreaterThan(BigDecimal value) {
			addCriterion("order_fee >", value, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("order_fee >=", value, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeLessThan(BigDecimal value) {
			addCriterion("order_fee <", value, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("order_fee <=", value, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeIn(List<BigDecimal> values) {
			addCriterion("order_fee in", values, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeNotIn(List<BigDecimal> values) {
			addCriterion("order_fee not in", values, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_fee between", value1, value2, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderFeeNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_fee not between", value1, value2, "orderFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeIsNull() {
			addCriterion("order_return_fee is null");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeIsNotNull() {
			addCriterion("order_return_fee is not null");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeEqualTo(BigDecimal value) {
			addCriterion("order_return_fee =", value, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeNotEqualTo(BigDecimal value) {
			addCriterion("order_return_fee <>", value, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeGreaterThan(BigDecimal value) {
			addCriterion("order_return_fee >", value, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("order_return_fee >=", value, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeLessThan(BigDecimal value) {
			addCriterion("order_return_fee <", value, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("order_return_fee <=", value, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeIn(List<BigDecimal> values) {
			addCriterion("order_return_fee in", values, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeNotIn(List<BigDecimal> values) {
			addCriterion("order_return_fee not in", values, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_return_fee between", value1, value2, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderReturnFeeNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_return_fee not between", value1, value2, "orderReturnFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeIsNull() {
			addCriterion("order_real_fee is null");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeIsNotNull() {
			addCriterion("order_real_fee is not null");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeEqualTo(BigDecimal value) {
			addCriterion("order_real_fee =", value, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeNotEqualTo(BigDecimal value) {
			addCriterion("order_real_fee <>", value, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeGreaterThan(BigDecimal value) {
			addCriterion("order_real_fee >", value, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("order_real_fee >=", value, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeLessThan(BigDecimal value) {
			addCriterion("order_real_fee <", value, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeLessThanOrEqualTo(BigDecimal value) {
			addCriterion("order_real_fee <=", value, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeIn(List<BigDecimal> values) {
			addCriterion("order_real_fee in", values, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeNotIn(List<BigDecimal> values) {
			addCriterion("order_real_fee not in", values, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_real_fee between", value1, value2, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andOrderRealFeeNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_real_fee not between", value1, value2, "orderRealFee");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceIsNull() {
			addCriterion("supply_price is null");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceIsNotNull() {
			addCriterion("supply_price is not null");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceEqualTo(BigDecimal value) {
			addCriterion("supply_price =", value, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceNotEqualTo(BigDecimal value) {
			addCriterion("supply_price <>", value, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceGreaterThan(BigDecimal value) {
			addCriterion("supply_price >", value, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("supply_price >=", value, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceLessThan(BigDecimal value) {
			addCriterion("supply_price <", value, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceLessThanOrEqualTo(BigDecimal value) {
			addCriterion("supply_price <=", value, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceIn(List<BigDecimal> values) {
			addCriterion("supply_price in", values, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceNotIn(List<BigDecimal> values) {
			addCriterion("supply_price not in", values, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("supply_price between", value1, value2, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andSupplyPriceNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("supply_price not between", value1, value2, "supplyPrice");
			return (Criteria) this;
		}

		public Criteria andReceiveUserIsNull() {
			addCriterion("receive_user is null");
			return (Criteria) this;
		}

		public Criteria andReceiveUserIsNotNull() {
			addCriterion("receive_user is not null");
			return (Criteria) this;
		}

		public Criteria andReceiveUserEqualTo(String value) {
			addCriterion("receive_user =", value, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserNotEqualTo(String value) {
			addCriterion("receive_user <>", value, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserGreaterThan(String value) {
			addCriterion("receive_user >", value, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserGreaterThanOrEqualTo(String value) {
			addCriterion("receive_user >=", value, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserLessThan(String value) {
			addCriterion("receive_user <", value, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserLessThanOrEqualTo(String value) {
			addCriterion("receive_user <=", value, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserLike(String value) {
			addCriterion("receive_user like", value, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserNotLike(String value) {
			addCriterion("receive_user not like", value, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserIn(List<String> values) {
			addCriterion("receive_user in", values, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserNotIn(List<String> values) {
			addCriterion("receive_user not in", values, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserBetween(String value1, String value2) {
			addCriterion("receive_user between", value1, value2, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveUserNotBetween(String value1, String value2) {
			addCriterion("receive_user not between", value1, value2, "receiveUser");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeIsNull() {
			addCriterion("receive_time is null");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeIsNotNull() {
			addCriterion("receive_time is not null");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeEqualTo(String value) {
			addCriterion("receive_time =", value, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeNotEqualTo(String value) {
			addCriterion("receive_time <>", value, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeGreaterThan(String value) {
			addCriterion("receive_time >", value, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeGreaterThanOrEqualTo(String value) {
			addCriterion("receive_time >=", value, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeLessThan(String value) {
			addCriterion("receive_time <", value, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeLessThanOrEqualTo(String value) {
			addCriterion("receive_time <=", value, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeLike(String value) {
			addCriterion("receive_time like", value, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeNotLike(String value) {
			addCriterion("receive_time not like", value, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeIn(List<String> values) {
			addCriterion("receive_time in", values, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeNotIn(List<String> values) {
			addCriterion("receive_time not in", values, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeBetween(String value1, String value2) {
			addCriterion("receive_time between", value1, value2, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andReceiveTimeNotBetween(String value1, String value2) {
			addCriterion("receive_time not between", value1, value2, "receiveTime");
			return (Criteria) this;
		}

		public Criteria andProvinceIdIsNull() {
			addCriterion("province_id is null");
			return (Criteria) this;
		}

		public Criteria andProvinceIdIsNotNull() {
			addCriterion("province_id is not null");
			return (Criteria) this;
		}

		public Criteria andProvinceIdEqualTo(Integer value) {
			addCriterion("province_id =", value, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceIdNotEqualTo(Integer value) {
			addCriterion("province_id <>", value, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceIdGreaterThan(Integer value) {
			addCriterion("province_id >", value, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("province_id >=", value, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceIdLessThan(Integer value) {
			addCriterion("province_id <", value, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceIdLessThanOrEqualTo(Integer value) {
			addCriterion("province_id <=", value, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceIdIn(List<Integer> values) {
			addCriterion("province_id in", values, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceIdNotIn(List<Integer> values) {
			addCriterion("province_id not in", values, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceIdBetween(Integer value1, Integer value2) {
			addCriterion("province_id between", value1, value2, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceIdNotBetween(Integer value1, Integer value2) {
			addCriterion("province_id not between", value1, value2, "provinceId");
			return (Criteria) this;
		}

		public Criteria andProvinceNameIsNull() {
			addCriterion("province_name is null");
			return (Criteria) this;
		}

		public Criteria andProvinceNameIsNotNull() {
			addCriterion("province_name is not null");
			return (Criteria) this;
		}

		public Criteria andProvinceNameEqualTo(String value) {
			addCriterion("province_name =", value, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameNotEqualTo(String value) {
			addCriterion("province_name <>", value, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameGreaterThan(String value) {
			addCriterion("province_name >", value, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameGreaterThanOrEqualTo(String value) {
			addCriterion("province_name >=", value, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameLessThan(String value) {
			addCriterion("province_name <", value, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameLessThanOrEqualTo(String value) {
			addCriterion("province_name <=", value, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameLike(String value) {
			addCriterion("province_name like", value, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameNotLike(String value) {
			addCriterion("province_name not like", value, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameIn(List<String> values) {
			addCriterion("province_name in", values, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameNotIn(List<String> values) {
			addCriterion("province_name not in", values, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameBetween(String value1, String value2) {
			addCriterion("province_name between", value1, value2, "provinceName");
			return (Criteria) this;
		}

		public Criteria andProvinceNameNotBetween(String value1, String value2) {
			addCriterion("province_name not between", value1, value2, "provinceName");
			return (Criteria) this;
		}

		public Criteria andCityIdIsNull() {
			addCriterion("city_id is null");
			return (Criteria) this;
		}

		public Criteria andCityIdIsNotNull() {
			addCriterion("city_id is not null");
			return (Criteria) this;
		}

		public Criteria andCityIdEqualTo(Integer value) {
			addCriterion("city_id =", value, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityIdNotEqualTo(Integer value) {
			addCriterion("city_id <>", value, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityIdGreaterThan(Integer value) {
			addCriterion("city_id >", value, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("city_id >=", value, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityIdLessThan(Integer value) {
			addCriterion("city_id <", value, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityIdLessThanOrEqualTo(Integer value) {
			addCriterion("city_id <=", value, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityIdIn(List<Integer> values) {
			addCriterion("city_id in", values, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityIdNotIn(List<Integer> values) {
			addCriterion("city_id not in", values, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityIdBetween(Integer value1, Integer value2) {
			addCriterion("city_id between", value1, value2, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityIdNotBetween(Integer value1, Integer value2) {
			addCriterion("city_id not between", value1, value2, "cityId");
			return (Criteria) this;
		}

		public Criteria andCityNameIsNull() {
			addCriterion("city_name is null");
			return (Criteria) this;
		}

		public Criteria andCityNameIsNotNull() {
			addCriterion("city_name is not null");
			return (Criteria) this;
		}

		public Criteria andCityNameEqualTo(String value) {
			addCriterion("city_name =", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameNotEqualTo(String value) {
			addCriterion("city_name <>", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameGreaterThan(String value) {
			addCriterion("city_name >", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameGreaterThanOrEqualTo(String value) {
			addCriterion("city_name >=", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameLessThan(String value) {
			addCriterion("city_name <", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameLessThanOrEqualTo(String value) {
			addCriterion("city_name <=", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameLike(String value) {
			addCriterion("city_name like", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameNotLike(String value) {
			addCriterion("city_name not like", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameIn(List<String> values) {
			addCriterion("city_name in", values, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameNotIn(List<String> values) {
			addCriterion("city_name not in", values, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameBetween(String value1, String value2) {
			addCriterion("city_name between", value1, value2, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameNotBetween(String value1, String value2) {
			addCriterion("city_name not between", value1, value2, "cityName");
			return (Criteria) this;
		}

		public Criteria andAreaIdIsNull() {
			addCriterion("area_id is null");
			return (Criteria) this;
		}

		public Criteria andAreaIdIsNotNull() {
			addCriterion("area_id is not null");
			return (Criteria) this;
		}

		public Criteria andAreaIdEqualTo(Integer value) {
			addCriterion("area_id =", value, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaIdNotEqualTo(Integer value) {
			addCriterion("area_id <>", value, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaIdGreaterThan(Integer value) {
			addCriterion("area_id >", value, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("area_id >=", value, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaIdLessThan(Integer value) {
			addCriterion("area_id <", value, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaIdLessThanOrEqualTo(Integer value) {
			addCriterion("area_id <=", value, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaIdIn(List<Integer> values) {
			addCriterion("area_id in", values, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaIdNotIn(List<Integer> values) {
			addCriterion("area_id not in", values, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaIdBetween(Integer value1, Integer value2) {
			addCriterion("area_id between", value1, value2, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaIdNotBetween(Integer value1, Integer value2) {
			addCriterion("area_id not between", value1, value2, "areaId");
			return (Criteria) this;
		}

		public Criteria andAreaNameIsNull() {
			addCriterion("area_name is null");
			return (Criteria) this;
		}

		public Criteria andAreaNameIsNotNull() {
			addCriterion("area_name is not null");
			return (Criteria) this;
		}

		public Criteria andAreaNameEqualTo(String value) {
			addCriterion("area_name =", value, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameNotEqualTo(String value) {
			addCriterion("area_name <>", value, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameGreaterThan(String value) {
			addCriterion("area_name >", value, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
			addCriterion("area_name >=", value, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameLessThan(String value) {
			addCriterion("area_name <", value, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameLessThanOrEqualTo(String value) {
			addCriterion("area_name <=", value, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameLike(String value) {
			addCriterion("area_name like", value, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameNotLike(String value) {
			addCriterion("area_name not like", value, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameIn(List<String> values) {
			addCriterion("area_name in", values, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameNotIn(List<String> values) {
			addCriterion("area_name not in", values, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameBetween(String value1, String value2) {
			addCriterion("area_name between", value1, value2, "areaName");
			return (Criteria) this;
		}

		public Criteria andAreaNameNotBetween(String value1, String value2) {
			addCriterion("area_name not between", value1, value2, "areaName");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressIsNull() {
			addCriterion("receive_address is null");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressIsNotNull() {
			addCriterion("receive_address is not null");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressEqualTo(String value) {
			addCriterion("receive_address =", value, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressNotEqualTo(String value) {
			addCriterion("receive_address <>", value, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressGreaterThan(String value) {
			addCriterion("receive_address >", value, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressGreaterThanOrEqualTo(String value) {
			addCriterion("receive_address >=", value, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressLessThan(String value) {
			addCriterion("receive_address <", value, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressLessThanOrEqualTo(String value) {
			addCriterion("receive_address <=", value, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressLike(String value) {
			addCriterion("receive_address like", value, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressNotLike(String value) {
			addCriterion("receive_address not like", value, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressIn(List<String> values) {
			addCriterion("receive_address in", values, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressNotIn(List<String> values) {
			addCriterion("receive_address not in", values, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressBetween(String value1, String value2) {
			addCriterion("receive_address between", value1, value2, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveAddressNotBetween(String value1, String value2) {
			addCriterion("receive_address not between", value1, value2, "receiveAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressIsNull() {
			addCriterion("receive_full_address is null");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressIsNotNull() {
			addCriterion("receive_full_address is not null");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressEqualTo(String value) {
			addCriterion("receive_full_address =", value, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressNotEqualTo(String value) {
			addCriterion("receive_full_address <>", value, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressGreaterThan(String value) {
			addCriterion("receive_full_address >", value, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressGreaterThanOrEqualTo(String value) {
			addCriterion("receive_full_address >=", value, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressLessThan(String value) {
			addCriterion("receive_full_address <", value, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressLessThanOrEqualTo(String value) {
			addCriterion("receive_full_address <=", value, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressLike(String value) {
			addCriterion("receive_full_address like", value, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressNotLike(String value) {
			addCriterion("receive_full_address not like", value, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressIn(List<String> values) {
			addCriterion("receive_full_address in", values, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressNotIn(List<String> values) {
			addCriterion("receive_full_address not in", values, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressBetween(String value1, String value2) {
			addCriterion("receive_full_address between", value1, value2, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveFullAddressNotBetween(String value1, String value2) {
			addCriterion("receive_full_address not between", value1, value2, "receiveFullAddress");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeIsNull() {
			addCriterion("receive_longitude is null");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeIsNotNull() {
			addCriterion("receive_longitude is not null");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeEqualTo(String value) {
			addCriterion("receive_longitude =", value, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeNotEqualTo(String value) {
			addCriterion("receive_longitude <>", value, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeGreaterThan(String value) {
			addCriterion("receive_longitude >", value, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeGreaterThanOrEqualTo(String value) {
			addCriterion("receive_longitude >=", value, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeLessThan(String value) {
			addCriterion("receive_longitude <", value, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeLessThanOrEqualTo(String value) {
			addCriterion("receive_longitude <=", value, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeLike(String value) {
			addCriterion("receive_longitude like", value, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeNotLike(String value) {
			addCriterion("receive_longitude not like", value, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeIn(List<String> values) {
			addCriterion("receive_longitude in", values, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeNotIn(List<String> values) {
			addCriterion("receive_longitude not in", values, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeBetween(String value1, String value2) {
			addCriterion("receive_longitude between", value1, value2, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLongitudeNotBetween(String value1, String value2) {
			addCriterion("receive_longitude not between", value1, value2, "receiveLongitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeIsNull() {
			addCriterion("receive_latitude is null");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeIsNotNull() {
			addCriterion("receive_latitude is not null");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeEqualTo(String value) {
			addCriterion("receive_latitude =", value, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeNotEqualTo(String value) {
			addCriterion("receive_latitude <>", value, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeGreaterThan(String value) {
			addCriterion("receive_latitude >", value, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeGreaterThanOrEqualTo(String value) {
			addCriterion("receive_latitude >=", value, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeLessThan(String value) {
			addCriterion("receive_latitude <", value, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeLessThanOrEqualTo(String value) {
			addCriterion("receive_latitude <=", value, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeLike(String value) {
			addCriterion("receive_latitude like", value, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeNotLike(String value) {
			addCriterion("receive_latitude not like", value, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeIn(List<String> values) {
			addCriterion("receive_latitude in", values, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeNotIn(List<String> values) {
			addCriterion("receive_latitude not in", values, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeBetween(String value1, String value2) {
			addCriterion("receive_latitude between", value1, value2, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceiveLatitudeNotBetween(String value1, String value2) {
			addCriterion("receive_latitude not between", value1, value2, "receiveLatitude");
			return (Criteria) this;
		}

		public Criteria andReceivePostIsNull() {
			addCriterion("receive_post is null");
			return (Criteria) this;
		}

		public Criteria andReceivePostIsNotNull() {
			addCriterion("receive_post is not null");
			return (Criteria) this;
		}

		public Criteria andReceivePostEqualTo(Integer value) {
			addCriterion("receive_post =", value, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceivePostNotEqualTo(Integer value) {
			addCriterion("receive_post <>", value, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceivePostGreaterThan(Integer value) {
			addCriterion("receive_post >", value, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceivePostGreaterThanOrEqualTo(Integer value) {
			addCriterion("receive_post >=", value, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceivePostLessThan(Integer value) {
			addCriterion("receive_post <", value, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceivePostLessThanOrEqualTo(Integer value) {
			addCriterion("receive_post <=", value, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceivePostIn(List<Integer> values) {
			addCriterion("receive_post in", values, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceivePostNotIn(List<Integer> values) {
			addCriterion("receive_post not in", values, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceivePostBetween(Integer value1, Integer value2) {
			addCriterion("receive_post between", value1, value2, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceivePostNotBetween(Integer value1, Integer value2) {
			addCriterion("receive_post not between", value1, value2, "receivePost");
			return (Criteria) this;
		}

		public Criteria andReceiveTelIsNull() {
			addCriterion("receive_tel is null");
			return (Criteria) this;
		}

		public Criteria andReceiveTelIsNotNull() {
			addCriterion("receive_tel is not null");
			return (Criteria) this;
		}

		public Criteria andReceiveTelEqualTo(String value) {
			addCriterion("receive_tel =", value, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelNotEqualTo(String value) {
			addCriterion("receive_tel <>", value, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelGreaterThan(String value) {
			addCriterion("receive_tel >", value, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelGreaterThanOrEqualTo(String value) {
			addCriterion("receive_tel >=", value, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelLessThan(String value) {
			addCriterion("receive_tel <", value, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelLessThanOrEqualTo(String value) {
			addCriterion("receive_tel <=", value, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelLike(String value) {
			addCriterion("receive_tel like", value, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelNotLike(String value) {
			addCriterion("receive_tel not like", value, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelIn(List<String> values) {
			addCriterion("receive_tel in", values, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelNotIn(List<String> values) {
			addCriterion("receive_tel not in", values, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelBetween(String value1, String value2) {
			addCriterion("receive_tel between", value1, value2, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveTelNotBetween(String value1, String value2) {
			addCriterion("receive_tel not between", value1, value2, "receiveTel");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileIsNull() {
			addCriterion("receive_mobile is null");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileIsNotNull() {
			addCriterion("receive_mobile is not null");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileEqualTo(String value) {
			addCriterion("receive_mobile =", value, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileNotEqualTo(String value) {
			addCriterion("receive_mobile <>", value, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileGreaterThan(String value) {
			addCriterion("receive_mobile >", value, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileGreaterThanOrEqualTo(String value) {
			addCriterion("receive_mobile >=", value, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileLessThan(String value) {
			addCriterion("receive_mobile <", value, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileLessThanOrEqualTo(String value) {
			addCriterion("receive_mobile <=", value, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileLike(String value) {
			addCriterion("receive_mobile like", value, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileNotLike(String value) {
			addCriterion("receive_mobile not like", value, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileIn(List<String> values) {
			addCriterion("receive_mobile in", values, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileNotIn(List<String> values) {
			addCriterion("receive_mobile not in", values, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileBetween(String value1, String value2) {
			addCriterion("receive_mobile between", value1, value2, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andReceiveMobileNotBetween(String value1, String value2) {
			addCriterion("receive_mobile not between", value1, value2, "receiveMobile");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleIsNull() {
			addCriterion("invoice_title is null");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleIsNotNull() {
			addCriterion("invoice_title is not null");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleEqualTo(String value) {
			addCriterion("invoice_title =", value, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleNotEqualTo(String value) {
			addCriterion("invoice_title <>", value, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleGreaterThan(String value) {
			addCriterion("invoice_title >", value, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleGreaterThanOrEqualTo(String value) {
			addCriterion("invoice_title >=", value, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleLessThan(String value) {
			addCriterion("invoice_title <", value, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleLessThanOrEqualTo(String value) {
			addCriterion("invoice_title <=", value, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleLike(String value) {
			addCriterion("invoice_title like", value, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleNotLike(String value) {
			addCriterion("invoice_title not like", value, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleIn(List<String> values) {
			addCriterion("invoice_title in", values, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleNotIn(List<String> values) {
			addCriterion("invoice_title not in", values, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleBetween(String value1, String value2) {
			addCriterion("invoice_title between", value1, value2, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceTitleNotBetween(String value1, String value2) {
			addCriterion("invoice_title not between", value1, value2, "invoiceTitle");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentIsNull() {
			addCriterion("invoice_content is null");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentIsNotNull() {
			addCriterion("invoice_content is not null");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentEqualTo(String value) {
			addCriterion("invoice_content =", value, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentNotEqualTo(String value) {
			addCriterion("invoice_content <>", value, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentGreaterThan(String value) {
			addCriterion("invoice_content >", value, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentGreaterThanOrEqualTo(String value) {
			addCriterion("invoice_content >=", value, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentLessThan(String value) {
			addCriterion("invoice_content <", value, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentLessThanOrEqualTo(String value) {
			addCriterion("invoice_content <=", value, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentLike(String value) {
			addCriterion("invoice_content like", value, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentNotLike(String value) {
			addCriterion("invoice_content not like", value, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentIn(List<String> values) {
			addCriterion("invoice_content in", values, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentNotIn(List<String> values) {
			addCriterion("invoice_content not in", values, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentBetween(String value1, String value2) {
			addCriterion("invoice_content between", value1, value2, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andInvoiceContentNotBetween(String value1, String value2) {
			addCriterion("invoice_content not between", value1, value2, "invoiceContent");
			return (Criteria) this;
		}

		public Criteria andOrderMsgIsNull() {
			addCriterion("order_msg is null");
			return (Criteria) this;
		}

		public Criteria andOrderMsgIsNotNull() {
			addCriterion("order_msg is not null");
			return (Criteria) this;
		}

		public Criteria andOrderMsgEqualTo(String value) {
			addCriterion("order_msg =", value, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgNotEqualTo(String value) {
			addCriterion("order_msg <>", value, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgGreaterThan(String value) {
			addCriterion("order_msg >", value, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgGreaterThanOrEqualTo(String value) {
			addCriterion("order_msg >=", value, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgLessThan(String value) {
			addCriterion("order_msg <", value, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgLessThanOrEqualTo(String value) {
			addCriterion("order_msg <=", value, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgLike(String value) {
			addCriterion("order_msg like", value, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgNotLike(String value) {
			addCriterion("order_msg not like", value, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgIn(List<String> values) {
			addCriterion("order_msg in", values, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgNotIn(List<String> values) {
			addCriterion("order_msg not in", values, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgBetween(String value1, String value2) {
			addCriterion("order_msg between", value1, value2, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderMsgNotBetween(String value1, String value2) {
			addCriterion("order_msg not between", value1, value2, "orderMsg");
			return (Criteria) this;
		}

		public Criteria andOrderNotesIsNull() {
			addCriterion("order_notes is null");
			return (Criteria) this;
		}

		public Criteria andOrderNotesIsNotNull() {
			addCriterion("order_notes is not null");
			return (Criteria) this;
		}

		public Criteria andOrderNotesEqualTo(String value) {
			addCriterion("order_notes =", value, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesNotEqualTo(String value) {
			addCriterion("order_notes <>", value, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesGreaterThan(String value) {
			addCriterion("order_notes >", value, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesGreaterThanOrEqualTo(String value) {
			addCriterion("order_notes >=", value, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesLessThan(String value) {
			addCriterion("order_notes <", value, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesLessThanOrEqualTo(String value) {
			addCriterion("order_notes <=", value, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesLike(String value) {
			addCriterion("order_notes like", value, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesNotLike(String value) {
			addCriterion("order_notes not like", value, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesIn(List<String> values) {
			addCriterion("order_notes in", values, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesNotIn(List<String> values) {
			addCriterion("order_notes not in", values, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesBetween(String value1, String value2) {
			addCriterion("order_notes between", value1, value2, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andOrderNotesNotBetween(String value1, String value2) {
			addCriterion("order_notes not between", value1, value2, "orderNotes");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdIsNull() {
			addCriterion("audit_user_id is null");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdIsNotNull() {
			addCriterion("audit_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdEqualTo(Integer value) {
			addCriterion("audit_user_id =", value, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdNotEqualTo(Integer value) {
			addCriterion("audit_user_id <>", value, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdGreaterThan(Integer value) {
			addCriterion("audit_user_id >", value, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("audit_user_id >=", value, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdLessThan(Integer value) {
			addCriterion("audit_user_id <", value, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdLessThanOrEqualTo(Integer value) {
			addCriterion("audit_user_id <=", value, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdIn(List<Integer> values) {
			addCriterion("audit_user_id in", values, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdNotIn(List<Integer> values) {
			addCriterion("audit_user_id not in", values, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdBetween(Integer value1, Integer value2) {
			addCriterion("audit_user_id between", value1, value2, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditUserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("audit_user_id not between", value1, value2, "auditUserId");
			return (Criteria) this;
		}

		public Criteria andAuditTimeIsNull() {
			addCriterion("audit_time is null");
			return (Criteria) this;
		}

		public Criteria andAuditTimeIsNotNull() {
			addCriterion("audit_time is not null");
			return (Criteria) this;
		}

		public Criteria andAuditTimeEqualTo(String value) {
			addCriterion("audit_time =", value, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeNotEqualTo(String value) {
			addCriterion("audit_time <>", value, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeGreaterThan(String value) {
			addCriterion("audit_time >", value, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeGreaterThanOrEqualTo(String value) {
			addCriterion("audit_time >=", value, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeLessThan(String value) {
			addCriterion("audit_time <", value, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeLessThanOrEqualTo(String value) {
			addCriterion("audit_time <=", value, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeLike(String value) {
			addCriterion("audit_time like", value, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeNotLike(String value) {
			addCriterion("audit_time not like", value, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeIn(List<String> values) {
			addCriterion("audit_time in", values, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeNotIn(List<String> values) {
			addCriterion("audit_time not in", values, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeBetween(String value1, String value2) {
			addCriterion("audit_time between", value1, value2, "auditTime");
			return (Criteria) this;
		}

		public Criteria andAuditTimeNotBetween(String value1, String value2) {
			addCriterion("audit_time not between", value1, value2, "auditTime");
			return (Criteria) this;
		}

		public Criteria andIsLockIsNull() {
			addCriterion("is_lock is null");
			return (Criteria) this;
		}

		public Criteria andIsLockIsNotNull() {
			addCriterion("is_lock is not null");
			return (Criteria) this;
		}

		public Criteria andIsLockEqualTo(String value) {
			addCriterion("is_lock =", value, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockNotEqualTo(String value) {
			addCriterion("is_lock <>", value, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockGreaterThan(String value) {
			addCriterion("is_lock >", value, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockGreaterThanOrEqualTo(String value) {
			addCriterion("is_lock >=", value, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockLessThan(String value) {
			addCriterion("is_lock <", value, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockLessThanOrEqualTo(String value) {
			addCriterion("is_lock <=", value, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockLike(String value) {
			addCriterion("is_lock like", value, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockNotLike(String value) {
			addCriterion("is_lock not like", value, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockIn(List<String> values) {
			addCriterion("is_lock in", values, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockNotIn(List<String> values) {
			addCriterion("is_lock not in", values, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockBetween(String value1, String value2) {
			addCriterion("is_lock between", value1, value2, "isLock");
			return (Criteria) this;
		}

		public Criteria andIsLockNotBetween(String value1, String value2) {
			addCriterion("is_lock not between", value1, value2, "isLock");
			return (Criteria) this;
		}

		public Criteria andLockTimeIsNull() {
			addCriterion("lock_time is null");
			return (Criteria) this;
		}

		public Criteria andLockTimeIsNotNull() {
			addCriterion("lock_time is not null");
			return (Criteria) this;
		}

		public Criteria andLockTimeEqualTo(String value) {
			addCriterion("lock_time =", value, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeNotEqualTo(String value) {
			addCriterion("lock_time <>", value, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeGreaterThan(String value) {
			addCriterion("lock_time >", value, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeGreaterThanOrEqualTo(String value) {
			addCriterion("lock_time >=", value, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeLessThan(String value) {
			addCriterion("lock_time <", value, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeLessThanOrEqualTo(String value) {
			addCriterion("lock_time <=", value, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeLike(String value) {
			addCriterion("lock_time like", value, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeNotLike(String value) {
			addCriterion("lock_time not like", value, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeIn(List<String> values) {
			addCriterion("lock_time in", values, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeNotIn(List<String> values) {
			addCriterion("lock_time not in", values, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeBetween(String value1, String value2) {
			addCriterion("lock_time between", value1, value2, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockTimeNotBetween(String value1, String value2) {
			addCriterion("lock_time not between", value1, value2, "lockTime");
			return (Criteria) this;
		}

		public Criteria andLockUserIdIsNull() {
			addCriterion("lock_user_id is null");
			return (Criteria) this;
		}

		public Criteria andLockUserIdIsNotNull() {
			addCriterion("lock_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andLockUserIdEqualTo(Integer value) {
			addCriterion("lock_user_id =", value, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andLockUserIdNotEqualTo(Integer value) {
			addCriterion("lock_user_id <>", value, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andLockUserIdGreaterThan(Integer value) {
			addCriterion("lock_user_id >", value, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andLockUserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("lock_user_id >=", value, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andLockUserIdLessThan(Integer value) {
			addCriterion("lock_user_id <", value, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andLockUserIdLessThanOrEqualTo(Integer value) {
			addCriterion("lock_user_id <=", value, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andLockUserIdIn(List<Integer> values) {
			addCriterion("lock_user_id in", values, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andLockUserIdNotIn(List<Integer> values) {
			addCriterion("lock_user_id not in", values, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andLockUserIdBetween(Integer value1, Integer value2) {
			addCriterion("lock_user_id between", value1, value2, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andLockUserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("lock_user_id not between", value1, value2, "lockUserId");
			return (Criteria) this;
		}

		public Criteria andCommitTimeIsNull() {
			addCriterion("commit_time is null");
			return (Criteria) this;
		}

		public Criteria andCommitTimeIsNotNull() {
			addCriterion("commit_time is not null");
			return (Criteria) this;
		}

		public Criteria andCommitTimeEqualTo(String value) {
			addCriterion("commit_time =", value, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeNotEqualTo(String value) {
			addCriterion("commit_time <>", value, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeGreaterThan(String value) {
			addCriterion("commit_time >", value, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeGreaterThanOrEqualTo(String value) {
			addCriterion("commit_time >=", value, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeLessThan(String value) {
			addCriterion("commit_time <", value, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeLessThanOrEqualTo(String value) {
			addCriterion("commit_time <=", value, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeLike(String value) {
			addCriterion("commit_time like", value, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeNotLike(String value) {
			addCriterion("commit_time not like", value, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeIn(List<String> values) {
			addCriterion("commit_time in", values, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeNotIn(List<String> values) {
			addCriterion("commit_time not in", values, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeBetween(String value1, String value2) {
			addCriterion("commit_time between", value1, value2, "commitTime");
			return (Criteria) this;
		}

		public Criteria andCommitTimeNotBetween(String value1, String value2) {
			addCriterion("commit_time not between", value1, value2, "commitTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeIsNull() {
			addCriterion("finish_time is null");
			return (Criteria) this;
		}

		public Criteria andFinishTimeIsNotNull() {
			addCriterion("finish_time is not null");
			return (Criteria) this;
		}

		public Criteria andFinishTimeEqualTo(String value) {
			addCriterion("finish_time =", value, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeNotEqualTo(String value) {
			addCriterion("finish_time <>", value, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeGreaterThan(String value) {
			addCriterion("finish_time >", value, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeGreaterThanOrEqualTo(String value) {
			addCriterion("finish_time >=", value, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeLessThan(String value) {
			addCriterion("finish_time <", value, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeLessThanOrEqualTo(String value) {
			addCriterion("finish_time <=", value, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeLike(String value) {
			addCriterion("finish_time like", value, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeNotLike(String value) {
			addCriterion("finish_time not like", value, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeIn(List<String> values) {
			addCriterion("finish_time in", values, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeNotIn(List<String> values) {
			addCriterion("finish_time not in", values, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeBetween(String value1, String value2) {
			addCriterion("finish_time between", value1, value2, "finishTime");
			return (Criteria) this;
		}

		public Criteria andFinishTimeNotBetween(String value1, String value2) {
			addCriterion("finish_time not between", value1, value2, "finishTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeIsNull() {
			addCriterion("cancel_time is null");
			return (Criteria) this;
		}

		public Criteria andCancelTimeIsNotNull() {
			addCriterion("cancel_time is not null");
			return (Criteria) this;
		}

		public Criteria andCancelTimeEqualTo(String value) {
			addCriterion("cancel_time =", value, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeNotEqualTo(String value) {
			addCriterion("cancel_time <>", value, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeGreaterThan(String value) {
			addCriterion("cancel_time >", value, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeGreaterThanOrEqualTo(String value) {
			addCriterion("cancel_time >=", value, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeLessThan(String value) {
			addCriterion("cancel_time <", value, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeLessThanOrEqualTo(String value) {
			addCriterion("cancel_time <=", value, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeLike(String value) {
			addCriterion("cancel_time like", value, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeNotLike(String value) {
			addCriterion("cancel_time not like", value, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeIn(List<String> values) {
			addCriterion("cancel_time in", values, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeNotIn(List<String> values) {
			addCriterion("cancel_time not in", values, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeBetween(String value1, String value2) {
			addCriterion("cancel_time between", value1, value2, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andCancelTimeNotBetween(String value1, String value2) {
			addCriterion("cancel_time not between", value1, value2, "cancelTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeIsNull() {
			addCriterion("platform_create_time is null");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeIsNotNull() {
			addCriterion("platform_create_time is not null");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeEqualTo(String value) {
			addCriterion("platform_create_time =", value, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeNotEqualTo(String value) {
			addCriterion("platform_create_time <>", value, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeGreaterThan(String value) {
			addCriterion("platform_create_time >", value, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeGreaterThanOrEqualTo(String value) {
			addCriterion("platform_create_time >=", value, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeLessThan(String value) {
			addCriterion("platform_create_time <", value, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeLessThanOrEqualTo(String value) {
			addCriterion("platform_create_time <=", value, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeLike(String value) {
			addCriterion("platform_create_time like", value, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeNotLike(String value) {
			addCriterion("platform_create_time not like", value, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeIn(List<String> values) {
			addCriterion("platform_create_time in", values, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeNotIn(List<String> values) {
			addCriterion("platform_create_time not in", values, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeBetween(String value1, String value2) {
			addCriterion("platform_create_time between", value1, value2, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformCreateTimeNotBetween(String value1, String value2) {
			addCriterion("platform_create_time not between", value1, value2, "platformCreateTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeIsNull() {
			addCriterion("platform_edit_time is null");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeIsNotNull() {
			addCriterion("platform_edit_time is not null");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeEqualTo(String value) {
			addCriterion("platform_edit_time =", value, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeNotEqualTo(String value) {
			addCriterion("platform_edit_time <>", value, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeGreaterThan(String value) {
			addCriterion("platform_edit_time >", value, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeGreaterThanOrEqualTo(String value) {
			addCriterion("platform_edit_time >=", value, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeLessThan(String value) {
			addCriterion("platform_edit_time <", value, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeLessThanOrEqualTo(String value) {
			addCriterion("platform_edit_time <=", value, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeLike(String value) {
			addCriterion("platform_edit_time like", value, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeNotLike(String value) {
			addCriterion("platform_edit_time not like", value, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeIn(List<String> values) {
			addCriterion("platform_edit_time in", values, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeNotIn(List<String> values) {
			addCriterion("platform_edit_time not in", values, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeBetween(String value1, String value2) {
			addCriterion("platform_edit_time between", value1, value2, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andPlatformEditTimeNotBetween(String value1, String value2) {
			addCriterion("platform_edit_time not between", value1, value2, "platformEditTime");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdIsNull() {
			addCriterion("cancel_user_id is null");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdIsNotNull() {
			addCriterion("cancel_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdEqualTo(Integer value) {
			addCriterion("cancel_user_id =", value, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdNotEqualTo(Integer value) {
			addCriterion("cancel_user_id <>", value, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdGreaterThan(Integer value) {
			addCriterion("cancel_user_id >", value, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("cancel_user_id >=", value, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdLessThan(Integer value) {
			addCriterion("cancel_user_id <", value, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdLessThanOrEqualTo(Integer value) {
			addCriterion("cancel_user_id <=", value, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdIn(List<Integer> values) {
			addCriterion("cancel_user_id in", values, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdNotIn(List<Integer> values) {
			addCriterion("cancel_user_id not in", values, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdBetween(Integer value1, Integer value2) {
			addCriterion("cancel_user_id between", value1, value2, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelUserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("cancel_user_id not between", value1, value2, "cancelUserId");
			return (Criteria) this;
		}

		public Criteria andCancelNotesIsNull() {
			addCriterion("cancel_notes is null");
			return (Criteria) this;
		}

		public Criteria andCancelNotesIsNotNull() {
			addCriterion("cancel_notes is not null");
			return (Criteria) this;
		}

		public Criteria andCancelNotesEqualTo(String value) {
			addCriterion("cancel_notes =", value, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesNotEqualTo(String value) {
			addCriterion("cancel_notes <>", value, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesGreaterThan(String value) {
			addCriterion("cancel_notes >", value, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesGreaterThanOrEqualTo(String value) {
			addCriterion("cancel_notes >=", value, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesLessThan(String value) {
			addCriterion("cancel_notes <", value, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesLessThanOrEqualTo(String value) {
			addCriterion("cancel_notes <=", value, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesLike(String value) {
			addCriterion("cancel_notes like", value, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesNotLike(String value) {
			addCriterion("cancel_notes not like", value, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesIn(List<String> values) {
			addCriterion("cancel_notes in", values, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesNotIn(List<String> values) {
			addCriterion("cancel_notes not in", values, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesBetween(String value1, String value2) {
			addCriterion("cancel_notes between", value1, value2, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andCancelNotesNotBetween(String value1, String value2) {
			addCriterion("cancel_notes not between", value1, value2, "cancelNotes");
			return (Criteria) this;
		}

		public Criteria andIsDeleteIsNull() {
			addCriterion("is_delete is null");
			return (Criteria) this;
		}

		public Criteria andIsDeleteIsNotNull() {
			addCriterion("is_delete is not null");
			return (Criteria) this;
		}

		public Criteria andIsDeleteEqualTo(String value) {
			addCriterion("is_delete =", value, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteNotEqualTo(String value) {
			addCriterion("is_delete <>", value, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteGreaterThan(String value) {
			addCriterion("is_delete >", value, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteGreaterThanOrEqualTo(String value) {
			addCriterion("is_delete >=", value, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteLessThan(String value) {
			addCriterion("is_delete <", value, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteLessThanOrEqualTo(String value) {
			addCriterion("is_delete <=", value, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteLike(String value) {
			addCriterion("is_delete like", value, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteNotLike(String value) {
			addCriterion("is_delete not like", value, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteIn(List<String> values) {
			addCriterion("is_delete in", values, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteNotIn(List<String> values) {
			addCriterion("is_delete not in", values, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteBetween(String value1, String value2) {
			addCriterion("is_delete between", value1, value2, "isDelete");
			return (Criteria) this;
		}

		public Criteria andIsDeleteNotBetween(String value1, String value2) {
			addCriterion("is_delete not between", value1, value2, "isDelete");
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

		public Criteria andHaveCfyIsNull() {
			addCriterion("have_cfy is null");
			return (Criteria) this;
		}

		public Criteria andHaveCfyIsNotNull() {
			addCriterion("have_cfy is not null");
			return (Criteria) this;
		}

		public Criteria andHaveCfyEqualTo(String value) {
			addCriterion("have_cfy =", value, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyNotEqualTo(String value) {
			addCriterion("have_cfy <>", value, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyGreaterThan(String value) {
			addCriterion("have_cfy >", value, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyGreaterThanOrEqualTo(String value) {
			addCriterion("have_cfy >=", value, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyLessThan(String value) {
			addCriterion("have_cfy <", value, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyLessThanOrEqualTo(String value) {
			addCriterion("have_cfy <=", value, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyLike(String value) {
			addCriterion("have_cfy like", value, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyNotLike(String value) {
			addCriterion("have_cfy not like", value, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyIn(List<String> values) {
			addCriterion("have_cfy in", values, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyNotIn(List<String> values) {
			addCriterion("have_cfy not in", values, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyBetween(String value1, String value2) {
			addCriterion("have_cfy between", value1, value2, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andHaveCfyNotBetween(String value1, String value2) {
			addCriterion("have_cfy not between", value1, value2, "haveCfy");
			return (Criteria) this;
		}

		public Criteria andSendStatusIsNull() {
			addCriterion("send_status is null");
			return (Criteria) this;
		}

		public Criteria andSendStatusIsNotNull() {
			addCriterion("send_status is not null");
			return (Criteria) this;
		}

		public Criteria andSendStatusEqualTo(String value) {
			addCriterion("send_status =", value, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusNotEqualTo(String value) {
			addCriterion("send_status <>", value, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusGreaterThan(String value) {
			addCriterion("send_status >", value, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusGreaterThanOrEqualTo(String value) {
			addCriterion("send_status >=", value, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusLessThan(String value) {
			addCriterion("send_status <", value, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusLessThanOrEqualTo(String value) {
			addCriterion("send_status <=", value, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusLike(String value) {
			addCriterion("send_status like", value, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusNotLike(String value) {
			addCriterion("send_status not like", value, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusIn(List<String> values) {
			addCriterion("send_status in", values, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusNotIn(List<String> values) {
			addCriterion("send_status not in", values, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusBetween(String value1, String value2) {
			addCriterion("send_status between", value1, value2, "sendStatus");
			return (Criteria) this;
		}

		public Criteria andSendStatusNotBetween(String value1, String value2) {
			addCriterion("send_status not between", value1, value2, "sendStatus");
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