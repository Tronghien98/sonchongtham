package spring.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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
import spring.model.User;
import spring.service.UserService;
import spring.util.FileUtil;
import spring.util.PageUtil;
import spring.validate.UserValidator;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminUserController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping({ URLConstant.URL_ADMIN_USER_INDEX, URLConstant.URL_ADMIN_USER_INDEX_PAGINATION })
	public String index(@PathVariable(required = false) Integer page, Model model) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "redirect:/" + URLConstant.URL_ADMIN_USER_INDEX_2;
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = userService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<User> listUser = userService.getList(offset, GlobalConstant.TOTAL_ROW);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listUser", listUser);
		return ViewNameConstant.USER_INDEX;
	}

	@GetMapping(URLConstant.URL_ADMIN_USER_ADD)
	public String add() {
		return ViewNameConstant.USER_ADD;
	}

	@PostMapping(URLConstant.URL_ADMIN_USER_ADD)
	public String add(@Valid @ModelAttribute("userError") User user, BindingResult rs,
			@RequestParam("picture") MultipartFile multipartFile, @RequestParam String confirmPassword, Model model,
			RedirectAttributes ra, HttpServletRequest request) {
		userValidator.validateUsername(user, rs, null);
		userValidator.validatePassword(user, rs, null, confirmPassword, model);
		userValidator.validatePhone(user, rs);
		userValidator.validatePicture(multipartFile.getOriginalFilename(), rs);
		if (rs.hasErrors()) {
			model.addAttribute("user", user);
			return ViewNameConstant.USER_ADD;
		}
		String fileName = FileUtil.uploadFile(multipartFile, request);
		user.setAvatar(fileName);
		if (userService.save(user) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("addUserSuccess", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_USER_INDEX_2;
		}
		model.addAttribute("error", messageSource.getMessage("addUserError", null, Locale.getDefault()));
		return ViewNameConstant.USER_ADD;
	}

	@GetMapping(URLConstant.URL_ADMIN_USER_UPDATE)
	public String update(@PathVariable int id, Model model, RedirectAttributes ra) {
		User user = userService.findById(id);
		if (user == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataUser", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_USER_INDEX_2;
		}
		model.addAttribute("user", user);
		return ViewNameConstant.USER_UPDATE;
	}

	@PostMapping(URLConstant.URL_ADMIN_USER_UPDATE)
	public String update(@Valid @ModelAttribute("userError") User user, BindingResult rs,
			@RequestParam("picture") MultipartFile multipartFile,
			@RequestParam(required = false) String confirmPassword, Model model, RedirectAttributes ra,
			HttpServletRequest request) {
		User oldUser = userService.findById(user.getId());
		userValidator.validateUsername(user, rs, oldUser);
		userValidator.validatePhone(user, rs);
		userValidator.validatePicture(multipartFile.getOriginalFilename(), rs);
		if (!user.getPassword().equals(GlobalConstant.EMPTY)) {
			userValidator.validatePassword(user, rs, oldUser, confirmPassword, model);
		} else {
			user.setPassword(oldUser.getPassword());
		}
		if (rs.hasErrors()) {
			model.addAttribute("user", user);
			return ViewNameConstant.USER_UPDATE;
		}
		String fileName = FileUtil.uploadFile(multipartFile, request);
		if (fileName.equals(GlobalConstant.EMPTY)) {
			user.setAvatar(oldUser.getAvatar());
		} else {
			FileUtil.delFile(oldUser.getAvatar(), request);
		}
		if (userService.update(user) > 0) {
			ra.addFlashAttribute("success", messageSource.getMessage("updateUserSuccess", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_USER_INDEX_2;
		}
		model.addAttribute("error", messageSource.getMessage("updateUserError", null, Locale.getDefault()));
		return ViewNameConstant.USER_UPDATE;
	}

	@GetMapping(URLConstant.URL_ADMIN_USER_DELETE)
	public String delete(@PathVariable int id, Model model, RedirectAttributes ra, HttpServletRequest request) {
		User user = userService.findById(id);
		if (user == null) {
			ra.addFlashAttribute("msg", messageSource.getMessage("noDataUser", null, Locale.getDefault()));
			return "redirect:/" + URLConstant.URL_ADMIN_USER_INDEX_2;
		}
		if (userService.del(id) > 0) {
			FileUtil.delFile(user.getAvatar(), request);
			ra.addFlashAttribute("success", messageSource.getMessage("deleteUserSuccess", null, Locale.getDefault()));
		} else {
			ra.addFlashAttribute("error", messageSource.getMessage("deleteUserError", null, Locale.getDefault()));
		}
		return "redirect:/" + URLConstant.URL_ADMIN_USER_INDEX_2;
	}

}
