package kr.pe.inface.hub.service.cmpny.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserSiteVO;
import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;

@Mapper
public interface CmpnyUserMapper {

	/**
	 * 업체 사용자 목록
	 *
	 * @return
	 */
	public List<CmpnyUserVO> getCmpnyUserList();

	/**
	 * 로그인 업체 사용자 정보
	 *
	 * @param loginId
	 * @return
	 */
	public CmpnyUserVO getCmpnyUser(String loginId);

	/**
	 * 업체 사용자 현장 목록. 현장별 결재선정보 포함하고 있으나 추후 별도 기준이 생기면 삭제해야 함.
	 *
	 * @param cmpnyUserId
	 * @return
	 */
	public List<CmpnyUserSiteVO> getCmpnyUserSiteList(String cmpnyUserId);

}