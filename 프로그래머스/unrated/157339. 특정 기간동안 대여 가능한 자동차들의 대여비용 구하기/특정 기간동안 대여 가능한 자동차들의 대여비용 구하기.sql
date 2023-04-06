-- 코드를 입력하세요
WITH 차정보 AS (   -- 자동차 종류가 세단과 SUV인것만 뽑아내는 임시테이블 정보
    SELECT CAR_ID, CAR_TYPE, DAILY_FEE
    FROM CAR_RENTAL_COMPANY_CAR
    WHERE CAR_TYPE IN ('세단', 'SUV')
)
, 대여기록 AS ( 
    SELECT DISTINCT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE CAR_ID NOT IN (SELECT DISTINCT CAR_ID   -- 2022-11-01 ~ 2022-11-30의 대여기간을 제외한 나머지 기간의 대여를 한 자동차 아이디를 제외한것을 검색조건으로 해줌(NOT IN)
                         FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                         WHERE TO_CHAR(START_DATE,'YYYY-MM') <= '2022-11'   -- 대여시작일이 2022년 11월보다 작거나 같으면서(2022년 11월까지 가능)
                         AND TO_CHAR(END_DATE,'YYYY-MM') >= '2022-11') -- 대여종료일이 2022년 11월보다 크거나 같은 경우
)
, 할인정보 AS ( -- 차종류가 세단 또는 SUV면서 대여기간이 30일이상인 할인률 정보를 뽑아내는 임시테이블
    SELECT CAR_TYPE, DISCOUNT_RATE
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    WHERE CAR_TYPE IN ('세단', 'SUV')
    AND DURATION_TYPE = '30일 이상'
)

SELECT A.CAR_ID, A.CAR_TYPE, (100 - DISCOUNT_RATE) / 100 * A.DAILY_FEE * 30 AS FEE 
                            -- (100 - 할인률) / 100 * 일일 대여 요금 * 30일 => 30일간 대여금액(할인된 가격임)
FROM 차정보 A, 대여기록 B, 할인정보 C
WHERE A.CAR_ID = B.CAR_ID       -- 첫번째 조인조건
AND A.CAR_TYPE = C.CAR_TYPE     -- 두번째 조인조건 
AND ((100 - DISCOUNT_RATE) / 100 * A.DAILY_FEE * 30) >= 500000  -- 30일간 대여금액이 50만원 이상이면서
AND ((100 - DISCOUNT_RATE) / 100 * A.DAILY_FEE * 30) < 2000000  -- 200만원 미만인 경우
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC
;