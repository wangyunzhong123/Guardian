package com.xd.Controller;

import com.xd.entity.Guardian_guardianer;
import com.xd.entity.Guardian_user_profile;
import com.xd.service.GuardianService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.List;

/**
 * Created by tianxi on 16-6-24.
 */
@Controller
public class GuardianController {

    private static Log log = LogFactory.getLog(GuardianController.class);
    @Resource(name="guardianService")
    GuardianService guardianService;

    /*
    * 手环串口守护程序发送来的串口数据
    * */
    @RequestMapping(value="SerialPortData",method={RequestMethod.POST, RequestMethod.GET})
    public void SerialPortData(@RequestParam("ring_id") String ring_id,@RequestParam("command")
        String command,HttpServletRequest request, HttpServletResponse response) throws JSONException,Exception{

        log.info("SerialPortData:"+ ring_id+",,,"+command);
        //获取手环id和指令
        /*
        * command,手环传送来的指令，0x01:主动求救，0x02：自动检测求救，0x03找人
        * */
        JSONObject object = new JSONObject();
        object.put("ring_id",ring_id);
        object.put("command",command);
        //发送到web前台页面
        String relationId ="111";
        int userCode =222;
        String result = ring_id+"&"+command;
//        String message = object.toString();
        String message = result;
        log.info("SerialPortData:发送给web前台,,,"+ message);
        System.out.println("SerialPortData:发送给web前台,,,"+ message);
        if (SessionUtils.hasConnection(relationId, userCode)) {
            SessionUtils.get(relationId, userCode).getAsyncRemote().sendText(message);
        } else {
            throw new NullPointerException(SessionUtils.getKey(relationId, userCode) +"Connection does not exist");
        }

        Guardian_user_profile user = guardianService.getUserByRingid(ring_id);
        if(null !=user){
            String result1 = "3"+"-"+user.getId()+"-"+user.getName()+"-"+user.getBirth()+"-"+user.getSex()+"-"+user.getPhone();
            //发送到平板，利用socket
            log.info("SerialPortData:发送给平板,,,"+ result1);
            System.out.println("SerialPortData:发送给平板,,,"+ result1);
            toPad(result1);
        }else
            log.info("SerialPortData:发送给平板，数据库中找不到与次ringid对应的用户");


    }

    /*
    * 添加用户和手环的对应信息
    * 用户上线
    * */
    @RequestMapping(value="addUser",method={RequestMethod.POST, RequestMethod.GET})
    public String addUser(Guardian_user_profile user,HttpServletRequest request, HttpServletResponse response) throws IOException,Exception{
        int id=guardianService.addUser(user);

        log.info("addUser:"+ user.toString());
        toPad("1-"+id+"-"+user.getName());
        log.info("addUser:发送给pad的上线提醒,,,"+ "1-"+id+"-"+user.getName());
        System.out.println("addUser:发送给pad的上线提醒,,,"+ "1-"+id+"-"+user.getName());
        return "redirect:/listGuardianUser";
    }

