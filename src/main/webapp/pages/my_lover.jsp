<%@ page import="com.xd.entity.MyLover" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-3-18
  Time: 下午5:59
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
    <title>心仪对象</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta content="telephone=no" name="format-detection">

    <style type="text/css">
        * { margin:0;padding:0;}
        h3 {font-size:20px;line-height:1.5em;display: inline}
        span{font-size: 18px;margin-left: 15px;}
        p {font-size:12px;text-indent:2em;line-height:1.5em; }
        .container { width:100%; margin:0 auto; margin-top:10px; padding-right:3px;border:1px dotted #666; position:relative; }
        .leftContent { position:absolute; top:2px; left:4px; }
        .leftContent img{ height:80px; width:100px; padding:2px; margin-top: 15px;}
        .rightContent { margin-left:120px;  margin-top: 10px;}
        h2{
            text-align: center;
            align-content: center;
            margin: 20% auto;
        }
    </style>

</head>
<body>
    <c:forEach items="${myloverlist}" var="lover" varStatus="vs">
        <div class="container">
            <div class="leftContent"><img src="<%=basePath%>resources/img/smile.jpg"></div>
            <div class="rightContent">
                <h3>昵称: </h3><span>${lover.name}</span><br/>
                <h3>居住地:</h3><span>${lover.address}</span><br/>
                <h3>学历:</h3><span>${lover.education}</span><br/>
                <h3>薪资水平:</h3><span>${lover.income}+</span><br/>
                <h3>${lover.beizhu}</h3><span></span><br/>
            </div>
        </div>
     </c:forEach>

    <%
        List<MyLover> myLoverList = (List<MyLover>)request.getAttribute("myloverlist");
        if(myLoverList ==null || myLoverList.size() == 0){
            out.print("<h2>你还没有添加过心仪对象,请从嘉宾推荐或者别人的分享添加关注!</h2>");
        }
    %>
</body>
</html>
