package cn.com.dubbo.bean.ext;

import java.util.ArrayList;
import java.util.List;

public class ItemBatchIndexExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemBatchIndexExample() {
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

        public Criteria andBatchItemIdIsNull() {
            addCriterion("batch_item_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdIsNotNull() {
            addCriterion("batch_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdEqualTo(Long value) {
            addCriterion("batch_item_id =", value, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdNotEqualTo(Long value) {
            addCriterion("batch_item_id <>", value, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdGreaterThan(Long value) {
            addCriterion("batch_item_id >", value, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("batch_item_id >=", value, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdLessThan(Long value) {
            addCriterion("batch_item_id <", value, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdLessThanOrEqualTo(Long value) {
            addCriterion("batch_item_id <=", value, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdIn(List<Long> values) {
            addCriterion("batch_item_id in", values, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdNotIn(List<Long> values) {
            addCriterion("batch_item_id not in", values, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdBetween(Long value1, Long value2) {
            addCriterion("batch_item_id between", value1, value2, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andBatchItemIdNotBetween(Long value1, Long value2) {
            addCriterion("batch_item_id not between", value1, value2, "batchItemId");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberIsNull() {
            addCriterion("e_ordernumber is null");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberIsNotNull() {
            addCriterion("e_ordernumber is not null");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberEqualTo(String value) {
            addCriterion("e_ordernumber =", value, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberNotEqualTo(String value) {
            addCriterion("e_ordernumber <>", value, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberGreaterThan(String value) {
            addCriterion("e_ordernumber >", value, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberGreaterThanOrEqualTo(String value) {
            addCriterion("e_ordernumber >=", value, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberLessThan(String value) {
            addCriterion("e_ordernumber <", value, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberLessThanOrEqualTo(String value) {
            addCriterion("e_ordernumber <=", value, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberLike(String value) {
            addCriterion("e_ordernumber like", value, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberNotLike(String value) {
            addCriterion("e_ordernumber not like", value, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberIn(List<String> values) {
            addCriterion("e_ordernumber in", values, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberNotIn(List<String> values) {
            addCriterion("e_ordernumber not in", values, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberBetween(String value1, String value2) {
            addCriterion("e_ordernumber between", value1, value2, "eOrdernumber");
            return (Criteria) this;
        }

        public Criteria andEOrdernumberNotBetween(String value1, String value2) {
            addCriterion("e_ordernumber not between", value1, value2, "eOrdernumber");
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

        public Criteria andBillstatesIsNull() {
            addCriterion("billstates is null");
            return (Criteria) this;
        }

        public Criteria andBillstatesIsNotNull() {
            addCriterion("billstates is not null");
            return (Criteria) this;
        }

        public Criteria andBillstatesEqualTo(Integer value) {
            addCriterion("billstates =", value, "billstates");
            return (Criteria) this;
        }

        public Criteria andBillstatesNotEqualTo(Integer value) {
            addCriterion("billstates <>", value, "billstates");
            return (Criteria) this;
        }

        public Criteria andBillstatesGreaterThan(Integer value) {
            addCriterion("billstates >", value, "billstates");
            return (Criteria) this;
        }

        public Criteria andBillstatesGreaterThanOrEqualTo(Integer value) {
            addCriterion("billstates >=", value, "billstates");
            return (Criteria) this;
        }

        public Criteria andBillstatesLessThan(Integer value) {
            addCriterion("billstates <", value, "billstates");
            return (Criteria) this;
        }

        public Criteria andBillstatesLessThanOrEqualTo(Integer value) {
            addCriterion("billstates <=", value, "billstates");
            return (Criteria) this;
        }

        public Criteria andBillstatesIn(List<Integer> values) {
            addCriterion("billstates in", values, "billstates");
            return (Criteria) this;
        }

        public Criteria andBillstatesNotIn(List<Integer> values) {
            addCriterion("billstates not in", values, "billstates");
            return (Criteria) this;
        }

        public Criteria andBillstatesBetween(Integer value1, Integer value2) {
            addCriterion("billstates between", value1, value2, "billstates");
            return (Criteria) this;
        }

        public Criteria andBillstatesNotBetween(Integer value1, Integer value2) {
            addCriterion("billstates not between", value1, value2, "billstates");
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

        public Criteria andGoodsnoIsNull() {
            addCriterion("goodsNo is null");
            return (Criteria) this;
        }

        public Criteria andGoodsnoIsNotNull() {
            addCriterion("goodsNo is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsnoEqualTo(String value) {
            addCriterion("goodsNo =", value, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoNotEqualTo(String value) {
            addCriterion("goodsNo <>", value, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoGreaterThan(String value) {
            addCriterion("goodsNo >", value, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoGreaterThanOrEqualTo(String value) {
            addCriterion("goodsNo >=", value, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoLessThan(String value) {
            addCriterion("goodsNo <", value, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoLessThanOrEqualTo(String value) {
            addCriterion("goodsNo <=", value, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoLike(String value) {
            addCriterion("goodsNo like", value, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoNotLike(String value) {
            addCriterion("goodsNo not like", value, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoIn(List<String> values) {
            addCriterion("goodsNo in", values, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoNotIn(List<String> values) {
            addCriterion("goodsNo not in", values, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoBetween(String value1, String value2) {
            addCriterion("goodsNo between", value1, value2, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoodsnoNotBetween(String value1, String value2) {
            addCriterion("goodsNo not between", value1, value2, "goodsno");
            return (Criteria) this;
        }

        public Criteria andGoods69IsNull() {
            addCriterion("goods69 is null");
            return (Criteria) this;
        }

        public Criteria andGoods69IsNotNull() {
            addCriterion("goods69 is not null");
            return (Criteria) this;
        }

        public Criteria andGoods69EqualTo(String value) {
            addCriterion("goods69 =", value, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69NotEqualTo(String value) {
            addCriterion("goods69 <>", value, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69GreaterThan(String value) {
            addCriterion("goods69 >", value, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69GreaterThanOrEqualTo(String value) {
            addCriterion("goods69 >=", value, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69LessThan(String value) {
            addCriterion("goods69 <", value, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69LessThanOrEqualTo(String value) {
            addCriterion("goods69 <=", value, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69Like(String value) {
            addCriterion("goods69 like", value, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69NotLike(String value) {
            addCriterion("goods69 not like", value, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69In(List<String> values) {
            addCriterion("goods69 in", values, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69NotIn(List<String> values) {
            addCriterion("goods69 not in", values, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69Between(String value1, String value2) {
            addCriterion("goods69 between", value1, value2, "goods69");
            return (Criteria) this;
        }

        public Criteria andGoods69NotBetween(String value1, String value2) {
            addCriterion("goods69 not between", value1, value2, "goods69");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andSumPriceIsNull() {
            addCriterion("sum_price is null");
            return (Criteria) this;
        }

        public Criteria andSumPriceIsNotNull() {
            addCriterion("sum_price is not null");
            return (Criteria) this;
        }

        public Criteria andSumPriceEqualTo(Long value) {
            addCriterion("sum_price =", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceNotEqualTo(Long value) {
            addCriterion("sum_price <>", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceGreaterThan(Long value) {
            addCriterion("sum_price >", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("sum_price >=", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceLessThan(Long value) {
            addCriterion("sum_price <", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceLessThanOrEqualTo(Long value) {
            addCriterion("sum_price <=", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceIn(List<Long> values) {
            addCriterion("sum_price in", values, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceNotIn(List<Long> values) {
            addCriterion("sum_price not in", values, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceBetween(Long value1, Long value2) {
            addCriterion("sum_price between", value1, value2, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceNotBetween(Long value1, Long value2) {
            addCriterion("sum_price not between", value1, value2, "sumPrice");
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