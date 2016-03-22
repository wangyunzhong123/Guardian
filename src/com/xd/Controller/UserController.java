package com.xd.Controller;

import com.xd.entity.*;
import com.xd.service.LoginService;
import com.xd.shiro.ShiroLoginUtil;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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

        User u = ShiroLoginUtil.getCurrentUser();
        user.setId(u.getId());
        loginService.updateUser(user);
//        User_to user_to = user.getUser_to();
//        HttpSession session = request.getSession();
//        session.setAttribute("user",user);
//        session.setAttribute("user_to",user_to);

        return new ModelAndView("redirect:/getuser");

//        xssfilter(aa);//过滤非法字符
        /*添加成功后返回一个新的页面，jsp页面显示最新的信息*/
    }


    /*新增用户to,,心仪对象实体*/
    @RequestMapping(value="adduser_to",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView AddUser_update_to(User_to user_to,HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        request.setCharacterEncoding("utf-8");//@RequestBody ProductComp aa,
        response.setCharacterEncoding("utf-8");
        System.out.println("得到的user_to 的ID是,,,"+user_to.toString()+"  ");

        User user1 = ShiroLoginUtil.getCurrentUser();

        User user = loginService.getUserByKey(user1.getPassword());
        user_to.setUser(user);
        loginService.updateUser_to(user_to);

        return new ModelAndView("redirect:/getuser");
    }


    @RequestMapping(value = "getuser",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        User user1= ShiroLoginUtil.getCurrentUser();
        User user = loginService.getUserById(user1.getId());

        User_to user_to = user.getUser_to();
        List<MyQuestion> myQuestionsSet = user.getItems();
        System.out.println("请求到的myQuestionsSet个数是  "+myQuestionsSet.size());

        HttpSession session = request.getSession();

        session.setAttribute("user",user);
        session.setAttribute("user_to",user_to);
        session.setAttribute("myquestionlist",myQuestionsSet);

        return new ModelAndView("pages/personal_center");
    }

    //获取重答的我的题目页面
    @RequestMapping(value="editmyquestion",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView editmyquestion(int question_prim_id, int question_id,HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {


//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
//        EntityManager em = factory.createEntityManager();
//        em.getTransaction().begin();


        Question question = loginService.getQuestionByid(question_prim_id);

//        em.persist(question);
//        em.getTransaction().commit();
//        em.close();
//        factory.close();

        request.setAttribute("question",question);
        request.setAttribute("myquestion_id",question_id);

        return new ModelAndView("pages/edit_myquestion");
    }

    //删除我的题目
    @RequestMapping(value="deletemyquestion",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView deletemyquestion (int myquestionid,HttpServletRequest request, HttpServletResponse response){
        loginService.deleteMyQuestion(myquestionid);
        return new ModelAndView("redirect:/getuser");
    }
    //编辑我的题目保存
    @RequestMapping(value="editmyquestion_save",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView editmyquestion_save(int question_id,MyQuestion myQuestion,HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        User user = ShiroLoginUtil.getCurrentUser();
        myQuestion.setUser(user);
        loginService.updateMyQuestion(myQuestion);

        return new ModelAndView("redirect:/getuser");
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


    //管理员向题库中添加题目
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

    //用户添加问题的时候,请求页面
    @RequestMapping(value = "getquestionlist",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getquestionlist(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        List<Question> questionList = loginService.getQuestionlist();
        User user1 = ShiroLoginUtil.getCurrentUser();
        User user = loginService.getUserByKey(user1.getPassword());
        List<MyQuestion> myQuestionList = user.getItems();
        //遍历题库中的题目是或否已在我的题目中
        List<Integer> result = new ArrayList<Integer>();
        int result1[] = new int[questionList.size()];
        for(int i=0;i<questionList.size();i++){
            if(questionList.get(i).isexistMyQuestionList(myQuestionList)){
                result.add(1);
                result1[i]=1;
            }
            else{
                result.add(0);
                result1[i]=0;
            }
        }
        for(int j=0;j<result.size();j++)
            System.out.println(result.get(j));
        request.setAttribute("questionlist",questionList);
        request.setAttribute("result",result);
        request.setAttribute("result1",result1);
        return new ModelAndView("pages/add_myquestion");
    }

    //用户添加题目
    @RequestMapping(value = "addmyquestion",method={RequestMethod.POST,RequestMethod.GET})
    public void addmyquestion(@RequestParam("questionid") int questionid, HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        //判断是否已经在我的题目中
        User user1 = ShiroLoginUtil.getCurrentUser();
        User user = loginService.getUserByKey(user1.getPassword());
        List<MyQuestion> myQuestionList = user.getItems();
        for(int i=0;i<myQuestionList.size();i++){
            if(myQuestionList.get(i).getPrim_id().equals(questionid)){
                response.getOutputStream().write("已存在".getBytes("utf-8"));
                return;
            }
        }
        Question question = loginService.getQuestionByid(questionid);
        MyQuestion myQuestion = new MyQuestion(question.getId(),question.getTitle());
        myQuestion.setUser(user);
//        loginService.addUser(user);
        loginService.addMyQuestion(myQuestion);

        response.getOutputStream().write("添加成功".getBytes("utf-8"));
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
