package kr.pe.inface.hub.service.matrl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.matrl.vo.MatrlPriceVO;

@Mapper
public interface MatrlPriceMapper {

	/**
	 * 업체 자재단가 공급업체 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenList(String cmpnyId);

	/**
	 * 업체 자재단가 공급업체 가격상세 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenDtlList(MatrlPriceVO paramVO);

}