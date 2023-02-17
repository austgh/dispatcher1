package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @date 2023年02月17日 14:33
 */
public class Wepmf08ReqBody {
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

    @Override
    public String toString() {
        return "Wepmf08ReqBody{" +
                "serviceId='" + serviceId + '\'' +
                ", entpNm='" + entpNm + '\'' +
                ", entName='" + entName + '\'' +
                ", userid='" + userid + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                '}';
    }
}
