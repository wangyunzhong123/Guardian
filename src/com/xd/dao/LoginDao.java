package com.xd.dao;

import com.xd.entity.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tianxi on 16-3-14.
 */
@Repository("loginDao")
@Transactional
public class LoginDao {

    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory;


    //向题库中添加问题
    public void addQuestion(Question question){
        System.out.println("进去到addQuestion");
        Session session = sessionFactory.getCurrentSession();
        session.save(question);
    }

    //向题库中添加问题item
    public void addQuestionItem(QuestionItem item){
        System.out.println("进去到addQuestion");
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    //根据密码查询用户..其实密码字段存储的是openid
    public User getUserByKey(String key){
        System.out.println("getUserByKey");
        Session session = sessionFactory.getCurrentSession();
        String hql = "from User as u where u.password=:openid";
        Query query = session.createQuery(hql);
        query.setString("openid",key);

        List<User> list=query.list();
        System.out.println("查询发哦user的数量是 "+list.size()+list.get(0).toString());
        if(list !=null)
            return list.get(0);
        else
            return null;
    }

    //保存注册用户
    public User addUser(User user){
        System.out.println("addUser");
        Session session = sessionFactory.getCurrentSession();
//        session.save(user);
        session.saveOrUpdate(user);
        return user;
    }

    //保存注册用户
    public User_to addUser_to(User_to user){
        System.out.println("addUser");
        Session session = sessionFactory.getCurrentSession();
//        session.save(user);
        session.saveOrUpdate(user);
        return user;
    }

    //请求问题列表
    public List<Question> getQuestionlist(){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Question";
        Query query = session.createQuery(hql);

        return query.list();
    }

    //根据id获取题目
    public Question getQuestionByid(int id){
        Session session = sessionFactory.getCurrentSession();
        Question question = (Question) session.get(Question.class,id);
        if(question!=null)
            return question;
        else
            return null;
    }

    //添加我的题目
    public MyQuestion addMyQuestion(MyQuestion myQuestion){
        System.out.println("addMyQuestion");
        Session session = sessionFactory.getCurrentSession();
        session.save(myQuestion);
        return myQuestion;
    }

    //跟新我的题目
    public MyQuestion updateMyQuestion(MyQuestion myQuestion){
        System.out.println("addMyQuestion");
        Session session = sessionFactory.getCurrentSession();
        myQuestion = (MyQuestion) session.merge(myQuestion);
        session.update(myQuestion);
        return myQuestion;
    }

}
