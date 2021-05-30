package kr.pe.inface.hub.controller.vendor;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

		// 00 - 작성중
		// 10 - 확인요청(공급업체)
		// 15 - 확인요청(건설업체)
		// 20 - 확정
		// 90 - 요청취소
		// TODO 코드 상수 처리
		String pageType = null; // DTL/UPD - 상세/수정

		// 업체자재단가 업체가격요청 상세
		MatrlPriceVO rst = null;

		// 적용일자가 지정된 경우, 상세/수정
		if ( StringUtils.isNotBlank(aplStrtDt) ) {
			rst = matrlPriceService.getCmpnyMatrlPriceVenReqDtl(cmpnyId, loginVo.getCmpnyId(), aplStrtDt);
			if (rst == null) {
				// TODO 오류 처리..
				throw new Exception("조회된 요청내역이 없습니다.");
			}
			switch (rst.getReqStatCd()) {
			case "10":
				pageType = "UPD";
				break;
			case "15": // 확인요청(건설업체)인 경우는 조회처리
			case "20":
			default:
				pageType = "DTL";
				break;
			}
			//case "00": // 작성중,삭제상태는 공급업체가 조회불가
			//case "90":
		}
		rst.setPageType(pageType);
		model.addAttribute("rst", rst);


		// 요청 메모 목록
		// 업체자재단가 업체가격요청 자재목록
		List<MatrlPriceVO> memoList = null;
		List<MatrlPriceVO> rstList = null;
		switch (pageType) {
		case "UPD":
		case "DTL":
			// 메모 목록
			memoList = matrlPriceService.getCmpnyMatrlPriceVenReqMemoList(cmpnyId, loginVo.getCmpnyId(), aplStrtDt);
			// 요청 자재목록
			rstList = matrlPriceService.getCmpnyMatrlPriceVenReqMatrlList(cmpnyId, loginVo.getCmpnyId(), aplStrtDt);
			break;
		}
		model.addAttribute("memoList", memoList);
		model.addAttribute("rstList", rstList);

		//
		switch (pageType) {
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

		// TODO 요청 등록....
		log.debug("paramVo : " + paramVO);
		matrlPriceService.updateCmpnyMatrlPriceReqInfoSplCmpny(loginVo, paramVO);

		// 등록된 상세화면으로.
		return "redirect:" + URL_PREFIX + "/cmpnyMatrlPriceVenReqDtl?cmpnyId=" + paramVO.getCmpnyId() + "&aplStrtDt=" + paramVO.getAplStrtDt();
	}

}
