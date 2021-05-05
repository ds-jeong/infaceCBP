package kr.pe.inface.hub.service.cmpny.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CmpnyUserSiteVO {

	private String cmpnyUserId;
	private String workSiteId;
	private String posiNm;
	private String useYn;

	private String siteNm;

}