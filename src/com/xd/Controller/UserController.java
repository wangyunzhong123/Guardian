package com.xd.Controller;

import com.xd.entity.User;
import com.xd.entity.User_to;
import com.xd.service.LoginService;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by tianxi on 16-3-15.
 */

@Controller
public class UserController {

    @Resource(name="loginService")
    LoginService loginService;


    /*新增用户*/
    @RequestMapping(value="adduser",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView AddUser_update(User user, HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        request.setCharacterEncoding("utf-8");//@RequestBody ProductComp aa,
        response.setCharacterEncoding("utf-8");
        System.out.println(user.toString());


        User_to user_to = new User_to("1","2","3","4","5","6","7","8","9","10");
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        session.setAttribute("user_to",user_to);

        return new ModelAndView("pages/personal_center");

//        xssfilter(aa);//过滤非法字符
        /*添加成功后返回一个新的页面，jsp页面显示最新的信息*/
    }


    /*新增用户to*/
    @RequestMapping(value="adduser_to",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView AddUser_update_to(User_to user_to, HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        request.setCharacterEncoding("utf-8");//@RequestBody ProductComp aa,
        response.setCharacterEncoding("utf-8");
        System.out.println(user_to.toString());


        User user = new User("1","2","3","4","5","6","7","8","9","10","11");

        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        session.setAttribute("user_to",user_to);
        return new ModelAndView("pages/personal_center");
    }


    @RequestMapping(value = "getuser",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        System.out.println("进入到getuser");

        User user = new User("1","2","3","4","5","6","7","8","9","10","11");

        User_to user_to = new User_to("1","2","3","4","5","6","7","8","9","10");

        Cookie cookie[] = request.getCookies();



        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        session.setAttribute("user_to",user_to);

        return new ModelAndView("pages/personal_center");
    }


}
