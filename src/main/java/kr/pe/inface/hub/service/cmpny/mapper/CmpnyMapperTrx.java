package kr.pe.inface.hub.service.cmpny.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyVO;

@Mapper
public interface CmpnyMapperTrx {

	/**
	 * 업체 정보 수정
	 *
	 * @param vo
	 */
	@CacheEvict(cacheNames = CmpnyMapper.CACHE_NAME, key = "#vo.cmpnyId")
	public int updateCmpny(CmpnyVO vo);

}