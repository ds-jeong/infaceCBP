package kr.pe.inface.hub.controller.cmpny;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.pe.inface.hub.service.cmpny.CmpnyService;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyVO;
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
	private CmpnyService cmpnyService;

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
		List<MatrlPriceVO> rstList = matrlService.getCmpnyMatrlPriceVenList(loginVo.getCmpnyId());
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenList";
	}


	/**
	 * 업체 자재단가 공급업체 가격요청 목록
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenDtlList" })
	public String cmpnyMatrlPriceVenDtlList(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String splCmpnyId,
			Model model) {

		// 공급업체 정보 조회
		CmpnyVO splCmpny = cmpnyService.getCmpny(splCmpnyId);
		model.addAttribute("splCmpny", splCmpny);

		// 공급업체 자재단가요청 목록
		List<MatrlPriceVO> rstList = matrlService.getCmpnyMatrlPriceVenDtlList(loginVo.getCmpnyId(), splCmpnyId);
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenDtlList";
	}

	/**
	 * 업체 자재단가 공급업체 요청 상세
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenReqDtl" })
	public String cmpnyMatrlPriceVenReqDtl(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String splCmpnyId,
			@RequestParam String aplStrtDt,
			Model model) {

		// 공급업체 정보 조회
		CmpnyVO splCmpny = cmpnyService.getCmpny(splCmpnyId);
		model.addAttribute("splCmpny", splCmpny);

		// 업체 자재단가 공급업체 요청 상세
		MatrlPriceVO rst = matrlService.getCmpnyMatrlPriceVenReqDtl(loginVo.getCmpnyId(), splCmpnyId, aplStrtDt);
		model.addAttribute("rst", rst);

		// 업체 자재단가 공급업체 요청 자재목록
		List<MatrlPriceVO> rstList = matrlService.getCmpnyMatrlPriceVenReqMatrlList(loginVo.getCmpnyId(), splCmpnyId, aplStrtDt);
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenReqDtl";
	}

}
