package kr.pe.inface.hub.service.matrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.inface.hub.service.matrl.mapper.MatrlClmMapper;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmVO;
import kr.pr.inface.framework.web.BaseService;

@Service
public class MatrlClmService extends BaseService {

	@Autowired
	private MatrlClmMapper matrlClmMapper;

	/**
	 * 자재청구목록 조회
	 *
	 * @param cmpnyId    필수, 업체 조건 설정
	 * @param workSiteId 값을 지정하면, 현장 조건 설정
	 * @param clmStatCd  값을 지정하면, 상태 조건 설정
	 * @param clmDt      값을 지정하면, 청구일자 조건 설정
	 * @return
	 */
	public List<MatrlClmVO> getMatrlClmList(String cmpnyId, String workSiteId, String clmStatCd, String clmDt) {
		// TODO 서비스객체에서.. 유저의 role 따라 파라미터를 조정하도록 하는게 나을지.. controller 에서 하는게 나을지..
		MatrlClmVO vo = new MatrlClmVO();
		vo.setCmpnyId(cmpnyId);
		vo.setWorkSiteId(workSiteId);
		vo.setClmStatCd(clmStatCd);
		vo.setClmDt(clmDt);

		return matrlClmMapper.getMatrlClmList(vo);
	}

}
