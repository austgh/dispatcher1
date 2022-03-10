package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class EntpInfoResultBody {
    @JacksonXmlProperty(localName = "EntpInf")
    private List<EntpInf> entpInfos;
    @JacksonXmlProperty(localName = "RcrdCnt")
    private String rcrdCnt;
    @JacksonXmlProperty(localName = "ReplyCd")
    private String replyCd;
    @JacksonXmlProperty(localName = "ReplyText")
    private String replyText;

    public List<EntpInf> getEntpInfos() {
        return entpInfos;
    }

    public void setEntpInfos(List<EntpInf> entpInfos) {
        this.entpInfos = entpInfos;
    }

    public String getRcrdCnt() {
        return rcrdCnt;
    }

    public void setRcrdCnt(String rcrdCnt) {
        this.rcrdCnt = rcrdCnt;
    }

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
        return "EntpInfoResultBody{" +
                "entpInfos=" + entpInfos +
                ", rcrdCnt='" + rcrdCnt + '\'' +
                ", replyCd='" + replyCd + '\'' +
                ", replyText='" + replyText + '\'' +
                '}';
    }
}
