-- 코드를 입력하세요
SELECT A.APNT_NO, P.PT_NAME, P.PT_NO, A.MCDP_CD, D.DR_NAME, A.APNT_YMD
FROM PATIENT P, DOCTOR D, APPOINTMENT A
WHERE D.DR_ID = A.MDDR_ID
AND P.PT_NO = A.PT_NO
AND TO_CHAR(A.APNT_YMD, 'YYYY-MM-DD') = '2022-04-13'
AND A.APNT_CNCL_YN = 'N'
ORDER BY A.APNT_YMD ASC
;