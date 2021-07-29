<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>   
		  <div class="col-md-10">
  			  <div class="row">
  				<div class="col-md-12 panel-info">
		  			<div class="content-box-header panel-heading">
	  					<div class="panel-title ">Chi tiết bài viết</div>
		  			</div>
		  			<div class="content-box-large box-with-header">
			  			<div>
							<div class="row mb-10"></div>
							<div class="row">
								<div class="col-sm-6">
									<div>
										<h3 style="color: red">Tiêu đề</h3>
										<p style="font-size: 16px">${blog.title}</p>
									</div>
									<hr>
									<div>
										<h3 style="color: red">Nội dung</h3>
										<div style="font-size: 14px">${blog.detail}</div>
									</div>
								</div>
								<div class="col-sm-6"></div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-12">
									<input type="button" onclick="back()" value="Quay lại" class="btn btn-success" />
								</div>
							</div>
						</div>
					</div>
		  		</div>
  			  </div>
		  </div>

<script type="text/javascript">
	document.getElementById("news_management").className = "current";
	
	function back() {
		history.back();
	}
</script>