package kr.pe.inface.hub.service.cmpny;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyVO;

@Mapper
public interface CmpnyMapper {

	public static final String CACHE_NAME = "cmpnyData";

	/**
	 * 업체 정보 조회
	 *
	 * @param cmpnyId
	 * @return
	 */
	@Cacheable(cacheNames = CACHE_NAME)
	public CmpnyVO getCmpny(String cmpnyId);

	/**
	 * 업체 정보 수정
	 *
	 * @param vo
	 */
	@CacheEvict(cacheNames = CACHE_NAME, key = "#vo.cmpnyId")
	public int updateCmpny(CmpnyVO vo);

}