<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品所有分类</title>


    <style type="text/css">
    </style>
</head>
<body>

<!-- 顶部导航条start -->
<%@ include file="/jsp/topBar.jsp" %><!-- 顶部导航条end -->

<div class="container">
    <div class="table-responsive">
        <table class="table">
            <caption>商品所有分类</caption>
            <thead>
            <tr>
                <th>分类名称</th>
                <th>是否在首页分类导航显示</th>
                <th>操作</th></tr>
            </thead>
            <tbody>
            <c:forEach items="${categoryList }" var="category">
            <tr>
                <td>${category.categoryName }</td>
                <td>
                    <c:choose>
                        <c:when test="${category.isDisplayIndex == 1}">
                            已在首页显示
                        </c:when>
                        <c:otherwise>未显示</c:otherwise>
                    </c:choose>
                </td>
                <td><a href="<c:url value='/category/modifyCategory?cid=${category.cid }'/>"><button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modifyCategory">修改显示状态</button></a>
                    <a href="<c:url value='/category/deleteCategoryByCid?cid=${category.cid }'/>" onclick="alert('确定删除该分类？')"><button class="btn btn-primary btn-sm">删除该分类</button></a></td></tr>
            <tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>