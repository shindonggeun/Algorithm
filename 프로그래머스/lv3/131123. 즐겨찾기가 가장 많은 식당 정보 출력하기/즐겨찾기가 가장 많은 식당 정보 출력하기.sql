-- 코드를 입력하세요
SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
FROM REST_INFO
WHERE (FOOD_TYPE, FAVORITES) IN (SELECT FOOD_TYPE, MAX(FAVORITES)   -- 음식 종류별로 즐겨찾기수가 가장 많은 식당들인것들 조건으로 걸어주기 (음식종류, 즐겨찾기가 가장 많은 식당) 이렇게 컬럼값들 매칭된 것끼리 조회
                    FROM REST_INFO
                    GROUP BY FOOD_TYPE)     -- 음식 종류별로 그룹화해주기 (MAX() 쓰기위해)
ORDER BY FOOD_TYPE DESC                     -- 음식 종류를 기준으로 내림차순 정렬
;