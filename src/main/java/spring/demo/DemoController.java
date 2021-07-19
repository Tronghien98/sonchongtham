package spring.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.util.StringUtil;

@Controller
public class DemoController {
	
	@GetMapping("demo")
	public String demo(Model model) {
		model.addAttribute("str", StringUtil.delSpace(""));
		return "demo/demo";
	}
	
}
