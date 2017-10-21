<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
    //解决路径问题
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>错误页面</title>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>
<body>
<!-- Header -->
<div id="header">
    <div class="shell">
        <!-- Logo + Top Nav -->
        <div id="top">
            <h1><a href="jsp/index.jsp">MagicWolf</a></h1>
            <div id="top-navigation">
                欢迎&nbsp;&nbsp;<a href="jsp/index.jsp">
                <strong>管理员</strong></a>
            </div>
        </div>
        <!-- End Logo + Top Nav -->

        <!-- Main Nav -->
        <div id="navigation">
            <ul>
                <li><a href="jsp/index.jsp" class="active"><span>首页</span></a></li>
                <li><a href="jsp/publish.jsp" ><span>发布文章</span></a></li>
            </ul>
        </div>
        <!-- End Main Nav -->
    </div>
</div>
<!-- End Header -->

<h1><strong>页面发生错误，请返回首页！</strong></h1>

<!-- Footer -->
<div id="footer">
    <div class="shell">
        <span class="left">&copy; 2010 - CompanyName</span>
        <span class="right">
			Design by <a href="http://chocotemplates.com" target="_blank" title="The Sweetest CSS Templates WorldWide">Chocotemplates.com</a>
		</span>
    </div>
</div>
<!-- End Footer -->
</body>
</html>
