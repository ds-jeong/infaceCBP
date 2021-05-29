package kr.pe.inface.hub.service.matrl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.matrl.vo.MatrlCntrtVO;
import kr.pe.inface.hub.service.matrl.vo.MatrlVO;

@Mapper
public interface MatrlMapper {

	/**
	 * 자재카테고리,품목 목록 조회
	 *
	 * @param cmpnyId 값을 지정하면, 해당 업체가 설정한 품목여부가 조회됨
	 * @return
	 */
	public List<MatrlVO> getMatrlItemList(String cmpnyId);

	/**
	 * 업체별 자재품목 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlVO> getCmpnyMatrlItemList(String cmpnyId);

	/**
	 * 업체별 자재 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlVO> getCmpnyMatrlList(String cmpnyId);

	/**
	 * 자재품목 공급업체 계약 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlCntrtVO> getMatrlItemCntrtList(MatrlCntrtVO paramVO);

}