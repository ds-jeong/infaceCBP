package kr.pe.inface.hub.config.security;

/**
 *
 */
//@EnableWebSecurity
public class WebSecurityConfig2 {

//	@Autowired
//	private FaceLoginApiUserDetailsService faceLoginApiUserDetailsService;
//
//	@Autowired
//	private FaceLoginUserDetailsService faceLoginUserDetailsService;

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

// https://sjh836.tistory.com/165
// http://dveamer.github.io/backend/PreventDuplicatedLogin.html

//	/**
//	 * API 용
//	 */
//	@Configuration
//	public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//		// WebSecurityConfigurerAdapter.. default <http /> config
//
//		// <authentication-manager> 설정...
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(faceLoginApiUserDetailsService);
//			PreAuthenticatedAuthenticationProvider preAuthenticatedProvider = new PreAuthenticatedAuthenticationProvider();
//			preAuthenticatedProvider.setPreAuthenticatedUserDetailsService(wrapper);
//
//			// @formatter:off
//			auth
//				.authenticationProvider(preAuthenticatedProvider)
//			;
//			// @formatter:on
//
//		}
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// PreAuthFilter 생성 - Authorization 헤더값으로 Authentication 수행
//			RequestHeaderAuthenticationFilter requestHeaderFilter = new RequestHeaderAuthenticationFilter();
//			requestHeaderFilter.setPrincipalRequestHeader("Authorization");
//			// authenticationManager() 메소드는 configure(AuthenticationManagerBuilder auth) 에서 설정된 auth 를 반환하는 듯 하다.
//			requestHeaderFilter.setAuthenticationManager(authenticationManager());
//
//			// @formatter:off
//			http
////				.portMapper() // 좀 더 조사해보자.
////				.http(8080)
////				.mapsTo(8443)
////				.and() // 다른 설정을 위해, HttpSecurity 를 반환
//
////				.httpBasic() // RequestHeaderAuthenticationFilter 사용하니까.. httpBasic 즉 BasicAuthenticationFilter 사용안해도 됨
////					.and()
//
//				.antMatcher("/api/**")
//
//				.authorizeRequests()
//					.antMatchers(HttpMethod.POST).access("hasRole('API')")
//					.antMatchers(HttpMethod.GET).denyAll()
//					.and()
//
//				                               // Filter 들의 순서는 아래 참고.
//				                               // WebAsyncManagerIntegrationFilter
//				.securityContext().disable()   // SecurityContextPersistenceFilter
//				.headers().disable()           // HeaderWriterFilter
//				.csrf().disable()              // CsrfFilter
//				.logout().disable()            // LogoutFilter
//
////				.x509().disable()              // X509AuthenticationFilter
//				                               // AbstractPreAuthenticatedProcessingFilter
//				.addFilterAt(requestHeaderFilter, AbstractPreAuthenticatedProcessingFilter.class) // RequestHeaderAuthenticationFilter 등록
//				                               // UsernamePasswordAuthenticationFilter
//				                               // BasicAuthenticationFilter
//
//				.requestCache().disable()      // RequestCacheAwareFilter
//                                               // SecurityContextHolderAwareRequestFilter
//				                               // RememberMeAuthenticationFilter
//				.anonymous().disable()         // AnonymousAuthenticationFilter
//				.sessionManagement().disable() // SessionManagementFilter
//				.exceptionHandling().disable() // ExceptionTranslationFilter
//
//				                               // FilterSecurityInterceptor
//			;
//			// @formatter:on
//
//			// TODO
//			// api 에서..
//			// WebAsyncManagerIntegrationFilter
//			// SecurityContextHolderAwareRequestFilter
//			// 필터를 사용안하게 하고 싶음.. 용도도 정확히 확인해야겟고..
//
//			// [[ 사용법 가이드 ]]
//
//			// addFilter 예제
//			//	.addFilterAt(testFilter, CsrfFilter.class)       // 지정된 filter 수행 전에 처리됨.
//			//	.addFilterBefore(testFilter, LogoutFilter.class) // 지정된 filter 수행 전에 처리됨. addFilterAt 과 동일한 듯..
//			//	.addFilter(testFilter)                           // 지정된 filter 수행 전에 처리됨. addFilterAt 과 동일한 듯..
//			//	.addFilterAfter(testFilter, LogoutFilter.class)  // 지정된 filter 수행 이후 처리됨.
//
//			// 5.9 Post Processing Configured Objects.
//			//   ref : https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle/#post-processing-configured-objects
//			//
//			// 모든 설정이 위와 같은 메소드로 제공되지 않으므로, 아래와 같이 사용자가 정의할 수 있는 방법을 제공한다.
//			// ( withObjectPostProcessor 를 사용 )
//			//	http
//			//		.authorizeRequests()
//			//			.anyRequest().authenticated()
//			//			.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//			//				public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
//			//					fsi.setPublishAuthorizationSuccess(true);
//			//					return fsi;
//			//				}
//			//			});
//
//			// 26. Expression-Based Access Control
//			//   ref : https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle/#el-common-built-in
//			// Referring to Beans.. 빈을 참조하여 체크하는 것도 가능.
//			//
//		}
//	}
//
//	/**
//	 * WEB formlogin 용
//	 */
//	@Configuration
//	public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//		@Override
//		public void configure(WebSecurity web) throws Exception {
//			// <http pattern="/resources/**" security="none" />
//			// @formatter:off
//			web
//				.ignoring()
//					.antMatchers("/resources/**")
//					.antMatchers("/favicon.ico")
//					.antMatchers("/")
//					.antMatchers("/h2-console")
//			;
//			// @formatter:on
//		}
//
//		// <authentication-manager> 설정...
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			// @formatter:off
////			auth
////				.userDetailsService(faceLoginUserDetailsService)
////				.passwordEncoder(passwordEncoder())
////			;
//			// @formatter:on
//		}
//
//		// <http> 설정....
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// @formatter:off
//			http
//				.authorizeRequests()
//					.antMatchers("/common/loginPage.do*").permitAll()
//					.anyRequest().access("hasRole('USER')")
//					.and()
//
//				// TODO remember-me 확인 필요... 서버재시작했는데, 로그인정보만 날아가고..로그인이라고 체크함..
//				.rememberMe()
//					.rememberMeCookieName("testRememberMeKey")
//					.and()
//
//				.formLogin()
//					.loginPage("/common/loginPage.do")
//					.loginProcessingUrl("/common/processLogin.do")
//					.defaultSuccessUrl("/", true)
//					.permitAll()
//					.and()
//
//				.logout()
//					.logoutUrl("/common/processLogout.do")
//					.deleteCookies("JSESSIONID")
//					.logoutSuccessUrl("/")
//					.and()
//
//				.csrf().disable()
//				.requestCache().disable()
//				.sessionManagement().disable()
//				// API 용 코드 참고...
//			;
//			// @formatter:on
//		}
//	}

}
