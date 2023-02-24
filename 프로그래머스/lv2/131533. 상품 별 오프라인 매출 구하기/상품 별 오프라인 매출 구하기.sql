-- 코드를 입력하세요
SELECT A.PRODUCT_CODE AS PRODUCT_CODE, SUM(B.SALES_AMOUNT * A.PRICE) AS SALES 
FROM PRODUCT A, OFFLINE_SALE B      -- 조인해줌
WHERE A.PRODUCT_ID = B.PRODUCT_ID   -- PRODUCT 테이블과 OFF_LINE_SALE 테이블의 상품코드 같은것으로 조건 걸어줌
GROUP BY PRODUCT_CODE               -- PRODUCT_CODE로 그룹핑
ORDER BY SALES DESC, PRODUCT_CODE ASC   -- 매출액 기준으로 내림차순 정렬, 매출액이 같으면 상품코드를 기준으로 오름차순 정렬
;