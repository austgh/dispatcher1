package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Scope("prototype")
public class Wepmf01Body {
    @JacksonXmlProperty(localName = "Userid")
    private String userid;	//serviceId
    @JacksonXmlProperty(localName = "EntpNm")
    private String entpNm;

    @JacksonXmlProperty(localName = "PartBkCd")
    private String partBkCd;


    private String zoneCd;

    public String getZoneCd() {
        return zoneCd;
    }

    @Override
    public String toString() {
        return "Wepmf01Body{" +
                "userid='" + userid + '\'' +
                ", entpNm='" + entpNm + '\'' +
                ", partBkCd='" + partBkCd + '\'' +
                ", zoneCd='" + zoneCd + '\'' +
                ", entname='" + entname + '\'' +
                ", aplFlwNo='" + aplFlwNo + '\'' +
                ", uvslSocCrCd='" + uvslSocCrCd + '\'' +
                ", lglPsnNm='" + lglPsnNm + '\'' +
                ", lglPsnIdentNo='" + lglPsnIdentNo + '\'' +
                ", rsplPrsnInfoList=" + rsplPrsnInfoList +
                ", legalname='" + legalname + '\'' +
                ", legalidno='" + legalidno + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", chanlNo='" + chanlNo + '\'' +
                '}';
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    public String getPartBkCd() {
        return partBkCd;
    }

    public void setPartBkCd(String partBkCd) {
        this.partBkCd = partBkCd;
    }

    @JacksonXmlProperty(localName = "Entname")
    private String entname;
    @JacksonXmlProperty(localName = "AplFlwNo")
    private String aplFlwNo;//流水号

    @JacksonXmlProperty(localName = "UvslSocCrCd")
    private String uvslSocCrCd; 	//统一社会信用代码

    @JacksonXmlProperty(localName = "LglPsnNm")
    private String lglPsnNm;
    @JacksonXmlProperty(localName = "LglPsnIdentNo")
    private String lglPsnIdentNo;
    @JacksonXmlElementWrapper(localName ="array")
    @JacksonXmlProperty(localName ="RsplPrsnInfo")
    List<RsplPrsnInfo> rsplPrsnInfoList;

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
    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname;
    }

    public String getLegalidno() {
        return legalidno;
    }

    public void setLegalidno(String legalidno) {
        this.legalidno = legalidno;
    }

    @JacksonXmlProperty(localName = "Legalname")
    private String legalname;	//serviceId
    @JacksonXmlProperty(localName = "Legalidno")
    private String legalidno;	//serviceId
    @JacksonXmlProperty(localName = "ServiceId")
    private String serviceId;	//serviceId

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @JacksonXmlProperty(localName = "ChanlNo")
    private String chanlNo;		//渠道号

    public String getEntpNm() {
        return entpNm;
    }

    public void setEntpNm(String entpNm) {
        this.entpNm = entpNm;
    }

    public String getAplFlwNo() {
        return aplFlwNo;
    }

    public void setAplFlwNo(String aplFlwNo) {
        this.aplFlwNo = aplFlwNo;
    }

    public String getChanlNo() {
        return chanlNo;
    }

    public void setChanlNo(String chanlNo) {
        this.chanlNo = chanlNo;
    }

    public String getUvslSocCrCd() {
        return uvslSocCrCd;
    }

    public void setUvslSocCrCd(String uvslSocCrCd) {
        this.uvslSocCrCd = uvslSocCrCd;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEntname() {
        return entname;
    }

    public void setEntname(String entname) {
        this.entname = entname;
    }

    public List<RsplPrsnInfo> getRsplPrsnInfoList() {
        return rsplPrsnInfoList;
    }

    public void setRsplPrsnInfoList(List<RsplPrsnInfo> rsplPrsnInfoList) {
        this.rsplPrsnInfoList = rsplPrsnInfoList;
    }

}
