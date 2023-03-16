-- 코드를 입력하세요
SELECT TO_NUMBER(TO_CHAR(START_DATE, 'MM')) AS MONTH, 
       CAR_ID, COUNT(*) AS RECORD         -- 월과 자동차ID에 따른 대여횟수
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE CAR_ID IN (SELECT CAR_ID
                 FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                 WHERE TO_NUMBER(TO_CHAR(START_DATE, 'MM')) BETWEEN 8 AND 10
                 GROUP BY CAR_ID
                 HAVING COUNT(*) >= 5) -- 대여시작일 기준으로 8월부터 10월까지 총 대여횟수가 5회 이상인 자동차 ID 추출
AND TO_NUMBER(TO_CHAR(START_DATE, 'MM')) BETWEEN 8 AND 10  -- 메인쿼리 대여시작일 기준 8월부터 10월까지 
HAVING COUNT(*) != 0                                       -- 특정 월의 총 대여횟수가 0인경우 결과에서 제외
GROUP BY TO_NUMBER(TO_CHAR(START_DATE, 'MM')), CAR_ID      -- 그룹핑 조건(월, 자동차ID)
ORDER BY MONTH ASC, CAR_ID DESC                            -- 월 기준으로 오름차순 정렬, 자동차 ID 내림차순 정렬
;