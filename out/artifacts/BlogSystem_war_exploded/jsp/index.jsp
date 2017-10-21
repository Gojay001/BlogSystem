<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.gojay.model.*" %>
<%@ page import="cn.gojay.encode.MyDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    //解决路径问题
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>后台管理</title>
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
                %></strong></a>
                <span>|</span><a href="Exit">退出</a>
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

<!-- Container -->
<div id="container">
    <div class="shell">

        <!-- Small Nav -->
        <div class="small-nav">
            <a href="jsp/index.jsp">首页</a>
            <span>&gt;</span>
            文章列表
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
                        <h2 class="left">文章列表</h2>
                        <div class="right">
                            <form method="post" action="Search?curPage=1">
                            <label>搜索文章</label>
                                <input type="text" class="field small-field" name="title" value="测试" />
                                <input type="submit" class="button" value="搜索" />
                            </form>
                        </div>
                    </div>
                    <!-- End Box Head -->

                    <!-- Table -->
                    <div class="table">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th width="13"><input type="checkbox" class="checkbox" /></th>
                                <th>标题</th>
                                <th>日期</th>
                                <th>作者</th>
                                <th width="110" class="ac"></th>
                            </tr>

                            <!-- 显示博客属性 -->
                            <%
                                if (request.getAttribute("blogs") != null) {
                                    ArrayList<Blog> blogs = (ArrayList<Blog>)request.getAttribute("blogs");
                                    for (Blog blogBean : blogs) {
                                        int id = blogBean.getBlogId();
                            %>
                            <tr>
                                <td><input type="checkbox" class="checkbox" /></td>
                                <td>
                                    <h3><a href="jsp/preview.jsp?id=<%=id%>"><%=blogBean.getBlogTitle()%></a></h3>
                                </td>
                                <td><%=MyDate.showDateByPoint(blogBean.getDate())%></td>
                                <td><a href="jsp/index.jsp"><%=blogBean.getName()%></a></td>
                                <td><a href="Delete?id=<%=id%>" class="ico del" >删除</a>
                                    <a href="Edit?id=<%=id%>" class="ico edit" target="_blank">编辑</a></td>
                            </tr>
                            <%
                                    }
                                } else {
                            %>
                            <strong><%="请输入需要查询的博客标题（如：测试）"%></strong>
                            <% } %>

                        </table>

                        <!-- Pagging -->
                        <div class="pagging">
                            <div class="left">当前第${param.curPage}页       总共${requestScope.totalPage}页</div>
                            <div class="right">
                                <c:if test="${param.curPage == 1}">
                                    <a>首页</a>
                                    <a>上一页</a>
                                </c:if>

                                <c:if test="${param.curPage != 1}">
                                    <a href="Search?curPage=1">首页</a>
                                    <a href="Search?curPage=${param.curPage - 1}">上一页</a>
                                </c:if>

                                <c:if test="${param.curPage == requestScope.totalPage}">
                                    <a>下一页</a>
                                    <a>尾页</a>
                                </c:if>

                                <c:if test="${param.curPage != requestScope.totalPage}">
                                    <a href="Search?curPage=${param.curPage + 1}">下一页</a>
                                    <a href="Search?curPage=${requestScope.totalPage}">尾页</a>
                                </c:if>

                                <!--
                                <a href="#">上一页</a>
                                <a href="#">1</a>
                                <a href="#">2</a>
                                <a href="#">3</a>
                                <a href="#">4</a>
                                <a href="#">245</a>
                                <span>...</span>
                                <a href="#">下一页</a>
                                <a href="#">最后一页</a>
                                -->
                            </div>
                        </div>
                        <!-- End Pagging -->

                    </div>
                    <!-- Table -->

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
