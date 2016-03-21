package com.xd.entity;

import javax.persistence.*;

/**
 * Created by tianxi on 16-3-18.
 */

@Entity
@Table(name="QUESTION_ITEM_T")
public class QuestionItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="context")
    private String context;

    public QuestionItem() {
    }

    public QuestionItem(String context) {
        this.context = context;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @ManyToOne(cascade ={CascadeType.ALL},optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name="question_id")
    private Question question;
    public void setQuestion(Question question){
        this.question = question;
    }

    public Question getQuestion(){
        return question;
    }


    @Override
    public String toString() {
        return "QuestionItem{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", question=" + question +
                '}';
    }
}
