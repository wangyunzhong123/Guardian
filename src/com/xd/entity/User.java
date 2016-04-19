package com.xd.entity;


import org.apache.commons.logging.Log;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tianxi on 16-3-15.
 */


/*用户本人*/
@Entity
@Table(name="USER_T")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="userid")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="sex")
    private String sex;

    @Column(name="birth")
    private String birth;

    @Column(name="height")
    private String height;

    @Column(name="education")
    private String education;

    @Column(name="career")
    private String career;

    @Column(name="income")
    private String income;

    @Column(name="address")
    private String address;//居住地

    @Column(name="locate")
    private String locate;//籍贯

    @Column(name="dubai")
    private String dubai;//内心独白


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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getDubai() {
        return dubai;
    }

    public void setDubai(String dubai) {
        this.dubai = dubai;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public User(String name, String password, String sex, String birth,
                String height, String education, String career, String income,
                String address, String locate, String dubai) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.height = height;
        this.education = education;
        this.career = career;
        this.income = income;
        this.address = address;
        this.locate = locate;
        this.dubai = dubai;
    }
    public void setUser(String name, String sex, String birth,
                String height, String education, String career, String income,
                String address, String locate, String dubai) {
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.height = height;
        this.education = education;
        this.career = career;
        this.income = income;
        this.address = address;
        this.locate = locate;
        this.dubai = dubai;
    }

    public User() {
    }

    public User(String name, String password, String sex, String address) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.address = address;
    }


    //
    @OneToOne(cascade = {CascadeType.ALL},mappedBy = "user",fetch = FetchType.EAGER)
    private User_to user_to;
    public User_to getUser_to() {
        return user_to;
    }

    public void setUser_to(User_to user_to) {
        this.user_to = user_to;
    }

    @OneToMany(cascade = {CascadeType.ALL},
            mappedBy = "user",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<MyQuestion> items = new ArrayList<MyQuestion>();

    public List<MyQuestion> getItems() {
        return items;
    }

    public void setItems(List<MyQuestion> items) {
        this.items = items;
    }
    //
    public void deleteQuestionItems(int id){
        for(int i=0;i<items.size();i++){
            if(items.get(i).getId() == id){
                items.get(i).setUser(null);
                items.remove(i);
                System.out.println("删除我的问题index=  "+i);

            }

        }
    }
    /*
    该方法用于向里面添加item
    * */
    public void addQuestionItem(MyQuestion item){
        item.setUser(this);
        this.items.add(item);
    }

    //我的心仪对象
    @OneToMany(cascade = {CascadeType.ALL},
            mappedBy = "user",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<MyLover> myLoverList = new ArrayList<MyLover>();

    public List<MyLover> getMyLoverList() {
        return myLoverList;
    }

    public void setMyLoverList(List<MyLover> items) {
        this.myLoverList = items;
    }
    //
    public void deleteMyLoverList(int id){
        for(int i=0;i<myLoverList.size();i++){
            if(items.get(i).getId() == id){
                items.get(i).setUser(null);
                items.remove(i);
                System.out.println("删除我的lover=  "+i);

            }

        }
    }
    /*
    该方法用于心仪对象
    * */
    public void addMyLoverList(MyLover item){
        item.setUser(this);
        this.myLoverList.add(item);
    }
    /*
    * 判断mylover是否已经关注
    * */
    public int isexist(User user){
        for(MyLover myLover1:myLoverList){
            if(myLover1.getPrim_id().equals(user.getId()))
                return myLover1.getId();
        }
        return -1;
    }

    /*
    判断当前题库中的question是否在我的question列表里面
    * */
    public boolean isexist(Question question){
        for(MyQuestion myQuestion:items){
            if(myQuestion.getPrim_id().equals(question.getId()))
                return true;
        }
        return false;
    }

    /*
    * 判断给定问题是否已在我的问题列表中,用于浏览其他用户信息是显示他们都回答过的问题答案
    *
    * */
    public MyQuestion getCommonMyquestion(MyQuestion myQuestion){
        for(MyQuestion myQuestion1:items){
            if(myQuestion1.isexist(myQuestion))
                return myQuestion1;
        }
        return null;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", height='" + height + '\'' +
                ", education='" + education + '\'' +
                ", career='" + career + '\'' +
                ", income='" + income + '\'' +
                ", address='" + address + '\'' +
                ", locate='" + locate + '\'' +
                ", dubai='" + dubai + '\'' +
                ", user_to=" + user_to +
                ", items=" + items +
                '}';
    }
}
