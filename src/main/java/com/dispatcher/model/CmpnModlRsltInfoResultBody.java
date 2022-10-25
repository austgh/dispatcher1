package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * @author think
 * @date 2022年09月01日 15:59
 */
public class CmpnModlRsltInfoResultBody {
    @JacksonXmlProperty(localName = "CmpnModlRsltInfo")
    private List<CmpnModlRsltInfo> cmpnModlRsltInfos;
    @JacksonXmlProperty(localName = "TotRcrdCnt")
    private String totRcrdCnt;
    @JacksonXmlProperty(localName = "ReplyCd")
    private String replyCd;
    @JacksonXmlProperty(localName = "ReplyText")
    private String replyText;

    public List<CmpnModlRsltInfo> getCmpnModlRsltInfos() {
        return cmpnModlRsltInfos;
    }

    public void setCmpnModlRsltInfos(List<CmpnModlRsltInfo> cmpnModlRsltInfos) {
        this.cmpnModlRsltInfos = cmpnModlRsltInfos;
    }

    public String getTotRcrdCnt() {
        return totRcrdCnt;
    }

    public void setTotRcrdCnt(String totRcrdCnt) {
        this.totRcrdCnt = totRcrdCnt;
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
        return "CmpnModlRsltInfoResultBody{" +
                "cmpnModlRsltInfos=" + cmpnModlRsltInfos +
                ", totRcrdCnt='" + totRcrdCnt + '\'' +
                ", replyCd='" + replyCd + '\'' +
                ", replyText='" + replyText + '\'' +
                '}';
    }
}
