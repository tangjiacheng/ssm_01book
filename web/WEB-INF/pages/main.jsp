<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TJC
  Date: 2020/6/5
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示页面</title>

    <%--bootstrap美化--%>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h2 class="text-center">
                    所有书籍
                </h2>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAdd">添加书籍</a>
            </div>
            <div class="col-md-4 column">

            </div>
            <div class="col-md-4 column">
                <%--查询书籍--%>
                <form class="form-inline" action="${pageContext.request.contextPath}/book/findBook" method="post" style="float: right">
                    <span style="color: red; font-weight: bold">${error}</span>
                    <input type="text" name="queryBookName" placeholder="请输入要查询的书籍名称">
                    <input type="submit" value="查询" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>书籍编号</th>
                    <th>书籍名称</th>
                    <th>书籍数量</th>
                    <th>书籍详情</th>
                    <th>操作</th>
                </tr>
                </thead>
                <%--书籍从数据库中查询出来, 从books中遍历出来--%>
                <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.bookId}</td>
                        <td>${book.bookName}</td>
                        <td>${book.bookCount}</td>
                        <td>${book.detail}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/book/toUpdate?bookId=${book.bookId}">修改</a>
                            &nbsp; | &nbsp;
                            <a href="${pageContext.request.contextPath}/book/deleteBook/${book.bookId}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>

    <div class="row">

            <a href="${pageContext.request.contextPath}/book/main" class="btn btn-primary">返回主页</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/logout">注销</a>
    </div>

</div>

</body>
</html>
