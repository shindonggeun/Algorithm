-- 코드를 입력하세요
SELECT ID, NAME, HOST_ID
FROM PLACES
WHERE HOST_ID in (SELECT HOST_ID    -- 서브쿼리를 이용(공간을 소유한 아이디를 조회) (즉 헤비유저 아이디 조회)
                  FROM PLACES       
                  GROUP BY HOST_ID  -- 공간을 소유한 아이디로 그룹화
                  HAVING COUNT(HOST_ID) >= 2)       -- 그룹화한 이후에 공간을 2개이상 소유한자가 조건
ORDER BY ID ASC    -- 아이디순으로 오름차순 정렬
;