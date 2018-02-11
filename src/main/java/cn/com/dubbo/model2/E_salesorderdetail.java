package cn.com.dubbo.model2;

import java.io.Serializable;
import java.math.BigDecimal;

public class E_salesorderdetail implements Serializable{
    private String eOrdernumber;

    private String pId;

    private Long quantity;

    private BigDecimal saleprice;

    private BigDecimal totalmoney;

    private String comment;

    private Integer deleted;

    public String geteOrdernumber() {
        return eOrdernumber;
    }

    public void seteOrdernumber(String eOrdernumber) {
        this.eOrdernumber = eOrdernumber == null ? null : eOrdernumber.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    public BigDecimal getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}