package com.gd.studentapp.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement(name = "student")
public class Student {
    private int id;
    private String name;
    private String surname;
    private int iqLevel;
    private int groupId;
    private Date birthday;

    public Student() {
    }

    public Student(int id, String fname, String lname, int iqLevel, int groupId, Date birthday) {
        this.id = id;
        this.name = fname;
        this.surname = lname;
        this.iqLevel = iqLevel;
        this.groupId = groupId;
        this.birthday = birthday;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @XmlElement
    public void setName(String fname) {
        this.name = fname;
    }

    public String getName() {
        return this.name;
    }

    @XmlElement
    public void setSurname(String lname) {
        this.surname = lname;
    }

    public String getSurname() {
        return this.surname;
    }

    @XmlElement
    public void setIqLevel(int iqLevel) {
        this.iqLevel = iqLevel;
    }

    public int getIqLevel() {
        return this.iqLevel;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return new StringBuffer(" ID : ").append(this.id + ", ")
                .append(" Name : ").append(this.name + ", ")
                .append(" Surname : ").append(this.surname + ", ")
                .append(" IqLevel : ").append(this.iqLevel + ", ")
                .append(" IqLevel : ").append(this.iqLevel)
                .toString();
    }
}
 