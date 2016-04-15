<%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-4-15
  Time: 下午5:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>关注微信公众账号</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta content="telephone=no" name="format-detection">

    <style type="text/css">
        .img{
            text-align: center;
            margin: 8% auto;
        }
        p{
            text-align: center;
            margin: 2% 10%;
        }
        h2{
            text-align: center;
            margin: 5% auto;
        }
    </style>
</head>
<body>

    <div class ="img"><img src="<%=basePath%>resources/img/cupid.jpg" width="100px" height="100px"></div>
    <p>关注公众好,菜单栏点击"个人中心",确定授权,自动注册.开启你的爱情之旅吧!</p>
    <div class ="img"><img src="<%=basePath%>resources/img/code.jpg"></div>
    <h2>长按扫码关注</h2>
    <h2>开启爱情之旅</h2>
</body>
</html>
