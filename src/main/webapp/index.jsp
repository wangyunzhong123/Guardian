<%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-5-24
  Time: 上午9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" autoFlush="false" buffer="512kb"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String path=request.getContextPath();
  String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>数据管理</title>
  <link href="<%=basePath%>resources/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<%=basePath%>resources/css/dashboard.css" rel="stylesheet">
  <link href="<%=basePath%>resources/css/datamanagecss.css" rel="stylesheet">

  <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
  <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
  <script src="<%=basePath%>resources/js/ie-emulation-modes-warning.js"></script>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">The Guardian泳池安全管理平台</a>
    </div>
  </div>
</nav>

<div class="row">
  <div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar nav_my_ul">
      <li class="active"><a href="#">相关操作 <span class="sr-only">(current)</span></a></li>
      <li><a href="database_design.html" target="_blank">数据标准规范</a></li>
      <%--<li><a href="#" data-toggle="modal" data-target="#toGetData">数据抓取平台</a></li>--%>
      <li><a href="javascript:writeExcel();" target="_blank">导出在线用户</a></li>
      <li><a href="javascript:alert('请使用相应的数据库管理软件操作！')">数据结构扩展</a></li>
      <li><a href="#">多元数据采集</a></li>
      <li><a href="#">数据资源管理</a></li>
      <li class="active_1"><a href="#" data-target="#addApis">标准化用户管理</a></li>
    </ul>

  </div>
  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <h2 class="sub-header">在线用户-共${userNumber}</h2>
    <button onclick="javascript:getWebsocketConn()" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addApis" >连接WebSocket</button>
    <button onclick="javascript:addApi()" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addApis" >增加接口信息</button>
    <div class="table-responsive table2excel">
      <table class="table table-striped">
        <thead id="thead">
        <tr>
          <th>编号</th>
          <th>id</th>
          <th>姓名</th>
          <th>手环id</th>
          <th>性别</th>
          <th>电话</th>
          <th>生日</th>
          <th>备注</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
          <c:forEach items="${userList}" var="userRow" varStatus="vs">
            <tr>
              <td>${vs.index+1}</td>
              <td>${userRow.id}</td>
              <td>${userRow.name}</td>
              <td name="${userRow.ring_id}">${userRow.ring_id}</td>
              <td>${userRow.sex}</td>
              <td>${userRow.phone}</td>
              <td>${userRow.birth}</td>
              <td>${userRow.remark}</td>
              <td id="${userRow.ring_id}">正常</td>
              <td><a href="javascript:void(0);"><span onclick="javascript:deleteTr(this,${userRow.id});" class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                <a href="javascript:void(0);" ><span id="edit" onclick="javascript:editTr(this,${vs.index});" class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <button onclick="javascript:writeExcel()" type="button" class="btn btn-primary">确定修改</button>
  </div>

  <%--在线救生员--%>
  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <h2 class="sub-header">在线救生员-共 1 位</h2>
    <button onclick="javascript:addApi()" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addGuardianer" >增加接口信息</button>
    <div class="table-responsive table3excel">
      <table class="table table-striped">
        <thead id="">
        <tr>
          <th>编号</th>
          <th>id</th>
          <th>姓名</th>
          <th>性别</th>
          <th>电话</th>
          <th>备注</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody id="">
        <c:forEach items="${guardianerList}" var="userRow" varStatus="vs">
          <tr>
            <td>${vs.index}</td>
            <td>${userRow.id}</td>
            <td>${userRow.name}</td>
            <td>${userRow.ring_id}</td>
            <td>${userRow.sex}</td>
            <td>${userRow.phone}</td>
            <td>${userRow.birth}</td>
            <td>${userRow.remark}</td>
            <td>在线</td>
            <td><a href="javascript:void(0);"><span onclick="javascript:deleteTr(this,${userRow.id});" class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
              <a href="javascript:void(0);" ><span id="edit1" onclick="javascript:editTr(this,${vs.index});" class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <button onclick="javascript:writeExcel()" type="button" class="btn btn-primary">确定修改</button>
  </div>

</div>


