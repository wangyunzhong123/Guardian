package com.xd.entity;

import javax.persistence.*;

/**
 * Created by tianxi on 16-4-19.
 */
@Entity
@Table(name="MYIMG_T")
public class MyImg {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="url")
    private String url;//apache的地址,暂时设端口为88.http://qbt.feite.org:88/resource/img/...

    public MyImg() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
