package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ResultBody {
    @JacksonXmlProperty(localName = "ReplyCd")
    private String replyCd;	//serviceId
    @JacksonXmlProperty(localName = "ReplyText")
    private String replyText;	//serviceId

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

    @Override
    public String toString() {
        return "ResultBody{" +
                "replyCd='" + replyCd + '\'' +
                ", replyText='" + replyText + '\'' +
                '}';
    }
}
