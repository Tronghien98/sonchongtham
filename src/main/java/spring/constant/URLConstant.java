package spring.constant;

public class URLConstant {

	public static final String URL_EMPTY = "";

	public static final String URL_INDEX = "";
	public static final String URL_BLOG = "tin-tuc";
	public static final String URL_BLOG_PAGINATION = "tin-tuc/trang-{page}";
	public static final String URL_CAT = "danh-muc/{name}/{id}";
	public static final String URL_CAT_PAGINATION = "danh-muc/{name}/{id}/trang-{page}";
	public static final String URL_CONTACT = "lien-he";
	public static final String URL_DETAIL = "chi-tiet/{name}/{id}";

	public static final String URL_ADMIN = "admin";
	public static final String URL_ADMIN_INDEX = "trang-chu.html";

	public static final String URL_ADMIN_CAT_INDEX = "danh-muc.html";
	public static final String URL_ADMIN_CAT_INDEX_PAGINATION = "danh-muc/trang-{page}.html";
	public static final String URL_ADMIN_CAT_ADD = "danh-muc/them-danh-muc.html";
	public static final String URL_ADMIN_CAT_UPDATE = "danh-muc/sua-danh-muc-{id}.html";
	public static final String URL_ADMIN_CAT_DELETE = "danh-muc/xoa-danh-muc-{id}.html";

	public static final String URL_ADMIN_NEWS_INDEX = "tin-tuc.html";

	public static final String URL_ADMIN_USER_INDEX = "nguoi-dung.html";
	public static final String URL_ADMIN_USER_INDEX_PAGINATION = "nguoi-dung/trang-{page}.html";
	public static final String URL_ADMIN_USER_ADD = "nguoi-dung/them-nguoi-dung.html";
	public static final String URL_ADMIN_USER_UPDATE = "nguoi-dung/sua-nguoi-dung-{id}.html";
	public static final String URL_ADMIN_USER_DELETE = "nguoi-dung/xoa-nguoi-dung-{id}.html";

	public static final String URL_ADMIN_CONTACT_INDEX = "lien-he.html";
	public static final String URL_ADMIN_CONTACT_INDEX_PAGINATION = "lien-he/trang-{page}.html";

	// dùng để redirect
	public static final String URL_ADMIN_CAT_INDEX_2 = "admin/danh-muc.html";
	public static final String URL_ADMIN_CONTACT_INDEX_2 = "admin/lien-he.html";
	public static final String URL_ADMIN_USER_INDEX_2 = "admin/nguoi-dung.html";

	public static final String ERROR = "error";
	public static final String ERROR_404 = "404";
	public static final String ERROR_403 = "403";
	public static final String ERROR_400 = "400";

}
