package kr.co.inface.hub.config;

import java.text.DecimalFormat;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

/**
 * 모든 컨트롤러에 적용된다.
 *
 */
@ControllerAdvice
public class GlobalControllAdvice {

	@InitBinder
	public void registerCustomEditors(WebDataBinder binder, WebRequest request) {

		// String trim 처리 및 빈문자열 null 처리
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

		// 숫자 포맷 처리 - VO 에서 primitive 말고 object 로 선언해야 함.
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, new DecimalFormat("###,###"), true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class,       new DecimalFormat("###,###"), true));
		// TODO double 포맷은 자릿수등을 확인하고 설정하자.
		//binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class,   new DecimalFormat("###,###,###.00"), true));

		// 날짜 포맷
		//binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
}
