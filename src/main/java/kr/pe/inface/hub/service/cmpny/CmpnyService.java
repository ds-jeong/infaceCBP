package kr.pe.inface.hub.service.cmpny;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserSiteVO;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pr.inface.framework.web.BaseService;

@Service
public class CmpnyService extends BaseService {

	@Autowired
	private CmpnyUserMapper cmpnyUserMapper;

	/**
	 * 업체 사용자 목록
	 *
	 * @return
	 */
	public List<CmpnyUserVO> getCmpnyUserList() {
		List<CmpnyUserVO> userList = cmpnyUserMapper.getCmpnyUserList();

		// TODO 테스트용이므로..나중에 지우자.
		// 건설사_현장 사용자인 경우, 현장목록을 조회.
		// TODO 코드값을 상수로 정의해야 할텐데..
		for (CmpnyUserVO vo : userList) {
			if ("12".equals(vo.getUserTypeCd())) {
				List<CmpnyUserSiteVO> siteList = cmpnyUserMapper.getCmpnyUserSiteList(vo.getCmpnyUserId());
				vo.setSiteList(siteList);
			}
		}

		return userList;
	};

}
