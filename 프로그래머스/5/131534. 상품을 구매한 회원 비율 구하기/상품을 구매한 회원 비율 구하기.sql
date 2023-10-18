-- 코드를 입력하세요
SELECT TO_CHAR(O.SALES_DATE, 'YYYY') AS YEAR,   -- 년도
       TO_NUMBER(TO_CHAR(O.SALES_DATE, 'MM')) AS MONTH, -- 월 
       COUNT(DISTINCT(O.USER_ID)) AS PUCHASED_USERS, -- 상품을 구매한 회원 수
       ROUND(COUNT(DISTINCT O.USER_ID) / (SELECT COUNT(DISTINCT USER_ID) 
                                           FROM USER_INFO
                                           WHERE TO_CHAR(JOINED,'YYYY') = '2021')   
             , 1) AS puchased_ratio -- 2021년에 가입한 회원 중 상품을 구매한 회운 수 / 2021년에 가입한 전체 회원수
                                    -- ROUND() 함수 이용하여 반올림(1은 소수점 첫째자리까지 출력)
FROM USER_INFO U, ONLINE_SALE O -- 조인할 테이블들
WHERE U.USER_ID = O.USER_ID         -- 조인조건
AND TO_CHAR(U.JOINED, 'YYYY') = '2021'  -- 2021년에 가입한 회원 조건 걸어주기
GROUP BY TO_CHAR(O.SALES_DATE, 'YYYY'), TO_NUMBER(TO_CHAR(O.SALES_DATE, 'MM'))  -- 년도와 월을 그룹화
ORDER BY YEAR, MONTH   -- 년, 월 기준으로 오름차순 정렬
;