package spring.controller.admin;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.Category;
import spring.service.CategoryService;
import spring.util.PageUtil;
import spring.util.StringUtil;
import spring.validate.CategoryValidate;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminCategoryController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryValidate categoryValidate;

	@GetMapping({ URLConstant.URL_ADMIN_CAT_INDEX, URLConstant.URL_ADMIN_CAT_INDEX_PAGINATION,
			URLConstant.URL_ADMIN_CAT_SEARCH, URLConstant.URL_ADMIN_CAT_SEARCH_PAGINATION })
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
		int totalRow = categoryService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Category> listCat = categoryService.getList(offset, GlobalConstant.TOTAL_ROW);
		if (keywordUrl != null) {
			keyword = StringUtil.dashToSpace(keywordUrl);
		}
		if (keyword != null) {
			if (keyword.equals(GlobalConstant.EMPTY)) {
				ra.addFlashAttribute("error", messageSource.getMessage("searchError", null, Locale.getDefault()));
				return "redirect:/" + URLConstant.URL_ADMIN_CAT_INDEX_2;
			}
			model.addAttribute("keyword", keyword);
			totalRow = categoryService.totalRowByName(keyword);
			totalPage = PageUtil.getTotalPage(totalRow);
			listCat = categoryService.searchByName(keyword, offset, GlobalConstant.TOTAL_ROW);
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listCat", listCat);
		return ViewNameConstant.CAT_INDEX;
	}

	@GetMapping(URLConstant.URL_ADMIN_CAT_ADD)
	public String add() {
		return ViewNameConstant.CAT_ADD;
	}

	@PostMapping(URLConstant.URL_ADMIN_CAT_ADD)
	public String add(@Valid @ModelAttribute("cat") Category category, BindingResult rs, RedirectAttributes ra,
			Model model) {
		categoryValidate.validate(category, rs);
		if (rs.hasErrors()) {
			model.addAttribute("category", category);
			return ViewNameConstant.CAT_ADD;
		}
		if (categoryService.save(category) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("addCatSuccess", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_CAT_INDEX_2;
		}
		model.addAttribute("error", messageSource.getMessage("addCatError", null, Locale.getDefault()));
		return ViewNameConstant.CAT_ADD;
	}

	@GetMapping(URLConstant.URL_ADMIN_CAT_UPDATE)
	public String update(@PathVariable int id, Model model, RedirectAttributes ra) {
		Category category = categoryService.findById(id);
		if (category == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataCat", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_CAT_INDEX_2;
		}
		model.addAttribute("category", category);
		return ViewNameConstant.CAT_UPDATE;
	}

	@PostMapping(URLConstant.URL_ADMIN_CAT_UPDATE)
	public String update(@Valid @ModelAttribute("cat") Category category, BindingResult rs, Model model,
			RedirectAttributes ra) {
		categoryValidate.validate(category, rs);
		if (rs.hasErrors()) {
			model.addAttribute("category", category);
			return ViewNameConstant.CAT_UPDATE;
		}
		if (categoryService.update(category) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("updateCatSuccess", null, Locale.getDefault()));
			ra.addFlashAttribute("categoryUpdate", category.getId()); // để biết được dữ liệu nào vừa được cập nhật
			return "redirect:/" + URLConstant.URL_ADMIN_CAT_INDEX_2;
		}
		model.addAttribute("error", messageSource.getMessage("updateCatError", null, Locale.getDefault()));
		return ViewNameConstant.CAT_UPDATE;
	}

	@GetMapping(URLConstant.URL_ADMIN_CAT_DELETE)
	public String delete(@PathVariable int id, RedirectAttributes ra) {
		Category category = categoryService.findById(id);
		if (category == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataCat", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_CAT_INDEX_2;
		}
		if (categoryService.del(id) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("deleteCatSuccess", null, Locale.getDefault()));
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("deleteCatError", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_CAT_INDEX_2;
	}

}
