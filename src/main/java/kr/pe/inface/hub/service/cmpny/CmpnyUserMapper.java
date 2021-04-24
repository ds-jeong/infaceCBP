package kr.pe.inface.hub.service.cmpny;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.inface.hub.service.cmpny.vo.CmpnyUserVO;

@Mapper
public interface CmpnyUserMapper {
	public List<CmpnyUserVO> getCmpnyUserList();
	public CmpnyUserVO getCmpnyUser(String loginId);
}