package kr.pe.inface.hub.service.matrl.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatrlCntrtVO {

	private String cmpnyId;
	private String matrlItemId;
	private String splCmpnyId;
	private String splCmpnyNm;
	private String buyTypeCd;
	private String cntrtStatCd;

	private Date modDts;
	private String userId;

}