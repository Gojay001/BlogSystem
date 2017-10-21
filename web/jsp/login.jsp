<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
    //解决路径问题
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>系统登录</title>
    <link rel="stylesheet" href="css/login.css" type="text/css" media="all" />
    <script type="text/javascript">
        function login(form) {
            if (form.username.value == "") {
                alert("用户名不能为空！");
                return false;
            }
            if (form.password.value == "") {
                alert("密码不能为空！");
                return false;
            }
        }
    </script>
</head>
<body>
    <form action="Login" method="post" onsubmit="return login(this);">
    <input type="hidden" name="action" value="login">
        <h2>欢 迎 登 录</h2>
        <input type="text" name="username" placeholder="用户名" />
        <input type="password" name="password" placeholder="用户密码" />
        <button type="reset">重&nbsp&nbsp&nbsp&nbsp&nbsp置</button>
        <button type="submit">登&nbsp&nbsp&nbsp&nbsp&nbsp录</button>
    </form>

</body>
</html>
