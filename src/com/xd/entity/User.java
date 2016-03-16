package com.xd.entity;


import javax.persistence.*;

/**
 * Created by tianxi on 16-3-15.
 */


/*用户本人*/
@Entity
@Table(name="USER_T")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="userid")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="sex")
    private String sex;

    @Column(name="birth")
    private String birth;

    @Column(name="height")
    private String height;

    @Column(name="education")
    private String education;

    @Column(name="career")
    private String career;

    @Column(name="income")
    private String income;

    @Column(name="address")
    private String address;//居住地

    @Column(name="locate")
    private String locate;//籍贯

    @Column(name="dubai")
    private String dubai;//内心独白


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
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

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
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

    public String getDubai() {
        return dubai;
    }

    public void setDubai(String dubai) {
        this.dubai = dubai;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public User(String name, String password, String sex, String birth,
                String height, String education, String career, String income,
                String address, String locate, String dubai) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.height = height;
        this.education = education;
        this.career = career;
        this.income = income;
        this.address = address;
        this.locate = locate;
        this.dubai = dubai;
    }

    public User() {
    }
}