<%--<div>--%>
  <%--<button class="my_button btn btn-primary btn-lg" data-toggle="modal" data-target="#toGetData">--%>
    <%--抓取平台数据--%>
  <%--</button>--%>
<%--</div>--%>
<%--<div>--%>
  <%--<a href="/resources/apis.xml" target="_blank"><button class="my_button btn btn-primary btn-lg" data-toggle="modal" data-target="#viewApis">--%>
    <%--查看数据服务接口--%>
  <%--</button></a>--%>
<%--</div>--%>
<%--<div>--%>
  <%--<button class="my_button btn btn-primary btn-lg" data-toggle="modal" data-target="#addApis">--%>
    <%--管理数据服务接口--%>
  <%--</button>--%>
<%--</div>--%>
<%--<div>--%>
  <%--<a href="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()%>+'/wb' " target="_blankleigang--%>

  <%--"><button class="my_button btn btn-primary btn-lg" data-toggle="modal" data-target="#">--%>
    <%--数据资源管理--%>
  <%--</button></a>--%>
<%--</div>--%>

<!-- 模态框（Modal） -->
<div class="modal fade" id="toGetData" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close"
                data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" id="myModalLabel">
          输入要连接的数据库信息
        </h4>
      </div>
      <form action="getdata" method="get">
        <div class="modal-body">
          <div class="modal-content_my">选择数据库类型:
            <select name="dbtype" class="form-control">
              <option value="sql_server">sql_server</option>
              <option value="oracle">oracle</option>
              <option value="mysql">mysql</option>
            </select>
          </div>
          <div class="input-group">
            <span class="input-group-addon">完整url:</span>
            <input type="text" name="url" class="form-control" placeholder="填写数据库连接url">
          </div>
          <h4>示例</h4>
          Sql_Server >>> localhost:1433;DatabaseName=*** <br>
          oracle >>> localhost:1521/*** <br>
          mysql >>> localhost:3306/*** <br>
          <div class="input-group">
            <span class="input-group-addon">用户名:</span>
            <input type="text" name="name" class="form-control" placeholder="输入用户名">
          </div><br>
          <div class="input-group">
            <span class="input-group-addon">密码:</span>
            <input type="password" name="key" class="form-control" placeholder="输入密码">
          </div><br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"
                  data-dismiss="modal">关闭
          </button>
          <button type="submit" class="btn btn-primary" onclick="openUrl();">
            连接
          </button>
        </div>
      </form>
    </div><!-- /.modal-content -->
  </div>
</div>
<!-- 模态框（Modal）管理数据接口 -->
<div class="modal fade" id="addApis" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close"
                data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" >
          添加在线用户
        </h4>
      </div>
      <form action="addUser" method="post" accept-charset="utf-8">
        <div class="modal-body">
          <%--<div class="modal-content_my">该接口所属类:--%>
            <%--<select name="cata" class="form-control">--%>
              <%--<option value="人">人</option>--%>
              <%--<option value="事">事</option>--%>
              <%--<option value="物">物</option>--%>
              <%--<option value="地">地</option>--%>
            <%--</select>--%>
          <%--</div>--%>
          <div class="input-group">
            <span class="input-group-addon">姓名:</span>
            <input type="text" id="id1" name="name" class="form-control" placeholder="填写姓名">
          </div><br>

          <div class="input-group">
            <span class="input-group-addon">手机:</span>
            <input type="text" id="id2" name="phone" class="form-control" placeholder="填写手机">
          </div><br>
          <div class="modal-content_my">性别:
            <select id="id3" name="sex" class="form-control">
              <option value="男">男</option>
              <option value="女">女</option>
            </select>
          </div>
          <div class="input-group">
            <span class="input-group-addon">手环id:</span>
            <input type="text" id="id4" name="ring_id" class="form-control" placeholder="填写手环id">
          </div><br>
          <div class="input-group">
            <span class="input-group-addon">出生年月:</span>
            <input type="text" id="id5" name="birth" class="form-control" placeholder="填写出生年月">
          </div><br>
          <div class="input-group">
            <span class="input-group-addon">备注:</span>
            <input type="text" id="id6" name="remark" class="form-control" placeholder="填写备注">
          </div><br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"
                  data-dismiss="modal">关闭
          </button>
          <button type="submit" class="btn btn-primary" onclick=";">
            添加
          </button>
        </div>
      </form>
    </div><!-- /.modal-content -->
  </div>
</div>
<!-- 添加救生员 -->
<div class="modal fade" id="addGuardianer" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close"
                data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" >
          添加管理员
        </h4>
      </div>
      <form action="addUser" method="post" accept-charset="utf-8">
        <div class="modal-body">
          <div class="input-group">
            <span class="input-group-addon">姓名:</span>
            <input type="text" id="id11" name="name" class="form-control" placeholder="填写姓名">
          </div><br>

          <div class="input-group">
            <span class="input-group-addon">手机:</span>
            <input type="text" id="id12" name="phone" class="form-control" placeholder="填写手机">
          </div><br>
          <div class="input-group">
            <span class="input-group-addon">密码:</span>
            <input type="text" id="id13" name="phone" class="form-control" placeholder="填写密码">
          </div><br>
          <div class="modal-content_my">性别:
            <select id="id14" name="sex" class="form-control">
              <option value="男">男</option>
              <option value="女">女</option>
            </select>
          </div><br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"
                  data-dismiss="modal">关闭
          </button>
          <button type="button" class="btn btn-primary" onclick="addGuardianer();">
            添加
          </button>
        </div>
      </form>
    </div><!-- /.modal-content -->
  </div>
</div>

</body>
<script src="<%=basePath%>resources/js/jquery.min.js"></script>
<script src="<%=basePath%>resources/js/bootstrap.min.js"></script>
<script src="<%=basePath%>resources/js/jquery.table2excel.js"></script>
<script language="javascript">
  function getData()
  {
    var type=prompt("请输入您的名字","");//将输入的内容赋给变量 name ，
    //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值
    if(name)//如果返回的有内容
    {
      alert("欢迎您："+ name)
    }

  }
  //写excel文件
  function writeExcel() {
    $(".table2excel").table2excel({
      exclude: ".noExl",
      name: "导出表",
      filename: "在线用户",
      exclude_img: true,
      exclude_links: true,
      exclude_inputs: true
    });
  }
  function openUrl(){
    var dbtype = $("select[name='dbtype']").val();
    var url = $("input[name='url']").val();
    var name = $("input[name='name']").val();
    var key = $("input[name='key']").val();
    //判断都不为空

    $.ajax({
      type: "GET",
      url: point_url,
      data: {
        streetcode: SCode,
        subClass: SClass,
        rt_long: rt_long,
        rt_lat: rt_lat,
      },
      success: function(data){
        if(data == null) return;
        var detail = data['detail'];
        var icon = basePath + "pnh/999.png";
        EQ.Map.createMark(detail, icon, SClass);
      },
      error: function(message){
        console.log(message);
      }
    });
  }
  function editSubmit(){

  }

  //删除一行数据
  function deleteTr(it,index){
    if(confirm("你确认用户下线吗？")){
      $.ajax({
        type: "GET",
        url: "deleteUser",
        data: {
          userid: index,
        },
        success: function(data){
          $(it).parent().parent().parent().remove();
        },
        error: function(message){
          console.log(message);
          alert("操作失败，请重试～！");
        }
      });
    }
  }
  //编辑这一行数据
  function editTr(it,index){
    var id = $(it).attr("id");
//    $(it).attr("id",id=="edit"?"confirm":"edit");
    if($(it).attr("id") == "edit"){
      $(it).removeClass("glyphicon glyphicon-pencil");
      $(it).addClass("glyphicon glyphicon-ok");
      $(it).attr("id","confirm");
    }else if($(it).attr("id") == "confirm"){
      //提交给后台
      $.ajax({
        type: "POST",
        url: "editApi",
        async:false,
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        data: {
          index: index,
          cata:$(it).parent().parent().siblings().find("input").eq(1).val(),
          name:$(it).parent().parent().siblings().find("input").eq(2).val(),
          todo:$(it).parent().parent().siblings().find("input").eq(3).val(),
          method:$(it).parent().parent().siblings().find("input").eq(4).val(),
          para:$(it).parent().parent().siblings().find("input").eq(5).val(),
          url:$(it).parent().parent().siblings().find("input").eq(6).val(),
          demo:$(it).parent().parent().siblings().find("input").eq(7).val(),
          reback:$(it).parent().parent().siblings().find("input").eq(8).val(),
        },
        success: function(data){
          console.log(data);
          $(it).attr("id","edit");
        },
        error: function(message){
          console.log(message);
          return ;
        }
      });
      $(it).removeClass("glyphicon glyphicon-ok");
      $(it).addClass("glyphicon glyphicon-pencil");
    }

    $(it).parent().parent().siblings("td").each(function() {  // 获取当前行的其他单元格
      obj_text = $(this).find("input:text");    // 判断单元格下是否有文本框
      if(!obj_text.length)   // 如果没有文本框，则添加文本框使之可以编辑
        $(this).html("<input type='text' value='"+$(this).text()+"'>");
      else   // 如果已经存在文本框，则将其显示为文本框修改的值
        $(this).html(obj_text.val());
    });

  }
  function addApi(){

  }

  //连接websocket
  function getWebsocketConn(){

  }

  var webSocket = null;
  var tryTime = 0;
  $(function () {
    initSocket();

    window.onbeforeunload = function () {
      //离开页面时的其他操作
    };
  });

  function strToJson(str){
    var json = (new Function("return " + str))();
    return json;
  }

  /**
   * 初始化websocket，建立连接
   */
  function initSocket() {
    if (!window.WebSocket) {
      alert("您的浏览器不支持websocket！");
      return false;
    }

//    webSocket = new WebSocket("ws://localhost:8081/websocket.ws/"+ relationId +"/"+ userCode);
    webSocket = new WebSocket("ws://localhost:8081/guardian/websocket.ws/"+ 111 +"/"+ 222);

    // 收到服务端消息
    webSocket.onmessage = function (msg) {
      console.log(msg);
      var arr =new Array();
      arr = msg.data.split('&');
      $("#"+arr[0]).html("求救中！！");
      $("#"+arr[0]).css("color","red");

      $("#"+arr[0]).click(function(){
        $("#"+arr[0]).html("正常");
        $("#"+arr[0]).css("color","");
      });
    }

    // 异常
    webSocket.onerror = function (event) {
      console.log(event);
    };

    // 建立连接
    webSocket.onopen = function (event) {
      console.log(event);
    };

    // 断线重连
    webSocket.onclose = function () {
      // 重试10次，每次之间间隔10秒
      if (tryTime < 10) {
        setTimeout(function () {
          webSocket = null;
          tryTime++;
          initSocket();
        }, 500);
      } else {
        tryTime = 0;
      }
    };

  }

  //添加用户和手环对应关系
  function addUser(){
    $.ajax({
      type: "POST",
      url: "addUser",
      async:false,
      contentType:"application/x-www-form-urlencoded;charset=utf-8",
      data: {
        name:$("input[id='id1']").val(),
        phone:$("input[id='id2']").val(),
        sex:$("select[id='id3'] option:selected").text(),
        ring_id:$("input[id='id4']").val(),
        birth:$("input[id='id5']").val(),
        remark:$("input[id='id6']").val(),
      },
      success: function(data){
        console.log(data);
      },
      error: function(message){
        console.log(message);
      }
    });
  }

    //添加救生员
  function addGuardianer(){
    $.ajax({
      type: "POST",
      url: "addGuardianerPad",
      async:false,
      contentType:"application/x-www-form-urlencoded;charset=utf-8",
      data: {
        name:$("input[id='id11']").val(),
        phone:$("input[id='id12']").val(),
        password:$("input[id='id13']").val(),
        sex:$("select[id='id14'] option:selected").text(),
      },
      success: function(data){
        console.log(data);
        alert(data)
      },
      error: function(message){
        console.log(message);
      }
    });
  }

</script>
</html>
