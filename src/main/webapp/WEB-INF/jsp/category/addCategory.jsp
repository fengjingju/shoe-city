<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加分类</title>

    <style type="text/css">
        .row {
            margin: 20px;
        }
    </style>
</head>
<body>
<!-- 顶部导航条start -->
<%@ include file="/jsp/topBar.jsp" %><!-- 顶部导航条end -->

<div class="container">

    <div class="row">
        <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
            <a href="#" class="thumbnail">
                <img src="/image/微信图片_20180902231010.jpg"
                     alt="通用的占位符缩略图">
            </a>
        </div>
    </div>

    <form action="/category/insertCategory" method="post">
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <input type="text" class="form-control" name="categoryName" placeholder="分类名称">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <input type="checkbox" class="ace ace-checkbox-2" value="1" name="isDisplayIndex"> 是否添加至首页分类导航
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <span style="color: red;">${fieldError}</span>
            </div>
        </div>
        <button class="btn btn-primary col-xs-4 col-xs-offset-4 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">添加分类
        </button>
    </form>
</div>
</body>
</html>