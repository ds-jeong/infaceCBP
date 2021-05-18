package kr.pe.inface.hub.config.security;

public class Const {

	// 사용자유형별(USER_TYPE_CD)로 URL prefix 권한 체크가 적용됨. WebSecurityConfig 참고
	// TODO 일단 이렇게.. 추후 메뉴관리 구조를 바꿔야 할듯..

	// 관리자 - /admin 하위
	public static final Object[] ADMIN_MENU = new Object[] {
	    new Object[] { "자재DB",
			new Object[] {
		  		new String[] { "자재목록", 		"/admin/matrl/matrlItemList" }
	  		}
		}
	};

	// 건설사_본사 - /cmpny 하위
	public static final Object[] CMPNY_MENU = new Object[] {
		new Object[] { "자재청구관리",
			new Object[] {
				new String[] { "청구목록", 		"/cmpny/matrlclm/matrlClmList" }
			  , new String[] { "청구합계표", 	"##" }
			}
		}
	  , new Object[] { "자재발주관리",
			new Object[] {
				new String[] { "발주목록", 		"##" }
	  		}
		}
	  , new Object[] { "투입자재관리",
			new Object[] {
		  		new String[] { "입고관리", 		"##" }
			}
		}
	  , new Object[] { "자재DB",
			new Object[] {
		  		new String[] { "자재품목설정", 	"/cmpny/matrl/matrlItemList" }
		  	  , new String[] { "자재품목", 		"/cmpny/matrl/cmpnyMatrlItemList" }
	    	  , new String[] { "자재", 			"/cmpny/matrl/cmpnyMatrlList" }
		  	  , new String[] { "업체단가관리", 	"/cmpny/price/cmpnyMatrlPriceVenList" }
	  		}
		}
	};

	// 건설사_현장 - /site 하위
	public static final Object[] SITE_MENU = new Object[] {
		new Object[] { "청구관리",
			new Object[] {
				new String[] { "청구목록", 		"##" }
			  , new String[] { "청구합계표", 	"##" }
			}
		}
	  , new Object[] { "자재DB",
			new Object[] {
		  		new String[] { "자재", 			"/site/matrl/cmpnyMatrlList" }
		  	  , new String[] { "자재분류", 		"##" }
	  		}
		}
	};

	// 공급업체 - /vendor 하위
	public static final Object[] VENDOR_MENU = new Object[] {
	    new Object[] { "자재DB",
			new Object[] {
		  		new String[] { "자재품목설정", 	"/vendor/matrl/matrlItemList" }
		  	  , new String[] { "자재품목", 		"/vendor/matrl/cmpnyMatrlItemList" }
		  	  , new String[] { "자재", 			"/vendor/matrl/cmpnyMatrlList" }
	  		}
		}
	};

}