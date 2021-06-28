package kr.pe.inface.hub.service.matrl.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatrlClmAprvVO {

	private String matrlClmNo;
	private int aprvSeq;
	private String aprvrId;
	private Date recvDts;
	private Date aprvDts;
	private String aprvStatCd;
	private String remark;

}
