-------------------------------------------------SQL문작성---------------------------------------------
/*
코딩컨벤션
1. CREATE TABLE, DEFAULT, UNIQUE, CONSTRAINT, VARCHAR2 등 sql명령어는 대문자로 쓰기. -> 컬럼명, 테이블명 제외하고 모두 대문자라고 생각하세요.
2. 테이블 명은 첫글자만 대문자, 나머지 소문자로 쓰기
3. 컬럼명은 소문자로 쓰기
4. primary key, foreign key는 마지막에 따로 쓰기
*/
------------------------------------------------------------------------------------------------------------------

--------------------------------------------------회원----------------------------------------------------------
CREATE TABLE Member(
	mem_num NUMBER NOT NULL,
	id VARCHAR2(30) NOT NULL,                   /*멤버 아이디*/
	auth NUMBER(1,0) DEFAULT 2 NOT NULL, /* (0:탈퇴회원 1:정지회원 2:일반회원 3:관리자) */
	
	CONSTRAINT member_pk PRIMARY KEY (mem_num)
);

CREATE TABLE Member_detail(
	mem_num NUMBER NOT NULL,   /*멤버 번호*/
	passwd VARCHAR2(30) NOT NULL,          /*멤버 비밀번호*/
	email VARCHAR2(50) NOT NULL,           /*멤버 이메일*/
	nickname VARCHAR2(50) NOT NULL,        /*멤버 닉네임*/
	major VARCHAR2(50),       /*학과*/
	stu_num VARCHAR2(10),   /*학번*/
	zipcode NUMBER(5,0),  /*우편번호*/
	address1 VARCHAR2(100),  /*주소*/
	address2 VARCHAR2(100),/*나머지주소*/ 
	reg_date DATE DEFAULT SYSDATE NOT NULL,          /*멤버 가입일*/
	modify_date DATE DEFAULT SYSDATE NOT NULL, /*멤버 수정일*/
	photo BLOB,        /*프로필사진 파일*/
	photoname VARCHAR2(100),    /*프로필 사진이름*/
	authkey VARCHAR2(15) , /*이메일 인증키*/
	authstatus NUMBER(1,0) DEFAULT 0, /*0이면로그인불가 1이면가능 */
	CONSTRAINT member_detail_pk PRIMARY KEY (mem_num),
	CONSTRAINT member_detail_fk FOREIGN KEY(mem_num) REFERENCES Member (mem_num),
	CONSTRAINT member_uk UNIQUE (email, nickname)
 );
 
-----------------------------------------------------게시판-------------------------------------------------------------
------------------------------------------   자유 게시판 -------------------------------------------------
CREATE TABLE FreeBoard(
	post_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	reg_date DATE DEFAULT SYSDATE NOT NULL,
	modify_date DATE DEFAULT SYSDATE NOT NULL,
	uploadfile BLOB,
	filename VARCHAR2(100),
	anonymous NUMBER(1) DEFAULT 1 NOT NULL,  /* (0:미허용 1:허용) */

	CONSTRAINT freeboard_pk PRIMARY KEY (post_num),
	CONSTRAINT freeboard_fk FOREIGN KEY (mem_num) REFERENCES Member (mem_num)
);

---------------------------------------- 태그 ----------------------------------------------
CREATE TABLE Tag(
	tag_num NUMBER NOT NULL,
	tag_name VARCHAR2(15) NOT NULL,
	
	CONSTRAINT tag_pk PRIMARY KEY (tag_num),
	CONSTRAINT tag_uk UNIQUE (tag_name)
);

--------------------------------------------- 정보 게시판----------------------------------------------------
CREATE TABLE Infoboard(
   post_num NUMBER NOT NULL,
   mem_num NUMBER NOT NULL,
   tag_num NUMBER,
   title VARCHAR2(100) NOT NULL,
   content CLOB NOT NULL,
   reg_date DATE DEFAULT SYSDATE NOT NULL,
   modify_date DATE DEFAULT SYSDATE NOT NULL,
   uploadfile BLOB,
   filename VARCHAR2(100),
    anonymous NUMBER(1) DEFAULT 1 NOT NULL,  /* (0:미허용 1:허용) */

   CONSTRAINT Infoboard_pk PRIMARY KEY (post_num),
   CONSTRAINT Infoboard_mem_fk FOREIGN KEY (mem_num) REFERENCES Member (mem_num),
   CONSTRAINT Infoboard_tag_fk FOREIGN KEY (tag_num) REFERENCES Tag (tag_num)
);


--------------------------------   사용자 생성 게시판 -----------------------------------
CREATE TABLE CustomBoard(
	board_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL, 
	title VARCHAR2(100) NOT NULL,
	subtitle VARCHAR2(200),
	type NUMBER(1) DEFAULT 0 NOT NULL, /* (0:기본형 1:사진형) */
	anonymous NUMBER(1) DEFAULT 1 NOT NULL,  /* (0:미허용 1:허용) */

	CONSTRAINT customboard_pk PRIMARY KEY (board_num),
	CONSTRAINT customboard_fk FOREIGN KEY (mem_num) REFERENCES Member (mem_num)
);

