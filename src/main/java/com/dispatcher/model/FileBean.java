package com.dispatcher.model;

import java.io.Serializable;

/**
 * @Author think
 * @Date 2021/8/12 9:08
 * @Version 1.0
 */
public class FileBean implements Serializable {

    private String filePath;// 文件保存路径
    private String fileName;// 文件保存名称
    public FileBean() {
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}