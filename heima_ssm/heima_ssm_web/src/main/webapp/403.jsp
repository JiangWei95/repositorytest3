<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/9 0009
  Time: 上午 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>访问错误--页面跳转中...</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="refresh" content="10;URL=http://www.baidu.com/" />
    //参数说明这里是10秒；10是跳转间隔时间可以自己设定
    <meta content="MSHTML 6.00.2800.1106" name="GENERATOR" />

</head>
<body>
<div id="maincolumn">
    <div> <jsp:forward page="/pages/main.jsp">首页</jsp:forward> -<jsp:forward page="/pages/main.jsp">即将跳转首页</jsp:forward> </div>
</div>
<div> Copyright &copy; 2018-2019 <jsp:forward page="/pages/main.jsp">首页</jsp:forward> </div>
</body>
</html>
