package kr.pe.inface.hub.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.pe.inface.hub.service.cmpny.CmpnyUserMapper;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;

@Service
public class CmpnyUserDetailsService implements UserDetailsService {

	@Autowired
	private CmpnyUserMapper cmpnyUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CmpnyUserVO vo = cmpnyUserMapper.getCmpnyUser(username);
		if (vo != null) {
			// TODO 권한 설정을 여기서 해야할 듯? // CmpnyUserVO, UserDetails 의 getAuthorities 메소드에서 정의할까..
			// TODO 여기서 정의하기는 애매하네.. UserDetails 에서 일단 해보자.
			// userTypeCd 사용자_유형_코드 : 00-관리자, 10-건설사, 20-자재공급업체
		}
		return vo;
	}

}
