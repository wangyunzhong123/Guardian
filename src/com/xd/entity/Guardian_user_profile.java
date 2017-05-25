package com.xd.entity;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * Created by tianxi on 16-6-24.
 */
@Entity
@Table(name="guardian_user_profile")
public class Guardian_user_profile {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="sex")
    private String sex;

    @Column(name="birth")
    private String birth;

    @Column(name="phone")
    private String phone;

    @Column(name="ring_id")
    private String ring_id;

    @Column(name="remark")
    private String remark;//内心独白

    public Guardian_user_profile() {
    }

    public Guardian_user_profile(String name, String remark, String password, String sex, String birth, String phone, String ring_id) {
        this.name = name;
        this.remark = remark;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.phone = phone;
        this.ring_id = ring_id;
    }

    @Override
    public String toString() {
        return "{'id':'" + id + "', 'name':'" + name
                + "', 'remark':'" + remark + "', 'sex':'" + sex
                + "', 'birth':'" + birth + "', 'phone':'" + phone
                + "', 'ring_id':'" + ring_id+ "'}";
    }
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRing_id() {
        return ring_id;
    }

    public void setRing_id(String ring_id) {
        this.ring_id = ring_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
