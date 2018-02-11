package cn.com.dubbo.model2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class E_productsExample implements Serializable{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public E_productsExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAliasIsNull() {
            addCriterion("alias is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("alias is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("alias =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("alias <>", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("alias >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("alias >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("alias <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("alias <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("alias like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("alias not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("alias in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("alias not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("alias between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("alias not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNull() {
            addCriterion("serial_number is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNotNull() {
            addCriterion("serial_number is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberEqualTo(String value) {
            addCriterion("serial_number =", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotEqualTo(String value) {
            addCriterion("serial_number <>", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThan(String value) {
            addCriterion("serial_number >", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThanOrEqualTo(String value) {
            addCriterion("serial_number >=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThan(String value) {
            addCriterion("serial_number <", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThanOrEqualTo(String value) {
            addCriterion("serial_number <=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLike(String value) {
            addCriterion("serial_number like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotLike(String value) {
            addCriterion("serial_number not like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIn(List<String> values) {
            addCriterion("serial_number in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotIn(List<String> values) {
            addCriterion("serial_number not in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberBetween(String value1, String value2) {
            addCriterion("serial_number between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotBetween(String value1, String value2) {
            addCriterion("serial_number not between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNull() {
            addCriterion("barcode is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNotNull() {
            addCriterion("barcode is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeEqualTo(String value) {
            addCriterion("barcode =", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotEqualTo(String value) {
            addCriterion("barcode <>", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThan(String value) {
            addCriterion("barcode >", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("barcode >=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThan(String value) {
            addCriterion("barcode <", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThanOrEqualTo(String value) {
            addCriterion("barcode <=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLike(String value) {
            addCriterion("barcode like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotLike(String value) {
            addCriterion("barcode not like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeIn(List<String> values) {
            addCriterion("barcode in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotIn(List<String> values) {
            addCriterion("barcode not in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeBetween(String value1, String value2) {
            addCriterion("barcode between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotBetween(String value1, String value2) {
            addCriterion("barcode not between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andUnit1IdIsNull() {
            addCriterion("unit1_id is null");
            return (Criteria) this;
        }

        public Criteria andUnit1IdIsNotNull() {
            addCriterion("unit1_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnit1IdEqualTo(String value) {
            addCriterion("unit1_id =", value, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdNotEqualTo(String value) {
            addCriterion("unit1_id <>", value, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdGreaterThan(String value) {
            addCriterion("unit1_id >", value, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdGreaterThanOrEqualTo(String value) {
            addCriterion("unit1_id >=", value, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdLessThan(String value) {
            addCriterion("unit1_id <", value, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdLessThanOrEqualTo(String value) {
            addCriterion("unit1_id <=", value, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdLike(String value) {
            addCriterion("unit1_id like", value, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdNotLike(String value) {
            addCriterion("unit1_id not like", value, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdIn(List<String> values) {
            addCriterion("unit1_id in", values, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdNotIn(List<String> values) {
            addCriterion("unit1_id not in", values, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdBetween(String value1, String value2) {
            addCriterion("unit1_id between", value1, value2, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andUnit1IdNotBetween(String value1, String value2) {
            addCriterion("unit1_id not between", value1, value2, "unit1Id");
            return (Criteria) this;
        }

        public Criteria andStandardIsNull() {
            addCriterion("standard is null");
            return (Criteria) this;
        }

        public Criteria andStandardIsNotNull() {
            addCriterion("standard is not null");
            return (Criteria) this;
        }

        public Criteria andStandardEqualTo(String value) {
            addCriterion("standard =", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotEqualTo(String value) {
            addCriterion("standard <>", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardGreaterThan(String value) {
            addCriterion("standard >", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardGreaterThanOrEqualTo(String value) {
            addCriterion("standard >=", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLessThan(String value) {
            addCriterion("standard <", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLessThanOrEqualTo(String value) {
            addCriterion("standard <=", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLike(String value) {
            addCriterion("standard like", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotLike(String value) {
            addCriterion("standard not like", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardIn(List<String> values) {
            addCriterion("standard in", values, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotIn(List<String> values) {
            addCriterion("standard not in", values, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardBetween(String value1, String value2) {
            addCriterion("standard between", value1, value2, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotBetween(String value1, String value2) {
            addCriterion("standard not between", value1, value2, "standard");
            return (Criteria) this;
        }

        public Criteria andPackstdIsNull() {
            addCriterion("PackStd is null");
            return (Criteria) this;
        }

        public Criteria andPackstdIsNotNull() {
            addCriterion("PackStd is not null");
            return (Criteria) this;
        }

        public Criteria andPackstdEqualTo(String value) {
            addCriterion("PackStd =", value, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdNotEqualTo(String value) {
            addCriterion("PackStd <>", value, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdGreaterThan(String value) {
            addCriterion("PackStd >", value, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdGreaterThanOrEqualTo(String value) {
            addCriterion("PackStd >=", value, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdLessThan(String value) {
            addCriterion("PackStd <", value, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdLessThanOrEqualTo(String value) {
            addCriterion("PackStd <=", value, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdLike(String value) {
            addCriterion("PackStd like", value, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdNotLike(String value) {
            addCriterion("PackStd not like", value, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdIn(List<String> values) {
            addCriterion("PackStd in", values, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdNotIn(List<String> values) {
            addCriterion("PackStd not in", values, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdBetween(String value1, String value2) {
            addCriterion("PackStd between", value1, value2, "packstd");
            return (Criteria) this;
        }

        public Criteria andPackstdNotBetween(String value1, String value2) {
            addCriterion("PackStd not between", value1, value2, "packstd");
            return (Criteria) this;
        }

        public Criteria andFactoryIsNull() {
            addCriterion("Factory is null");
            return (Criteria) this;
        }

        public Criteria andFactoryIsNotNull() {
            addCriterion("Factory is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryEqualTo(String value) {
            addCriterion("Factory =", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryNotEqualTo(String value) {
            addCriterion("Factory <>", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryGreaterThan(String value) {
            addCriterion("Factory >", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryGreaterThanOrEqualTo(String value) {
            addCriterion("Factory >=", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryLessThan(String value) {
            addCriterion("Factory <", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryLessThanOrEqualTo(String value) {
            addCriterion("Factory <=", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryLike(String value) {
            addCriterion("Factory like", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryNotLike(String value) {
            addCriterion("Factory not like", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryIn(List<String> values) {
            addCriterion("Factory in", values, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryNotIn(List<String> values) {
            addCriterion("Factory not in", values, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryBetween(String value1, String value2) {
            addCriterion("Factory between", value1, value2, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryNotBetween(String value1, String value2) {
            addCriterion("Factory not between", value1, value2, "factory");
            return (Criteria) this;
        }

        public Criteria andMakeareaIsNull() {
            addCriterion("makearea is null");
            return (Criteria) this;
        }

        public Criteria andMakeareaIsNotNull() {
            addCriterion("makearea is not null");
            return (Criteria) this;
        }

        public Criteria andMakeareaEqualTo(String value) {
            addCriterion("makearea =", value, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaNotEqualTo(String value) {
            addCriterion("makearea <>", value, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaGreaterThan(String value) {
            addCriterion("makearea >", value, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaGreaterThanOrEqualTo(String value) {
            addCriterion("makearea >=", value, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaLessThan(String value) {
            addCriterion("makearea <", value, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaLessThanOrEqualTo(String value) {
            addCriterion("makearea <=", value, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaLike(String value) {
            addCriterion("makearea like", value, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaNotLike(String value) {
            addCriterion("makearea not like", value, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaIn(List<String> values) {
            addCriterion("makearea in", values, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaNotIn(List<String> values) {
            addCriterion("makearea not in", values, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaBetween(String value1, String value2) {
            addCriterion("makearea between", value1, value2, "makearea");
            return (Criteria) this;
        }

        public Criteria andMakeareaNotBetween(String value1, String value2) {
            addCriterion("makearea not between", value1, value2, "makearea");
            return (Criteria) this;
        }

        public Criteria andMedtypeIsNull() {
            addCriterion("Medtype is null");
            return (Criteria) this;
        }

        public Criteria andMedtypeIsNotNull() {
            addCriterion("Medtype is not null");
            return (Criteria) this;
        }

        public Criteria andMedtypeEqualTo(String value) {
            addCriterion("Medtype =", value, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeNotEqualTo(String value) {
            addCriterion("Medtype <>", value, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeGreaterThan(String value) {
            addCriterion("Medtype >", value, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeGreaterThanOrEqualTo(String value) {
            addCriterion("Medtype >=", value, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeLessThan(String value) {
            addCriterion("Medtype <", value, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeLessThanOrEqualTo(String value) {
            addCriterion("Medtype <=", value, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeLike(String value) {
            addCriterion("Medtype like", value, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeNotLike(String value) {
            addCriterion("Medtype not like", value, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeIn(List<String> values) {
            addCriterion("Medtype in", values, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeNotIn(List<String> values) {
            addCriterion("Medtype not in", values, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeBetween(String value1, String value2) {
            addCriterion("Medtype between", value1, value2, "medtype");
            return (Criteria) this;
        }

        public Criteria andMedtypeNotBetween(String value1, String value2) {
            addCriterion("Medtype not between", value1, value2, "medtype");
            return (Criteria) this;
        }

        public Criteria andPermitcodeIsNull() {
            addCriterion("permitcode is null");
            return (Criteria) this;
        }

        public Criteria andPermitcodeIsNotNull() {
            addCriterion("permitcode is not null");
            return (Criteria) this;
        }

        public Criteria andPermitcodeEqualTo(String value) {
            addCriterion("permitcode =", value, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeNotEqualTo(String value) {
            addCriterion("permitcode <>", value, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeGreaterThan(String value) {
            addCriterion("permitcode >", value, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeGreaterThanOrEqualTo(String value) {
            addCriterion("permitcode >=", value, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeLessThan(String value) {
            addCriterion("permitcode <", value, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeLessThanOrEqualTo(String value) {
            addCriterion("permitcode <=", value, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeLike(String value) {
            addCriterion("permitcode like", value, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeNotLike(String value) {
            addCriterion("permitcode not like", value, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeIn(List<String> values) {
            addCriterion("permitcode in", values, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeNotIn(List<String> values) {
            addCriterion("permitcode not in", values, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeBetween(String value1, String value2) {
            addCriterion("permitcode between", value1, value2, "permitcode");
            return (Criteria) this;
        }

        public Criteria andPermitcodeNotBetween(String value1, String value2) {
            addCriterion("permitcode not between", value1, value2, "permitcode");
            return (Criteria) this;
        }

        public Criteria andGsppropertIsNull() {
            addCriterion("GSPPropert is null");
            return (Criteria) this;
        }

        public Criteria andGsppropertIsNotNull() {
            addCriterion("GSPPropert is not null");
            return (Criteria) this;
        }

        public Criteria andGsppropertEqualTo(String value) {
            addCriterion("GSPPropert =", value, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertNotEqualTo(String value) {
            addCriterion("GSPPropert <>", value, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertGreaterThan(String value) {
            addCriterion("GSPPropert >", value, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertGreaterThanOrEqualTo(String value) {
            addCriterion("GSPPropert >=", value, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertLessThan(String value) {
            addCriterion("GSPPropert <", value, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertLessThanOrEqualTo(String value) {
            addCriterion("GSPPropert <=", value, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertLike(String value) {
            addCriterion("GSPPropert like", value, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertNotLike(String value) {
            addCriterion("GSPPropert not like", value, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertIn(List<String> values) {
            addCriterion("GSPPropert in", values, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertNotIn(List<String> values) {
            addCriterion("GSPPropert not in", values, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertBetween(String value1, String value2) {
            addCriterion("GSPPropert between", value1, value2, "gsppropert");
            return (Criteria) this;
        }

        public Criteria andGsppropertNotBetween(String value1, String value2) {
            addCriterion("GSPPropert not between", value1, value2, "gsppropert");
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