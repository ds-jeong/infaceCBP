package kr.co.inface.hub.service.cmpny.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.inface.hub.service.cmpny.vo.CmpnyUserVO;
import kr.co.inface.hub.service.cmpny.vo.WorkSiteVO;

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
	 * 업체 사용자 현장 목록
	 *
	 * @param cmpnyUserId
	 * @return
	 */
	public List<WorkSiteVO> getCmpnyUserSiteList(String cmpnyUserId);

}