package kr.pe.inface.hub.controller.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.matrl.MatrlService;
import kr.pe.inface.hub.service.matrl.vo.MatrlCntrtVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlVO;

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
	 * @param authentication
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlItemList" })
	public String matrlItemList(Authentication authentication, Model model) {
		// TODO Principal principal.. CmpnyUserVO 로 매핑할수 있을건데..
		CmpnyUserVO userVO = (CmpnyUserVO) authentication.getPrincipal();
//		) { UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<MatrlVO> miList = matrlService.getMatrlItemList(userVO.getCmpnyId());
		model.addAttribute("miList", miList);

		return URL_PREFIX + "/matrlItemList";
	}

	/**
	 * 자재폼목 사용 설정 처리
	 *
	 * @param authentication
	 * @param matrlItemId
	 * @param useYn
	 * @return
	 */
	@GetMapping({ "/useMyMatrlItem" })
	public String useMyMatrlItem(Authentication authentication,
			@RequestParam String matrlItemId,
			@RequestParam String useYn) {

		CmpnyUserVO userVO = (CmpnyUserVO) authentication.getPrincipal();

		// TODO get -> post 로 설정..
		String buyTypeCd = null; // 건설사는 구매타입을 별도로 지정하지 않음.
		matrlService.useMyMatrlItem(userVO.getCmpnyUserId(), userVO.getCmpnyId(), matrlItemId, buyTypeCd, useYn);

		return "redirect:" + URL_PREFIX + "/matrlItemList";
	}

	/**
	 * 업체 자재품목 목록
	 *
	 * @param authentication
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlItemList" })
	public String cmpnyMatrlItemList(Authentication authentication, Model model) {
		// TODO Principal principal.. CmpnyUserVO 로 매핑할수 있을건데..
		CmpnyUserVO userVO = (CmpnyUserVO) authentication.getPrincipal();
//		) { UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<MatrlVO> miList = matrlService.getCmpnyMatrlItemList(userVO.getCmpnyId());
		model.addAttribute("miList", miList);

		return URL_PREFIX + "/cmpnyMatrlItemList";
	}

	/**
	 * 업체 자재 목록
	 *
	 * @param authentication
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlList" })
	public String cmpnyMatrlList(Authentication authentication, Model model) {
		// TODO Principal principal.. CmpnyUserVO 로 매핑할수 있을건데..
		CmpnyUserVO userVO = (CmpnyUserVO) authentication.getPrincipal();
//		) { UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<MatrlVO> miList = matrlService.getCmpnyMatrlList(userVO.getCmpnyId());
		model.addAttribute("miList", miList);

		return URL_PREFIX + "/cmpnyMatrlList";
	}


	/**
	 * 자재품목 공급업체 계약 목록
	 *
	 * @param authentication
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlItemCntrtList" })
	public String matrlItemCntrtList(Authentication authentication,
			@RequestParam String matrlItemId,
			@RequestParam String buyTypeCd,
			Model model) {
		// TODO Principal principal.. CmpnyUserVO 로 매핑할수 있을건데..
		CmpnyUserVO userVO = (CmpnyUserVO) authentication.getPrincipal();
//		) { UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<MatrlCntrtVO> miList = matrlService.getMatrlItemCntrtList(userVO.getCmpnyId(), matrlItemId, buyTypeCd);
		model.addAttribute("miList", miList);

		return URL_PREFIX + "/matrlItemCntrtList";
	}


	/**
	 * 자재품목 공급업체 계약 설정
	 *
	 * @param authentication
	 * @param matrlItemId
	 * @param splCmpnyId
	 * @param buyTypeCd
	 * @param cntrtStatCd
	 * @return
	 */
	@GetMapping({ "/updMatrlItemCntrtStat" })
	public String updMatrlItemCntrtStat(Authentication authentication,
			@RequestParam String matrlItemId,
			@RequestParam String splCmpnyId,
			@RequestParam String buyTypeCd,
			@RequestParam String cntrtStatCd) {

		CmpnyUserVO userVO = (CmpnyUserVO) authentication.getPrincipal();

		// TODO get -> post 로 설정..
		matrlService.updMatrlItemCntrtStat(userVO.getCmpnyUserId(), userVO.getCmpnyId(), matrlItemId, splCmpnyId, buyTypeCd, cntrtStatCd);

		return "redirect:" + URL_PREFIX + "/matrlItemCntrtList?matrlItemId=" + matrlItemId + "&buyTypeCd=" + buyTypeCd;
	}

}
