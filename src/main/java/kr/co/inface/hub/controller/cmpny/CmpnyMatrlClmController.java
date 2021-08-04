package kr.co.inface.hub.controller.cmpny;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.co.inface.hub.service.matrl.MatrlClmService;
import kr.co.inface.hub.service.matrl.vo.MatrlClmVO;

//@Slf4j
@Controller
@RequestMapping({ CmpnyMatrlClmController.URL_PREFIX })
public class CmpnyMatrlClmController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/cmpny/matrlclm";

	@Autowired
	private MatrlClmService matrlClmService;

	/**
	 * 자재청구 목록 조회
	 *
	 * @param loginVo
	 * @param workSiteId
	 * @param clmStatCd
	 * @param clmDt
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlClmList" })
	public String matrlClmList(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam(required = false) String workSiteId,
			@RequestParam(required = false) String clmStatCd,
			@RequestParam(required = false) String clmDt,
			Model model) throws Exception {

		List<MatrlClmVO> rstList = matrlClmService.getMatrlClmList(loginVo, workSiteId, clmStatCd, clmDt);
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/matrlClmList";
	}

}
