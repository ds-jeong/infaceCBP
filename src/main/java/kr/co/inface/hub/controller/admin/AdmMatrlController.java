package kr.co.inface.hub.controller.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.inface.hub.service.matrl.MatrlService;
import kr.co.inface.hub.service.matrl.vo.MatrlVO;

//@Slf4j
@Controller
@RequestMapping(value = "/admin/matrl")
public class AdmMatrlController {

	@Autowired
	private MatrlService matrlService;

	@GetMapping({ "/matrlItemList" })
	public String getMatrlItemList(Principal principal, Model model) {

		List<MatrlVO> rstList = matrlService.getMatrlItemList(null);
		model.addAttribute("rstList", rstList);

		return "/admin/matrl/matrlItemList";
	}
}
