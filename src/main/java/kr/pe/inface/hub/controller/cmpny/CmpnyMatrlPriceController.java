package kr.pe.inface.hub.controller.cmpny;

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
@RequestMapping({ CmpnyMatrlPriceController.URL_PREFIX })
public class CmpnyMatrlPriceController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/cmpny/matrl/price";

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
		List<MatrlPriceVO> rstList = matrlPriceService.getCmpnyMatrlPriceVenList(loginVo.getCmpnyId(), null);
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
			@RequestParam String splCmpnyId,
			Model model) {

		// 공급업체 정보 조회
		CmpnyVO splCmpny = cmpnyService.getCmpny(splCmpnyId);
		model.addAttribute("splCmpny", splCmpny);

		// 업체가격요청 목록
		List<MatrlPriceVO> rstList = matrlPriceService.getCmpnyMatrlPriceVenDtlList(loginVo.getCmpnyId(), splCmpnyId);
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenDtlList";
	}

	/**
	 * 업체자재단가 업체가격요청 상세/등록/수정
	 *
	 * @param loginVo
	 * @param splCmpnyId
	 * @param aplStrtDt 지정하지 않으면 신규 등록
	 * @param model
	 * @return
	 */
	@GetMapping({ "/cmpnyMatrlPriceVenReqDtl" })
	public String cmpnyMatrlPriceVenReqDtl(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String splCmpnyId,
			@RequestParam(required = false) String aplStrtDt,
			Model model) throws Exception {


		// 공급업체 정보 조회
		CmpnyVO splCmpny = cmpnyService.getCmpny(splCmpnyId);
		model.addAttribute("splCmpny", splCmpny);

		// 데이터 조회.
		Map<String, Object> modelMap = matrlPriceService.getCmpnyMatrlPriceVenReqDtlForCmpny(loginVo, splCmpnyId, aplStrtDt);
		model.addAllAttributes(modelMap);

		// 결과타입에 따른 화면 분기
		switch (((MatrlPriceVO)modelMap.get("rst")).getPageType()) {
		case "INS":
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
	 * 업체자재단가 요청정보 등록
	 *
	 * @param loginVo
	 * @param splCmpnyId
	 * @param model
	 * @return
	 */
	@PostMapping({ "/cmpnyMatrlPriceReqInsert" })
	public String cmpnyMatrlPriceReqInsert(@AuthenticationPrincipal CmpnyUserVO loginVo,
			MatrlPriceVO paramVO,
			Model model) throws Exception {

		log.debug("paramVo : " + paramVO);
		matrlPriceService.insertCmpnyMatrlPriceReqInfo(loginVo, paramVO);

		// 등록된 상세화면으로.
		return "redirect:" + URL_PREFIX + "/cmpnyMatrlPriceVenReqDtl?splCmpnyId=" + paramVO.getSplCmpnyId() + "&aplStrtDt=" + paramVO.getAplStrtDt();
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
		matrlPriceService.updateCmpnyMatrlPriceReqInfoForCmpny(loginVo, paramVO);

		// 등록된 상세화면으로.
		return "redirect:" + URL_PREFIX + "/cmpnyMatrlPriceVenReqDtl?splCmpnyId=" + paramVO.getSplCmpnyId() + "&aplStrtDt=" + paramVO.getAplStrtDt();
	}

}
