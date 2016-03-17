package com.xd.Controller;

import com.xd.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tianxi on 16-3-14.
 */
@Controller
public class LoginController {

    @Resource(name="loginService")
    LoginService loginService;

    @RequestMapping(value="index.htm",method={RequestMethod.POST,RequestMethod.GET})
    public void testReturnUser(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
//        return new ModelAndView("index");
//        Log4JLogger.
        System.out.println("收到index.htm请求");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("hello");
    }

    @RequestMapping(value="index",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView testReturnUser1(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        System.out.println("收到index请求");
        return new ModelAndView("pages/login");
    }
    /**
     *
     */

}
