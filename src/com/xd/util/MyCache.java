package com.xd.util;

import com.xd.Controller.LoginController;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tianxi on 16-4-12.
 */

/*
* 单例的自定义缓存实现,用于缓存JSSDK的签名,
* 并每两个小时更新*/
public class MyCache {

    //配置上传图片在服务器的地址
    public static String img_url = "http://qbt.feite.org:4000/img/";
    private static final Logger logger = Logger.getLogger(LoginController.class);
    public static String appid = "wx8db58af5e05d7ca6";
    public static String secret = "d698d109e0bc69c09fbb4c5e2e843a3d";

    //获取ticket的url
    public static String getTicket_Access_token_Url = "https://api.weixin.qq.com/cgi-bin/token";
    //获取
    public static String getTicket_Ticket_Url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    private static String noncestr="sfsdgsdfwet43gds";//随机字符串
    public static String access_token;
    private static String jsapi_ticket;//
    public static String timestamp = "";//时间戳
//    public static String url = "http://qbt.feite.org/CupidDaydayOnline_war/getuser";//
    public static String url = "http://qbt.feite.org/CupidDaydayOnline_war/getuser";//

    public static String signature;//生成的签名

//    public static
    private MyCache(){//throws JSONException, IOException
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
                //先获取ticket
                Map<String,String> content = new HashMap<String, String>();
                content.put("grant_type","client_credential");
                content.put("appid",appid);
                content.put("secret",secret);
                String info1 = HttpUtil.sendGet(getTicket_Access_token_Url,content);
                String info2 = null;
                String temp = null;
                try {
                    access_token = new JSONObject(info1).getString("access_token");
                    logger.fatal("获取jssDK的 access_token: "+access_token);

                    Map<String,String> con = new HashMap<String, String>();
                    con.put("access_token",access_token);
                    con.put("type","jsapi");
                    info2 = HttpUtil.sendGet(getTicket_Ticket_Url,con);
                    if(Integer.valueOf(new JSONObject(info2).getString("errcode")) == 0){
                        temp = new JSONObject(info2).getString("ticket");
                        logger.fatal("获取jssDK的 ticket: "+temp);
                    }
                }catch (JSONException e){
                }
                //
                jsapi_ticket = temp;

            }
        }, 100, 1000*3600*2);
    }

    private static MyCache myCache = new MyCache();

    public static MyCache getInstance(){
        if(myCache != null)
            return myCache;
        return new MyCache();
    }

    //动态根据url获取signature
    public static String retureSignature(){
        timestamp = String.valueOf(System.currentTimeMillis()/1000);
        //拼接
        String temp = "jsapi_ticket="+jsapi_ticket+
                "&noncestr="+noncestr+
                "&timestamp="+timestamp+
                "&url="+url;
        //得到签名
        signature = new SHA1().getDigestOfString(temp.getBytes());
        logger.fatal("获取jssDK的 signature: "+signature);
        return signature;
    }
    //测试
//    public static void main(String[] args) {
//
//    }
}
