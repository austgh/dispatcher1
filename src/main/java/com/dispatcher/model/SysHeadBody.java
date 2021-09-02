package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.context.annotation.Scope;

import java.util.List;


/**
 * ESB报文系统头信息（SYS_HEAD）
 * 
 * @author zhucl
 */
@Scope("prototype")
public class SysHeadBody {
	/**
	 * 服务代码<br>
	 * 服务唯一标识，由ESB提供
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "ServiceCode")
	private String serviceCode;

	/**
	 * 服务场景<br>
	 * 描述每个服务的应用场景 默认值必须为：01
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "ServiceScene")
	private String serviceScene;

	/**
	 * 请求方系统编号
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "ConsumerId")
	private String consumerId;

	/**
	 * 服务方系统编号
	 */
	@JacksonXmlProperty(localName = "TargetId")
	private String targetId;

	/**
	 * 渠道类型<br>
	 * 请求方系统渠道类型
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "ChannelTyp")
	private String channelTyp;

	/**
	 * 请求方系统编号(新核心系统)
	 */
	@JacksonXmlProperty(localName = "OrgConsumerId")
	private String orgConsumerId;

	/**
	 * 系统流水号<br>
	 * 请求方系统的交易流水号
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "ConsumerSeqNo")
	private String consumerSeqNo;

	/**
	 * 业务流水号<br>
	 * 由请求方提供 用来唯一标识一笔业务
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "OrgConsumerSeqNo")
	private String orgConsumerSeqNo;

	/**
	 * 交易模式<br>
	 * ONLINE－联机处理 <br>
	 * ASYNC －异步处理
	 */
	@JacksonXmlProperty(localName = "TranMode")
	private String tranMode;

	/**
	 * 交易日期<br>
	 * 请求方系统的日期 格式：YYYYMMDD 无掩码<br>
	 * 交易返回时服务方填写本系统的日期
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "TranDate")
	private String tranDate;

	/**
	 * 交易时间<br>
	 * 请求方系统的时间 格式：HHMMSS 无掩码<br>
	 * 交易返回时服务方填写本系统的时间
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "TranTime")
	private String tranTime;

	/**
	 * 发起方终端号
	 */
	@JacksonXmlProperty(localName = "TerminalCode")
	private String terminalCode;

	/**
	 * 发起方终端号2
	 */
	@JacksonXmlProperty(localName = "OrgTerminalCode")
	private String orgTerminalCode;

	/**
	 * 请求方服务器标识
	 */
	@JacksonXmlProperty(localName = "ConsumerSvrId")
	private String consumerSvrId;

	/**
	 * 请求方服务器标识2<br>
	 * 参照公共标准代码主代码ID上送
	 */
	@JacksonXmlProperty(localName = "OrgConsumerSvrId")
	@ParamsCheck(notNull = true)
	private String orgConsumerSvrId;

	/**
	 * 服务方服务器标识
	 */
	@JacksonXmlProperty(localName = "DestSvrId")
	private String destSvrId;

	/**
	 * 用户语言<br>
	 * CHINESE－中文(默认)<br>
	 * ENGLISH－英文
	 */
	@JacksonXmlProperty(localName = "UserLang")
	private String userLang;

	/**
	 * 文件标志<br>
	 * 0 无文件<br>
	 * 1 请求报文有文件传送<br>
	 * 2 应答报文有文件传送
	 */
	@JacksonXmlProperty(localName = "FilFlg")
	@ParamsCheck(notNull = true)
	private String filFlg;

	/**
	 * 文件路径<br>
	 * 文件交易服务时必输(包含文件名)
	 */
	@JacksonXmlProperty(localName = "FilPath")
	private String filPath;

	/**
	 * 业务摘要码
	 */
	@JacksonXmlProperty(localName = "BusiSmyCd")
	private String busiSmyCd;

	@JacksonXmlElementWrapper(localName ="array")
	@JacksonXmlProperty(localName ="Ret")
	private List<ResultInfo> resultInfos;

