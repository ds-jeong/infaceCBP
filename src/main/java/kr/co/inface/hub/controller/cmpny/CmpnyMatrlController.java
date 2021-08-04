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
import kr.co.inface.hub.service.matrl.MatrlService;
import kr.co.inface.hub.service.matrl.vo.MatrlCntrtVO;
import kr.co.inface.hub.service.matrl.vo.MatrlVO;

//@Slf4j
@Controller
@RequestMapping({ CmpnyMatrlController.URL_PREFIX })
public class CmpnyMatrlController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/cmpny/matrl";

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
	 * @param useYn
	 * @return
	 */
	@GetMapping({ "/useMyMatrlItem" })
	public String useMyMatrlItem(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String matrlItemId,
			@RequestParam String useYn) {

		// TODO get -> post 로 설정..
		String buyTypeCd = null; // 건설사는 구매타입을 별도로 지정하지 않음.
		matrlService.useMyMatrlItem(loginVo.getCmpnyUserId(), loginVo.getCmpnyId(), matrlItemId, buyTypeCd, useYn);

		return "redirect:" + URL_PREFIX + "/matrlItemList";
	}

	/**
	 * 업체 자재품목 목록
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlItemList" })
	public String cmpnyMatrlItemList(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) {
		List<MatrlVO> rstList = matrlService.getCmpnyMatrlItemList(loginVo.getCmpnyId());
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/cmpnyMatrlItemList";
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


	/**
	 * 자재품목 공급업체 계약 목록
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlItemCntrtList" })
	public String matrlItemCntrtList(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String matrlItemId,
			Model model) {

		List<MatrlCntrtVO> rstList = matrlService.getMatrlItemCntrtList(loginVo.getCmpnyId(), matrlItemId, null);
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/matrlItemCntrtList";
	}


	/**
	 * 자재품목 공급업체 계약 설정
	 *
	 * @param loginVo
	 * @param matrlItemId
	 * @param splCmpnyId
	 * @param buyTypeCd
	 * @param cntrtStatCd
	 * @return
	 */
	@GetMapping({ "/updMatrlItemCntrtStat" })
	public String updMatrlItemCntrtStat(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String matrlItemId,
			@RequestParam String splCmpnyId,
			@RequestParam String buyTypeCd,
			@RequestParam String cntrtStatCd) {

		// TODO get -> post 로 설정..
		matrlService.updMatrlItemCntrtStat(loginVo.getCmpnyUserId(), loginVo.getCmpnyId(), matrlItemId, splCmpnyId, buyTypeCd, cntrtStatCd);

		return "redirect:" + URL_PREFIX + "/matrlItemCntrtList?matrlItemId=" + matrlItemId + "&buyTypeCd=" + buyTypeCd;
	}

}
