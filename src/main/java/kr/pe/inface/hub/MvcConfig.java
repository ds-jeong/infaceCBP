package kr.pe.inface.hub;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.pe.inface.hub.config.security.SecurityHandlerInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(new SecurityHandlerInterceptor())
			.excludePathPatterns("/favicon.ico", "/css/**", "/fonts/**", "/images/**", "/js/**")
		;

		WebMvcConfigurer.super.addInterceptors(registry);
	}

}