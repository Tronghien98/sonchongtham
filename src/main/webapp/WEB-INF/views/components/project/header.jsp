<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__left">
                            <ul>
                                <li><i class="fa fa-envelope"></i> mail@gmail.com</li>
                                <li>Dịch vụ sơn chống thấm</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__right">
                            <div class="header__top__right__social">
                                <a href="#" title="facebook"><i class="fa fa-facebook"></i></a>
                                <a href="#" title="instagram"><i class="fa fa-instagram"></i></a>
                            </div>
                            <div class="header__top__right__auth">
                                <a href="#"><i class="fa fa-user"></i> Đăng nhập</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        <a href="${urlIndex}"><img src="${projectContextPath}/img/logo.jpg" alt=""  
                        style="max-width: 100px; max-height: 50px; margin: 0px 30px 0px 20px; "></a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="${urlIndex}">Trang chủ</a></li>
                            <li><a href="${urlBlog}">Tin tức</a></li>
							<li><a href="${urlContact}">Liên hệ</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>

    <section class="hero hero-normal">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>Danh mục</span>
                        </div>
                        <ul>
                        	<c:if test="${not empty listCat}">
                        		<c:forEach items="${listCat}" var="cat">
                        			<li><a href="${urlCat}/${stringUtil.makeSlug(cat.name)}/${cat.id}" title="${cat.name}" >${cat.name}</a></li>
                        		</c:forEach>
                            </c:if>                  
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="${urlSearch}" method="get">
                                <input type="text" value="${keyword}" name="keyword" placeholder="Tìm kiếm">
                                <button type="submit" class="site-btn">Tìm</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>0899922239</h5>
                                <span>Thời gian: Hỗ trợ 24/7</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>