<%@ page import="com.xd.entity.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xd.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-3-21
  Time: 下午5:40
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
    <title>Title</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <%--, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui--%>
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta content="telephone=no" name="format-detection">
    <script src="<%=basePath%>resources/js/jquery-1.8.2.js"> </script>

    <style type="text/css">
        .to_all{margin: 6%;}
        .id_none{display: none;}
        .input_margin_left{margin-left: 10%;}
        .line_begin_2{width: 180px;-moz-box-pack: center;-webkit-box-pack: center;margin-left: auto;margin-right: auto;margin-top: 15px;}
    </style>

</head>
<body>

<%
    List<Question> questionlist = (List<Question>)request.getAttribute("questionlist");
%>

<div class="to_all">
        <%--<input name="id" value="<%=question.getId()%>" class="id_none"/>--%>
        <%--<h1 name="title"><%=question.getTitle()%></h1>--%>
        <br/>
        <%!
            int i=0;
        %>
        <c:forEach items="<%=questionlist%>" var="question" varStatus="vs" >

            <%--<c:set var="flag" value="${result1[vs]}" />--%>
            <h3>${vs.index+1}. ${question.title}></h3>
            <c:forEach items="${question.items}" var="item" varStatus="v">
                <label><input class="input_margin_left" name="Fruit" type="radio" value=${item.context} />${item.context} </label>
                <br/>
            </c:forEach>
            <div class="line_begin_2">
                <%--本来是想用user判断当前题目是否已经在用户的mmyquestion里,但还未能实现--%>
                <input type="button" onclick="add_myquestion(${question.id})" class="weui_btn weui_btn_primary" value="确认添加" />
                <%--<c:if test="${flag == 1}">--%>
                    <%--<input type="button" class="weui_btn weui_btn_primary" value="已存在" />--%>
                 <%--</c:if>--%>
                <%--<c:if test="${flag == 0}">--%>
                    <%--<input type="button" onclick="add_myquestion(${question.id})" class="weui_btn weui_btn_primary" value="确认添加" />--%>
                <%--</c:if>--%>
            </div>
            <br/><br/>


        </c:forEach>

        <div class="line_begin_2">
            <%--本来是想用user判断当前题目是否已经在用户的mmyquestion里,但还未能实现--%>
                <a href="<%=basePath%>getuser" class="weui_btn weui_btn_primary">完成</a>
        </div>
        <%--<h3>我的答案是:</h3>--%>
        <%--<c:forEach items="<%=question.getItems()%>" var="item" varStatus="vs">--%>
            <%--<label><input class="input_margin_left" name="Fruit" type="radio" value=${item.context} />${item.context} </label>--%>
            <%--<br/>--%>
        <%--</c:forEach>--%>

        <%--<br/>--%>
        <%--<h3>我接受的答案是:</h3>--%>
        <%--<c:forEach items="<%=question.getItems()%>" var="item" varStatus="vs">--%>
            <%--<label><input class="input_margin_left" name="Fruit" type="radio" value=${item.context} />${item.context} </label>--%>
            <%--<br/>--%>
        <%--</c:forEach>--%>

        <%--<br/>--%>
        <%--<h3>重要程度:</h3>--%>
        <%--<label><input class="input_margin_left" name="Fruit" type="radio" value="无关重要" />无关重要 </label><br/>--%>
        <%--<label><input class="input_margin_left" name="Fruit" type="radio" value="稍微重要一点" />稍微重要一点 </label><br/>--%>
        <%--<label><input class="input_margin_left" name="Fruit" type="radio" value="重要" />重要 </label><br/>--%>
        <%--<label><input class="input_margin_left" name="Fruit" type="radio" value="非常重要" />非常重要 </label><br/>--%>
        <%--<br/>--%>

        <%--<div class="line_begin_2">--%>
            <%--<input type="submit" class="weui_btn weui_btn_primary" />--%>
        <%--</div>--%>
        <%--<a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_default">跳过</a>--%>

</div>
</body>
    <script type="text/javascript">
        function add_myquestion(questionid){
            $.ajax({
                url:'addmyquestion',
                type:'post', //数据发送方式
//                dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
                data:"questionid="+questionid, //要传递的数据
                error: function(){ //失败
//                    alert('失败');
                },
                success: function(msg){ //成功
                    alert(msg);
                }
            });
        }
        </script>
</html>
