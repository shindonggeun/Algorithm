-- 코드를 작성해주세요
select COUNT(*) AS FISH_COUNT
from FISH_INFO
where date_format(TIME, "%Y") = '2021'
;