-- 코드를 입력하세요
SELECT B.USER_ID, B.NICKNAME, SUM(A.PRICE) AS TOTAL_SALES
FROM USED_GOODS_BOARD A, USED_GOODS_USER B
WHERE A.WRITER_ID = B.USER_ID   -- 조인 조건
AND A.STATUS = 'DONE'           -- 완료된 중고거래
GROUP BY B.USER_ID, B.NICKNAME  -- 유저아이디, 유저닉네임 그룹핑 해줌(총금액을 뽑기 위해)
HAVING SUM(A.PRICE) >= 700000   -- 중고 거래의 총 금액이 70만원 이상인 것들 추출
ORDER BY TOTAL_SALES            -- 총거래금액 기준 오름차순 정렬
;