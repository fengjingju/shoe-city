<%--
  Created by IntelliJ IDEA.
  User: NARUTO
  Date: 2018/9/2
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品发布结果</title>
</head>
<body>
<div style="width: 200px;margin: 0 auto;margin-top: 100px;">
    <span style="color: red;font-weight: bold;text-align: center;">${publishRestlt}</span>
    <p>
<span style="text-align: center;">
    <a href="/category/findAllCategory?requestType=product">继续添加</a>
    <a href="/product/findProduct?persentPage=1">查看商品列表</a></span>
    </p>
</div>
</body>
</html>
