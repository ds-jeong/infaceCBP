package kr.pe.inface.hub.service.cmpny.mapper;

import org.apache.ibatis.annotations.Mapper;
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

}