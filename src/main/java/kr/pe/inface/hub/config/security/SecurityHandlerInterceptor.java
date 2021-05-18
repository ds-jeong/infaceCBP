package kr.pe.inface.hub.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;

/**
 * Spring Security 관련 인터셉터
 *
 */
public class SecurityHandlerInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// 로그인 객체를 기본적으로 mav 에 설정해준다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof CmpnyUserVO) {
			CmpnyUserVO loginVo = (CmpnyUserVO) authentication.getPrincipal();
			modelAndView.addObject("LOGIN_VO", loginVo);

			// 사용자_유형_코드
			// 00 - 관리자
			// 10 - 건설사
			// 11 - 건설사-본사
			// 12 - 건설사-현장, cmpny_user_site 참조
			// 20 - 자재공급업체
			Object menuList = null;
			switch (loginVo.getUserTypeCd()) {
				case "00":
					menuList = Const.ADMIN_MENU;
					break;
				case "11":
					menuList = Const.CMPNY_MENU;
					break;
				case "12":
					menuList = Const.SITE_MENU;
					break;
				case "20":
					menuList = Const.VENDOR_MENU;
					break;
			}
			modelAndView.addObject("MENU_LIST", menuList);
		}

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
