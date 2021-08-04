package kr.co.inface.hub.service.matrl.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatrlPriceVO {

	private List<MatrlPriceVO> matrlPriceList;


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

	private String leasePerdCd;
	private Long price;
	private Long curPrice;
	private Long reqPrice;
	private Long sugstPrice;
	private Long leasePrice;
	private Long curLeasePrice;
	private Long reqLeasePrice;
	private Long sugstLeasePrice;

	private String cmpnyId;
	private String cmpnyNm;
	private String splCmpnyId;
	private String splCmpnyNm;
	private String title;
	private String remark;
	private String aplStrtDt;
	private String aplEndDt;
	private String reqStatCd;
	private String prevReqStatCd;
	private String reqDt;
	private String confirmDt;

	private String pageType;
	private int memoSeq;
	private String memoCont;
	private String regpeId;
	private String modpeId;
	private Date modDts;

}
