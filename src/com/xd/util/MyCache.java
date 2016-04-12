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

    private static final Logger logger = Logger.getLogger(LoginController.class);
    public static String appid = "wx8db58af5e05d7ca6";
    public static String secret = "d698d109e0bc69c09fbb4c5e2e843a3d";

    //获取ticket的url
    public static String getTicket_Access_token_Url = "https://api.weixin.qq.com/cgi-bin/token";
    //获取
    public static String getTicket_Ticket_Url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    private static String noncestr="sfsdgsdfwet43gds3554tsg54";//随机字符串
    private static String jsapi_ticket;//
    private static String timestamp="2341243534";//时间戳
    private static String url = "http://qbt.feite.org/CupidDaydayOnline_war/getuser";//

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
                String access_token = null;
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
                //拼接
                temp = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;

                //得到签名
                signature = new SHA1().getDigestOfString(temp.getBytes());
                logger.fatal("获取jssDK的 signature: "+signature);

            }
        }, 1000, 1000*3600*2);
    }

    private static MyCache myCache = new MyCache();

    public static MyCache getInstance(){
        return myCache;
    }

    //测试
//    public static void main(String[] args) {
//
//    }
}
