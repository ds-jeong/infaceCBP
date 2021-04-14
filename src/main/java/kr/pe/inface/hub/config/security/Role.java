package kr.pe.inface.hub.config.security;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private static final String PREFIX = "ROLE_";

	public static enum ROLE_NAME {
		  ADMIN   // 관리자
		, COMPANY // 건설사
		, VENDOR  // 자재공급업체
	};

	String name;

	public Role(ROLE_NAME name) {
		this.name = name.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return PREFIX + name;
	}

}
