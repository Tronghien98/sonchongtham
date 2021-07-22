package spring.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {

	// rút gọn chuỗi
	public static String setStringCompact(String str, int n) {
		StringBuilder sb = new StringBuilder(str);
		if (sb.length() > n) {
			sb.delete(n, sb.length()).append("...");
		}
		return sb.toString();
	}

	// xoá khoảng trắng trong chuỗi
	public static String delSpace(String str) {
		return str.trim().replaceAll("\\s+", " ");
	}

	// format phone number
	public static String beautifulPhone(String phone) {
		return phone.replaceFirst("(\\d{4})(\\d{3})(\\d+)", "$1.$2.$3");
	}

	// thay thế ký tự khoảng trắng thành ký tự "-"
	public static String spaceToDash(String str) {
		return str.replaceAll("\\s", "-");
	}

	// thay thế ký tự "-" thành ký tự khoảng trắng
	public static String dashToSpace(String str) {
		return str.replaceAll("-", " ");
	}

	public static String makeSlug(String title) {
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		// Replace 'đ' to 'd'
		slug = slug.replaceAll("đ", "d");
		// Delete special characters
		slug = slug.replaceAll("([^0-9a-z-\\s])", "");
		// Replace 'space' to '-'
		slug = slug.replaceAll("[\\s]", "-");
		// Converts multiple characters '-' consecutively to 1 character '-'
		slug = slug.replaceAll("(-+)", "-");
		// Delete characters '-' at the begin and the end
		slug = slug.replaceAll("^-+", "");
		slug = slug.replaceAll("-+$", "");
		return slug;
	}

}
