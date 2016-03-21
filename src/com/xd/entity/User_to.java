package com.xd.entity;

import javax.persistence.*;

/**
 * Created by tianxi on 16-3-15.
 */

/*用户要求的对方实体*/


@Entity
@Table(name="USER_TO_T")
public class User_to {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="age_start")
    private String age_start;

    @Column(name="age_end")
    private String age_end;


    @Column(name="height_start")
    private String height_start;

    @Column(name="height_end")
    private String height_end;

    @Column(name="education")
    private String education;

    @Column(name="income_start")
    private String income_start;

    @Column(name="income_end")
    private String income_end;

    @Column(name="address")
    private String address;//居住地

    @Column(name="locate")
    private String locate;//籍贯

    @Column(name="tell_to")
    private String tell_to;//对他说

    public User_to() {
    }

    public User_to(String age_start, String age_end,
                   String height_start, String height_end,
                   String education, String income_start,
                   String income_end, String address, String locate, String tell_to) {
        this.age_start = age_start;
        this.age_end = age_end;
        this.height_start = height_start;
        this.height_end = height_end;
        this.education = education;
        this.income_start = income_start;
        this.income_end = income_end;
        this.address = address;
        this.locate = locate;
        this.tell_to = tell_to;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAge_start() {
        return age_start;
    }

    public void setAge_start(String age_start) {
        this.age_start = age_start;
    }

    public String getAge_end() {
        return age_end;
    }

    public void setAge_end(String age_end) {
        this.age_end = age_end;
    }

    public String getHeight_start() {
        return height_start;
    }

    public void setHeight_start(String height_start) {
        this.height_start = height_start;
    }

    public String getHeight_end() {
        return height_end;
    }

    public void setHeight_end(String height_end) {
        this.height_end = height_end;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getIncome_start() {
        return income_start;
    }

    public void setIncome_start(String income_start) {
        this.income_start = income_start;
    }

    public String getIncome_end() {
        return income_end;
    }

    public void setIncome_end(String income_end) {
        this.income_end = income_end;
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

    public String getTell_to() {
        return tell_to;
    }

    public void setTell_to(String tell_to) {
        this.tell_to = tell_to;
    }

    @OneToOne(mappedBy = "user_to",fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
