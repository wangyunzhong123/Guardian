package com.xd.entity;

import javax.persistence.*;

/**
 * Created by tianxi on 16-6-25.
 */
@Entity
@javax.persistence.Table(name="guardian_guardianer")
public class Guardian_guardianer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="phone")
    private String phone;

    public Guardian_guardianer() {
    }

    public Guardian_guardianer(String name, String password,String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{'id':'" + id + "', 'name':'" + name
                + "', 'password':'" + password + "', 'phone':'" + phone
                + "'}";
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
