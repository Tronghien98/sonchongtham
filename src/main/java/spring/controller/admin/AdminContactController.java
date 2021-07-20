package spring.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.constant.GlobalConstant;
import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;
import spring.model.Contact;
import spring.service.ContactService;
import spring.util.PageUtil;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping({ URLConstant.URL_ADMIN_CONTACT_INDEX, URLConstant.URL_ADMIN_CONTACT_INDEX_PAGINATION })
	public String index(@PathVariable(required = false) Integer page, Model model) {
		int currentPage = GlobalConstant.DEFAULT_PAGE;
		if (page != null) {
			if (page < GlobalConstant.DEFAULT_PAGE) {
				return "redirect:/" + URLConstant.URL_ADMIN_CONTACT_INDEX_2;
			}
			currentPage = page;
		}
		int offset = PageUtil.getOffset(currentPage);
		int totalRow = contactService.totalRow();
		int totalPage = PageUtil.getTotalPage(totalRow);
		List<Contact> listContact = contactService.getList(offset, GlobalConstant.TOTAL_ROW);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("listContact", listContact);
		return ViewNameConstant.CONTACT_INDEX;
	}

}
