package com.xd.Controller;

import com.xd.service.LoginService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by tianxi on 16-3-15.
 */

@Controller
public class UserController {

    @Resource(name="loginService")
    LoginService loginService;

}
