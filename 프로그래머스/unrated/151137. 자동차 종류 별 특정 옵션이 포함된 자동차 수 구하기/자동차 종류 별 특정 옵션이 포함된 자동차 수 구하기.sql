-- 코드를 입력하세요
SELECT CAR_TYPE, COUNT(CAR_TYPE) AS CARS    -- 자동차 종류별로 몇대인지 그룹화해서 COUNT
FROM CAR_RENTAL_COMPANY_CAR
WHERE OPTIONS LIKE '%통풍시트%' -- 옵션이 통풍시트 또는
OR OPTIONS LIKE '%열선시트%'    -- 열선시트 또는
OR OPTIONS LIKE '%가죽시트%'    -- 옵션에 가죽시트 중 하나이상의 옵션이 포함되어야 함
GROUP BY CAR_TYPE           -- 자동차 종류를 그룹화함
ORDER BY CAR_TYPE ASC       -- 자동자 종류를 기준으로 오름차순 정렬
;