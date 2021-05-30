package kr.pe.inface.hub.service.matrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.inface.hub.config.security.Role.ROLE_NAME;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.matrl.mapper.MatrlPriceMapper;
import kr.pe.inface.hub.service.matrl.mapper.MatrlPriceMapperTrx;
import kr.pe.inface.hub.service.matrl.vo.MatrlPriceVO;
import kr.pr.inface.framework.web.BaseService;

@Service
public class MatrlPriceService extends BaseService {

	@Autowired
	private MatrlPriceMapper matrlPriceMapper;

	@Autowired
	private MatrlPriceMapperTrx matrlPriceMapperTrx;

	/**
	 * 건설업체/공급업체 기준으로 계약된 단가업체 목록을 조회
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenList(String cmpnyId, String splCmpnyId) throws Exception {
		if (cmpnyId != null && splCmpnyId != null) {
			throw new Exception("건설업체,공급업체 내역을 동시에 조회할 수 없습니다.");
		}

		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setSplCmpnyId(splCmpnyId);

		return matrlPriceMapper.getCmpnyMatrlPriceVenList(paramVO);
	}

	/**
	 * 업체자재단가 업체가격요청 목록
	 *
	 * @param cmpnyId
	 * @param splCmpnyId
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenDtlList(String cmpnyId, String splCmpnyId) {
		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setSplCmpnyId(splCmpnyId);

		return matrlPriceMapper.getCmpnyMatrlPriceVenDtlList(paramVO);
	}

	/**
	 * 업체자재단가 요청 마스터 조회
	 *
	 * @param cmpnyId
	 * @param splCmpnyId
	 * @param aplStrtDt
	 * @return
	 */
	public MatrlPriceVO getCmpnyMatrlPriceVenReqDtl(String cmpnyId, String splCmpnyId, String aplStrtDt) {
		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setSplCmpnyId(splCmpnyId);
		paramVO.setAplStrtDt(aplStrtDt);

		return matrlPriceMapper.getCmpnyMatrlPriceVenReqDtl(paramVO);
	}

	/**
	 * 업체자재단가 요청이 aplStrtDt 가 속한 연도 내역이 있는지 체크
	 *
	 * @param cmpnyId
	 * @param splCmpnyId
	 * @param aplStrtDt
	 * @return
	 */
	public MatrlPriceVO checkCmpnyMatrlPriceVenReqDtlAplStrtDt(String cmpnyId, String splCmpnyId, String aplStrtDt) {
		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setSplCmpnyId(splCmpnyId);
		paramVO.setAplStrtDt(aplStrtDt);

		return matrlPriceMapper.checkCmpnyMatrlPriceVenReqDtlAplStrtDt(paramVO);
	}


