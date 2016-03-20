package com.xd.Controller;

import com.xd.entity.*;
import com.xd.service.LoginService;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

        setMyquestion(request);//测试使用,静态数据

        return new ModelAndView("pages/personal_center");

//        xssfilter(aa);//过滤非法字符
        /*添加成功后返回一个新的页面，jsp页面显示最新的信息*/
    }


    /*新增用户to,,心仪对象实体*/
    @RequestMapping(value="adduser_to",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView AddUser_update_to(User_to user_to, HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        request.setCharacterEncoding("utf-8");//@RequestBody ProductComp aa,
        response.setCharacterEncoding("utf-8");
        System.out.println(user_to.toString());


        User user = new User("1","2","3","4","5","6","7","8","9","10","11");

        setMyquestion(request);//测试使用,静态数据


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

        setMyquestion(request);//测试使用,静态数据

        session.setAttribute("user",user);
        session.setAttribute("user_to",user_to);

        return new ModelAndView("pages/personal_center");
    }

    @RequestMapping(value="editmyquestion",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView editmyquestion(String question_prim_id, HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {


//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
//        EntityManager em = factory.createEntityManager();
//        em.getTransaction().begin();

        QuestionItem item1 = new QuestionItem("非常讨厌");
        QuestionItem item2 = new QuestionItem("讨厌");
        QuestionItem item3 = new QuestionItem("喜欢");
        QuestionItem item4 = new QuestionItem("非常喜欢");

        Question question = new Question("你喜欢宋仲基吗?");
        question.addQuestionItem(item1);
        question.addQuestionItem(item2);
        question.addQuestionItem(item3);
        question.addQuestionItem(item4);

//        em.persist(question);
//        em.getTransaction().commit();
//        em.close();
//        factory.close();

        request.setAttribute("question",question);

        return new ModelAndView("pages/edit_myquestion");
    }


    @RequestMapping(value = "getmylover",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getmylover(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        List<MyLover> myLoverList = new ArrayList<MyLover>();
        myLoverList.add(new MyLover("萧十一郎","上海","研究生","10K+","2016.3.18更新了个人信息"));
        myLoverList.add(new MyLover("萧十一郎","上海","研究生","10K+","2016.3.18更新了个人信息"));
        myLoverList.add(new MyLover("萧十一郎","上海","研究生","10K+","2016.3.18更新了个人信息"));
        myLoverList.add(new MyLover("萧十一郎","上海","研究生","10K+","2016.3.18更新了个人信息"));
        myLoverList.add(new MyLover("萧十一郎","上海","研究生","10K+","2016.3.18更新了个人信息"));
        myLoverList.add(new MyLover("萧十一郎","上海","研究生","10K+","2016.3.18更新了个人信息"));
        myLoverList.add(new MyLover("萧十一郎","上海","研究生","10K+","2016.3.18更新了个人信息"));

        request.setAttribute("myloverlist",myLoverList);
        return new ModelAndView("pages/my_lover");
    }


    @RequestMapping(value = "addquestion",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView addquestion(Question question, HashSet<QuestionItem> items, HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        System.out.println("addquestion");
        System.out.println(question);
        System.out.println(items);


//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
//        EntityManager em = factory.createEntityManager();
//        em.getTransaction().begin();

        //遍历set
        Iterator<QuestionItem> it = items.iterator();
        int i=0;
        while(it.hasNext()){
            QuestionItem item = it.next();
            System.out.println(String.valueOf(i++)+item);

            question.addQuestionItem(item);
        }

        loginService.addQuestion(question);//需要先存储
        String context[] = request.getParameterValues("context");
        for(int j=0;j<context.length;j++){
            System.out.println(j+" ,"+context[j]);
            QuestionItem item = new QuestionItem(context[j]);

            item.setQuestion(question);
//            question.addQuestionItem(item);
            loginService.addQuestionItem(item);
        }
//        loginService.addQuestion(question);

//        em.persist(question);
//        em.getTransaction().commit();
//        em.close();
//        factory.close();

        return new ModelAndView("pages/add_question");
    }

    public void setMyquestion(HttpServletRequest request){

        List<MyQuestion> myQuestion = new ArrayList<MyQuestion>();
        myQuestion.add(new MyQuestion(1,"你喜欢吃苹果吗?","喜欢","都行","无关紧要"));
        myQuestion.add(new MyQuestion(1,"你喜欢吃苹果吗?","喜欢","都行","无关紧要"));
        myQuestion.add(new MyQuestion(1,"你喜欢吃苹果吗?","喜欢","都行","无关紧要"));
        myQuestion.add(new MyQuestion(1,"你喜欢吃苹果吗?","喜欢","都行","无关紧要"));
        myQuestion.add(new MyQuestion(1,"你喜欢吃苹果吗?","喜欢","都行","无关紧要"));
        myQuestion.add(new MyQuestion(1,"你喜欢吃苹果吗?","喜欢","都行","无关紧要"));
        request.setAttribute("questionlist",myQuestion);

    }


}
