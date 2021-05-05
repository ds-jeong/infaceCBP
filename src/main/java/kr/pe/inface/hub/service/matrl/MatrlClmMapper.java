package kr.pe.inface.hub.service.matrl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.matrl.vo.MatrlClmVO;

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

}