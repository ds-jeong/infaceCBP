package kr.co.inface.hub.util;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드/다운로드 유틸
 */
public class FileUpDnUtil {

	// TODO 경로는 yml 설정등으로 분리해야 됨. 별도 파일서버구축시 다른 방식으로..
	private static final String STORE_DEST = "C:/TEST_UPLOAD/";

	/**
	 * 파일 업로드
	 *
	 * @param file
	 * @return 저장된 경로
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile file) throws Exception {
		String originalfileName = file.getOriginalFilename();
		String dummyStr = FastDateFormat.getInstance("yyyyMMddHHmmssSSS").format(new Date());

		String destPath = STORE_DEST + dummyStr + "_" + originalfileName;

		// TODO 오브젝트 스토리지등 다른 리모트서버에 저장되는 경우 등의 처리 필요
		// TODO 파일명 중복등의 처리.. 실제 저장경로는 임의로 생성하고, 파일명을 메타정보로 관리?
		// TODO 경로.. 중간경로도 만들어서 중복되지 않도록..
		File dest = new File(destPath);
		file.transferTo(dest);

		return destPath;
	}

}