import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        // 순서보장되는 HashSet, 중복허용 X
        LinkedHashSet<String> set = new LinkedHashSet<>();  // 문제에서 주기억장치라고 보면 된다
        
        // 도시 순서대로 탐색
        for(String city: cities) {
            city = city.toLowerCase();  // 도시이름 소문자로 변환
            
            // 캐쉬 사이즈가 set 사이즈와 같아지는 경우(즉 주기억장치 사이즈와 같아지는 경우)
            if(cacheSize == set.size()) {
                // 주기억장치에 저장된 도시인 경우
                if(set.contains(city)) {
                    set.remove(city);   // 그 도시 주기억장치에서 제거한 뒤
                    set.add(city);      // 맨 뒤에 다시 저장해줌
                    answer+=1;          // cache hit이므로 +1 더해줌
                }
                // 주기억장치에 저장된 도시가 아닌 경우
                else {
                    // 가장 먼저 저장된 도시만 삭제해주고 반복문 빠져나옴
                    for(String str: set) {
                        set.remove(str);
                        break; 
                    }
                    set.add(city);  // 도시 주기억장치에 저장해줌
                    answer+=5;      // chche miss이므로 실행시간 +5 더해줌
                }
            }
            // 캐쉬 사이즈가 0인 경우
            else if(cacheSize == 0) {
                answer+=5;         // 주기억장치에 저장될 공간 아예 없으므로 chach miss임, 그러므로 +5 더해줌
            }
            // 캐쉬 사이즈가 0이 아니면서 캐쉬 사이즈가 set 사이즈와 같지 않은 경우
            else {
                // 주기억장치에 저장된 도시이름인 경우
                if(set.contains(city)) {
                    set.remove(city);   // 해당 도시를 삭제한 뒤
                    set.add(city);  // 다시 뒤에 추가한다
                    answer+=1;      // chche hit이므로 +1 더해줌
                }
                // 주기억장치에 저장된 도시이름이 아닌 경우
                else {
                    set.add(city);  // 해당 도시를 뒤에 추가해준다
                    answer+=5;      // chche miss이므로 +5 더해줌
                }
            }
            
        }
        
        return answer;
    }
}