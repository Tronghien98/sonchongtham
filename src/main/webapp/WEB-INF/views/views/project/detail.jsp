<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
    <section class="breadcrumb-section set-bg" data-setbg="${projectContextPath}/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Chi tiết tin tức</h2>
                        <div class="breadcrumb__option">
                            <a href="${urlIndex}">Trang chủ</a>
                            <a href="#">${blog.cat.name}</a>
                            <span>Chi tiết</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img class="product__details__pic__item--large"
                                src="${projectContextPath}/img/product/details/product-details-1.jpg" alt="">
                        </div>
                        <div class="product__details__pic__slider owl-carousel">
                            <img data-imgbigurl="${projectContextPath}/img/product/details/product-details-2.jpg"
                                src="${projectContextPath}/img/product/details/thumb-1.jpg" alt="">
                            <img data-imgbigurl="${projectContextPath}/img/product/details/product-details-3.jpg"
                                src="${projectContextPath}/img/product/details/thumb-2.jpg" alt="">
                            <img data-imgbigurl="${projectContextPath}/img/product/details/product-details-5.jpg"
                                src="${projectContextPath}/img/product/details/thumb-3.jpg" alt="">
                            <img data-imgbigurl="${projectContextPath}/img/product/details/product-details-4.jpg"
                                src="${projectContextPath}/img/product/details/thumb-4.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__text">
                        <h3>${blog.title}</h3>
                        <p>Ngày đăng: ${dateUtil.formatDate(blog.createAt)} - Lượt xem: ${blog.views}</p>
                        <p>${stringUtil.setStringCompact(blog.detail,1000)}</p>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab"
                                    aria-selected="true">Chi tiết</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab"
                                    aria-selected="false">Thông tin</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                <div class="product__details__tab__desc">
                                    <h6>Chi tiết bài viết</h6>
                                    <p>${blog.detail}</p>
                                </div>
                            </div>
                            <div class="tab-pane" id="tabs-2" role="tabpanel">
                                <div class="product__details__tab__desc">
                                    <h6>Thông tin</h6>
                                    <p>Thông tin về doanh nghiệp, dịch vụ...</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="related-product">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title related__product__title">
                        <h2>Tin liên quan</h2>
                    </div>
                </div>
            </div>
            <div class="row">
            	<c:if test="${not empty listBlogRelate}">
            		<c:forEach items="${listBlogRelate}" var="blogRelate">
		                <div class="col-lg-3 col-md-4 col-sm-6">
		                    <div class="product__item">
		                        <div class="product__item__pic set-bg" data-setbg="${projectContextPath}/img/product/product-1.jpg"></div>
		                        <div class="product__item__text">
		                            <h6><a href="${urlDetail}/${stringUtil.makeSlug(blogRelate.title)}/${blogRelate.id}">${blogRelate.title}</a></h6>
		                        </div>
		                    </div>
		                </div>
	                </c:forEach>
                </c:if>
            </div>
        </div>
    </section>