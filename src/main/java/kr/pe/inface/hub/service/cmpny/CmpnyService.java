package kr.pe.inface.hub.service.cmpny;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.inface.hub.service.cmpny.mapper.CmpnyMapper;
import kr.pe.inface.hub.service.cmpny.mapper.CmpnyUserMapper;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyVO;
import kr.pe.inface.hub.service.cmpny.vo.WorkSiteVO;
import kr.pr.inface.framework.web.BaseService;

@Service
public class CmpnyService extends BaseService {

	@Autowired
	private CmpnyMapper cmpnyMapper;

	//	@Autowired
	//	private CmpnyMapperTrx cmpnyMapperTrx;

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
		// TODO 테스트용이므로..나중에 지우자.
		// TODO 테스트용이므로..나중에 지우자.
		// 건설사_현장 사용자인 경우, 현장목록을 조회.
		// TODO 코드값을 상수로 정의해야 할텐데..
		for (CmpnyUserVO vo : userList) {
			if ("12".equals(vo.getUserTypeCd())) {
				List<WorkSiteVO> siteList = cmpnyUserMapper.getCmpnyUserSiteList(vo.getCmpnyUserId());
				if (siteList == null || siteList.size() == 0) {
				} else {
					Map<String, WorkSiteVO> workSiteMap = new HashMap<String, WorkSiteVO>();
					for (WorkSiteVO v : siteList) {
						workSiteMap.put(v.getWorkSiteId(), v);
					}
					vo.setWorkSiteMap(workSiteMap);
				}
			}
		}

		return userList;
	};

	/**
	 * 업체 정보 조회
	 *
	 * @param cmpnyId
	 * @return
	 */
	public CmpnyVO getCmpny(String cmpnyId) {
		//		if ((new Date()).getTime() % 2 == 1) {
		//			CmpnyVO v = new CmpnyVO();
		//			v.setCmpnyId(cmpnyId);
		//			System.out.println(cmpnyMapperTrx.updateCmpny(v));
		//			System.out.println("=================================== cache evict =========================");
		//		}
		return cmpnyMapper.getCmpny(cmpnyId);
	}

	/**
	 * 작업현장 정보 조회
	 *
	 * @param workSiteId
	 * @return
	 */
	public WorkSiteVO getWorkSite(String workSiteId) {
		return cmpnyMapper.getWorkSite(workSiteId);
	}

}
