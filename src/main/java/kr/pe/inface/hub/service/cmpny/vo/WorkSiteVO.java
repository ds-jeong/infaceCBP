package kr.pe.inface.hub.service.cmpny.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
//@Slf4j
public class WorkSiteVO {

	private String cmpnyId;
	private String workSiteId;
	private String siteNm;
	private String useYn;
	private String addrZipcd;
	private String addr;
	private Date regDts;
	private String regpeId;
	private Date modDts;
	private String modpeId;

	private String clmAprvrId1;
	private String clmAprvrId2;
	private String clmAprvrId3;
	private String ordrAprvrId1;
	private String ordrAprvrId2;

}
