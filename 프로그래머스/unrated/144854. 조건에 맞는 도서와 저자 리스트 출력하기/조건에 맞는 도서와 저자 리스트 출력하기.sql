-- 코드를 입력하세요
SELECT B.BOOK_ID, A.AUTHOR_NAME, TO_CHAR(B.PUBLISHED_DATE, 'YYYY-MM-DD') AS PUBLISHED_DATE
FROM BOOK B, AUTHOR A
WHERE B.AUTHOR_ID = A.AUTHOR_ID     -- 조인조건
AND B.CATEGORY = '경제'              -- 경제 카테고리에 속하는 도서
ORDER BY PUBLISHED_DATE ASC         -- 출판일을 기준으로 오름차순 정렬
;