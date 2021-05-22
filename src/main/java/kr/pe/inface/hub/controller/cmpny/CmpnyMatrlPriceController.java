package kr.pe.inface.hub.controller.cmpny;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping({ CmpnyMatrlPriceController.URL_PREFIX })
public class CmpnyMatrlPriceController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/cmpny/matrl/price";

	@Autowired
	private MatrlService matrlService;

	/**
	 * 업체 자재단가 공급업체 목록
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenList" })
	public String cmpnyMatrlPriceVenList(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) {
		List<MatrlPriceVO> miList = matrlService.getCmpnyMatrlPriceVenList(loginVo.getCmpnyId());
		model.addAttribute("miList", miList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenList";
	}


	/**
	 * 업체 자재단가 공급업체 가격상세 목록
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenDtlList" })
	public String cmpnyMatrlPriceVenDtlList(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String splCmpnyId,
			Model model) {

		List<MatrlPriceVO> miList = matrlService.getCmpnyMatrlPriceVenDtlList(loginVo.getCmpnyId(), splCmpnyId);
		model.addAttribute("miList", miList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenDtlList";
	}

}
