package kr.pe.inface.hub.service.matrl.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatrlPriceVO {

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
	private long price;
	private long reqPrice;
	private long sugstPrice;
	private long leasePrice;
	private long reqLeasePrice;
	private long sugstLeasePrice;

	private String cmpnyId;
	private String splCmpnyId;
	private String splCmpnyNm;
	private String title;
	private String aplStrtDt;
	private String aplEndDt;
	private String reqStatCd;
	private String reqDt;
	private String confirmDt;

	private int memoSeq;
	private String memoCont;
	private Date modDts;
	private String modpeId;

	private String priceExistYn;

}
