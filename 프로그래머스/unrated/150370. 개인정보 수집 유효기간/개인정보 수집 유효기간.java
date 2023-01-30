import java.util.*;

class Solution {
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        //int[] answer = {};
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(); // key: 약관종류, value: 유효기간 달에 따른 일수 계산 (달 * 28)
        int now_days = getDays(today);
        
        for(String term: terms) {
            map.put(term.split(" ")[0], Integer.parseInt(term.split(" ")[1]) * 28);
        }
        
        for(int i=0; i<privacies.length; i++) {
            String[] pri_arr = privacies[i].split(" "); // [0] = yyyy.mm.dd, [1] = 약관종류
            int result_days = getDays(pri_arr[0]);
            
            int map_days = map.get(pri_arr[1]); // map에 저장된 약관에 따른 유효기간 일수 가져오기
            
            result_days = result_days + map_days - 1;   // 유효기간 마지막 날짜 (-1 빼주는거 잊지말자)
            
            if(now_days > result_days) {
                list.add(i+1);
            }
        }        
        //System.out.println(list);
        //answer = list.stream().mapToInt(x->x).toArray();
        
        return list;
    }
    
    public int getDays(String yymmdd) {
        String[] ymd = yymmdd.split("[.]");
        int yy = Integer.parseInt(ymd[0]);
        int mm = Integer.parseInt(ymd[1]);
        int dd = Integer.parseInt(ymd[2]);
        
        // 1년은 12달이면서 한달에 28일을 가지고 있음 (문제 설명)
        // 2000 ~ 2022년까지 있음 (yyyy)
        int calculate_days = dd + mm * 28 + (yy - 2000) * 12 * 28;
        return calculate_days;
    }
}