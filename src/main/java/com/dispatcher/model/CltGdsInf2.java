package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CltGdsInf2 {

    /*
    *抵押物类型
    * 01住宅
    * 02写字楼或公寓
    * 03厂房
    * 04土地
     */
    @JacksonXmlProperty(localName = "CltTyp")
    private String cltTyp;
    @JacksonXmlProperty(localName = "CltzMktVal")
    private String cltzMktVal;

    public String getCltTyp() {
        return cltTyp;
    }

    public void setCltTyp(String cltTyp) {
        this.cltTyp = cltTyp;
    }

    public String getCltzMktVal() {
        return cltzMktVal;
    }

    public void setCltzMktVal(String cltzMktVal) {
        this.cltzMktVal = cltzMktVal;
    }

    @Override
    public String toString() {
        return "CltGdsInf2{" +
                "cltTyp='" + cltTyp + '\'' +
                ", cltzMktVal='" + cltzMktVal + '\'' +
                '}';
    }
}
