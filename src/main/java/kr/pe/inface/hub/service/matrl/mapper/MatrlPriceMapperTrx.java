package kr.pe.inface.hub.service.matrl.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.matrl.vo.MatrlPriceVO;

@Mapper
public interface MatrlPriceMapperTrx {

	/**
	 * 업체자재단가 요청 마스터 등록
	 *
	 * @param paramVO
	 */
	public void insertCmpnyMatrlPriceReqMst(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청 마스터 수정
	 *
	 * @param paramVO
	 */
	public int updateCmpnyMatrlPriceReqMst(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청 자재가격 등록
	 *
	 * @param paramVO
	 */
	public void insertCmpnyMatrlPriceReq(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청 자재가격 수정 - 요청금액. 건설업체용
	 *
	 * @param paramVO
	 */
	public int updateCmpnyMatrlPriceReqCmpny(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청 자재가격 수정 - 제안금액. 공급업체용
	 *
	 * @param paramVO
	 */
	public int updateCmpnyMatrlPriceReqSplCmpny(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청 자재가격목록 확정 처리. 임시상태 요청가격 -> 확정가격 셋팅
	 *
	 * @param paramVO
	 */
	public int updateCmpnyMatrlPriceReqAllConfirm(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청 자재가격목록 가격테이블 이관
	 *
	 * @param paramVO
	 */
	public int insertCmpnyMatrlPriceFromReqByConfirm(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청 메모 등록
	 *
	 * @param paramVO
	 */
	public void insertCmpnyMatrlPriceReqMemo(MatrlPriceVO paramVO);

}