<%--
  Created by IntelliJ IDEA.
  User: tianxi
  Date: 16-3-14
  Time: 下午5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>丘比特天天在线</title>

    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <%--, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui--%>
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta content="telephone=no" name="format-detection">

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">

    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

    <style type="text/css">
        .cate_tab{-moz-box-pack: justify;-webkit-box-pack: justify;-webkit-box-lines: single|multiple}
        .cate_tab li{width: 33% ;text-align:center}
        .cate_tab li a{}

        .tab1{font-size: large; }
        .tab1_edit{float: right;padding-right: 10%}
        .line_begin{padding: 0px 15px 15px 15px;}

        .tab1_ul li{list-style-type:none;height:40px;width: 100%;line-height: 40px;font-size: 16px;font-family: Arial,"微软雅黑",Helvetica,sans-serif}

        .line_begin_1{margin-left: 15px;margin-right: 15px;}
        .line_begin_2{width: 100%;-moz-box-pack: center;-webkit-box-pack: center;margin-top: 20px;margin-bottom: 20px;margin-left: auto;margin-right: auto}
    </style>

</head>
<body>
    <div>
        <%--上部分个人图片--%>
        <div>
            <img src="..//resources/img/person.jpg" alt="个人图片" class="img-rounded" width="100%" height="180px" >
        </div>
            <%--导航栏--%>
        <div width="100%" >
            <ul class="nav nav-pills cate_tab" width="100%">
                <li  id="li_0"><a href="#" onclick="tab_change(0)">基本信息</a></li>
                <li class="active" id="li_1"><a href="#"  onclick="tab_change(1)">择偶条件</a></li>
                <li id="li_2" ><a href="#"  onclick="tab_change(2)">我问你答</a></li>
            </ul>
        </div>
        <%--下面内容区--%>
        <div id=tab1 width="100%" class="tab1">
            <%--姓名--%>
            <div width="100%" height="20px">

            </div>
            <ul class="tab1_ul">
                <li  >
                    <span class="line_begin">姓名:</span>
                    <span id="name" >王二小</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">出生日期:</span>
                    <span id="birth" >1989-12-12</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">身高:</span>
                    <span id="height" >186cm</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">学历:</span>
                    <span id="level" >硕士</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">职业:</span>
                    <span id="career" >工程师</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">收入:</span>
                    <span id="income" >10000~15000</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">居住地:</span>
                    <span id="address" >北京</span>
                    <%--<img src="">--%>
                    <span class="line_begin">籍贯:</span>
                    <span id="locate" >山东</span>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin" >内心独白:</span>
                </li>
            </ul>

            <div width="100%" >
                <textarea type="text" class="form-control line_begin_1 " rows=6 placeholder="填写内心独白" ></textarea>
            </div>
        </div>



        <%--tab2--%>
        <div id=tab2 width="100%" class="tab1">
            <%--姓名--%>
            <div width="100%" height="20px">

            </div>
            <ul class="tab1_ul">
                <li  >
                    <span class="line_begin">年龄:</span>
                    <span id="tab2_age" >1975~1988</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">身高:</span>
                    <span id="tab2_height" >170cm~185cm</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">学历:</span>
                    <span id="tab2_level" >硕士</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">收入:</span>
                    <span id="tab2_income" >10000~15000</span>
                    <%--<img src="">--%>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin">居住地:</span>
                    <span id="tab2_address" >北京</span>
                    <%--<img src="">--%>
                    <span class="line_begin">籍贯:</span>
                    <span id="tab2_locate" >山东</span>
                    <button type="button" class="btn btn-primary tab1_edit">
                        编辑
                    </button>
                </li>
                <li >
                    <span class="line_begin" >想对他/她说:</span>
                </li>
            </ul>
                <%--对她说--%>
            <div width="100%" >
                <textarea type="text" class="form-control line_begin_1 " rows=4 placeholder="想对她/他说..." ></textarea>
            </div>
        </div>

        <%--tab3--%>

        <%--自我推广按钮--%>
        <div class="line_begin_2">
            <button type="button" width="50%" class="btn btn-primary">
                自我推广
            </button>
        </div>


    </div>
</body>
<script type="text/javascript">
    function tab_change(which){
        var $tabs = $("li");
        var ul_button0 = $tabs[0];
        var $ul_button1 = $tabs[1];
        var $ul_button2 = $tabs[2];
        var $tab1 = $(document.getElementById("tab1"));
        var $tab2 = $(document.getElementById("tab2"));
       // var $tab3 = $(document.getElementById("tab3"));
        switch(which){
            case 0:
//                ul_button0.addClass("active");
//                $ul_button1.removeClass("active");
//                $ul_button2.removeClass("active");
                    $tab1.show();
                    $tab2.hide();
                break;
            case 1:
//                $ul_button1.addClass("active");
//                $ul_button0.removeClass("active");
//                $ul_button2.removeClass("active");
                    $tab1.hide();
                    $tab2.show();
                break;
            case 2:
                $ul_button2.addClass("active");
                $ul_button0.removeClass("active");
                $ul_button1.removeClass("active");
                break;
            default:
                break;

        }
    }
</script>
</html>
