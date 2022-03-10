package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @version 1.0
 * @date 2022/2/16 10:59
 */
public class EntpInf {
    @JacksonXmlProperty(localName = "ShrhlrNm")
    private String shrhlrNm;

    @JacksonXmlProperty(localName = "UvslSocCrCd")
    private String uvslSocCrCd;

    public String getShrhlrNm() {
        return shrhlrNm;
    }

    public void setShrhlrNm(String shrhlrNm) {
        this.shrhlrNm = shrhlrNm;
    }

    public String getUvslSocCrCd() {
        return uvslSocCrCd;
    }

    public void setUvslSocCrCd(String uvslSocCrCd) {
        this.uvslSocCrCd = uvslSocCrCd;
    }

    @Override
    public String toString() {
        return "EntpInf{" +
                "shrhlrNm='" + shrhlrNm + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                '}';
    }
}
