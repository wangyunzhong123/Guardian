package com.xd.Controller;

import com.xd.entity.*;
import com.xd.service.LoginService;
import com.xd.shiro.ShiroLoginUtil;
import com.xd.util.HttpUtil;
import com.xd.util.MyCache;
import org.apache.log4j.Logger;
import org.json.JSONArray;
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

    private static final Logger logger = Logger.getLogger(LoginController.class);

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
        List<MyImg> myImgList = user.getMyImgList();
        System.out.println("请求到的myQuestionsSet个数是  "+myQuestionsSet.size());

        HttpSession session = request.getSession();

        session.setAttribute("user",user);
        session.setAttribute("user_to",user_to);
        session.setAttribute("myquestionlist",myQuestionsSet);
        session.setAttribute("myimglist",myImgList);
        ArrayList imglist = new ArrayList();
        for(int i=0;i<myImgList.size();i++){
            imglist.add(myImgList.get(i).getUrl());
        }
        session.setAttribute("imglist",imglist);

//        session.setAttribute("user",user);
//        session.setAttribute("user_to",user_to);
//        session.setAttribute("myquestionlist",myQuestionsSet);
//        session.setAttribute("type",0);//0表示里面的择偶条件和我问你答tab指向关注引导,
//        request.setAttribute("type",0);
//        return new ModelAndView("pages/other_center");

        //时间戳
        session.setAttribute("timestamp", MyCache.timestamp);

        return new ModelAndView("pages/personal_center");
    }
    //分享后,获取其他人的信息页面
    @RequestMapping(value = "getotheruser",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getotheruser(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

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
        MyQuestion myQuestion = loginService.getMyQuestionById(myquestionid);
        myQuestion.setUser(null);
        loginService.deleteMyQuestion(myQuestion);

        User user1 = ShiroLoginUtil.getCurrentUser();
        User user = loginService.getUserByKey(user1.getPassword());
        user.deleteQuestionItems(myquestionid);
        loginService.addUser(user);

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
//        myQuestion.setUser(user);
        user.addQuestionItem(myQuestion);
        loginService.addUser(user);
//        loginService.addMyQuestion(myQuestion);

        response.getOutputStream().write("添加成功".getBytes("utf-8"));
    }


    //删除我的心仪对象
    @RequestMapping(value="deletemylover",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView deletemylover (int myuser_id,int otheruser_id,int myloverid,HttpServletRequest request, HttpServletResponse response)throws JSONException, IOException {

        logger.fatal("收到取消关注请求!!"+myuser_id+"  "+otheruser_id+"  "+myloverid);

        User myuser = loginService.getUserById(myuser_id);
        User otheruser = loginService.getUserById(otheruser_id);
        MyLover myLover = loginService.getMyLoverById(myloverid);
        myLover.setUser(null);
        //从myuser中删除
        myuser.deleteMyLoverList(myloverid);
        loginService.deleteMyLover(myLover);

        return refreshOther(myuser,otheruser,request,response);
    }
    //增加我的心仪对象
    @RequestMapping(value="addmylove",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView addmylover(int myuser_id,int otheruser_id,HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

//        User user = ShiroLoginUtil.getCurrentUser();
//        myQuestion.setUser(user);
//        loginService.updateMyQuestion(myQuestion);
        logger.fatal("收到添加关注请求!!"+myuser_id+"  "+otheruser_id);
        User myuser = loginService.getUserById(myuser_id);
        User otheruser = loginService.getUserById(otheruser_id);
        MyLover myLover = new MyLover(otheruser_id);
        myLover.setName(otheruser.getName());
        myLover.setAddress(otheruser.getAddress());
        myLover.setBeizhu(otheruser.getDubai());
        myLover.setEducation(otheruser.getEducation());
        myLover.setIncome(otheruser.getIncome());
        myLover.setUser(myuser);

        loginService.addMyLover(myLover);
        myuser.addMyLoverList(myLover);

//        myuser.addMyLoverList(myLover);

        return refreshOther(myuser,otheruser,request,response);
    }
    //关注或者取消关注之后,重新载入页面
    public ModelAndView refreshOther(User existUser,User user,HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        User_to user_to = user.getUser_to();
        List<MyQuestion> myQuestionsSet = user.getItems();
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        session.setAttribute("user_to",user_to);
        session.setAttribute("myquestionlist",myQuestionsSet);
        //添加existuser的ListMyQUestion,用以对比显示
        session.setAttribute("browseruser",existUser);
        logger.fatal("refreshOther:browserquestionlist的个数是 "+existUser.getItems().size());
        //1表示里面的基本信息,择偶条件,均可见,我问你答tab浏览者已经回答的问题可以直接看答案,否则...

        session.setAttribute("type",1);
//                request.setAttribute("type",1);
        //判断是否已经关注
        ShiroLoginUtil.login(existUser);//登陆
        if(existUser.isexist(user)!= -1) {
            int i;
            request.setAttribute("flag", 1);//表明已经关注
            session.setAttribute("flag",1);
            request.setAttribute("myloverid",existUser.isexist(user));

        }else{
            request.setAttribute("flag",0);//没有关注
            session.setAttribute("flag",0);
            request.setAttribute("myloverid",-1);
        }
        request.setAttribute("browseruserid",existUser.getId());
        request.setAttribute("userid",user.getId());

        return new ModelAndView("pages/other_center");

    }

    //增加个人图片
    @RequestMapping(value="addmyimg",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView addmyimg(String serverId,HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        //serverId是微信上传图片后服务器返回的服务器id
        //根据这个id下载图片到自己的服务器本地


        return null;
    }

    //删除图片,还没想好怎么实现

    //更改背景和头像
    @RequestMapping(value="changebgperson",method={RequestMethod.POST,RequestMethod.GET})
    public void changebgperson(int flag,String serverId,HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+MyCache.access_token+"&media_id="+serverId;
        byte[] btImg = HttpUtil.getImageFromNetByUrl(url);
        if(null != btImg && btImg.length > 0){
            logger.fatal("读取到：" + btImg.length + " 字节");
//            String fileName = "百度.gif";
            String filename=UUID.randomUUID().toString()+".jpg";
            HttpUtil.writeImageToDisk(btImg, filename);
            User user = ShiroLoginUtil.getCurrentUser();
            if(flag ==0)
                user.setBgimg(MyCache.img_url+filename);
            if(flag ==1)
                user.setPersonimg(MyCache.img_url+filename);
            //flag==2表示上传的是个人图片
            if(flag ==2){
                MyImg myImg = new MyImg();
                myImg.setUser(user);
                myImg.setUrl(MyCache.img_url+filename);
                user.addMyImg(myImg);
            }
            loginService.addUser(user);
        }else{
            logger.fatal("没有从该连接获得内容");
        }
        response.getOutputStream().write("成功".getBytes("utf-8"));
    }


}
