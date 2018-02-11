package cn.com.dubbo.model2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class E_PstockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public E_PstockExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andPrice1IsNull() {
            addCriterion("Price1 is null");
            return (Criteria) this;
        }

        public Criteria andPrice1IsNotNull() {
            addCriterion("Price1 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice1EqualTo(BigDecimal value) {
            addCriterion("Price1 =", value, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice1NotEqualTo(BigDecimal value) {
            addCriterion("Price1 <>", value, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice1GreaterThan(BigDecimal value) {
            addCriterion("Price1 >", value, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice1GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Price1 >=", value, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice1LessThan(BigDecimal value) {
            addCriterion("Price1 <", value, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice1LessThanOrEqualTo(BigDecimal value) {
            addCriterion("Price1 <=", value, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice1In(List<BigDecimal> values) {
            addCriterion("Price1 in", values, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice1NotIn(List<BigDecimal> values) {
            addCriterion("Price1 not in", values, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice1Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price1 between", value1, value2, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice1NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price1 not between", value1, value2, "price1");
            return (Criteria) this;
        }

        public Criteria andPrice2IsNull() {
            addCriterion("Price2 is null");
            return (Criteria) this;
        }

        public Criteria andPrice2IsNotNull() {
            addCriterion("Price2 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice2EqualTo(BigDecimal value) {
            addCriterion("Price2 =", value, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice2NotEqualTo(BigDecimal value) {
            addCriterion("Price2 <>", value, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice2GreaterThan(BigDecimal value) {
            addCriterion("Price2 >", value, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Price2 >=", value, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice2LessThan(BigDecimal value) {
            addCriterion("Price2 <", value, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("Price2 <=", value, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice2In(List<BigDecimal> values) {
            addCriterion("Price2 in", values, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice2NotIn(List<BigDecimal> values) {
            addCriterion("Price2 not in", values, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price2 between", value1, value2, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price2 not between", value1, value2, "price2");
            return (Criteria) this;
        }

        public Criteria andPrice3IsNull() {
            addCriterion("Price3 is null");
            return (Criteria) this;
        }

        public Criteria andPrice3IsNotNull() {
            addCriterion("Price3 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice3EqualTo(BigDecimal value) {
            addCriterion("Price3 =", value, "price3");
            return (Criteria) this;
        }

        public Criteria andPrice3NotEqualTo(BigDecimal value) {
            addCriterion("Price3 <>", value, "price3");
            return (Criteria) this;
        }

        public Criteria andPrice3GreaterThan(BigDecimal value) {
            addCriterion("Price3 >", value, "price3");
            return (Criteria) this;
        }

        public Criteria andPrice3GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Price3 >=", value, "price3");
            return (Criteria) this;
        }

        public Criteria andPrice3LessThan(BigDecimal value) {
            addCriterion("Price3 <", value, "price3");
            return (Criteria) this;
        }

        public Criteria andPrice3LessThanOrEqualTo(BigDecimal value) {
            addCriterion("Price3 <=", value, "price3");
            return (Criteria) this;
        }

        public Criteria andPrice3In(List<BigDecimal> values) {
            addCriterion("Price3 in", values, "price3");
            return (Criteria) this;
        }

        public Criteria andPrice3NotIn(List<BigDecimal> values) {
            addCriterion("Price3 not in", values, "price3");
            return (Criteria) this;
        }

        public Criteria andPrice3Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price3 between", value1, value2, "price3");
            return (Criteria) this;
        }

        public Criteria andPrice3NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price3 not between", value1, value2, "price3");
            return (Criteria) this;
        }

        public Criteria andBatchnoIsNull() {
            addCriterion("batchno is null");
            return (Criteria) this;
        }

        public Criteria andBatchnoIsNotNull() {
            addCriterion("batchno is not null");
            return (Criteria) this;
        }

        public Criteria andBatchnoEqualTo(String value) {
            addCriterion("batchno =", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotEqualTo(String value) {
            addCriterion("batchno <>", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoGreaterThan(String value) {
            addCriterion("batchno >", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoGreaterThanOrEqualTo(String value) {
            addCriterion("batchno >=", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoLessThan(String value) {
            addCriterion("batchno <", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoLessThanOrEqualTo(String value) {
            addCriterion("batchno <=", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoLike(String value) {
            addCriterion("batchno like", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotLike(String value) {
            addCriterion("batchno not like", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoIn(List<String> values) {
            addCriterion("batchno in", values, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotIn(List<String> values) {
            addCriterion("batchno not in", values, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoBetween(String value1, String value2) {
            addCriterion("batchno between", value1, value2, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotBetween(String value1, String value2) {
            addCriterion("batchno not between", value1, value2, "batchno");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Long value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Long value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Long value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Long value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Long value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Long value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Long> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Long> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Long value1, Long value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Long value1, Long value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andMakedateIsNull() {
            addCriterion("makedate is null");
            return (Criteria) this;
        }

        public Criteria andMakedateIsNotNull() {
            addCriterion("makedate is not null");
            return (Criteria) this;
        }

        public Criteria andMakedateEqualTo(String value) {
            addCriterion("makedate =", value, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateNotEqualTo(String value) {
            addCriterion("makedate <>", value, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateGreaterThan(String value) {
            addCriterion("makedate >", value, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateGreaterThanOrEqualTo(String value) {
            addCriterion("makedate >=", value, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateLessThan(String value) {
            addCriterion("makedate <", value, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateLessThanOrEqualTo(String value) {
            addCriterion("makedate <=", value, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateLike(String value) {
            addCriterion("makedate like", value, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateNotLike(String value) {
            addCriterion("makedate not like", value, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateIn(List<String> values) {
            addCriterion("makedate in", values, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateNotIn(List<String> values) {
            addCriterion("makedate not in", values, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateBetween(String value1, String value2) {
            addCriterion("makedate between", value1, value2, "makedate");
            return (Criteria) this;
        }

        public Criteria andMakedateNotBetween(String value1, String value2) {
            addCriterion("makedate not between", value1, value2, "makedate");
            return (Criteria) this;
        }

        public Criteria andValiddateIsNull() {
            addCriterion("validdate is null");
            return (Criteria) this;
        }

        public Criteria andValiddateIsNotNull() {
            addCriterion("validdate is not null");
            return (Criteria) this;
        }

        public Criteria andValiddateEqualTo(String value) {
            addCriterion("validdate =", value, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateNotEqualTo(String value) {
            addCriterion("validdate <>", value, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateGreaterThan(String value) {
            addCriterion("validdate >", value, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateGreaterThanOrEqualTo(String value) {
            addCriterion("validdate >=", value, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateLessThan(String value) {
            addCriterion("validdate <", value, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateLessThanOrEqualTo(String value) {
            addCriterion("validdate <=", value, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateLike(String value) {
            addCriterion("validdate like", value, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateNotLike(String value) {
            addCriterion("validdate not like", value, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateIn(List<String> values) {
            addCriterion("validdate in", values, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateNotIn(List<String> values) {
            addCriterion("validdate not in", values, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateBetween(String value1, String value2) {
            addCriterion("validdate between", value1, value2, "validdate");
            return (Criteria) this;
        }

        public Criteria andValiddateNotBetween(String value1, String value2) {
            addCriterion("validdate not between", value1, value2, "validdate");
            return (Criteria) this;
        }

        public Criteria andInstoretimeIsNull() {
            addCriterion("Instoretime is null");
            return (Criteria) this;
        }

        public Criteria andInstoretimeIsNotNull() {
            addCriterion("Instoretime is not null");
            return (Criteria) this;
        }

        public Criteria andInstoretimeEqualTo(String value) {
            addCriterion("Instoretime =", value, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeNotEqualTo(String value) {
            addCriterion("Instoretime <>", value, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeGreaterThan(String value) {
            addCriterion("Instoretime >", value, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeGreaterThanOrEqualTo(String value) {
            addCriterion("Instoretime >=", value, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeLessThan(String value) {
            addCriterion("Instoretime <", value, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeLessThanOrEqualTo(String value) {
            addCriterion("Instoretime <=", value, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeLike(String value) {
            addCriterion("Instoretime like", value, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeNotLike(String value) {
            addCriterion("Instoretime not like", value, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeIn(List<String> values) {
            addCriterion("Instoretime in", values, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeNotIn(List<String> values) {
            addCriterion("Instoretime not in", values, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeBetween(String value1, String value2) {
            addCriterion("Instoretime between", value1, value2, "instoretime");
            return (Criteria) this;
        }

        public Criteria andInstoretimeNotBetween(String value1, String value2) {
            addCriterion("Instoretime not between", value1, value2, "instoretime");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNull() {
            addCriterion("supplier is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNotNull() {
            addCriterion("supplier is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEqualTo(String value) {
            addCriterion("supplier =", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotEqualTo(String value) {
            addCriterion("supplier <>", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThan(String value) {
            addCriterion("supplier >", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThanOrEqualTo(String value) {
            addCriterion("supplier >=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThan(String value) {
            addCriterion("supplier <", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThanOrEqualTo(String value) {
            addCriterion("supplier <=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLike(String value) {
            addCriterion("supplier like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotLike(String value) {
            addCriterion("supplier not like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierIn(List<String> values) {
            addCriterion("supplier in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotIn(List<String> values) {
            addCriterion("supplier not in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierBetween(String value1, String value2) {
            addCriterion("supplier between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotBetween(String value1, String value2) {
            addCriterion("supplier not between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andReduceAmountIsNull() {
            addCriterion("reduce_amount is null");
            return (Criteria) this;
        }

        public Criteria andReduceAmountIsNotNull() {
            addCriterion("reduce_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReduceAmountEqualTo(Integer value) {
            addCriterion("reduce_amount =", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountNotEqualTo(Integer value) {
            addCriterion("reduce_amount <>", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountGreaterThan(Integer value) {
            addCriterion("reduce_amount >", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("reduce_amount >=", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountLessThan(Integer value) {
            addCriterion("reduce_amount <", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountLessThanOrEqualTo(Integer value) {
            addCriterion("reduce_amount <=", value, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountIn(List<Integer> values) {
            addCriterion("reduce_amount in", values, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountNotIn(List<Integer> values) {
            addCriterion("reduce_amount not in", values, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountBetween(Integer value1, Integer value2) {
            addCriterion("reduce_amount between", value1, value2, "reduceAmount");
            return (Criteria) this;
        }

        public Criteria andReduceAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("reduce_amount not between", value1, value2, "reduceAmount");
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