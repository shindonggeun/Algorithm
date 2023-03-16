-- 코드를 입력하세요
SELECT '/home/grep/src/' || B.BOARD_ID || '/' || B.FILE_ID || B.FILE_NAME || B.FILE_EXT AS FILE_PATH
        -- || 연산자를 이용해서 문자열 결합해줌
FROM (SELECT BOARD_ID
      FROM USED_GOODS_BOARD
      WHERE VIEWS = (SELECT MAX(VIEWS) 
                     FROM USED_GOODS_BOARD)     -- 조회수가 가장 높은 중고거래 게시물의 게시판 ID 추출
     ) A, USED_GOODS_FILE B
WHERE A.BOARD_ID = B.BOARD_ID   -- 조인조건
ORDER BY B.FILE_ID DESC         -- FILE ID를 기준으로 내림차순 정렬
;