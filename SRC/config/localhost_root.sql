CREATE USER root
IDENTIFIED BY root
DEFAULT tablespace users
TEMPORARY tablespace temp;


GRANT resource, connect TO root;

CONN root/root;



/* ���� */
CREATE TABLE ROOT.N_USER (
	U_ID VARCHAR2(15) NOT NULL, /* ���̵� */
	U_PW VARCHAR2(15) NOT NULL, /* �н����� */
	U_NIK VARCHAR2(20) NOT NULL, /* �г��� */
	U_EMAIL VARCHAR2(20) NOT NULL, /* �̸��� */
	JOINDATE CHAR(10) NOT NULL, /* ������ */
	START_DAY VARCHAR2(10) NOT NULL, /* ����������� */
	END_DAY VARCHAR2(10) NOT NULL, /* ����������� */
	U_LEVEL CHAR(1) NOT NULL, /* ��� */
	GOLD NUMBER(4) NOT NULL, /* ������Ƚ�� */
	SILVER NUMBER(4) NOT NULL, /* �ǹ�����Ƚ�� */
	BRONZE NUMBER(4) NOT NULL /* ��������Ƚ�� */
);

COMMENT ON TABLE ROOT.N_USER IS '����';

COMMENT ON COLUMN ROOT.N_USER.U_ID IS '���̵�';

COMMENT ON COLUMN ROOT.N_USER.U_PW IS '�н�����';

COMMENT ON COLUMN ROOT.N_USER.U_NIK IS '�г���';

COMMENT ON COLUMN ROOT.N_USER.U_EMAIL IS '�̸���';

COMMENT ON COLUMN ROOT.N_USER.JOINDATE IS '������';

COMMENT ON COLUMN ROOT.N_USER.START_DAY IS '�����������';

COMMENT ON COLUMN ROOT.N_USER.END_DAY IS '�����������';

COMMENT ON COLUMN ROOT.N_USER.U_LEVEL IS '���';

COMMENT ON COLUMN ROOT.N_USER.GOLD IS '������Ƚ��';

COMMENT ON COLUMN ROOT.N_USER.SILVER IS '�ǹ�����Ƚ��';

COMMENT ON COLUMN ROOT.N_USER.BRONZE IS '��������Ƚ��';

CREATE UNIQUE INDEX ROOT.PK_N_USER
	ON ROOT.N_USER (
		U_ID ASC
	);

ALTER TABLE ROOT.N_USER
	ADD
		CONSTRAINT PK_N_USER
		PRIMARY KEY (
			U_ID
		);

/* �̵�� */
CREATE TABLE ROOT.MEDIA (
	MEDIA_ID NUMBER(10) NOT NULL, /* �̵����̵� */
	MEDIA_NM VARCHAR2(40) NOT NULL, /* �̵���̸� */
	NATION VARCHAR2(15) NOT NULL, /* ���� */
	MD_CATEGORY VARCHAR2(10) NOT NULL, /* ī�װ� */
	GENRE VARCHAR2(10) NOT NULL, /* �帣 */
	M_DATE VARCHAR2(10) NOT NULL, /* ��ó�� */
	RATING VARCHAR2(10) NOT NULL, /* ��û���ɵ�� */
	AUDIENCE NUMBER(10) NOT NULL /* ��ȸ�� */
);

COMMENT ON TABLE ROOT.MEDIA IS '�̵��';

COMMENT ON COLUMN ROOT.MEDIA.MEDIA_ID IS '�̵����̵�';

COMMENT ON COLUMN ROOT.MEDIA.MEDIA_NM IS '�̵���̸�';

COMMENT ON COLUMN ROOT.MEDIA.NATION IS '����';

COMMENT ON COLUMN ROOT.MEDIA.MD_CATEGORY IS 'ī�װ�';

COMMENT ON COLUMN ROOT.MEDIA.GENRE IS '�帣';

COMMENT ON COLUMN ROOT.MEDIA.M_DATE IS '��ó��';

COMMENT ON COLUMN ROOT.MEDIA.RATING IS '��û���ɵ��';

COMMENT ON COLUMN ROOT.MEDIA.AUDIENCE IS '��ȸ��';

