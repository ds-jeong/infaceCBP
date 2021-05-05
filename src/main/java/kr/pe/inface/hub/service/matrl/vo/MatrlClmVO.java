package kr.pe.inface.hub.service.matrl.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatrlClmVO {

	private String matrlClmNo;
	private String cmpnyId;
	private String workSiteId;
	private String clmStatCd;
	private String clmDt;
	private Date inHopeDts;
	private String inAddr;
	private String inGateNo;
	private String inChargeNm;
	private String inChargeTel;
	private String inRemark;

}