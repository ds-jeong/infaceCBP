package kr.pe.inface.hub.controller.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.matrl.MatrlClmService;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmVO;

//@Slf4j
@Controller
@RequestMapping({ SiteMatrlClmController.URL_PREFIX })
public class SiteMatrlClmController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/site/matrlclm";

	@Autowired
	private MatrlClmService matrlClmService;

	/**
	 * 자재청구 목록 조회
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlClmList" })
	public String matrlClmList(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) {
		String clmStatCd = null;
		String clmDt = null;
		List<MatrlClmVO> rstList = matrlClmService.getMatrlClmList(loginVo.getCmpnyId(), null, clmStatCd, clmDt);
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/matrlClmList";
	}

	/**
	 * 자재청구 상세 조회
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlClmDtl" })
	public String matrlClmDtl(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) {
		return URL_PREFIX + "/matrlClmDtl";
	}

	/**
	 * 자재청구 등록 페이지
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlClmInsPage" })
	public String matrlClmInsPage(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) {
		return URL_PREFIX + "/matrlClmUpd";
	}

}
