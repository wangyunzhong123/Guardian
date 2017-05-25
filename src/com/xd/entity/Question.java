package com.xd.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Table;
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
    private List<QuestionItem> items = new ArrayList<QuestionItem>();

    public void setItems(List<QuestionItem> items){
        this.items = items;
    }
    public List<QuestionItem> getItems(){
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
    //检测是否包含List MyQUestion
    public boolean isexistMyQuestionList(List<MyQuestion> myQuestionList){
        for(int i=0;i<myQuestionList.size();i++){
            System.out.println(myQuestionList.get(i).getPrim_id()+",,,,"+this.getId());
            if(myQuestionList.get(i).getPrim_id().equals(this.getId()))
                return true;

        }
        return false;//不包含返回false
    }
}
