package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @date 2022年09月01日 14:32
 */
public class Wepmf06ReqBody {
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
     * 查询类型
     */
    @JacksonXmlProperty(localName = "CusMngr")
    private String cusMngr;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getEntpNm() {
        return entpNm;
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

    public void setEntpNm(String entpNm) {
        this.entpNm = entpNm;
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

    @Override
    public String toString() {
        return "Wepmf06ReqBody{" +
                "serviceId='" + serviceId + '\'' +
                ", entpNm='" + entpNm + '\'' +
                ", entName='" + entName + '\'' +
                ", userid='" + userid + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                ", cusMngr='" + cusMngr + '\'' +
                '}';
    }
}
