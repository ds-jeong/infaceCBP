package kr.pe.inface.hub.service.cmpny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pr.inface.framework.web.BaseService;

@Service
public class CmpnyService extends BaseService {

	@Autowired
	private CmpnyUserMapper mainMapper;

	/**
	 * @param id
	 * @return
	 */
	public CmpnyUserVO getCmpnyUser(String id) {
		return mainMapper.getCmpnyUser(id);
	};

}
