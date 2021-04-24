package kr.pe.inface.hub.controller.cmpny;

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
import kr.pe.inface.hub.service.matrl.vo.MatrlPriceVO;

//@Slf4j
@Controller
@RequestMapping({ CmpnyPriceController.URL_PREFIX })
public class CmpnyPriceController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/cmpny/price";

	@Autowired
	private MatrlService matrlService;

	/**
	 * 업체 자재단가 공급업체 목록
	 *
	 * @param authentication
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenList" })
	public String cmpnyMatrlPriceVenList(Authentication authentication, Model model) {
		// TODO Principal principal.. CmpnyUserVO 로 매핑할수 있을건데..
		CmpnyUserVO userVO = (CmpnyUserVO) authentication.getPrincipal();
//		) { UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<MatrlPriceVO> miList = matrlService.getCmpnyMatrlPriceVenList(userVO.getCmpnyId());
		model.addAttribute("miList", miList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenList";
	}


	/**
	 * 업체 자재단가 공급업체 가격상세 목록
	 *
	 * @param authentication
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenDtlList" })
	public String cmpnyMatrlPriceVenDtlList(Authentication authentication,
			@RequestParam String splCmpnyId,
			Model model) {

		// TODO Principal principal.. CmpnyUserVO 로 매핑할수 있을건데..
		CmpnyUserVO userVO = (CmpnyUserVO) authentication.getPrincipal();
//		) { UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<MatrlPriceVO> miList = matrlService.getCmpnyMatrlPriceVenDtlList(userVO.getCmpnyId(), splCmpnyId);
		model.addAttribute("miList", miList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenDtlList";
	}

}
