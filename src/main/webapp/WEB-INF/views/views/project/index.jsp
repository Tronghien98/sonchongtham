<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>

    <section class="hero">
		<div class="container">
            <div class="row">
            	
                <div class="col-lg-12">
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
                	<c:if test="${not empty listCat}">
                		<c:forEach items="${listCat}" var="cat">
		                    <div class="col-lg-3">
		                        <div class="categories__item set-bg" data-setbg="${urlUpload}/${cat.image}">
		                            <h5><a href="${urlCat}/${stringUtil.makeSlug(cat.name)}/${cat.id}" title="${blog.title}">${cat.name}</a></h5>
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
                        <h2>Bài viết của chúng tôi</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                       		<li class="active" data-filter="*">Tất cả</li>
                        <c:forEach items="${listCat}" var="cat">
                        	<li data-filter=".danh-muc-${cat.id}">${cat.name}</li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
            	<c:if test="${not empty listBlogNew}">
                	<c:forEach items="${listBlogNew}" var="blog">
		                <div class="col-lg-3 col-md-4 col-sm-6 mix danh-muc-${blog.cat.id}">
		                    <div class="featured__item">
		                        <div class="featured__item__pic set-bg" data-setbg="${urlUpload}/${blog.picture}"></div>
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
                        <h2>Bài viết nổi bật</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                	<c:if test="${not empty listBlogByViews}">
                		<c:forEach items="${listBlogByViews}" var="blog">
                		 <div class="col-lg-4 col-md-4 col-sm-6">
                			<div class="blog__item">
		                        <div class="blog__item__pic">
		                            <img src="${urlUpload}/${blog.picture}" alt="">
		                        </div>
		                        <div class="blog__item__text">
		                            <ul>
		                                <li><i class="fa fa-calendar-o"></i><fmt:formatDate type = "date"  value = "${blog.createAt}" /></li>
		                                
		                            </ul>
		                            <h5><a href="${urlDetail}/${stringUtil.makeSlug(blog.title)}/${blog.id}">${blog.title}</a></h5>
		                            <p>${stringUtil.setStringCompact(blog.detail, 50)} </p>
		                        </div>
		                    </div>
		                 </div>
                		</c:forEach>
                	</c:if>
            </div>
        </div>
    </section>