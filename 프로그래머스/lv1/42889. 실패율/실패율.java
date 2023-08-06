import java.util.*;

class Solution {

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];  
        double[] stage_human = new double[N];   // 각 스테이지에 머물러있는 사용자 수을 담은 배열
        double[] fail = new double[N];          // 각 스테이지에 따른 실패율을 담은 배열
        
        HashMap<Integer, Double> map = new HashMap<>();  // 스테이지 번호가 Key, 실패율이 value
        
        double human_num = stages.length;       // 총 사용자 수
        
        // 각 스테이지 반복
        for(int stage: stages) {
            // 스테이지개수 + 1 한것이 아직 클리어하지 못한 스테이지(스테이지 배열에서 최종 스테이지)와 같은 경우
            if(stage == N+1) {
                continue;
            }
            stage_human[stage-1]++;     // 각 스테이지에 머물러 있는 사용자 수 증가
                                        // 배열은 0번방부터 시작하므로 stage - 1
        }
        //System.out.println(Arrays.toString(stage_human));
        
        // 스테이지 개수만큼 반복문 돌리기
        for(int i=0; i<N; i++) {
            // 스테이지의 머물러 있는 사용자수가 0인 경우
            if(stage_human[i] == 0) {
                fail[i] = 0.0;  // 실패율 0으로 세팅
            }
            // 스테이지의 머무러 있는 사용자수가 0이 아닌 경우
            else {
                fail[i] = stage_human[i] / human_num;   // 실패율 계산   
            }      
            human_num = human_num - stage_human[i];
            map.put(i+1, fail[i]);  // hashmap에 세팅 (key: 스테이지 번호, value: 실패율)
        }
        //System.out.println(Arrays.toString(fail));
        //System.out.println(map);
        List<Integer> list = new ArrayList<>(map.keySet()); // map의 key값을 list에 세팅
        //System.out.println(list);
        Collections.sort(list, (a, b) -> Double.compare(map.get(b), map.get(a)));   // map의 value값 기준으로 내림차순 정렬
        //System.out.println(list);
        
        // Integer ArrayList을 int 배열로 변환
        answer = list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        
        return answer;
    }

}