----------------------------사용자 생성 게시판의 게시글---------------------------
CREATE TABLE CustomPost(
	post_num NUMBER NOT NULL,
	board_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL,
	content CLOB NOT NULL,
	reg_date DATE DEFAULT SYSDATE NOT NULL,
	modify_date DATE DEFAULT SYSDATE NOT NULL,
	uploadfile BLOB,
	filename VARCHAR2(100),
	anonymous NUMBER(1) DEFAULT 1 NOT NULL,  /* (0:미허용 1:허용) */

	CONSTRAINT custompost_pk PRIMARY KEY (post_num),
	CONSTRAINT custompost_board_fk FOREIGN KEY (board_num) REFERENCES CustomBoard (board_num),
	CONSTRAINT custompost_mem_fk FOREIGN KEY (mem_num) REFERENCES Member (mem_num)
);


---------------------------------------------해시태그----------------------------------------------------
CREATE TABLE Hashtag(
	hashtag_num NUMBER NOT NULL,
	name VARCHAR2(100) NOT NULL,
	
	CONSTRAINT hashtag_pk PRIMARY KEY (hashtag_num),
	CONSTRAINT hashtag_uk UNIQUE (name)
);
--해시태그,게시글 관계 테이블은 post 외래키가 모호하므로 아직 생성하지 않음--
--------------------------------------------------댓글----------------------------------------------------

CREATE TABLE FreeBoard_Comment(
	comment_num NUMBER NOT NULL,
	post_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL,
	content CLOB NOT NULL,
	reg_date  DATE DEFAULT SYSDATE NOT NULL,
	anonymous NUMBER(1) NOT NULL,

	CONSTRAINT freecomment_pk PRIMARY KEY(comment_num),
	CONSTRAINT freecomment_fk FOREIGN KEY (post_num) REFERENCES FreeBoard (post_num),
	CONSTRAINT freecomment_fk2 FOREIGN KEY (mem_num) REFERENCES Member (mem_num)			
);

-------------------------------------------------- 추천 -----------------------------------------------


-------------------------------------------------- 신고 -----------------------------------------------


--------------------------------------- 즐겨찾기 -----------------------------------------------


--------------------------------------------------쪽지-----------------------------------------------------------


--------------------------------------------------시간표-----------------------------------------------------------

CREATE TABLE Timetable(
	t_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL,
	semester VARCHAR2(6) NOT NULL,
	t_name VARCHAR2(50) DEFAULT '시간표' NOT NULL,
	reg_date DATE DEFAULT SYSDATE NOT NULL,
	modify_date DATE DEFAULT SYSDATE NOT NULL,
	isPrimary NUMBER DEFAULT 0 NOT NULL,

	CONSTRAINT timetable_pk PRIMARY KEY (t_num),
	CONSTRAINT timetable_mem_fk FOREIGN KEY (mem_num) REFERENCES Member (mem_num)
);

CREATE TABLE Subject(
	sub_num NUMBER NOT NULL,
	sub_category VARCHAR2(20) NOT NULL,
	sub_name VARCHAR2(100) NOT NULL,
	prof_name VARCHAR2(50) NOT NULL,
	sub_credit NUMBER DEFAULT 3 NOT NULL ,
	sub_time VARCHAR2(50) NOT NULL,
	sub_classRoom VARCHAR2(50) NOT NULL,
	sub_capacity NUMBER DEFAULT 100 NOT NULL ,
	sub_online NUMBER(1) DEFAULT 0 NOT NULL ,	/*사이버강의여부 0:대면강의, 1:사이버강의*/
	sub_remark VARCHAR2(300),

	CONSTRAINT subject_pk PRIMARY KEY (sub_num)
);


CREATE TABLE Timetable_Subject(
	ts_num NUMBER NOT NULL,
	t_num NUMBER NOT NULL,
	sub_num NUMBER NOT NULL,

	CONSTRAINT timetable_subject_pk PRIMARY KEY (ts_num),
	CONSTRAINT timetable_subject_t_fk FOREIGN KEY (t_num) REFERENCES Timetable (t_num),
	CONSTRAINT timetable_subject_s_fk FOREIGN KEY (sub_num) REFERENCES Subject (sub_num)
);

CREATE TABLE CustomSubject(
	csub_num NUMBER NOT NULL,
	t_num NUMBER NOT NULL,
	csub_name VARCHAR2(100) NOT NULL,
	prof_name VARCHAR2(50),
	csub_time VARCHAR2(50),
	csub_classRoom VARCHAR2(50),

	CONSTRAINT customSubject_pk PRIMARY KEY (csub_num),
	CONSTRAINT customSubject_fk FOREIGN KEY (t_num) REFERENCES Timetable (t_num)
);
--------------------------------------------------책방-----------------------------------------------------------

