-- 코드를 입력하세요
SELECT TO_CHAR(SALES_DATE, 'YYYY-MM-DD') AS SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
FROM (SELECT SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
      FROM ONLINE_SALE
      UNION ALL
      SELECT SALES_DATE, PRODUCT_ID, NULL, SALES_AMOUNT
      FROM OFFLINE_SALE)  -- ONLNE_SALE 테이블 데이터와 OFFLINE_SALE 테이블 테이터에서 컬럼 같은것들 합집합(중복허용)
WHERE TO_CHAR(SALES_DATE, 'YYYY-MM') = '2022-03'    -- 판매 날짜는 2022년 03월
ORDER BY SALES_DATE, PRODUCT_ID, USER_ID            -- 판매일, 상품 ID, 유저 ID 기준으로 오름차순 정렬
;     