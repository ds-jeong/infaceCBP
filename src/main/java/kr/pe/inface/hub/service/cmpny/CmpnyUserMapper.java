package kr.pe.inface.hub.service.cmpny;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;

@Mapper
public interface CmpnyUserMapper {
	public CmpnyUserVO getCmpnyUser(String loginId);
}