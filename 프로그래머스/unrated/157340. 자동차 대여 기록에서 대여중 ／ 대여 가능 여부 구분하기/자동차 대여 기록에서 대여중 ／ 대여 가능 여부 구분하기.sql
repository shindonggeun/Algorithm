-- 코드를 입력하세요
SELECT CAR_ID, CASE WHEN AP = 0 THEN '대여 가능' -- SUM한 값이 0이면 대여 가능
                    ELSE '대여중'        -- SUM한 값이 0이 아닌 경우 대여중
                    END AS AVAILABILITY
FROM (SELECT CAR_ID, SUM(P) AS AP           -- 자동차ID로 그룹화해서 CASE문을 이용한 P값을 SUM해줌
      FROM (SELECT CAR_ID, CASE WHEN TO_DATE('20221016','YYYY-MM-DD') BETWEEN START_DATE AND END_DATE THEN 1
                                ELSE 0
                                END AS P    -- 2022-10-16에 대여 중인 자동차인 경우 1, 아닌경우 0
            FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY)
      GROUP BY CAR_ID)                      -- 자동차ID로 그룹화함
ORDER BY CAR_ID DESC
;


