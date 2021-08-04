package kr.co.inface.hub.controller.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.co.inface.hub.service.matrl.MatrlService;
import kr.co.inface.hub.service.matrl.vo.MatrlVO;

//@Slf4j
@Controller
@RequestMapping({ SiteMatrlController.URL_PREFIX })
public class SiteMatrlController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/site/matrl";

	@Autowired
	private MatrlService matrlService;

	/**
	 * 업체 자재 목록
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlList" })
	public String cmpnyMatrlList(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) {
		List<MatrlVO> rstList = matrlService.getCmpnyMatrlList(loginVo.getCmpnyId());
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/cmpnyMatrlList";
	}

}
