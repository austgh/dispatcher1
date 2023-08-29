package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @date 2023年06月14日 14:03
 */
public class Wepmf11ReqBody {

    private String serviceId="";
    /**
     * 企业名称
     */
    @JacksonXmlProperty(localName = "EntpNm")
    private String entpNm="";
    /**
     * 企业名称
     */
    private String entName="";

    @JacksonXmlProperty(localName = "Userid")
    private String userid="";
    /**
     * 统一社会信用代码
     */
    @JacksonXmlProperty(localName = "UvslSocCrCd")
    private String uvslSocCrCd="";
    /**
     * 工商注册号
     */
    @JacksonXmlProperty(localName = "ICRgstNo")
    private String icRgstNo      ;
    /**
     * 组织机构代码
     */
    @JacksonXmlProperty(localName = "OrgInstCd")
    private String orgInstCd     ;
    /**
     * 法人名称
     */
    @JacksonXmlProperty(localName = "LglPsnNm")
    private String lglPsnNm      ;
    /**
     * 法人证件号码
     */
    @JacksonXmlProperty(localName = "LglPsnIdentNo")
    private String lglPsnIdentNo ;
    /**
     * 法人证件类别
     */
    @JacksonXmlProperty(localName = "LglPsnIdentTyp")
    private String lglPsnIdentTyp;
    /**
     * 联系人
     */
    @JacksonXmlProperty(localName = "CtcPrsn")
    private String ctcPrsn       ;
    /**
     * 手机号码
     */
    @JacksonXmlProperty(localName = "MblNum")
    private String mblNum        ;
    /**
     * 应税销售额
     */
    @JacksonXmlProperty(localName = "TaxSaleAmt")
    private String taxSaleAmt    ;
    private String saleIncome    ;
    /**
     * 纳税总额
     */
    @JacksonXmlProperty(localName = "TaxPymtTotAmt")
    private String taxPymtTotAmt ;
    private String taxAmount ;
    /**
     * 负债合计
     */
    @JacksonXmlProperty(localName = "LbySmy")
    private String lbySmy        ;
    private String debtAmount        ;
    /**
     * 申请流水号
     */
    @JacksonXmlProperty(localName = "AplFlwNo")
    private String aplFlwNo      ;
    /**
     * 额度金额
     */
    @JacksonXmlProperty(localName = "CrLmtAmt")
    private String crLmtAmt      ;
    /**
     * 推荐人信息
     */
    @JacksonXmlProperty(localName = "RecomPrsnInf")
    private String recomPrsnInf  ;
    /**
     * 地区码
     */
    @JacksonXmlProperty(localName = "ZoneCd")
    private String zoneCd="";

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getEntpNm() {
        return entpNm;
    }

    public void setEntpNm(String entpNm) {
        this.entpNm = entpNm;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUvslSocCrCd() {
        return uvslSocCrCd;
    }

    public void setUvslSocCrCd(String uvslSocCrCd) {
        this.uvslSocCrCd = uvslSocCrCd;
    }

    public String getAplFlwNo() {
        return aplFlwNo;
    }

    public void setAplFlwNo(String aplFlwNo) {
        this.aplFlwNo = aplFlwNo;
    }


    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    public String getIcRgstNo() {
        return icRgstNo;
    }

    public void setIcRgstNo(String icRgstNo) {
        this.icRgstNo = icRgstNo;
    }

    public String getOrgInstCd() {
        return orgInstCd;
    }

    public void setOrgInstCd(String orgInstCd) {
        this.orgInstCd = orgInstCd;
    }

    public String getLglPsnNm() {
        return lglPsnNm;
    }

    public void setLglPsnNm(String lglPsnNm) {
        this.lglPsnNm = lglPsnNm;
    }

    public String getLglPsnIdentNo() {
        return lglPsnIdentNo;
    }

    public void setLglPsnIdentNo(String lglPsnIdentNo) {
        this.lglPsnIdentNo = lglPsnIdentNo;
    }

    public String getLglPsnIdentTyp() {
        return lglPsnIdentTyp;
    }

    public void setLglPsnIdentTyp(String lglPsnIdentTyp) {
        this.lglPsnIdentTyp = lglPsnIdentTyp;
    }

    public String getCtcPrsn() {
        return ctcPrsn;
    }

    public void setCtcPrsn(String ctcPrsn) {
        this.ctcPrsn = ctcPrsn;
    }

    public String getMblNum() {
        return mblNum;
    }

    public void setMblNum(String mblNum) {
        this.mblNum = mblNum;
    }

    public String getTaxSaleAmt() {
        return taxSaleAmt;
    }

    public void setTaxSaleAmt(String taxSaleAmt) {
        this.taxSaleAmt = taxSaleAmt;
    }

    public String getSaleIncome() {
        return saleIncome;
    }

    public void setSaleIncome(String saleIncome) {
        this.saleIncome = saleIncome;
    }

    public String getTaxPymtTotAmt() {
        return taxPymtTotAmt;
    }

    public void setTaxPymtTotAmt(String taxPymtTotAmt) {
        this.taxPymtTotAmt = taxPymtTotAmt;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getLbySmy() {
        return lbySmy;
    }

    public void setLbySmy(String lbySmy) {
        this.lbySmy = lbySmy;
    }

    public String getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(String debtAmount) {
        this.debtAmount = debtAmount;
    }

    public String getCrLmtAmt() {
        return crLmtAmt;
    }

    public void setCrLmtAmt(String crLmtAmt) {
        this.crLmtAmt = crLmtAmt;
    }

    public String getRecomPrsnInf() {
        return recomPrsnInf;
    }

    public void setRecomPrsnInf(String recomPrsnInf) {
        this.recomPrsnInf = recomPrsnInf;
    }

    @Override
    public String toString() {
        return "Wepmf11ReqBody{" +
                "serviceId='" + serviceId + '\'' +
                ", entpNm='" + entpNm + '\'' +
                ", entName='" + entName + '\'' +
                ", userid='" + userid + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                ", icRgstNo='" + icRgstNo + '\'' +
                ", orgInstCd='" + orgInstCd + '\'' +
                ", lglPsnNm='" + lglPsnNm + '\'' +
                ", lglPsnIdentNo='" + lglPsnIdentNo + '\'' +
                ", lglPsnIdentTyp='" + lglPsnIdentTyp + '\'' +
                ", ctcPrsn='" + ctcPrsn + '\'' +
                ", mblNum='" + mblNum + '\'' +
                ", taxSaleAmt='" + taxSaleAmt + '\'' +
                ", saleIncome='" + saleIncome + '\'' +
                ", taxPymtTotAmt='" + taxPymtTotAmt + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                ", lbySmy='" + lbySmy + '\'' +
                ", debtAmount='" + debtAmount + '\'' +
                ", aplFlwNo='" + aplFlwNo + '\'' +
                ", crLmtAmt='" + crLmtAmt + '\'' +
                ", recomPrsnInf='" + recomPrsnInf + '\'' +
                ", zoneCd='" + zoneCd + '\'' +
                '}';
    }
}
