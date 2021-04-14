package kr.pe.inface.hub.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class MainController {

//	@Autowired
//	private MainService mainService;

	@GetMapping({ "/", "/main" })
	public String main(Principal principal, Model model) {
		if (principal != null) {
			log.debug(principal.getName());
		}

		return "main";
	}
}