<%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-3-29
  Time: 下午10:29
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>jQuery点击小图显示大图代码 - 源码之家</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta content="telephone=no" name="format-detection">

    <style type="text/css">
        body{font-family:"HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, sans-serif;background:#f4f4f4;padding:0;margin:0;}
        h1{font-size:31px;line-height:32px;font-weight:normal;margin-bottom:25px;}
        a,a:hover{border:none;text-decoration:none;}
        img,a img{border:none;}
        pre{overflow-x:scroll;background:#ffffff;border:1px solid #cecece;padding:10px;}
        .clear{clear:both;}
        .zoomed > .container{-webkit-filter:blur(3px);filter:blur(3px);}
        .container{width:860px;margin:20px auto;display: none}
        .gallery{list-style-type:none;float:left;}
        .gallery li{float:left;margin:10px; width:80px; height:80px;}
        .gallery li:nth-child(2n){padding-right:0;}
        .gallery li a,.gallery li img{float:left;}
    </style>
    <!--图片弹出层样式 必要样式-->
    <link rel="stylesheet"  href="<%=basePath%>resources/css/zoom.css" media="all" />
</head>
<body>
<div class="container">
    <ul class="gallery">
        <li><a href="<%=basePath%>resources/img/b1.jpg"><img src="<%=basePath%>resources/img/1.jpg" /></a></li>
        <li><a href="<%=basePath%>resources/img/b2.jpg"><img src="<%=basePath%>resources/img/2.jpg" /></a></li>
        <li><a href="<%=basePath%>resources/img/b3.jpg"><img src="<%=basePath%>resources/img/3.jpg" /></a></li>
        <li><a href="<%=basePath%>resources/img/b4.jpg"><img src="<%=basePath%>resources/img/4.jpg" /></a></li>
        <li><a href="<%=basePath%>resources/img/b5.jpg"><img src="<%=basePath%>resources/img/5.jpg" /></a></li>
    </ul>
    <div class="clear"></div>
</div>
<script src="<%=basePath%>resources/js/jquery.min.js"></script>
<script src="<%=basePath%>resources/js/zoom.min.js"></script>

<script type="text/javascript" >
    $(function () {
        $("img").trigger("click");
    })

</script>

</body>
</html>
