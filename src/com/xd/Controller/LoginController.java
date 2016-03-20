package com.xd.Controller;

import com.xd.service.LoginService;
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
        System.out.println("收到index请求");
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
        System.out.println("收到weChatOauth请求");
        logger.fatal("收到weChatOauth请求");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.fatal("code:"+code);
        logger.fatal("state:"+state);
        System.out.println("code="+code);
        if (code.equals("authdeny")){
            logger.fatal("用户未授权");
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("appid", "wx698530e78c6c010a");
        map.put("secret", "f1413d92a240e3482153754c953eeab5");
        map.put("grant_type", "authorization_code");
        String result = sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", map);
        System.out.println("access_token==="+result);
        logger.fatal("access_token:"+result);

        JSONObject object = new JSONObject(result);
        String openid = object.getString("openid");
        String access_token = object.getString("access_token");
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("access_token", access_token);
        map1.put("openid", openid);
        map1.put("lang", "zh_CN");
        String info = sendGet("https://api.weixin.qq.com/sns/userinfo", map1);
        System.out.println("info==="+info);
        logger.fatal("info:"+info);

        ModelAndView mv = new ModelAndView("pages/weChatOauthTest");
        mv.addObject("result", info);
        logger.fatal("mv:"+"返回modelandview");
        return mv;
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

}
