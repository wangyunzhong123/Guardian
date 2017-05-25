package com.xd.entity;

import javax.persistence.*;

/**
 * Created by tianxi on 16-6-25.
 */
@Entity
@javax.persistence.Table(name="guardian_hostip")
public class Guardian_hostip {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="host")
    private String host;

    public Guardian_hostip() {
    }

    public Guardian_hostip(String host) {
        this.host = host;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
