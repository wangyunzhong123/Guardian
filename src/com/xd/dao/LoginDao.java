package com.xd.dao;

import com.xd.entity.Question;
import com.xd.entity.QuestionItem;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
