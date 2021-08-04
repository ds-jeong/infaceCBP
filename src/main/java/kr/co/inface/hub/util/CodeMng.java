package kr.co.inface.hub.util;

public class CodeMng {

}

// TODO 코드매니저 구현


///**
// *
// * 코드관리 클래스 <br />
// *
// * static 메소드를 통해 바로 접근 가능. ( ex. CodeManager.getCodeNm("OS01", "001") )
// *
// */
//
//public final class CodeManager {
//
//	private static final Logger LOG = LoggerFactory.getLogger(CodeManager.class);
//
//	/**
//	 *
//	 * static inner class 사용하여 Singleton 처리 ( effectice java 추천.. )
//	 *
//	 */
//
//	private static final class SingletonHolder {
//
//		// static final 을 선언하기 위해 CodeManager 객체를 스프링 Context에 직접 등록할 수 없다.
//
//		// ( 객체생성을 static 코드로 처리하기위해 )
//
//		static final CodeManager INSTANCE = new CodeManager();
//
//	}
//
//	private CodeService codeService;
//
//	public void setCodeService(CodeService codeService) {
//
//		this.codeService = codeService;
//
//	}
//
//	/**
//	 *
//	 * commCdGrpNo 별로 공통코드목록화
//	 *
//	 */
//
//	private Map<String, List<CommCdDtlcDtoExt>> commCdGrpMap;
//
//	/**
//	 *
//	 * commCdGrpNo_commCdNo 를 key 로 공통코드를 map 에 저장.
//	 *
//	 */
//
//	private Map<String, CommCdDtlcDtoExt> commCdNoMap;
//
//	/**
//	 *
//	 * commCdGrpNo 별로 사이트별코드목록화
//	 *
//	 */
//
//	private Map<String, List<CommCdDtlcDtoExt>> siteCommCdGrpMap;
//
//	/**
//	 *
//	 * commCdGrpNo_commCdNo 를 key 로 사이트별코드를 map 에 저장.
//	 *
//	 */
//
//	private Map<String, CommCdDtlcDtoExt> siteCommCdNoMap;
//
//	public Map<String, List<CommCdDtlcDtoExt>> getCommCdGrpMap() {
//
//		if (commCdGrpMap == null) {
//
//			updateCode();
//
//		}
//
//		return commCdGrpMap;
//
//	}
//
//	public Map<String, CommCdDtlcDtoExt> getCommCdNoMap() {
//
//		if (commCdNoMap == null) {
//
//			updateCode();
//
//		}
//
//		return commCdNoMap;
//
//	}
//
//	public Map<String, List<CommCdDtlcDtoExt>> getSiteCommCdGrpMap() {
//
//		if (siteCommCdGrpMap == null) {
//
//			updateCode();
//
//		}
//
//		return siteCommCdGrpMap;
//
//	}
//
//	public Map<String, CommCdDtlcDtoExt> getSiteCommCdNoMap() {
//
//		if (siteCommCdNoMap == null) {
//
//			updateCode();
//
//		}
//
//		return siteCommCdNoMap;
//
//	}
//
//	/**
//	 *
//	 * 코드매니저 객체 조회
//	 *
//	 *
//	 *
//	 * @return 코드매니저 객체
//	 *
//	 */
//
//	public static CodeManager getInst() {
//
//		return SingletonHolder.INSTANCE;
//
//	}
//
//	/**
//	 *
//	 * 생성자 호출 방지.
//	 *
//	 */
//
//	private CodeManager() {
//	}
//
//	/**
//	 *
//	 * 공통코드 key 를 조회한다. commCdGrpNo_commCdNo 형식
//	 *
//	 *
//	 *
//	 * @param commCdGrpNo
//	 *
//	 *                    코드그룹번호
//	 *
//	 * @param commCdNo
//	 *
//	 *                    코드번호
//	 *
//	 * @return
//	 *
//	 */
//
//	private static String getCodeKey(String commCdGrpNo, String commCdNo) {
//
//		return commCdGrpNo + "_" + commCdNo;
//
//	}
//
//	/**
//	 *
//	 * 코드 갱신한다. 초기화시 호출, 스케쥴러 호출은 CodeManagerWrapper 에서 처리.
//	 *
//	 *
//	 *
//	 * @see ssg.common.code.CodeManagerSpringWrapper#updateCodeManager()
//	 *
//	 */
//
//	public void updateCodeProcess() {
//
//		LOG.debug("CodeManager updateCode() is starting...");
//
//		// 처리중 사용할 변수들
//
//		Map<String, List<CommCdDtlcDtoExt>> tempCommCdGrpMap = new HashMap<String, List<CommCdDtlcDtoExt>>();
//
//		Map<String, CommCdDtlcDtoExt> tempCommCdNoMap = new HashMap<String, CommCdDtlcDtoExt>();
//
//		Map<String, List<CommCdDtlcDtoExt>> tempSiteCommCdGrpMap = new HashMap<String, List<CommCdDtlcDtoExt>>();
//
//		Map<String, CommCdDtlcDtoExt> tempSiteCommCdNoMap = new HashMap<String, CommCdDtlcDtoExt>();
//
//		List<CommCdDtlcDtoExt> commCdDtlcList = codeService.getCommCdDtlcList();
//
//		List<CommCdDtlcDtoExt> siteCommCdDtlcList = codeService.getSiteAplCommCdDtlcList();
//
//		// 공통코드
//
//		for (CommCdDtlcDtoExt code : commCdDtlcList) {
//
//			// codeGrpMap 용
//
//			String inCommCdGrpNo = code.getCommCdGrpNo();
//
//			List<CommCdDtlcDtoExt> inCommCdNoList = tempCommCdGrpMap.get(inCommCdGrpNo);
//
//			if (inCommCdNoList == null) {
//
//				tempCommCdGrpMap.put(inCommCdGrpNo, (inCommCdNoList = new ArrayList<CommCdDtlcDtoExt>()));
//
//			}
//
//			inCommCdNoList.add(code);
//
//			// codeKeyMap 용 ( commCdGrpNo_commCdNo )
//
//			tempCommCdNoMap.put(getCodeKey(code.getCommCdGrpNo(), code.getCommCdNo()), code);
//
//		}
//
//		// 처리완료 후 replace
//
//		this.commCdGrpMap = tempCommCdGrpMap;
//
//		this.commCdNoMap = tempCommCdNoMap;
//
//		this.siteCommCdGrpMap = tempSiteCommCdGrpMap;
//
//		this.siteCommCdNoMap = tempSiteCommCdNoMap;
//
//		LOG.debug("CodeManager updateCode() is finished...");
//
//	}
//
//	/**
//	 *
//	 * 코드 갱신
//	 *
//	 */
//
//	public static void updateCode() {
//
//		SingletonHolder.INSTANCE.updateCodeProcess();
//
//	}
//
//	/**
//	 *
//	 * 코드상세 목록를 조회한다.
//	 *
//	 *
//	 *
//	 * @param commCdGrpNo
//	 *
//	 *                    코드그룹번호
//	 *
//	 * @return 코드상세 목록. 값이 없으면 null
//	 *
//	 */
//
//	public static List<CommCdDtlcDtoExt> getCodeDtlList(String commCdGrpNo) {
//
//		return SingletonHolder.INSTANCE.getCommCdGrpMap().get(commCdGrpNo);
//
//	}
//
//	/**
//	 *
//	 * 지정한 commCdGrpNo 의 상세코드목록을 select option 태그목록으로 반환. <br />
//	 *
//	 * getSiteCodeDtlOptionListHtml(null, commCdGrpNo, defaultCommCdNo) 와 같은 결과값을
//	 * 반환한다.
//	 *
//	 *
//	 *
//	 * @param commCdGrpNo
//	 *
//	 *                        코드그룹번호
//	 *
//	 * @param defaultCommCdNo
//	 *
//	 *                        기본코드번호
//	 *
//	 * @return
//	 *
//	 * @see CodeManager#getSiteCodeDtlOptionListHtml(String, String, String)
//	 *
//	 */
//
//	public static String getCodeDtlOptionListHtml(String commCdGrpNo, String defaultCommCdNo, CommCdDtlcBaseDto cdb) {
//
//		return getSiteCodeDtlOptionListHtml(null, commCdGrpNo, defaultCommCdNo, cdb);
//
//	}
//
//	/**
//	 *
//	 * 지정한 commCdGrpNo 의 상세코드목록을 input radio 태그목록으로 반환. <br />
//	 *
//	 * getSiteCodeDtlRadioListHtml(name, extAttr, null, commCdGrpNo,
//	 * defaultCommCdNo) 와 같은 결과값을 반환한다.
//	 *
//	 *
//	 *
//	 * @param name
//	 *
//	 *                        태그 name. 모두 동일한 name 을 가진다.
//	 *
//	 * @param extAttr
//	 *
//	 *                        추가 속성. 지정된 문자열을 태그안에 넣어서 출력한다.
//	 *
//	 * @param commCdGrpNo
//	 *
//	 *                        코드그룹번호
//	 *
//	 * @param defaultCommCdNo
//	 *
//	 *                        기본코드번호
//	 *
//	 * @return
//	 *
//	 * @see CodeManager#getSiteCodeDtlOptionListHtml(String, String, String)
//	 *
//	 */
//
//	public static String getCodeDtlRadioListHtml(String name, String extAttr, String commCdGrpNo,
//			String defaultCommCdNo, CommCdDtlcBaseDto cdb) {
//
//		return getSiteCodeDtlRadioListHtml(name, extAttr, null, commCdGrpNo, defaultCommCdNo, cdb);
//
//	}
//
//	/**
//	 *
//	 * 코드 조회
//	 *
//	 *
//	 *
//	 * @param commCdGrpNo
//	 *
//	 *                    코드그룹번호
//	 *
//	 * @param commCdNo
//	 *
//	 *                    조회할 코드
//	 *
//	 * @return 조회된 코드, 없으면 null
//	 *
//	 */
//
//	public static CommCdDtlcDtoExt getCode(String commCdGrpNo, String commCdNo) {
//
//		return SingletonHolder.INSTANCE.getCommCdNoMap().get(getCodeKey(commCdGrpNo, commCdNo));
//
//	}
//
//	/**
//	 *
//	 * 코드명 조회
//	 *
//	 *
//	 *
//	 * @param commCdGrpNo
//	 *
//	 *                    조회할 코드그룹번호
//	 *
//	 * @param commCdNo
//	 *
//	 *                    조회할 코드
//	 *
//	 * @return 조회된 코드명, 없는 코드인 경우는 빈문자열("") 반환
//	 *
//	 */
//
//	public static String getCodeNm(String commCdGrpNo, String commCdNo) {
//
//		CommCdDtlcDtoExt code = getCode(commCdGrpNo, commCdNo);
//
//		return code == null ? "" : code.getCommCdNm();
//
//	}
//
//	/**
//	 *
//	 * 코드명 조회. 코드가 없는 경우 defaultStr 를 반환
//	 *
//	 *
//	 *
//	 * @param commCdGrpNo
//	 *
//	 *                    조회할 코드그룹번호
//	 *
//	 * @param commCdNo
//	 *
//	 *                    조회할 코드
//	 *
//	 * @param defaultStr
//	 *
//	 *                    기본값
//	 *
//	 * @return 조회된 코드명, 없는 코드이면 defaultStr
//	 *
//	 */
//
//	public static String getCodeNm(String commCdGrpNo, String commCdNo, String defaultStr) {
//
//		return StringUtils.defaultString(getCodeNm(commCdGrpNo, commCdNo), defaultStr);
//
//	}
//
//	/**
//	 *
//	 * defaultIfBlank 이거 때문에 그냥 추가
//	 *
//	 * @param commCdGrpNo
//	 *
//	 * @param commCdNo
//	 *
//	 * @param defaultStr
//	 *
//	 * @return
//	 *
//	 */
//
//	public static String getCodeNm2(String commCdGrpNo, String commCdNo, String defaultStr) {
//
//		return StringUtils.defaultIfBlank(getCodeNm(commCdGrpNo, commCdNo), defaultStr);
//
//	}
//
//}
