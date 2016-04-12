package com.xd.dao;

import com.xd.entity.*;
import com.xd.shiro.ShiroLoginUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tianxi on 16-3-14.
 */
@Repository("loginDao")
@Transactional
public class LoginDao extends HibernateDaoSupport{

    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactoryOverride(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    //向题库中添加问题
    public void addQuestion(Question question){
        System.out.println("进去到addQuestion");
//        Session session = sessionFactory.getCurrentSession();
//        session.save(question);

        getHibernateTemplate().save(question);
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
//        System.out.println("查询发哦user的数量是 "+list.size()+list.get(0).toString());
        if(list.size()!=0)
            return list.get(0);
        else
            return null;
    }

    //根据ID查询用户..其实密码字段存储的是openid
    public User getUserById(int id){
        System.out.println("getUserByKey");
        Session session = sessionFactory.getCurrentSession();
        User user = (User)session.get(User.class,id);
        return user;
    }

    //根据userId查询user_to
    public User_to getUser_ToByUserId(int id){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from User_to as u where u.user=:user";
        Query query = session.createQuery(hql);
        query.setEntity("user",ShiroLoginUtil.getCurrentUser());
        List<User_to> list=query.list();
//        System.out.println("查询发哦user的数量是 "+list.size()+list.get(0).toString());
        if(list.size()!=0)
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

    //更新用户
    public User updateUser(User user){
        Session session = sessionFactory.getCurrentSession();
        User u = (User)session.get(User.class,user.getId());
        u.setUser(user.getName(),user.getSex(),user.getBirth(),user.getHeight(),user.getEducation(),user.getCareer(),
                user.getIncome(),user.getAddress(),user.getLocate(),user.getDubai());
        session.saveOrUpdate(u);
        return u;
    }

    //保存
    public User_to addUser_to(User_to user){
        System.out.println("addUser");
        Session session = sessionFactory.getCurrentSession();
//        session.save(user);
        session.saveOrUpdate(user);
        return user;
    }

    //更新
    public User_to updateUser_to(User_to user){
        System.out.println("updateUser_to");
        Session session = sessionFactory.getCurrentSession();
        User_to user_to = (User_to) session.get(User_to.class,user.getId());
//        User_to user_to = new User_to();
//        user_to.setUser(ShiroLoginUtil.getCurrentUser());
        user_to.setUser_to(user.getAge_start(),user.getAge_end(),user.getHeight_start(),user.getHeight_end(),user.getEducation(),
                user.getIncome_start(),user.getIncome_end(),user.getAddress(),user.getLocate(),user.getTell_to());
        session.saveOrUpdate(user_to);
        return user_to;
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
        session.clear();
        return myQuestion;
    }
    //查找我的问题
    public MyQuestion getMyQuestionById(int id){
        return getHibernateTemplate().get(MyQuestion.class,id);
    }
    //删除我的问题
    public MyQuestion deleteMyQuestion(MyQuestion myQuestion){
        System.out.println("deleteMyQuestion");
//        Session session = sessionFactory.getCurrentSession();
//        MyQuestion myQuestion = (MyQuestion) session.get(MyQuestion.class,id);
//        session.delete(myQuestion);
        getHibernateTemplate().delete(myQuestion);
        return null;
    }

    //跟新我的题目
    public MyQuestion updateMyQuestion(MyQuestion myQuestion){
        System.out.println("addMyQuestion");
        Session session = sessionFactory.getCurrentSession();
//        myQuestion = (MyQuestion) session.merge(myQuestion);
        MyQuestion my = (MyQuestion)session.get(MyQuestion.class,myQuestion.getId());
        my.setMyanswer(myQuestion.getMyanswer());
        my.setAcceptanswer(myQuestion.getAcceptanswer());
        my.setImportance(myQuestion.getImportance());

        session.saveOrUpdate(my);
        return my;
    }

}