CREATE UNIQUE INDEX ROOT.PK_MEDIA
	ON ROOT.MEDIA (
		MEDIA_ID ASC
	);

ALTER TABLE ROOT.MEDIA
	ADD
		CONSTRAINT PK_MEDIA
		PRIMARY KEY (
			MEDIA_ID
		);

/* ��û��� */
CREATE TABLE ROOT.MYMEDIA (
	MY_M_ID NUMBER(10) NOT NULL, /* ���̵��ID */
	MEDIA_ID NUMBER(10) NOT NULL, /* �̵����̵� */
	U_ID VARCHAR2(15) NOT NULL /* ���̵� */
);

COMMENT ON TABLE ROOT.MYMEDIA IS '��û���';

COMMENT ON COLUMN ROOT.MYMEDIA.MY_M_ID IS '���̵��ID';

COMMENT ON COLUMN ROOT.MYMEDIA.MEDIA_ID IS '�̵����̵�';

COMMENT ON COLUMN ROOT.MYMEDIA.U_ID IS '���̵�';

CREATE UNIQUE INDEX ROOT.PK_MYMEDIA
	ON ROOT.MYMEDIA (
		MY_M_ID ASC
	);

ALTER TABLE ROOT.MYMEDIA
	ADD
		CONSTRAINT PK_MYMEDIA
		PRIMARY KEY (
			MY_M_ID
		);

ALTER TABLE ROOT.MYMEDIA
	ADD
		CONSTRAINT FK_N_USER_TO_MYMEDIA
		FOREIGN KEY (
			U_ID
		)
		REFERENCES ROOT.N_USER (
			U_ID
		);

ALTER TABLE ROOT.MYMEDIA
	ADD
		CONSTRAINT FK_MEDIA_TO_MYMEDIA
		FOREIGN KEY (
			MEDIA_ID
		)
		REFERENCES ROOT.MEDIA (
			MEDIA_ID
		);


create sequence media_seq
increment by 1
start with 1;

create sequence MY_MEDIA_SEQ
increment by 1
start with 1;


insert into N_USER(U_ID,U_PW,U_NIK,U_EMAIL,U_LEVEL,JOINDATE,START_DAY,END_DAY,GOLD,SILVER,BRONZE)values ('admin','admin','admin','admin@naver.com','0','2019/06/06','2019/06/06','2019/06/06',0,0,0);

