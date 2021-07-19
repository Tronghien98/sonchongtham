<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
    <section class="hero">
		<div class="container">
            <div class="row">
            	<div class="col-lg-3"></div>
                <div class="col-lg-9">
                    <div class="hero__search"></div>
                    <div class="hero__item set-bg" data-setbg="${projectContextPath}/img/hero/banner.jpg">
                        <div class="hero__text">
                            <span>Doanh nghiệp</span>
                            <h2>Sơn chống thấm <br />100% Chất lượng</h2>
                            <p>Hỗ trợ khác hàng 24/7</p>
                            <a href="${urlBlog}" class="primary-btn">Tin tức</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>    
    </section>
    
    <section class="categories">
        <div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel">
                	<c:if test="${not empty listBlogByViews}">
                		<c:forEach items="${listBlogByViews}" var="blog">
		                    <div class="col-lg-3">
		                        <div class="categories__item set-bg" data-setbg="${projectContextPath}/img/categories/cat-1.jpg">
		                            <h5><a href="${urlDetail}/${stringUtil.makeSlug(blog.title)}/${blog.id}" title="${blog.title}">${stringUtil.setStringCompact(blog.title,10)}</a></h5>
		                        </div>
		                    </div>
	                    </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </section>

    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Tin mới nhất</h2>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
            	<c:if test="${not empty listBlogNew}">
                	<c:forEach items="${listBlogNew}" var="blog">
		                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
		                    <div class="featured__item">
		                        <div class="featured__item__pic set-bg" data-setbg="${projectContextPath}/img/featured/feature-1.jpg"></div>
		                        <div class="featured__item__text">
		                            <h6><a href="${urlDetail}/${stringUtil.makeSlug(blog.title)}/${blog.id}" title="${blog.title}">${blog.title}</a></h6>
		                        </div>
		                    </div>
		                </div>
                	</c:forEach>
                </c:if>
            </div>
        </div>
    </section>

    <div class="banner">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="${projectContextPath}/img/banner/banner-1.jpg" alt="">
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="${projectContextPath}/img/banner/banner-2.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section class="from-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>Về chúng tôi</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="${projectContextPath}/img/blog/blog-1.jpg" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i> May 4,2019</li>
                                <li><i class="fa fa-comment-o"></i> 5</li>
                            </ul>
                            <h5><a href="#">Cooking tips make cooking simple</a></h5>
                            <p>Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat </p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="${projectContextPath}/img/blog/blog-2.jpg" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i> May 4,2019</li>
                                <li><i class="fa fa-comment-o"></i> 5</li>
                            </ul>
                            <h5><a href="#">6 ways to prepare breakfast for 30</a></h5>
                            <p>Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat </p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="${projectContextPath}/img/blog/blog-3.jpg" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i> May 4,2019</li>
                                <li><i class="fa fa-comment-o"></i> 5</li>
                            </ul>
                            <h5><a href="#">Visit the clean farm in the US</a></h5>
                            <p>Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>