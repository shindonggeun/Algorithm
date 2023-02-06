-- 코드를 입력하세요
SELECT MCDP_CD as "진료과코드", count(MCDP_CD) as "5월예약건수"   
                                            -- as 별칭을 싱글쿼테이션('')으로 감싸면 from 뭐시기 오류남
                                            -- 주의하자!!!(더블퀘테이션("")으로 감싸거나 감싸지말고 그냥 쓰도록 하자)
from appointment
where to_char(APNT_YMD, 'YYYY-MM') = '2022-05'  -- 조건: 2022년 05월에 예약한 환자 
group by MCDP_CD                                -- 진료과 코드로 그룹을 묶는다
order by "5월예약건수" asc, MCDP_CD asc           -- 예약한 환자수 기준 및 진료과 코드 기준으로 오름차순 정렬
;