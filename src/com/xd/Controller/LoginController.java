package com.xd.Controller;

import com.xd.service.LoginService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by tianxi on 16-3-14.
 */
@Controller
public class LoginController {

    @Resource(name="LoginService")
    LoginService loginService;

}
