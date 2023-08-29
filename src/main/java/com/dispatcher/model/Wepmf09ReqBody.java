package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @date 2023年03月10日 10:49
 */
public class Wepmf09ReqBody {

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
     * 企业统一码
     */
    @JacksonXmlProperty(localName = "EntpSocCrCd")
    private String entpSocCrCd="";

    /**
     * 原业务流水号
     */
    @JacksonXmlProperty(localName = "OriBsnFlwNo")
    private String oriBsnFlwNo="";


    /**
     * 法人代表名称
     */
    @JacksonXmlProperty(localName = "LglPrsnRprsNm")
    private String lglPrsnRprsNm="";

    /**
     * 法人代表证件号码
     */
    @JacksonXmlProperty(localName = "LglPrsnIdNo")
    private String lglPrsnIdNo="";

    /**
     * 产品代码
     */
    @JacksonXmlProperty(localName = "ProCd")
    private String proCd="";

    /**
     * 场景码
     */
    @JacksonXmlProperty(localName = "ScenarCd")
    private String scenarCd="";

    /**
     * 担保方式
     */
    @JacksonXmlProperty(localName = "GntMd")
    private String gntMd="";

    /**
     * 担保公司编号
     */
    @JacksonXmlProperty(localName = "GntCmpId")
    private String gntCmpId="";

    /**
     * 地区码
     */
    @JacksonXmlProperty(localName = "ZoneCd")
    private String zoneCd="";

    /**
     * 额度金额
     */
    @JacksonXmlProperty(localName = "CrLmtAmt")
    private String crLmtAmt="";

    /**
     * 贷款余额
     */
    @JacksonXmlProperty(localName = "LoanBal")
    private String loanBal="";

    /**
     * 登记机构代码
     */
    @JacksonXmlProperty(localName = "RgstInstCd")
    private String rgstInstCd="";

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

    public String getEntpSocCrCd() {
        return entpSocCrCd;
    }

    public void setEntpSocCrCd(String entpSocCrCd) {
        this.entpSocCrCd = entpSocCrCd;
    }

    public String getLglPrsnRprsNm() {
        return lglPrsnRprsNm;
    }

    public void setLglPrsnRprsNm(String lglPrsnRprsNm) {
        this.lglPrsnRprsNm = lglPrsnRprsNm;
    }

    public String getLglPrsnIdNo() {
        return lglPrsnIdNo;
    }

    public void setLglPrsnIdNo(String lglPrsnIdNo) {
        this.lglPrsnIdNo = lglPrsnIdNo;
    }

    public String getProCd() {
        return proCd;
    }

    public void setProCd(String proCd) {
        this.proCd = proCd;
    }

    public String getScenarCd() {
        return scenarCd;
    }

    public void setScenarCd(String scenarCd) {
        this.scenarCd = scenarCd;
    }

    public String getGntMd() {
        return gntMd==null?"":gntMd;
    }

    public void setGntMd(String gntMd) {
        if(gntMd!=null){
            this.gntMd = gntMd;
        }
    }

    public String getGntCmpId() {
        return gntCmpId;
    }

    public void setGntCmpId(String gntCmpId) {
        this.gntCmpId = gntCmpId;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    public String getCrLmtAmt() {
        return crLmtAmt;
    }

    public void setCrLmtAmt(String crLmtAmt) {
        this.crLmtAmt = crLmtAmt;
    }

    public String getLoanBal() {
        return loanBal;
    }

    public void setLoanBal(String loanBal) {
        this.loanBal = loanBal;
    }

    public String getRgstInstCd() {
        return rgstInstCd;
    }

    public void setRgstInstCd(String rgstInstCd) {
        this.rgstInstCd = rgstInstCd;
    }

    public String getOriBsnFlwNo() {
        return oriBsnFlwNo;
    }

    public void setOriBsnFlwNo(String oriBsnFlwNo) {
        this.oriBsnFlwNo = oriBsnFlwNo;
    }

    @Override
    public String toString() {
        return "Wepmf09ReqBody{" +
                "serviceId='" + serviceId + '\'' +
                ", entpNm='" + entpNm + '\'' +
                ", entName='" + entName + '\'' +
                ", userid='" + userid + '\'' +
                ", entpSocCrCd='" + entpSocCrCd + '\'' +
                ", oriBsnFlwNo='" + oriBsnFlwNo + '\'' +
                ", lglPrsnRprsNm='" + lglPrsnRprsNm + '\'' +
                ", lglPrsnIdNo='" + lglPrsnIdNo + '\'' +
                ", proCd='" + proCd + '\'' +
                ", scenarCd='" + scenarCd + '\'' +
                ", gntMd='" + gntMd + '\'' +
                ", gntCmpId='" + gntCmpId + '\'' +
                ", zoneCd='" + zoneCd + '\'' +
                ", crLmtAmt='" + crLmtAmt + '\'' +
                ", loanBal='" + loanBal + '\'' +
                ", rgstInstCd='" + rgstInstCd + '\'' +
                '}';
    }
}
