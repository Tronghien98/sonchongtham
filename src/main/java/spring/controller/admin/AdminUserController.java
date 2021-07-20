package spring.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import spring.util.PageUtil;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminUserController {

	@Autowired
	private UserService userService;

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
	public String add(@ModelAttribute User user, @RequestParam("picture") MultipartFile multipartFile, Model model,
			RedirectAttributes ra) {
		user.setAvatar(multipartFile.getOriginalFilename());
		System.out.println(user);
		return ViewNameConstant.USER_ADD;
	}

}
