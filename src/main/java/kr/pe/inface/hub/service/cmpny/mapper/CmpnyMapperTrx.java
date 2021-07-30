package kr.pe.inface.hub.service.cmpny.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyVO;
import kr.pe.inface.hub.service.cmpny.vo.WorkSiteVO;

@Mapper
public interface CmpnyMapperTrx {

	/**
	 * 업체 정보 수정
	 *
	 * @param vo
	 */
	@CacheEvict(cacheNames = CmpnyMapper.CACHE_CMPNY, key = "#vo.cmpnyId")
	public int updateCmpny(CmpnyVO vo);

	/**
	 * 작업현장 정보 수정
	 *
	 * @param vo
	 */
	@CacheEvict(cacheNames = CmpnyMapper.CACHE_WORK_SITE, key = "#vo.workSiteId")
	public int updateCmpny(WorkSiteVO vo);

}