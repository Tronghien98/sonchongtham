<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
    <section class="breadcrumb-section set-bg" data-setbg="${projectContextPath}/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Danh mục bài viết</h2>
                        <div class="breadcrumb__option">
                            <a href="${urlIndex}">Trang chủ</a>
                            <span>${category.name}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-5">
                    <div class="sidebar">
                        <div class="sidebar__item">
                            <h4>Danh mục bài viết</h4>
                            <ul>
                                <c:if test="${not empty listCat}">
	                        		<c:forEach items="${listCat}" var="cat">
	                        			<li><a href="${urlCat}/${stringUtil.makeSlug(cat.name)}/${cat.id}" title="${cat.name}" >${cat.name}</a></li>
	                        		</c:forEach>
	                            </c:if>    
                            </ul>
                        </div>
                        <div class="sidebar__item">
                            <!-- Banner, quảng cáo... -->
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-7">
                	<c:choose>
                		<c:when test="${not empty listBlog}">
		                    <div class="row">
	                			<c:forEach items="${listBlog}" var="blog">
			                        <div class="col-lg-4 col-md-6 col-sm-6">
			                            <div class="product__item">
			                                <div class="product__item__pic set-bg" data-setbg="${projectContextPath}/img/product/product-1.jpg"></div>
			                                <div class="product__item__text">
			                                    <h6><a href="${urlDetail}/${stringUtil.makeSlug(blog.title)}/${blog.id}" title="${blog.title}">${blog.title}</a></h6>
			                                </div>
			                            </div>
			                        </div>
	                        	</c:forEach>
		                    </div>
		                    <div class="product__pagination">
		                    	<c:set value="${currentPage - 1}" var="pagePrevious"></c:set>
						   	    <c:if test="${currentPage == 1}">
						   	  		<c:set value="${currentPage}" var="pagePrevious"></c:set>
						        </c:if>
							  	<a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}/trang-${pagePrevious}"><i class="fa fa-long-arrow-left"></i></a>
		                        
		                        <c:choose>
							      <c:when test="${totalPage > 5}">
							      	  <c:if test="${currentPage > 3 and currentPage < (totalPage - 2)}">
							      	  	  <a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}">Trang Đầu</a>
									      <c:forEach begin="${currentPage - 2}" end="${currentPage + 2}" var="page">
									      	  <a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}/trang-${page}">${page}</a>
									      </c:forEach>
									      <a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}/trang-${totalPage}">Trang Cuối</a>
								      </c:if>
							      	  <c:if test="${currentPage <= 3}">
									      <c:forEach begin="1" end="5" var="page">
								      	  	  <a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}/trang-${page}">${page}</a>
									      </c:forEach>
									      <a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}/trang-${totalPage}">Trang Cuối</a>
								      </c:if>
							      	  <c:if test="${currentPage >= (totalPage - 2)}">
							      	  	  <a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}">Trang Đầu</a>
									      <c:forEach begin="${totalPage - 4}" end="${totalPage}" var="page">
								      	  	  <a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}/trang-${page}">${page}</a>
									      </c:forEach>
								      </c:if>
							      </c:when>
							      <c:otherwise>
							      	  <c:forEach begin="1" end="${totalPage}" var="page">
							      	  	  <a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}/trang-${page}">${page}</a>
								      </c:forEach>
							      </c:otherwise>
							    </c:choose>
		                        
		                        <c:set value="${currentPage + 1}" var="pageNext"></c:set>
						        <c:if test="${currentPage == totalPage}">
						      		<c:set value="${currentPage}" var="pageNext"></c:set>
						        </c:if>
							  	<a href="${urlCat}/${stringUtil.makeSlug(category.name)}/${category.id}/trang-${pageNext}"><i class="fa fa-long-arrow-right"></i></a>
		                    </div>
	                    </c:when>
	                    <c:otherwise>
	                    	<div class="row">
				                <div class="col-lg-12">
				                    <div class="alert alert-primary" role="alert">
									  	Không có bài viết
									</div>
				                </div>
				            </div>
	                    </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>