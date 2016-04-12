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

    //根据ID查询用户..其实密码字段存储的是openid
    public User getUserById(int id){
        return userDao.getUserById(id);
    }

    //根据userId查询user_to
    public User_to getUser_ToByUserId(int id){
        return userDao.getUser_ToByUserId(id);
    }

    //保存注册用户
    public User addUser(User user){
        return userDao.addUser(user);
    }

    //更新用户
    public User updateUser(User user){
        return userDao.updateUser(user);
    }
    //保存注册用户
    public User_to addUser_to(User_to user){
        return userDao.addUser_to(user);
    }

    //更新
    public User_to updateUser_to(User_to user){
        return userDao.updateUser_to(user);
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
    //删除我的问题
    public MyQuestion deleteMyQuestion(MyQuestion myQuestion){
        return userDao.deleteMyQuestion(myQuestion);
    }
    //查找我的问题
    public MyQuestion getMyQuestionById(int id){
        return userDao.getMyQuestionById(id);
    }

}
