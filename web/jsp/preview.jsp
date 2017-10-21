<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="cn.gojay.model.*" %>
<%@ page import="cn.gojay.DB.BlogDB" %>
<%@ page import="cn.gojay.encode.MyDate" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>查看博客</title>
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
                <strong>管理员<%
                    Admin adminBean = (Admin)request.getSession().getAttribute("adminBean");
                    out.print(adminBean.getName());
                %></strong><strong>
                </strong></a>
                <span>|</span>
                <a href="Exit">退出</a>
            </div>
        </div>
        <!-- End Logo + Top Nav -->

        <!-- Main Nav -->
        <div id="navigation">
            <ul>
                <li><a href="jsp/index.jsp" ><span>首页</span></a></li>
                <li><a href="jsp/publish.jsp" class="active"><span>发布文章</span></a></li>
            </ul>
        </div>
        <!-- End Main Nav -->
    </div>
</div>
<!-- End Header -->

<!-- Container -->
<div id="container">
    <div class="shell">

        <!-- Small Nav -->
        <div class="small-nav">
            <a href="jsp/index.jsp">首页</a>
            <span>&gt;</span>
            显示文章
        </div>
        <!-- End Small Nav -->

        <br />
        <!-- Main -->
        <div id="main">
            <div class="cl">&nbsp;</div>

            <!-- Content -->
            <div id="content">


                <!-- Box -->
                <div class="box">
                    <!-- Box Head -->
                    <div class="box-head">
                        <h2>显示文章</h2>
                    </div>
                    <!-- End Box Head -->

                        <!-- Form -->
                        <form action="Add" method="post">
                        <div class="form">
                            <!-- 查看博客内容 -->
                            <p>
                                <label>内容<span></span></label>
                            <pre>
                                <%
                                    Blog blogBean;
                                    if (request.getParameter("id") != null) {
                                        //从index.jsp查看博客文章内容
                                        blogBean = BlogDB.searchBlogById(Integer.parseInt(request.getParameter("id")));
                                    } else {
                                        //从PreviewServlet查看文章内容
                                        blogBean = (Blog)session.getAttribute("blogBean");
                                    }
                                    if (blogBean != null) {
                                        out.print("<br><strong>" + blogBean.getBlogTitle() + "</strong><br><br>");
                                        out.print(blogBean.getName() + "&nbsp&nbsp&nbsp");
                                        out.print(MyDate.showDateByLine(blogBean.getDate()) + "<br><br>");
                                        out.print("<h4>" + blogBean.getBlogText() + "</h4>");
                                    }
                                %>
                            </pre>
                            </p>
                        </div>

                    <!-- Form Buttons -->
                    <div class="buttons">
                        <input type="button" class="button" value="返回" onclick="javascript:history.back();" />
                        <!-- previewServlet转发时显示上传 -->
                        <c:if test="${requestScope.submit != null}">
                            <input type="submit" class="button" value="确认上传" />
                        </c:if>
                    </div>
                    <!-- End Form Buttons -->
                        </form>
                        <!-- End Form Buttons -->

                </div>
                <!-- End Box -->

            </div>
            <!-- End Content -->

            <div class="cl">&nbsp;</div>
        </div>
        <!-- Main -->
    </div>
</div>
<!-- End Container -->

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