	/**
	 * 업체자재단가 요청메모 목록
	 *
	 * @param cmpnyId
	 * @param splCmpnyId
	 * @param aplStrtDt
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenReqMemoList(String cmpnyId, String splCmpnyId, String aplStrtDt) {
		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setSplCmpnyId(splCmpnyId);
		paramVO.setAplStrtDt(aplStrtDt);

		return matrlPriceMapper.getCmpnyMatrlPriceVenReqMemoList(paramVO);
	}

	/**
	 * 업체자재단가 요청자재 목록
	 *
	 * @param cmpnyId
	 * @param splCmpnyId
	 * @param aplStrtDt
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenReqMatrlList(String cmpnyId, String splCmpnyId, String aplStrtDt) {
		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setSplCmpnyId(splCmpnyId);
		paramVO.setAplStrtDt(aplStrtDt);

		return matrlPriceMapper.getCmpnyMatrlPriceVenReqMatrlList(paramVO);
	}

	/**
	 * 업체자재단가 현재자재 목록
	 *
	 * @param cmpnyId
	 * @param splCmpnyId
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenCurMatrlList(String cmpnyId, String splCmpnyId) {
		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setSplCmpnyId(splCmpnyId);

		return matrlPriceMapper.getCmpnyMatrlPriceVenCurMatrlList(paramVO);
	}

	// TODO 트랜잭션 처리. 일단은 선언트랜잭션 사용. 추후 aop 기반설정으로 전환?
	/**
	 * 업체자재단가 요청 등록 - 마스터,메모,가격
	 *
	 * @param cmpnyId
	 * @param paramVO
	 * @param userId
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertCmpnyMatrlPriceReqInfo(CmpnyUserVO userVo, MatrlPriceVO paramVO) throws Exception {
		// 단가요청은 건설업체-본사 사용자만 등록할 수 있음.
		// TODO 권한체크로직 만들어야 함.. 아래처럼 안됨..
//		Collection<? extends GrantedAuthority> roles = userVo.getAuthorities();
//		if (!roles.contains(new Role(ROLE_NAME.COMPANY))) {
//			throw new Exception("업체단가요청 처리권한이 없습니다.");
//		}

		//
		String cmpnyId = userVo.getCmpnyId();
		String userId = userVo.getCmpnyUserId();

		// 요청 마스터 등록
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setReqStatCd("10"); // 건설업체가 등록하므로, 상태는 확인요청(공급업체)로 등록한다.
		paramVO.setRegpeId(userId);
		paramVO.setModpeId(userId);
		matrlPriceMapperTrx.insertCmpnyMatrlPriceReqMst(paramVO);

		// 요청 메모 등록
		matrlPriceMapperTrx.insertCmpnyMatrlPriceReqMemo(paramVO);

		// 요청 가격목록 등록
		for ( MatrlPriceVO priceLiVO : paramVO.getMatrlPriceList() ) {
			priceLiVO.setCmpnyId(cmpnyId);
			priceLiVO.setSplCmpnyId(paramVO.getSplCmpnyId());
			priceLiVO.setAplStrtDt(paramVO.getAplStrtDt());
			priceLiVO.setReqStatCd("10"); // 진행중
			priceLiVO.setReqDt(paramVO.getReqDt()); // 요청 마스터의 요청일자 사용
			priceLiVO.setRegpeId(userId);
			priceLiVO.setModpeId(userId);
			matrlPriceMapperTrx.insertCmpnyMatrlPriceReq(priceLiVO);
		}
	}

	/**
	 * 업체자재단가 요청 수정. 건설업체용 - 마스터,가격
	 *   메모는 수정없이 추가만 있음.
	 *
	 * @param cmpnyId
	 * @param paramVO
	 * @param userId
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateCmpnyMatrlPriceReqInfoCmpny(CmpnyUserVO userVo, MatrlPriceVO paramVO) throws Exception {
		// TODO 권한체크로직 만들어야 함.. 아래처럼 안됨..
//		Collection<? extends GrantedAuthority> roles = userVo.getAuthorities();
//		if (!roles.contains(new Role(ROLE_NAME.COMPANY))) {
//			throw new Exception("업체단가요청 처리권한이 없습니다.");
//		}

		// 업데이트된 내역이 있어야 다음 단계로 넘어감.

		//
		String cmpnyId = userVo.getCmpnyId();
		String userId = userVo.getCmpnyUserId();

		// 요청 마스터 수정
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setReqStatCd("10"); // 건설업체 수정 후, 상태는 확인요청(공급업체)로 등록한다.
		paramVO.setRegpeId(userId);
		paramVO.setModpeId(userId);
		int rstCnt = matrlPriceMapperTrx.updateCmpnyMatrlPriceReqMst(paramVO);
		// 업데이트된 내역이 있어야 다음 단계로 넘어감.
		if ( rstCnt == 0 ) {
			throw new Exception("업체단가요청 수정가능한 마스터상태가 아닙니다.");
		}

		// 요청 메모 등록
		matrlPriceMapperTrx.insertCmpnyMatrlPriceReqMemo(paramVO);

		// 요청 가격목록 수정
		for ( MatrlPriceVO priceLiVO : paramVO.getMatrlPriceList() ) {
			priceLiVO.setCmpnyId(cmpnyId);
			priceLiVO.setSplCmpnyId(paramVO.getSplCmpnyId());
			priceLiVO.setAplStrtDt(paramVO.getAplStrtDt());
			priceLiVO.setModpeId(userId);
			matrlPriceMapperTrx.updateCmpnyMatrlPriceReqCmpny(priceLiVO);
		}
	}

	/**
	 * 업체자재단가 요청 수정. 공급업체용 - 마스터,가격
	 *   메모는 수정없이 추가만 있음.
	 *
	 * @param cmpnyId
	 * @param paramVO
	 * @param userId
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateCmpnyMatrlPriceReqInfoSplCmpny(CmpnyUserVO userVo, MatrlPriceVO paramVO) throws Exception {
		// TODO 권한체크로직 만들어야 함.. 아래처럼 안됨..
//		Collection<? extends GrantedAuthority> roles = userVo.getAuthorities();
//		if (!roles.contains(new Role(ROLE_NAME.VENDOR))) {
//			throw new Exception("업체단가요청 처리권한이 없습니다.");
//		}

		//
		String splCmpnyId = userVo.getCmpnyId();
		String userId = userVo.getCmpnyUserId();

		// 요청 마스터 수정
		paramVO.setSplCmpnyId(splCmpnyId);
		paramVO.setReqStatCd("15"); // 공급업체 수정 후, 상태는 확인요청(건설업체)로 등록한다.
		paramVO.setRegpeId(userId);
		paramVO.setModpeId(userId);
		int rstCnt = matrlPriceMapperTrx.updateCmpnyMatrlPriceReqMst(paramVO);
		// 업데이트된 내역이 있어야 다음 단계로 넘어감.
		if ( rstCnt == 0 ) {
			throw new Exception("업체단가요청 수정가능한 마스터상태가 아닙니다.");
		}

		// 요청 메모 등록
		matrlPriceMapperTrx.insertCmpnyMatrlPriceReqMemo(paramVO);

		// 요청 가격목록 수정
		for ( MatrlPriceVO priceLiVO : paramVO.getMatrlPriceList() ) {
			priceLiVO.setCmpnyId(paramVO.getCmpnyId());
			priceLiVO.setSplCmpnyId(splCmpnyId);
			priceLiVO.setAplStrtDt(paramVO.getAplStrtDt());
			priceLiVO.setModpeId(userId);
			matrlPriceMapperTrx.updateCmpnyMatrlPriceReqSplCmpny(priceLiVO);
		}
	}

}
