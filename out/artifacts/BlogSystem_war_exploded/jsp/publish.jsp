<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="cn.gojay.model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>后台管理</title>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
    <script type="text/javascript">
        function publish(form) {
            if (form.title.value == "") {
                alert("博客标题不能为空！");
                return false;
            }
            if (form.blogText.value == "") {
                alert("博客内容不能为空！");
                return false;
            }
            if (form.year.value == "") {
                alert("时间不能为空！");
                return false;
            }
            if (form.month.value == "") {
                alert("时间不能为空！");
                return false;
            }
            if (form.day.value == "") {
                alert("时间不能为空！");
                return false;
            }
        }
    </script>
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
                %></strong></a>
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
            发布文章
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
                        <h2>发布文章</h2>
                    </div>
                    <!-- End Box Head -->

                    <form action="Add" method="post" onsubmit="return publish(this);">

                        <!-- Form -->
                        <div class="form">

                            <!-- 编辑时获取博客信息 -->
                            <p>
                                <span class="req">最多100字</span>
                                <label>标题<span>*</span></label>
                                <input type="text" class="field size1" name="title" maxlength="100" size="100"
                                        <% if (request.getAttribute("title") != null) { %>
                                       value="<%= request.getAttribute("title")%>"
                                        <% } %>/>
                            </p>
                            <p class="inline-field">
                                <label>Date</label>
                                <input type="text" class="field size2" name="year" maxlength="4" size="4"
                                       <% if (request.getAttribute("year") != null) { %>
                                       value="<%= request.getAttribute("year")%>"
                                       <% } %>/>
                                <input type="text" class="field size3" name="month" maxlength="2" size="2"
                                       <% if (request.getAttribute("month") != null) { %>
                                       value="<%= request.getAttribute("month")%>"
                                       <% } %>/>
                                <input type="text" class="field size3" name="day" maxlength="2" size="2"
                                       <% if (request.getAttribute("day") != null) { %>
                                       value="<%= request.getAttribute("day")%>"
                                       <% } %>/>
                            </p>

                            <p>
                                <label>内容<span>*</span></label>
                                <textarea class="field size1" rows="10" cols="30" name="blogText"><% if (request.getAttribute("blogText") != null) { %><%=request.getAttribute("blogText")%><% } %></textarea>
                            </p>

                        </div>
                        <!-- End Form -->

                        <!-- Form Buttons -->
                        <div class="buttons">
                            <input type="submit" class="button" value="preview" formaction="Preview" />
                            <input type="submit" class="button" value="提交" formaction="Add" />

                        </div>
                        <!-- End Form Buttons -->
                    </form>
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
