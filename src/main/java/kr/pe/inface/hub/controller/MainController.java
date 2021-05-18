package kr.pe.inface.hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.inface.hub.service.cmpny.CmpnyService;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;

//@Slf4j
@Controller
@RequestMapping(value = "/")
public class MainController {

	@Autowired
	private CmpnyService cmpnyService;

	@GetMapping({ "/", "/main" })
	public String main(Model model) {
		List<CmpnyUserVO> cuList = cmpnyService.getCmpnyUserList();
		model.addAttribute("cuList", cuList);

		return "main";
	}

	@GetMapping("/auth/login")
	public String login() {
		return "/auth/login";
	}

}