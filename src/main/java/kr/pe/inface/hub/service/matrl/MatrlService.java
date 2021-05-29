package kr.pe.inface.hub.service.matrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.inface.hub.service.matrl.mapper.MatrlClmMapper;
import kr.pe.inface.hub.service.matrl.mapper.MatrlMapper;
import kr.pe.inface.hub.service.matrl.mapper.MatrlMapperTrx;
import kr.pe.inface.hub.service.matrl.mapper.MatrlPriceMapper;
import kr.pe.inface.hub.service.matrl.mapper.MatrlPriceMapperTrx;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlCntrtVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlPriceVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlVO;
import kr.pr.inface.framework.web.BaseService;

@Service
public class MatrlService extends BaseService {

	@Autowired
	private MatrlMapper matrlMapper;

	@Autowired
	private MatrlMapperTrx matrlMapperTrx;

	@Autowired
	private MatrlPriceMapper matrlPriceMapper;

	@Autowired
	private MatrlPriceMapperTrx matrlPriceMapperTrx;

	@Autowired
	private MatrlClmMapper matrlClmMapper;

	/**
	 * 자재카테고리,품목 목록 조회
	 *
	 * @param cmpnyId 값을 지정하면, 해당 업체가 설정한 품목여부가 조회됨
	 * @return
	 */
	public List<MatrlVO> getMatrlItemList(String cmpnyId) {
		return matrlMapper.getMatrlItemList(cmpnyId);
	};

	/**
	 * 자재폼목 사용 설정 처리
	 *
	 * @param userId      사용자ID
	 * @param cmpnyId     업체ID
	 * @param matrlItemId 자재품목ID
	 * @param buyTypeCd   구매타입코드
	 * @param useYn       사용여부
	 */
	public void useMyMatrlItem(String userId
			, String cmpnyId
			, String matrlItemId
			, String buyTypeCd
			, String useYn) {

		MatrlVO paramVO = new MatrlVO();
		paramVO.setUserId(userId);
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setMatrlItemId(matrlItemId);
		paramVO.setBuyTypeCd(buyTypeCd);
		paramVO.setUseYn(useYn);

		matrlMapperTrx.useMyMatrlItem(paramVO);
	}

	/**
	 * 업체 자재품목 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlVO> getCmpnyMatrlItemList(String cmpnyId) {
		return matrlMapper.getCmpnyMatrlItemList(cmpnyId);
	}

	/**
	 * 업체 자재 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlVO> getCmpnyMatrlList(String cmpnyId) {
		return matrlMapper.getCmpnyMatrlList(cmpnyId);
	}

	/**
	 * 자재품목 공급업체 계약 목록
	 *
	 * @param cmpnyId
	 * @param matrlItemId
	 * @param buyTypeCd
	 * @return
	 */
	public List<MatrlCntrtVO> getMatrlItemCntrtList(String cmpnyId, String matrlItemId, String buyTypeCd) {
		MatrlCntrtVO paramVO = new MatrlCntrtVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setMatrlItemId(matrlItemId);
		paramVO.setBuyTypeCd(buyTypeCd);
		return matrlMapper.getMatrlItemCntrtList(paramVO);
	}

	/**
	 * 자재품목 공급업체 계약 설정
	 *
	 * @param userId      사용자ID
	 * @param cmpnyId     업체ID
	 * @param matrlItemId 자재품목ID
	 * @param splCmpnyId  공급업체ID
	 * @param buyTypeCd   구매타입코드
	 * @param cntrtStatCd 계약상태코드
	 */
	public void updMatrlItemCntrtStat(String userId
			, String cmpnyId
			, String matrlItemId
			, String splCmpnyId
			, String buyTypeCd
			, String cntrtStatCd) {

		MatrlCntrtVO paramVO = new MatrlCntrtVO();
		paramVO.setUserId(userId);
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setMatrlItemId(matrlItemId);
		paramVO.setSplCmpnyId(splCmpnyId);
		paramVO.setBuyTypeCd(buyTypeCd);
		paramVO.setCntrtStatCd(cntrtStatCd);

		matrlMapperTrx.updMatrlItemCntrtStat(paramVO);
	}

	/**
	 * 업체 자재단가 공급업체 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenList(String cmpnyId) {
		return matrlPriceMapper.getCmpnyMatrlPriceVenList(cmpnyId);
	}

	/**
	 * 업체 자재단가 공급업체 가격요청 목록
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
	 * 업체 자재단가 공급업체 요청 상세
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
	 * 업체 자재단가 요청이 aplStrtDt 가 속한 연도 내역이 있는지 체크
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
	 * 업체 자재단가 공급업체 요청 메모 목록
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
	 * 업체 자재단가 공급업체 요청 자재목록
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
	 * 업체 자재단가 공급업체 현재 자재목록
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
	 * 업체단가 요청 등록 - 마스터,메모,가격
	 *
	 * @param cmpnyId
	 * @param paramVO
	 * @param userId
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertCmpnyMatrlPriceReqInfo(String cmpnyId, MatrlPriceVO paramVO, String userId) {
		String reqStatCd = "10"; // 건설업체가 등록하므로, 상태는 확인요청(공급업체)로 등록한다.

		// TODO 등록처리시 예외처리 추가.

		// 요청 마스터 등록
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setReqStatCd(reqStatCd);
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
			priceLiVO.setReqStatCd(reqStatCd);
			priceLiVO.setReqDt(paramVO.getReqDt()); // 요청 마스터의 요청일자 사용
			priceLiVO.setRegpeId(userId);
			priceLiVO.setModpeId(userId);
			matrlPriceMapperTrx.insertCmpnyMatrlPriceReq(priceLiVO);
		}
	}










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
