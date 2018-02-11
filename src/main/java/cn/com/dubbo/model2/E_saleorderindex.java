package cn.com.dubbo.model2;

import java.io.Serializable;

public class E_saleorderindex implements Serializable{
    private String eOrdernumber;

    private String cId;

    private String billdate;

    private Integer storeId;

    private String note;

    private Integer deleted;

    public String geteOrdernumber() {
        return eOrdernumber;
    }

    public void seteOrdernumber(String eOrdernumber) {
        this.eOrdernumber = eOrdernumber == null ? null : eOrdernumber.trim();
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId == null ? null : cId.trim();
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate == null ? null : billdate.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}