package com.dispatcher.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.context.annotation.Scope;


/**
 * ESB报文信息
 * @author zhucl
 */
@Scope("prototype")
@JacksonXmlRootElement(localName ="service")
public class EsbXmlBody1 {
	@ParamsCheck(value = "SYS_HEAD", notNull = true)
	@JacksonXmlProperty(localName = "SYS_HEAD")
	private SysHeadBody sysHeadBody;

	@ParamsCheck(value = "APP_HEAD", notNull = true)
	@JacksonXmlProperty(localName = "APP_HEAD")
	private AppHeadBody appHeadBody;

	@ParamsCheck(value = "BODY", notNull = true)
	@JacksonXmlProperty(localName = "BODY")
	private ReqBody body;
	public <T> EsbXmlBody1(SysHeadBody sysHeadBody, AppHeadBody appHeadBody,ReqBody body) {
		setSysHeadBody(sysHeadBody);
		setAppHeadBody(appHeadBody);
		setBody(body);
	}

	public ReqBody getBody() {
		return body;
	}

	public void setBody(ReqBody body) {
		this.body = body;
	}

	public AppHeadBody getAppHeadBody() {
		return appHeadBody;
	}

	public void setAppHeadBody(AppHeadBody appHeadBody) {
		this.appHeadBody = appHeadBody;
	}

	public SysHeadBody getSysHeadBody() {
		return sysHeadBody;
	}

	public void setSysHeadBody(SysHeadBody sysHeadBody) {
		this.sysHeadBody = sysHeadBody;
	}

	@Override
	public String toString() {
		return "EsbXmlBody1{" +
				"sysHeadBody=" + sysHeadBody +
				", appHeadBody=" + appHeadBody +
				", body=" + body +
				'}';
	}
}
