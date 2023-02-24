-- 코드를 입력하세요
SELECT ORDER_ID, 
       PRODUCT_ID, 
       TO_CHAR(OUT_DATE, 'YYYY-MM-DD') AS OUT_DATE,
       CASE WHEN TO_CHAR(OUT_DATE, 'YYYY-MM-DD') <= '2022-05-01' THEN '출고완료'  -- 2022-05-01 까지는 출고완료
            WHEN OUT_DATE IS NULL THEN '출고미정'                           -- OUT_DATE값이 NULL인 경우 출고미정
            ELSE '출고대기'               -- 위의 경우 이외의 것들은 다 출고대기(2022-05-01 이후는 다 출고대기)
       END AS 출고여부                    -- 컬럼값은 출고여부
FROM FOOD_ORDER 
ORDER BY ORDER_ID
;