<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>

    <!-- 由于引用该文件的文件已经存在这些js和css，重复引用会导致冲突，使导航无法下拉 -->
    <!-- Bootstrap -->
    <%--<link href="/bootstrap-3.3.7-dist/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">--%>
    <%--<script src="/bootstrap-3.3.7-dist/bootstrap-3.3.7-dist/js/jquery.min.js"></script>--%>
    <%--<script src="/bootstrap-3.3.7-dist/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>--%>

    <style type="text/css">

        #topImage{
            display:inline-block;
            width:100%;/*图片自适应的父容器的宽*/
            height:100%;/*图片自适应的父容器的高*/
        }
        #topImage img{
            width:100%;
            height:100%;
        }
        #floatWeixin{
            position: fixed;
            display:inline-block;
            width:100%;/*图片自适应的父容器的宽*/
            height:100%;/*图片自适应的父容器的高*/
            left: 70%;
            top: 30%;
        }
        #floatWeixin img{
            width:20%;
        }
    </style>
</head>
<body>

<%@ include file="/jsp/topBar.jsp" %>

<div id="floatWeixin">
    <img src="/image/logo/20180916135121.png"/>
</div>


    <div id="topImage">
        <img src="/image/index/20180916124432.jpg"/>
        <img src="/image/index/20180916124440.jpg"/>
    </div>

</body>
</html>