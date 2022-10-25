package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ResultBody1 {
    @JacksonXmlProperty(localName = "ReplyCd")
    private String replyCd;	//serviceId
    @JacksonXmlProperty(localName = "ReplyText")
    private String replyText;	//serviceId
    @JacksonXmlProperty(localName = "Result")
    private String result;
    public String getReplyCd() {
        return replyCd;
    }

    public void setReplyCd(String replyCd) {
        this.replyCd = replyCd;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultBody1{" +
                "replyCd='" + replyCd + '\'' +
                ", replyText='" + replyText + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
