<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>   
		  <div class="col-md-10">
			  <form id="formAddUser1" action="${urlAdminUser}/them-nguoi-dung.html" method="post" enctype="multipart/form-data">
	  			  <div class="row">
	  				<div class="col-md-12 panel-info">
			  			<div class="content-box-header panel-heading">
		  					<div class="panel-title ">Thêm người dùng</div>
			  			</div>
			  			<div class="content-box-large box-with-header">
				  			<div>
								<div class="row mb-10"></div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="username">Tên đăng nhập (Username)</label>
											<form:errors path="userError.username" cssClass="err"/>
											<input type="text" value="${user.username}" name="username" class="form-control" placeholder="Nhập tên đăng nhập">										
										</div>
										
										<div class="form-group">
											<label for="password">Mật khẩu (Password)</label>
											<form:errors path="userError.password" cssClass="err"/>
											<input type="password" value="" name="password" class="form-control" placeholder="Nhập mật khẩu">										
										</div>

										<div class="form-group">
											<label for="confirmPassword">Xác nhận mật khẩu</label>
											<span style="color:red" >${confirmPasswordError}</span>
											<input type="password" value="" name="confirmPassword" class="form-control" placeholder="Xác nhận mật khẩu">										
										</div>

										<div class="form-group">
											<label for="fullname">Tên người dùng</label>
											<form:errors path="userError.fullname" cssClass="err"/>
											<input type="text" value="${user.fullname}" name="fullname" class="form-control" placeholder="Nhập họ và tên">										
										</div>
										
										<div class="form-group">
											<label for="email">Email</label>
											<form:errors path="userError.email" cssClass="err"/>
											<input type="text" value="${user.email}" name="email" class="form-control" placeholder="Nhập email">										
										</div>

										<div class="form-group">
											<label for="phone">Số điện thoại</label>
											<form:errors path="userError.phone" cssClass="err"/>
											<input type="text" value="${user.phone}" name="phone" class="form-control" placeholder="Nhập số điện thoại">										
										</div>
										
										<div class="form-group">
											<label>Ảnh đại diện</label>
											<form:errors path="userError.avatar" cssClass="err"/>
											<input type="file" name="picture" class="btn btn-default" id="exampleInputFile1" >
											<p class="help-block"><em>Định dạng: jpg, png, jpeg, gif</em></p>
										</div>
									</div>
									<div class="col-sm-6"></div>
								</div>
								<hr>
								<div class="row">
									<div class="col-sm-12">
										<input type="submit" value="Thêm" class="btn btn-success" />
										<input type="reset" value="Nhập lại" class="btn btn-default" />
									</div>
								</div>
							</div>
						</div>
			  		</div>
	  			  </div>
	  		  </form>
		  </div>

<script type="text/javascript">
	document.getElementById("user_management").className = "current";
</script>	