<%@ page import="com.xd.entity.User" %><%--
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
            User user = (User)session.getAttribute("user");


        %>

    <form action="<%=basePath%>adduser" method="post">
    <div class="weui_cells weui_cells_form">
        <div class="weui_cell weui_cell_select">

            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="sex">
                    <option value="0">选择性别</option>
                    <option selected="" value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">生日</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="date" name="birth" value=<%=user.getBirth()%> />
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">身高</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="number" name="height" placeholder=<%=user.getHeight()%> value=<%=user.getHeight()%> />
            </div>
        </div>

        <div class="weui_cell weui_cell_select">

            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="education">
                    <option value="选择学历">选择学历</option>
                    <option value="小学">小学</option>
                    <option value="初中">初中</option>
                    <option value="高中">高中</option>
                    <option value="中专">中专</option>
                    <option value="大专">大专</option>
                    <option selected="" value="本科">本科</option>
                    <option value="研究生">研究生</option>
                </select>
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">职业</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text" name = "career" placeholder=<%=user.getCareer()%> value=<%=user.getCareer()%> />
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">收入</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="number" name="income" placeholder=<%=user.getIncome()%> value=<%=user.getIncome()%> />
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">居住地</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text" name="address" placeholder=<%=user.getAddress()%> value=<%=user.getAddress()%> />
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">籍贯</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text" name="locate" placeholder=<%=user.getLocate()%> value=<%=user.getLocate()%> />
            </div>
        </div>

        <%--<%@ include file="address.jsp" %>--%>



    </div>
    <div class="weui_cells_title">想对他/她说</div>

    <div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <textarea class="weui_textarea" name="dubai" placeholder="请输入想说的话..." rows="3"><%=user.getDubai()%></textarea>
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
