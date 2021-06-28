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

	private String clmAprvrId1;
	private String clmAprvrId2;
	private String clmAprvrId3;
	private String ordrAprvrId1;
	private String ordrAprvrId2;

}