package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class CltGdsInf {

    @JacksonXmlProperty(localName = "CltTyp")
    private String cltTyp;

    @JacksonXmlProperty(localName = "HsPtyTyp")
    private String hsPtyTyp;

    @JacksonXmlProperty(localName = "OwnCtfNo")
    private String ownCtfNo;

    @JacksonXmlProperty(localName = "CltNm")
    private String cltNm;

    @JacksonXmlProperty(localName = "CltAdr")
    private String cltAdr;

    @JacksonXmlProperty(localName = "ValEvltMode")
    private String valEvltMode;

    @JacksonXmlProperty(localName = "CltzEvltVal")
    private String cltzEvltVal;

    @JacksonXmlProperty(localName = "CltzRvalVal")
    private String cltzRvalVal;

    public String getCltzRvalVal() {
        return cltzRvalVal;
    }

    public void setCltzRvalVal(String cltzRvalVal) {
        this.cltzRvalVal = cltzRvalVal;
    }

    @JacksonXmlProperty(localName = "lglPsnNm")
    private String lglPsnNm;

    @JacksonXmlProperty(localName = "lglPsnIdentNo")
    private String lglPsnIdentNo;

    private List<AttrInf> AttrInfArray;

    public String getCltTyp() {
        return cltTyp;
    }

    public void setCltTyp(String cltTyp) {
        this.cltTyp = cltTyp;
    }

    public String getHsPtyTyp() {
        return hsPtyTyp;
    }

    public void setHsPtyTyp(String hsPtyTyp) {
        this.hsPtyTyp = hsPtyTyp;
    }

    public String getOwnCtfNo() {
        return ownCtfNo;
    }

    public void setOwnCtfNo(String ownCtfNo) {
        this.ownCtfNo = ownCtfNo;
    }

    public String getCltNm() {
        return cltNm;
    }

    public void setCltNm(String cltNm) {
        this.cltNm = cltNm;
    }

    public String getCltAdr() {
        return cltAdr;
    }

    public void setCltAdr(String cltAdr) {
        this.cltAdr = cltAdr;
    }

    public String getValEvltMode() {
        return valEvltMode;
    }

    public void setValEvltMode(String valEvltMode) {
        this.valEvltMode = valEvltMode;
    }

    public String getCltzEvltVal() {
        return cltzEvltVal;
    }

    public void setCltzEvltVal(String cltzEvltVal) {
        this.cltzEvltVal = cltzEvltVal;
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

    @JacksonXmlElementWrapper(localName ="array")
    @JacksonXmlProperty(localName ="AttrInf")
    public List<AttrInf> getAttrInfArray() {
        return AttrInfArray;
    }

    public void setAttrInfArray(List<AttrInf> attrInfArray) {
        AttrInfArray = attrInfArray;
    }

    @Override
    public String toString() {
        return "CltGdsInf{" +
                "cltTyp='" + cltTyp + '\'' +
                ", hsPtyTyp='" + hsPtyTyp + '\'' +
                ", ownCtfNo='" + ownCtfNo + '\'' +
                ", cltNm='" + cltNm + '\'' +
                ", cltAdr='" + cltAdr + '\'' +
                ", valEvltMode='" + valEvltMode + '\'' +
                ", cltzEvltVal='" + cltzEvltVal + '\'' +
                ", cltzRvalVal='" + cltzRvalVal + '\'' +
                ", lglPsnNm='" + lglPsnNm + '\'' +
                ", lglPsnIdentNo='" + lglPsnIdentNo + '\'' +
                ", AttrInfArray=" + AttrInfArray +
                '}';
    }
}
