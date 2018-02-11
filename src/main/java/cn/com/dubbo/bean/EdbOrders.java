package cn.com.dubbo.bean;

import java.util.Date;

public class EdbOrders {
    private Long id;

    private Byte storageId;

    private String tid;

    private String transactionId;

    private String customerId;

    private String distributorId;

    private String shopName;

    private String outTid;

    private String outPayTid;

    private String voucherId;

    private Byte shopid;

    private String serialNum;

    private String orderChannel;

    private String orderFrom;

    private String buyerId;

    private String buyerName;

    private String type;

    private String status;

    private String abnormalStatus;

    private String mergeStatus;

    private String receiverName;

    private String receiverMobile;

    private String phone;

    private String province;

    private String city;

    private String district;

    private String address;

    private String post;

    private String email;

    private Byte isBill;

    private String invoiceName;

    private String invoiceSituation;

    private String invoiceTitle;

    private String invoiceType;

    private String invoiceContent;

    private String proTotalfee;

    private String orderTotalfee;

    private String referencePricePaid;

    private String invoiceFee;

    private String codFee;

    private String otherFee;

    private String refundTotalfee;

    private String discountFee;

    private String discount;

    private String channelDisfee;

    private String merchantDisfee;

    private String orderDisfee;

    private String commissionFee;

    private String isCod;

    private String pointPay;

    private String costPoint;

    private String point;

    private String superiorPoint;

    private String royaltyFee;

    private String externalPoint;

    private String expressNo;

    private String express;

    private String expressCoding;

    private String onlineExpress;

    private String sendingType;

    private String realIncomeFreight;

    private String realPayFreight;

    private String grossWeightFreight;

    private String netWeightFreight;

    private String freightExplain;

    private String totalWeight;

    private String tidNetWeight;

    private String tidTime;

    private String payTimeBak;

    private String getTime;

    private String orderCreater;

    private String businessMan;

    private String paymentReceivedOperator;

    private String paymentReceivedTime;

    private String reviewOrdersTime;

    private String reviewOrdersOperator;

    private String financeReviewOperator;

    private String financeReviewTime;

    private String advancePrinter;

    private String printer;

    private String printTime;

    private String isPrint;

    private String advDistributer;

    private String advDistributTime;

    private String distributer;

    private String distributTime;

    private String isInspection;

    private String inspecter;

    private String inspectTime;

    private String cancelOperator;

    private String cancelTime;

    private String revokeCancelEr;

    private String revokeCancelTime;

    private String packager;

    private String packTime;

    private String weighOperator;

    private String weighTime;

    private String bookDeliveryTime;

    private String deliveryOperator;

    private String deliveryTime;

    private String locker;

    private String lockTime;

    private String bookFileTime;

    private String fileOperator;

    private String fileTime;

    private String finishTime;

    private String modityTime;

    private String isPromotion;

    private String promotionPlan;

    private String outPromotionDetail;

    private String goodReceiveTime;

    private String receiveTime;

    private String verificatyTime;

    private String enableInteStoTime;

    private String enableInteDeliveryTime;

    private String alipayId;

    private String alipayStatus;

    private String payMothed;

    private String payStatus;

    private String platformStatus;

    private String rate;

    private String currency;

    private String deliveryStatus;

    private String buyerMessage;

    private String serviceRemarks;

    private String innerLable;

    private String distributorMark;

    private String systemRemarks;

    private String otherRemarks;

    private String message;

    private String messageTime;

    private String isStock;

    private String relatedOrders;

    private String relatedOrdersType;

    private String importMark;

    private String deliveryName;

    private String isNewCustomer;

    private String distributorLevel;

    private String codServiceFee;

    private String expressColFee;

    private String productNum;

    private String sku;

    private String itemNum;

    private String singleNum;

    private String flagColor;

    private String isFlag;

    private String taobaoDeliveryOrderStatus;

    private String taobaoDeliveryStatus;

    private String taobaoDeliveryMethod;

    private String orderProcessTime;

    private String isBreak;

    private String breaker;

    private String breakTime;

    private String breakExplain;

    private Byte platSendStatus;

    private String platType;

    private String isAdvSale;

