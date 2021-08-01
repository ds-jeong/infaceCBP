-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.3
-- PostgreSQL version: 13.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file.
-- These commands were put in this file only as a convenience.
--
-- -- object: inface_hub | type: DATABASE --
-- -- DROP DATABASE IF EXISTS inface_hub;
-- CREATE DATABASE inface_hub
--  ENCODING = 'UTF8';
-- -- ddl-end --
--

-- object: public.base_column | type: TABLE --
-- DROP TABLE IF EXISTS public.base_column CASCADE;
CREATE TABLE public.base_column (
    reg_dts timestamp NOT NULL DEFAULT current_timestamp,
    regpe_id varchar(10) NOT NULL,
    mod_dts timestamp NOT NULL DEFAULT current_timestamp,
    modpe_id varchar(10) NOT NULL
);
-- ddl-end --
COMMENT ON COLUMN public.base_column.reg_dts IS E'등록_일시';
-- ddl-end --
COMMENT ON COLUMN public.base_column.regpe_id IS E'등록자_ID';
-- ddl-end --
COMMENT ON COLUMN public.base_column.mod_dts IS E'수정_일시';
-- ddl-end --
COMMENT ON COLUMN public.base_column.modpe_id IS E'수정자_ID';
-- ddl-end --

-- object: public.matrl | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl CASCADE;
CREATE TABLE public.matrl (
    LIKE public.base_column,
    matrl_id varchar(10) NOT NULL,
    matrl_std varchar(100),
    unit_cd varchar(4),
    matrl_desc varchar(200),
    matrl_img varchar(300),
    use_yn varchar(1) DEFAULT 'N',
    matrl_item_id varchar(9),
    CONSTRAINT matrl_pk PRIMARY KEY (matrl_id)

);
-- ddl-end --
COMMENT ON TABLE public.matrl IS E'자재정보';
-- ddl-end --
COMMENT ON COLUMN public.matrl.matrl_id IS E'자재_ID';
-- ddl-end --
COMMENT ON COLUMN public.matrl.matrl_std IS E'자재_규격\n3*6*12(일반)\n2000*2000*800';
-- ddl-end --
COMMENT ON COLUMN public.matrl.unit_cd IS E'단위_코드(그룹코드기입!!)';
-- ddl-end --
COMMENT ON COLUMN public.matrl.matrl_desc IS E'자재_설명';
-- ddl-end --
COMMENT ON COLUMN public.matrl.matrl_img IS E'자재_이미지';
-- ddl-end --
COMMENT ON COLUMN public.matrl.use_yn IS E'사용_여부';
-- ddl-end --

