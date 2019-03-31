<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>

    <!-- Bootstrap -->
    <link href="/bootstrap-3.3.7-dist/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="/bootstrap-3.3.7-dist/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
    <script src="/bootstrap-3.3.7-dist/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <style type="text/css">

        /* 导航css */
        .navbar{
            margin-bottom: 0px;
            border-radius: 5px;
            background: #ffffff;
        }
        .navbar .nav > li > a{
            color: #000000;
        }
        .navbar .nav >li >a:visited{
            color: rgb(62,138,200);
        }
        .navbar .nav > li > a:hover {
            color: rgb(62,138,200);;
            background-color: rgb(242,242,242);
        }
        .navbar .nav >li >a:active{
            color: rgb(62,138,200);
        }


        #pcLogo{
            clear: both;
            display:inline-block;
            width:100%;/*图片自适应的父容器的宽*/
            height:100%;/*图片自适应的父容器的高*/
            margin-top: 50px;
            margin-bottom: 50px;
            text-align: center;
            vertical-align: middle;
        }
        #pcLogo img{
            width:25%;
            height:25%;
        }
        #phoneLogo{
            clear: both;
            display:inline-block;
            width:100%;/*图片自适应的父容器的宽*/
            height:100%;/*图片自适应的父容器的高*/
            margin-top: 20px;
            margin-bottom: 20px;
            text-align: center;
            vertical-align: middle;
        }
        #phoneLogo img{
            width:70%;
            height:70%;
        }
    </style>
</head>
<body>
<div class="pull-right">
    <c:choose>
        <c:when test="${empty sessionScope.admin }">
            <a id="loginUrl" href="/admin/login" style="color: #b1bdce;">管理员入口</a>
        </c:when>
        <c:otherwise>
            <span style="color: #575761;">管理员：${sessionScope.admin.userName}</span>
        </c:otherwise>
    </c:choose>
</div>


<div id="pcLogo" class="hidden-xs">
    <img src="/image/logo/4456454151.png"/>
</div>

<div id="phoneLogo" class="visible-xs">
    <img src="/image/logo/4456454151.png"/>
</div>


    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#example-navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/index">首页</a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/product/findProduct?persentPage=1">全部商品</a></li>
                    <li><a href="/product/findProduct?publishTime_desc&persentPage=1">新品上架</a></li>
                    <c:forEach items="${categoryList }" var="category">
                        <c:if test="${category.isDisplayIndex == 1}">
                            <li><a href="/product/findProduct?persentPage=1&cids=${category.cid}">${category.categoryName}</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${not empty sessionScope.admin }">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            管理员菜单 <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/category/findAllCategory?requestType=category'/>">查看所有分类</a></li>
                            <li><a href="/category/findAllCategory?requestType=product">发布商品</a></li>
                            <li><a href="/category/toAddCategory">添加分类</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/quit">退出登录</a></li>
                        </ul>
                    </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

</body>
</html>