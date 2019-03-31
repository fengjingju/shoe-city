<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>发布商品</title>

    <style type="text/css">
        .row{
            margin: 20px;
        }
        #login{
            margin: 0 auto;
        }
    </style>
</head>
<body>
<!-- 顶部导航条start -->
<%@ include file="/jsp/topBar.jsp" %><!-- 顶部导航条end -->

<div class="container">
    <form action="/product/publishProduct" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <input type="text" class="form-control" name="productName" placeholder="*商品名">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <input type="text" class="form-control" name="price" placeholder="*价格">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <input type="text" class="form-control" name="size" placeholder="*尺码">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <input type="checkbox" class="ace ace-checkbox-2" value="1" name="isIncludePostage"> 是否包邮
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                <span style="color: red;">*</span>选择商品展示缩略图：<input type="file" class="form-control" name="productImage" accept="image/*" multiple="multiple"/> <br>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                选择商品详情展示图：<input type="file" class="form-control" name="productDetailImage" accept="image/*" multiple="multiple"/> <br>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">选择分类：
            </div>
            <c:forEach items="${categoryList }" var="category">
                <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                    <span class="price_r">
                        <input type="checkbox" class="ace ace-checkbox-2" value="${category.cid }"
                               name="cids">${category.categoryName }
                    </span>
                </div>
            </c:forEach>
        </div>

        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                商品详情描述：
                <textarea class="form-control" name="description" rows="3"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4" style="text-align: center;">
                <span style="color: red;">${fieldError}</span>
            </div>
        </div>
        <div id="login">
            <button class="btn btn-primary col-xs-4 col-xs-offset-4 col-md-8 col-md-offset-2 col-lg-4 col-lg-offset-4">
                发布商品
            </button>
        </div>
    </form>
</div>
</body>
</html>