-- object: public.matrl_ctg | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_ctg CASCADE;
CREATE TABLE public.matrl_ctg (
    LIKE public.base_column,
    matrl_ctg_id varchar(8) NOT NULL,
    ctg_nm varchar(50),
    ctg_desc varchar(200),
    disp_ordr smallint DEFAULT 0,
    use_yn varchar(1) DEFAULT 'N',
    matrl_mctg_id varchar(7),
    CONSTRAINT matrl_ctg_pk PRIMARY KEY (matrl_ctg_id)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_ctg IS E'자재_분류';
-- ddl-end --
COMMENT ON COLUMN public.matrl_ctg.matrl_ctg_id IS E'자재_분류_ID';
-- ddl-end --
COMMENT ON COLUMN public.matrl_ctg.ctg_nm IS E'분류_이름';
-- ddl-end --
COMMENT ON COLUMN public.matrl_ctg.ctg_desc IS E'분류_설명';
-- ddl-end --
COMMENT ON COLUMN public.matrl_ctg.disp_ordr IS E'노출_순서';
-- ddl-end --
COMMENT ON COLUMN public.matrl_ctg.use_yn IS E'사용_여부';
-- ddl-end --

-- object: public.matrl_item | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_item CASCADE;
CREATE TABLE public.matrl_item (
    LIKE public.base_column,
    matrl_item_id varchar(9) NOT NULL,
    item_nm varchar(50),
    item_img varchar(300),
    item_desc varchar(200),
    disp_ordr smallint DEFAULT 0,
    use_yn varchar(1) DEFAULT 'N',
    matrl_ctg_id varchar(8),
    CONSTRAINT matrl_item_pk PRIMARY KEY (matrl_item_id)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_item IS E'자재_품목';
-- ddl-end --
COMMENT ON COLUMN public.matrl_item.matrl_item_id IS E'자재_품목_ID';
-- ddl-end --
COMMENT ON COLUMN public.matrl_item.item_nm IS E'품목_이름';
-- ddl-end --
COMMENT ON COLUMN public.matrl_item.item_img IS E'품목_이미지';
-- ddl-end --
COMMENT ON COLUMN public.matrl_item.item_desc IS E'품목_설명';
-- ddl-end --
COMMENT ON COLUMN public.matrl_item.disp_ordr IS E'노출_순서';
-- ddl-end --
COMMENT ON COLUMN public.matrl_item.use_yn IS E'사용_여부';
-- ddl-end --

-- object: public.cmpny | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny CASCADE;
CREATE TABLE public.cmpny (
    LIKE public.base_column,
    cmpny_id varchar(6) NOT NULL,
    cmpny_type_cd varchar(4) NOT NULL,
    biz_kind_cd varchar(4),
    biz_reg_no varchar(15),
    cmpny_nm varchar(100),
    reppe_nm varchar(50),
    biz_addr varchar(200),
    biz_type_item varchar(100),
    tax_bill_email varchar(100),
    biz_reg_img varchar(300),
    CONSTRAINT cmpny_pk PRIMARY KEY (cmpny_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny IS E'업체\n건설업체,자재업체 등\n종류별로 prefix 를 지정하고, 구분자코드를 만들까..\nC00001\nV00001';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.cmpny_id IS E'업체_ID';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.cmpny_type_cd IS E'업체_유형_코드\n10 - 건설사\n20 - 자재공급업체';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.biz_kind_cd IS E'사업자_구분_코드\n10 - 법인사업자\n20 - 개인사업자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.biz_reg_no IS E'사업자_등록_번호';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.cmpny_nm IS E'업체_이름';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.reppe_nm IS E'대표자_이름';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.biz_addr IS E'업체_주소\n우편번호,주소,상세주소?';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.biz_type_item IS E'업태_종목\n여러개 가능..\n코드? 텍스트?';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.tax_bill_email IS E'세금_계산서_이메일';
-- ddl-end --
COMMENT ON COLUMN public.cmpny.biz_reg_img IS E'사업자_등록_이미지';
-- ddl-end --

-- object: public.cmpny_matrl_item | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_item CASCADE;
CREATE TABLE public.cmpny_matrl_item (
    LIKE public.base_column,
    cmpny_id varchar(6) NOT NULL,
    matrl_item_id varchar(9) NOT NULL,
    buy_type_cd varchar(4),
    use_yn varchar(1) DEFAULT 'N',
    CONSTRAINT cmpny_matrl_item_pk PRIMARY KEY (matrl_item_id,cmpny_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_matrl_item IS E'업체_자재_품목\n건설사는 별도로 구매타입을 설정하지 않음.\n공급업체는 품목별로 구매타입을 지정해야 함.';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_item.buy_type_cd IS E'구매_타입_코드\n10 - 구매\n20 - 임대\n공급업체인 경우는 필수.';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_item.use_yn IS E'사용_여부';
-- ddl-end --

-- object: public.cmpny_matrl_price | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_price CASCADE;
CREATE TABLE public.cmpny_matrl_price (
    LIKE public.base_column,
    cmpny_id varchar(6) NOT NULL,
    matrl_id varchar(10) NOT NULL,
    spl_cmpny_id varchar(6) NOT NULL,
    apl_strt_dt varchar(8) NOT NULL,
    apl_end_dt varchar(8) NOT NULL,
    buy_type_cd varchar(4),
    lease_perd_cd varchar(4),
    price int4 NOT NULL DEFAULT 0,
    lease_price int4,
    CONSTRAINT cmpny_matrl_price_pk PRIMARY KEY (spl_cmpny_id,apl_strt_dt,cmpny_id,matrl_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_matrl_price IS E'업체_자재_가격\n확정되면.. 요청/제안가격에서 실제 가격컬럼으로 업데이트';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price.spl_cmpny_id IS E'공급_업체_ID';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price.apl_strt_dt IS E'적용_시작_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price.apl_end_dt IS E'적용_종료_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price.buy_type_cd IS E'구매_타입_코드';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price.lease_perd_cd IS E'임대_주기_코드\n10 - 일\n20 - 주\n30 - 월';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price.price IS E'가격\n구매가격\n임대기본료';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price.lease_price IS E'임대_가격';
-- ddl-end --

-- object: public.work_site | type: TABLE --
-- DROP TABLE IF EXISTS public.work_site CASCADE;
CREATE TABLE public.work_site (
    LIKE public.base_column,
    work_site_id varchar(8) NOT NULL,
    site_nm varchar(100),
    use_yn varchar(1) DEFAULT 'N',
    addr_zipcd varchar(6),
    addr varchar(200),
    cmpny_id varchar(6),
    CONSTRAINT work_site_pk PRIMARY KEY (work_site_id)

);
-- ddl-end --
COMMENT ON TABLE public.work_site IS E'건설현장 - 건설현장의 소유자는 누구?\n야적장 - 건설업체는 기본 야적장을 가진다.\n자재창고 - 자재업체는 기본창고를 가진다.';
-- ddl-end --
COMMENT ON COLUMN public.work_site.work_site_id IS E'작업_현장_ID';
-- ddl-end --
COMMENT ON COLUMN public.work_site.site_nm IS E'현장_이름';
-- ddl-end --
COMMENT ON COLUMN public.work_site.use_yn IS E'사용_여부';
-- ddl-end --
COMMENT ON COLUMN public.work_site.addr_zipcd IS E'주소_우편번호';
-- ddl-end --
COMMENT ON COLUMN public.work_site.addr IS E'주소';
-- ddl-end --

-- object: public.matrl_clm | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_clm CASCADE;
CREATE TABLE public.matrl_clm (
    LIKE public.base_column,
    matrl_clm_no varchar(15) NOT NULL,
    clm_stat_cd varchar(4),
    clm_dt varchar(8),
    clm_chargr_id varchar(10),
    dtl_qty smallint,
    remark varchar(2000),
    in_addr_zipcd varchar(6),
    in_addr varchar(200),
    in_addr_remark varchar(100),
    in_gate_no varchar(10),
    in_chargr_nm varchar(20),
    in_chargr_tel varchar(20),
    in_remark varchar(300),
    work_site_id varchar(8),
    cmpny_id varchar(6),
    CONSTRAINT matrl_clm_pk PRIMARY KEY (matrl_clm_no)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_clm IS E'자재_청구';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.matrl_clm_no IS E'자재_청구_번호\n결재ID 와는 다른 각 영역별로 유니크한 문서번호 부여..?';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.clm_stat_cd IS E'청구_상태_코드(그룹코드기입!!)\n10 - 작성중\n20 - 승인중\n30 - 수정요청\n40 - 승인완료\n90 - 취소';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.clm_dt IS E'청구_일자';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.clm_chargr_id IS E'청구_담당자_ID';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.dtl_qty IS E'상세_수량';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.remark IS E'비고';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_addr_zipcd IS E'입고_주소_우편번호';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_addr IS E'입고_주소';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_addr_remark IS E'입고_주소_비고\n하차위치 설명 등';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_gate_no IS E'입고_게이트_번호\nex. 동3문?';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_chargr_nm IS E'입고_담당자_이름';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_chargr_tel IS E'입고_담당자_연락처';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_remark IS E'입고_비고';
-- ddl-end --

-- object: public.matrl_clm_dtl | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_clm_dtl CASCADE;
CREATE TABLE public.matrl_clm_dtl (
    LIKE public.base_column,
    matrl_clm_no varchar(15) NOT NULL,
    matrl_id varchar(10) NOT NULL,
    matrl_clm_dtl_no varchar(20),
    clm_dtl_stat_cd varchar(4),
    in_hope_dt varchar(10),
    in_hope_hour varchar(2),
    prev_clm_qty smallint DEFAULT 0,
    clm_qty smallint DEFAULT 0,
    aprv_qty smallint DEFAULT 0,
    req_desc varchar(300),
    remark varchar(300),
    CONSTRAINT matrl_clm_dtl_pk PRIMARY KEY (matrl_id,matrl_clm_no)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_clm_dtl IS E'자재_청구_상세\n임시저장시에는 생성하지 않음.';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_dtl.matrl_clm_dtl_no IS E'자재_청구_상세_번호';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_dtl.clm_dtl_stat_cd IS E'청구_상세_상태_코드(그룹코드기입!!)\n20 - 완료\n90 - 취소';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_dtl.in_hope_dt IS E'입고_희망_일자';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_dtl.in_hope_hour IS E'입고_희망_시간';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_dtl.prev_clm_qty IS E'기청구_수량\n월별.현장의 기 청구수량?\n미리 집계? -> 컬럼 삭제?';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_dtl.clm_qty IS E'청구_수량';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_dtl.aprv_qty IS E'승인_수량';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_dtl.req_desc IS E'요청_내용';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_dtl.remark IS E'비고';
-- ddl-end --

-- object: public.matrl_mctg | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_mctg CASCADE;
CREATE TABLE public.matrl_mctg (
    LIKE public.base_column,
    matrl_mctg_id varchar(7) NOT NULL,
    mctg_nm varchar(50),
    mctg_desc varchar(200),
    disp_ordr smallint DEFAULT 0,
    use_yn varchar(1) DEFAULT 'N',
    CONSTRAINT matrl_mctg_pk PRIMARY KEY (matrl_mctg_id)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_mctg IS E'자재_중분류';
-- ddl-end --
COMMENT ON COLUMN public.matrl_mctg.matrl_mctg_id IS E'자재_중분류_ID';
-- ddl-end --
COMMENT ON COLUMN public.matrl_mctg.mctg_nm IS E'중분류_이름';
-- ddl-end --
COMMENT ON COLUMN public.matrl_mctg.mctg_desc IS E'중분류_설명';
-- ddl-end --
COMMENT ON COLUMN public.matrl_mctg.disp_ordr IS E'노출_순서';
-- ddl-end --
COMMENT ON COLUMN public.matrl_mctg.use_yn IS E'사용_여부';
-- ddl-end --

-- object: public.comm_grp_cd | type: TABLE --
-- DROP TABLE IF EXISTS public.comm_grp_cd CASCADE;
CREATE TABLE public.comm_grp_cd (
    LIKE public.base_column,
    comm_grp_cd varchar(4) NOT NULL,
    grp_cd_nm varchar(20),
    use_yn varchar(1) DEFAULT 'N',
    remark varchar(100),
    CONSTRAINT comm_grp_cd_pk PRIMARY KEY (comm_grp_cd)

);
-- ddl-end --
COMMENT ON TABLE public.comm_grp_cd IS E'공통_그룹_코드\nXXYY ( 영역,코드번호 ? )';
-- ddl-end --
COMMENT ON COLUMN public.comm_grp_cd.comm_grp_cd IS E'공통_그룹_코드';
-- ddl-end --
COMMENT ON COLUMN public.comm_grp_cd.grp_cd_nm IS E'그룹_코드_이름';
-- ddl-end --
COMMENT ON COLUMN public.comm_grp_cd.use_yn IS E'사용_여부';
-- ddl-end --
COMMENT ON COLUMN public.comm_grp_cd.remark IS E'비고';
-- ddl-end --

-- object: public.comm_cd | type: TABLE --
-- DROP TABLE IF EXISTS public.comm_cd CASCADE;
CREATE TABLE public.comm_cd (
    LIKE public.base_column,
    comm_cd varchar(4) NOT NULL,
    cd_nm varchar(20),
    use_yn varchar(1) DEFAULT 'N',
    remark varchar(100),
    comm_grp_cd varchar(4),
    CONSTRAINT comm_cd_pk PRIMARY KEY (comm_cd)

);
-- ddl-end --
COMMENT ON TABLE public.comm_cd IS E'공통_코드';
-- ddl-end --
COMMENT ON COLUMN public.comm_cd.comm_cd IS E'공통_코드';
-- ddl-end --
COMMENT ON COLUMN public.comm_cd.cd_nm IS E'코드_이름';
-- ddl-end --
COMMENT ON COLUMN public.comm_cd.use_yn IS E'사용_여부';
-- ddl-end --
COMMENT ON COLUMN public.comm_cd.remark IS E'비고';
-- ddl-end --

-- object: public.cmpny_matrl_item_cntrt | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_item_cntrt CASCADE;
CREATE TABLE public.cmpny_matrl_item_cntrt (
    LIKE public.base_column,
    cmpny_id varchar(6) NOT NULL,
    matrl_item_id varchar(9) NOT NULL,
    spl_cmpny_id varchar(6) NOT NULL,
    buy_type_cd varchar(4) NOT NULL,
    cntrt_stat_cd varchar(4),
    CONSTRAINT cmpny_matrl_item_cntrt_pk PRIMARY KEY (spl_cmpny_id,buy_type_cd,matrl_item_id,cmpny_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_matrl_item_cntrt IS E'업체_자재_품목_계약';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_item_cntrt.spl_cmpny_id IS E'공급_업체_ID';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_item_cntrt.buy_type_cd IS E'구매_타입_코드\n10 - 구매\n20 - 임대';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_item_cntrt.cntrt_stat_cd IS E'계약_상태_코드(코드)\n10 - 검토중\n20 - 거래중\n90 - 거래중지';
-- ddl-end --

-- object: public.cmpny_matrl_price_req | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_price_req CASCADE;
CREATE TABLE public.cmpny_matrl_price_req (
    LIKE public.base_column,
    cmpny_id varchar(6) NOT NULL,
    spl_cmpny_id varchar(6) NOT NULL,
    apl_strt_dt varchar(8) NOT NULL,
    matrl_id varchar(10) NOT NULL,
    req_stat_cd varchar(4),
    req_dt varchar(8),
    confirm_dt varchar(8),
    buy_type_cd varchar(4),
    lease_perd_cd varchar(4),
    price int4 NOT NULL DEFAULT 0,
    req_price int4 DEFAULT 0,
    sugst_price int4 DEFAULT 0,
    lease_price int4,
    req_lease_price int4,
    sugst_lease_price int4,
    CONSTRAINT cmpny_matrl_price_req_pk PRIMARY KEY (matrl_id,apl_strt_dt,spl_cmpny_id,cmpny_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_matrl_price_req IS E'업체_자재_가격_요청\n1년단위이긴하나.. 임의의 날짜도 가능하도록?';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.req_stat_cd IS E'요청_상태_코드\n10 - 진행중\n20 - 확정\n추후 추가되는 경우, 기존에 확정된 데이터와 구분하기 위한 확정여부관리 필요.';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.req_dt IS E'요청_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.confirm_dt IS E'확정_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.buy_type_cd IS E'구매_타입_코드';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.lease_perd_cd IS E'임대_주기_코드\n10 - 일\n20 - 주\n30 - 월';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.price IS E'가격\n구매가격\n임대기본료';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.req_price IS E'요청_가격';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.sugst_price IS E'제안_가격';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.lease_price IS E'임대_가격';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.req_lease_price IS E'요청_임대_가격';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.sugst_lease_price IS E'제안_임대_가격';
-- ddl-end --

-- object: public.matrl_clm_aprv | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_clm_aprv CASCADE;
CREATE TABLE public.matrl_clm_aprv (
    LIKE public.base_column,
    matrl_clm_no varchar(15) NOT NULL,
    aprv_seq smallint NOT NULL DEFAULT 1,
    aprvr_id varchar(10) NOT NULL,
    aprv_dts timestamp,
    aprv_stat_cd varchar(4),
    remark varchar(200),
    CONSTRAINT matrl_clm_aprv_pk PRIMARY KEY (aprv_seq,matrl_clm_no)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_clm_aprv IS E'자재_청구_결재';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_aprv.aprv_seq IS E'결재_순번';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_aprv.aprvr_id IS E'결재자_ID';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_aprv.aprv_dts IS E'결재_일시';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_aprv.aprv_stat_cd IS E'결재_상태_코드\n00 - 대기 ( 결재선에 있지만 차례가 아님 )\n10 - 승인요청 ( 결재할 차례인 경우 )\n20 - 승인\n90 - 반려';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_aprv.remark IS E'비고\n반려사유등.';
-- ddl-end --

-- object: public.cmpny_user | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_user CASCADE;
CREATE TABLE public.cmpny_user (
    LIKE public.base_column,
    cmpny_user_id varchar(10) NOT NULL,
    user_type_cd varchar(4),
    user_nm varchar(20),
    login_id varchar(20) NOT NULL,
    pwd varchar(100) NOT NULL,
    stat_cd varchar(4),
    join_dt varchar(8),
    dept_nm varchar(50),
    posi_nm varchar(50),
    tel_no varchar(20),
    fax_no varchar(20),
    hp_no varchar(20),
    email varchar(100),
    cmpny_id varchar(6),
    CONSTRAINT cmpny_user_pk PRIMARY KEY (cmpny_user_id),
    CONSTRAINT login_id_uq UNIQUE (login_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_user IS E'업체_사용자\n근로자..와 구분하는게 나을듯..\n건설사.공급업체?\n사용자? 뭐라고 정의할까..';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.cmpny_user_id IS E'업체_사용자_ID';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.user_type_cd IS E'사용자_유형_코드\n00 - 관리자\n10 - 건설사\n11 - 건설사-본사\n12 - 건설사-현장, cmpny_user_site 참조\n20 - 자재공급업체';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.user_nm IS E'사용자_이름';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.login_id IS E'로그인_ID';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.pwd IS E'비밀번호';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.stat_cd IS E'상태_코드\n10 - 가입요청\n20 - 사용중\n90 - 사용중지';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.join_dt IS E'가입_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.dept_nm IS E'부서_이름';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.posi_nm IS E'직책_이름';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.tel_no IS E'전화_번호';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.fax_no IS E'팩스_번호';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.hp_no IS E'핸드폰_번호';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.email IS E'이메일';
-- ddl-end --

-- -- object: matrl_mctg_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.matrl_ctg DROP CONSTRAINT IF EXISTS matrl_mctg_fk CASCADE;
-- ALTER TABLE public.matrl_ctg ADD CONSTRAINT matrl_mctg_fk FOREIGN KEY (matrl_mctg_id)
-- REFERENCES public.matrl_mctg (matrl_mctg_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: matrl_item_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.matrl DROP CONSTRAINT IF EXISTS matrl_item_fk CASCADE;
-- ALTER TABLE public.matrl ADD CONSTRAINT matrl_item_fk FOREIGN KEY (matrl_item_id)
-- REFERENCES public.matrl_item (matrl_item_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: matrl_ctg_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.matrl_item DROP CONSTRAINT IF EXISTS matrl_ctg_fk CASCADE;
-- ALTER TABLE public.matrl_item ADD CONSTRAINT matrl_ctg_fk FOREIGN KEY (matrl_ctg_id)
-- REFERENCES public.matrl_ctg (matrl_ctg_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: work_site_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.matrl_clm DROP CONSTRAINT IF EXISTS work_site_fk CASCADE;
-- ALTER TABLE public.matrl_clm ADD CONSTRAINT work_site_fk FOREIGN KEY (work_site_id)
-- REFERENCES public.work_site (work_site_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: matrl_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.matrl_clm_dtl DROP CONSTRAINT IF EXISTS matrl_fk CASCADE;
-- ALTER TABLE public.matrl_clm_dtl ADD CONSTRAINT matrl_fk FOREIGN KEY (matrl_id)
-- REFERENCES public.matrl (matrl_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: matrl_item_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_matrl_item DROP CONSTRAINT IF EXISTS matrl_item_fk CASCADE;
-- ALTER TABLE public.cmpny_matrl_item ADD CONSTRAINT matrl_item_fk FOREIGN KEY (matrl_item_id)
-- REFERENCES public.matrl_item (matrl_item_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: cmpny_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_matrl_item DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
-- ALTER TABLE public.cmpny_matrl_item ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
-- REFERENCES public.cmpny (cmpny_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: cmpny_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.work_site DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
-- ALTER TABLE public.work_site ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
-- REFERENCES public.cmpny (cmpny_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: cmpny_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_user DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
-- ALTER TABLE public.cmpny_user ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
-- REFERENCES public.cmpny (cmpny_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: cmpny_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_matrl_price DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
-- ALTER TABLE public.cmpny_matrl_price ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
-- REFERENCES public.cmpny (cmpny_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: matrl_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_matrl_price DROP CONSTRAINT IF EXISTS matrl_fk CASCADE;
-- ALTER TABLE public.cmpny_matrl_price ADD CONSTRAINT matrl_fk FOREIGN KEY (matrl_id)
-- REFERENCES public.matrl (matrl_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: matrl_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_matrl_price_req DROP CONSTRAINT IF EXISTS matrl_fk CASCADE;
-- ALTER TABLE public.cmpny_matrl_price_req ADD CONSTRAINT matrl_fk FOREIGN KEY (matrl_id)
-- REFERENCES public.matrl (matrl_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: comm_grp_cd_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.comm_cd DROP CONSTRAINT IF EXISTS comm_grp_cd_fk CASCADE;
-- ALTER TABLE public.comm_cd ADD CONSTRAINT comm_grp_cd_fk FOREIGN KEY (comm_grp_cd)
-- REFERENCES public.comm_grp_cd (comm_grp_cd) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- object: public.cmpny_matrl_price_req_mst | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_price_req_mst CASCADE;
CREATE TABLE public.cmpny_matrl_price_req_mst (
    LIKE public.base_column,
    cmpny_id varchar(6) NOT NULL,
    spl_cmpny_id varchar(6) NOT NULL,
    apl_strt_dt varchar(8) NOT NULL,
    apl_end_dt varchar(8),
    title varchar(100),
    req_stat_cd varchar(4),
    req_dt varchar(8),
    confirm_dt varchar(8),
    remark varchar(300),
    CONSTRAINT cmpny_matrl_price_req_mst_pk PRIMARY KEY (apl_strt_dt,spl_cmpny_id,cmpny_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_matrl_price_req_mst IS E'업체_자재_가격_요청_마스터';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.spl_cmpny_id IS E'공급_업체_ID';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.apl_strt_dt IS E'적용_시작_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.apl_end_dt IS E'적용_종료_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.title IS E'제목';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.req_stat_cd IS E'요청_상태_코드\n00 - 작성중\n10 - 확인요청(공급업체)\n15 - 확인요청(건설업체)\n20 - 확정\n90 - 요청취소';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.req_dt IS E'요청_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.confirm_dt IS E'확정_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.remark IS E'비고';
-- ddl-end --

-- -- object: cmpny_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_matrl_price_req_mst DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
-- ALTER TABLE public.cmpny_matrl_price_req_mst ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
-- REFERENCES public.cmpny (cmpny_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: cmpny_matrl_price_req_mst_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_matrl_price_req DROP CONSTRAINT IF EXISTS cmpny_matrl_price_req_mst_fk CASCADE;
-- ALTER TABLE public.cmpny_matrl_price_req ADD CONSTRAINT cmpny_matrl_price_req_mst_fk FOREIGN KEY (apl_strt_dt,spl_cmpny_id,cmpny_id)
-- REFERENCES public.cmpny_matrl_price_req_mst (apl_strt_dt,spl_cmpny_id,cmpny_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: cmpny_matrl_item_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_matrl_item_cntrt DROP CONSTRAINT IF EXISTS cmpny_matrl_item_fk CASCADE;
-- ALTER TABLE public.cmpny_matrl_item_cntrt ADD CONSTRAINT cmpny_matrl_item_fk FOREIGN KEY (matrl_item_id,cmpny_id)
-- REFERENCES public.cmpny_matrl_item (matrl_item_id,cmpny_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- object: public.v_matrl_item | type: VIEW --
-- DROP VIEW IF EXISTS public.v_matrl_item CASCADE;
CREATE VIEW public.v_matrl_item
AS

SELECT
        MM.MATRL_MCTG_ID, MM.MCTG_NM, MM.MCTG_DESC, MM.DISP_ORDR AS MCTG_DISP_ORDR, MM.USE_YN AS MCTG_USE_YN
      , MC.MATRL_CTG_ID, MC.CTG_NM, MC.CTG_DESC, MC.DISP_ORDR AS CTG_DISP_ORDR, MC.USE_YN AS CTG_USE_YN
      , MI.MATRL_ITEM_ID, MI.ITEM_NM, MI.ITEM_DESC, MI.ITEM_IMG, MI.DISP_ORDR, MI.USE_YN
FROM
        MATRL_MCTG MM
      , MATRL_CTG MC
      , MATRL_ITEM MI
WHERE   1 = 1
AND     MM.MATRL_MCTG_ID = MC.MATRL_MCTG_ID
AND     MC.MATRL_CTG_ID = MI.MATRL_CTG_ID;
-- ddl-end --
COMMENT ON VIEW public.v_matrl_item IS E'뷰_자재_품목';
-- ddl-end --

-- object: public.cmpny_user_site | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_user_site CASCADE;
CREATE TABLE public.cmpny_user_site (
    LIKE public.base_column,
    cmpny_user_id varchar(10) NOT NULL,
    work_site_id varchar(8) NOT NULL,
    posi_nm varchar(50),
    use_yn varchar(1) DEFAULT 'N',
    CONSTRAINT cmpny_user_site_pk PRIMARY KEY (work_site_id,cmpny_user_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_user_site IS E'업체_사용자_현장';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user_site.posi_nm IS E'직책_이름';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user_site.use_yn IS E'사용_여부';
-- ddl-end --

-- -- object: work_site_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_user_site DROP CONSTRAINT IF EXISTS work_site_fk CASCADE;
-- ALTER TABLE public.cmpny_user_site ADD CONSTRAINT work_site_fk FOREIGN KEY (work_site_id)
-- REFERENCES public.work_site (work_site_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: cmpny_user_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_user_site DROP CONSTRAINT IF EXISTS cmpny_user_fk CASCADE;
-- ALTER TABLE public.cmpny_user_site ADD CONSTRAINT cmpny_user_fk FOREIGN KEY (cmpny_user_id)
-- REFERENCES public.cmpny_user (cmpny_user_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: cmpny_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.matrl_clm DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
-- ALTER TABLE public.matrl_clm ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
-- REFERENCES public.cmpny (cmpny_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: matrl_clm_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.matrl_clm_dtl DROP CONSTRAINT IF EXISTS matrl_clm_fk CASCADE;
-- ALTER TABLE public.matrl_clm_dtl ADD CONSTRAINT matrl_clm_fk FOREIGN KEY (matrl_clm_no)
-- REFERENCES public.matrl_clm (matrl_clm_no) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: matrl_clm_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.matrl_clm_aprv DROP CONSTRAINT IF EXISTS matrl_clm_fk CASCADE;
-- ALTER TABLE public.matrl_clm_aprv ADD CONSTRAINT matrl_clm_fk FOREIGN KEY (matrl_clm_no)
-- REFERENCES public.matrl_clm (matrl_clm_no) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- object: public.cmpny_matrl_price_req_memo | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_price_req_memo CASCADE;
CREATE TABLE public.cmpny_matrl_price_req_memo (
    LIKE public.base_column,
    cmpny_id varchar(6) NOT NULL,
    spl_cmpny_id varchar(6) NOT NULL,
    apl_strt_dt varchar(8) NOT NULL,
    memo_seq smallint NOT NULL,
    memo_cont varchar(400),
    CONSTRAINT cmpny_matrl_price_req_memo_pk PRIMARY KEY (memo_seq,apl_strt_dt,spl_cmpny_id,cmpny_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_matrl_price_req_memo IS E'업체_자재_가격_요청_메모';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_memo.memo_seq IS E'메모_순번';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_memo.memo_cont IS E'메모_내용';
-- ddl-end --

-- -- object: cmpny_matrl_price_req_mst_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.cmpny_matrl_price_req_memo DROP CONSTRAINT IF EXISTS cmpny_matrl_price_req_mst_fk CASCADE;
-- ALTER TABLE public.cmpny_matrl_price_req_memo ADD CONSTRAINT cmpny_matrl_price_req_mst_fk FOREIGN KEY (apl_strt_dt,spl_cmpny_id,cmpny_id)
-- REFERENCES public.cmpny_matrl_price_req_mst (apl_strt_dt,spl_cmpny_id,cmpny_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- object: public.work_site_aprv_tmplt | type: TABLE --
-- DROP TABLE IF EXISTS public.work_site_aprv_tmplt CASCADE;
CREATE TABLE public.work_site_aprv_tmplt (
    LIKE public.base_column,
    work_site_id varchar(8) NOT NULL,
    clm_aprvr_id_1 varchar(10),
    clm_aprvr_id_2 varchar(10),
    clm_aprvr_id_3 varchar(10),
    ordr_aprvr_id_1 varchar(10),
    ordr_aprvr_id_2 varchar(10),
    CONSTRAINT work_site_aprv_tmplt_pk PRIMARY KEY (work_site_id)

);
-- ddl-end --
COMMENT ON TABLE public.work_site_aprv_tmplt IS E'작업_현장 결재선 수기관리 템플릿\n청구 결재선 3명 - 현장 : 담당, 소장, 이사\n발주 결재선 2명 - 본사 : 담당, 이사';
-- ddl-end --
COMMENT ON COLUMN public.work_site_aprv_tmplt.clm_aprvr_id_1 IS E'청구_승인_id_1';
-- ddl-end --
COMMENT ON COLUMN public.work_site_aprv_tmplt.clm_aprvr_id_2 IS E'청구_승인_id_2';
-- ddl-end --
COMMENT ON COLUMN public.work_site_aprv_tmplt.clm_aprvr_id_3 IS E'청구_승인_id_3';
-- ddl-end --
COMMENT ON COLUMN public.work_site_aprv_tmplt.ordr_aprvr_id_1 IS E'발주_승인_id_1';
-- ddl-end --
COMMENT ON COLUMN public.work_site_aprv_tmplt.ordr_aprvr_id_2 IS E'발주_승인_id_2';
-- ddl-end --

-- -- object: work_site_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.work_site_aprv_tmplt DROP CONSTRAINT IF EXISTS work_site_fk CASCADE;
-- ALTER TABLE public.work_site_aprv_tmplt ADD CONSTRAINT work_site_fk FOREIGN KEY (work_site_id)
-- REFERENCES public.work_site (work_site_id) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- -- object: work_site_aprv_tmplt_uq | type: CONSTRAINT --
-- -- ALTER TABLE public.work_site_aprv_tmplt DROP CONSTRAINT IF EXISTS work_site_aprv_tmplt_uq CASCADE;
-- ALTER TABLE public.work_site_aprv_tmplt ADD CONSTRAINT work_site_aprv_tmplt_uq UNIQUE (work_site_id);
-- -- ddl-end --
--
-- object: public.matrl_clm_file | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_clm_file CASCADE;
CREATE TABLE public.matrl_clm_file (
    LIKE public.base_column,
    matrl_clm_no varchar(15) NOT NULL,
    file_seq smallint NOT NULL,
    use_yn varchar(1) NOT NULL DEFAULT 'Y',
    file_desc varchar(100),
    file_path varchar(300),
    file_nm varchar(50),
    CONSTRAINT matrl_clm_file_pk PRIMARY KEY (file_seq,matrl_clm_no)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_clm_file IS E'자재_청구_파일\n첨부파일 등';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_file.file_seq IS E'파일_순번';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_file.use_yn IS E'사용_여부';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_file.file_desc IS E'파일_설명';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_file.file_path IS E'파일_경로';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm_file.file_nm IS E'파일_이름';
-- ddl-end --

-- -- object: matrl_clm_fk | type: CONSTRAINT --
-- -- ALTER TABLE public.matrl_clm_file DROP CONSTRAINT IF EXISTS matrl_clm_fk CASCADE;
-- ALTER TABLE public.matrl_clm_file ADD CONSTRAINT matrl_clm_fk FOREIGN KEY (matrl_clm_no)
-- REFERENCES public.matrl_clm (matrl_clm_no) MATCH FULL
-- ON DELETE NO ACTION ON UPDATE NO ACTION;
-- -- ddl-end --
--
-- object: spl_compny_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_price DROP CONSTRAINT IF EXISTS spl_compny_fk CASCADE;
ALTER TABLE public.cmpny_matrl_price ADD CONSTRAINT spl_compny_fk FOREIGN KEY (spl_cmpny_id)
REFERENCES public.cmpny (cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --
COMMENT ON CONSTRAINT spl_compny_fk ON public.cmpny_matrl_price  IS E'공급_업체_fk';
-- ddl-end --


-- object: spl_cmpny_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_item_cntrt DROP CONSTRAINT IF EXISTS spl_cmpny_fk CASCADE;
ALTER TABLE public.cmpny_matrl_item_cntrt ADD CONSTRAINT spl_cmpny_fk FOREIGN KEY (spl_cmpny_id)
REFERENCES public.cmpny (cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --
COMMENT ON CONSTRAINT spl_cmpny_fk ON public.cmpny_matrl_item_cntrt  IS E'공급_업체_fk';
-- ddl-end --


-- object: spl_cmpny_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_price_req_mst DROP CONSTRAINT IF EXISTS spl_cmpny_fk CASCADE;
ALTER TABLE public.cmpny_matrl_price_req_mst ADD CONSTRAINT spl_cmpny_fk FOREIGN KEY (spl_cmpny_id)
REFERENCES public.cmpny (cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --
COMMENT ON CONSTRAINT spl_cmpny_fk ON public.cmpny_matrl_price_req_mst  IS E'공급_업체_fk';
-- ddl-end --



