package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.context.annotation.Scope;

/**
 * ESB报文应用头信息（APP_HEAD）
 * 
 * @author zhucl
 */
@Scope("prototype")
public class AppHeadBody {
	/**
	 * 柜员号<br>
	 * 服务请求者的唯一标识
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "TellerNo")
	private String TellerNo;

	/**
	 * 机构代码<br>
	 * 服务请求者的机构归属
	 */
	@ParamsCheck(notNull = true)
	@JacksonXmlProperty(localName = "BranchId")
	private String BranchId;

	/**
	 * 柜员密码<br>
	 * 服务请求者的密码
	 */
	@JacksonXmlProperty(localName = "TellerPassword")
	private String TellerPassword;

	/**
	 * 柜员级别<br>
	 * 服务请求者的级别
	 */
	@JacksonXmlProperty(localName = "TellerLevel")
	private String TellerLevel;

	/**
	 * 柜员类别<br>
	 * 服务请求者的类别
	 */
	@JacksonXmlProperty(localName = "TellerType")
	private String TellerType;

	/**
	 * 复核标志<br>
	 * 当交易发生复核时必输，标志是否通过 <br>
	 * E－交易录入<br>
	 * A－交易复核
	 */
	@JacksonXmlProperty(localName = "ApprFlag")
	private String ApprFlag;

	/**
	 * 授权标志<br>
	 * 当交易发生授权时必输<br>
	 * 1－ 需要授权<br>
	 * 0－ 不需要授权
	 */
	@JacksonXmlProperty(localName = "AuthFlag")
	private String AuthFlag;




	public AppHeadBody() {
	}
	
	public String getTellerNo() {
		return TellerNo;
	}

	public void setTellerNo(String tellerNo) {
		TellerNo = tellerNo;
	}

	public String getBranchId() {
		return BranchId;
	}

	public void setBranchId(String branchId) {
		BranchId = branchId;
	}

	public String getTellerPassword() {
		return TellerPassword;
	}

	public void setTellerPassword(String tellerPassword) {
		TellerPassword = tellerPassword;
	}

	public String getTellerLevel() {
		return TellerLevel;
	}

	public void setTellerLevel(String tellerLevel) {
		TellerLevel = tellerLevel;
	}

	public String getTellerType() {
		return TellerType;
	}

	public void setTellerType(String tellerType) {
		TellerType = tellerType;
	}

	public String getApprFlag() {
		return ApprFlag;
	}

	public void setApprFlag(String apprFlag) {
		ApprFlag = apprFlag;
	}

	public String getAuthFlag() {
		return AuthFlag;
	}

	public void setAuthFlag(String authFlag) {
		AuthFlag = authFlag;
	}

	@Override
	public String toString() {
		return "AppHeadBody [TellerNo=" + TellerNo + ", BranchId=" + BranchId + ", TellerPassword=" + TellerPassword
				+ ", TellerLevel=" + TellerLevel + ", TellerType=" + TellerType + ", ApprFlag=" + ApprFlag
				+ ", AuthFlag=" + AuthFlag + "]";
	}
	
}