	@JacksonXmlElementWrapper(localName ="array")
	@JacksonXmlProperty(localName ="Ret")
	public void setResultInfos(List<ResultInfo> resultInfos) {
		this.resultInfos = resultInfos;
	}
	@JacksonXmlElementWrapper(localName ="array")
	@JacksonXmlProperty(localName ="Ret")
	public List<ResultInfo> getResultInfos() {
		return resultInfos;
	}

//	@JacksonXmlElementWrapper(localName ="array")
//	@JacksonXmlProperty(localName ="Ret")
//	public List<ResultInfo> getAttrInfArray() {
//		return resultInfos;
//	}

	/**
	 * 交易状态<br>
	 * S－系统处理成功<br>
	 * F－系统处理失败
	 */
	private String returnStatus;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceScene() {
		return serviceScene;
	}

	public void setServiceScene(String serviceScene) {
		this.serviceScene = serviceScene;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getChannelTyp() {
		return channelTyp;
	}

	public void setChannelTyp(String channelTyp) {
		this.channelTyp = channelTyp;
	}

	public String getOrgConsumerId() {
		return orgConsumerId;
	}

	public void setOrgConsumerId(String orgConsumerId) {
		this.orgConsumerId = orgConsumerId;
	}

	public String getConsumerSeqNo() {
		return consumerSeqNo;
	}

	public void setConsumerSeqNo(String consumerSeqNo) {
		this.consumerSeqNo = consumerSeqNo;
	}

	public String getOrgConsumerSeqNo() {
		return orgConsumerSeqNo;
	}

	public void setOrgConsumerSeqNo(String orgConsumerSeqNo) {
		this.orgConsumerSeqNo = orgConsumerSeqNo;
	}

	public String getTranMode() {
		return tranMode;
	}

	public void setTranMode(String tranMode) {
		this.tranMode = tranMode;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getOrgTerminalCode() {
		return orgTerminalCode;
	}

	public void setOrgTerminalCode(String orgTerminalCode) {
		this.orgTerminalCode = orgTerminalCode;
	}

	public String getConsumerSvrId() {
		return consumerSvrId;
	}

	public void setConsumerSvrId(String consumerSvrId) {
		this.consumerSvrId = consumerSvrId;
	}

	public String getOrgConsumerSvrId() {
		return orgConsumerSvrId;
	}

	public void setOrgConsumerSvrId(String orgConsumerSvrId) {
		this.orgConsumerSvrId = orgConsumerSvrId;
	}

	public String getDestSvrId() {
		return destSvrId;
	}

	public void setDestSvrId(String destSvrId) {
		this.destSvrId = destSvrId;
	}

	public String getUserLang() {
		return userLang;
	}

	public void setUserLang(String userLang) {
		this.userLang = userLang;
	}

	public String getFilFlg() {
		return filFlg;
	}

	public void setFilFlg(String filFlg) {
		this.filFlg = filFlg;
	}

	public String getFilPath() {
		return filPath;
	}

	public void setFilPath(String filPath) {
		this.filPath = filPath;
	}

	public String getBusiSmyCd() {
		return busiSmyCd;
	}

	public void setBusiSmyCd(String busiSmyCd) {
		this.busiSmyCd = busiSmyCd;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	@Override
	public String toString() {
		return "SysHeadBody{" +
				"serviceCode='" + serviceCode + '\'' +
				", serviceScene='" + serviceScene + '\'' +
				", consumerId='" + consumerId + '\'' +
				", targetId='" + targetId + '\'' +
				", channelTyp='" + channelTyp + '\'' +
				", orgConsumerId='" + orgConsumerId + '\'' +
				", consumerSeqNo='" + consumerSeqNo + '\'' +
				", orgConsumerSeqNo='" + orgConsumerSeqNo + '\'' +
				", tranMode='" + tranMode + '\'' +
				", tranDate='" + tranDate + '\'' +
				", tranTime='" + tranTime + '\'' +
				", terminalCode='" + terminalCode + '\'' +
				", orgTerminalCode='" + orgTerminalCode + '\'' +
				", consumerSvrId='" + consumerSvrId + '\'' +
				", orgConsumerSvrId='" + orgConsumerSvrId + '\'' +
				", destSvrId='" + destSvrId + '\'' +
				", userLang='" + userLang + '\'' +
				", filFlg='" + filFlg + '\'' +
				", filPath='" + filPath + '\'' +
				", busiSmyCd='" + busiSmyCd + '\'' +
				", resultInfos=" + resultInfos +
				", returnStatus='" + returnStatus + '\'' +
				'}';
	}
}
