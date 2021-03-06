<%@ page import="com.xd.entity.User" %>
<%@ page import="com.xd.entity.User_to" %>
<%@ page import="com.xd.entity.MyQuestion" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xd.entity.MyImg" %>
<%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-3-14
  Time: 下午5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" autoFlush="false" buffer="512kb"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--修改--%>
<!DOCTYPE html>
<html>
<head>
    <title>丘比特在线</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <%--, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui--%>
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta content="telephone=no" name="format-detection">

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">

    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

    <link href="<%=basePath%>resources/css/weui.min.css" rel="stylesheet">

    <script src="<%=basePath%>resources/js/prefixfree.min.js"></script>

    <script src="<%=basePath%>resources/js/modernizr.js"></script>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath%>resources/css/bootstrap.min.css" rel="stylesheet">

    <style type="text/css">

        .cate_tab{-moz-box-pack: justify;-webkit-box-pack: justify;-webkit-box-lines: single;
            align-content: center;
        }
        .cate_tab li{width: 33% ;text-align:center}
        .cate_tab li a{}

        .tab1{font-size: large; }
        .tab1_edit{float: right;padding-right: 10%}
        .line_begin{padding: 0px 15px 15px 15px;}

        .tab1_ul li{list-style-type:none;height:40px;width: 100%;line-height: 40px;font-size: 16px;font-family: Arial,"微软雅黑",Helvetica,sans-serif}

        .line_begin_1{margin-left: 15px;margin-right: 15px;}
        .line_begin_2{width: 180px;-moz-box-pack: center;-webkit-box-pack: center;margin-left: auto;margin-right: auto;margin-top: 15px;}
        .line_begin_3{width: 100%;-moz-box-pack: center;-webkit-box-pack: center;margin-left: auto;margin-right: auto;margin-top: 15px;}

        .cate_tab label{
            font-weight: bold;
            width: 33.33%;
            font-size: 14px;
            display: block;
            float: left;
            text-align: center;
            padding: 10px ;
            border-top: 2px solid transparent;
            border-right: 1px solid transparent;
            border-left: 1px solid transparent;
            border-bottom: 3px solid transparent;
        }

        .cate_tab input{
            clear: both;
            padding-top: 10px;
            display: none;
            text-align: center;
        }

        .cate_tab label:hover{
            cursor: pointer;
        }
        #tab1:checked ~ #content1, #tab2:checked ~ #content2, #tab3:checked ~ #content3, #tab4:checked ~ #content4 {
            display: block;
        }
        .cate_tab input:checked + label {
            border-top-color: transparent;
            border-right-color: transparent;
            border-left-color: transparent;
            border-bottom-color: #FFB03D;
            text-decoration: transparent;
        }
        .diver{
            width: 75%;
            border-bottom: 1px solid #dddddd;
            margin: 1% 1px 1% auto;
        }
        .diver_{
            width: 100%;
            border-bottom: 1px solid #dddddd;
        }
        .wang_weui_cell{
            width: 100%;
            margin: 10px auto;
        }
        .wang_weui_cell_bd{
            color: #777;
            font-size: 15px;
        }
        .wang_weui_cell_ft{
            color: #222;
            font-size: 16px;
        }
        .img-content{
            width: 100%;
            height: 180px;
            background: url("<%=basePath%>resources/img/bg.jpg");
            filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";
            -moz-background-size:100% 100%;
            background-size:100% 100%;
            text-align: center;
            align-content: center;
        }
        .img-content .img-content-img{
            padding: 20px 0 5px 0;
        }
        .img-content .img-content-img image{
            border: 3px solid #ffffff;
        }
        .img-content .img-content-name{
            font-size: 16px;
            color: #ffffff;
        }
        .img-content .img-content-id{
            font-size: 15px;
            color: #ffffff;
        }
        .img-photo{
            padding: 15px 10px;
            text-align: center;
            align-content: center;
        }
        .img-photo image{

        }
        .mycontent{
            padding: 15px 10px 10px 15px;
        }
        .mycontent a{
            text-align: right;
        }
        .to_adb{
            position: fixed;
            bottom: 15px;
            right: 10px;
            align-content: center;
            text-align: center;
        }
        .contentt{
            margin-bottom: 45px;
            padding-bottom: 45px;
        }
    </style>


    <style type="text/css">
        .tab3_question_title{font-size: larger;margin-left: 4%;margin-top: 20px;}
        .tab3_question_answer{font-size: large;margin: 6%;align-content: center}
        .tab3_question_img{width: 30px;height: 30px;}
        .tab3_question_reanswer{float:right; margin-right: 6%;}
        .tab3_add_question{float:right; margin-top: 4%; margin-right: 6%;width: 90px;}

        .chosepersonimg {
            margin: 10px auto;
            background: transparent;
        }
        .imgimg{
            position: fixed;
            top: 30px;
            right: 10px;
        }

    </style>


