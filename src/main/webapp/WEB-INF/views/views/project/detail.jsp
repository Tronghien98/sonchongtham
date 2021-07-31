<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>


    <!-- Blog Details Section Begin -->
    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-5 order-md-1 order-2">
                    <div class="blog__sidebar">
                       
                        <div class="blog__sidebar__item">
                            <h4>Danh mục</h4>
                            <ul>
                                <li><a href="#">All</a></li>
                                <c:forEach items="${listCat}" var="cat">
                                	<li><a href="#">${cat.name} (20)</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="blog__sidebar__item">
                            <h4>Bài viết nổi bật</h4>
                            <div class="blog__sidebar__recent">
                            	<c:forEach items="${listBlogByViews}" var="blog">
                                <a href="#" class="blog__sidebar__recent__item">
                                    <div class="blog__sidebar__recent__item__pic">
                                        <img src="${urlUpload}/${blog.picture}" alt="" style="max-width: 50px; max-height: 50px">
                                    </div>
                                    <div class="blog__sidebar__recent__item__text">
                                        <h6>${blog.title}</h6>
                                        <span><fmt:formatDate type = "date"  value = "${blog.createAt}" /></span>
                                    </div>
                                </a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-7 order-md-1 order-1">
                    <div class="blog__details__text">
                    	<c:if test="${not empty blog.picture}">
                    		<img src="${urlUpload}/${blog.picture}" alt="">
                    	</c:if>
                        ${blog.detail}
                    </div>
                    <div class="blog__details__content">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="blog__details__author">
                                    <div class="blog__details__author__pic">
                                        <img src="${urlUpload}/${blog.user.avatar}" alt="">
                                    </div>
                                    <div class="blog__details__author__text">
                                        <h6>${blog.user.username}</h6>
                                        <span>${blog.createAt}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="blog__details__widget">
                                    <ul>
                                        <li><span>Danh mục:</span> ${blog.cat.name}</li>
                                    </ul>
                                    <div class="blog__details__social">
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                        <a href="#"><i class="fa fa-google-plus"></i></a>
                                        <a href="#"><i class="fa fa-envelope"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Details Section End -->

    <!-- Related Blog Section Begin -->
    <section class="related-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title related-blog-title">
                        <h2>Bài viết liên quan</h2>
                    </div>
                </div>
            </div>
            <div class="row">
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
            </div>
        </div>
    </section>