    private String provincCode;

    private String cityCode;

    private String areaCode;

    private String expressCode;

    private String lastReturnedTime;

    private String lastRefundTime;

    private String deliverCentre;

    private String deliverStation;

    private String isPreDeliveryNotice;

    private String jdDeliveryTime;

    private String sortingCode;

    private String codSettlementVouchernumber;

    private Integer totalNum;

    private String origincode;

    private String destcode;

    private String bigMarker;

    private Date payTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getStorageId() {
        return storageId;
    }

    public void setStorageId(Byte storageId) {
        this.storageId = storageId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId == null ? null : distributorId.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getOutTid() {
        return outTid;
    }

    public void setOutTid(String outTid) {
        this.outTid = outTid == null ? null : outTid.trim();
    }

    public String getOutPayTid() {
        return outPayTid;
    }

    public void setOutPayTid(String outPayTid) {
        this.outPayTid = outPayTid == null ? null : outPayTid.trim();
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId == null ? null : voucherId.trim();
    }

    public Byte getShopid() {
        return shopid;
    }

    public void setShopid(Byte shopid) {
        this.shopid = shopid;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel == null ? null : orderChannel.trim();
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom == null ? null : orderFrom.trim();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAbnormalStatus() {
        return abnormalStatus;
    }

    public void setAbnormalStatus(String abnormalStatus) {
        this.abnormalStatus = abnormalStatus == null ? null : abnormalStatus.trim();
    }

    public String getMergeStatus() {
        return mergeStatus;
    }

    public void setMergeStatus(String mergeStatus) {
        this.mergeStatus = mergeStatus == null ? null : mergeStatus.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getIsBill() {
        return isBill;
    }

    public void setIsBill(Byte isBill) {
        this.isBill = isBill;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName == null ? null : invoiceName.trim();
    }

    public String getInvoiceSituation() {
        return invoiceSituation;
    }

    public void setInvoiceSituation(String invoiceSituation) {
        this.invoiceSituation = invoiceSituation == null ? null : invoiceSituation.trim();
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent == null ? null : invoiceContent.trim();
    }

    public String getProTotalfee() {
        return proTotalfee;
    }

    public void setProTotalfee(String proTotalfee) {
        this.proTotalfee = proTotalfee == null ? null : proTotalfee.trim();
    }

    public String getOrderTotalfee() {
        return orderTotalfee;
    }

    public void setOrderTotalfee(String orderTotalfee) {
        this.orderTotalfee = orderTotalfee == null ? null : orderTotalfee.trim();
    }

    public String getReferencePricePaid() {
        return referencePricePaid;
    }

    public void setReferencePricePaid(String referencePricePaid) {
        this.referencePricePaid = referencePricePaid == null ? null : referencePricePaid.trim();
    }

    public String getInvoiceFee() {
        return invoiceFee;
    }

    public void setInvoiceFee(String invoiceFee) {
        this.invoiceFee = invoiceFee == null ? null : invoiceFee.trim();
    }

    public String getCodFee() {
        return codFee;
    }

    public void setCodFee(String codFee) {
        this.codFee = codFee == null ? null : codFee.trim();
    }

    public String getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee == null ? null : otherFee.trim();
    }

    public String getRefundTotalfee() {
        return refundTotalfee;
    }

    public void setRefundTotalfee(String refundTotalfee) {
        this.refundTotalfee = refundTotalfee == null ? null : refundTotalfee.trim();
    }

    public String getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(String discountFee) {
        this.discountFee = discountFee == null ? null : discountFee.trim();
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount == null ? null : discount.trim();
    }

    public String getChannelDisfee() {
        return channelDisfee;
    }

    public void setChannelDisfee(String channelDisfee) {
        this.channelDisfee = channelDisfee == null ? null : channelDisfee.trim();
    }

    public String getMerchantDisfee() {
        return merchantDisfee;
    }

    public void setMerchantDisfee(String merchantDisfee) {
        this.merchantDisfee = merchantDisfee == null ? null : merchantDisfee.trim();
    }

    public String getOrderDisfee() {
        return orderDisfee;
    }

    public void setOrderDisfee(String orderDisfee) {
        this.orderDisfee = orderDisfee == null ? null : orderDisfee.trim();
    }

    public String getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(String commissionFee) {
        this.commissionFee = commissionFee == null ? null : commissionFee.trim();
    }

    public String getIsCod() {
        return isCod;
    }

    public void setIsCod(String isCod) {
        this.isCod = isCod == null ? null : isCod.trim();
    }

    public String getPointPay() {
        return pointPay;
    }

    public void setPointPay(String pointPay) {
        this.pointPay = pointPay == null ? null : pointPay.trim();
    }

    public String getCostPoint() {
        return costPoint;
    }

    public void setCostPoint(String costPoint) {
        this.costPoint = costPoint == null ? null : costPoint.trim();
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point == null ? null : point.trim();
    }

    public String getSuperiorPoint() {
        return superiorPoint;
    }

    public void setSuperiorPoint(String superiorPoint) {
        this.superiorPoint = superiorPoint == null ? null : superiorPoint.trim();
    }

    public String getRoyaltyFee() {
        return royaltyFee;
    }

    public void setRoyaltyFee(String royaltyFee) {
        this.royaltyFee = royaltyFee == null ? null : royaltyFee.trim();
    }

    public String getExternalPoint() {
        return externalPoint;
    }

    public void setExternalPoint(String externalPoint) {
        this.externalPoint = externalPoint == null ? null : externalPoint.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    public String getExpressCoding() {
        return expressCoding;
    }

    public void setExpressCoding(String expressCoding) {
        this.expressCoding = expressCoding == null ? null : expressCoding.trim();
    }

    public String getOnlineExpress() {
        return onlineExpress;
    }

    public void setOnlineExpress(String onlineExpress) {
        this.onlineExpress = onlineExpress == null ? null : onlineExpress.trim();
    }

    public String getSendingType() {
        return sendingType;
    }

    public void setSendingType(String sendingType) {
        this.sendingType = sendingType == null ? null : sendingType.trim();
    }

    public String getRealIncomeFreight() {
        return realIncomeFreight;
    }

    public void setRealIncomeFreight(String realIncomeFreight) {
        this.realIncomeFreight = realIncomeFreight == null ? null : realIncomeFreight.trim();
    }

    public String getRealPayFreight() {
        return realPayFreight;
    }

    public void setRealPayFreight(String realPayFreight) {
        this.realPayFreight = realPayFreight == null ? null : realPayFreight.trim();
    }

    public String getGrossWeightFreight() {
        return grossWeightFreight;
    }

    public void setGrossWeightFreight(String grossWeightFreight) {
        this.grossWeightFreight = grossWeightFreight == null ? null : grossWeightFreight.trim();
    }

    public String getNetWeightFreight() {
        return netWeightFreight;
    }

    public void setNetWeightFreight(String netWeightFreight) {
        this.netWeightFreight = netWeightFreight == null ? null : netWeightFreight.trim();
    }

    public String getFreightExplain() {
        return freightExplain;
    }

    public void setFreightExplain(String freightExplain) {
        this.freightExplain = freightExplain == null ? null : freightExplain.trim();
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight == null ? null : totalWeight.trim();
    }

    public String getTidNetWeight() {
        return tidNetWeight;
    }

    public void setTidNetWeight(String tidNetWeight) {
        this.tidNetWeight = tidNetWeight == null ? null : tidNetWeight.trim();
    }

    public String getTidTime() {
        return tidTime;
    }

    public void setTidTime(String tidTime) {
        this.tidTime = tidTime == null ? null : tidTime.trim();
    }

    public String getPayTimeBak() {
        return payTimeBak;
    }

    public void setPayTimeBak(String payTimeBak) {
        this.payTimeBak = payTimeBak == null ? null : payTimeBak.trim();
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime == null ? null : getTime.trim();
    }

    public String getOrderCreater() {
        return orderCreater;
    }

    public void setOrderCreater(String orderCreater) {
        this.orderCreater = orderCreater == null ? null : orderCreater.trim();
    }

    public String getBusinessMan() {
        return businessMan;
    }

    public void setBusinessMan(String businessMan) {
        this.businessMan = businessMan == null ? null : businessMan.trim();
    }

    public String getPaymentReceivedOperator() {
        return paymentReceivedOperator;
    }

    public void setPaymentReceivedOperator(String paymentReceivedOperator) {
        this.paymentReceivedOperator = paymentReceivedOperator == null ? null : paymentReceivedOperator.trim();
    }

    public String getPaymentReceivedTime() {
        return paymentReceivedTime;
    }

    public void setPaymentReceivedTime(String paymentReceivedTime) {
        this.paymentReceivedTime = paymentReceivedTime == null ? null : paymentReceivedTime.trim();
    }

    public String getReviewOrdersTime() {
        return reviewOrdersTime;
    }

    public void setReviewOrdersTime(String reviewOrdersTime) {
        this.reviewOrdersTime = reviewOrdersTime == null ? null : reviewOrdersTime.trim();
    }

    public String getReviewOrdersOperator() {
        return reviewOrdersOperator;
    }

    public void setReviewOrdersOperator(String reviewOrdersOperator) {
        this.reviewOrdersOperator = reviewOrdersOperator == null ? null : reviewOrdersOperator.trim();
    }

    public String getFinanceReviewOperator() {
        return financeReviewOperator;
    }

    public void setFinanceReviewOperator(String financeReviewOperator) {
        this.financeReviewOperator = financeReviewOperator == null ? null : financeReviewOperator.trim();
    }

    public String getFinanceReviewTime() {
        return financeReviewTime;
    }

    public void setFinanceReviewTime(String financeReviewTime) {
        this.financeReviewTime = financeReviewTime == null ? null : financeReviewTime.trim();
    }

    public String getAdvancePrinter() {
        return advancePrinter;
    }

    public void setAdvancePrinter(String advancePrinter) {
        this.advancePrinter = advancePrinter == null ? null : advancePrinter.trim();
    }

    public String getPrinter() {
        return printer;
    }

    public void setPrinter(String printer) {
        this.printer = printer == null ? null : printer.trim();
    }

    public String getPrintTime() {
        return printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime == null ? null : printTime.trim();
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint == null ? null : isPrint.trim();
    }

    public String getAdvDistributer() {
        return advDistributer;
    }

    public void setAdvDistributer(String advDistributer) {
        this.advDistributer = advDistributer == null ? null : advDistributer.trim();
    }

    public String getAdvDistributTime() {
        return advDistributTime;
    }

    public void setAdvDistributTime(String advDistributTime) {
        this.advDistributTime = advDistributTime == null ? null : advDistributTime.trim();
    }

    public String getDistributer() {
        return distributer;
    }

    public void setDistributer(String distributer) {
        this.distributer = distributer == null ? null : distributer.trim();
    }

    public String getDistributTime() {
        return distributTime;
    }

    public void setDistributTime(String distributTime) {
        this.distributTime = distributTime == null ? null : distributTime.trim();
    }

    public String getIsInspection() {
        return isInspection;
    }

    public void setIsInspection(String isInspection) {
        this.isInspection = isInspection == null ? null : isInspection.trim();
    }

    public String getInspecter() {
        return inspecter;
    }

    public void setInspecter(String inspecter) {
        this.inspecter = inspecter == null ? null : inspecter.trim();
    }

    public String getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime == null ? null : inspectTime.trim();
    }

    public String getCancelOperator() {
        return cancelOperator;
    }

    public void setCancelOperator(String cancelOperator) {
        this.cancelOperator = cancelOperator == null ? null : cancelOperator.trim();
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime == null ? null : cancelTime.trim();
    }

    public String getRevokeCancelEr() {
        return revokeCancelEr;
    }

    public void setRevokeCancelEr(String revokeCancelEr) {
        this.revokeCancelEr = revokeCancelEr == null ? null : revokeCancelEr.trim();
    }

    public String getRevokeCancelTime() {
        return revokeCancelTime;
    }

    public void setRevokeCancelTime(String revokeCancelTime) {
        this.revokeCancelTime = revokeCancelTime == null ? null : revokeCancelTime.trim();
    }

    public String getPackager() {
        return packager;
    }

    public void setPackager(String packager) {
        this.packager = packager == null ? null : packager.trim();
    }

    public String getPackTime() {
        return packTime;
    }

    public void setPackTime(String packTime) {
        this.packTime = packTime == null ? null : packTime.trim();
    }

    public String getWeighOperator() {
        return weighOperator;
    }

    public void setWeighOperator(String weighOperator) {
        this.weighOperator = weighOperator == null ? null : weighOperator.trim();
    }

    public String getWeighTime() {
        return weighTime;
    }

    public void setWeighTime(String weighTime) {
        this.weighTime = weighTime == null ? null : weighTime.trim();
    }

    public String getBookDeliveryTime() {
        return bookDeliveryTime;
    }

    public void setBookDeliveryTime(String bookDeliveryTime) {
        this.bookDeliveryTime = bookDeliveryTime == null ? null : bookDeliveryTime.trim();
    }

    public String getDeliveryOperator() {
        return deliveryOperator;
    }

    public void setDeliveryOperator(String deliveryOperator) {
        this.deliveryOperator = deliveryOperator == null ? null : deliveryOperator.trim();
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime == null ? null : deliveryTime.trim();
    }

    public String getLocker() {
        return locker;
    }

    public void setLocker(String locker) {
        this.locker = locker == null ? null : locker.trim();
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime == null ? null : lockTime.trim();
    }

    public String getBookFileTime() {
        return bookFileTime;
    }

    public void setBookFileTime(String bookFileTime) {
        this.bookFileTime = bookFileTime == null ? null : bookFileTime.trim();
    }

    public String getFileOperator() {
        return fileOperator;
    }

    public void setFileOperator(String fileOperator) {
        this.fileOperator = fileOperator == null ? null : fileOperator.trim();
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime == null ? null : fileTime.trim();
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime == null ? null : finishTime.trim();
    }

    public String getModityTime() {
        return modityTime;
    }

    public void setModityTime(String modityTime) {
        this.modityTime = modityTime == null ? null : modityTime.trim();
    }

    public String getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(String isPromotion) {
        this.isPromotion = isPromotion == null ? null : isPromotion.trim();
    }

    public String getPromotionPlan() {
        return promotionPlan;
    }

    public void setPromotionPlan(String promotionPlan) {
        this.promotionPlan = promotionPlan == null ? null : promotionPlan.trim();
    }

    public String getOutPromotionDetail() {
        return outPromotionDetail;
    }

    public void setOutPromotionDetail(String outPromotionDetail) {
        this.outPromotionDetail = outPromotionDetail == null ? null : outPromotionDetail.trim();
    }

    public String getGoodReceiveTime() {
        return goodReceiveTime;
    }

    public void setGoodReceiveTime(String goodReceiveTime) {
        this.goodReceiveTime = goodReceiveTime == null ? null : goodReceiveTime.trim();
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime == null ? null : receiveTime.trim();
    }

    public String getVerificatyTime() {
        return verificatyTime;
    }

    public void setVerificatyTime(String verificatyTime) {
        this.verificatyTime = verificatyTime == null ? null : verificatyTime.trim();
    }

    public String getEnableInteStoTime() {
        return enableInteStoTime;
    }

    public void setEnableInteStoTime(String enableInteStoTime) {
        this.enableInteStoTime = enableInteStoTime == null ? null : enableInteStoTime.trim();
    }

    public String getEnableInteDeliveryTime() {
        return enableInteDeliveryTime;
    }

    public void setEnableInteDeliveryTime(String enableInteDeliveryTime) {
        this.enableInteDeliveryTime = enableInteDeliveryTime == null ? null : enableInteDeliveryTime.trim();
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId == null ? null : alipayId.trim();
    }

    public String getAlipayStatus() {
        return alipayStatus;
    }

    public void setAlipayStatus(String alipayStatus) {
        this.alipayStatus = alipayStatus == null ? null : alipayStatus.trim();
    }

    public String getPayMothed() {
        return payMothed;
    }

    public void setPayMothed(String payMothed) {
        this.payMothed = payMothed == null ? null : payMothed.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPlatformStatus() {
        return platformStatus;
    }

    public void setPlatformStatus(String platformStatus) {
        this.platformStatus = platformStatus == null ? null : platformStatus.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus == null ? null : deliveryStatus.trim();
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    public String getServiceRemarks() {
        return serviceRemarks;
    }

    public void setServiceRemarks(String serviceRemarks) {
        this.serviceRemarks = serviceRemarks == null ? null : serviceRemarks.trim();
    }

    public String getInnerLable() {
        return innerLable;
    }

    public void setInnerLable(String innerLable) {
        this.innerLable = innerLable == null ? null : innerLable.trim();
    }

    public String getDistributorMark() {
        return distributorMark;
    }

    public void setDistributorMark(String distributorMark) {
        this.distributorMark = distributorMark == null ? null : distributorMark.trim();
    }

    public String getSystemRemarks() {
        return systemRemarks;
    }

    public void setSystemRemarks(String systemRemarks) {
        this.systemRemarks = systemRemarks == null ? null : systemRemarks.trim();
    }

    public String getOtherRemarks() {
        return otherRemarks;
    }

    public void setOtherRemarks(String otherRemarks) {
        this.otherRemarks = otherRemarks == null ? null : otherRemarks.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime == null ? null : messageTime.trim();
    }

    public String getIsStock() {
        return isStock;
    }

    public void setIsStock(String isStock) {
        this.isStock = isStock == null ? null : isStock.trim();
    }

    public String getRelatedOrders() {
        return relatedOrders;
    }

    public void setRelatedOrders(String relatedOrders) {
        this.relatedOrders = relatedOrders == null ? null : relatedOrders.trim();
    }

    public String getRelatedOrdersType() {
        return relatedOrdersType;
    }

    public void setRelatedOrdersType(String relatedOrdersType) {
        this.relatedOrdersType = relatedOrdersType == null ? null : relatedOrdersType.trim();
    }

    public String getImportMark() {
        return importMark;
    }

    public void setImportMark(String importMark) {
        this.importMark = importMark == null ? null : importMark.trim();
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName == null ? null : deliveryName.trim();
    }

    public String getIsNewCustomer() {
        return isNewCustomer;
    }

    public void setIsNewCustomer(String isNewCustomer) {
        this.isNewCustomer = isNewCustomer == null ? null : isNewCustomer.trim();
    }

    public String getDistributorLevel() {
        return distributorLevel;
    }

    public void setDistributorLevel(String distributorLevel) {
        this.distributorLevel = distributorLevel == null ? null : distributorLevel.trim();
    }

    public String getCodServiceFee() {
        return codServiceFee;
    }

    public void setCodServiceFee(String codServiceFee) {
        this.codServiceFee = codServiceFee == null ? null : codServiceFee.trim();
    }

    public String getExpressColFee() {
        return expressColFee;
    }

    public void setExpressColFee(String expressColFee) {
        this.expressColFee = expressColFee == null ? null : expressColFee.trim();
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum == null ? null : productNum.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum == null ? null : itemNum.trim();
    }

    public String getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(String singleNum) {
        this.singleNum = singleNum == null ? null : singleNum.trim();
    }

    public String getFlagColor() {
        return flagColor;
    }

    public void setFlagColor(String flagColor) {
        this.flagColor = flagColor == null ? null : flagColor.trim();
    }

    public String getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag == null ? null : isFlag.trim();
    }

    public String getTaobaoDeliveryOrderStatus() {
        return taobaoDeliveryOrderStatus;
    }

    public void setTaobaoDeliveryOrderStatus(String taobaoDeliveryOrderStatus) {
        this.taobaoDeliveryOrderStatus = taobaoDeliveryOrderStatus == null ? null : taobaoDeliveryOrderStatus.trim();
    }

    public String getTaobaoDeliveryStatus() {
        return taobaoDeliveryStatus;
    }

    public void setTaobaoDeliveryStatus(String taobaoDeliveryStatus) {
        this.taobaoDeliveryStatus = taobaoDeliveryStatus == null ? null : taobaoDeliveryStatus.trim();
    }

    public String getTaobaoDeliveryMethod() {
        return taobaoDeliveryMethod;
    }

    public void setTaobaoDeliveryMethod(String taobaoDeliveryMethod) {
        this.taobaoDeliveryMethod = taobaoDeliveryMethod == null ? null : taobaoDeliveryMethod.trim();
    }

    public String getOrderProcessTime() {
        return orderProcessTime;
    }

    public void setOrderProcessTime(String orderProcessTime) {
        this.orderProcessTime = orderProcessTime == null ? null : orderProcessTime.trim();
    }

    public String getIsBreak() {
        return isBreak;
    }

    public void setIsBreak(String isBreak) {
        this.isBreak = isBreak == null ? null : isBreak.trim();
    }

    public String getBreaker() {
        return breaker;
    }

    public void setBreaker(String breaker) {
        this.breaker = breaker == null ? null : breaker.trim();
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime == null ? null : breakTime.trim();
    }

    public String getBreakExplain() {
        return breakExplain;
    }

    public void setBreakExplain(String breakExplain) {
        this.breakExplain = breakExplain == null ? null : breakExplain.trim();
    }

    public Byte getPlatSendStatus() {
        return platSendStatus;
    }

    public void setPlatSendStatus(Byte platSendStatus) {
        this.platSendStatus = platSendStatus;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
    }

    public String getIsAdvSale() {
        return isAdvSale;
    }

    public void setIsAdvSale(String isAdvSale) {
        this.isAdvSale = isAdvSale == null ? null : isAdvSale.trim();
    }

    public String getProvincCode() {
        return provincCode;
    }

    public void setProvincCode(String provincCode) {
        this.provincCode = provincCode == null ? null : provincCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode == null ? null : expressCode.trim();
    }

    public String getLastReturnedTime() {
        return lastReturnedTime;
    }

    public void setLastReturnedTime(String lastReturnedTime) {
        this.lastReturnedTime = lastReturnedTime == null ? null : lastReturnedTime.trim();
    }

    public String getLastRefundTime() {
        return lastRefundTime;
    }

    public void setLastRefundTime(String lastRefundTime) {
        this.lastRefundTime = lastRefundTime == null ? null : lastRefundTime.trim();
    }

    public String getDeliverCentre() {
        return deliverCentre;
    }

    public void setDeliverCentre(String deliverCentre) {
        this.deliverCentre = deliverCentre == null ? null : deliverCentre.trim();
    }

    public String getDeliverStation() {
        return deliverStation;
    }

    public void setDeliverStation(String deliverStation) {
        this.deliverStation = deliverStation == null ? null : deliverStation.trim();
    }

    public String getIsPreDeliveryNotice() {
        return isPreDeliveryNotice;
    }

    public void setIsPreDeliveryNotice(String isPreDeliveryNotice) {
        this.isPreDeliveryNotice = isPreDeliveryNotice == null ? null : isPreDeliveryNotice.trim();
    }

    public String getJdDeliveryTime() {
        return jdDeliveryTime;
    }

    public void setJdDeliveryTime(String jdDeliveryTime) {
        this.jdDeliveryTime = jdDeliveryTime == null ? null : jdDeliveryTime.trim();
    }

    public String getSortingCode() {
        return sortingCode;
    }

    public void setSortingCode(String sortingCode) {
        this.sortingCode = sortingCode == null ? null : sortingCode.trim();
    }

    public String getCodSettlementVouchernumber() {
        return codSettlementVouchernumber;
    }

    public void setCodSettlementVouchernumber(String codSettlementVouchernumber) {
        this.codSettlementVouchernumber = codSettlementVouchernumber == null ? null : codSettlementVouchernumber.trim();
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getOrigincode() {
        return origincode;
    }

    public void setOrigincode(String origincode) {
        this.origincode = origincode == null ? null : origincode.trim();
    }

    public String getDestcode() {
        return destcode;
    }

    public void setDestcode(String destcode) {
        this.destcode = destcode == null ? null : destcode.trim();
    }

    public String getBigMarker() {
        return bigMarker;
    }

    public void setBigMarker(String bigMarker) {
        this.bigMarker = bigMarker == null ? null : bigMarker.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}