</head>

<body>
    <div class="imgimg">
        <div class="chosepersonimg">
            <button id="changepersonimg">更改头像</button>
        </div>
        <div class="chosepersonimg">
            <button id="changebgimg">更改背景</button>
        </div>
        <div class="chosepersonimg">
            <button id="uploadimg">上传图片</button>
        </div>
    </div>

    <div class="contentt">
        <%--上部分个人图片--%>
        <%--<h1><%=basePath%></h1>--%>
            <%
                User user = (User)session.getAttribute("user");
            %>

            <div class="img-content">
                <div class="img-content-img"><img class="img-circle " src="${user.personimg}" width="110px" height="90px"></div>
                <div class="img-content-name"><span>${user.name}</span></div>
                <div class="img-content-id"><span>${user.id}</span></div>

            </div>
            <%--导航栏--%>
            <div width="100%" class="cate_tab">
                <input id="tab1" type="radio" name="tabs" checked onclick="tab_change(0);">
                <label for="tab1">基本信息</label>
                <input id="tab2" type="radio" name="tabs" onclick="tab_change(1);">
                <label for="tab2">择偶条件</label>
                <input id="tab3" type="radio" name="tabs" onclick="tab_change(2);">
                <label for="tab3">我问你答</label>
                <a></a>
            </div>

         <%--tab1--%>
        <div id=tab11 class="container">


            <!--图片-->
            <%--<div class="img-photo" onclick="window.open('<%=basePath%>pages/browse_photos.jsp','_self')">--%>
            <div class="img-photo" onclick="brower_photos();">
                <span>相册</span>
                <a><img id=img-1 class="img-rounded" width="75px" height="75px" src="<%=basePath%>resources/img/person1.jpg"></a>
                <a><img id=img-2 class="img-rounded" width="75px" height="75px" src="<%=basePath%>resources/img/person2.jpg"></a>
                <a><img id=img-3 class="img-rounded" width="75px" height="75px" src="<%=basePath%>resources/img/person3.jpg"></a>
                <%--<a><img id=img-1 class="img-rounded" width="75px" height="75px" src="${imglist[0].url}"></a>--%>
                <%--<a><img id=img-2 class="img-rounded" width="75px" height="75px" src="${imglist[1].url}"></a>--%>
                <%--<a><img id=img-3 class="img-rounded" width="75px" height="75px" src="${imglist[2].url}"></a>--%>
            </div>
            <div class="diver_"></div>
            <div class="mycontent">
                <p id = mycontent>${user.dubai}</p>
                <div style="float: right;margin-right: 15px;margin-bottom: 5px;padding-bottom: 10px;"><a id=moremycontent>更多</a></div>
            </div>
            <div style="width: 100%;padding:8px 0;align-content:center;background-color: #e5e5e5;margin: 5px auto">
                基本信息
            </div>

            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>性别</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user.getSex()%>--%>
                    ${user.sex}
                </div>
            </div>
            <div class="diver">
                </div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>年龄</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user.getBirth()%>--%>
                    ${user.birth}
                </div>
            </div>
            <div class="diver">
            </div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>身高</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user.getHeight()%>--%>
                    ${user.height}
                </div>
            </div>
            <div class="diver">
            </div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>学历</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user.getEducation()%>--%>
                    ${user.education}
                </div>
            </div>
            <div class="diver">
            </div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>职业</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user.getCareer()%>--%>
                    ${user.career}
                </div>
            </div>
            <div class="diver">
            </div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>收入</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user.getIncome()%>--%>
                    ${user.income}
                </div>
            </div>
            <div class="diver">
            </div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>居住</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user.getAddress()%>--%>
                    ${user.address}
                </div>
            </div>
            <div class="diver">
            </div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>籍贯</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user.getLocate()%>--%>
                    ${user.locate}
                </div>
            </div>
            <div class="diver">
            </div>

            <div class="line_begin_2">
                <%--<button type="button" width="50%" class="btn btn-primary">--%>
                    <%--编辑修改--%>
                <%--</button>--%>
                <a href="<%=basePath%>pages/edit_personalinfo.jsp" class="weui_btn weui_btn_primary">编辑修改</a>

            </div>

        </div>



          <%--tab2--%>
         <%
             User_to user_to = (User_to)session.getAttribute("user_to");
         %>
        <div id=tab12 class="container">
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>年龄</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user_to.getAge_start()%>--%>
                    ${user_to.age_start} - ${user_to.age_end}岁
                </div>
            </div><div class="diver"></div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>身高</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user_to.getHeight_start()%>--%>
                    ${user_to.height_start} - ${user_to.height_end} cm
                </div>
            </div><div class="diver"></div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>学历</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user_to.getEducation()%>--%>
                    ${user_to.education}
                </div>
            </div><div class="diver"></div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>收入</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user_to.getIncome_start()%>--%>
                    ${user_to.income_start} - ${user_to.income_end} 元
                </div>
            </div><div class="diver"></div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>居住</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user_to.getAddress()%>--%>
                    ${user_to.address}
                </div>
            </div><div class="diver"></div>
            <div class="row wang_weui_cell">
                <div class="col-xs-3 wang_weui_cell_bd">
                    <p>籍贯</p>
                </div>
                <div class="col-xs-9 wang_weui_cell_ft">
                    <%--<%=user_to.getLocate()%>--%>
                    ${user_to.locate}
                </div>
            </div><div class="diver"></div>
            <div><span >对他/她说:</span></div>
            <div class="mycontent">
                <p>${user_to.tell_to}</p>
            </div>

            <div class="line_begin_2">
                <%--<button type="button" width="50%" class="btn btn-primary">--%>
                    <%--编辑修改--%>
                <%--</button>--%>
                    <a href="<%=basePath%>pages/edit_user_to.jsp" class="weui_btn weui_btn_primary">编辑修改</a>
            </div>


        </div>
        <%--tab3,,我问你答--%>
            <%
                List<MyQuestion> myQuestionSet = (List<MyQuestion>)session.getAttribute("myquestionlist");
            %>
        <div id="tab13">
            <div>
                <a href="<%=basePath%>getquestionlist" class="tab3_add_question weui_btn weui_btn_mini weui_btn_primary">添加题目</a>
            </div>
            <br/>
            <c:forEach items="<%=myQuestionSet%>" var="myquestion" varStatus="vs">
                    <div class="tab3_question_title">
                        <header>${vs.index+1}. ${myquestion.title}</header>
                    </div>
                    <div class="tab3_question_answer">
                        <img src="<%=basePath%>resources/img/smile.jpg" alt="图标" class="tab3_question_img" />
                        <span >${myquestion.myanswer}</span>
                        <div class="tab3_question_reanswer">
                            <a href="<%=basePath%>editmyquestion?question_prim_id=${myquestion.prim_id}&question_id=${myquestion.id}"
                               class="weui_btn weui_btn_mini weui_btn_primary">重答</a>
                        </div>
                    </div>
             </c:forEach>
        </div>

    </div>
    <%--自我推广按钮--%>
    <div class="line_begin_3">
        <a href="javascript:to_adb(<%=user.getId()%>);" class="to_adb weui_btn weui_btn_disabled weui_btn_primary">分享给朋友</a>
    </div>
