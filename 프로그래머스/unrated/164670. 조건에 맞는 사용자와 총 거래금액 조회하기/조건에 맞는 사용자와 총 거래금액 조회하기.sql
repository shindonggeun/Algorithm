-- 코드를 입력하세요
SELECT B.USER_ID, B.NICKNAME, CITY || ' ' || STREET_ADDRESS1 || ' ' || STREET_ADDRESS2 AS 전체주소,
       REGEXP_REPLACE(TLNO, '(.{3})(.+)(.{4})', '\1-\2-\3') AS 전화번호     
       -- || 을 이용해서 문자열 합치기 -> CONCAT(문자열1, 문자열2)를 이용해서 문자열1과 문자열2를 합칠수도 있음
       -- REGEXP_REPLACE() 함수를 이용해서 정규표현식을 이용해서 원하는 문자열 형태로 치환 (외워두자!)
FROM (SELECT WRITER_ID
      FROM USED_GOODS_BOARD
      GROUP BY WRITER_ID
      HAVING COUNT(*) >= 3) A, USED_GOODS_USER B    -- 중고 거래 게시물 3건이상 올린 사용자 ID를 뽑은 테이블 A
WHERE A.WRITER_ID = B.USER_ID   -- 조인조건
ORDER BY B.USER_ID DESC         -- 회원 ID를 기준으로 내림차순 정렬
;