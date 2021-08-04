package kr.co.inface.hub.service.cmpny.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.inface.hub.config.security.Role;
import kr.co.inface.hub.config.security.Role.ROLE_NAME;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@ToString
@Slf4j
public class CmpnyUserVO implements UserDetails {

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

	private List<WorkSiteVO> workSiteList;
	private Map<String, WorkSiteVO> workSiteMap;
	private Collection<? extends GrantedAuthority> auths;

	/**
	 * 해당 현장권한이 있는지 체크
	 *
	 * @param workSiteId
	 * @return
	 */
	public boolean hasWorkSite(String workSiteId) {
		return workSiteMap != null && workSiteMap.containsKey(workSiteId);
	}

	/**
	 *
	 * 지정한 권한이 하나라도 포함되어 있는지 체크
	 *
	 * @param params
	 * @return
	 */
	public boolean hasAnyAuths(ROLE_NAME... params) {
		// TODO spring security 권한과 역할 개념에 대해서 공부가 필요해 보임..
		// https://stackoverflow.com/questions/19525380/difference-between-role-and-grantedauthority-in-spring-security
		if (auths != null && params != null) {
			for (GrantedAuthority auth : auths) {
				for (ROLE_NAME param : params) {
					if (((Role)auth).getName().equals(param.toString())) {
						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (auths != null) {
			log.debug("getAuthorities called.. prev auths.");
		} else {
			log.debug("getAuthorities called.. new auths");
			// TODO Auto-generated method stub
			// userTypeCd 사용자_유형_코드
			//		00 - 관리자
			//		10 - 건설사
			//		11 - 건설사-본사
			//		12 - 건설사-현장, 작업_현장_ID 필수
			//		20 - 자재공급업체
			List<GrantedAuthority> newAuths = new ArrayList<GrantedAuthority>();
			// TODO 코드 상수 처리..
			switch (userTypeCd) {
			case "00":
				// 관리자는 모든 권한을 할당.
				for (ROLE_NAME role : ROLE_NAME.values()) {
					newAuths.add(new Role(role));
				}
				break;
			case "11":
				// 11 - 건설사_본사, 권한체크로직등에서 현장권한도 포함해야함.
				newAuths.add(new Role(ROLE_NAME.COMPANY));
				newAuths.add(new Role(ROLE_NAME.COMPANY_SITE));
				break;
			case "12":
				// 12 - 건설사_현장
				newAuths.add(new Role(ROLE_NAME.COMPANY_SITE));
				break;
			case "20":
				// 20 - 자재공급업체
				newAuths.add(new Role(ROLE_NAME.VENDOR));
				break;
			}
			auths = newAuths;
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