    /*
    * 列出所有在线用户
    * */
    @RequestMapping(value="listGuardianUser",method={RequestMethod.POST, RequestMethod.GET})
    public String listGuardianUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Guardian_user_profile> userList = guardianService.getGuardian_user_profile_List();
        request.setAttribute("userList",userList);
        request.setAttribute("userNumber",userList.size());
        log.info("listGuardianUser:");
        System.out.println("listGuardianUser:");
        return "index";
    }

    /*
    * 为平板提供的获取所有在线用户的接口
    * */
    @RequestMapping(value="listGuardianUserPad",method={RequestMethod.POST, RequestMethod.GET})
    public void listGuardianUserPad(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Guardian_user_profile> userList = guardianService.getGuardian_user_profile_List();

        log.info("listGuardianUserPad:");
        JSONArray ret = new JSONArray();
        for(Guardian_user_profile user:userList){
            JSONObject json = null;
            try {
                json = new JSONObject(user.toString().replaceAll("\n", ""));
            } catch (Exception e) {
            }
            ret.put(json);
        }
        log.info("listGuardianUserPad:返回的用户列表串,,,"+ret.toString().getBytes("utf-8"));
        System.out.println("listGuardianUserPad:返回的用户列表串,,,"+ret.toString().getBytes("utf-8"));
        response.getOutputStream().write(ret.toString().getBytes("utf-8"));
    }

    /*
    * 平板请求某一具体用户的信息
    * */
    @RequestMapping(value="getuserByIdUserPad",method={RequestMethod.POST, RequestMethod.GET})
    public void getuserByIdUserPad(@RequestParam("user_id") int userid,HttpServletRequest request, HttpServletResponse response) throws JSONException,IOException{
        Guardian_user_profile user = guardianService.getUserByid(userid);

        log.info("getuserByIdUserPad:"+userid);
        JSONObject ret = new JSONObject(user.toString().replaceAll("\n", ""));
        log.info("getuserByIdUserPad:返回的数据"+ret.toString().getBytes("utf-8"));
        System.out.println("getuserByIdUserPad:返回的数据"+ret.toString().getBytes("utf-8"));
        response.getOutputStream().write(ret.toString().getBytes("utf-8"));
    }

    /*
    * 添加救生员信息
    * */
    @RequestMapping(value="addGuardianerPad",method={RequestMethod.POST, RequestMethod.GET})
    public void addGuardianerPad(Guardian_guardianer user, HttpServletRequest request, HttpServletResponse response) throws JSONException,IOException{
        guardianService.addGuardianer(user);
        log.info("addGuardianerPad:"+user.toString());
        System.out.println("addGuardianerPad:"+user.toString());
        response.getOutputStream().write("OK".getBytes("utf-8"));
    }
    /*
    * 救生员登陆
    * */
    @RequestMapping(value="guardianerLoginPad",method={RequestMethod.POST, RequestMethod.GET})
    public void guardianerLoginPad(@RequestParam("user_id") int userid,@RequestParam("password") String password,HttpServletRequest request, HttpServletResponse response) throws JSONException,IOException{
        Guardian_guardianer user = guardianService.getGuardianerByid(userid);
        log.info("guardianerLoginPad:"+userid+",,,"+password);
        if(null!=user && user.getPassword().equals(password)){
            JSONObject ret = new JSONObject(user.toString().replaceAll("\n", ""));
            response.getOutputStream().write(ret.toString().getBytes("utf-8"));
            log.info("guardianerLoginPad:返回"+ret.toString().getBytes("utf-8"));
            return;
        }
        log.info("guardianerLoginPad:返回"+"no".getBytes("utf-8"));
        System.out.println("guardianerLoginPad:返回"+"no".getBytes("utf-8"));
        response.getOutputStream().write("no".getBytes("utf-8"));
    }

    /*
    * 发送到平板socket
    * */
    private void toPad(String str) throws Exception{
        //为了简单起见，所有的异常都直接往外抛
        String host = guardianService.getHostipByid(1).getHost();  //要连接的服务端IP地址
        int port = 8899;   //要连接的服务端对应的监听端口
        //与服务端建立连接
        Socket client = new Socket(host, port);
        //建立连接后就可以往服务端写数据了
        Writer writer = new OutputStreamWriter(client.getOutputStream());
        writer.write(new String(str.getBytes("utf-8")));
        System.out.println("toPad的str是："+new String(str.getBytes("utf-8")));
        writer.flush();//写完后要记得flush
        writer.close();
        client.close();
    }
    /*
    * 新用户上线
    * */
    private void newUser(Guardian_user_profile user){

    }

    /*
    * 用户下线deleteUser
    * */
    @RequestMapping(value="deleteUser",method={RequestMethod.POST, RequestMethod.GET})
    public void deleteUser(int userid,HttpServletRequest request, HttpServletResponse response) throws IOException,Exception{
        int id=guardianService.deleteUser(userid);

        log.info("deleteUser:"+ userid);
        toPad("2-"+userid);
        log.info("deleteUser:发送给pad的下线提醒,,,"+ "2-"+id);
        System.out.println("deleteUser:发送给pad的下线提醒,,,"+ "2-"+id);
        response.getOutputStream().write("ok".getBytes("utf-8"));
    }
}
