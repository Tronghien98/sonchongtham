package spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminController {

	@GetMapping(URLConstant.URL_ADMIN_INDEX)
	public String index(HttpSession session) {
		session.setAttribute("username", "admin");
		return ViewNameConstant.ADMIN_INDEX;
	}
	
}
