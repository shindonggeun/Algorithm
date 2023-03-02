-- 코드를 입력하세요
SELECT TRUNC(PRICE, -4) AS PRICE_GROUP, COUNT(TRUNC(PRICE,-4)) AS PRODUCTS  -- TRUNC() 함수를 이용하여 숫자 절사
FROM PRODUCT
GROUP BY TRUNC(PRICE, -4)   -- -4는 1000 단위 절사한것(가격대 만단위로 나오게끔 -> 10000, 20000 이런식)을 그룹화
ORDER BY PRICE_GROUP ASC    -- 가격대 기준으로 오름차순 정렬
;
