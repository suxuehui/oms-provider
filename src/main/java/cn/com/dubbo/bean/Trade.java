package cn.com.dubbo.bean;

import java.util.Date;
import java.util.List;

public class Trade {

	private String tid;
	private Date create_time;
	private String receiver_province;
	private String receiver_province_code;
	private String receiver_city;
	private String receiver_city_code;
	private String receiver_district;
	private String receiver_district_code;
	private String receiver_address;
	private String receiver_name;
	private String receiver_zip;
	private String receiver_mobile;
	private String receiver_phone;
	private String trade_from;
	private String buyer_nick;
	private String logistics_company_code;
	private String logistics_company;
	private String logistics_no;
	private Date delivery_time;
	private String post_fee;
	private String total_fee;
	private String discount_fee;
	private String payment;
	private String merchants_receivable_amount;
	private String third_party_fee_discount;
	private Date pay_time;
	private Date modify_time;
	private Date end_time;
	private String status;
	private String haveCFY;
	private String payment_type;
	private List<TradeDetail> trade_details;
	private String refund_id;
	private String customer_remark;
	private String vender_remark;
	private Invoice invoice;
	private String pdstate;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getReceiver_province() {
		return receiver_province;
	}
	public void setReceiver_province(String receiver_province) {
		this.receiver_province = receiver_province;
	}
	public String getReceiver_province_code() {
		return receiver_province_code;
	}
	public void setReceiver_province_code(String receiver_province_code) {
		this.receiver_province_code = receiver_province_code;
	}
	public String getReceiver_city() {
		return receiver_city;
	}
	public void setReceiver_city(String receiver_city) {
		this.receiver_city = receiver_city;
	}
	public String getReceiver_city_code() {
		return receiver_city_code;
	}
	public void setReceiver_city_code(String receiver_city_code) {
		this.receiver_city_code = receiver_city_code;
	}
	public String getReceiver_district() {
		return receiver_district;
	}
	public void setReceiver_district(String receiver_district) {
		this.receiver_district = receiver_district;
	}
	public String getReceiver_district_code() {
		return receiver_district_code;
	}
	public void setReceiver_district_code(String receiver_district_code) {
		this.receiver_district_code = receiver_district_code;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_zip() {
		return receiver_zip;
	}
	public void setReceiver_zip(String receiver_zip) {
		this.receiver_zip = receiver_zip;
	}
	public String getReceiver_mobile() {
		return receiver_mobile;
	}
	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getTrade_from() {
		return trade_from;
	}
	public void setTrade_from(String trade_from) {
		this.trade_from = trade_from;
	}
	public String getBuyer_nick() {
		return buyer_nick;
	}
	public void setBuyer_nick(String buyer_nick) {
		this.buyer_nick = buyer_nick;
	}
	public String getLogistics_company_code() {
		return logistics_company_code;
	}
	public void setLogistics_company_code(String logistics_company_code) {
		this.logistics_company_code = logistics_company_code;
	}
	public String getLogistics_company() {
		return logistics_company;
	}
	public void setLogistics_company(String logistics_company) {
		this.logistics_company = logistics_company;
	}
	public String getLogistics_no() {
		return logistics_no;
	}
	public void setLogistics_no(String logistics_no) {
		this.logistics_no = logistics_no;
	}
	public Date getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}
	public String getPost_fee() {
		return post_fee;
	}
	public void setPost_fee(String post_fee) {
		this.post_fee = post_fee;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getDiscount_fee() {
		return discount_fee;
	}
	public void setDiscount_fee(String discount_fee) {
		this.discount_fee = discount_fee;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getMerchants_receivable_amount() {
		return merchants_receivable_amount;
	}
	public void setMerchants_receivable_amount(String merchants_receivable_amount) {
		this.merchants_receivable_amount = merchants_receivable_amount;
	}
	public String getThird_party_fee_discount() {
		return third_party_fee_discount;
	}
	public void setThird_party_fee_discount(String third_party_fee_discount) {
		this.third_party_fee_discount = third_party_fee_discount;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHaveCFY() {
		return haveCFY;
	}
	public void setHaveCFY(String haveCFY) {
		this.haveCFY = haveCFY;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public List<TradeDetail> getTrade_details() {
		return trade_details;
	}
	public void setTrade_details(List<TradeDetail> trade_details) {
		this.trade_details = trade_details;
	}
	public String getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	public String getCustomer_remark() {
		return customer_remark;
	}
	public void setCustomer_remark(String customer_remark) {
		this.customer_remark = customer_remark;
	}
	public String getVender_remark() {
		return vender_remark;
	}
	public void setVender_remark(String vender_remark) {
		this.vender_remark = vender_remark;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public String getPdstate() {
		return pdstate;
	}
	public void setPdstate(String pdstate) {
		this.pdstate = pdstate;
	}
	
	
}
