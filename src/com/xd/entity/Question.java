package com.xd.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianxi on 16-3-18.
 */

@Entity
@Table(name="QUESTION_T")
public class Question {



    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="titile")
    private String title;

    @OneToMany(cascade = {CascadeType.ALL},
    mappedBy = "question",fetch = FetchType.EAGER)
    private Set<QuestionItem> items = new HashSet<QuestionItem>();

    public void setItems(Set<QuestionItem> items){
        this.items = items;
    }
    public Set<QuestionItem> getItems(){
        return items;
    }



    /*
    该方法用于向里面添加item
    * */
    public void addQuestionItem(QuestionItem item){
        item.setQuestion(this);
        this.items.add(item);
    }

    public Question() {
    }

    public Question(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", items=" + items +
                '}';
    }
}