</body>
<script src="<%=basePath%>resources/js/jquery.min.js"></script>
<script src="<%=basePath%>resources/js/bootstrap.min.js"></script>
<script src="<%=basePath%>resources/js/zoom.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
    $(function(){
        $(document.getElementById("tab12")).hide();
        $(document.getElementById("tab13")).hide();

        /*我的介绍,显示更多*/
//    var text = $("#mycontent").textContent;
        var text = document.getElementById("mycontent");
        var str = text.innerText;
        var ll =35;
        var temp =str.length;
        if( temp> ll){
            text.innerHTML = str.substring(0,ll)+"...";
        }
        var bt = document.getElementById("moremycontent");
        bt.onclick=function () {
            if(bt.innerText=="更多"){
                text.innerHTML = str;
                bt.innerHTML="收起";
            }else {
                text.innerHTML = str.substring(0,ll)+"...";
                bt.innerHTML="更多";
            }
        };

        //初始化背景图和头像,以及个人图片
        $(".img-content").css("background-image","url("+'${user.bgimg}'+")");
        <%
                List<MyImg> myimglist = (List<MyImg>)session.getAttribute("myimglist");
        %>
        //初始化个人照片
        temp = <%=myimglist.size()%> ;
        if(temp >=1){
            $("#img-1").attr("src",${imglist[0]});
        }
        if(temp >=2){
            $("#img-1").attr("src",${imglist[0]});
            $("#img-2").attr("src",${imglist[1]});
        }
        if(temp >=3){
            $("#img-1").attr("src",${imglist[0]});
            $("#img-2").attr("src",${imglist[1]});
            $("#img-3").attr("src",${imglist[2]});
        }
    });


    //微信配置
    //先ajax请求所需的参数
    var sign='';
    var timestamp='';
    var url = location.href.split('#')[0];
