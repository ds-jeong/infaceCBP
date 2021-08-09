package kr.co.inface.hub.service.matrl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.inface.hub.service.matrl.vo.MatrlClmAprvVO;
import kr.co.inface.hub.service.matrl.vo.MatrlClmFileVO;
import kr.co.inface.hub.service.matrl.vo.MatrlClmVO;

@Mapper
public interface MatrlClmMapper {

	/**
	 * 자재청구목록 조회
	 *
	 * @param cmpnyId    필수, 업체 조건 설정
	 * @param workSiteId 값을 지정하면, 현장 조건 설정
	 * @param clmStatCd  값을 지정하면, 상태 조건 설정
	 * @param clmDt      값을 지정하면, 청구일자 조건 설정
	 * @return
	 */
	public List<MatrlClmVO> getMatrlClmList(MatrlClmVO vo);

	/**
	 * 자재청구 조회
	 *
	 * @param matrlClmNo
	 * @return
	 */
	public MatrlClmVO getMatrlClm(String matrlClmNo);

	/**
	 * 자재청구상세 목록 조회
	 *
	 * @param matrlClmNo
	 * @return
	 */
	public List<MatrlClmVO> getMatrlClmDtlList(String matrlClmNo);

	/**
	 * 자재청구승인 목록 조회
	 *
	 * @param matrlClmNo
	 * @return
	 */
	public List<MatrlClmAprvVO> getMatrlClmAprvList(String matrlClmNo);

	/**
	 * 자재청구파일 목록 조회
	 *
	 * @param matrlClmNo
	 * @return
	 */
	public List<MatrlClmFileVO> getMatrlClmFileList(String matrlClmNo);

	/**
	 * 자재청구파일 다음 순번 조회. 신규생성시 seq 판단용
	 *
	 * @param matrlClmNo
	 * @return
	 */
	public int getMatrlClmFileSeqNext(String matrlClmNo);

}