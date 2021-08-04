package kr.pe.inface.hub.service.matrl.vo;

import java.util.Date;
import java.util.List;

import kr.pe.inface.hub.service.cmpny.vo.WorkSiteVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatrlClmVO {

	private String matrlClmNo;
	private String clmStatCd;
	private String clmDt;
	private String clmChargrId;
	private int dtlQty;
	private String remark;
	private String inAddrZipcd;
	private String inAddr;
	private String inAddrRemark;
	private String inGateNo;
	private String inChargrNm;
	private String inChargrTel;
	private String inRemark;
	private String workSiteId;
	private String cmpnyId;

	private String matrlId;
	private String matrlClmDtlNo;
	private String clmDtlStatCd;
	private String inHopeDt;
	private String inHopeHour;
	private int prevClmQty;
	private int clmQty;
	private int aprvQty;
	//private String reqDesc; // TODO matrl_clm_dtl.req_desc 요청내용 사용하는 곳이 없는듯.. 별다른 에러 없으면 추후 컬럼 삭제하자
	private String modFlag;

	private String regpeId;
	private Date regDts;
	private String modpeId;
	private Date modDts;

	private String clmChargrNm;
	private String clmChargrLoginId;
	private String siteNm;
	private String userId;
	private String itemNm;
	private String matrlStd;

	private List<WorkSiteVO> siteList;
	private List<MatrlClmVO> dtlList;
	private List<MatrlClmFileVO> fileList;
	private List<MatrlClmAprvVO> aprvList;

}
