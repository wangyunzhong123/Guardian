package com.xd.service;

import com.xd.dao.LoginDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tianxi on 16-3-14.
 */
@Service("loginService")
public class LoginService {

    @Resource(name="loginDao")
    LoginDao userDao;

}
