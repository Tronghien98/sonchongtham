<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-md-10">
	<div class="row">
		<div class="col-md-12 panel-warning">
			<div class="content-box-header panel-heading">
				<div class="panel-title ">TRANG CHỦ</div>
			</div>
			<div class="content-box-large box-with-header">
				<div class="row">
					<div class="col-md-3 col-sm-3 col-xs-3">
						<div class="panel panel-back noti-box">
							<span class="icon-box bg-color-green set-icon"> <span
								class="glyphicon glyphicon-th-list"></span>
							</span>
							<div class="text-box">
								<p class="main-text">
									<a class="fs-14" href="${urlAdminCat}.html" title="Quản lý danh mục">Danh mục</a>
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3 col-xs-3">
						<div class="panel panel-back noti-box">
							<span class="icon-box bg-color-blue set-icon"> <span
								class="glyphicon glyphicon-globe"></span>
							</span>
							<div class="text-box">
								<p class="main-text">
									<a class="fs-14" href="${urlAdminNews}.html" title="Quản lý tin tức">Tin tức</a>
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3 col-xs-3">
						<div class="panel panel-back noti-box">
							<span class="icon-box bg-color-brown set-icon"> <span
								class="glyphicon glyphicon-user"></span>
							</span>
							<div class="text-box">
								<p class="main-text">
									<a class="fs-14" href="${urlAdminUser}.html" title="Quản lý người dùng">Người dùng</a>
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3 col-xs-3">
						<div class="panel panel-back noti-box">
							<span class="icon-box bg-color-red set-icon"> <span
								class="glyphicon glyphicon-envelope"></span>
							</span>
							<div class="text-box">
								<p class="main-text">
									<a class="fs-14" href="${urlAdminContact}.html" title="Quản lý liên hệ">Liên hệ</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<div class="content-box-large">
				<div class="panel-heading">
					<div class="panel-title">Chào mừng đến với trang quản trị</div>
				</div>
				<div class="panel-body">
					Thông tin về trang web
				</div>
			</div>
		</div>

		<div class="col-md-6">
			<div class="row">
				<div class="col-md-12">
					<div class="content-box-header">
						<div class="panel-title">Hướng dẫn sử dụng</div>
					</div>
					<div class="content-box-large box-with-header">
						Hướng dẫn sử dụng trang web
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="content-box-header">
						<div class="panel-title">Nội quy</div>
					</div>
					<div class="content-box-large box-with-header">
						Nội quy trang web (có thể có hoặc không)
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	document.getElementById("admin-index").className = "current";
</script>