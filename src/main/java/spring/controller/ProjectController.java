package spring.controller;

import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.Blog;
import spring.model.Category;
import spring.model.Contact;
import spring.service.BlogService;
import spring.service.CategoryService;
import spring.service.ContactService;
import spring.util.PageUtil;
import spring.util.StringUtil;
import spring.validate.ContactValidate;

@Controller
public class ProjectController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactValidate contactValidate;

	@ModelAttribute
	public void setModelCat(Model model) {
		List<Category> listCat = categoryService.getAll();
		model.addAttribute("listCat", listCat);
	}

	@GetMapping(URLConstant.URL_INDEX)
	public String index(Model model) {
		List<Blog> listBlogNew = blogService.getListNew();
		model.addAttribute("listBlogNew", listBlogNew);
		List<Blog> listBlogByViews = blogService.getListByViews();
		model.addAttribute("listBlogByViews", listBlogByViews);
		return ViewNameConstant.INDEX;
	}

	@GetMapping({ URLConstant.URL_BLOG, URLConstant.URL_BLOG_PAGINATION })
	public String blog(@PathVariable(required = false) Integer page, Model model) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "redirect:/" + URLConstant.URL_BLOG;
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = blogService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Blog> listBlog = blogService.getList(offset, GlobalConstant.TOTAL_ROW);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listBlog", listBlog);
		return ViewNameConstant.BLOG;
	}

	@GetMapping({ URLConstant.URL_CAT, URLConstant.URL_CAT_PAGINATION })
	public String cat(@PathVariable(required = false) Integer page, @PathVariable("id") int catId, Model model) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "redirect:/" + URLConstant.URL_INDEX;
			}
			currentPage = page;
		}
		Category category = categoryService.findById(catId);
		if (category == null) {
			return "redirect:/" + URLConstant.URL_INDEX;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = blogService.totalRowByCat(catId);
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Blog> listBlog = blogService.getListByCat(catId, offset, GlobalConstant.TOTAL_ROW);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listBlog", listBlog);
		model.addAttribute("category", category);
		return ViewNameConstant.CAT;
	}

	@GetMapping(URLConstant.URL_DETAIL)
	public String detail(@PathVariable int id, Model model) {
		Blog blog = blogService.findById(id);
		if (blog == null) {
			return "redirect:/" + URLConstant.URL_INDEX;
		}
		model.addAttribute("blog", blog);
		List<Blog> listBlogRelate = blogService.getListRelate(id, blog.getCat().getId());
		model.addAttribute("listBlogRelate", listBlogRelate);
		List<Blog> listBlogByViews = blogService.getListByViews();
		model.addAttribute("listBlogByViews", listBlogByViews);
		return ViewNameConstant.DETAIL;
	}

	@GetMapping(URLConstant.URL_CONTACT)
	public String contact() {
		return ViewNameConstant.CONTACT;
	}

	@PostMapping(URLConstant.URL_CONTACT)
	public String contact(@Valid @ModelAttribute("ct") Contact contact, BindingResult rs, RedirectAttributes ra,
			Model model) {
		contactValidate.validatePhone(contact, rs);
		if (rs.hasErrors()) {
			model.addAttribute("contact", contact);
			return ViewNameConstant.CONTACT;
		}
		if (contactService.save(contact) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("sendContactSuccess", null, Locale.getDefault()));
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("sendContactError", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_CONTACT;
	}

	@GetMapping({ URLConstant.URL_SEARCH, URLConstant.URL_SEARCH_PAGINATION })
	public String search(@RequestParam(required = false) String keyword,
			@PathVariable(required = false) String keywordUrl, @PathVariable(required = false) Integer page,
			Model model) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "redirect:/" + URLConstant.URL_BLOG;
			}
			currentPage = page;
		}
		if (keywordUrl != null) {
			keyword = StringUtil.dashToSpace(keywordUrl);
		}
		if (keyword.equals(GlobalConstant.EMPTY)) {
			return "redirect:/" + URLConstant.URL_BLOG;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = blogService.totalRowByTitle(keyword);
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Blog> listBlog = blogService.searchByTitle(keyword, offset, GlobalConstant.TOTAL_ROW);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listBlog", listBlog);
		model.addAttribute("keyword", keyword);
		return ViewNameConstant.SEARCH;
	}

}
