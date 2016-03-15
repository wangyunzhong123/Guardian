package com.xd.Controller;

import com.xd.entity.User;
import com.xd.service.LoginService;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public void AddUser_update(int flag, User user, HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        request.setCharacterEncoding("utf-8");//@RequestBody ProductComp aa,
        response.setCharacterEncoding("utf-8");
//        xssfilter(aa);//过滤非法字符
        /*添加成功后返回一个新的页面，页面显示最新的信息*/
    }


}
