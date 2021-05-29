package kr.pe.inface.hub.controller.cmpny;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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
import kr.pe.inface.hub.service.matrl.MatrlService;
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

		// 공급업체 자재단가요청 목록
		List<MatrlPriceVO> rstList = matrlService.getCmpnyMatrlPriceVenDtlList(loginVo.getCmpnyId(), splCmpnyId);
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/cmpnyMatrlPriceVenDtlList";
	}

	/**
	 * 업체 자재단가 공급업체 요청 상세/등록/수정
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

		// 00 - 작성중
		// 10 - 확인요청(공급업체)
		// 15 - 확인요청(건설업체)
		// 20 - 확정
		// 90 - 요청취소
		// TODO 코드 상수 처리
		String pageType = null; // DTL/INS/UPD - 상세/등록/수정

		// 업체 자재단가 공급업체 요청 상세
		MatrlPriceVO rst = null;

		// 적용일자가 지정된 경우, 상세/수정
		if ( StringUtils.isNotBlank(aplStrtDt) ) {
			rst = matrlService.getCmpnyMatrlPriceVenReqDtl(loginVo.getCmpnyId(), splCmpnyId, aplStrtDt);
			if (rst == null) {
				// TODO 오류 처리..
				throw new Exception("조회된 요청내역이 없습니다.");
			}
			switch (rst.getReqStatCd()) {
			case "00":
			case "10":
			case "15":
				pageType = "UPD";
				break;
			case "20":
			case "90":
			default:
				pageType = "DTL";
				break;
			}
		} else {
			String thisYearReqDt = DateFormatUtils.format(new Date(), "yyyyMMdd");
			String nextYearReqDt = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 1) + "0101";

			// 올해, 내년 데이터 있는지 체크하여 없는 경우를 등록처리
			if ((matrlService.checkCmpnyMatrlPriceVenReqDtlAplStrtDt(loginVo.getCmpnyId(), splCmpnyId, thisYearReqDt)) == null) {
				aplStrtDt = thisYearReqDt;
			} else if ((matrlService.checkCmpnyMatrlPriceVenReqDtlAplStrtDt(loginVo.getCmpnyId(), splCmpnyId, nextYearReqDt)) == null) {
				aplStrtDt = nextYearReqDt;
			} else {
				// TODO 오류 처리..
				throw new Exception("이미 처리내역이 있으므로, 신규등록할 수 없습니다.");
			}

			pageType = "INS";
			rst = new MatrlPriceVO();
			rst.setReqStatCd("00");
			rst.setAplStrtDt(aplStrtDt);
			rst.setAplEndDt(aplStrtDt.substring(0,4) + "1231");
			rst.setReqDt(thisYearReqDt);
		}
		rst.setPageType(pageType);
		model.addAttribute("rst", rst);


		// 요청 메모 목록
		// 업체 자재단가 공급업체 요청 자재목록
		List<MatrlPriceVO> memoList = null;
		List<MatrlPriceVO> rstList = null;
		switch (pageType) {
		case "UPD":
			// 메모 조회 - UPD 인 경우만 조회
			memoList = matrlService.getCmpnyMatrlPriceVenReqMemoList(loginVo.getCmpnyId(), splCmpnyId, aplStrtDt);
		case "DTL":
			// 요청 자재목록
			rstList = matrlService.getCmpnyMatrlPriceVenReqMatrlList(loginVo.getCmpnyId(), splCmpnyId, aplStrtDt);
			break;
		case "INS":
			// 현재 자재목록
			rstList = matrlService.getCmpnyMatrlPriceVenCurMatrlList(loginVo.getCmpnyId(), splCmpnyId);
			break;
		}
		model.addAttribute("memoList", memoList);
		model.addAttribute("rstList", rstList);

		//
		switch (pageType) {
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
	 * 업체 자재단가 요청정보 등록
	 *
	 * @param loginVo
	 * @param splCmpnyId
	 * @param model
	 * @return
	 */
	@PostMapping({ "/cmpnyMatrlPriceReqInsert" })
	public String cmpnyMatrlPriceReqInsert(@AuthenticationPrincipal CmpnyUserVO loginVo,
			MatrlPriceVO paramVO,
			Model model) {

		// TODO 요청 등록....
		log.debug("paramVo : ", paramVO);
		matrlService.insertCmpnyMatrlPriceReqInfo(loginVo.getCmpnyId(), paramVO, loginVo.getCmpnyUserId());

		// 등록된 상세화면으로.
		return "redirect:" + URL_PREFIX + "/cmpnyMatrlPriceVenReqDtl?splCmpnyId=" + paramVO.getSplCmpnyId() + "&aplStrtDt=" + paramVO.getAplStrtDt();
	}



}
