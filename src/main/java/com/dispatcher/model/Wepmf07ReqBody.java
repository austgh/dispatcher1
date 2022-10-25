package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @date 2022年09月01日 14:33
 */
public class Wepmf07ReqBody {
    @JacksonXmlProperty(localName = "ServiceId")
    private String serviceId;
    /**
     * 企业名称
     */
    @JacksonXmlProperty(localName = "EntpNm")
    private String entpNm;

    @JacksonXmlProperty(localName = "EntName")
    private String entName;
    @JacksonXmlProperty(localName = "Userid")
    private String userid;
    /**
     * 统一社会信用代码
     */
    @JacksonXmlProperty(localName = "UvslSocCrCd")
    private String uvslSocCrCd;
    /**
     * 客户经理代码
     */
    @JacksonXmlProperty(localName = "CusMngr")
    private String cusMngr;
    /**
     * 查开始日期
     */
    @JacksonXmlProperty(localName = "BgnDt")
    private String bgnDt;

    /**
     * 查结束日期
     */
    @JacksonXmlProperty(localName = "EndDt")
    private String endDt;

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

    public String getCusMngr() {
        return cusMngr;
    }

    public void setCusMngr(String cusMngr) {
        this.cusMngr = cusMngr;
    }

    public String getBgnDt() {
        return bgnDt;
    }

    public void setBgnDt(String bgnDt) {
        this.bgnDt = bgnDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    @Override
    public String toString() {
        return "Wepmf07ReqBody{" +
                "serviceId='" + serviceId + '\'' +
                ", entpNm='" + entpNm + '\'' +
                ", entName='" + entName + '\'' +
                ", userid='" + userid + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                ", cusMngr='" + cusMngr + '\'' +
                ", bgnDt='" + bgnDt + '\'' +
                ", endDt='" + endDt + '\'' +
                '}';
    }
}
