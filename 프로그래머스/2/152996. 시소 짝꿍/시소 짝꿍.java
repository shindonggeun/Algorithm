import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        // 시소 짝꿍을 계산하기 위해 각 몸무게 비율에 따른 개수를 저장하기 위한 해시맵 자료구조
        // key: 몸무게 비율, value: 해당 몸무게의 개수
        Map<Double, Integer> countMap = new HashMap<>();
        
        Arrays.sort(weights); // 사람들의 몸무게 목록 오름차순 정렬
        
        // 사람들의 몸무게 순회
        for (int weight: weights) {
            // 해당 몸무게와 동일한 비율의 경우
            double a = weight * 1.0; // 자신과 동일한 몸무게의 쌍을 찾기 위해 1.0을 곱해줌 (double 타입)
            
            // 해당 몸무게를 가진 사람이 3m에 앉을 때, 반대쪽 사람이 2m에 앉으면 균형이 되는 비율 (2:3)
            double b = (weight * 2.0) / 3.0;
            
            // 해당 몸무게를 가진 사람이 4m이 앉을 때, 반대쪽 사람이 2m에 앉으면 균형이 되는 비율 (2:4 -> 1:2)
            double c = (weight * 1.0) / 2.0;
            
            // 해당 몸무게를 가진 사람이 4m에 앉을 때, 반대쪽 사람이 3m에 앉으면 균형이 되는 비율 (3:4)
            double d = (weight * 3.0) / 4.0;
            
            // 동일한 몸무게의 쌍이 존재하는 경우
            if (countMap.containsKey(a)) {
                answer += countMap.get(a); // 해당 몸무게를 가진 사람들과 시소 짝꿍이 가능하므로 그 쌍의 수만큼 누적
            }
            
            // 해당 몸무게 * 2 / 3 비율의 쌍이 존재하는 경우
            if (countMap.containsKey(b)) {
                answer += countMap.get(b); // 해당 비율을 가진 사람과 쌍이 가능하므로 그 수만큼 누적
            }
            
            // 해당 몸무게 * 1 / 2 비율의 쌍이 존재하는 경우
            if (countMap.containsKey(c)) {
                answer += countMap.get(c); // 해당 비율을 가진 사람과 쌍이 가능하므로 그 수만큼 누적
            }
            
            // 해당 몸무게 * 3 / 4 비율의 쌍이 존재하는 경우
            if (countMap.containsKey(d)) {
                answer += countMap.get(d); // 해당 비율을 가진 사람과 쌍이 가능하므로 그 수만큼 누적
            }
            
            // 해당 몸무게를 가진 사람 countMap에 추가하거나, 기존 값이 있는 경우 누적
            countMap.put((weight * 1.0), countMap.getOrDefault(weight * 1.0, 0) + 1);
        }
        
        // 최종적으로 계산된 시소 짝꿍의 개수 반환
        return answer;
    }
}