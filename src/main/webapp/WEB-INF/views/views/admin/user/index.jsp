<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
<div class="col-md-10">
  			<div class="content-box-large">
  				<div class="row">
	  				<div class="panel-heading">
	  					<div class="panel-title ">QUẢN LÝ NGƯỜI DÙNG</div>
		  			</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-8">
						<a href="${urlAdminUser}/them-nguoi-dung.html" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>
					</div>
                	<div class="col-md-4">
                 	 <div class="input-group form">
                       <input type="text" class="form-control" placeholder="Tìm kiếm...">
                       <span class="input-group-btn">
                         <button class="btn btn-primary" type="button">Tìm kiếm</button>
                       </span>
                  	 </div>
                  	</div>
				</div>

				<div class="row">
	  				<div class="panel-body">
		  				<c:choose>
							<c:when test="${not empty listUser}">
			  					<table class="table table-striped table-bordered" id="example">
									<thead>
										<tr>
											<th width="4%">ID</th>
											<th>Username</th>
											<th>Tên</th>
											<th>Email</th>
											<th width="9%">Số điện thoại</th>
											<th width="10%">Vai trò</th>
											<th width="11%">Trạng thái</th>
											<th width="15%">Chức năng</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listUser}" var="user">
											<tr class="odd gradeX">
												<td>${user.id}</td>
												<td>${user.username}</td>
												<td>${user.fullname}</td>
												<td>${user.email}</td>
												<td align="center">${stringUtil.beautifulPhone(user.phone)}</td>
												<td>${user.role.name}</td>
												<td>
													<c:if test="${user.enabled == 1}">
														Đã kích hoạt
													</c:if>
													<c:if test="${user.enabled == 0}">
														Vô hiệu hoá
													</c:if>
												</td>
												<td class="center text-center">
													<a href="${urlAdminUser}/sua-danh-muc-${user.id}.html" title="" class="btn btn-primary"><span class="glyphicon glyphicon-pencil "></span> Sửa</a>
				                                    <a href="${urlAdminUser}/xoa-danh-muc-${user.id}.html" onclick="return confirm('Bạn có chắc muốn xoá người dùng \'${user.username}\' không?')" title="" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
								<!-- pagination -->
								<nav class="text-center" aria-label="...">
								   <ul class="pagination">
								   	  <c:set value="${currentPage - 1}" var="pagePrevious"></c:set>
								   	  <c:if test="${currentPage == 1}">
								   	  	<c:set value="${currentPage}" var="pagePrevious"></c:set>
								      </c:if>
									  <li <c:if test='${currentPage == 1}'>class="disabled"</c:if>>
									  	<a href="${urlAdminUser}/trang-${pagePrevious}.html" aria-label="Previous" >
									  		<span aria-hidden="true">«</span>
									  	</a>
									  </li>
																			      
								      <c:choose>
									      <c:when test="${totalPage > 5}">
									      	  <c:if test="${currentPage > 3 and currentPage < (totalPage - 2)}">
									      	  	  <li><a href="${urlAdminUser}.html">Đầu</a></li>
											      <c:forEach begin="${currentPage - 2}" end="${currentPage + 2}" var="page">
											      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
											      	  	  <a href="${urlAdminUser}/trang-${page}.html">${page}</a>
											      	  </li>
											      </c:forEach>
											      <li><a href="${urlAdminUser}/trang-${totalPage}.html">Cuối</a></li>
										      </c:if>
									      	  <c:if test="${currentPage <= 3}">
											      <c:forEach begin="1" end="5" var="page">
											      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
											      	  	  <a href="${urlAdminUser}/trang-${page}.html">${page}</a>
											      	  </li>
											      </c:forEach>
											      <li><a href="${urlAdminUser}/trang-${totalPage}.html">Cuối</a></li>
										      </c:if>
									      	  <c:if test="${currentPage >= (totalPage - 2)}">
									      	  	  <li><a href="${urlAdminUser}.html">Đầu</a></li>
											      <c:forEach begin="${totalPage - 4}" end="${totalPage}" var="page">
											      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
											      	  	  <a href="${urlAdminUser}/trang-${page}.html">${page}</a>
											      	  </li>
											      </c:forEach>
										      </c:if>
									      </c:when>
									      <c:otherwise>
									      	  <c:forEach begin="1" end="${totalPage}" var="page">
										      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
										      	  	  <a href="${urlAdminUser}/trang-${page}.html">${page}</a>
										      	  </li>
										      </c:forEach>
									      </c:otherwise>
								      </c:choose>
								      
								      <c:set value="${currentPage + 1}" var="pageNext"></c:set>
								      <c:if test="${currentPage == totalPage}">
								      	<c:set value="${currentPage}" var="pageNext"></c:set>
								      </c:if>
									  <li <c:if test='${currentPage == totalPage}'>class="disabled"</c:if>>
									  	<a href="${urlAdminUser}/trang-${pageNext}.html" aria-label="Next">
									  		<span aria-hidden="true">»</span>
									  	</a>
									  </li>
								   </ul>
								</nav>
								<!-- end pagination -->
							</c:when>
							<c:otherwise>
								<div class="alert alert-info" role="alert">
								  	Không có dữ liệu.
								</div>
							</c:otherwise>
						</c:choose>
	  				</div>
  				</div>
  			</div>
		  </div>
		  
<script type="text/javascript">
	document.getElementById("user_management").className = "current";
</script>