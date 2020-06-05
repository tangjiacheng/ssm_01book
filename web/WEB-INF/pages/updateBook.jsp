<%--
  Created by IntelliJ IDEA.
  User: TJC
  Date: 2020/6/5
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3 class="text-center">
                    更新书籍信息
                </h3>
            </div>
        </div>

    </div>
    <div class="row clearfix">
        <div class="col-md-4 column"></div>
        <div class="col-md-4 column">
            <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
                <div><input type="hidden" name="bookId" value="${QBook.bookId}"></div>
                <div class="form-group">
                    <label>书籍名称</label>
                    <input type="text" name="bookName" class="form-control" value="${QBook.bookName}" required>
                </div>
                <div class="form-group">
                    <label>书籍数量</label>
                    <input type="number" name="bookCount" class="form-control" value="${QBook.bookCount}" required>
                </div>
                <div class="form-group">
                    <label>书籍介绍</label>
                    <input type="text" name="detail" class="form-control" value="${QBook.detail}" required>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-default">
                </div>
            </form>
        </div>
        <div class="col-md-4 column"></div>
    </div>



    <div class="row">
        <div class="col-md-4 column">
            <a href="${pageContext.request.contextPath}/book/main" class="btn btn-primary">返回</a>
        </div>
    </div>

</div>

</body>
</html>