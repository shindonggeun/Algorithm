-- 코드를 입력하세요
SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, 
       CASE WHEN STATUS = 'SALE' THEN '판매중'     -- 거래상태가 SALE이면 판매중
            WHEN STATUS = 'RESERVED' THEN '예약중' -- 거래상태가 RESERVED이면 예약중
            ELSE '거래완료'                        -- 거래상태가 그 이외의 것이면(DONE)이면 거래완료
            END AS STATUS
FROM USED_GOODS_BOARD
WHERE TO_CHAR(CREATED_DATE, 'YYYY-MM-DD') = '2022-10-05'    -- 2022년 10월 05일에 등록된 중고거래 게시물
ORDER BY BOARD_ID DESC      -- 게시글 ID 내림차순 정렬
;