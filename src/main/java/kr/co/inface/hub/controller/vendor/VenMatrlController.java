package kr.co.inface.hub.controller.vendor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.co.inface.hub.service.matrl.MatrlService;
import kr.co.inface.hub.service.matrl.vo.MatrlVO;

//@Slf4j
@Controller
@RequestMapping({ VenMatrlController.URL_PREFIX })
public class VenMatrlController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/vendor/matrl";

	@Autowired
	private MatrlService matrlService;

	/**
	 * 자재카테고리,품목 목록 조회
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlItemList" })
	public String matrlItemList(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) {
		List<MatrlVO> rstList = matrlService.getMatrlItemList(loginVo.getCmpnyId());
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/matrlItemList";
	}

	/**
	 * 자재폼목 사용 설정 처리
	 *
	 * @param loginVo
	 * @param matrlItemId
	 * @param buyTypeCd
	 * @param useYn
	 * @return
	 */
	@GetMapping({ "/useMyMatrlItem" })
	public String useMyMatrlItem(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String matrlItemId,
			@RequestParam(required = false) String buyTypeCd,
			@RequestParam String useYn) {

		// TODO get -> post 로 설정..
		matrlService.useMyMatrlItem(loginVo.getCmpnyUserId(), loginVo.getCmpnyId(), matrlItemId, buyTypeCd, useYn);

		return "redirect:" + URL_PREFIX + "/matrlItemList";
	}

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
