package kr.pe.inface.hub.controller.site;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.matrl.MatrlClmService;
import kr.pe.inface.hub.service.matrl.MatrlService;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlVO;

//@Slf4j
@Controller
@RequestMapping({ SiteMatrlClmController.URL_PREFIX })
public class SiteMatrlClmController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/site/matrlclm";

	@Autowired
	private MatrlService matrlService;

	@Autowired
	private MatrlClmService matrlClmService;

	/**
	 * 자재청구 목록 조회
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlClmList" })
	public String matrlClmList(@AuthenticationPrincipal CmpnyUserVO loginVo, Model model) {
		Calendar clmDtCal = Calendar.getInstance();
		clmDtCal.add(Calendar.MONTH, -1); // 지정하지 않으면 최근 1개월

		String clmStatCd = null;
		String clmDt = FastDateFormat.getInstance("yyyyMMdd").format(clmDtCal);
		List<MatrlClmVO> rstList = matrlClmService.getMatrlClmList(loginVo.getCmpnyId(), null, clmStatCd, clmDt);
		model.addAttribute("rstList", rstList);

		return URL_PREFIX + "/matrlClmList";
	}

	/**
	 * 자재청구 상세 조회
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/matrlClmDtl" })
	public String matrlClmDtl(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String matrlClmNo,
			Model model) throws Exception {

		MatrlClmVO matrlVo = matrlClmService.getMatrlClm(loginVo, matrlClmNo);
		model.addAttribute("matrlVo", matrlVo);

		return URL_PREFIX + "/matrlClmDtl";
	}

	/**
	 * 자재청구 등록/수정 페이지
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping({ "/matrlClmUpdPage" })
	public String matrlClmUpdPage(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam(required = false) String matrlClmNo,
			Model model) throws Exception {

		MatrlClmVO retVo = matrlClmService.getMatrlClmUpdPage(loginVo, matrlClmNo);
		model.addAttribute("matrlVo", retVo);

		// TODO 업체 자재 목록. 화면에서 ajax 기능으로 구현 필요
		List<MatrlVO> matrlList = matrlService.getCmpnyMatrlList(loginVo.getCmpnyId());
		model.addAttribute("matrlList", matrlList);

		return URL_PREFIX + "/matrlClmUpd";
	}

	/**
	 * 자재청구 등록
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@PostMapping({ "/matrlClmInsert" })
	public String matrlClmInsert(@AuthenticationPrincipal CmpnyUserVO loginVo,
			MatrlClmVO paramVo,
			Model model) throws Exception {

		String matrlClmNo = matrlClmService.insertMatrlClm(loginVo, paramVo);

		// 등록된 상세화면으로.
		return "redirect:" + URL_PREFIX + "/matrlClmDtl?matrlClmNo=" + matrlClmNo;
	}

	/**
	 * 자재청구 수정
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 */
	@PostMapping({ "/matrlClmUpdate" })
	public String matrlClmUpdate(@AuthenticationPrincipal CmpnyUserVO loginVo,
			MatrlClmVO paramVo,
			Model model) {

		String matrlClmNo = null;

		// 등록된 상세화면으로.
		return "redirect:" + URL_PREFIX + "/matrlClmDtl?matrlClmNo=" + matrlClmNo;
	}

	/**
	 * 자재청구 승인
	 *
	 * @param loginVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping({ "/matrlClmAprv" })
	public String matrlClmAprv(@AuthenticationPrincipal CmpnyUserVO loginVo,
			@RequestParam String matrlClmNo,
			@RequestParam int aprvSeq,
			Model model) throws Exception {

		// TODO
		matrlClmService.updateMatrlClmAprv(loginVo, matrlClmNo, aprvSeq);

		// 등록된 상세화면으로.
		return "redirect:" + URL_PREFIX + "/matrlClmDtl?matrlClmNo=" + matrlClmNo;
	}

}
