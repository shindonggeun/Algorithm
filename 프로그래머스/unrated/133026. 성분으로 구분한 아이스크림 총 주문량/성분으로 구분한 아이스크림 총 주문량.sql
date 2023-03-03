-- 코드를 입력하세요
SELECT I.INGREDIENT_TYPE, SUM(TOTAL_ORDER) AS TOTAL_ORDER   -- 성분타입, 성분타입에 대한 아이스크림 총 주문량
FROM FIRST_HALF F, ICECREAM_INFO I
WHERE F.FLAVOR = I.FLAVOR   -- 조인조건
GROUP BY I.INGREDIENT_TYPE  -- 성분타입을 그룹화
ORDER BY TOTAL_ORDER ASC    -- 총주문량 작은순서대로(오름차순) 정렬
;