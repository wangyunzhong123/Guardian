<%@ page import="com.xd.entity.User" %>
<%@ page import="com.xd.entity.User_to" %>
<%@ page import="com.xd.entity.MyQuestion" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-4-12
  Time: 下午5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--修改--%>
<!DOCTYPE html>
<html>
<head>
    <title>丘比特天天在线</title>

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
    </style>


</head>
<body>
<div class="contentt">
    <%--上部分个人图片--%>
    <%--<h1><%=basePath%></h1>--%>
    <div class="img-content">
        <div class="img-content-img"><img class="img-circle " src="<%=basePath%>resources/img/person.png" width="110px" height="90px"></div>
        <div class="img-content-name"><span>王二小</span></div>
        <div class="img-content-id"><span>ID:32443563</span></div>

    </div>
    <%
        Integer type = (Integer)session.getAttribute("type");
    %>
    <%--导航栏--%>
    <div width="100%" class="cate_tab">
        <input id="tab1" type="radio" name="tabs" checked onclick="tab_change(0,<%=type%>)">
        <label for="tab1">基本信息</label>
        <input id="tab2" type="radio" name="tabs" onclick="tab_change(1,<%=type%>)">
        <label for="tab2">择偶条件</label>
        <input id="tab3" type="radio" name="tabs" onclick="tab_change(2,<%=type%>)">
        <label for="tab3">我问你答</label>
        <a></a>
    </div>

    <%--tab1--%>
    <%
        User user = (User)session.getAttribute("user");
    %>
    <div id=tab11 class="container">


        <!--图片-->
        <div class="img-photo" onclick="window.open('<%=basePath%>pages/browse_photos.jsp','_self')">
            <span>相册</span>
            <a><img id=img-1 class="img-rounded" width="75px" height="75px" src="<%=basePath%>resources/img/person1.jpg"></a>
            <a><img id=img-2 class="img-rounded" width="75px" height="75px" src="<%=basePath%>resources/img/person2.jpg"></a>
            <a><img id=img-3 class="img-rounded" width="75px" height="75px" src="<%=basePath%>resources/img/person3.jpg"></a>
        </div>
        <div class="diver_"></div>
        <div class="mycontent">
            <p id = mycontent>小弟不才将这部时间被打乱的电影依照自己的理解，按事件所发生的先后顺序重
                新排列“剪辑”了一遍，一方面为了和网友们交流一
                下片中难以理解的情节，为还没看此片的朋友提供一些方便，另一方面也是为了
                给自己消耗在该片中的众多脑细胞一个交待喽。${user.dubai}</p>
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

        <%--<div class="line_begin_2">--%>
            <%--&lt;%&ndash;<button type="button" width="50%" class="btn btn-primary">&ndash;%&gt;--%>
            <%--&lt;%&ndash;编辑修改&ndash;%&gt;--%>
            <%--&lt;%&ndash;</button>&ndash;%&gt;--%>
            <%--<a href="<%=basePath%>pages/edit_personalinfo.jsp" class="weui_btn weui_btn_primary">编辑修改</a>--%>

        <%--</div>--%>

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
            <p>小弟不才将这部时间被打乱的电影依照自己的理解，按事件所发生的先后顺序重
                新排列“剪辑”了一遍，一方面为了和网友们交流一
                下片中难以理解的情节，为还没看此片的朋友提供一些方便，另一方面也是为了
                给自己消耗在该片中的众多脑细胞一个交待喽。${user_to.tell_to}</p>
        </div>

        <%--<div class="line_begin_2">--%>
            <%--&lt;%&ndash;<button type="button" width="50%" class="btn btn-primary">&ndash;%&gt;--%>
            <%--&lt;%&ndash;编辑修改&ndash;%&gt;--%>
            <%--&lt;%&ndash;</button>&ndash;%&gt;--%>
            <%--<a href="<%=basePath%>pages/edit_user_to.jsp" class="weui_btn weui_btn_primary">编辑修改</a>--%>
        <%--</div>--%>


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
    <a href="javascript:;" class="to_adb weui_btn weui_btn_disabled weui_btn_primary">关注ta</a>
</div>
</body>
<script src="<%=basePath%>resources/js/jquery.min.js"></script>
<script src="<%=basePath%>resources/js/bootstrap.min.js"></script>
<script src="<%=basePath%>resources/js/zoom.min.js"></script>
<script type="text/javascript">
    $(function(){//初始化
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
        }

        /*控制点击图片放大浏览*/
//    $("#img-1,#img-2,#img-3").imgbox({
//      'speedIn'		: 0,
//      'speedOut'		: 0,
//      'alignment'		: 'center',
//      'overlayShow'	: true,
//      'allowMultiple'	: false
//    });

    })

    function tab_change(which,type){
        var $tab1 = $(document.getElementById("tab11"));
        var $tab2 = $(document.getElementById("tab12"));
        var $tab3 = $(document.getElementById("tab13"));
        if(which!=0 && type ==0){
            alert("请先关注公众号!");
            return;
        }
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
</script>
</html>
