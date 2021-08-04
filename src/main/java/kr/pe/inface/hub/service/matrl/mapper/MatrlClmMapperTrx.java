package kr.pe.inface.hub.service.matrl.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.matrl.vo.MatrlClmAprvVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmFileVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlClmVO;

@Mapper
public interface MatrlClmMapperTrx {

	/**
	 * 자재청구 등록
	 *
	 * @param paramVo
	 * @return
	 */
	public int insertMatrlClm(MatrlClmVO paramVo);

	/**
	 * 자재청구 상태 수정
	 *
	 * @param paramVo
	 * @return
	 */
	public int updateMatrlClmStat(MatrlClmVO paramVo);


	/**
	 * 자재청구 정보 수정
	 *  - 작성중(10),수정요청(30) 및 등록자만 가능
	 *  - 수정불가한 항목이 있음. ex. workSiteId
	 *
	 * @param paramVo
	 * @return
	 */
	public int updateMatrlClmInfo(MatrlClmVO paramVo);

	/**
	 * <!-- 자재청구상세 등록/수정 upsert -->
	 *
	 * @param paramVo
	 * @return
	 */
	public int upsertMatrlClmDtl(MatrlClmVO paramVo);

	/**
	 * 자재청구상세 삭제
	 *
	 * @param paramVo
	 * @return
	 */
	public int deleteMatrlClmDtl(MatrlClmVO paramVo);

	/**
	 * 자재청구승인 등록
	 *
	 * @param paramVo
	 * @return
	 */
	public int insertMatrlClmAprv(MatrlClmAprvVO paramVo);

	/**
	 * 자재청구승인 상태 수정
	 *
	 * @param paramVo
	 * @return
	 */
	public int updateMatrlClmAprvStat(MatrlClmAprvVO paramVo);

	/**
	 * 자재청구파일 등록
	 *
	 * @param paramVo
	 * @return
	 */
	public int insertMatrlClmFile(MatrlClmFileVO paramVo);

}