package spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("demo")
	@ResponseBody
	public String demo() {
		return bCryptPasswordEncoder.encode("Admin@123");
	}

}
