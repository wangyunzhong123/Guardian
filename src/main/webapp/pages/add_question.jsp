<%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-3-20
  Time: 下午8:31
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
    <style type="text/css">
        body {width: 100%;align-content: center;}
        </style>

</head>
<body>

    <form action="<%=basePath%>addquestion" method="post">
        问题内容:<input type="text" name="title"><br/><br/>
        答案选项1:<input type="text" name="context"><br/>
        答案选项2:<input type="text" name="context"><br/>
        答案选项3:<input type="text" name="context"><br/>
        答案选项4:<input type="text" name="context"><br/>

        <input type="submit" >

    </form>
</body>
</html>
