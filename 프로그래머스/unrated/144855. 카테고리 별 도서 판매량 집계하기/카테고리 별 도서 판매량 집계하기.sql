-- 코드를 입력하세요
SELECT A.CATEGORY, SUM(B.SALES) AS TOTAL_SALES      -- 카테고리별 판매량 집계
FROM BOOK A, BOOK_SALES B
WHERE A.BOOK_ID = B.BOOK_ID                         -- 조인조건
AND TO_CHAR(B.SALES_DATE, 'YYYY-MM') = '2022-01'    -- 2022년 1월
GROUP BY A.CATEGORY                                 -- 카테고리별로 그룹화
ORDER BY A.CATEGORY ASC                             -- 카테고리명을 기준으로 오름차순 정렬
;