CREATE TABLE BookStoreBoard(
    bs_num NUMBER NOT NULL ,
    mem_num NUMBER NOT NULL,
    bs_selling_price NUMBER NOT NULL,
    bs_comment VARCHAR2(300),
    bs_condition VARCHAR2(100) NOT NULL,
    bs_method VARCHAR2(100) NOT NULL,
    bs_address VARCHAR2(300),
    bs_complete NUMBER(1) DEFAULT 0 NOT NULL, /* (0:미완료 1:완료) */
    reg_date DATE DEFAULT SYSDATE NOT NULL,
    modify_date DATE DEFAULT SYSDATE NOT NULL,
    uploadfile BLOB,
    filename VARCHAR2(100),
    isbn VARCHAR2(100) NOT NULL

    CONSTRAINT bookstoreboard_pk PRIMARY KEY (bs_num),
    CONSTRAINT bookstoreboard_mem_fk FOREIGN KEY (mem_num) REFERENCES Member (mem_num)
);
 
-----------------------------------------------SEQUENCE--------------------------------------------
CREATE SEQUENCE member_seq START WITH 1000;	--Member의 mem_num


CREATE SEQUENCE freeBoard_post_seq START WITH 10000;  --FreeBoard의 post_num
CREATE SEQUENCE infoBoard_post_seq START WITH 20000;	--InfoBoard의 post_num
CREATE SEQUENCE customBoard_seq START WITH 30000; --CustomBoard의 board_num
CREATE SEQUENCE customPost_seq START WITH 30000; --CustomPost의 post_num


CREATE SEQUENCE tag_seq START WITH 100;	-- Tag의 tag_num
CREATE SEQUENCE hashtag_seq START WITH 200; --Hashtag의 hashtag_num


CREATE SEQUENCE free_Reply_seq START WITH 1 INCREMENT BY 1 MAXVALUE 10000; --FreeBoard_Comment의comment_num


CREATE SEQUENCE timetable_seq START WITH 40000; --Timetable의 t_num
CREATE SEQUENCE subject_seq START WITH 300; --Subject의 sub_num
CREATE SEQUENCE csub_seq START WITH 400; --CustomSubject의 csub_num
CREATE SEQUENCE timetableSubject_seq START WITH 500; --Timetable_Subject의 ts_num


CREATE SEQUENCE bookstore_seq START WITH 50000; --BookStoreBoard의 bs_num



------------------------------------------------------------------------------------------------
------------------------------------------Data 삽입 ----------------------------------------
/* 관리자 입력 */
INSERT INTO Member (mem_num, id, auth) VALUES (1, 'admin', 3);
INSERT INTO Member_Detail (mem_num, passwd, email, nickname, zipcode, address1, address2) VALUES (1, '1234', 'admin@test.com', 'Admin', 12345, '서울시 강남구', '역삼동 역삼푸르지오A');


/* 과목 입력 */
INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online, sub_remark) 
VALUES (subject_seq.nextval, '전선', '컴퓨터그래픽스', '박준', 3, '화45,금5', 'T802,T802', 60, 0, '선수과목:자료구조');

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online, sub_remark) 
VALUES (subject_seq.nextval, '전필', '운영체제', '김선일', 3, '수56,금7', 'T701,T702', 60, 0, '선수과목:자료구조');

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online, sub_remark) 
VALUES (subject_seq.nextval, '전필', '운영체제', '이장호', 3, '월7,목7,금7', 'T502,T502,T502', 60, 0, '선수과목:자료구조');

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전선', '오토마타', '정균락', 3, '월9,화9,수9', 'T502,T502,T502', 50, 0);

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전선', '기초데이터베이스', '김경창', 4, '월6,화23,목6', 'T701,T701,T602', 60, 0);

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전선', '기초데이터베이스', '김경창', 4, '월6,화23,목6', 'T701,T701,T602', 60, 0);

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전선', '네트워크프로그래밍', '김한규', 3, '화78,수7', 'T701,T801', 90, 0);

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전필', '자료구조 및 프로그래밍', '배성일', 4, '목89,금89', 'T801,T602', 55, 0);

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전필', '자료구조 및 프로그래밍', '송하윤', 4, '월89,수89', 'T602,T701', 55, 0);

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전필', '논리회로 설계 및 실험', '이준용', 4, '월67,화23', 'T503,T503', 80, 0);

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전선', '어셈블리언어 및 실습', '권건우', 3, '화4,수4,목4', 'T505,T505,T505', 40, 0);

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전선', '데이터통신', '심영철', 3, '월2,수2,목2', 'T801,T801,T801', 50, 0);

INSERT INTO Subject (sub_num, sub_category, sub_name, prof_name, sub_credit, sub_time, sub_classRoom, sub_capacity, sub_online) 
VALUES (subject_seq.nextval, '전선', '임베디드시스템 및 실험', '표창우', 3, '금234', 'T701', 40, 0);





