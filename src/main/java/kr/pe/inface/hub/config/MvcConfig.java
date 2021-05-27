package kr.pe.inface.hub.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching // TODO 캐시 설정 필요
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(new MvcDefaultHandlerInterceptor())
			.excludePathPatterns("/favicon.ico", "/css/**", "/fonts/**", "/images/**", "/js/**")
		;

		WebMvcConfigurer.super.addInterceptors(registry);
	}

}