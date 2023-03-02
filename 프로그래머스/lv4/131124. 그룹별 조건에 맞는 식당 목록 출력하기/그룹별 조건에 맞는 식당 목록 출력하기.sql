-- 코드를 입력하세요
SELECT M.MEMBER_NAME, R.REVIEW_TEXT, TO_CHAR(R.REVIEW_DATE, 'YYYY-MM-DD') AS REVIEW_DATE
FROM MEMBER_PROFILE M, REST_REVIEW R
WHERE M.MEMBER_ID = R.MEMBER_ID         -- 조인조건
AND M.MEMBER_ID IN (SELECT MEMBER_ID    -- 회원정보를 담은 테이블의 MEMBER_ID와 리뷰테이블에서 리뷰 가장 많이 작성한 멤버의 아이디를 뽑아내기 (서브쿼리 및 MAX(COUNT) 이용)
                      FROM REST_REVIEW 
                      GROUP BY MEMBER_ID
                      HAVING COUNT(MEMBER_ID) = (SELECT MAX(COUNT(MEMBER_ID)) 
                                                 FROM REST_REVIEW 
                                                 GROUP BY MEMBER_ID))
ORDER BY R.REVIEW_DATE ASC, R.REVIEW_TEXT ASC   -- 리뷰 작성일 기준으로 오름차순, 리뷰텍스트 오른차순 정렬
;