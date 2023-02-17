package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author think
 * @date 2022年09月01日 15:59
 */
public class TechNameModlResultBody {

    @JacksonXmlProperty(localName = "TechCmpFlg")
    private String techCmpFlg;
    @JacksonXmlProperty(localName = "ReplyCd")
    private String replyCd;
    @JacksonXmlProperty(localName = "ReplyText")
    private String replyText;


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

    public String getTechCmpFlg() {
        return techCmpFlg;
    }

    public void setTechCmpFlg(String techCmpFlg) {
        this.techCmpFlg = techCmpFlg;
    }

    @Override
    public String toString() {
        return "TechNameModlResultBody{" +
                "techCmpFlg='" + techCmpFlg + '\'' +
                ", replyCd='" + replyCd + '\'' +
                ", replyText='" + replyText + '\'' +
                '}';
    }
}
