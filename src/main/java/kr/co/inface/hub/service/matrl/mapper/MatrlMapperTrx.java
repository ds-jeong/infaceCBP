package kr.co.inface.hub.service.matrl.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.inface.hub.service.matrl.vo.MatrlCntrtVO;
import kr.co.inface.hub.service.matrl.vo.MatrlVO;

@Mapper
public interface MatrlMapperTrx {

	/**
	 * 자재폼목 사용 설정 처리
	 *
	 * @param paramMap
	 */
	public void useMyMatrlItem(MatrlVO paramVO);

	/**
	 * 자재품목 공급업체 계약 설정
	 *
	 * @param paramMap
	 */
	public void updMatrlItemCntrtStat(MatrlCntrtVO paramVO);

}