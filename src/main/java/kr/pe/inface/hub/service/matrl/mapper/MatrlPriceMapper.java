package kr.pe.inface.hub.service.matrl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.matrl.vo.MatrlPriceVO;

@Mapper
public interface MatrlPriceMapper {

	/**
	 * 건설업체/공급업체 기준으로 계약된 단가업체 목록을 조회
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenList(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 업체가격요청 목록
	 *
	 * @param cmpnyId
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenDtlList(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청 마스터 조회
	 *
	 * @param paramVO
	 * @return
	 */
	public MatrlPriceVO getCmpnyMatrlPriceVenReqDtl(MatrlPriceVO paramVO);

	/**
	 * 업체 자재단가 요청이 aplStrtDt 가 속한 연도 내역이 있는지 체크
	 *
	 * @param paramVO
	 * @return
	 */
	public MatrlPriceVO checkCmpnyMatrlPriceVenReqDtlAplStrtDt(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청메모 목록
	 *
	 * @param paramVO
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenReqMemoList(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 요청자재 목록
	 *
	 * @param paramVO
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenReqMatrlList(MatrlPriceVO paramVO);

	/**
	 * 업체자재단가 현재자재 목록
	 *
	 * @param paramVO
	 * @return
	 */
	public List<MatrlPriceVO> getCmpnyMatrlPriceVenCurMatrlList(MatrlPriceVO paramVO);

}