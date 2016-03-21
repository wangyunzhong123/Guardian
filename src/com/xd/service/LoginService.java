package com.xd.service;

import com.xd.dao.LoginDao;
import com.xd.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public User getUserByKey(String key){
       return userDao.getUserByKey(key);
    }

    //保存注册用户
    public User addUser(User user){
        return userDao.addUser(user);
    }

    //保存注册用户
    public User_to addUser_to(User_to user){
        return userDao.addUser_to(user);
    }

    //请求问题列表
    public List<Question> getQuestionlist(){
        return userDao.getQuestionlist();
    }

    //根据id获取题目
    public Question getQuestionByid(int id){
        return userDao.getQuestionByid(id);
    }

    //添加我的题目
    public MyQuestion addMyQuestion(MyQuestion myQuestion){
        return userDao.addMyQuestion(myQuestion);
    }

    //跟新我的题目
    public MyQuestion updateMyQuestion(MyQuestion myQuestion){
        return userDao.updateMyQuestion(myQuestion);
    }

}
