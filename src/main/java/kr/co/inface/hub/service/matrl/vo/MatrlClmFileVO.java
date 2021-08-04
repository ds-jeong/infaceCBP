package kr.co.inface.hub.service.matrl.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MatrlClmFileVO {

	private String matrlClmNo;
	private int fileSeq;
	private String useYn;
	private String fileDesc;
	private String filePath;
	private String fileNm;

	private String userId;

}
