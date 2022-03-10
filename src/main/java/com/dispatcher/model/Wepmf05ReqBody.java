package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @version 1.0
 * @date 2021/12/6 9:47
 */
public class Wepmf05ReqBody {

    @JacksonXmlProperty(localName = "ServiceId")
    private String serviceId;
    /**
     * 企业名称
     */
    @JacksonXmlProperty(localName = "EntpNm")
    private String entpNm;
    /**
     * 统一社会信用代码
     */
    @JacksonXmlProperty(localName = "UvslSocCrCd")
    private String uvslSocCrCd;
    /**
     * 查询类型
     */
    @JacksonXmlProperty(localName = "InqrTyp")
    private String inqrTyp;
    /**
     * 查询页码
     */
    @JacksonXmlProperty(localName = "InqPgNo")
    private String inqPgNo;
    /**
     * 每页记录数
     */
    @JacksonXmlProperty(localName = "PerPgCnt")
    private String perPgCnt;

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

    public String getUvslSocCrCd() {
        return uvslSocCrCd;
    }

    public void setUvslSocCrCd(String uvslSocCrCd) {
        this.uvslSocCrCd = uvslSocCrCd;
    }

    public String getInqrTyp() {
        return inqrTyp;
    }

    public void setInqrTyp(String inqrTyp) {
        this.inqrTyp = inqrTyp;
    }

    public String getInqPgNo() {
        return inqPgNo;
    }

    public void setInqPgNo(String inqPgNo) {
        this.inqPgNo = inqPgNo;
    }

    public String getPerPgCnt() {
        return perPgCnt;
    }

    public void setPerPgCnt(String perPgCnt) {
        this.perPgCnt = perPgCnt;
    }

    @Override
    public String toString() {
        return "Wepmf05ReqBody{" +
                "serviceId='" + serviceId + '\'' +
                ", entpNm='" + entpNm + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                ", inqrTyp='" + inqrTyp + '\'' +
                ", inqPgNo='" + inqPgNo + '\'' +
                ", perPgCnt='" + perPgCnt + '\'' +
                '}';
    }
}
