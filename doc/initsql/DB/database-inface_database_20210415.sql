-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.3
-- PostgreSQL version: 13.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: inface_database | type: DATABASE --
-- DROP DATABASE IF EXISTS inface_database;
CREATE DATABASE inface_database
	ENCODING = 'UTF8';
-- ddl-end --


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
ALTER TABLE public.base_column OWNER TO postgres;
-- ddl-end --

-- object: public.matrl | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl CASCADE;
CREATE TABLE public.matrl (
	LIKE public.base_column,
	matrl_id varchar(10) NOT NULL,
	matrl_item_id varchar(7),
	matrl_std varchar(100),
	unit_cd varchar(4),
	matrl_desc varchar(200),
	matrl_img varchar(300),
	use_yn varchar(1) DEFAULT 'N',
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
ALTER TABLE public.matrl OWNER TO postgres;
-- ddl-end --

-- object: public.matrl_ctg | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_ctg CASCADE;
CREATE TABLE public.matrl_ctg (
	LIKE public.base_column,
	matrl_ctg_id varchar(4) NOT NULL,
	matrl_lctg_id varchar(2),
	ctg_nm varchar(50),
	ctg_desc varchar(200),
	disp_ordr smallint DEFAULT 0,
	use_yn varchar(1) DEFAULT 'N',
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
ALTER TABLE public.matrl_ctg OWNER TO postgres;
-- ddl-end --

-- object: public.matrl_item | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_item CASCADE;
CREATE TABLE public.matrl_item (
	LIKE public.base_column,
	matrl_item_id varchar(7) NOT NULL,
	matrl_ctg_id varchar(4),
	item_nm varchar(50),
	item_img varchar(300),
	item_desc varchar(200),
	disp_ordr smallint DEFAULT 0,
	use_yn varchar(1) DEFAULT 'N',
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
ALTER TABLE public.matrl_item OWNER TO postgres;
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
ALTER TABLE public.cmpny OWNER TO postgres;
-- ddl-end --

-- object: public.cmpny_matrl_item | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_item CASCADE;
CREATE TABLE public.cmpny_matrl_item (
	LIKE public.base_column,
	cmpny_id varchar(6) NOT NULL,
	matrl_item_id varchar(7) NOT NULL,
	buy_type_cd varchar(4),
	use_yn varchar(1) DEFAULT 'N',
	CONSTRAINT cmpny_matrl_item_pk PRIMARY KEY (matrl_item_id,cmpny_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_matrl_item IS E'업체_자재_품목\n건설사는 별도로 구매타입을 설정하지 않음.\n공급업체는 품목별로 구매타입을 지정해야 함.';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_item.buy_type_cd IS E'구매_타입_코드\n10 - 구매\n20 - 임대';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_item.use_yn IS E'사용_여부';
-- ddl-end --
ALTER TABLE public.cmpny_matrl_item OWNER TO postgres;
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
COMMENT ON COLUMN public.cmpny_matrl_price.lease_perd_cd IS E'임대_주기_코드\n구매방식코드=임대 인 경우\n월,일 등..';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price.price IS E'가격\n구매가격\n임대기본료';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price.lease_price IS E'임대_가격';
-- ddl-end --
ALTER TABLE public.cmpny_matrl_price OWNER TO postgres;
-- ddl-end --

-- object: public.work_site | type: TABLE --
-- DROP TABLE IF EXISTS public.work_site CASCADE;
CREATE TABLE public.work_site (
	LIKE public.base_column,
	work_site_id varchar(8) NOT NULL,
	site_nm varchar(100),
	use_yn varchar(1) DEFAULT 'N',
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
ALTER TABLE public.work_site OWNER TO postgres;
-- ddl-end --

-- object: public.matrl_clm | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_clm CASCADE;
CREATE TABLE public.matrl_clm (
	LIKE public.base_column,
	aprv_id varchar(10) NOT NULL,
	matrl_clm_no varchar(15),
	clm_stat_cd varchar(4),
	work_site_id varchar(8),
	clm_dt varchar(8),
	in_hope_dts timestamp,
	in_addr varchar(200),
	in_gate_no varchar(10),
	in_charge_nm varchar(20),
	in_charge_tel varchar(20),
	in_remark varchar(300),
	CONSTRAINT matrl_clm_pk PRIMARY KEY (aprv_id)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_clm IS E'자재_청구';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.matrl_clm_no IS E'자재_청구_번호\n결재ID 와는 다른 각 영역별로 유니크한 문서번호 부여..?';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.clm_stat_cd IS E'청구_상태_코드\n결재.결재상태..와 다르게 관리할까..\n전체 쳥구상태 중에서.. 일부상태가 결재.결재상태가 된다고 봐도 될듯한데..\n즉, 결재상태 완료 전/후로 청구로직만의 상태가 필요할수 있음.';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.clm_dt IS E'청구_일자';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_hope_dts IS E'입고_희망_일시';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_addr IS E'입고_주소';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_gate_no IS E'입고_게이트_번호\nex. 동3문?';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_charge_nm IS E'입고_담당자_이름';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_charge_tel IS E'입고_담당자_연락처';
-- ddl-end --
COMMENT ON COLUMN public.matrl_clm.in_remark IS E'입고_비고';
-- ddl-end --
ALTER TABLE public.matrl_clm OWNER TO postgres;
-- ddl-end --

-- object: public.matrl_clm_dtl | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_clm_dtl CASCADE;
CREATE TABLE public.matrl_clm_dtl (
	LIKE public.base_column,
	aprv_id varchar(10) NOT NULL,
	matrl_id varchar(10) NOT NULL,
	prev_clm_qty smallint DEFAULT 0,
	clm_qty smallint DEFAULT 0,
	aprv_qty smallint DEFAULT 0,
	req_desc varchar(300),
	remark varchar(300),
	CONSTRAINT matrl_clm_dtl_pk PRIMARY KEY (aprv_id,matrl_id)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_clm_dtl IS E'자재_청구_상세';
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
ALTER TABLE public.matrl_clm_dtl OWNER TO postgres;
-- ddl-end --

-- object: public.aprv | type: TABLE --
-- DROP TABLE IF EXISTS public.aprv CASCADE;
CREATE TABLE public.aprv (
	LIKE public.base_column,
	aprv_id varchar(10) NOT NULL,
	arpv_kind_cd varchar(4),
	stat_cd varchar(4),
	title varchar(100),
	drftr_id varchar(10),
	drft_dts timestamp,
	remark varchar(300),
	CONSTRAINT aprv_pk PRIMARY KEY (aprv_id)

);
-- ddl-end --
COMMENT ON TABLE public.aprv IS E'결재\n각 결재종류에 따른 상세정보는 각각의 테이블에 저장., 여기는 결재선등의 공통정보만..\n별도 모듈로 구현..\n각 영역에서는 결재ID 만 공유하고. 결재에 관한 정보는 모두 여기서 처리.';
-- ddl-end --
COMMENT ON COLUMN public.aprv.aprv_id IS E'결재_ID';
-- ddl-end --
COMMENT ON COLUMN public.aprv.arpv_kind_cd IS E'결재_종류_코드\n자재청구, 발주청구\n각종 결재 프로세스..';
-- ddl-end --
COMMENT ON COLUMN public.aprv.stat_cd IS E'상태_코드\n작성중\n결재중,\n완료\n반려\n등.';
-- ddl-end --
COMMENT ON COLUMN public.aprv.title IS E'제목';
-- ddl-end --
COMMENT ON COLUMN public.aprv.drftr_id IS E'기안자_ID';
-- ddl-end --
COMMENT ON COLUMN public.aprv.drft_dts IS E'기안_일시';
-- ddl-end --
COMMENT ON COLUMN public.aprv.remark IS E'비고';
-- ddl-end --
ALTER TABLE public.aprv OWNER TO postgres;
-- ddl-end --

-- object: public.matrl_lctg | type: TABLE --
-- DROP TABLE IF EXISTS public.matrl_lctg CASCADE;
CREATE TABLE public.matrl_lctg (
	LIKE public.base_column,
	matrl_lctg_id varchar(2) NOT NULL,
	lctg_nm varchar(50),
	lctg_desc varchar(200),
	disp_ordr smallint DEFAULT 0,
	use_yn varchar(1) DEFAULT 'N',
	CONSTRAINT matrl_lctg_pk PRIMARY KEY (matrl_lctg_id)

);
-- ddl-end --
COMMENT ON TABLE public.matrl_lctg IS E'자재_대분류';
-- ddl-end --
COMMENT ON COLUMN public.matrl_lctg.matrl_lctg_id IS E'자재_대분류_ID';
-- ddl-end --
COMMENT ON COLUMN public.matrl_lctg.lctg_nm IS E'대분류_이름';
-- ddl-end --
COMMENT ON COLUMN public.matrl_lctg.lctg_desc IS E'대분류_설명';
-- ddl-end --
COMMENT ON COLUMN public.matrl_lctg.disp_ordr IS E'노출_순서';
-- ddl-end --
COMMENT ON COLUMN public.matrl_lctg.use_yn IS E'사용_여부';
-- ddl-end --
ALTER TABLE public.matrl_lctg OWNER TO postgres;
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
ALTER TABLE public.comm_grp_cd OWNER TO postgres;
-- ddl-end --

-- object: public.comm_cd | type: TABLE --
-- DROP TABLE IF EXISTS public.comm_cd CASCADE;
CREATE TABLE public.comm_cd (
	LIKE public.base_column,
	comm_cd varchar(4) NOT NULL,
	comm_grp_cd varchar(4),
	cd_nm varchar(20),
	use_yn varchar(1) DEFAULT 'N',
	remark varchar(100),
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
ALTER TABLE public.comm_cd OWNER TO postgres;
-- ddl-end --

-- object: public.cmpny_matrl_item_cntrt | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_item_cntrt CASCADE;
CREATE TABLE public.cmpny_matrl_item_cntrt (
	LIKE public.base_column,
	cmpny_id varchar(6) NOT NULL,
	matrl_item_id varchar(7) NOT NULL,
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
COMMENT ON COLUMN public.cmpny_matrl_item_cntrt.cntrt_stat_cd IS E'계약_상태_코드(코드)\n10 - 검토중\n20 - 거래중\n40 - 거래중지\n90 - 삭제?';
-- ddl-end --
ALTER TABLE public.cmpny_matrl_item_cntrt OWNER TO postgres;
-- ddl-end --

-- object: public.cmpny_matrl_price_req | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_price_req CASCADE;
CREATE TABLE public.cmpny_matrl_price_req (
	LIKE public.base_column,
	cmpny_id varchar(6) NOT NULL,
	matrl_id varchar(10) NOT NULL,
	spl_cmpny_id varchar(6) NOT NULL,
	apl_strt_dt varchar(8) NOT NULL,
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
COMMENT ON COLUMN public.cmpny_matrl_price_req.req_stat_cd IS E'요청_상태_코드\n00 - 작성중\n10 - 진행중\n20 - 확정';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.req_dt IS E'요청_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.confirm_dt IS E'확정_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.buy_type_cd IS E'구매_타입_코드';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req.lease_perd_cd IS E'임대_주기_코드';
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
ALTER TABLE public.cmpny_matrl_price_req OWNER TO postgres;
-- ddl-end --

-- object: public.aprv_line | type: TABLE --
-- DROP TABLE IF EXISTS public.aprv_line CASCADE;
CREATE TABLE public.aprv_line (
	LIKE public.base_column,
	aprv_id varchar(10) NOT NULL,
	aprv_line_seq smallint NOT NULL DEFAULT 1,
	aprvr_id varchar(10),
	recv_dts timestamp,
	aprv_dts timestamp,
	aprv_stat_cd varchar(4),
	remark varchar(200),
	CONSTRAINT aprv_line_pk PRIMARY KEY (aprv_line_seq,aprv_id)

);
-- ddl-end --
COMMENT ON TABLE public.aprv_line IS E'결재_결재선\n결재, 합의.. 구분 필요할까..\n단순 순서만 있으면 될지.';
-- ddl-end --
COMMENT ON COLUMN public.aprv_line.aprv_line_seq IS E'결재선_순번\n순서만 있으면될까.. 순서를 수정하는 경우가 필요할까..\n삭제후 다시 넣기?';
-- ddl-end --
COMMENT ON COLUMN public.aprv_line.aprvr_id IS E'결재자_ID';
-- ddl-end --
COMMENT ON COLUMN public.aprv_line.recv_dts IS E'수신_일시\n결재자가 문서를 조회한 일시';
-- ddl-end --
COMMENT ON COLUMN public.aprv_line.aprv_dts IS E'결재_일시';
-- ddl-end --
COMMENT ON COLUMN public.aprv_line.aprv_stat_cd IS E'결재_상태_코드\n결재,반려';
-- ddl-end --
COMMENT ON COLUMN public.aprv_line.remark IS E'비고\n반려사유등.';
-- ddl-end --
ALTER TABLE public.aprv_line OWNER TO postgres;
-- ddl-end --

-- object: public.cmpny_user | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_user CASCADE;
CREATE TABLE public.cmpny_user (
	LIKE public.base_column,
	cmpny_user_id varchar(10) NOT NULL,
	cmpny_id varchar(6),
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
	CONSTRAINT cmpny_user_pk PRIMARY KEY (cmpny_user_id),
	CONSTRAINT login_id_uq UNIQUE (login_id)

);
-- ddl-end --
COMMENT ON TABLE public.cmpny_user IS E'업체_사용자\n근로자..와 구분하는게 나을듯..\n건설사.공급업체?\n사용자? 뭐라고 정의할까..';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.cmpny_user_id IS E'업체_사용자_ID';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.user_type_cd IS E'사용자_유형_코드\n00 - 관리자\n10 - (업체_유형_코드)건설사\n20 - (업체_유형_코드)자재공급업체';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.user_nm IS E'사용자_이름';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.login_id IS E'로그인_ID';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.pwd IS E'비밀번호';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_user.stat_cd IS E'상태_코드\n10 - 가입요청\n20 - 사용중\n30 - 사용중지';
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
ALTER TABLE public.cmpny_user OWNER TO postgres;
-- ddl-end --

-- object: matrl_lctg_fk | type: CONSTRAINT --
-- ALTER TABLE public.matrl_ctg DROP CONSTRAINT IF EXISTS matrl_lctg_fk CASCADE;
ALTER TABLE public.matrl_ctg ADD CONSTRAINT matrl_lctg_fk FOREIGN KEY (matrl_lctg_id)
REFERENCES public.matrl_lctg (matrl_lctg_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: matrl_item_fk | type: CONSTRAINT --
-- ALTER TABLE public.matrl DROP CONSTRAINT IF EXISTS matrl_item_fk CASCADE;
ALTER TABLE public.matrl ADD CONSTRAINT matrl_item_fk FOREIGN KEY (matrl_item_id)
REFERENCES public.matrl_item (matrl_item_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: matrl_ctg_fk | type: CONSTRAINT --
-- ALTER TABLE public.matrl_item DROP CONSTRAINT IF EXISTS matrl_ctg_fk CASCADE;
ALTER TABLE public.matrl_item ADD CONSTRAINT matrl_ctg_fk FOREIGN KEY (matrl_ctg_id)
REFERENCES public.matrl_ctg (matrl_ctg_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: aprv_fk | type: CONSTRAINT --
-- ALTER TABLE public.aprv_line DROP CONSTRAINT IF EXISTS aprv_fk CASCADE;
ALTER TABLE public.aprv_line ADD CONSTRAINT aprv_fk FOREIGN KEY (aprv_id)
REFERENCES public.aprv (aprv_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: aprv_fk | type: CONSTRAINT --
-- ALTER TABLE public.matrl_clm DROP CONSTRAINT IF EXISTS aprv_fk CASCADE;
ALTER TABLE public.matrl_clm ADD CONSTRAINT aprv_fk FOREIGN KEY (aprv_id)
REFERENCES public.aprv (aprv_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: matrl_clm_uq | type: CONSTRAINT --
-- ALTER TABLE public.matrl_clm DROP CONSTRAINT IF EXISTS matrl_clm_uq CASCADE;
ALTER TABLE public.matrl_clm ADD CONSTRAINT matrl_clm_uq UNIQUE (aprv_id);
-- ddl-end --

-- object: work_site_fk | type: CONSTRAINT --
-- ALTER TABLE public.matrl_clm DROP CONSTRAINT IF EXISTS work_site_fk CASCADE;
ALTER TABLE public.matrl_clm ADD CONSTRAINT work_site_fk FOREIGN KEY (work_site_id)
REFERENCES public.work_site (work_site_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: matrl_clm_fk | type: CONSTRAINT --
-- ALTER TABLE public.matrl_clm_dtl DROP CONSTRAINT IF EXISTS matrl_clm_fk CASCADE;
ALTER TABLE public.matrl_clm_dtl ADD CONSTRAINT matrl_clm_fk FOREIGN KEY (aprv_id)
REFERENCES public.matrl_clm (aprv_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: matrl_fk | type: CONSTRAINT --
-- ALTER TABLE public.matrl_clm_dtl DROP CONSTRAINT IF EXISTS matrl_fk CASCADE;
ALTER TABLE public.matrl_clm_dtl ADD CONSTRAINT matrl_fk FOREIGN KEY (matrl_id)
REFERENCES public.matrl (matrl_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: matrl_item_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_item DROP CONSTRAINT IF EXISTS matrl_item_fk CASCADE;
ALTER TABLE public.cmpny_matrl_item ADD CONSTRAINT matrl_item_fk FOREIGN KEY (matrl_item_id)
REFERENCES public.matrl_item (matrl_item_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: cmpny_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_item DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
ALTER TABLE public.cmpny_matrl_item ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
REFERENCES public.cmpny (cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: cmpny_fk | type: CONSTRAINT --
-- ALTER TABLE public.work_site DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
ALTER TABLE public.work_site ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
REFERENCES public.cmpny (cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: cmpny_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_user DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
ALTER TABLE public.cmpny_user ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
REFERENCES public.cmpny (cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: cmpny_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_price DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
ALTER TABLE public.cmpny_matrl_price ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
REFERENCES public.cmpny (cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: matrl_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_price DROP CONSTRAINT IF EXISTS matrl_fk CASCADE;
ALTER TABLE public.cmpny_matrl_price ADD CONSTRAINT matrl_fk FOREIGN KEY (matrl_id)
REFERENCES public.matrl (matrl_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: matrl_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_price_req DROP CONSTRAINT IF EXISTS matrl_fk CASCADE;
ALTER TABLE public.cmpny_matrl_price_req ADD CONSTRAINT matrl_fk FOREIGN KEY (matrl_id)
REFERENCES public.matrl (matrl_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: comm_grp_cd_fk | type: CONSTRAINT --
-- ALTER TABLE public.comm_cd DROP CONSTRAINT IF EXISTS comm_grp_cd_fk CASCADE;
ALTER TABLE public.comm_cd ADD CONSTRAINT comm_grp_cd_fk FOREIGN KEY (comm_grp_cd)
REFERENCES public.comm_grp_cd (comm_grp_cd) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: public.cmpny_matrl_price_req_mst | type: TABLE --
-- DROP TABLE IF EXISTS public.cmpny_matrl_price_req_mst CASCADE;
CREATE TABLE public.cmpny_matrl_price_req_mst (
	LIKE public.base_column,
	cmpny_id varchar(6) NOT NULL,
	spl_cmpny_id varchar(6) NOT NULL,
	apl_strt_dt varchar(8) NOT NULL,
	apl_end_dt varchar(8),
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
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.req_stat_cd IS E'요청_상태_코드\n00 - 작성중\n10 - 진행중\n20 - 확정';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.req_dt IS E'요청_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.confirm_dt IS E'확정_일자';
-- ddl-end --
COMMENT ON COLUMN public.cmpny_matrl_price_req_mst.remark IS E'비고';
-- ddl-end --
ALTER TABLE public.cmpny_matrl_price_req_mst OWNER TO postgres;
-- ddl-end --

-- object: cmpny_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_price_req_mst DROP CONSTRAINT IF EXISTS cmpny_fk CASCADE;
ALTER TABLE public.cmpny_matrl_price_req_mst ADD CONSTRAINT cmpny_fk FOREIGN KEY (cmpny_id)
REFERENCES public.cmpny (cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: cmpny_matrl_price_req_mst_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_price_req DROP CONSTRAINT IF EXISTS cmpny_matrl_price_req_mst_fk CASCADE;
ALTER TABLE public.cmpny_matrl_price_req ADD CONSTRAINT cmpny_matrl_price_req_mst_fk FOREIGN KEY (apl_strt_dt,spl_cmpny_id,cmpny_id)
REFERENCES public.cmpny_matrl_price_req_mst (apl_strt_dt,spl_cmpny_id,cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: cmpny_matrl_item_fk | type: CONSTRAINT --
-- ALTER TABLE public.cmpny_matrl_item_cntrt DROP CONSTRAINT IF EXISTS cmpny_matrl_item_fk CASCADE;
ALTER TABLE public.cmpny_matrl_item_cntrt ADD CONSTRAINT cmpny_matrl_item_fk FOREIGN KEY (matrl_item_id,cmpny_id)
REFERENCES public.cmpny_matrl_item (matrl_item_id,cmpny_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: public.v_matrl_item | type: VIEW --
-- DROP VIEW IF EXISTS public.v_matrl_item CASCADE;
CREATE VIEW public.v_matrl_item
AS 

SELECT
        ML.MATRL_LCTG_ID, ML.LCTG_NM, ML.LCTG_DESC, ML.DISP_ORDR AS LCTG_DISP_ORDR, ML.USE_YN AS LCTG_USE_YN
      , MC.MATRL_CTG_ID, MC.CTG_NM, MC.CTG_DESC, MC.DISP_ORDR AS CTG_DISP_ORDR, MC.USE_YN AS CTG_USE_YN
      , MI.MATRL_ITEM_ID, MI.ITEM_NM, MI.ITEM_DESC, MI.ITEM_IMG, MI.DISP_ORDR, MI.USE_YN
FROM
        MATRL_LCTG ML
      , MATRL_CTG MC
      , MATRL_ITEM MI
WHERE   1 = 1
AND     ML.MATRL_LCTG_ID = MC.MATRL_LCTG_ID
AND     MC.MATRL_CTG_ID = MI.MATRL_CTG_ID;
-- ddl-end --
COMMENT ON VIEW public.v_matrl_item IS E'뷰_자재_품목';
-- ddl-end --
ALTER VIEW public.v_matrl_item OWNER TO postgres;
-- ddl-end --

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



