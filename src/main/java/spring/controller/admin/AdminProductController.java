package spring.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.constant.URLConstant;
import spring.constant.ViewNameConstant;

@Controller
@RequestMapping(URLConstant.URL_ADMIN)
public class AdminProductController {

	@GetMapping(URLConstant.URL_ADMIN_NEWS_INDEX)
	public String index() {
		return ViewNameConstant.PRODUCT_INDEX;
	}

}
