package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Scope("prototype")
public class ReqBody {
	@JacksonXmlProperty(localName = "Userid")
	private String userid;	//serviceId
	@JacksonXmlProperty(localName = "Entname")
	private String entname;	//serviceId
	@JacksonXmlProperty(localName = "LglPsnNm")
	private String lglPsnNm;	//serviceId
	@JacksonXmlProperty(localName = "LglPsnIdentNo")
	private String lglPsnIdentNo;	//serviceId
	@JacksonXmlProperty(localName = "AprvLmt")
	private String aprvLmt;	//serviceId
	@JacksonXmlProperty(localName = "AplCrBal")
	private String aplCrBal;	//serviceId
	@JacksonXmlProperty(localName = "ProTyp")
	private String proTyp;	//serviceId

	public String getProTyp() {
		return proTyp;
	}

	public void setProTyp(String proTyp) {
		this.proTyp = proTyp;
	}

	public String getAplCrBal() {
		return aplCrBal;
	}

	public void setAplCrBal(String aplCrBal) {
		this.aplCrBal = aplCrBal;
	}

	public String getAprvLmt() {
		return aprvLmt;
	}

	public void setAprvLmt(String aprvLmt) {
		this.aprvLmt = aprvLmt;
	}

	@JacksonXmlProperty(localName = "PartBkCd")
	private String partBkCd;

	public String getPartBkCd() {
		return partBkCd;
	}
	@JacksonXmlProperty(localName = "ZoneCd")
	private String zoneCd;
	@JacksonXmlProperty(localName = "SmlEntpHighCusFlg")
	private String smlEntpHighCusFlg;
	@JacksonXmlProperty(localName = "SmlGntClbMbrFlg")
	private String smlGntClbMbrFlg;

	public String getSmlEntpHighCusFlg() {
		return smlEntpHighCusFlg;
	}

	public void setSmlEntpHighCusFlg(String smlEntpHighCusFlg) {
		this.smlEntpHighCusFlg = smlEntpHighCusFlg;
	}

	public String getSmlGntClbMbrFlg() {
		return smlGntClbMbrFlg;
	}

	public void setSmlGntClbMbrFlg(String smlGntClbMbrFlg) {
		this.smlGntClbMbrFlg = smlGntClbMbrFlg;
	}

	public String getZoneCd() {
		return zoneCd;
	}

	public void setZoneCd(String zoneCd) {
		this.zoneCd = zoneCd;
	}

	public void setPartBkCd(String partBkCd) {
		this.partBkCd = partBkCd;
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

	public String getEntname() {
		return entname;
	}

	public void setEntname(String entname) {
		this.entname = entname;
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
	@JacksonXmlProperty(localName = "EntpNm")
	private String entpNm;	//企业名称

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@JacksonXmlProperty(localName = "AplFlwNo")
	private String aplFlwNo;//流水号
	@JacksonXmlProperty(localName = "LoanFlwNo")
	private String loanFlwNo;//流水号

	public String getLoanFlwNo() {
		return loanFlwNo;
	}

	public void setLoanFlwNo(String loanFlwNo) {
		this.loanFlwNo = loanFlwNo;
	}

	@JacksonXmlProperty(localName = "ChanlNo")
	private String chanlNo;		//渠道号

	@JacksonXmlProperty(localName = "UvslSocCrCd")
	private String uvslSocCrCd; 	//统一社会信用代码

	private List<CltGdsInf> cltGdsInfList;

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
	@JacksonXmlElementWrapper(localName ="array")
	@JacksonXmlProperty(localName ="CltGdsInf")
	public List<CltGdsInf> getCltGdsInfList() {
		return cltGdsInfList;
	}

	public void setCltGdsInfList(List<CltGdsInf> cltGdsInfList) {
		this.cltGdsInfList = cltGdsInfList;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	private List<RsplPrsnInfo> rsplPrsnInfoList;
	@JacksonXmlElementWrapper(localName ="array")
	@JacksonXmlProperty(localName ="RsplPrsnInfo")
	public List<RsplPrsnInfo> getRsplPrsnInfoList() {
		return rsplPrsnInfoList;
	}

	public void setRsplPrsnInfoList(List<RsplPrsnInfo> rsplPrsnInfoList) {
		this.rsplPrsnInfoList = rsplPrsnInfoList;
	}

	@Override
	public String toString() {
		return "ReqBody{" +
				"userid='" + userid + '\'' +
				", entname='" + entname + '\'' +
				", lglPsnNm='" + lglPsnNm + '\'' +
				", lglPsnIdentNo='" + lglPsnIdentNo + '\'' +
				", aprvLmt='" + aprvLmt + '\'' +
				", aplCrBal='" + aplCrBal + '\'' +
				", proTyp='" + proTyp + '\'' +
				", partBkCd='" + partBkCd + '\'' +
				", zoneCd='" + zoneCd + '\'' +
				", smlEntpHighCusFlg='" + smlEntpHighCusFlg + '\'' +
				", smlGntClbMbrFlg='" + smlGntClbMbrFlg + '\'' +
				", legalname='" + legalname + '\'' +
				", legalidno='" + legalidno + '\'' +
				", serviceId='" + serviceId + '\'' +
				", entpNm='" + entpNm + '\'' +
				", aplFlwNo='" + aplFlwNo + '\'' +
				", loanFlwNo='" + loanFlwNo + '\'' +
				", chanlNo='" + chanlNo + '\'' +
				", uvslSocCrCd='" + uvslSocCrCd + '\'' +
				", cltGdsInfList=" + cltGdsInfList +
				", rsplPrsnInfoList=" + rsplPrsnInfoList +
				'}';
	}
}
