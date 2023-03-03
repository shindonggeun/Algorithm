-- 코드를 입력하세요
SELECT A.AUTHOR_ID, A.AUTHOR_NAME, B.CATEGORY, 
       SUM(S.SALES * B.PRICE) AS TOTAL_SALES        -- 저자별, 카테고리 별 매출액 (판매량 * 판매가)
FROM BOOK B, AUTHOR A, BOOK_SALES S
WHERE B.AUTHOR_ID = A.AUTHOR_ID                     -- 조인조건 1
AND B.BOOK_ID = S.BOOK_ID                           -- 조인조건 2 (삼중오인)
AND TO_CHAR(S.SALES_DATE, 'YYYY-MM') = '2022-01'    -- 2022년 1월 도서 판매 데이터 뽑아오도록
GROUP BY A.AUTHOR_ID, A.AUTHOR_NAME, B.CATEGORY     -- 저자 ID, 저자 이름, 카테고리 그룹화
ORDER BY A.AUTHOR_ID ASC, B.CATEGORY DESC           -- 저자 ID 오름차순, 카테고리 내림차순 정렬
;