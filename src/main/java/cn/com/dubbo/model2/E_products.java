package cn.com.dubbo.model2;

import java.io.Serializable;

public class E_products implements Serializable{
    private String productId;

    private String name;

    private String alias;

    private String serialNumber;

    private String barcode;

    private String unit1Id;

    private String standard;

    private String packstd;

    private String factory;

    private String makearea;

    private String medtype;

    private String permitcode;

    private String gsppropert;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getUnit1Id() {
        return unit1Id;
    }

    public void setUnit1Id(String unit1Id) {
        this.unit1Id = unit1Id == null ? null : unit1Id.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public String getPackstd() {
        return packstd;
    }

    public void setPackstd(String packstd) {
        this.packstd = packstd == null ? null : packstd.trim();
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getMakearea() {
        return makearea;
    }

    public void setMakearea(String makearea) {
        this.makearea = makearea == null ? null : makearea.trim();
    }

    public String getMedtype() {
        return medtype;
    }

    public void setMedtype(String medtype) {
        this.medtype = medtype == null ? null : medtype.trim();
    }

    public String getPermitcode() {
        return permitcode;
    }

    public void setPermitcode(String permitcode) {
        this.permitcode = permitcode == null ? null : permitcode.trim();
    }

    public String getGsppropert() {
        return gsppropert;
    }

    public void setGsppropert(String gsppropert) {
        this.gsppropert = gsppropert == null ? null : gsppropert.trim();
    }
}