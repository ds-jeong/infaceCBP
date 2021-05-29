package kr.pe.inface.hub.service.matrl.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.matrl.vo.MatrlPriceVO;

@Mapper
public interface MatrlPriceMapperTrx {

	/**
	 * 업체단가 요청 마스터 등록
	 *
	 * @param paramVO
	 */
	public void insertCmpnyMatrlPriceReqMst(MatrlPriceVO paramVO);

	/**
	 * 업체단가 요청 자재가격 등록
	 *
	 * @param paramVO
	 */
	public void insertCmpnyMatrlPriceReq(MatrlPriceVO paramVO);

	/**
	 * 업체단가 요청 메모 등록
	 *
	 * @param paramVO
	 */
	public void insertCmpnyMatrlPriceReqMemo(MatrlPriceVO paramVO);

}