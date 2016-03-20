<%@ page import="com.xd.entity.User" %>
<%@ page import="com.xd.entity.User_to" %>
<%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-3-14
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
<html>
<head>
    <title>丘比特天天在线</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
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


    <style type="text/css">
        .cate_tab{-moz-box-pack: justify;-webkit-box-pack: justify;-webkit-box-lines: single|multiple}
        .cate_tab li{width: 33% ;text-align:center}
        .cate_tab li a{}

        .tab1{font-size: large; }
        .tab1_edit{float: right;padding-right: 10%}
        .line_begin{padding: 0px 15px 15px 15px;}

        .tab1_ul li{list-style-type:none;height:40px;width: 100%;line-height: 40px;font-size: 16px;font-family: Arial,"微软雅黑",Helvetica,sans-serif}

        .line_begin_1{margin-left: 15px;margin-right: 15px;}
        .line_begin_2{width: 180px;-moz-box-pack: center;-webkit-box-pack: center;margin-left: auto;margin-right: auto;margin-top: 15px;}
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
    <div>
        <%--上部分个人图片--%>
        <%--<h1><%=basePath%></h1>--%>
        <div>
            <img src="<%=basePath%>resources/img/person.jpg" alt="个人图片" class="img-rounded" width="100%" height="180px" >
        </div>
            <%--导航栏--%>
        <div width="100%" >
            <ul class="nav nav-pills cate_tab" width="100%">
                <li  id="li_0"><a href="#" onclick="tab_change(0)">基本信息</a></li>
                <li class="active" id="li_1"><a href="#"  onclick="tab_change(1)">择偶条件</a></li>
                <li id="li_2" ><a href="#"  onclick="tab_change(2)">我问你答</a></li>
            </ul>
        </div>

         <%--tab1--%>
         <%
             User user = (User)session.getAttribute("user");


         %>
        <div id=tab1 class="weui_cells">
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>性别</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user.getSex()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>出生日期</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user.getBirth()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>身高</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user.getHeight()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>学历</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user.getEducation()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>职业</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user.getCareer()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>收入</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user.getIncome()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>居住地</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user.getAddress()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>籍贯</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user.getLocate()%>
                </div>
            </div>

            <span class="line_begin" >内心独白:</span>
            <div width="100%" >
                <textarea type="text" class="form-control line_begin_1 " rows=6 placeholder="填写内心独白" ><%=user.getDubai()%></textarea>
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
        <div id=tab2 class="weui_cells">
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>年龄</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user_to.getAge_start()%>
                </div>---
                <div class="weui_cell_ft">
                    <%=user_to.getAge_end()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>身高</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user_to.getHeight_start()%>
                </div>---
                <div class="weui_cell_ft">
                    <%=user_to.getHeight_end()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>学历</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user_to.getEducation()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>收入</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user_to.getIncome_start()%>
                </div>---
                <div class="weui_cell_ft">
                    <%=user_to.getIncome_end()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>居住地</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user_to.getAddress()%>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <p>籍贯</p>
                </div>
                <div class="weui_cell_ft">
                    <%=user_to.getLocate()%>
                </div>
            </div>
            <span class="line_begin" >对他/她说:</span>
            <div width="100%" >
                <textarea type="text" class="form-control line_begin_1 " readonly="readonly" rows=6 placeholder="想对他说..." ><%=user_to.getTell_to()%></textarea>
            </div>

            <div class="line_begin_2">
                <%--<button type="button" width="50%" class="btn btn-primary">--%>
                    <%--编辑修改--%>
                <%--</button>--%>
                    <a href="<%=basePath%>pages/edit_user_to.jsp" class="weui_btn weui_btn_primary">编辑修改</a>
            </div>


        </div>



        <%--tab3,,我问你答--%>
        <div id="tab3">
            <div>
                <a href="<%=basePath%>pages/add_question.jsp" class="tab3_add_question weui_btn weui_btn_mini weui_btn_primary">添加题目</a>
            </div>
            <br/>
            <c:forEach items="${questionlist}" var="question" varStatus="vs">
                    <div class="tab3_question_title">
                        <header>${vs.index+1}. ${question.title}</header>
                    </div>
                    <div class="tab3_question_answer">
                        <img src="<%=basePath%>resources/img/smile.jpg" alt="图标" class="tab3_question_img" />
                        <span >${question.myanswer}</span>
                        <div class="tab3_question_reanswer">
                            <a href="<%=basePath%>editmyquestion?question_prim_id=${question.prim_id}"
                               class="weui_btn weui_btn_mini weui_btn_primary">重答</a>
                        </div>
                    </div>
             </c:forEach>
        </div>


        <%--自我推广按钮--%>
        <div class="line_begin_2">
            <%--<button type="button" width="50%" class="btn btn-primary">--%>
                <%--自我推广--%>
            <%--</button>--%>
             <a href="javascript:;" class="weui_btn weui_btn_disabled weui_btn_primary">自我推广</a>
        </div>



    </div>
</body>
<script type="text/javascript">
    $(function(){
        $(document.getElementById("tab2")).hide();
        $(document.getElementById("tab3")).hide();
    })
    function tab_change(which){
        var $tabs = $("li");
        var ul_button0 = $tabs[0];
        var $ul_button1 = $tabs[1];
        var $ul_button2 = $tabs[2];
        var $tab1 = $(document.getElementById("tab1"));
        var $tab2 = $(document.getElementById("tab2"));
        var $tab3 = $(document.getElementById("tab3"));
       // var $tab3 = $(document.getElementById("tab3"));
        switch(which){
            case 0:
//                ul_button0.addClass("active");
//                $ul_button1.removeClass("active");
//                $ul_button2.removeClass("active");
                    $tab1.show();
                    $tab2.hide();
                    $tab3.hide();
                break;
            case 1:
//                $ul_button1.addClass("active");
//                $ul_button0.removeClass("active");
//                $ul_button2.removeClass("active");
                    $tab2.show();
                    $tab1.hide();
                    $tab3.hide();
                break;
            case 2:
//                $ul_button2.addClass("active");
//                $ul_button0.removeClass("active");
//                $ul_button1.removeClass("active");

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
