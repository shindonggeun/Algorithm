-- 코드를 입력하세요
WITH 차정보_대여기록 AS (
    SELECT A.CAR_ID, A.CAR_TYPE, A.DAILY_FEE, B.HISTORY_ID, B.PERIOD
    FROM CAR_RENTAL_COMPANY_CAR A, (SELECT HISTORY_ID, CAR_ID, END_DATE - START_DATE + 1 AS PERIOD 
                                    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY) B
    WHERE A.CAR_TYPE = '트럭' -- 조인조건
    AND A.CAR_ID = B.CAR_ID
)
, 할인정책 AS (
    SELECT CAR_TYPE, DURATION_TYPE, DISCOUNT_RATE
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    WHERE CAR_TYPE = '트럭'
)

SELECT A.HISTORY_ID, (A.PERIOD * A.DAILY_FEE) * NVL(1 - B.DISCOUNT_RATE / 100, 1) AS FEE
                    -- 요금할인정책에 따라 대여기간에 따른 자동차 대여금액 계산 공식
                    -- NVL을 이용하여 할인률 계산이 NULL로 나오는것은 1로 처리해줌
FROM 차정보_대여기록 A LEFT OUTER JOIN 할인정책 B  -- LEFT OUTER JOIN을 해줘야 NULL인 컬럼도 나올 수 있다!
ON A.CAR_TYPE = B.CAR_TYPE                     -- 조인조건 (ON에 조인조건을 AND절까지 같이 걸어준다)
                                               -- WHERE절에 검색조건 거는거와 내부 동작과정이 다름!(결과 다르다)
AND B.DURATION_TYPE IN (CASE WHEN A.PERIOD >= 90 THEN '90일 이상' 
                            WHEN A.PERIOD >= 30 THEN '30일 이상'
                            WHEN A.PERIOD >= 7 THEN '7일 이상'
                            ELSE NULL END)  -- 각 기간에 따라 대여기간종류를 정해준다 
                                            -- 대여기간안에 없는 경우는 NULL처리 
                                            -- 그래서 각 경우에 맞는 컬럼이 하나씩 뽑아져나옴
ORDER BY FEE DESC, A.HISTORY_ID DESC
;