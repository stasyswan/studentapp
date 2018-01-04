package com.gd.studentapp.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;


@XmlRootElement(name = "student")
public class StudentWithGroupName {
    private int id;
    private String name;
    private String surname;
    private int iqLevel;
    private String groupName;
    private Date birthday;

    public StudentWithGroupName() {
    }

    public StudentWithGroupName(int id,
                                String fname,
                                String lname,
                                int iqLevel,
                                String groupName,
                                Date birthday) {
        this.id = id;
        this.name = fname;
        this.surname = lname;
        this.iqLevel = iqLevel;
        this.groupName = groupName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
                .append(" GroupName : ").append(this.groupName + ", ")
                .append(" Birthday : ").append(this.birthday)
                .toString();
    }
}
 