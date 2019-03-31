<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>全部商品</title>

    <style type="text/css">
        .price{
            color: rgb(255,68,87);
            font-weight: bold;
        }
        .productName{
            color: rgb(102,102,102);
            font-family: "SimSun","Microsoft YaHei";
            font-size: small;
        }
        .sxName{
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
        }
        .thumbnail:hover {
            border: solid 1px rgb(62, 138, 200);
        }
    </style>
</head>
<body>

<!-- 顶部导航条start -->
<%@ include file="/jsp/topBar.jsp" %><!-- 顶部导航条end -->
<div class="container">

    <!-- 中间功能条start -->
    <form class="form-inline" role="form" action="/product/findProduct" method="get" style="margin: 10px;">
        <div id="demo" class="collapse hidden-xs" style="margin-bottom: 10px;">
            请勾选要查询的分类：
            <c:forEach items="${categoryList }" var="category">
            <span style="margin-left: 10px;"><input type="checkbox" class="ace ace-checkbox-2" value="${category.cid }"
                                                    name="cids">${category.categoryName }</span>
            </c:forEach>
        </div>


        <!--action?后的参数会被自动过滤掉，要通过hidden来添加-->
        <input type="hidden" name="persentPage" value="1"/>
        <div class="form-group pull-left">
            排序：
            <a href="/product/findProduct?publishTime_desc&persentPage=1">新品<span class="glyphicon glyphicon-arrow-down"></span></a>
            <a href="/product/findProduct?sort=price_asc&persentPage=1">价格<span class="glyphicon glyphicon-arrow-up"></span></a>
            <a href="/product/findProduct?sort=price_desc&persentPage=1">价格<span class="glyphicon glyphicon-arrow-down"></span></a>
        </div>
        <div class="form-group hidden-xs pull-left">
            <input type="text" class="form-control input-sm" placeholder="关键字" name="productName">
            价格：<input type="text" class="form-control input-sm" style="width:60px;" name="priceStrat">
            至 <input type="text" class="form-control input-sm" style="width:60px;" name="priceEnd">
        </div>
        <button class="btn btn-default hidden-xs pull-left" style="margin-left: 10px;">查询</button>
        <a href="/product/findProduct?persentPage=1"><button class="btn btn-default pull-left" style="margin-left: 10px;">重置</button></a>

        <span title="点击显示隐藏分类面板" class="glyphicon glyphicon-circle-arrow-up hidden-xs pull-left" data-target="#demo"
              data-toggle="collapse" style="margin-top: 7px;margin-left: 5px;color: #2aabd2;"></span>
    </form><!-- 中间功能条end -->

    <!-- 按钮触发模态框 -->
    <button class="btn btn-primary btn-default pull-left visible-xs" data-toggle="modal" data-target="#myModal">筛选</button>

    <form class="form-horizontal" role="form" action="/product/findProduct" method="get">
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">条件筛选</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">关键字</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" placeholder="请输入关键字" name="productName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">价格</label>
                            <div class="col-sm-10">
                                <input type="text"
                                       class="form-control pull-left"
                                       style="width:60px;"
                                       name="priceStrat">
                                <span class="pull-left">&nbsp;&nbsp;至&nbsp;&nbsp;</span><input type="text"
                                                                                               class="form-control pull-left"
                                                                                               style="width:60px;"
                                                                                               name="priceEnd">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">分类</label>
                            <div class="col-sm-10">
                                <c:forEach items="${categoryList }" var="category">
                                    <span class=" col-xs-6 col-md-3 col-lg-3"><input type="checkbox"
                                                                                     class="ace ace-checkbox-2"
                                                                                     value="${category.cid }"
                                                                                     name="cids">${category.categoryName }</span>
                                </c:forEach>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-primary">筛选</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
    </form>

    <div style="clear: both;">
    <c:if test="${productPage.beanList.size() == 0}">
        <div class="pull-right">
        当前条件下共查询到0个商品。
        </div>
    </c:if>
    </div>

    <!-- 商品列表展示区start -->
    <div class="row" style="clear: both;margin-top: 10px;">
        <c:forEach items="${productPage.beanList }" var="product">
            <div class="col-sm-6 col-md-3 col-xs-6">
                <div class="thumbnail">
                    <a class="pic" href="/product/findProductByPid?pid=${product.pid }"><img
                            src="${product.productImageUrl.split(";")[0] }"/></a>
                    <div class="caption">
                        <p class="price">
                            <span class="price_r">&yen;${product.price }</span>
                        </p>
                        <p class="sxName"><a id="productName" class="productName" title="${product.productName }"
                              href="/product/findProduct?pid=${product.pid }">${product.productName }</a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div><!-- 商品列表展示区end -->


    <!-- 分页导航条 pc版 start -->
    <ul class="pagination hidden-xs">
        <%-- 上一页在第一页时失效 --%>
        <c:choose>
            <c:when test="${productPage.persentPage == 1}">
                <li class="disabled"><a href="#">&lt;上一页</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${productPage.url}persentPage=${productPage.persentPage-1}">&lt;上一页</a></li>
            </c:otherwise>
        </c:choose><%-- 上一页在第一页时失效end --%>

        <c:choose>
            <%-- 当页码总数<=8，则显示全部页码 --%>
            <c:when test="${productPage.pageCount<=8}">
                <c:forEach begin="1" end="${productPage.pageCount}" var="page">
                    <c:choose>
                        <c:when test="${productPage.persentPage == page}"><%-- 当前页设置为活动状态 --%>
                            <li class="active"><a href="#">${page}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${productPage.url}persentPage=${page}">${page}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when><%-- 当页码总数小于8，则显示全部页码end --%>

            <c:otherwise>
                <c:choose>
                    <%-- 当页码总数大于8，并且当前页码<=5，则显示1234567...最后一页 --%>
                    <c:when test="${productPage.pageCount>8 && productPage.persentPage<=5}">
                        <c:forEach begin="1" end="7" var="page">
                            <c:choose>
                                <c:when test="${productPage.persentPage == page}">
                                    <li class="active"><a href="#">${page}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${productPage.url}persentPage=${page}">${page}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <li class="disabled"><a href="#">...</a></li>
                        <li><a href="${productPage.url}persentPage=${productPage.pageCount}">${productPage.pageCount}</a></li>
                    </c:when><%-- 当页码总数大于8，并且当前页码<=5，则显示1234567...最后一页end --%>

                    <c:otherwise>
                        <c:choose>
                            <%-- 当页码总数大于8，并且当前页码>5，并且当前页码在倒数4页内，则显示12...最后6页 --%>
                            <c:when test="${productPage.pageCount>8 && productPage.persentPage>=productPage.pageCount-4}">
                                <c:forEach begin="1" end="2" var="page">
                                    <li><a href="${productPage.url}persentPage=${page}">${page}</a></li>
                                </c:forEach>
                                <li class="disabled"><a href="#">...</a></li>
                                <c:forEach begin="${productPage.pageCount-6}" end="${productPage.pageCount}" var="page">
                                    <c:choose>
                                        <c:when test="${productPage.persentPage == page}">
                                            <li class="active"><a href="#">${page}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="${productPage.url}persentPage=${page}">${page}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when><%-- 当页码总数大于8，并且当前页码>5，并且当前页码在倒数4页内，则显示12...最后6页end --%>

                            <%-- 当页码总数大于8，并且当前页码>5，并且当前页码不在倒数4页内，则显示12...中间5页（当前-2，当前，当前+2）...最后一页 --%>
                            <c:otherwise>
                                <c:forEach begin="1" end="2" var="page">
                                    <li><a href="${productPage.url}persentPage=${page}">${page}</a></li>
                                </c:forEach>
                                <li class="disabled"><a href="#">...</a></li>
                                <c:forEach begin="${productPage.persentPage-2}" end="${productPage.persentPage-1}"
                                           var="page">
                                    <li><a href="${productPage.url}persentPage=${page}">${page}</a></li>
                                </c:forEach>
                                <li class="active"><a href="#">${productPage.persentPage}</a></li>
                                <c:forEach begin="${productPage.persentPage+1}" end="${productPage.persentPage+2}"
                                           var="page">
                                    <li><a href="${productPage.url}persentPage=${page}">${page}</a></li>
                                </c:forEach>
                                <li class="disabled"><a href="#">...</a></li>
                                <li><a href="${productPage.url}persentPage=${productPage.pageCount}">${productPage.pageCount}</a></li>
                            </c:otherwise><%-- 当页码总数大于8，并且当前页码>5，并且当前页码不在倒数4页内，则显示12...中间四页（当前-2，当前，当前+2）...最后一页end --%>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>



        <%-- 下一页在最后一页时失效 --%>
        <c:choose>
            <c:when test="${productPage.persentPage == productPage.pageCount}">
                <li class="disabled"><a href="#">下一页&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${productPage.url}persentPage=${productPage.persentPage+1}">下一页&gt;</a></li>
            </c:otherwise>
        </c:choose><%-- 下一页在最后一页时失效end --%>
    </ul><!-- 分页导航条 pc版 end -->



    <!-- 分页导航条 phone版 start -->
    <ul class="pagination visible-xs">
        <%-- 上一页及首页在第一页时失效 --%>
        <c:choose>
            <c:when test="${productPage.persentPage == 1}">
                <li class="disabled"><a href="#">&lt;上一页</a></li>
                <li class="disabled"><a href="#">首页</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${productPage.url}persentPage=${productPage.persentPage-1}">&lt;上一页</a></li>
                <li><a href="${productPage.url}persentPage=1">首页</a></li>
            </c:otherwise>
        </c:choose><%-- 上一页及首页在第一页时失效end --%>


        <%-- 下一页及尾页在最后一页时失效 --%>
        <c:choose>
            <c:when test="${productPage.persentPage == productPage.pageCount}">
                <li class="disabled"><a href="#">尾页</a></li>
                <li class="disabled"><a href="#">下一页&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${productPage.url}persentPage=${productPage.pageCount}">尾页</a></li>
                <li><a href="${productPage.url}persentPage=${productPage.persentPage+1}">下一页&gt;</a></li>
            </c:otherwise>
        </c:choose><%-- 下一页及尾页在最后一页时失效end --%>

    </ul><!-- 分页导航条 phone版 end -->
</div>
</body>
</html>