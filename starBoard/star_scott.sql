SELECT USER
FROM DUAL;
--SCOTT



DROP TABLE TBL_BBS PURGE;
--Table TBL_BBS��(��) �����Ǿ����ϴ�.

--�Խ��� ���̺� ����
/*
CREATE TABLE TBL_BBS(
BBSID           NUMBER          NOT NULL
,BBSTITLE       VARCHAR2(50)     NOT NULL
,USERID         VARCHAR2(50)     NOT NULL
,BBSDATE        DATE             DEFAULT SYSDATE  NOT NULL
,BBSCONTENT     VARCHAR2(2048)   NOT NULL
,BBSAVAILABLE   NUMBER          NOT NULL
,CONSTRAINT BBS_BBSID_PK PRIMARY KEY(BBSID)
);
*/
--Table TBL_BBS��(��) �����Ǿ����ϴ�.


CREATE TABLE TBL_BBS(
BBSID           NUMBER          
,BBSTITLE       VARCHAR2(50)    
,USERID         VARCHAR2(50)   
,BBSDATE        DATE            DEFAULT SYSDATE
,BBSCONTENT     VARCHAR2(2048)  
,BBSAVAILABLE   NUMBER          
,CONSTRAINT BBS_BBSID_PK PRIMARY KEY(BBSID)
);
--Table TBL_BBS��(��) �����Ǿ����ϴ�.


--�μ�Ʈ ����
/*
INSERT INTO FACILITY_DELETE_REQ(F_DELETE_ID, FACILITY_ID, REQ_USER_ID,
ADMIN_ID, CONTENT, REQUEST_DATE, OPEN_DATE, ACCEPT_DATE)
VALUES(FACDELETESEQ.NEXTVAL, '4', '2', '0', '���� ������ �� �����Ǿ����', SYSDATE, NULL, NULL);
--1 �� ��(��) ���ԵǾ����ϴ�.
*/

--������ ���� �� ���� ����
/*
DROP SEQUENCE FACILITYSEQ;

CREATE SEQUENCE SEQ_FACILITY
NOCACHE;
*/


--���Խ��ǿ� �Խù� �ֱ�
INSERT INTO TBL_BBS(BBSID, BBSTITLE, USERID, BBSDATE, BBSCONTENT, BBSAVAILABLE) VALUES(SEQ_BBS.NEXTVAL, '����', 'OKAY@AHA.COM', TO_DATE(sysdate, 'YYYY-MM-DD'), '�����ڱ�', null);
--1 �� ��(��) ���ԵǾ����ϴ�.

--���� �����
INSERT INTO TBL_BBS(BBSID, BBSTITLE, USERID, BBSDATE, BBSCONTENT, BBSAVAILABLE) VALUES(SEQ_BBS.NEXTVAL, '����', 'OKAY@AHA.COM', sysdate, '�����ڱ�', null)
;


select *
from tbl_bbs;
--1	����	OKAY@AHA.COM	 2022-08-24	�����ڱ�	


CREATE SEQUENCE SEQ_BBS
NOCACHE;
--Sequence SEQ_BBS��(��) �����Ǿ����ϴ�.

--drop sequence seq_bbs;


rollback;
--�ѹ� �Ϸ�.

--==============================================================================


select *
from tbl_staruser;

DROP TABLE TBL_STARUSER PURGE;
--Table TBL_STARUSER��(��) �����Ǿ����ϴ�.

--����� ���̺� ����
CREATE TABLE TBL_STARUSER(
USERID          VARCHAR2(50)     NOT NULL
,USERPASSWORD   VARCHAR2(50)     NOT NULL
,USERNAME       VARCHAR2(50)     NOT NULL
,CONSTRAINT STARUSER_USERID_PK PRIMARY KEY(USERID)
);
--Table TBL_STARUSER��(��) �����Ǿ����ϴ�.


commit;
--Ŀ�� �Ϸ�.


