import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];  
        int[] stageHumanArr = new int[N+1];   // 각 스테이지에 머물러 있는 사람들을 저장한 배열
                                              // [1] = 1번 스테이지에 머물러 있는 사람
        double[] failArr = new double[N+1]; // 각 스테이지마다 실패율을 저장한 배열 ([1] = 1번 스테이지 실패율)
        
        for(int stage: stages) {
            // 스테이지개수 + 1 한것이 아직 클리어하지 못한 스테이지(스테이지 배열에서 최종 스테이지)와 같은 경우
            // 즉, 마지막 스테이지(N번째 스테이지) 까지 클리어한 사용자를 나타내므로 최종 스테이지에 머물러 있지 않다
            if(stage == N+1) {
                continue;   // 넘어감
            }
            stageHumanArr[stage]++; // 해당 스테이지에 머물러있는 사람 증가
        }
        
        Map<Integer, Double> map = new HashMap<>(); // key: 스테이지 번호, value: 실패율
        int humanNum = stages.length;    // 총 사용자 수
        for(int i=1; i<=N; i++) {
            // 해당 스테이지에 머물러 있는 사람이 없는 경우
            if(stageHumanArr[i] == 0) {
                failArr[i] = 0.0;   // 해당 스테이지번호에 따른 실패율 0으로 저장
            }
            else {
                failArr[i] = (double)stageHumanArr[i] / humanNum;   // 해당 스테이지에 따른 실패율 저장
            }
            humanNum -= stageHumanArr[i];   // 총 사용자 수에서 해당 스테이지에 머무른 사람 수 빼주기
            map.put(i, failArr[i]); // 맵에 해당 스테이지 번호와 해당 스테이지에 따른 실패율 저장
        }
        List<Integer> list = new ArrayList<>(map.keySet()); // 맵의 key값을 ArrayList에 세팅
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer key1, Integer key2) {
                // 맵에 저장된 value값(실패율)을 기준으로 내림차순 정렬해서 
                // 정렬한 실패율에 맞는 스테이지 번호를 정렬해준다
                // 실패율은 double타입이므로 Double.compare()를 이용한다
                return Double.compare(map.get(key2), map.get(key1));
            }
        });
        
        // 최종으로 answer배열에 값 넣어주기
        for(int i=0; i<answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}