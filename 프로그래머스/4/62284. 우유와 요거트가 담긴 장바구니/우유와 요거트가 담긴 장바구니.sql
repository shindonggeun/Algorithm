-- 코드를 입력하세요
WITH 장바구니_요거트 AS (  -- 장바구니에 요거트만 담긴 장바구니 아이디 조회하기 (DISTINCT로 중복 제외)
    SELECT DISTINCT(CART_ID)
    FROM CART_PRODUCTS
    WHERE NAME = 'Yogurt'
)
, 장바구니_우유 AS (      -- 장바구니에 우유만 담긴 장바구니 아이디 조회하기 (DISTINCT로 중복 제외)
    SELECT DISTINCT(CART_ID)
    FROM CART_PRODUCTS
    WHERE NAME = 'Milk'
)

SELECT A.CART_ID
FROM 장바구니_요거트 A, 장바구니_우유 B
WHERE A.CART_ID = B.CART_ID -- 조인조건 (장바구니 아이디 같은것끼리) -> 즉, 요거트와 우유 둘다 담긴 장바구니 ID
ORDER BY A.CART_ID ASC      -- 장바구니 아이디순으로 오름차순
;