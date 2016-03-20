package com.xd.service;

import com.xd.dao.LoginDao;
import com.xd.entity.Question;
import com.xd.entity.QuestionItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tianxi on 16-3-14.
 */
@Service("loginService")
public class LoginService {

    @Resource(name="loginDao")
    LoginDao userDao;

    public void addQuestion(Question question){
        userDao.addQuestion(question);
    }

    public void addQuestionItem(QuestionItem item){
        userDao.addQuestionItem(item);
    }

}
