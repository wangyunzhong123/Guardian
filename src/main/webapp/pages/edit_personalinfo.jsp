<%--
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


</head>
<body>

    <div class="weui_cells weui_cells_form">
        <div class="weui_cell weui_cell_select">

            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="select1">
                    <option selected="" value="0">选择性别</option>
                    <option value="1">微信号</option>
                    <option value="2">QQ号</option>
                    <option value="3">Email</option>
                </select>
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">出生日期</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="date" value="">
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">身高</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="number" placeholder="输入身高cm">
            </div>
        </div>

        <div class="weui_cell weui_cell_select">

            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="select1">
                    <option selected="" value="0">选择学历</option>
                    <option value="1">小学</option>
                    <option value="2">初中</option>
                    <option value="3">高中</option>
                    <option value="4">中专</option>
                    <option value="5">大专</option>
                    <option value="6">本科</option>
                    <option value="7">研究生</option>
                </select>
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">职业</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text" placeholder="请输入职业">
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">收入</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="number" placeholder="请输入收入">
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">时间</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="datetime-local" value="" placeholder="">
            </div>
        </div>

    </div>
    <div class="weui_cells_title">文本域</div>
    <div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <textarea class="weui_textarea" placeholder="请输入评论" rows="3"></textarea>
                <div class="weui_textarea_counter"><span>0</span>/200</div>
            </div>
        </div>
    </div>


</body>
</html>
