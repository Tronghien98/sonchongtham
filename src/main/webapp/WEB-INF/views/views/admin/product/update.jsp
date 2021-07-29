<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>   
		  <div class="col-md-10">
			  <form id="formBlog" action="" method="post" enctype="multipart/form-data">
	  			  <div class="row">
	  				<div class="col-md-12 panel-info">
			  			<div class="content-box-header panel-heading">
		  					<div class="panel-title ">Sửa bài viết</div>
			  			</div>
			  			<div class="content-box-large box-with-header">
				  			<div>
								<div class="row mb-10"></div>
								<c:if test="${not empty error}">
									<div class="alert alert-danger" role="alert">
									    ${error}
									</div>
								</c:if>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="name">Tiêu đề bài viết</label>
											<form:errors path="blog.title" cssClass="err"/>
											<input type="text" value="${blog.title}" name="title" class="form-control" placeholder="Nhập tiêu đề bài viết">										
										</div>
										<div class="form-group">
											<label for="cat">Danh mục</label>
											<form:errors path="blog.cat" cssClass="err"/>
											<select name="cat.id" class="form-control selectpicker">
												<option value="0">-- Danh mục --</option>
												<c:if test="${not empty listCat}">
													<c:forEach items="${listCat}" var="cat">
														<option <c:if test='${cat.id == blog.cat.id}'>selected="selected"</c:if> value="${cat.id}">${cat.name}</option>
													</c:forEach>
												</c:if>
											</select>
										</div>
										<div class="form-group">
											<label for="file">Hình ảnh</label>
											<form:errors path="blog.picture" cssClass="err"/>
											<input type="file" name="file">							
										</div>
									</div>
									<div class="col-sm-12">
										<div class="form-group">
											<label for="detail">Nội dung bài viết</label>
											<form:errors path="blog.detail" cssClass="err"/>
											<textarea id="detail" name="detail" class="form-control" rows="30">${blog.detail}</textarea>
										</div>
									</div>
								</div>
								<hr>
								<div class="row">
									<div class="col-sm-12">
										<input type="submit" value="Sửa" class="btn btn-success" />
									</div>
								</div>
							</div>
						</div>
			  		</div>
	  			  </div>
	  		  </form>
		  </div>

<script type="text/javascript">
	var ckeditor = CKEDITOR.replace('detail');
	CKFinder.setupCKEditor(ckeditor, '${pageContext.request.contextPath}/resources/admin/ckfinder/');
</script>	
<script type="text/javascript">
	$('.selectpicker').selectpicker();
	document.getElementById("news_management").className = "current";
</script>