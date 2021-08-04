package kr.co.inface.hub.service.matrl.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatrlVO {

	private String matrlMctgId;
	private String mctgNm;
	private String mctgDesc;

	private String matrlCtgId;
	private String ctgNm;
	private String ctgDesc;

	private String matrlItemId;
	private String itemNm;
	private String itemDesc;
	private String itemImg;
	private String buyTypeCd;

	private String matrlId;
	private String matrlStd;
	private String unitCd;
	private String matrlDesc;
	private Date modDts;

	private String cmpnyId;
	private String myItemYn;
	private String cntrtCmpnyCnt10;
	private String cntrtCmpnyCnt20;
	private String useYn;
	private String userId;
	private Integer cmpnyCnt;

}