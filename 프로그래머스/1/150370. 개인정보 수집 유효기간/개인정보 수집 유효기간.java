import java.util.*;

class Solution {
    
    static final int ONE_YEAR_OF_MONTH = 12;
    static final int START_YEAR = 2000;
    static final int MONTH_OF_DAY = 28;
    static Map<String, Integer> map; // key: 약관 종류, value: 유효기간 달에 따른 일수 계산 (달 * 28)
    static List<Integer> list;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        
        map = new HashMap<>();
        list = new ArrayList<>();
        
        int nowDays = calculateDays(today);
        
        for (String term: terms) {
            String[] termSplit = term.split(" ");
            map.put(termSplit[0], Integer.parseInt(termSplit[1]) * MONTH_OF_DAY);
        }
        
        for (int i=0; i<privacies.length; i++) {
            String[] privacySplit = privacies[i].split(" "); // [0] = yyyy.mm.dd, [1] = 약관 종류 (A, B, ...)
            
            int resultDays = calculateDays(privacySplit[0]);
            int mapGetDays = map.get(privacySplit[1]);
            
            resultDays += (mapGetDays - 1);
            
            if (resultDays < nowDays) {
                list.add(i + 1);
            }
        }
        
        answer = new int[list.size()];
        
        for (int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public int calculateDays(String yyyymmdd) {
        String[] yyyymmddSplit = yyyymmdd.split("[.]"); // [0] = yyyy, [1] = mm, [2] = dd
        
        int year = Integer.parseInt(yyyymmddSplit[0]);
        int month = Integer.parseInt(yyyymmddSplit[1]);
        int day = Integer.parseInt(yyyymmddSplit[2]);
        
        int days = day + month * MONTH_OF_DAY + (year - START_YEAR) * ONE_YEAR_OF_MONTH * MONTH_OF_DAY;
        
        return days;
    }
}