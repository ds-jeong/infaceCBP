package kr.pe.inface.hub.controller.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.matrl.MatrlService;
import kr.pe.inface.hub.service.matrl.vo.MatrlVO;

//@Slf4j
@Controller
@RequestMapping({ SiteMatrlController.URL_PREFIX })
public class SiteMatrlController {

	/**
	 * url, view 의 prefix 를 동일하게 사용.
	 */
	public static final String URL_PREFIX = "/site/matrl";

	@Autowired
	private MatrlService matrlService;

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