CREATE OR REPLACE PROCEDURE sp_user_selectAll
(
    user_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN user_record FOR
    SELECT * 
    FROM N_USER
    ORDER BY JOINDATE ASC;
    
    
END;
/

CREATE OR REPLACE PROCEDURE sp_user_selectId
(
    v_userId    IN  N_USER.U_ID%TYPE,
    user_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN user_record FOR
    SELECT *
    FROM N_USER
    WHERE U_ID = v_userId;
   
END;
/

CREATE OR REPLACE PROCEDURE sp_user_selectEmail
(
    v_userEmail IN N_USER.U_EMAIL%TYPE,
    user_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN user_record FOR
    SELECT * 
    FROM N_USER 
    WHERE U_EMAIL = v_userEmail;
    
   
END;
/

CREATE OR REPLACE PROCEDURE sp_user_selectNickname
(
   v_userNickname IN N_USER.U_NIK%TYPE,
    user_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN user_record FOR
    SELECT * 
    FROM N_USER 
    WHERE U_NIK = v_userNickname;
    
   
END;
/

CREATE OR REPLACE PROCEDURE sp_user_insert
(
  v_u_ID    IN  N_USER.U_ID%TYPE,
  v_U_PW    IN  N_USER.U_PW%TYPE,
  v_U_NIK   IN  N_USER.U_NIK%TYPE,
  v_U_EMAIL IN  N_USER.U_EMAIL%TYPE,
  v_U_LEVEL IN  N_USER.U_LEVEL%TYPE,
  v_JOINDATE    IN  N_USER.JOINDATE%TYPE,
  v_START_DAY   IN  N_USER.START_DAY%TYPE,
  v_END_DAY IN  N_USER.END_DAY%TYPE,
  v_GOLD    IN  N_USER.GOLD%TYPE,
  v_SILVER  IN  N_USER.SILVER%TYPE,
  v_BRONZE  IN  N_USER.BRONZE%TYPE
)
IS
BEGIN
    INSERT INTO N_USER(U_ID,U_PW,U_NIK,U_EMAIL,U_LEVEL,JOINDATE,START_DAY,END_DAY,GOLD,SILVER,BRONZE)
    VALUES(v_u_ID,v_U_PW,v_U_NIK,v_U_EMAIL,v_U_LEVEL,v_JOINDATE,v_START_DAY,v_END_DAY,v_GOLD,v_SILVER,v_BRONZE);
                
    COMMIT;
   
END;
/


CREATE OR REPLACE PROCEDURE sp_user_delete
(
    v_u_id IN N_USER.U_ID%TYPE
)
IS
BEGIN
    DELETE FROM N_USER
    WHERE U_ID = v_u_id;
    
    COMMIT;
    
   
END;
/

CREATE OR REPLACE PROCEDURE sp_user_updatePassword
(
    v_userpw    IN   N_USER.U_PW%TYPE,
    v_userid    IN   N_USER.U_ID%TYPE
)
IS
BEGIN
    UPDATE N_USER 
    SET  U_PW = v_userpw
    where U_ID =v_userid;
    
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE sp_user_updateNickname
(
    v_userNickname  IN   N_USER.U_NIK%TYPE,
    v_userid        IN   N_USER.U_ID%TYPE
)
IS
BEGIN
    UPDATE N_USER 
    SET U_NIK = v_userNickname
    WHERE U_ID = v_userid;
    
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE sp_user_updateEmail
(
    
    v_userEmail IN N_USER.U_EMAIL%TYPE,
    v_userid IN N_USER.U_ID%TYPE
)
IS
BEGIN
    UPDATE N_USER 
    SET U_EMAIL = v_userEmail
    WHERE U_ID = v_userid;
    
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE sp_user_updateUserLevel
(
    
    v_userlevel IN N_USER.U_LEVEL%TYPE,
    v_startday  IN N_USER.START_DAY%TYPE,
    v_endday    IN N_USER.END_DAY%TYPE,
    v_gold      IN N_USER.GOLD%TYPE,
    v_silver    IN N_USER.SILVER%TYPE,
    v_bronze    IN N_USER.BRONZE%TYPE,
    v_userid    IN N_USER.U_ID%TYPE
)
IS
BEGIN
    UPDATE N_USER 
    SET U_LEVEL = v_userlevel ,
        START_DAY = v_startday , 
        END_DAY =  v_endday , 
        GOLD =  v_gold  , 
        SILVER = v_silver , 
        BRONZE =  v_bronze 
    WHERE U_ID = v_userid;
    
   
    
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE sp_media_selectAll
(
    media_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN media_record FOR
    SELECT * 
    FROM MEDIA 
    ORDER BY MEDIA_ID ASC;
    
    
END;
/

CREATE OR REPLACE PROCEDURE sp_media_selectbyview
(
 media_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN  media_record FOR
    SELECT * 
    FROM MEDIA 
    ORDER BY AUDIENCE DESC;
END;
/

CREATE OR REPLACE PROCEDURE sp_media_insert
(
    v_medianm   IN  MEDIA.MEDIA_NM%TYPE,
    v_nation    IN  MEDIA.NATION%TYPE,
    v_category  IN  MEDIA.MD_CATEGORY%TYPE,
    v_genre     IN  MEDIA.GENRE%TYPE,
    v_date      IN  MEDIA.M_DATE%TYPE,
    v_rating    IN  MEDIA.RATING%TYPE,
    v_audience  IN  MEDIA. AUDIENCE%TYPE
)
IS
BEGIN
   INSERT INTO MEDIA(MEDIA_ID,MEDIA_NM,NATION,MD_CATEGORY,GENRE,M_DATE,RATING,AUDIENCE)
    VALUES (media_seq.nextval, v_medianm, v_nation , v_category  , v_genre ,v_date, v_rating ,v_audience);
    COMMIT;
   
END;
/


CREATE OR REPLACE PROCEDURE sp_media_delete
(
    v_mediaid IN MEDIA.MEDIA_ID%TYPE
)
IS
BEGIN
    DELETE FROM MEDIA
    WHERE MEDIA_ID = v_mediaid;
    
    COMMIT;
    
   
END;
/

CREATE OR REPLACE PROCEDURE sp_media_update
(
    v_medianm   IN  MEDIA.MEDIA_NM%TYPE,
    v_nation    IN  MEDIA.NATION%TYPE,
    v_category  IN  MEDIA.MD_CATEGORY%TYPE,
    v_genre     IN  MEDIA.GENRE%TYPE,
    v_rating    IN  MEDIA.RATING%TYPE,
    v_date      IN  MEDIA.M_DATE%TYPE,
    v_mediaid    IN MEDIA.MEDIA_ID%TYPE
)
IS
BEGIN
   UPDATE MEDIA 
   SET  MEDIA_NM = v_medianm,
        NATION = v_nation,
        MD_CATEGORY =  v_category,
        GENRE = v_genre,
        RATING = v_rating,
        M_DATE =  v_date  
   where MEDIA_ID = v_mediaid;
 

    
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE sp_media_selectMediaId
(
    v_mediaid IN MEDIA.MEDIA_ID%TYPE,
    media_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN media_record FOR
    SELECT * 
    FROM MEDIA
    WHERE MEDIA_ID = v_mediaid;
    
   
END;
/

CREATE OR REPLACE PROCEDURE sp_media_selectMediaName
(
    v_medianm IN MEDIA.MEDIA_NM%TYPE,
    media_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN media_record FOR
    SELECT * 
    FROM MEDIA
    WHERE MEDIA_NM like v_medianm;
    
END;
/

CREATE OR REPLACE PROCEDURE sp_media_selectMediaCategory
(
    v_category IN MEDIA.MD_CATEGORY%TYPE,
    media_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN media_record FOR
    SELECT * 
    FROM MEDIA
    WHERE MD_CATEGORY = v_category;
    
   
END;
/

CREATE OR REPLACE PROCEDURE sp_media_selectMediaGenre
(
    v_genre IN MEDIA.GENRE%TYPE,
    media_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN media_record FOR
    SELECT * 
    FROM MEDIA
   Where GENRE = v_genre;
    
   
END;
/

CREATE OR REPLACE PROCEDURE sp_media_selectMediaRating
(
    v_rating IN MEDIA.RATING%TYPE,
    media_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN media_record FOR
    SELECT * 
    FROM MEDIA
   Where RATING = v_rating;
    
   
END;
/


CREATE OR REPLACE PROCEDURE sp_media_selectMediaNation
(
    
    v_nation IN MEDIA.NATION%TYPE,
    media_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN media_record FOR
    SELECT * 
    FROM MEDIA
   Where NATION = v_nation;
    
   
END;
/

CREATE OR REPLACE PROCEDURE sp_mymedia_selectuser
(
    v_userid IN MYMEDIA.U_ID%TYPE,
    user_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN user_record FOR
    SELECT *
    FROM MEDIA JOIN MYMEDIA USING (MEDIA_ID)
    WHERE MYMEDIA.U_ID = v_userid;
    
END;
/

CREATE OR REPLACE PROCEDURE sp_mymedia_insert
(
  v_userid   IN  MYMEDIA.U_ID%TYPE,
  v_mediaid  IN  MYMEDIA.MEDIA_ID%TYPE
)
IS
BEGIN
    INSERT INTO MYMEDIA(MY_M_ID,U_ID,MEDIA_ID)
        VALUES (My_media_seq.nextval,v_userid,v_mediaid);
                
    COMMIT;
   
END;
/

CREATE OR REPLACE PROCEDURE sp_user_updateMembership
(
    v_userlevel    IN   N_USER.U_LEVEL%TYPE,
    v_userid    IN   N_USER.U_ID%TYPE
)
IS
BEGIN
    UPDATE N_USER 
    SET U_LEVEL = v_userlevel
    where U_ID = v_userid ;
    
    COMMIT;
END;
/


CREATE OR REPLACE PROCEDURE sp_media_updateView
(
    v_audience    IN   MEDIA.AUDIENCE%TYPE,
    v_mediaid    IN   MEDIA.MEDIA_ID%TYPE
)
IS
BEGIN
    UPDATE MEDIA
    SET AUDIENCE = v_audience
    WHERE MEDIA_ID = v_mediaid;
    
    COMMIT;
END;
/


