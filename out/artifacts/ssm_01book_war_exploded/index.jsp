<%--
  Created by IntelliJ IDEA.
  User: TJC
  Date: 2020/6/5
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

  <head>
    <title>$Title$</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <h1 class="text-center">
          <em><span class="marker"><strong>SSM整合项目</strong></span></em>
        </h1><hr />

        <ul class="thumbnails">
          <li class="span4">
            <div class="thumbnail">
              <img alt="150x100" src="img/library.jpeg" class="img-thumbnail"/>
              <div class="caption">
                <h3>
                  书籍管理系统
                </h3>
                <p>
                  整合Spring, SpringMVC和MyBatis的简单书籍管理系统
                </p>
                <p>
                  支持用户登录/注销
                </p>
                <p>
                  支持书籍的增删改查操作
                </p>
                <p>
                  <a class="btn btn-primary" href="book/main">主页</a> <a class="btn" href="https://github.com/tangjiacheng/ssm_01book">源码</a>
                </p>
              </div>
            </div>
          </li>
          <li class="span4">
            <div class="thumbnail">
              <img alt="150x100" src="img/library.jpeg" class="img-thumbnail" />
              <div class="caption">
                <h3>
                  敬请期待
                </h3>
                <p>
                  哈佛结构是一种将程序指令存储和数据存储分开的存储器结构，它的主要特点是将程序和数据存储在不同的存储空间中，进行独立编址。
                </p>
                <p>
                  <a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a>
                </p>
              </div>
            </div>
          </li>
          <li class="span4">
            <div class="thumbnail">
              <img alt="150x100" src="img/library.jpeg" class="img-thumbnail" />
              <div class="caption">
                <h3>
                  敬请期待
                </h3>
                <p>
                  改进型的哈佛结构具有一条独立的地址总线和一条独立的数据总线，两条总线由程序存储器和数据存储器分时复用，使结构更紧凑。
                </p>
                <p>
                  <a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a>
                </p>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
  </body>
</html>