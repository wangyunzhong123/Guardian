package com.xd.entity;

import javax.persistence.*;

/**
 * Created by tianxi on 16-3-15.
 */

/*用户要求的对方实体*/


@Entity
@Table(name="USER_TO_T")
public class User_to {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="age")
    private String age;

    @Column(name="height")
    private String height;

    @Column(name="education")
    private String education;

    @Column(name="income")
    private String income;

    @Column(name="address")
    private String address;//居住地

    @Column(name="locate")
    private String locate;//籍贯

    @Column(name="tell_to")
    private String tell_to;//对他说


    public String getTell_to() {
        return tell_to;
    }

    public void setTell_to(String tell_to) {
        this.tell_to = tell_to;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User_to(String age, String height, String education, String income,
                   String address, String locate, String tell_to) {
        this.age = age;
        this.height = height;
        this.education = education;
        this.income = income;
        this.address = address;
        this.locate = locate;
        this.tell_to = tell_to;
    }
}
