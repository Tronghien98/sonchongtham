$(document).ready(function() {
	
	$("#formCat").validate({
		rules: {
			name: {
				required: true,
			}
		},
		messages: {
			name: {
				required: "Vui lòng nhập tên danh mục",
			}
		}
	});
	
	$("#formContact").validate({
		rules: {
			name: {
				required: true,
			},
			email: {
				required: true,
				email: true,
			},
			phone: {
				required: true,
			},
			content: {
				required: true,
			}
		},
		messages: {
			name: {
				required: "Vui lòng nhập tên của bạn",
			},
			email: {
				required: "Vui lòng nhập email",
				email: "Email không đúng định dạng",
			},
			phone: {
				required: "Vui lòng nhập số điện thoại",
			},
			content: {
				required: "Vui lòng để lại lời nhắn",
			}
		}
	});
	
	$("#formBlog").validate({
		rules: {
			title: {
				required: true,
			},
			detail: {
				required: true,
			}
		},
		messages: {
			title: {
				required: "Vui lòng nhập tiêu đề",
			},
			detail: {
				required: "Vui lòng nhập nội dung bài viết",
			}
		}
	});
	
	$("#formLogin").validate({
		rules: {
			username: {
				required: true,
			},
			password: {
				required: true,
			}
		},
		messages: {
			username: {
				required: "Vui lòng nhập tên đăng nhập!",
			},
			password: {
				required: "Vui lòng nhập mật khẩu!",
			}
		}
	});

	$("#formProfile").validate({
		rules: {
			confirmPassword: {
				equalTo: "#password",
			},
			fullname: {
				required: true,
			},
			email: {
				required: true,
				email: true,
			},
			phone: {
				required: true,
			}
		},
		messages: {
			confirmPassword: {
				equalTo: "Xác nhận mật khẩu không chính xác, vui lòng kiểm tra lại",
			},
			fullname: {
				required: "Vui lòng nhập tên người dùng (họ và tên)",
			},
			email: {
				required: "Vui lòng nhập email",
				email: "Email không đúng định dạng",
			},
			phone: {
				required: "Vui lòng nhập số điện thoại",
			}
		}
	});
});