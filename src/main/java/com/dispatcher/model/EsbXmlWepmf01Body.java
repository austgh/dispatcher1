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
public class EsbXmlWepmf01Body {
	@ParamsCheck(value = "SYS_HEAD", notNull = true)
	@JacksonXmlProperty(localName = "SYS_HEAD")
	private SysHeadBody sysHeadBody;

	@ParamsCheck(value = "APP_HEAD", notNull = true)
	@JacksonXmlProperty(localName = "APP_HEAD")
	private AppHeadBody appHeadBody;

	@ParamsCheck(value = "BODY", notNull = true)
	@JacksonXmlProperty(localName = "BODY")
	private Wepmf01Body body;
	public <T> EsbXmlWepmf01Body(SysHeadBody sysHeadBody, AppHeadBody appHeadBody, Wepmf01Body body) {
		setSysHeadBody(sysHeadBody);
		setAppHeadBody(appHeadBody);
		setBody(body);
	}

	public Wepmf01Body getBody() {
		return body;
	}

	public void setBody(Wepmf01Body body) {
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
		return "EsbXmlWepmf01Body{" +
				"sysHeadBody=" + sysHeadBody +
				", appHeadBody=" + appHeadBody +
				", body=" + body +
				'}';
	}
}
