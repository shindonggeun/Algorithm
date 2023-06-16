import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for(String oper: operations) {
            String[] operSplit = oper.split(" ");
            int num = Integer.parseInt(operSplit[1]);
            
            // 삽입 연산
            if(operSplit[0].equals("I")) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            // 삭제 연산
            else if(operSplit[0].equals("D")) {
                // 이중우선순위큐(트리맵)가 비어있는 경우 연산 무시
                if(map.size() == 0) {
                    continue;
                }
                
                // 최소값 삭제 연산인 경우(-1) map.firstKey()
                // 최대값 삭제 연산인 경우(1) map.lastKey() 
                int key = num == -1 ? map.firstKey() : map.lastKey();
                int cnt = map.get(key);
                
                if(cnt == 1) {
                    map.remove(key);
                }
                else {
                    map.put(key, cnt - 1);
                }
            }
        }
        
        if(map.size() == 0) {
            answer[0] = 0;
            answer[1] = 0;
        }
        else {
            answer[0] = map.lastKey();
            answer[1] = map.firstKey();
        }
        
        return answer;
    }
}