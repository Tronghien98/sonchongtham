<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
<div class="col-md-10">
  			<div class="content-box-large">
  				<div class="row">
	  				<div class="panel-heading">
	  					<div class="panel-title ">QUẢN LÝ LIÊN HỆ</div>
		  			</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-8"></div>
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
							<c:when test="${not empty listContact}">
			  					<table class="table table-striped table-bordered" id="example">
									<thead>
										<tr>
											<th width="4%">ID</th>
											<th>Tên</th>
											<th width="17%">Email</th>
											<th width="9%">Số điện thoại</th>
											<th>Lời nhắn</th>
											<th width="10%">Trạng thái</th>
											<th width="8%">Ngày gửi</th>
											<th width="8%">Chức năng</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listContact}" var="contact">
											<tr class="odd gradeX">
												<td>${contact.id}</td>
												<td>${contact.name}</td>
												<td>${contact.email}</td>
												<td align="center">${stringUtil.beautifulPhone(contact.phone)}</td>
												<td>${contact.content}</td>
												<td>
													<c:if test="${contact.status == 0}">
														<span>Chưa xử lý</span>
														<a href="javascript:void(0)" title="Cập nhật trạng thái" style="margin-left: 6px"><img alt="Chưa xử lý" src="${adminContextPath}/images/deactive.gif" /></a>
													</c:if>
													<c:if test="${contact.status == 1}">
														<span>Đã liên hệ</span>
														<a href="javascript:void(0)" title="Cập nhật trạng thái" style="margin-left: 6px"><img alt="Đã liên hệ" src="${adminContextPath}/images/active.gif" /></a>
													</c:if>
												</td>
												<td align="center">${dateUtil.formatDate(contact.createAt)}</td>
												<td class="center text-center">
				                                    <a href="${urlAdminContact}/xoa-lien-he-${contact.id}.html" onclick="return confirm('Bạn có chắc muốn xoá liên hệ của \'${contact.name}\' không?')" title="" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
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
									  	<a href="${urlAdminContact}/trang-${pagePrevious}.html" aria-label="Previous" >
									  		<span aria-hidden="true">«</span>
									  	</a>
									  </li>
																			      
								      <c:choose>
									      <c:when test="${totalPage > 5}">
									      	  <c:if test="${currentPage > 3 and currentPage < (totalPage - 2)}">
									      	  	  <li><a href="${urlAdminContact}.html">Đầu</a></li>
											      <c:forEach begin="${currentPage - 2}" end="${currentPage + 2}" var="page">
											      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
											      	  	  <a href="${urlAdminContact}/trang-${page}.html">${page}</a>
											      	  </li>
											      </c:forEach>
											      <li><a href="${urlAdminContact}/trang-${totalPage}.html">Cuối</a></li>
										      </c:if>
									      	  <c:if test="${currentPage <= 3}">
											      <c:forEach begin="1" end="5" var="page">
											      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
											      	  	  <a href="${urlAdminContact}/trang-${page}.html">${page}</a>
											      	  </li>
											      </c:forEach>
											      <li><a href="${urlAdminContact}/trang-${totalPage}.html">Cuối</a></li>
										      </c:if>
									      	  <c:if test="${currentPage >= (totalPage - 2)}">
									      	  	  <li><a href="${urlAdminContact}.html">Đầu</a></li>
											      <c:forEach begin="${totalPage - 4}" end="${totalPage}" var="page">
											      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
											      	  	  <a href="${urlAdminContact}/trang-${page}.html">${page}</a>
											      	  </li>
											      </c:forEach>
										      </c:if>
									      </c:when>
									      <c:otherwise>
									      	  <c:forEach begin="1" end="${totalPage}" var="page">
										      	  <li <c:if test='${page == currentPage}'> class="active" </c:if> >
										      	  	  <a href="${urlAdminContact}/trang-${page}.html">${page}</a>
										      	  </li>
										      </c:forEach>
									      </c:otherwise>
								      </c:choose>
								      
								      <c:set value="${currentPage + 1}" var="pageNext"></c:set>
								      <c:if test="${currentPage == totalPage}">
								      	<c:set value="${currentPage}" var="pageNext"></c:set>
								      </c:if>
									  <li <c:if test='${currentPage == totalPage}'>class="disabled"</c:if>>
									  	<a href="${urlAdminContact}/trang-${pageNext}.html" aria-label="Next">
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
	document.getElementById("contact_management").className = "current";
</script>