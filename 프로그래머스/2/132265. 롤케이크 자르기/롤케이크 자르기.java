import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Set<Integer> leftSet = new HashSet<>(); // 철수가 먹을 토핑들을 저장하는 해시셋 (왼쪽 담당)
        // 동생이 먹을 토핑들을 저장하는 해시맵
        Map<Integer, Integer> rightMap = new HashMap<>(); // key: 해당 토핑 번호, value: 해당 토핑의 개수
        
        // 1. 동생이 먹을 토핑들을 먼저 저장
        for (int to: topping) {
            rightMap.put(to, rightMap.getOrDefault(to, 0) + 1); // 해당 토핑이 나올때마다 개수를 증가시켜서 저장
        }
        
        int cutCount = 0; // 공평하게 자르는 방법의 수
        
        // 2. 자를 수 있는 모든 위치에서 철수와 동생의 토핑들을 비교하는 과정
        for (int to: topping) {
            leftSet.add(to); // 철수가 먹을 수 있는 토핑 저장
            
            rightMap.put(to, rightMap.get(to) - 1); // 동생이 토핑에서 해당 토핑을 하나 뺌
            // 해당 토핑의 개수가 0인 경우 (즉, 동생에게 해당 토핑이 없는 경우)
            if (rightMap.get(to) == 0) {
                rightMap.remove(to); // 해당 토핑을 제거
            }
            
            // 철수의 토핑 가지수와 동생의 토핑 가지수가 같은 경우 (즉, 두 집합의 크기가 동일한 경우)
            if (leftSet.size() == rightMap.size()) {
                cutCount++; // 공평하게 자를 수 있는 경우이므로 방법의 수 증가
            }
        }
        
        answer = cutCount; // 공평하게 자르는 방법의 수 최종 결과값에 저장
        
        return answer;
    }
}