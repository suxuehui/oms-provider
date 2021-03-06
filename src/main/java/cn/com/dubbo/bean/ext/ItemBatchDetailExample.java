package cn.com.dubbo.bean.ext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemBatchDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemBatchDetailExample() {
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

        public Criteria andOrderDataIdIsNull() {
            addCriterion("order_data_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdIsNotNull() {
            addCriterion("order_data_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdEqualTo(Integer value) {
            addCriterion("order_data_id =", value, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdNotEqualTo(Integer value) {
            addCriterion("order_data_id <>", value, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdGreaterThan(Integer value) {
            addCriterion("order_data_id >", value, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_data_id >=", value, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdLessThan(Integer value) {
            addCriterion("order_data_id <", value, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_data_id <=", value, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdIn(List<Integer> values) {
            addCriterion("order_data_id in", values, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdNotIn(List<Integer> values) {
            addCriterion("order_data_id not in", values, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdBetween(Integer value1, Integer value2) {
            addCriterion("order_data_id between", value1, value2, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderDataIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_data_id not between", value1, value2, "orderDataId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIsNull() {
            addCriterion("order_item_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIsNotNull() {
            addCriterion("order_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdEqualTo(Long value) {
            addCriterion("order_item_id =", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotEqualTo(Long value) {
            addCriterion("order_item_id <>", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThan(Long value) {
            addCriterion("order_item_id >", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_item_id >=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThan(Long value) {
            addCriterion("order_item_id <", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThanOrEqualTo(Long value) {
            addCriterion("order_item_id <=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIn(List<Long> values) {
            addCriterion("order_item_id in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotIn(List<Long> values) {
            addCriterion("order_item_id not in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdBetween(Long value1, Long value2) {
            addCriterion("order_item_id between", value1, value2, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotBetween(Long value1, Long value2) {
            addCriterion("order_item_id not between", value1, value2, "orderItemId");
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

        public Criteria andGoodsNoIsNull() {
            addCriterion("goods_no is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNoIsNotNull() {
            addCriterion("goods_no is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNoEqualTo(String value) {
            addCriterion("goods_no =", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotEqualTo(String value) {
            addCriterion("goods_no <>", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoGreaterThan(String value) {
            addCriterion("goods_no >", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoGreaterThanOrEqualTo(String value) {
            addCriterion("goods_no >=", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoLessThan(String value) {
            addCriterion("goods_no <", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoLessThanOrEqualTo(String value) {
            addCriterion("goods_no <=", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoLike(String value) {
            addCriterion("goods_no like", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotLike(String value) {
            addCriterion("goods_no not like", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoIn(List<String> values) {
            addCriterion("goods_no in", values, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotIn(List<String> values) {
            addCriterion("goods_no not in", values, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoBetween(String value1, String value2) {
            addCriterion("goods_no between", value1, value2, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotBetween(String value1, String value2) {
            addCriterion("goods_no not between", value1, value2, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69IsNull() {
            addCriterion("goods_no_69 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69IsNotNull() {
            addCriterion("goods_no_69 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69EqualTo(String value) {
            addCriterion("goods_no_69 =", value, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69NotEqualTo(String value) {
            addCriterion("goods_no_69 <>", value, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69GreaterThan(String value) {
            addCriterion("goods_no_69 >", value, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69GreaterThanOrEqualTo(String value) {
            addCriterion("goods_no_69 >=", value, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69LessThan(String value) {
            addCriterion("goods_no_69 <", value, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69LessThanOrEqualTo(String value) {
            addCriterion("goods_no_69 <=", value, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69Like(String value) {
            addCriterion("goods_no_69 like", value, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69NotLike(String value) {
            addCriterion("goods_no_69 not like", value, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69In(List<String> values) {
            addCriterion("goods_no_69 in", values, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69NotIn(List<String> values) {
            addCriterion("goods_no_69 not in", values, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69Between(String value1, String value2) {
            addCriterion("goods_no_69 between", value1, value2, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andGoodsNo69NotBetween(String value1, String value2) {
            addCriterion("goods_no_69 not between", value1, value2, "goodsNo69");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNull() {
            addCriterion("cost_price is null");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNotNull() {
            addCriterion("cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andCostPriceEqualTo(BigDecimal value) {
            addCriterion("cost_price =", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotEqualTo(BigDecimal value) {
            addCriterion("cost_price <>", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThan(BigDecimal value) {
            addCriterion("cost_price >", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_price >=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThan(BigDecimal value) {
            addCriterion("cost_price <", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_price <=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceIn(List<BigDecimal> values) {
            addCriterion("cost_price in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotIn(List<BigDecimal> values) {
            addCriterion("cost_price not in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_price between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_price not between", value1, value2, "costPrice");
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

        public Criteria andGoodNameIsNull() {
            addCriterion("good_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodNameIsNotNull() {
            addCriterion("good_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodNameEqualTo(String value) {
            addCriterion("good_name =", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameNotEqualTo(String value) {
            addCriterion("good_name <>", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameGreaterThan(String value) {
            addCriterion("good_name >", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameGreaterThanOrEqualTo(String value) {
            addCriterion("good_name >=", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameLessThan(String value) {
            addCriterion("good_name <", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameLessThanOrEqualTo(String value) {
            addCriterion("good_name <=", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameLike(String value) {
            addCriterion("good_name like", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameNotLike(String value) {
            addCriterion("good_name not like", value, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameIn(List<String> values) {
            addCriterion("good_name in", values, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameNotIn(List<String> values) {
            addCriterion("good_name not in", values, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameBetween(String value1, String value2) {
            addCriterion("good_name between", value1, value2, "goodName");
            return (Criteria) this;
        }

        public Criteria andGoodNameNotBetween(String value1, String value2) {
            addCriterion("good_name not between", value1, value2, "goodName");
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

        public Criteria andAmountEqualTo(Long value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Long value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Long value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Long value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Long value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Long value1, Long value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Long value1, Long value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andStockStatusIsNull() {
            addCriterion("stock_status is null");
            return (Criteria) this;
        }

        public Criteria andStockStatusIsNotNull() {
            addCriterion("stock_status is not null");
            return (Criteria) this;
        }

        public Criteria andStockStatusEqualTo(String value) {
            addCriterion("stock_status =", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotEqualTo(String value) {
            addCriterion("stock_status <>", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusGreaterThan(String value) {
            addCriterion("stock_status >", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusGreaterThanOrEqualTo(String value) {
            addCriterion("stock_status >=", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusLessThan(String value) {
            addCriterion("stock_status <", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusLessThanOrEqualTo(String value) {
            addCriterion("stock_status <=", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusLike(String value) {
            addCriterion("stock_status like", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotLike(String value) {
            addCriterion("stock_status not like", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusIn(List<String> values) {
            addCriterion("stock_status in", values, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotIn(List<String> values) {
            addCriterion("stock_status not in", values, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusBetween(String value1, String value2) {
            addCriterion("stock_status between", value1, value2, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotBetween(String value1, String value2) {
            addCriterion("stock_status not between", value1, value2, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchIsNull() {
            addCriterion("goods_batch is null");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchIsNotNull() {
            addCriterion("goods_batch is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchEqualTo(String value) {
            addCriterion("goods_batch =", value, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchNotEqualTo(String value) {
            addCriterion("goods_batch <>", value, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchGreaterThan(String value) {
            addCriterion("goods_batch >", value, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchGreaterThanOrEqualTo(String value) {
            addCriterion("goods_batch >=", value, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchLessThan(String value) {
            addCriterion("goods_batch <", value, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchLessThanOrEqualTo(String value) {
            addCriterion("goods_batch <=", value, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchLike(String value) {
            addCriterion("goods_batch like", value, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchNotLike(String value) {
            addCriterion("goods_batch not like", value, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchIn(List<String> values) {
            addCriterion("goods_batch in", values, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchNotIn(List<String> values) {
            addCriterion("goods_batch not in", values, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchBetween(String value1, String value2) {
            addCriterion("goods_batch between", value1, value2, "goodsBatch");
            return (Criteria) this;
        }

        public Criteria andGoodsBatchNotBetween(String value1, String value2) {
            addCriterion("goods_batch not between", value1, value2, "goodsBatch");
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

        public Criteria andSummaryIsNull() {
            addCriterion("summary is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNotNull() {
            addCriterion("summary is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryEqualTo(String value) {
            addCriterion("summary =", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotEqualTo(String value) {
            addCriterion("summary <>", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThan(String value) {
            addCriterion("summary >", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("summary >=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThan(String value) {
            addCriterion("summary <", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThanOrEqualTo(String value) {
            addCriterion("summary <=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLike(String value) {
            addCriterion("summary like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotLike(String value) {
            addCriterion("summary not like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryIn(List<String> values) {
            addCriterion("summary in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotIn(List<String> values) {
            addCriterion("summary not in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryBetween(String value1, String value2) {
            addCriterion("summary between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotBetween(String value1, String value2) {
            addCriterion("summary not between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andIndexIdIsNull() {
            addCriterion("index_id is null");
            return (Criteria) this;
        }

        public Criteria andIndexIdIsNotNull() {
            addCriterion("index_id is not null");
            return (Criteria) this;
        }

        public Criteria andIndexIdEqualTo(Integer value) {
            addCriterion("index_id =", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotEqualTo(Integer value) {
            addCriterion("index_id <>", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdGreaterThan(Integer value) {
            addCriterion("index_id >", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("index_id >=", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdLessThan(Integer value) {
            addCriterion("index_id <", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdLessThanOrEqualTo(Integer value) {
            addCriterion("index_id <=", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdIn(List<Integer> values) {
            addCriterion("index_id in", values, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotIn(List<Integer> values) {
            addCriterion("index_id not in", values, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdBetween(Integer value1, Integer value2) {
            addCriterion("index_id between", value1, value2, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotBetween(Integer value1, Integer value2) {
            addCriterion("index_id not between", value1, value2, "indexId");
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