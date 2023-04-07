-- 코드를 입력하세요
SELECT A.HOUR, COUNT(B.DATETIME) AS COUNT   -- 시간대와 그 시간대에 입양이 발생한 수(COUNT 이용)
FROM (
    SELECT LEVEL-1 as HOUR  -- 0 ~ 23까지 조회
    FROM DUAL
    connect by level<=24    -- 1부터 24까지 나오게끔
) A LEFT JOIN ANIMAL_OUTS B -- 0부터 23까지 나와야하므로 LEFT JOIN을 이용해야한다 (그냥 WHERE 이용해서 조인조건 걸어주면 0부터 안나옴) (A테이블의 레코드는 무조건 나오지만 B테이블에서 가져온 컬럼들은 NULL로 채워짐)
ON HOUR = TO_CHAR(B.DATETIME, 'HH24')   -- 시간대 조인조건
GROUP BY A.HOUR -- 시간대로 그룹화함
order by HOUR   -- 시간대순으로 오름차순 정렬
;