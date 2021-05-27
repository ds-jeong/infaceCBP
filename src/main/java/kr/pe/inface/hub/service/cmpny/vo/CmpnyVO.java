package kr.pe.inface.hub.service.cmpny.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.pe.inface.hub.config.security.Role;
import kr.pe.inface.hub.config.security.Role.ROLE_NAME;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@ToString
@Slf4j
public class CmpnyVO implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String cmpnyUserId;
	private String userTypeCd;
	private String cmpnyId;
	private String userNm;
	private String loginId;
	private String pwd;
	private String statCd;
	private String joinDt;
	private String deptNm;
	private String posiNm;
	private String telNo;
	private String faxNo;
	private String hpNo;
	private String email;
	private Date regDts;
	private String regpeId;
	private Date modDts;
	private String modpeId;

	private String cmpnyTypeCd;
	private String cmpnyNm;
	private List<CmpnyUserSiteVO> siteList;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		log.debug("getAuthorities called..");
		// TODO Auto-generated method stub
		// userTypeCd 사용자_유형_코드
		//		00 - 관리자
		//		10 - 건설사
		//		11 - 건설사-본사
		//		12 - 건설사-현장, 작업_현장_ID 필수
		//		20 - 자재공급업체
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		// TODO 코드 상수 처리..
		switch (userTypeCd) {
		case "00":
			// 관리자는 모든 권한을 할당.
			for (ROLE_NAME role : ROLE_NAME.values()) {
				auths.add(new Role(role));
			}
			break;
		case "11":
			// 11 - 건설사_본사 ( 모든 현장 권한 가능 )
			auths.add(new Role(ROLE_NAME.COMPANY));
			auths.add(new Role(ROLE_NAME.COMPANY_SITE));
			break;
		case "12":
			// 12 - 건설사_현장
			auths.add(new Role(ROLE_NAME.COMPANY_SITE));
			break;
		case "20":
			// 20 - 자재공급업체
			auths.add(new Role(ROLE_NAME.VENDOR));
			break;
		}
		return auths;
	}

	@Override
	public String getPassword() {
		return pwd;
	}

	@Override
	public String getUsername() {
		return userNm;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}