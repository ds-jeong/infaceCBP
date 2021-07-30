package kr.pe.inface.hub.service.matrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.inface.hub.config.security.Role.ROLE_NAME;
import kr.pe.inface.hub.service.cmpny.mapper.CmpnyMapper;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserSiteVO;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.pe.inface.hub.service.cmpny.vo.WorkSiteVO;
import kr.pe.inface.hub.service.matrl.mapper.MatrlClmMapper;
import kr.pe.inface.hub.service.matrl.mapper.MatrlClmMapperTrx;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmAprvVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmFileVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmVO;
import kr.pr.inface.framework.web.BaseService;

@Service
public class MatrlClmService extends BaseService {

	@Autowired
	private CmpnyMapper cmpnyMapper;

	@Autowired
	private MatrlClmMapper matrlClmMapper;

	@Autowired
	private MatrlClmMapperTrx matrlClmMapperTrx;

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

		List<MatrlClmVO> retList = matrlClmMapper.getMatrlClmList(vo);
		if (retList != null && retList.size() > 0) {
			for (MatrlClmVO v : retList) {
				v.setAprvList(matrlClmMapper.getMatrlClmAprvList(v.getMatrlClmNo()));
			}
		}

		return retList;
	}

	/**
	 * 자재청구 조회 권한 체크. 권한없으면 예외 발생
	 *
	 * @param userVo
	 * @param matrlClm
	 * @throws Exception
	 */
	public void checkMatrlClmDtl(CmpnyUserVO userVo, MatrlClmVO matrlClm) throws Exception {
		String cmpnyId = userVo.getCmpnyId();

		// 본사이면 전체 조회, 현장이면 관리현장만 조회
		if (matrlClm == null) {
			throw new Exception("조회된 자재청구 데이터가 없습니다.");
		} else if (!((cmpnyId != null && cmpnyId.equals(matrlClm.getCmpnyId())) && (userVo
				.hasAnyAuths(ROLE_NAME.COMPANY)
				|| (userVo.hasAnyAuths(ROLE_NAME.COMPANY_SITE) && userVo.hasWorkSite(matrlClm.getWorkSiteId()))))) {
			throw new Exception("자재청구 조회권한이 없습니다.");
		}
	}

	/**
	 * 자재청구 상세 조회
	 *
	 * @param userVo
	 * @param matrlClmNO
	 * @return
	 * @throws Exception
	 */
	public MatrlClmVO getMatrlClm(CmpnyUserVO userVo, String matrlClmNo) throws Exception {
		// 자재청구 마스터 조회
		MatrlClmVO matrlClm = matrlClmMapper.getMatrlClm(matrlClmNo);

		// 조회 권한 체크
		this.checkMatrlClmDtl(userVo, matrlClm);

		// 자재,첨부,승인 목록 조회
		matrlClm.setDtlList(matrlClmMapper.getMatrlClmDtlList(matrlClmNo));
		matrlClm.setFileList(matrlClmMapper.getMatrlClmFileList(matrlClmNo));
		matrlClm.setAprvList(matrlClmMapper.getMatrlClmAprvList(matrlClmNo));

		return matrlClm;
	}

	/**
	 * 자재청구 등록/수정 페이지 조회
	 *
	 * @param userVo
	 * @param matrlClmNo
	 * @return
	 * @throws Exception
	 */
	public MatrlClmVO getMatrlClmUpdPage(CmpnyUserVO userVo, String matrlClmNo) throws Exception {
		MatrlClmVO retVo = null;

		if (matrlClmNo == null) {
			// 등록페이지 - 기본값 설정
			retVo = new MatrlClmVO();
			retVo.setClmDt(FastDateFormat.getInstance("yyyyMMdd").format(new Date())); // 청구일자 - 현재일자
			retVo.setClmStatCd("10"); // 청구상태코드 - 작성중
			retVo.setClmChargrId(userVo.getCmpnyUserId());
			retVo.setClmChargrNm(userVo.getUserNm());
			retVo.setClmChargrLoginId(userVo.getLoginId());

			// TODO 현장 담당자 권한이 있는지 체크. 현재는 별도 기준이 없으므로 결재템플릿의 첫 승인자와 매핑 체크
			// 로그인유저의 현장 목록 조회
			List<WorkSiteVO> siteList = new ArrayList<WorkSiteVO>();
			Map<String, CmpnyUserSiteVO> userSiteMap = userVo.getWorkSiteMap();
			if (userSiteMap != null) {
				for (CmpnyUserSiteVO cusVo : userSiteMap.values() ) {
					WorkSiteVO wsVo = cmpnyMapper.getWorkSite(cusVo.getWorkSiteId());
					if (wsVo != null && userVo.getCmpnyUserId().equals(wsVo.getClmAprvrId1())) {
						// 첫 승인자로 지정된 경우만.
						siteList.add(wsVo);
					}
				}
			}

			if (siteList.size() == 0) {
				throw new Exception("관리 현장이 존재하지 않거나, 청구권한이 없습니다.");
			}
			retVo.setSiteList(siteList);
		} else {
			// 수정페이지 - 상세조회
			retVo = this.getMatrlClm(userVo, matrlClmNo);
		}

		return retVo;
	}

	/**
	 * 자재청구 등록 처리
	 *
	 * @param userVo
	 * @param paramVo
	 * @return 등록된 자재청구번호
	 */
	@Transactional(rollbackFor = Exception.class)
	public String insertMatrlClm(CmpnyUserVO userVo, MatrlClmVO paramVo) throws Exception {

		// TODO 등록 권한 체크
//		rthis.checkMatrlClmDtl(userVo, paramVo);

		// TODO 저장상태에 따른 체크 필요.. 임시저장은 검증없음. 승인요청이면 모두 체크..등

		String nowDts = DateFormatUtils.format(new Date(), "yyMMdd-SSS");
		String matrlClmNo = nowDts.substring(0, 6) + RandomUtils.nextInt(10000, 99999) + nowDts.substring(7, 10); // TODO 청구번호 생성 룰?
		String cmpnyId = userVo.getCmpnyId();
		String userId = userVo.getCmpnyUserId();
		String workSiteId = paramVo.getWorkSiteId();
		String clmStatCd = paramVo.getClmStatCd();
		boolean isTemp = !"20".equals(clmStatCd); // 임시저장 여부.
		List<MatrlClmVO> clmDtlList = paramVo.getDtlList();
		List<MatrlClmFileVO> clmFileList = paramVo.getFileList();

		// 청구 마스터 등록
		paramVo.setMatrlClmNo(matrlClmNo);
		paramVo.setDtlQty(clmDtlList.size());
		paramVo.setCmpnyId(cmpnyId);
		paramVo.setClmChargrId(userId); // 등록자가 담당자.
		paramVo.setUserId(userId);
		matrlClmMapperTrx.insertMatrlClm(paramVo);

		// 청구 자재 목록 등록 - 확정요청시 청구상세번호 생성.
		if ( clmDtlList != null && clmDtlList.size() > 0 ) {
			for ( MatrlClmVO v : clmDtlList ) {
				v.setMatrlClmNo(matrlClmNo);
				v.setMatrlClmDtlNo(null); // TODO 계속 수정될수 있는데... 청구완료되는 시점에 생성? 그냥 순서대로?
				v.setClmDtlStatCd("20"); // 청구상세코드 '완료'
				v.setUserId(userId);
				matrlClmMapperTrx.insertMatrlClmDtl(v);
			}
		}

		// 청구 승인자 목록 등록
		WorkSiteVO workSiteVo = cmpnyMapper.getWorkSite(workSiteId);
		// 청구 승인자 1
		MatrlClmAprvVO clmAprvVo1 = new MatrlClmAprvVO();
		clmAprvVo1.setMatrlClmNo(matrlClmNo);
		clmAprvVo1.setAprvSeq(1);
		clmAprvVo1.setAprvrId(workSiteVo.getClmAprvrId1());
		clmAprvVo1.setAprvStatCd(isTemp ? "00" : "20"); // 확정등록시 본인으로 결재자 승인처리
		clmAprvVo1.setUserId(userId);
		matrlClmMapperTrx.insertMatrlClmAprv(clmAprvVo1);
		// 청구 승인자 2
		MatrlClmAprvVO clmAprvVo2 = new MatrlClmAprvVO();
		clmAprvVo2.setMatrlClmNo(matrlClmNo);
		clmAprvVo2.setAprvSeq(2);
		clmAprvVo2.setAprvrId(workSiteVo.getClmAprvrId2());
		clmAprvVo2.setAprvStatCd(isTemp ? "00" : "10"); // 승인요청 - 다음 승인 차례.
		clmAprvVo2.setUserId(userId);
		matrlClmMapperTrx.insertMatrlClmAprv(clmAprvVo2);
		// 청구 승인자 3
		MatrlClmAprvVO clmAprvVo3 = new MatrlClmAprvVO();
		clmAprvVo3.setMatrlClmNo(matrlClmNo);
		clmAprvVo3.setAprvSeq(3);
		clmAprvVo3.setAprvrId(workSiteVo.getClmAprvrId3());
		clmAprvVo3.setAprvStatCd("00"); // 대기 - 아직 차례는 아님.
		clmAprvVo3.setUserId(userId);
		matrlClmMapperTrx.insertMatrlClmAprv(clmAprvVo3);
		// TODO 발주 승인정보를 같이 기록할 것인지..

		// 청구 첨부 목록 등록
		if ( clmFileList != null && clmFileList.size() > 0 ) {
			int seq = 1;
			for ( MatrlClmFileVO v : clmFileList ) {
				v.setMatrlClmNo(matrlClmNo);
				v.setFileSeq(seq++);
				v.setFileNm(v.getFilePath()); // TODO 파일명 별도 저장.
				v.setUseYn("Y");
				v.setUserId(userId);
				matrlClmMapperTrx.insertMatrlClmFile(v);
			}
		}

		return matrlClmNo;
	}


	/**
	 * 자재청구 승인 처리
	 *
	 *  - 해당 내역을 승인처리하고 다음 순번을 승인요청 처리함.
	 *  - 모두 승인처리된 경우, 자재청구의 상태를 청구완료 상태로 업데이트
	 *
	 * @param userVo
	 * @param matrlClmNo
	 * @param aprvSeq
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateMatrlClmAprv(CmpnyUserVO userVo, String matrlClmNo, int aprvSeq) throws Exception {
		String userId = userVo.getCmpnyUserId();
		MatrlClmAprvVO curAprv = null;
		MatrlClmAprvVO nextAprv = null;
		int restAprvCnt = 0;

		MatrlClmVO matrlClm = matrlClmMapper.getMatrlClm(matrlClmNo);
		List<MatrlClmAprvVO> aprvList = matrlClmMapper.getMatrlClmAprvList(matrlClmNo);
		if (aprvList != null && aprvList.size() > 0) {
			for (MatrlClmAprvVO v : aprvList) {
				if (v.getAprvSeq() == aprvSeq && v.getAprvrId().equals(userId)) {
					// 본인이 승인자인지 체크
					curAprv = v;
				} else if (curAprv != null && nextAprv == null && "00".equals(v.getAprvStatCd())) {
					// 현재 승인정보가 설정되어 있으면, 다음번 승인정보 체크 ( 대기(00)->승인요청(10) )
					nextAprv = v;
				}
				// 승인안된 건 수.
				if (!"20".equals(v.getAprvStatCd())) {
					restAprvCnt++;
				}
			}
		}

		//
		if ( matrlClm == null ||  !"20".equals(matrlClm.getClmStatCd()) ) {
			throw new Exception(String.format("자재청구 정보가 정확하지 않습니다. %s,%s", matrlClmNo, matrlClm == null ? "" : matrlClm.getClmStatCd()));
		} else if ( curAprv == null || !"10".equals(curAprv.getAprvStatCd()) ) {
			throw new Exception(String.format("승인정보가 정확하지 않습니다. %s,%s,%s", matrlClmNo, aprvSeq, curAprv == null ? "" : curAprv.getAprvStatCd()));
		} else {
			// 현재 승인정보 업데이트 ( 승인요청 -> 승인 )
			curAprv.setAprvStatCd("20"); // 승인
			curAprv.setRemark(null);
			curAprv.setUserId(userId);
			if ( matrlClmMapperTrx.updateMatrlClmAprvStat(curAprv) < 1 ) {
				throw new Exception(String.format("승인처리에 실패하였습니다. %s,%s", matrlClmNo, aprvSeq));
			}

			// 다음 승인정보 업데이트 ( 대기 -> 승인요청 )
			if ( nextAprv != null ) {
				nextAprv.setAprvStatCd("10"); // 승인요청
				nextAprv.setRemark(null);
				nextAprv.setUserId(userId);
				matrlClmMapperTrx.updateMatrlClmAprvStat(nextAprv);
			}
			// 잔여 승인건수가 없으면 자재청구 상태 업데이트 ( 승인중 -> 승인완료 )
			if ( restAprvCnt == 1 ) {
				// 이번 승인으로, 이후 승인잔여건이 없는 경우.
				matrlClm.setClmStatCd("40"); // 승인완료
				matrlClm.setUserId(userId);
				matrlClmMapperTrx.updateMatrlClmStat(matrlClm);
			}
		}
	}

	// TODO 승인반려..는 어떻게 할것인지 비지니스 로직 정리가 필요함.



}
