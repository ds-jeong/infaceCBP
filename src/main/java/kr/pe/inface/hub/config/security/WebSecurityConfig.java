package kr.pe.inface.hub.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	접근 주체 ( Principal ) : 보호된 대상에 접근하는 클라이언트
//	인증 ( Authentication ) : 현재 유저가 누구인지 확인 / 애플리케이션의 작업을 수행할 수 있는 주체임을 증명하는 과정
//	인가 ( Authorize ) : 현재 유저가 어떤 서비스, 페이지에 접근할 수 있는 권한이 있는지 검사
//	권한 ( Authorization ) : 인증된 주체가 애플리케이션의 동작을 수행할 수 있도록 허락되있는지를 결정

	@Autowired
	private CmpnyUserDetailsService cmpnyUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				// Spring Security should completely ignore URLs starting with /resources/
				.antMatchers("/favicon.ico")
				.antMatchers("/jquery/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// 순서대로 먼저 적용되는 항목이 적용됨.
				.antMatchers("/admin/**").hasAnyRole("ADMIN") // Role별 메뉴가 다르므로, 모든 메뉴에 "ADMIN" 줄 필요는 없을 듯
				.antMatchers("/cmpny/**").hasAnyRole("COMPANY")
				.antMatchers("/site/**").hasAnyRole("COMPANY_SITE")
				.antMatchers("/vendor/**").hasAnyRole("VENDOR")
				.antMatchers("/auth/**").permitAll()
				.antMatchers("/*").permitAll() // root 경로 허용.
				.antMatchers("/**").denyAll()  // 2depth 이상 기본 거부
				.and()

			.formLogin()
				.defaultSuccessUrl("/", true)
				.permitAll() // set permitAll for all URLs associated with Form Login
				.and()

			.logout()
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/")
				.and()

			.csrf()
				.disable()

			.sessionManagement()
				.maximumSessions(1)
				.expiredUrl("/auth/login") // TODO 기본 url 설정 변경 확인.. 변수로 선언?
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			// enable in memory based authentication with a user named "user" and "admin"
//			.inMemoryAuthentication()
//				.withUser("admin").password("{noop}1").roles("ADMIN", "GRP01").and() // admin 은 권한을 모두 지정해줘야 하나?
//				.withUser("company").password("{noop}1").roles("COMPANY").and()
//				.withUser("vendor").password("{noop}1").roles("VENDOR").and()
//				.and()

			.userDetailsService(cmpnyUserDetailsService)
			.passwordEncoder(passwordEncoder())
			.and()
		;
	}
}
