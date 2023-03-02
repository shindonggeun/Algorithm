-- 코드를 입력하세요
SELECT TO_CHAR(O.SALES_DATE, 'YYYY') AS YEAR, TO_NUMBER(TO_CHAR(O.SALES_DATE, 'MM')) AS MONTH, U.GENDER, COUNT(DISTINCT(U.USER_ID)) AS USERS -- 이 문제에서 COUNT() 함수 쓸때 DISTINCT() 이용해서 USER_ID 중복 제거해줘야한다!
FROM USER_INFO U, ONLINE_SALE O
WHERE U.USER_ID = O.USER_ID     -- 조인조건
AND U.GENDER IS NOT NULL        -- 성별정보가 없는 경우 제외
GROUP BY TO_CHAR(O.SALES_DATE, 'YYYY'), TO_CHAR(O.SALES_DATE, 'MM'), U.GENDER   -- COUNT()를 쓰기 위해 년, 월, 성별 그룹화 
ORDER BY YEAR, MONTH, GENDER    -- 년, 월, 성별을 기준으로 오름차순 정렬
;