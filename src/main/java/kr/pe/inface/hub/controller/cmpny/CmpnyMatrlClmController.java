package kr.pe.inface.hub.controller.cmpny;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.matrl.MatrlService;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmVO;

//@Slf4j
@Controller
@RequestMapping({ CmpnyMatrlClmController.URL_PREFIX })
public class CmpnyMatrlClmController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/cmpny/matrlclm";

	@Autowired
	private MatrlService matrlService;

	/**
	 * 자재카테고리,품목 목록 조회
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlClmList" })
	public String matrlItemList(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) {
		String clmStatCd = null;
		String clmDt = null;
		List<MatrlClmVO> miList = matrlService.getMatrlClmList(loginVo.getCmpnyId(), null, clmStatCd, clmDt);
		model.addAttribute("miList", miList);

		return URL_PREFIX + "/matrlClmList";
	}

}
