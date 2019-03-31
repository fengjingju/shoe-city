<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品详情</title>

    <style type="text/css">
        .productName {
            color: rgb(102, 102, 102);
            font-family: "SimSun", "Microsoft YaHei";
            font-size: large;
            font-weight: bold;
            margin: 20px 20px;
        }

        .price {
            color: rgb(255, 68, 87);
            font-weight: bold;
            font-size: x-large;
        }

        .detail{
            margin-top: 20px;
        }

        .productDetail{
            margin: 20px 20px;
            margin-left: 50px;
        }

        #floatWeixin{
            display:inline-block;
            width:100%;/*图片自适应的父容器的宽*/
            height:100%;/*图片自适应的父容器的高*/
        }
        #floatWeixin img{
            width:100%;
        }

        .detailImage{
            display:inline-block;
            width:100%;/*图片自适应的父容器的宽*/
            height:100%;/*图片自适应的父容器的高*/
            text-align: center;
            vertical-align: middle;
        }
        .detailImage img{
            width:100%;
            height: 100%;
        }
    </style>
</head>
<body>


<!-- 顶部导航条start -->
<%@ include file="/jsp/topBar.jsp" %><!-- 顶部导航条end -->

<div class="container">

    <div class="row">
        <div class="col-sm-12 col-md-5 pull-left">
            <!--data-interval:轮播时间间隔；data-ride="carousel" 属性用于标记轮播在页面加载时就开始动画播放。-->
            <div id="myCarousel" class="carousel slide" data-interval="5000">
                <!-- 轮播（Carousel）指标，下面的小圆白点，标识当前是哪个图片 -->
                <ol class="carousel-indicators">
                    <c:set var="pointCount" value="0"></c:set>
                    <c:forEach items="${fn:split(product.productImageUrl, ';')}">
                        <c:choose>
                            <c:when test="${pointCount == 0}">
                                <li data-target="#myCarousel" data-slide-to="${pointCount}" class="active"></li>
                            </c:when>

                            <c:otherwise>
                                <li data-target="#myCarousel" data-slide-to="${pointCount}"></li>
                            </c:otherwise>
                        </c:choose>
                        <c:set var="pointCount" value="${pointCount+1}"/>
                    </c:forEach>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <c:set var="count" value="0"></c:set>
                    <c:forEach items="${fn:split(product.productImageUrl, ';')}" var="productImageUrl">
                        <c:choose>
                            <c:when test="${count == 0}">
                                <div class="item active">
                                    <img src="${productImageUrl}">
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div class="item">
                                    <img src="${productImageUrl}">
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:set var="count" value="${count+1}"/>
                    </c:forEach>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <div class="col-sm-12 col-md-7 pull-right hidden-xs">
            <div class="productName">${product.productName}</div>
            <div class="pull-left" style="width: 70%">
                <div class="productDetail">
                    价格 <span class="price">&yen;${product.price }</span>
                </div>
                <div class="productDetail">
                    尺码 <span class="price">${product.size }</span>
                </div>
                <div class="productDetail">
                    运费：
                    <c:choose>
                        <c:when test="${product.isIncludePostage == 1}">
                            <span>包邮</span>
                        </c:when>
                        <c:otherwise><span>不包邮</span></c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div id="floatWeixin" class="pull-right" style="width: 30%"><img src="/image/logo/20180916135121.png"/></div>
        </div>
    </div>

    <div class="col-sm-12 col-md-7 visible-xs">
        <p style="margin-top: 10px;">
            <span style="color: #8f979b">价格：</span> <span style="font-size: x-large;color: red;">&yen;${product.price }
            </span>
        </p>
        <p style="font-family: 黑体;font-weight: bold;">
            ${product.productName}
        </p>
        <p>
            尺码： <span>${product.size }</span>
        </p>
        <p style="color: #8f979b;font-size: small;">
            快递： <c:choose>
            <c:when test="${product.isIncludePostage == 1}">
                <span>包邮</span>
            </c:when>
            <c:otherwise><span>不包邮</span></c:otherwise>
        </c:choose>
        </p>
    </div>


    <div class="panel panel-info detail hidden-xs">
        <div class="panel-heading">商品详情</div>
        <div class="panel-body">
            <p><c:choose>
                <c:when test="${product.description == null || product.description == ''}">
                    <span>卖家最近比较懒哦~~ 此商品暂无详细信息。</span>
                </c:when>
                <c:otherwise>
                    ${product.description }
                </c:otherwise>
            </c:choose>
            </p>
        </div>
        <c:if test="${product.productDetailImageUrl != null && product.productDetailImageUrl != ''}">
            <ul class="list-group">
                <li class="list-group-item">
                    <div class="detailImage">
                    <c:forEach items="${fn:split(product.productDetailImageUrl, ';')}" var="productDetailImageUrl">
                        <img class="text-center" src="${productDetailImageUrl}">
                    </c:forEach>
                    </div>
                </li>
            </ul>
        </c:if>
    </div>

</div>
</div>
<div class="panel panel-info detail visible-xs">
    <div class="panel-heading">商品详情</div>
    <div class="panel-body">
        <p><c:choose>
            <c:when test="${product.description == null || product.description == ''}">
                <span>卖家最近比较懒哦~~ 此商品暂无详细信息。</span>
            </c:when>
            <c:otherwise>
                ${product.description }
            </c:otherwise>
        </c:choose>
        </p>
    </div>
    <c:if test="${product.productDetailImageUrl != null && product.productDetailImageUrl != ''}">
        <ul class="list-group">
            <li class="list-group-item">
                <div class="detailImage">
                    <c:forEach items="${fn:split(product.productDetailImageUrl, ';')}" var="productDetailImageUrl">
                        <img class="text-center" src="${productDetailImageUrl}">
                    </c:forEach>
                </div>
            </li>
        </ul>
    </c:if>
</div>
</body>

<script>

    $(function () {
        // 获取手指在轮播图元素上的一个滑动方向（左右）

        // 获取界面上轮播图容器
        var $carousels = $('.carousel');
        var startX, endX;
        // 在滑动的一定范围内，才切换图片
        var offset = 50;
        // 注册滑动事件
        $carousels.on('touchstart', function (e) {
            // console.log(e);
            // 手指触摸开始时记录一下手指所在的坐标x
            startX = e.originalEvent.touches[0].clientX;

        });
        $carousels.on('touchmove', function (e) {
            // 目的是：记录手指离开屏幕一瞬间的位置 ，用move事件重复赋值
            endX = e.originalEvent.touches[0].clientX;
        });
        $carousels.on('touchend', function (e) {
            //console.log(endX);
            //结束触摸一瞬间记录手指最后所在坐标x的位置 endX
            //比较endX与startX的大小，并获取每次运动的距离，当距离大于一定值时认为是有方向的变化
            var distance = Math.abs(startX - endX);
            if (distance > offset) {
                //说明有方向的变化
                //根据获得的方向 判断是上一张还是下一张出现
                $(this).carousel(startX > endX ? 'next' : 'prev');
            }
        })
    });
</script>
</html>