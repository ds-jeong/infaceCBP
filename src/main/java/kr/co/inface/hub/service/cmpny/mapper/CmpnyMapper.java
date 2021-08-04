package kr.co.inface.hub.service.cmpny.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

import kr.co.inface.hub.service.cmpny.vo.CmpnyVO;
import kr.co.inface.hub.service.cmpny.vo.WorkSiteVO;

@Mapper
public interface CmpnyMapper {

	public static final String CACHE_CMPNY = "cmpnyCache";
	public static final String CACHE_WORK_SITE = "workSiteCache";

	/**
	 * 업체 정보 조회. cache
	 *
	 * @param cmpnyId
	 * @return
	 */
	@Cacheable(cacheNames = CACHE_CMPNY)
	public CmpnyVO getCmpny(String cmpnyId);

	/**
	 * 작업현장 정보 조회. cache
	 *
	 * @param cmpnyId
	 * @return
	 */
	// TODO 지금은 수기관리중인 결재선 정보도.. 필요하면 캐싱해야할까..
	@Cacheable(cacheNames = CACHE_WORK_SITE)
	public WorkSiteVO getWorkSite(String workSiteId);

}