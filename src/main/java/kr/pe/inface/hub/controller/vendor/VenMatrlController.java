package kr.pe.inface.hub.controller.vendor;

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
import kr.pe.inface.hub.service.matrl.vo.MatrlVO;

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
	 * @param buyTypeCd
	 * @param useYn
	 * @return
	 */
	@GetMapping({ "/useMyMatrlItem" })
	public String useMyMatrlItem(Authentication authentication,
			@RequestParam String matrlItemId,
			@RequestParam(required = false) String buyTypeCd,
			@RequestParam String useYn) {

		CmpnyUserVO userVO = (CmpnyUserVO) authentication.getPrincipal();

		// TODO get -> post 로 설정..
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

}
