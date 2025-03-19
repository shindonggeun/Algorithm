import java.util.*;

class Solution {
    
    static final int MONTHS_IN_YEAR = 12; // 1년은 12개월
    static final int START_YEAR = 2000; // 기준이 되는 연도 (2000 <= YYYY <= 2022)
    static final int DAYS_IN_MONTH = 28; // 모든 달은 28일까지
    static Map<String, Integer> termMap; // key: 약관 종류, value: 유효기간 달에 따른 일수 계산 (달 * 28일)
    static List<Integer> expiredList; // 파기해야할 개인 정보 번호 저장 리스트
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        
        termMap = new HashMap<>();
        expiredList = new ArrayList<>();
        
        int nowDays = calculateDays(today); // 오늘 날짜를 총 일수로 변환
        
        // 1. 약관 종류에 따른 유효기간 일수를 해시맵에 저장하는 과정
        for (String term: terms) {
            String[] termSplit = term.split(" "); // [0] = 약관 종류 (A, B, ...), [1] = 유효 기간
            
            // 해당 약관 종류에 따른 유효기간을 일(day)수로 변환하여 해시맵에 저장
            termMap.put(termSplit[0], Integer.parseInt(termSplit[1]) * DAYS_IN_MONTH);
        }
        
        // 2. 개인정보별 유효기간 검사하는 과정
        for (int i=0; i<privacies.length; i++) {
            String[] privacySplit = privacies[i].split(" "); // [0] = yyyy.mm.dd, [1] = 약관 종류 (A, B, ...)
            
            int totalDays = calculateDays(privacySplit[0]); // 개인정보 수집 날짜를 총 일수로 변환
            int validDays = termMap.get(privacySplit[1]); // 해당 약관의 유효기간을 일수로 변환
            
            totalDays += (validDays - 1); // 유효기간 계산 (마지막 보관 가능 날짜까지 포함하려면 - 1)
            
            // 오늘 날짜가 유효기간을 넘은 경우 (파기해야 할 대상)
            if (totalDays < nowDays) {
                expiredList.add(i + 1); // 개인정보 번호 저장 
            }
        }
        
        // 3. 해당 리스트를 결과 배열로 변환하는 과정
        answer = new int[expiredList.size()];
        
        for (int i=0; i<expiredList.size(); i++) {
            answer[i] = expiredList.get(i);
        }
        
        return answer;
    }
    
    // 날짜(YYYY.MM.DD)를 총 일수로 변환하는 메서드
    public int calculateDays(String yyyymmdd) {
        String[] yyyymmddSplit = yyyymmdd.split("[.]"); // [0] = yyyy, [1] = mm, [2] = dd
        
        int year = Integer.parseInt(yyyymmddSplit[0]);
        int month = Integer.parseInt(yyyymmddSplit[1]);
        int day = Integer.parseInt(yyyymmddSplit[2]);
        
        // 총 일수 = (년 - 기준연도) * 12개월 + 28일 + (월 * 28일) + 일
        int totalDays = (year - START_YEAR) * MONTHS_IN_YEAR * DAYS_IN_MONTH + (month * DAYS_IN_MONTH) + day;
        
        return totalDays;
    }
}