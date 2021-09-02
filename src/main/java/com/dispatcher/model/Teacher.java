package com.dispatcher.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName ="MESSAGE")
public class Teacher {
    private Integer id;
    private String teacherName;
    private List<Student> studentList;

    @JacksonXmlProperty(isAttribute = true,localName = "TEACHER_ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JacksonXmlProperty(localName = "TEACHER_NAME")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @JacksonXmlElementWrapper(localName ="STUDENT_LIST")
    @JacksonXmlProperty(localName ="STUDENT")
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}