//    alert(url);
//    alert(encodeURIComponent(location.href.split('#')[0]));
    $.ajax({
        url:'returnSignature',
        async:false,
        type:'post', //数据发送方式
        dataType:"json",
        data:"url="+encodeURIComponent(location.href.split('#')[0]), //要传递的数据
        success: function(msg){ //成功
            sign = msg.signature;
            timestamp = msg.timestamp;
//            alert("成功"+sign+"  "+timestamp);
        },
        error: function(){ //失败
//           alert('发送ajaxreturnSignature请求失败');
        }
    });

//    alert("时间戳= "+timestamp);
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: 'wx8db58af5e05d7ca6', // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: 'sfsdgsdfwet43gds', // 必填，生成签名的随机串
        signature: sign,// 必填，签名，见附录1
        jsApiList: [
                'onMenuShareTimeline',//分享到朋友圈
                'onMenuShareAppMessage',//分享给朋友
                'onMenuShareQQ',//分享到QQ
                'onMenuShareWeibo',//腾讯微博
                'onMenuShareQZone',//空间
                'chooseImage',//拍照或从手机相册中选图接口
                'previewImage',//预览图片接口
                'uploadImage',//上传图片接口
                'downloadImage'//下载图片
        ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function(){
//       alert("config成功");
        var userid = '<%=user.getId()%>';
        initAllApi(userid);
    });
    wx.error(function(){
//       alert("config失败");
    });

    //个人推广按钮
    function to_adb(userid) {
        alert("请点击菜单中的分享按钮.");
//        initAllApi();
    }

    var urls = ${imglist} ;
    //利用jssdk预览图片
    function brower_photos(){
        wx.previewImage({
            current: urls[0], // 当前显示图片的http链接
            urls: urls // 需要预览的图片http链接列表
        });
    }


    /*<div class="chosepersonimg">
            <button id="changepersonimg">更改头像</button>
            </div>
            <div class="chosepersonimg">
            <button id="changebgimg">更改背景</button>
            </div>
            <div class="chosepersonimg">
            <button id="uploadimg">上传图片</button>
            </div>*/

    //更改背景
    $("#changebgimg").click(function(){
        wx.chooseImage({
            count: 1, // 默认9
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
                var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                //改变背景
                $(".img-content").css("background-image","url("+res.localIds[0]+")");
                startUploadImg(0,res.localIds[0]);
            }
        });
    });
    //改变头像
    $("#changepersonimg").click(function(){
        wx.chooseImage({
            count: 1, // 默认9
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
                var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                //改变背景
                $(".img-circle").attr("src",res.localIds[0]);
                startUploadImg(1,res.localIds[0]);
            }
        });
    });
    //上传个人图片
    $("#uploadimg").click(function(){
        wx.chooseImage({
            count: 9, // 默认9
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
                var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                for(var i=0;i<res.localIds.length;i++){
                    startUploadImg(2,res.localIds[i]);
                }
            }
        });
    });
    //异步上传图片到微信服务器
    function startUploadImg(flag,localid){
        wx.uploadImage({
            localId: localid, // 需要上传的图片的本地ID，由chooseImage接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                var serverId = res.serverId; // 返回图片的服务器端ID
                //再调用自己服务器的请求,自己服务器下载
                downloadImg(flag,serverId);
            }
        });
    }

    //异步请求自己的服务i器资源,下载到服务i器本地
    function downloadImg(flag,serverId){
        $.ajax({
            url:'changebgperson',
            type:'post', //数据发送方式
//                dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
            data:"flag="+flag+"&serverId="+serverId, //要传递的数据
            success: function(msg){ //成功
//                alert(msg);
            },
            error: function(){ //失败
//                    alert('失败');
            }
        });
    }
    function tab_change(which){
        var $tab1 = $(document.getElementById("tab11"));
        var $tab2 = $(document.getElementById("tab12"));
        var $tab3 = $(document.getElementById("tab13"));
        switch(which){
            case 0:
                $tab1.show();
                $tab2.hide();
                $tab3.hide();
                break;
            case 1:
                $tab2.show();
                $tab1.hide();
                $tab3.hide();
                break;
            case 2:
                $tab3.show();
                $tab1.hide();
                $tab2.hide();
                break;
            default:
                break;

        }
    }
    //初始化所有接口
    function initAllApi(userid){
        //分享给朋友
        wx.onMenuShareAppMessage({
            title: '这是我的主页,欢迎来访.', // 分享标题
            desc: '我在丘比特在线,快来加入我们吧!昵称: <%=user.getName()%>;地址: <%=user.getAddress()%>;职业: <%=user.getCareer()%>;教育程度: <%=user.getEducation()%>', // 分享描述
            link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8db58af5e05d7ca6&redirect_uri=http%3a%2f%2fqbt.feite.org%2fCupidDaydayOnline_war%2fweChatFromChare&response_type=code&scope=snsapi_base&state='+userid+'#wechat_redirect', // 分享链接
            imgUrl: '<%=basePath%>resources/img/person.png', // 分享图标
            type: '', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
//            alert("分享成功");
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
                alert("分享失败");
            }
        });
        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: '这是我的主页,欢迎来访.', // 分享标题
            link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8db58af5e05d7ca6&redirect_uri=http%3a%2f%2fqbt.feite.org%2fCupidDaydayOnline_war%2fweChatFromChare&response_type=code&scope=snsapi_base&state='+userid+'#wechat_redirect', // 分享链接
            imgUrl: '<%=basePath%>resources/img/person.png', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        //分享到QQ
        wx.onMenuShareQQ({
            title: '这是我的主页,欢迎来访.', // 分享标题
            desc: '我在丘比特在线,快来加入我们吧!昵称: <%=user.getName()%>;地址: <%=user.getAddress()%>;职业: <%=user.getCareer()%>;教育程度: <%=user.getEducation()%>', // 分享描述
            link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8db58af5e05d7ca6&redirect_uri=http%3a%2f%2fqbt.feite.org%2fCupidDaydayOnline_war%2fweChatFromChare&response_type=code&scope=snsapi_base&state='+userid+'#wechat_redirect', // 分享链接
            imgUrl: '<%=basePath%>resources/img/person.png', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        //QQ空间
        wx.onMenuShareQZone({
            title: '这是我的主页,欢迎来访.', // 分享标题
            desc: '我在丘比特在线,快来加入我们吧!昵称: <%=user.getName()%>;地址: <%=user.getAddress()%>;职业: <%=user.getCareer()%>;教育程度: <%=user.getEducation()%>', // 分享描述
            link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8db58af5e05d7ca6&redirect_uri=http%3a%2f%2fqbt.feite.org%2fCupidDaydayOnline_war%2fweChatFromChare&response_type=code&scope=snsapi_base&state='+userid+'#wechat_redirect', // 分享链接
            imgUrl: '<%=basePath%>resources/img/person.png', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        //腾讯微博
        wx.onMenuShareWeibo({
            title: '这是我的主页,欢迎来访.', // 分享标题
            desc: '我在丘比特在线,快来加入我们吧!昵称: <%=user.getName()%>;地址: <%=user.getAddress()%>;职业: <%=user.getCareer()%>;教育程度: <%=user.getEducation()%>', // 分享描述
            link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8db58af5e05d7ca6&redirect_uri=http%3a%2f%2fqbt.feite.org%2fCupidDaydayOnline_war%2fweChatFromChare&response_type=code&scope=snsapi_base&state='+userid+'#wechat_redirect', // 分享链接
            imgUrl: '<%=basePath%>resources/img/person.png', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    }
</script>
</html>
