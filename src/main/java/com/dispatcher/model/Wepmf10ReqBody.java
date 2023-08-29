package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @date 2023年05月30日 8:42
 */
public class Wepmf10ReqBody {

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
     * 申请流水号
     */
    @JacksonXmlProperty(localName = "AplFlwNo")
    private String aplFlwNo="";

    /**
     * 申请编号
     */
    @JacksonXmlProperty(localName = "AplNo")
    private String aplNo="";

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

    public String getAplNo() {
        return aplNo;
    }

    public void setAplNo(String aplNo) {
        this.aplNo = aplNo;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    @Override
    public String toString() {
        return "Wepmf10ReqBody{" +
                "serviceId='" + serviceId + '\'' +
                ", entpNm='" + entpNm + '\'' +
                ", entName='" + entName + '\'' +
                ", userid='" + userid + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                ", aplFlwNo='" + aplFlwNo + '\'' +
                ", aplNo='" + aplNo + '\'' +
                ", zoneCd='" + zoneCd + '\'' +
                '}';
    }
}
