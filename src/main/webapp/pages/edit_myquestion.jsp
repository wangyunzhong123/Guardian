<%@ page import="com.xd.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-3-18
  Time: 下午4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <title>重答我的问题</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <%--, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui--%>
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta content="telephone=no" name="format-detection">

    <link href="<%=basePath%>resources/css/weui.min.css" rel="stylesheet">

    <style type="text/css">
        .to_all{margin: 6%;}
        .id_none{display: none;}
        .input_margin_left{margin-left: 10%;}
        .line_begin_2{width: 180px;-moz-box-pack: center;-webkit-box-pack: center;margin-left: auto;margin-right: auto;margin-top: 15px;}
    </style>

</head>
<body>

        <%
            Question question = (Question)request.getAttribute("question");
            int question_id = (Integer) request.getAttribute("question_id");
        %>

        <div class="to_all">
            <form action="<%=basePath%>editmyquestion_save?question_id=<%=question_id%>" method="post">
                <input name="id" value="<%=question_id%>" class="id_none"/>
                <h1><input name="title" value = "<%=question.getTitle()%>" readonly="readonly" /></h1>
                <br/>
                <h3>我的答案是:</h3>
                <c:forEach items="<%=question.getItems()%>" var="item" varStatus="vs">
                    <label><input class="input_margin_left" name="myanswer" type="radio" value=${item.context} />${item.context} </label>
                    <br/>
                </c:forEach>

                <br/>
                <h3>我接受的答案是:</h3>
                <c:forEach items="<%=question.getItems()%>" var="item" varStatus="vs">
                    <label><input class="input_margin_left" name="acceptanswer" type="radio" value=${item.context} />${item.context} </label>
                    <br/>
                </c:forEach>

                <br/>
                <h3>重要程度:</h3>
                <label><input class="input_margin_left" name="importance" type="radio" value="无关重要" />无关重要 </label><br/>
                <label><input class="input_margin_left" name="importance" type="radio" value="稍微重要一点" />稍微重要一点 </label><br/>
                <label><input class="input_margin_left" name="importance" type="radio" value="重要" />重要 </label><br/>
                <label><input class="input_margin_left" name="importance" type="radio" value="非常重要" />非常重要 </label><br/>
                <br/>

                <div class="line_begin_2">
                    <input type="submit" class="weui_btn weui_btn_primary" />
                </div>
                <a href="<%=basePath%>getuser" class="weui_btn weui_btn_mini weui_btn_default">跳过</a>

            </form>
        </div>



</body>
</html>
