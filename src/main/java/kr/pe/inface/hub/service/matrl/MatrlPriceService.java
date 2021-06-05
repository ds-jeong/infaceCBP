package kr.pe.inface.hub.service.matrl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.inface.hub.config.security.Role.ROLE_NAME;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.matrl.mapper.MatrlPriceMapper;
import kr.pe.inface.hub.service.matrl.mapper.MatrlPriceMapperTrx;
import kr.pe.inface.hub.service.matrl.vo.MatrlPriceVO;
import kr.pr.inface.framework.web.BaseService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
	 * @return 타입 key, 타입별 목록을 value 로 반환
	 */
	public Map<String, List<MatrlPriceVO>> getCmpnyMatrlPriceVenReqMatrlListByTypeMap(String cmpnyId, String splCmpnyId, String aplStrtDt) {
		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setSplCmpnyId(splCmpnyId);
		paramVO.setAplStrtDt(aplStrtDt);

		// 구매타입 10:구매, 20:임대
		List<MatrlPriceVO> retList = matrlPriceMapper.getCmpnyMatrlPriceVenReqMatrlList(paramVO);
		List<MatrlPriceVO> retList_10 = null;
		List<MatrlPriceVO> retList_20 = null;
		for (MatrlPriceVO ret : retList) {
			switch (ret.getBuyTypeCd()) {
			case "10":
				if (retList_10 == null) {
					retList_10 = new ArrayList<MatrlPriceVO>();
				}
				retList_10.add(ret);
				break;
			case "20":
				if (retList_20 == null) {
					retList_20 = new ArrayList<MatrlPriceVO>();
				}
				retList_20.add(ret);
				break;
			}
		}

		//
		Map<String, List<MatrlPriceVO>> retListMap = new HashMap<String, List<MatrlPriceVO>>();
		retListMap.put("10", retList_10);
		retListMap.put("20", retList_20);
		return retListMap;
	}

	/**
	 * 업체자재단가 현재자재 목록
	 *
	 * @param cmpnyId
	 * @param splCmpnyId
	 * @param aplStrtDt
	 * @return 타입 key, 타입별 목록을 value 로 반환
	 */
	public Map<String, List<MatrlPriceVO>> getCmpnyMatrlPriceVenCurMatrlListByTypeMap(String cmpnyId, String splCmpnyId) {
		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setSplCmpnyId(splCmpnyId);

		// 구매타입 10:구매, 20:임대
		List<MatrlPriceVO> retList = matrlPriceMapper.getCmpnyMatrlPriceVenCurMatrlList(paramVO);
		List<MatrlPriceVO> retList_10 = null;
		List<MatrlPriceVO> retList_20 = null;
		for (MatrlPriceVO ret : retList) {
			switch (ret.getBuyTypeCd()) {
			case "10":
				if (retList_10 == null) {
					retList_10 = new ArrayList<MatrlPriceVO>();
				}
				retList_10.add(ret);
				break;
			case "20":
				if (retList_20 == null) {
					retList_20 = new ArrayList<MatrlPriceVO>();
				}
				// 임대_주기_코드 - 현재는 '일'주기만 존재. 건설업체기 자재별로 설정가능해야하지만, 일단은 하드코딩.
				// 10 - 일
				// 20 - 주
				// 30 - 월
				ret.setLeasePerdCd("10");
				retList_20.add(ret);
				break;
			}
		}

		//
		Map<String, List<MatrlPriceVO>> retListMap = new HashMap<String, List<MatrlPriceVO>>();
		retListMap.put("10", retList_10);
		retListMap.put("20", retList_20);
		return retListMap;
	}

	/**
	 * 단가요청 페이지 조회 - 건설업체용
	 * 상태에 따라 pageType 상세/등록/수정 처리 구분
	 *
	 * @param userVo
	 * @param splCmpnyId
	 * @param aplStrtDt
	 * @return ((MatrlPriceVO)modelMap.get("rst")).getPageType() = DTL/INS/UPD 비교
	 */
	public Map<String, Object> getCmpnyMatrlPriceVenReqDtlForCmpny(CmpnyUserVO userVo, String splCmpnyId, String aplStrtDt) throws Exception {
		// 권한 체크 - 건설업체-본사
		if ( !userVo.hasAnyAuths(ROLE_NAME.COMPANY)) {
			throw new Exception(ROLE_NAME.COMPANY + " 권한이 없습니다.");
		};

		//
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String cmpnyId = userVo.getCmpnyId();

		// 00 - 작성중
		// 10 - 확인요청(공급업체)
		// 15 - 확인요청(건설업체)
		// 20 - 확정
		// 90 - 요청취소
		// TODO 코드 상수 처리
		String pageType = null; // DTL/INS/UPD - 상세/등록/수정

		// 업체자재단가 업체가격요청 상세
		MatrlPriceVO rst = null;

		// 적용일자가 지정된 경우, 상세/수정
		if ( StringUtils.isNotBlank(aplStrtDt) ) {
			rst = this.getCmpnyMatrlPriceVenReqDtl(cmpnyId, splCmpnyId, aplStrtDt);
			if (rst == null) {
				// TODO 오류 처리..
				throw new Exception("조회된 요청내역이 없습니다.");
			}
			switch (rst.getReqStatCd()) {
			case "00":
			case "10":
			case "15":
			case "90": // 취소상태는 확정된 금액이 없으므로, 수정화면으로 조회만 가능. 수정버튼 불가
				pageType = "UPD";
				break;
			case "20":
			default:
				pageType = "DTL";
				break;
			}
		} else {
			String thisYearReqDt = DateFormatUtils.format(new Date(), "yyyyMMdd");
			String nextYearReqDt = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 1) + "0101";

			// 올해, 내년 데이터 있는지 체크하여 없는 경우를 등록처리
			if ((this.checkCmpnyMatrlPriceVenReqDtlAplStrtDt(cmpnyId, splCmpnyId, thisYearReqDt)) == null) {
				aplStrtDt = thisYearReqDt;
			} else if ((this.checkCmpnyMatrlPriceVenReqDtlAplStrtDt(cmpnyId, splCmpnyId, nextYearReqDt)) == null) {
				aplStrtDt = nextYearReqDt;
			} else {
				// TODO 오류 처리..
				throw new Exception("이미 처리내역이 있으므로, 신규등록할 수 없습니다.");
			}

			pageType = "INS";
			rst = new MatrlPriceVO();
			rst.setReqStatCd("00");
			rst.setAplStrtDt(aplStrtDt);
			rst.setAplEndDt(aplStrtDt.substring(0,4) + "1231");
			rst.setReqDt(thisYearReqDt);
		}
		rst.setPageType(pageType);
		modelMap.put("rst", rst);

		// 요청 메모 목록
		// 업체자재단가 업체가격요청 자재목록
		List<MatrlPriceVO> memoList = null;
		Map<String,List<MatrlPriceVO>> rstListMap = null;
		switch (pageType) {
		case "UPD":
			// 메모 목록 - 업데이트인 경우만 필요
			memoList = this.getCmpnyMatrlPriceVenReqMemoList(cmpnyId, splCmpnyId, aplStrtDt);
		case "DTL":
			// 요청 자재목록
			rstListMap = this.getCmpnyMatrlPriceVenReqMatrlListByTypeMap(cmpnyId, splCmpnyId, aplStrtDt);
			break;
		case "INS":
			// 현재 자재목록
			rstListMap = this.getCmpnyMatrlPriceVenCurMatrlListByTypeMap(cmpnyId, splCmpnyId);
			break;
		}
		modelMap.put("memoList", memoList);
		modelMap.put("rstList_10", rstListMap.get("10"));
		modelMap.put("rstList_20", rstListMap.get("20"));

		return modelMap;
	}

	/**
	 * 단가요청 페이지 조회 - 공급업체용
	 * 상태에 따라 pageType 상세/수정 처리 구분
	 *
	 * @param userVo
	 * @param splCmpnyId
	 * @param aplStrtDt
	 * @return ((MatrlPriceVO)modelMap.get("rst")).getPageType() = DTL/UPD 비교
	 */
	public Map<String, Object> getCmpnyMatrlPriceVenReqDtlForSplCmpny(CmpnyUserVO userVo, String cmpnyId, String aplStrtDt) throws Exception {
		// 권한 체크 - 공급업체
		if ( !userVo.hasAnyAuths(ROLE_NAME.VENDOR)) {
			throw new Exception(ROLE_NAME.VENDOR + " 권한이 없습니다.");
		};

		//
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String splCmpnyId = userVo.getCmpnyId();

		// 00 - 작성중
		// 10 - 확인요청(공급업체)
		// 15 - 확인요청(건설업체)
		// 20 - 확정
		// 90 - 요청취소
		// TODO 코드 상수 처리
		String pageType = null; // DTL/UPD - 상세/수정

		// 업체자재단가 업체가격요청 상세
		MatrlPriceVO rst = null;

		// 적용일자가 지정된 경우, 상세/수정
		if ( StringUtils.isNotBlank(aplStrtDt) ) {
			rst = this.getCmpnyMatrlPriceVenReqDtl(cmpnyId, splCmpnyId, aplStrtDt);
			if (rst == null) {
				// TODO 오류 처리..
				throw new Exception("조회된 요청내역이 없습니다.");
			}
			switch (rst.getReqStatCd()) {
			case "10":
			case "15":
				pageType = "UPD";
				break;
			case "20":
			default:
				pageType = "DTL";
				break;
			}
			//case "00": // 작성중,삭제상태는 공급업체가 조회불가
			//case "90":
		}
		rst.setPageType(pageType);
		modelMap.put("rst", rst);

		// 요청 메모 목록
		// 업체자재단가 업체가격요청 자재목록
		List<MatrlPriceVO> memoList = null;
		Map<String,List<MatrlPriceVO>> rstListMap = null;
		switch (pageType) {
		case "UPD":
			// 메모 목록 - 업데이트인 경우만 필요
			memoList = this.getCmpnyMatrlPriceVenReqMemoList(cmpnyId, splCmpnyId, aplStrtDt);
		case "DTL":
			// 요청 자재목록
			rstListMap = this.getCmpnyMatrlPriceVenReqMatrlListByTypeMap(cmpnyId, splCmpnyId, aplStrtDt);
			break;
		}
		modelMap.put("memoList", memoList);
		modelMap.put("rstList_10", rstListMap.get("10"));
		modelMap.put("rstList_20", rstListMap.get("20"));

		return modelMap;
	}

	// TODO 트랜잭션 처리. 일단은 선언트랜잭션 사용. 추후 aop 기반설정으로 전환?
	/**
	 * 업체자재단가 요청 등록 - 마스터,메모,가격
	 *
	 * @param userVo
	 * @param paramVO
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertCmpnyMatrlPriceReqInfo(CmpnyUserVO userVo, MatrlPriceVO paramVO) throws Exception {
		// 권한 체크 - 건설업체-본사
		if ( !userVo.hasAnyAuths(ROLE_NAME.COMPANY)) {
			throw new Exception(ROLE_NAME.COMPANY + " 권한이 없습니다.");
		};

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
	 * @param userVo
	 * @param paramVO
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateCmpnyMatrlPriceReqInfoForCmpny(CmpnyUserVO userVo, MatrlPriceVO paramVO) throws Exception {
		// 권한 체크 - 건설업체-본사
		if ( !userVo.hasAnyAuths(ROLE_NAME.COMPANY)) {
			throw new Exception(ROLE_NAME.COMPANY + " 권한이 없습니다.");
		};

		//
		String cmpnyId = userVo.getCmpnyId();
		String userId = userVo.getCmpnyUserId();

		// 요청 마스터 수정
		paramVO.setCmpnyId(cmpnyId);
		paramVO.setPrevReqStatCd("15"); // 확인요청(건설업체) 상태에서 수정한 후
		paramVO.setReqStatCd("10");     // 확인요청(공급업체) 상태로 변경한다.
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
	 * @param userVo
	 * @param paramVO
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateCmpnyMatrlPriceReqInfoForSplCmpny(CmpnyUserVO userVo, MatrlPriceVO paramVO) throws Exception {
		// 권한 체크 - 공급업체
		if ( !userVo.hasAnyAuths(ROLE_NAME.VENDOR)) {
			throw new Exception(ROLE_NAME.VENDOR + " 권한이 없습니다.");
		};

		//
		String splCmpnyId = userVo.getCmpnyId();
		String userId = userVo.getCmpnyUserId();

		// 요청 마스터 수정
		paramVO.setSplCmpnyId(splCmpnyId);
		paramVO.setPrevReqStatCd("10"); // 확인요청(공급업체) 상태에서 수정한 후
		paramVO.setReqStatCd("15");     // 확인요청(건설업체) 상태로 변경한다.
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

	/**
	 * 업체자재단가 요청 확정 처리 - 마스터,가격
	 *   * 요청에 대한 상태를 업데이트하고.. 확정된 가격을 실제 가격 테이블에 insert 한다.
	 *
	 * @param userVo
	 * @param cmpnyId
	 * @param aplStrtDt
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateCmpnyMatrlPriceReqInfoConfirm(CmpnyUserVO userVo, String cmpnyId, String aplStrtDt) throws Exception {
		// 권한 체크 - 공급업체
		if ( !userVo.hasAnyAuths(ROLE_NAME.VENDOR)) {
			throw new Exception(ROLE_NAME.VENDOR + " 권한이 없습니다.");
		};

		//
		String splCmpnyId = userVo.getCmpnyId();
		String userId = userVo.getCmpnyUserId();

		// 요청 마스터 확정 처리
		MatrlPriceVO paramVO = new MatrlPriceVO();
		paramVO.setCmpnyId(cmpnyId);;
		paramVO.setSplCmpnyId(splCmpnyId);
		paramVO.setAplStrtDt(aplStrtDt);
		paramVO.setPrevReqStatCd("10"); // 확인요청(공급업체) 상태에서
		paramVO.setReqStatCd("20");     // 확정 상태로 변경한다.
		paramVO.setRegpeId(userId);
		paramVO.setModpeId(userId);
		int rstCnt = matrlPriceMapperTrx.updateCmpnyMatrlPriceReqMst(paramVO);
		// 업데이트된 내역이 있어야 다음 단계로 넘어감.
		if ( rstCnt == 0 ) {
			throw new Exception("업체단가요청 확정가능한 마스터상태가 아닙니다.");
		}

		log.debug("updateCmpnyMatrlPriceReqInfoConfirm key" + paramVO);

		// 요청 가격목록 임시상태 -> 가격 테이블로 확정 이관
		int priceCnt = matrlPriceMapperTrx.insertCmpnyMatrlPriceFromReqByConfirm(paramVO);
		log.debug("insertCmpnyMatrlPriceFromReqByConfirm 건수 : " + priceCnt);

		// 확정이관 후, 요청 가격목록 확정 처리. 하나의 마스터에 나누어서 요청할 수도 있음.
		int priceReqCnt = matrlPriceMapperTrx.updateCmpnyMatrlPriceReqAllConfirm(paramVO);
		log.debug("updateCmpnyMatrlPriceReqAllConfirm 건수 : " + priceReqCnt);

		// 이관,확정 처리 건수가 다른 경우.
		if (priceCnt != priceReqCnt) {
			throw new Exception("이관, 확정 처리 건수가 다릅니다. 재시도 실패시에는 관리자에게 문의하세요.");
		}
	}

}
