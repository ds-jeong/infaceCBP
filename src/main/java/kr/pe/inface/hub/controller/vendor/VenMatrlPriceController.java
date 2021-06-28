package kr.pe.inface.hub.controller.vendor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.pe.inface.hub.service.cmpny.CmpnyService;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyVO;
import kr.pe.inface.hub.service.matrl.MatrlPriceService;
import kr.pe.inface.hub.service.matrl.vo.MatrlPriceVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping({ VenMatrlPriceController.URL_PREFIX })
public class VenMatrlPriceController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/vendor/matrl/price";

	@Autowired
	private CmpnyService cmpnyService;

	@Autowired
	private MatrlPriceService matrlPriceService;

	/**
	 * 건설업체/공급업체 기준으로 계약된 단가업체 목록을 조회
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenList" })
	public String cmpnyMatrlPriceVenList(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) throws Exception {
		List<MatrlPriceVO> rstList = matrlPriceService.getCmpnyMatrlPriceVenList(null, loginVo.getCmpnyId());
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenList";
	}


	/**
	 * 업체자재단가 업체가격요청 목록
	 *
	 * @param loginVo
	 * @param splCmpnyId
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenDtlList" })
	public String cmpnyMatrlPriceVenDtlList(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String cmpnyId,
			Model model) {

		// 건설업체 정보 조회
		CmpnyVO cmpny = cmpnyService.getCmpny(cmpnyId);
		model.addAttribute("cmpny", cmpny);

		// 업체가격요청 목록
		List<MatrlPriceVO> rstList = matrlPriceService.getCmpnyMatrlPriceVenDtlList(cmpnyId, loginVo.getCmpnyId());
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenDtlList";
	}

	/**
	 * 업체자재단가 업체가격요청 상세/수정
	 *
	 * @param loginVo
	 * @param splCmpnyId
	 * @param aplStrtDt 공급업체가 등록하는 경우는 없으므로, 필수값
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenReqDtl" })
	public String cmpnyMatrlPriceVenReqDtl(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String cmpnyId,
			@RequestParam String aplStrtDt,
			Model model) throws Exception {

		// 건설업체 정보 조회
		CmpnyVO cmpny = cmpnyService.getCmpny(cmpnyId);
		model.addAttribute("cmpny", cmpny);

		// 데이터 조회.
		Map<String, Object> modelMap = matrlPriceService.getCmpnyMatrlPriceVenReqDtlForSplCmpny(loginVo, cmpnyId, aplStrtDt);
		model.addAllAttributes(modelMap);

		// 결과타입에 따른 화면 분기
		switch (((MatrlPriceVO)modelMap.get("rst")).getPageType()) {
		case "UPD":
			// 등록/수정 페이지
			return URL_PREFIX + "/cmpnyMatrlPriceVenReqDtlUpd";
		case "DTL":
		default:
			// 상세페이지
			return URL_PREFIX + "/cmpnyMatrlPriceVenReqDtl";
		}
	}

	/**
	 * 업체자재단가 요청정보 수정
	 *
	 * @param loginVo
	 * @param splCmpnyId
	 * @param model
	 * @return
	 */
	@PostMapping({ "/cmpnyMatrlPriceReqUpdate" })
	public String cmpnyMatrlPriceReqUpdate(@AuthenticationPrincipal CmpnyUserVO loginVo,
			MatrlPriceVO paramVO,
			Model model) throws Exception {

		log.debug("paramVo : " + paramVO);
		matrlPriceService.updateCmpnyMatrlPriceReqInfoForSplCmpny(loginVo, paramVO);

		// 등록된 상세화면으로.
		return "redirect:" + URL_PREFIX + "/cmpnyMatrlPriceVenReqDtl?cmpnyId=" + paramVO.getCmpnyId() + "&aplStrtDt=" + paramVO.getAplStrtDt();
	}

}
