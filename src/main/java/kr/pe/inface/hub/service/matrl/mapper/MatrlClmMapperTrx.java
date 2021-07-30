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
	 * 자재청구상세 등록
	 *
	 * @param paramVo
	 * @return
	 */
	public int insertMatrlClmDtl(MatrlClmVO paramVo);

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