package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @version 1.0
 * @date 2021/12/6 9:47
 */
public class Wepmf03ReqBody {
    @JacksonXmlProperty(localName = "AprvFlwNo")
    //审批流水号
    private String aprvFlwNo;

    @JacksonXmlProperty(localName = "Userid")
    private String userid;
    @JacksonXmlProperty(localName = "ServiceId")
    private String serviceId;
    //企业名称
    @JacksonXmlProperty(localName = "Entname")
    private String entname;
    //企业名称
    @JacksonXmlProperty(localName = "EntpNm")
    private String entpNm;
    //法人姓名
    @JacksonXmlProperty(localName = "LglPsnNm")
    private String lglPsnNm;
    //法人身份证号
    @JacksonXmlProperty(localName = "LglPsnIdentNo")
    private String lglPsnIdentNo;
    //组织几斗代码
    @JacksonXmlProperty(localName = "OrgInstCd")
    private String orgInstCd;
    //纳税人识别号
    @JacksonXmlProperty(localName = "TaxpyDistgNo")
    private String taxpyDistgNo;
    //工商注册号
    @JacksonXmlProperty(localName = "ICRgstNo")
    private String icrgstNo;
    //统一社会信用代码
    @JacksonXmlProperty(localName = "UvslSocCrCd")
    private String uvslSocCrCd;
    //申请金额
    @JacksonXmlProperty(localName = "AplAmt")
    private String aplAmt;
    //申请金额
    @JacksonXmlProperty(localName = "AplCrBal")
    private String aplCrBal;
    //借款人手机号码
    @JacksonXmlProperty(localName = "BrwMblNum")
    private String brwMblNum;
    //地区码
    @JacksonXmlProperty(localName = "ZoneCd")
    private String zoneCd;
    //申请金额
    @JacksonXmlProperty(localName = "AhrCd")
    private String ahrCd;
    //客户号
    @JacksonXmlProperty(localName = "CusNo")
    private String cusNo;

    public String getAprvFlwNo() {
        return aprvFlwNo;
    }

    public void setAprvFlwNo(String aprvFlwNo) {
        this.aprvFlwNo = aprvFlwNo;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getEntname() {
        return entname;
    }

    public void setEntname(String entname) {
        this.entname = entname;
    }

    public String getEntpNm() {
        return entpNm;
    }

    public void setEntpNm(String entpNm) {
        this.entpNm = entpNm;
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

    public String getOrgInstCd() {
        return orgInstCd;
    }

    public void setOrgInstCd(String orgInstCd) {
        this.orgInstCd = orgInstCd;
    }

    public String getTaxpyDistgNo() {
        return taxpyDistgNo;
    }

    public void setTaxpyDistgNo(String taxpyDistgNo) {
        this.taxpyDistgNo = taxpyDistgNo;
    }

    public String getIcrgstNo() {
        return icrgstNo;
    }

    public void setIcrgstNo(String icrgstNo) {
        this.icrgstNo = icrgstNo;
    }

    public String getUvslSocCrCd() {
        return uvslSocCrCd;
    }

    public void setUvslSocCrCd(String uvslSocCrCd) {
        this.uvslSocCrCd = uvslSocCrCd;
    }

    public String getAplAmt() {
        return aplAmt;
    }

    public void setAplAmt(String aplAmt) {
        this.aplAmt = aplAmt;
    }

    public String getAplCrBal() {
        return aplCrBal;
    }

    public void setAplCrBal(String aplCrBal) {
        this.aplCrBal = aplCrBal;
    }

    public String getBrwMblNum() {
        return brwMblNum;
    }

    public void setBrwMblNum(String brwMblNum) {
        this.brwMblNum = brwMblNum;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    public String getAhrCd() {
        return ahrCd;
    }

    public void setAhrCd(String ahrCd) {
        this.ahrCd = ahrCd;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    @Override
    public String toString() {
        return "Wepmf03ReqBody{" +
                "aprvFlwNo='" + aprvFlwNo + '\'' +
                ", userid='" + userid + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", entname='" + entname + '\'' +
                ", entpNm='" + entpNm + '\'' +
                ", lglPsnNm='" + lglPsnNm + '\'' +
                ", lglPsnIdentNo='" + lglPsnIdentNo + '\'' +
                ", orgInstCd='" + orgInstCd + '\'' +
                ", taxpyDistgNo='" + taxpyDistgNo + '\'' +
                ", icrgstNo='" + icrgstNo + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                ", aplAmt='" + aplAmt + '\'' +
                ", aplCrBal='" + aplCrBal + '\'' +
                ", brwMblNum='" + brwMblNum + '\'' +
                ", zoneCd='" + zoneCd + '\'' +
                ", ahrCd='" + ahrCd + '\'' +
                ", cusNo='" + cusNo + '\'' +
                '}';
    }
}
