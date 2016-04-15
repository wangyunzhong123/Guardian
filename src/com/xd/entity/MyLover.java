package com.xd.entity;

import javax.persistence.*;

/**
 * Created by tianxi on 16-3-18.
 */
@Entity
@Table(name="MYLOVER_T")
public class MyLover {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="prim_id")
    private Integer prim_id;//在user库中的id

    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="education")
    private String education;
    @Column(name="income")
    private String income;
    @Column(name="beizhu")
    private String beizhu;

    public MyLover(){

    }
    public MyLover(int prim_id){
        this.prim_id = prim_id;
    }
    public MyLover(String name, String address, String education, String income, String beizhu) {
        this.name = name;
        this.address = address;
        this.education = education;
        this.income = income;
        this.beizhu = beizhu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrim_id() {
        return prim_id;
    }

    public void setPrim_id(Integer prim_id) {
        this.prim_id = prim_id;
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

    @ManyToOne(cascade ={CascadeType.ALL},optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
