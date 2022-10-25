package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @date 2022年09月01日 15:45
 */
public class CmpnModlRsltInfo {
    //企业名称
    @JacksonXmlProperty(localName = "EntpNm")
    private String entpNm;
    //统一社会信用代码
    @JacksonXmlProperty(localName = "UvslSocCrCd")
    private String uvslSocCrCd;
    //审批结果
    @JacksonXmlProperty(localName = "AprRslt")
    private String aprRslt;
    //审批日期
    @JacksonXmlProperty(localName = "AprdDt")
    private String aprdDt;
    //模型等级
    @JacksonXmlProperty(localName = "ModlLvl")
    private String modlLvl;
    //触发项
    @JacksonXmlProperty(localName = "TrgrOpt")
    private String trgrOpt;

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

    public String getAprRslt() {
        return aprRslt;
    }

    public void setAprRslt(String aprRslt) {
        this.aprRslt = aprRslt;
    }

    public String getAprdDt() {
        return aprdDt;
    }

    public void setAprdDt(String aprdDt) {
        this.aprdDt = aprdDt;
    }

    public String getModlLvl() {
        return modlLvl;
    }

    public void setModlLvl(String modlLvl) {
        this.modlLvl = modlLvl;
    }

    public String getTrgrOpt() {
        return trgrOpt;
    }

    public void setTrgrOpt(String trgrOpt) {
        this.trgrOpt = trgrOpt;
    }

    @Override
    public String toString() {
        return "CmpnModlRsltInfo{" +
                "entpNm='" + entpNm + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                ", aprRslt='" + aprRslt + '\'' +
                ", aprdDt='" + aprdDt + '\'' +
                ", modlLvl='" + modlLvl + '\'' +
                ", trgrOpt='" + trgrOpt + '\'' +
                '}';
    }
}
