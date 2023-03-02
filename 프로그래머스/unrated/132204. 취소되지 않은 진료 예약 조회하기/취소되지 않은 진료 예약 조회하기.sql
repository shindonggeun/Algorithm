-- 코드를 입력하세요
SELECT A.APNT_NO, P.PT_NAME, P.PT_NO, A.MCDP_CD, D.DR_NAME, A.APNT_YMD
FROM PATIENT P, DOCTOR D, APPOINTMENT A -- 삼중조인
WHERE D.DR_ID = A.MDDR_ID           -- 조인조건
AND P.PT_NO = A.PT_NO               -- 조인조건 
AND TO_CHAR(A.APNT_YMD, 'YYYY-MM-DD') = '2022-04-13'    -- 예약취소날짜가 2022년04월13일
AND A.APNT_CNCL_YN = 'N'        -- 예약취소여부 N (즉 취소되지 않은) => 즉 2022년04월13에 취소되지 않은
AND D.MCDP_CD = 'CS'            -- 진료과코드는 흉부외과
ORDER BY A.APNT_YMD ASC         -- 진료예약일시 기준 오름차순 정렬
;