package com.xd.Controller;

import com.xd.entity.MyQuestion;
import com.xd.entity.User;
import com.xd.entity.User_to;
import com.xd.service.LoginService;
import com.xd.shiro.ShiroLoginUtil;
import com.xd.util.MyCache;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxi on 16-3-14.
 */
@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

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
//        System.out.println("收到index请求");
        logger.fatal("收到index请求");
        return new ModelAndView("pages/login");
    }

    @RequestMapping(value="toMainInterface",method={RequestMethod.POST,RequestMethod.GET})
    public void toMainInterface(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        System.out.println("收到toMainInterface请求");
    }

    /**
     * 微信授权登录---用户授权（返回code参数）
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="weChatOauth",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView weChatOauth(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
//        System.out.println("收到weChatOauth请求");
//        logger.fatal("收到weChatOauth请求");
//        String code = request.getParameter("code");
//        String state = request.getParameter("state");
//        logger.fatal("code:"+code);
//        logger.fatal("state:"+state);
//        System.out.println("code="+code);
//        if (code.equals("authdeny")){
//            logger.fatal("用户未授权");
//            return null;
//        }
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("code", code);
//        map.put("appid", "wx8db58af5e05d7ca6");
//        map.put("secret", "d698d109e0bc69c09fbb4c5e2e843a3d");
//        map.put("grant_type", "authorization_code");
//        String result = sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", map);
//        System.out.println("access_token==="+result);
//        logger.fatal("access_token:"+result);
//
//        JSONObject object = new JSONObject(result);
//        String openid = object.getString("openid");
//        String access_token = object.getString("access_token");
//        Map<String, String> map1 = new HashMap<String, String>();
//        map1.put("access_token", access_token);
//        map1.put("openid", openid);
//        map1.put("lang", "zh_CN");
//        String info = sendGet("https://api.weixin.qq.com/sns/userinfo", map1);
//        System.out.println("info==="+info);
//        logger.fatal("info:"+info);
//
//        JSONObject personInfo = new JSONObject(info);
//        String nickname = personInfo.getString("nickname");
//        String sex = personInfo.getString("sex");
//        String language = personInfo.getString("language");
//        String city = personInfo.getString("city");
//        String province = personInfo.getString("province");
//        String country = personInfo.getString("country");
//        String headimgurl = personInfo.getString("headimgurl");
//        String privilege = personInfo.getString("privilege");

//        //测试使用
        String openid = "12345";
        String nickname = "wang";
        String sex = "男";
        String city = "北京";

        //完成个人信息请求,开始操作数据库
        User existUser=loginService.getUserByKey(openid);
        if(existUser!=null)
        {
            if(ShiroLoginUtil.passwordsMatch(openid,existUser.getPassword()))
            {
                System.out.println("存在,匹配.");
                ShiroLoginUtil.login(existUser);
//                mv.setViewName("index");
                return new ModelAndView("redirect:/getuser");
            }
            else
            {
                System.out.println("存在,不匹配.");
//                mv.addObject("error", "密码错误！");
//                mv.setViewName("login");
            }
        }
        else//没有,则注册当前用户
        {
//            mv.addObject("error", "用户不存在！");
//            mv.setViewName("login");
            System.out.println("不存在,.");
            User user1 = new User(nickname,openid,sex,city);
            User_to user_to = new User_to();

            loginService.addUser(user1);
            User user = loginService.getUserByKey(user1.getPassword());
            user_to.setUser(user);
            loginService.addUser_to(user_to);

            ShiroLoginUtil.login(user);
            return new ModelAndView("redirect:/getuser");
        }

        System.out.println("!!!!!!!!!!!!");
        return null;
    }

    /***
     * 标志是个人进入个人主页,还是别人根据分享链接进来的,
     如果是别人的分享链接,如果已注册,则打开other_center.jsp,里面的基本信息,择偶条件,均可见,我问你答tab浏览者已经回答的问题可以直接看答案,否则...
                        如果未注册,则里面的择偶条件和我问你答tab指向关注引导,
     如果是自己进入,则正常进入个人主页
     */

    @RequestMapping(value="weChatFromChare",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView weChatFromChare(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        //可以直接获取openid
        String openid = request.getParameter("openid");
        String state = request.getParameter("state");//就是要查看用户的id
        logger.fatal("weChatFromChare获取的openid= :"+openid+"  "+state);

        User existUser=loginService.getUserByKey(openid);
        if(existUser == null){//表示该用户还没授权(我们的实现流程是,只要授权,就自动使用openid为该用户注册,注册)
            //则other_center页面,只显示基本信息,......

            //获取指定用户的info
            User user = loginService.getUserById(Integer.valueOf(state));
            User_to user_to = user.getUser_to();
            List<MyQuestion> myQuestionsSet = user.getItems();
            System.out.println("请求到的myQuestionsSet个数是  "+myQuestionsSet.size());
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            session.setAttribute("user_to",user_to);
            session.setAttribute("myquestionlist",myQuestionsSet);
            session.setAttribute("type",0);//0表示里面的择偶条件和我问你答tab指向关注引导,
            return new ModelAndView("other_center");
        }else{//该用户授权过
            if(existUser.getId() == Integer.valueOf(state)){//是本人
                ShiroLoginUtil.login(existUser);
                return new ModelAndView("redirect:/getuser");
            }else{//已经注册的外人看的
                //获取指定用户的info
                User user = loginService.getUserById(Integer.valueOf(state));
                User_to user_to = user.getUser_to();
                List<MyQuestion> myQuestionsSet = user.getItems();
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                session.setAttribute("user_to",user_to);
                session.setAttribute("myquestionlist",myQuestionsSet);
                session.setAttribute("type",1);//1表示里面的基本信息,择偶条件,均可见,我问你答tab浏览者已经回答的问题可以直接看答案,否则...
                return new ModelAndView("other_center");
            }
        }
    }

    /**
     * 发送GET请求
     *
     * @param url
     *            目的地址
     * @param parameters
     *            请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendGet(String url, Map<String, String> parameters) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;
            System.out.println(full_url);
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(full_url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 模拟微信服务器返回access_token
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="returnAccessToken",method={RequestMethod.POST,RequestMethod.GET})
    public void returnAccessToken(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        System.out.println("收到returnAccessToken请求");
        String result = "access_token";
        response.getWriter().print(result);
        response.getWriter().flush();
        response.getWriter().close();
    }

    //前端使用JSSDK需要获取签名,签名由后台生成,并有一定的实
    // 效性,为2个小时.所以还要做服务器端缓存,时间问两个小时,超过就重新获取
    @RequestMapping(value="returnSignature",method = {RequestMethod.POST,RequestMethod.GET})
    public void returnSignature(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException{

        response.getOutputStream().write(MyCache.getInstance().signature.getBytes("utf-8"));
        logger.fatal("请求的returnSignature,返回 "+MyCache.getInstance().signature);

    }

}
