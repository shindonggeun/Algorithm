SELECT CATEGORY, PRICE AS MAX_PRICE, PRODUCT_NAME
FROM FOOD_PRODUCT
WHERE (category,price) IN (                         -- 서브쿼리에서 뽑아져 나온 값과 같은것들만 조회
    SELECT CATEGORY, MAX(PRICE) AS MAX_PRICE        -- 그룹화중 가장 비싼 식품의 분류, 가격 조회
    FROM FOOD_PRODUCT
    WHERE CATEGORY IN ('과자', '국', '김치', '식용유') -- IN을 써서 여러개 조건 걸어주기
    GROUP BY CATEGORY                               -- 식품분류 그룹화하기
)
ORDER BY PRICE DESC                                 -- 식품가격 기준 내림차순 정렬