-- 코드를 입력하세요
SELECT I.REST_ID, I.REST_NAME, I.FOOD_TYPE, I.FAVORITES, I.ADDRESS, ROUND(AVG(R.REVIEW_SCORE), 2) AS SCORE  -- 리뷰 평균점수 소수점 세번째 자리에서 반올림 (소수점 두 번째 자리까지 표시하게끔)
FROM REST_INFO I, REST_REVIEW R
WHERE I.REST_ID = R.REST_ID                -- 조인 조건
AND I.ADDRESS LIKE '서울%'                  -- 주소 서울에 위치해야함, %서울%은 틀리다!(서울로 시작해야하므로)
GROUP BY I.REST_ID, I.REST_NAME, I.FOOD_TYPE, I.FAVORITES, I.ADDRESS    -- AVG()함수에서 쓰인 컬럼(리뷰점수) 빼고 나머지 그룹화
ORDER BY SCORE DESC, I.FAVORITES DESC       -- 리뷰 평균점수 기준으로 내림차순 정렬, 즐겨찾기 수 내림차순 정렬
;