package kr.co.inface.hub.config.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.inface.hub.service.cmpny.mapper.CmpnyUserMapper;
import kr.co.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.co.inface.hub.service.cmpny.vo.WorkSiteVO;

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

			// 현재 유효한 상태가 아니면..
			// TODO 코드값을 상수로 정의해야 할텐데..
			if (!"20".equals(vo.getStatCd())) {
				vo = null;
			}

			// 건설사_현장 사용자인 경우, 현장목록을 조회하여 map 으로 저장.
			// TODO 코드값을 상수로 정의해야 할텐데..
			if ("12".equals(vo.getUserTypeCd())) {
				List<WorkSiteVO> siteList = cmpnyUserMapper.getCmpnyUserSiteList(vo.getCmpnyUserId());

				Map<String, WorkSiteVO> workSiteMap = new HashMap<String, WorkSiteVO>();
				if (siteList == null || siteList.size() == 0) {
					// TODO 관리현장이 없는 경우..
				} else {
					for (WorkSiteVO v : siteList) {
						workSiteMap.put(v.getWorkSiteId(), v);
					}
				}
				vo.setWorkSiteList(siteList);
				vo.setWorkSiteMap(workSiteMap);
			}
		}
		return vo;
	}

}
