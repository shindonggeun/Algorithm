import java.util.*;

class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        int answer = 0;
        Map<Integer, Integer> rankMap = new HashMap<>();    // key: 등수, value: 학생번호
        Map<Integer, Boolean> attendMap = new HashMap<>();  // key: 학생 번호, value: 참석여부
        
        for(int i=0; i<rank.length; i++) {
            rankMap.put(rank[i], i);
            attendMap.put(i, attendance[i]);
        }
        
        List<Integer> list = new ArrayList<>(rankMap.keySet()); // map의 key값들 list화 (등수를 저장한 리스트)
        Collections.sort(list); // 등수 오름차순 정렬
        
        int[] arr = new int[3]; // 1등부터 3등까지
        int index = 0;  // 인덱스 0번부터
        // 1등부터 탐색
        for(int rankKey: list) {
            int numIdx = rankMap.get(rankKey);
            // 해당 번호를 가진 학생이 전국대회 참석 가능한 경우
            if(attendMap.get(numIdx)) {
                arr[index] = numIdx;    
                index++;
                // 인덱스 3 된 경우 반복문 빠져나옴 (3등까지만 선발해야 하므로)
                if(index == 3) {
                    break;
                }
            }
        }
        answer = 10000 * arr[0] + 100 * arr[1] + arr[2];
        
        return answer;
    }
}