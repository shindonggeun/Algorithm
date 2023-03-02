-- 코드를 입력하세요
SELECT DISTINCT(A.CAR_ID) AS CAR_ID                  -- 자동차ID 중복 없게끔
FROM CAR_RENTAL_COMPANY_CAR A, CAR_RENTAL_COMPANY_RENTAL_HISTORY B
WHERE A.CAR_ID = B.CAR_ID
AND A.CAR_TYPE LIKE '%세단%'                          -- 자동차 종류는 세단 
AND TO_CHAR(B.START_DATE, 'YYYY-MM') = '2022-10'    -- 2022년 10월에 대여를 시작한 기록이 있는
ORDER BY A.CAR_ID DESC                          -- 자동차 ID 기준 내림차순 정렬
;