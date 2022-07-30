select user
from dual;
--SCOTT

--MVC03=========================================================================

DROP TABLE TBL_MEMBERLIST;
--Table TBL_MEMBERLIST��(��) �����Ǿ����ϴ�.

-- MAKE TABLE (TBL_MEMBERLIST)
CREATE TABLE TBL_MEMBERLIST
( MID       NUMBER 
, NAME      VARCHAR2(30)
, TELEPHONE VARCHAR2(50)
, CONSTRAINT MEMBERLIST_MID_PK PRIMARY KEY(MID)
);
--Table TBL_MEMBERLIST��(��) �����Ǿ����ϴ�.


-- MEMBERLISTSEQ
CREATE SEQUENCE MEMBERLISTSEQ
NOCACHE;
--Sequence MEMBERLISTSEQ��(��) �����Ǿ����ϴ�.

INSERT INTO TBL_MEMBERLIST(MID, NAME, TELEPHONE)
VALUES(MEMBERLISTSEQ.NEXTVAL, '������', '010-1234-1234');

--�� �� ����
INSERT INTO TBL_MEMBERLIST(MID, NAME, TELEPHONE) VALUES(MEMBERLISTSEQ.NEXTVAL, '������', '010-1234-1234')
;
--1 �� ��(��) ���ԵǾ����ϴ�.


SELECT *
FROM TBL_MEMBERLIST;
--1	������	010-1234-1234


ROLLBACK;
--�ѹ� �Ϸ�.

COMMIT;
--Ŀ�� �Ϸ�.

--����Ʈ ��ȸ ������ ����
SELECT MID, NAME, TELEPHONE
FROM TBL_MEMBERLIST
ORDER BY MID;

--�� �� ����
SELECT MID, NAME, TELEPHONE FROM TBL_MEMBERLIST ORDER BY MID
;


-- �ο� �� ��ȸ ������ ����
SELECT COUNT(*) AS COUNT
FROM TBL_MEMBERLIST;

-- �� �� ����
SELECT COUNT(*) AS COUNT FROM TBL_MEMBERLIST
;


--==============================================================================












