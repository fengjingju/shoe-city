<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理员登录</title>

    <!-- Bootstrap -->
    <link href="/bootstrap-3.3.7-dist/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="/bootstrap-3.3.7-dist/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

    <style type="text/css">
        .row{
            margin: 20px;
        }
    </style>
</head>
<body>

<%@ include file="/jsp/topBar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
            <a href="#" class="thumbnail">
                <img src="/image/微信图片_20180902231010.jpg"
                     alt="通用的占位符缩略图">
            </a>
        </div>
    </div>
    <form action="/admin/login" method="post">
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <input type="text" class="form-control" name="userName" placeholder="请输入用户名">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <input type="password" class="form-control" name="password" placeholder="请输入用密码">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4" style="text-align: center;">
                <span style="color: red;">${error}</span>
            </div>
        </div>
        <button class="btn btn-primary col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">登录
        </button>
    </form>
</div>
</body>
</html>