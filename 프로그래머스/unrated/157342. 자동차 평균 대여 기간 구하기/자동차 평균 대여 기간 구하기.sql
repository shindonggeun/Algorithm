-- 코드를 입력하세요
SELECT CAR_ID, 
       ROUND(AVG(END_DATE - START_DATE + 1), 1) AS AVERAGE_DURATION -- AVG()이용해서 평균 대여기간 뽑아내기
                                                              -- ROUND()이용해서 반올림, 소수점 첫째자리까지 출력
                                                                    -- 시작한 날짜부터 계산해야하므로 +1 해줘야함
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID                                         -- 자동차 ID로 그룹화
HAVING ROUND(AVG(END_DATE - START_DATE + 1), 1) >= 7    -- 그룹화한 뒤 평균대여기간이 7일 이상인것들을 조건 걸어주기
ORDER BY AVERAGE_DURATION DESC, CAR_ID DESC             -- 평균대여기간을 기준으로 내림차순, 자동차 ID 내림차순 정렬
;