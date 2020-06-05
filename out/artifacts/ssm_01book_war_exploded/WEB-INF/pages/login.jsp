<%--
  Created by IntelliJ IDEA.
  User: TJC
  Date: 2020/6/5
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>Title</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%--<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-12 column">
            <h3 class="text-center">
                书籍管理系统
            </h3>
            <div class="col-md-5 column"></div>
            <div class="col-md-3 column">
                <form action="${pageContext.request.contextPath}/book/login" method="post">
                    <div class="form-group">
                        <label class="sr-only">用户名</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="用户名" required>
                    </div>
                    <div class="form-group">
                        <label class="sr-only">密码</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="密码" required>
                    </div>
                    <span style="color: red">${msg}</span>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Remember me
                        </label>
                    </div>
                    <button type="submit" class="btn btn-default">登录</button>
                </form>
            </div>
            <div class="col-md-3 column"></div>
        </div>
    </div>
</div>--%>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3 class="text-center">
                    系统登录
                </h3>
            </div>
        </div>

    </div>
    <div class="row clearfix">
        <div class="col-md-4 column"></div>
        <div class="col-md-4 column">
            <form action="${pageContext.request.contextPath}/book/login" method="post">
                <div class="form-group">
                    <label class="sr-only">用户名</label>
                    <input type="text" class="form-control" name="username" placeholder="用户名" required>
                </div>
                <div class="form-group">
                    <label class="sr-only">密码</label>
                    <input type="password" class="form-control" name="password" placeholder="密码" required>
                </div>
                <span style="color: red">${msg}</span>
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> Remember me
                    </label>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">登录</button>

                </div>
                <div>
                    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary">返回主页</a>
                </div>

            </form>

        </div>
        <div class="col-md-4 column"></div>
    </div>


</div>

</body>
</html>
