package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Student {
    private Integer id;
    private String stuName;
    private String sex;

    @JacksonXmlProperty(isAttribute = true,localName = "STUDENT_ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JacksonXmlProperty(localName = "STUDENT_NAME")
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @JacksonXmlProperty(localName = "STUDENT_SEX")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}