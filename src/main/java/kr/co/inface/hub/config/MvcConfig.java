package kr.co.inface.hub.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching // TODO 캐시 설정 필요, 적당한 시간의 evict 기준 필요.
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(new MvcDefaultHandlerInterceptor())
			.excludePathPatterns("/favicon.ico", "/css/**", "/fonts/**", "/images/**", "/js/**")
		;

		WebMvcConfigurer.super.addInterceptors(registry);
	}

	//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//		resolvers.add(new HandlerMethodArgumentResolver() {
//			@Override
//			public boolean supportsParameter(MethodParameter parameter) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
//					NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
//
//		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
//	}

//	@Override
//	public void addFormatters(FormatterRegistry registry) {
//
//		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addFormatters(registry);
//	}

}


