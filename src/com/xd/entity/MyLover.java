package com.xd.entity;

/**
 * Created by tianxi on 16-3-18.
 */
public class MyLover {

    private String name;
    private String address;
    private String education;
    private String income;
    private String beizhu;

    public MyLover(String name, String address, String education, String income, String beizhu) {
        this.name = name;
        this.address = address;
        this.education = education;
        this.income = income;
        this.beizhu = beizhu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
