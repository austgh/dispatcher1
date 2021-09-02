package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AttrInf {

    @JacksonXmlProperty(localName = "AttrNm")
    private String attrNm;

    @JacksonXmlProperty(localName = "AttrTyp")
    private String attrTyp;

    @JacksonXmlProperty(localName = "IdentNo")
    private String identNo;


    public String getAttrNm() {
        return attrNm;
    }

    public void setAttrNm(String attrNm) {
        this.attrNm = attrNm;
    }

    public String getAttrTyp() {
        return attrTyp;
    }

    public void setAttrTyp(String attrTyp) {
        this.attrTyp = attrTyp;
    }

    public String getIdentNo() {
        return identNo;
    }

    public void setIdentNo(String identNo) {
        this.identNo = identNo;
    }

    @Override
    public String toString() {
        return "AttrInf{" +
                "attrNm='" + attrNm + '\'' +
                ", attrTyp='" + attrTyp + '\'' +
                ", identNo='" + identNo + '\'' +
                '}';
    }
}
