package spring.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.Blog;
import spring.model.User;
import spring.service.BlogService;
import spring.service.CategoryService;
import spring.service.UserService;
import spring.util.FileUtil;
import spring.util.PageUtil;
import spring.util.StringUtil;
import spring.validate.BlogValidate;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminProductController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService catService;

	@Autowired
	private BlogValidate blogValidate;

	@Autowired
	private UserService userService;

	@GetMapping({ URLConstant.URL_ADMIN_NEWS_INDEX, URLConstant.URL_ADMIN_NEWS_INDEX_PAGINATION,
			URLConstant.URL_ADMIN_NEWS_SEARCH, URLConstant.URL_ADMIN_NEWS_SEARCH_PAGINATION })
	public String index(@PathVariable(required = false) Integer page, @RequestParam(required = false) String keyword,
			@PathVariable(required = false) String keywordUrl, Model model, RedirectAttributes ra) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "redirect:/" + URLConstant.URL_ADMIN_CAT_INDEX_2;
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = blogService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Blog> listBlog = blogService.getList(offset, GlobalConstant.TOTAL_ROW);
		if (keywordUrl != null) {
			keyword = StringUtil.dashToSpace(keywordUrl);
		}
		if (keyword != null) {
			if (keyword.equals(GlobalConstant.EMPTY)) {
				ra.addFlashAttribute("error", messageSource.getMessage("searchError", null, Locale.getDefault()));
				return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_2;
			}
			model.addAttribute("keyword", keyword);
			totalRow = blogService.totalRowByTitle(keyword);
			totalPage = PageUtil.getTotalPage(totalRow);
			listBlog = blogService.searchByTitle(keyword, offset, GlobalConstant.TOTAL_ROW);
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listBlog", listBlog);

		return ViewNameConstant.PRODUCT_INDEX;
	}

	@GetMapping(URLConstant.URL_ADMIN_NEWS_DETAIL)
	public String detail(@PathVariable int id, Model model, RedirectAttributes ra) {
		Blog blog = blogService.findById(id);
		if (blog == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("NoDataBlog", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_2;
		}
		model.addAttribute("blog", blog);
		return ViewNameConstant.PRODUCT_DETAIL;
	}

	@GetMapping(URLConstant.URL_ADMIN_NEWS_ADD)
	public String add(Model model) {
		model.addAttribute("listCat", catService.getAll());
		return ViewNameConstant.PRODUCT_ADD;
	}

	@PostMapping(URLConstant.URL_ADMIN_NEWS_ADD)
	public String add(@Valid @ModelAttribute("blog") Blog blog, BindingResult br,
			@RequestParam("file") MultipartFile file, RedirectAttributes ra, Model model, HttpServletRequest request,
			HttpSession session) {
		blogValidate.validatePicture(file.getOriginalFilename(), br);
		blogValidate.validateCat(blog.getCat().getId(), br);
		if (br.hasErrors()) {
			model.addAttribute("blog", blog);
			model.addAttribute("listCat", catService.getAll());
			return ViewNameConstant.PRODUCT_ADD;
		}
		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		String fileName = FileUtil.uploadFile(file, request, GlobalConstant.PICTURE);

		blog.setCat(catService.findById(blog.getCat().getId()));
		blog.setPicture(fileName);
		blog.setUser(user);
		if (blogService.save(blog) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("addBlogSuccess", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_2;
		}
		model.addAttribute("error", messageSource.getMessage("addBlogError", null, Locale.getDefault()));
		return ViewNameConstant.PRODUCT_ADD;
	}

	@GetMapping(URLConstant.URL_ADMIN_NEWS_UPDATE)
	public String update(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		if (blogService.findById(id) == null) {
			ra.addFlashAttribute("error", messageSource.getMessage("NoDataBlog", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_2;
		}
		model.addAttribute("blog", blogService.findById(id));
		model.addAttribute("listCat", catService.getAll());
		return ViewNameConstant.PRODUCT_UPDATE;
	}

	@PostMapping(URLConstant.URL_ADMIN_NEWS_UPDATE)
	public String update(@Valid @ModelAttribute("blog") Blog blog, BindingResult br,
			@RequestParam("file") MultipartFile file, RedirectAttributes ra, Model model, HttpServletRequest request,
			HttpSession session) {
		Blog oldBlog = blogService.findById(blog.getId());
		if (!GlobalConstant.EMPTY.equals(file.getOriginalFilename())) {
			blogValidate.validatePicture(file.getOriginalFilename(), br);
		}
		blogValidate.validateCat(blog.getCat().getId(), br);
		if (br.hasErrors()) {
			model.addAttribute("blog", blog);
			model.addAttribute("listCat", catService.getAll());
			return ViewNameConstant.PRODUCT_UPDATE;
		}
		if (GlobalConstant.EMPTY.equals(file.getOriginalFilename())) {
			blog.setPicture(oldBlog.getPicture());
		} else {
			String fileName = FileUtil.uploadFile(file, request, GlobalConstant.PICTURE);
			blog.setPicture(fileName);
		}
		blog.setCat(catService.findById(blog.getCat().getId()));
		if (blogService.update(blog) > 0) {
			if (!GlobalConstant.EMPTY.equals(file.getOriginalFilename())) {
				FileUtil.delFile(oldBlog.getPicture(), request, GlobalConstant.PICTURE);
			}
			ra.addFlashAttribute("success", messageSource.getMessage("UpdateBlogSuccess", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_2;
		}
		model.addAttribute("error", messageSource.getMessage("UpdateBlogError", null, Locale.getDefault()));
		return ViewNameConstant.PRODUCT_ADD;
	}

	@GetMapping(URLConstant.URL_ADMIN_NEWS_DELETE)
	public String del(@PathVariable("id") Integer id, RedirectAttributes ra, HttpServletRequest request) {
		Blog blog = blogService.findById(id);
		if (blog == null) {
			ra.addFlashAttribute("error", messageSource.getMessage("NoDataBlog", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_2;
		}

		if (blogService.del(id) > 0) {
			FileUtil.delFile(blog.getPicture(), request, GlobalConstant.PICTURE);
			ra.addFlashAttribute("success", messageSource.getMessage("DeleteBlogSuccess", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_2;
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("deletePictureError", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_NEWS_INDEX_2;
	}
}