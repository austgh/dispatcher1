package com.dispatcher.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.context.annotation.Scope;


/**
 * ESB报文信息
 * @author zhucl
 */
@Scope("prototype")
@JacksonXmlRootElement(localName ="service")
public class EsbXmlBodyResult {
	@ParamsCheck(value = "SYS_HEAD", notNull = true)
	private SysHeadBody sysHeadBody;
	@ParamsCheck(value = "APP_HEAD", notNull = true)
	private AppHeadBody appHeadBody;
	@ParamsCheck(value = "BODY", notNull = true)
	private Object body;

	public EsbXmlBodyResult(){}

	public <T> EsbXmlBodyResult(SysHeadBody sysHeadBody, AppHeadBody appHeadBody, T body) {
		setSysHeadBody(sysHeadBody);
		setAppHeadBody(appHeadBody);
		setBody(body);
	}
	
	public SysHeadBody getSysHeadBody() {
		return sysHeadBody;
	}

	public void setSysHeadBody(SysHeadBody sysHeadBody) {
		this.sysHeadBody = sysHeadBody;
	}

	public AppHeadBody getAppHeadBody() {
		return appHeadBody;
	}

	public void setAppHeadBody(AppHeadBody appHeadBody) {
		this.appHeadBody = appHeadBody;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "EsbXmlBody [sysHeadBody=" + sysHeadBody + ", appHeadBody=" + appHeadBody + ", body=" + body + "]";
	}
	
}
