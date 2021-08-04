package kr.co.inface.hub.service.cmpny.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
//@Slf4j
public class CmpnyVO {

	private String cmpnyId;
	private String cmpnyNm;
	private String cmpnyTypeCd;
	private String bizKindCd;
	private String bizRegNo;
	private String reppeNm;
	private String bizAddr;
	private String bizTypeItem;
	private String taxBillEmail;
	private String bizRegImg;
	private Date regDts;
	private String regpeId;
	private Date modDts;
	private String modpeId;

}
