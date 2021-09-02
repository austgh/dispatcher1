package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class RsplPrsnInfo {
    private String rsplPrsnNm;
    private String rsplPrsnIdTyp;
    private String identNo;
    @JacksonXmlProperty(isAttribute = true,localName = "RsplPrsnNm")
    public String getRsplPrsnNm() {
        return rsplPrsnNm;
    }

    public void setRsplPrsnNm(String rsplPrsnNm) {
        this.rsplPrsnNm = rsplPrsnNm;
    }
    @JacksonXmlProperty(isAttribute = true,localName = "RsplPrsnIdTyp")
    public String getRsplPrsnIdTyp() {
        return rsplPrsnIdTyp;
    }

    public void setRsplPrsnIdTyp(String rsplPrsnIdTyp) {
        this.rsplPrsnIdTyp = rsplPrsnIdTyp;
    }
    @JacksonXmlProperty(isAttribute = true,localName = "IdentNo")
    public String getIdentNo() {
        return identNo;
    }

    public void setIdentNo(String identNo) {
        this.identNo = identNo;
    }
    @Override
    public String toString() {
        return "RsplPrsnInfo{" +
                "reslPrsnNm='" + rsplPrsnNm + '\'' +
                ", rsplPrsnIdTyp='" + rsplPrsnIdTyp + '\'' +
                ", identNo='" + identNo + '\'' +
                '}';
    }
}
