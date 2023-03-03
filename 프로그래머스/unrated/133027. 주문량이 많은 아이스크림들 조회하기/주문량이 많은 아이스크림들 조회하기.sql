-- 코드를 입력하세요
SELECT A.FLAVOR
FROM (SELECT A.FLAVOR, (A.TOTAL_ORDER + B.TOTAL_ORDER) AS RNK   -- 각 테이블에서 맛마다 총 주문량 계산한거를 더해줌 
      FROM  (SELECT FLAVOR, SUM(TOTAL_ORDER) AS TOTAL_ORDER
               FROM FIRST_HALF
              GROUP BY FLAVOR) A,   -- FIRST_HALF 테이블에서 맛을 그룹화하여 맛마다 총 주문량 계산
            (SELECT FLAVOR, SUM(TOTAL_ORDER) AS TOTAL_ORDER
               FROM JULY
              GROUP BY FLAVOR) B    -- JULY 테이블에서 맛을 그룹화하여 맛마다 총 주문량 계산
      WHERE A.FLAVOR = B.FLAVOR     -- 조인조건
      ORDER BY RNK DESC) A          -- 아이스크림 총 주문량 더한값이 큰 순서대로 나와야하므로 내림차순 정렬해줌
WHERE ROWNUM <= 3   -- ROWNUM을 이용하여 상위 3개 컬럼 조회
;