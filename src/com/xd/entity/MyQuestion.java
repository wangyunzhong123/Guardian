package com.xd.entity;

import javax.persistence.*;

/**
 * Created by tianxi on 16-3-18.
 */

@Entity
@Table(name="MYQUESTION_T")
public class MyQuestion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="prim_id")
    private Integer prim_id;//在题库中的id

    @Column(name="titile")
    private String title;

    @Column(name="myanswer")
    private String myanswer;

    @Column(name="acceptanswer")
    private String acceptanswer;

    @Column(name="importance")
    private String importance;

    public MyQuestion() {
    }

    public MyQuestion(Integer prim_id,String title, String myanswer, String acceptanswer, String importance) {
        this.prim_id = prim_id;
        this.title = title;
        this.myanswer = myanswer;
        this.acceptanswer = acceptanswer;
        this.importance = importance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMyanswer() {
        return myanswer;
    }

    public void setMyanswer(String myanswer) {
        this.myanswer = myanswer;
    }

    public String getAcceptanswer() {
        return acceptanswer;
    }

    public void setAcceptanswer(String acceptanswer) {
        this.acceptanswer = acceptanswer;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public Integer getPrim_id() {
        return prim_id;
    }

    public void setPrim_id(Integer prim_id) {
        this.prim_id = prim_id;
    }


}
