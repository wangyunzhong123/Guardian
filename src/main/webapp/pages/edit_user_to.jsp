<%@ page import="com.xd.entity.User_to" %><%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-3-15
  Time: 下午11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
    Cookie cookie = new Cookie("username","");
    response.addCookie(cookie);
%>
<html>
<head>
    <title>Title</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <%--, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui--%>
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta content="telephone=no" name="format-detection">

    <link href="<%=basePath%>resources/css/weui.min.css" rel="stylesheet">

    <%--<script src="<%=basePath%>resources/js/app.49fa7b3d52e9c971c34b.js"></script>--%>

    <%--<link href="https://o3e85j0cv.qnssl.com/static/app.263d29833b28f0c04aa3c0ab9255c317.css" rel="stylesheet">--%>
    <%--<script type="text/javascript" src="<%=basePath%>resources/js/zepto.1.1.4.min.js"></script>--%>
    <%--<script type="text/javascript" src="<%=basePath%>resources/js/fastclick.1.0.6.min.js"></script>--%>


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


</head>
<body>

<%
    User_to user_to = (User_to) session.getAttribute("user_to");


%>

<form action="/adduser_to" method="post">
    <div class="weui_cells weui_cells_form">

        <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">年龄</label></div>
            <%--<div class="weui_cell_bd weui_cell_primary">--%>
                <%--<input class="weui_input" type="date" name="birth" value=<%=user.getBirth()%>>--%>
            <%--</div>--%>
            <input type="number" name="age_start" min="18" max="120" step="1" value=<%=user_to.getAge_start()%>/>---
            <input type="number" name="age_end" min="18" max="120" step="1" value=<%=user_to.getAge_end()%>/>

        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">身高</label></div>
            <%--<div class="weui_cell_bd weui_cell_primary">--%>
                <%--<input class="weui_input" type="number" name="height" placeholder=<%=user.getHeight()%>>--%>
            <%--</div>--%>
            <input type="number" name="height_start" min="160" max="220" step="2" value=<%=user_to.getHeight_start()%>/>---
            <input type="number" name="height_end" min="160" max="220" step="2" value=<%=user_to.getHeight_end()%>/>
        </div>

        <div class="weui_cell weui_cell_select">

            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="education">
                    <option value="0">选择学历</option>
                    <option value="1">小学</option>
                    <option value="2">初中</option>
                    <option value="3">高中</option>
                    <option value="4">中专</option>
                    <option value="5">大专</option>
                    <option selected="" value="6">本科</option>
                    <option value="7">研究生</option>
                </select>
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">收入</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input type="number" name="income_start" min="1000" max="100000" step="1000" value=<%=user_to.getIncome_start()%>/>---
                <input type="number" name="income_end" min="1000" max="100000" step="1000" value=<%=user_to.getIncome_end()%>/>
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">居住地</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text" name="address" placeholder=<%=user_to.getAddress()%>>
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">籍贯</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text" name="locate" placeholder=<%=user_to.getLocate()%>>
            </div>
        </div>

        <%--<%@ include file="address.jsp" %>--%>



    </div>
    <div class="weui_cells_title">想对他/她说</div>

    <div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <textarea class="weui_textarea" name="tell_to" placeholder="请输入想说的话..." rows="3"><%=user_to.getTell_to()%></textarea>
                <div class="weui_textarea_counter"><span>0</span>/200</div>
            </div>
        </div>
    </div>

    <div class="line_begin_2">
        <%--<button type="button" width="50%" class="btn btn-primary">--%>
        <%--编辑修改--%>
        <%--</button>--%>
        <input type="submit" class="weui_btn weui_btn_primary" />

    </div>

</form>


</body>
</html>
