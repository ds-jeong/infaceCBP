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
	 * 업체 자재단가 공급업체 가격요청 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenDtlList(MatrlPriceVO paramVO);

	/**
	 * 업체 자재단가 공급업체 요청 상세
	 *
	 * @param paramVO
	 * @return
	 */
	public MatrlPriceVO getCmpnyMatrlPriceVenReqDtl(MatrlPriceVO paramVO);

	/**
	 * 업체 자재단가 공급업체 요청 자재목록
	 *
	 * @param paramVO
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenReqMatrlList(MatrlPriceVO paramVO);

	// TODO 가격요청 메모 목록 조회해야함.


}