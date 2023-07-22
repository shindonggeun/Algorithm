import java.util.*;

class Solution {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;
        int[] arr = {a, b, c, d};
        Arrays.sort(arr);   // 오름차순 정렬 (a, b, c, d 중 최소값 뽑기 위해)
        Map<Integer, Integer> map = new HashMap<>();    // key: 주사위 값, value: 해당 값이 나온 횟수
        
        for(int i=0; i<4; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        // 1. 4개의 주사위에서 나온 숫자가 모두 같은 경우
        if(map.size() == 1) {
            answer = 1111 * a;
        }
        // 2. 세 주사위에서 나온 숫자가 모두 같고, 나머지 다른 주사위에서 나온 숫자가 다른 경우
        // 3. 주사위가 두 개씩 같은 값이 나온 경우 (서로 짝 지어짐)
        else if(map.size() == 2) {
            int p = 0;
            int q = 0;
            boolean find = false;   // 2번 조건인지 3번 조건인지 판단해주는 변수
            
            // 맵에 저장된 key값들 순회
            for(int num: map.keySet()) {
                // 2번 조건 -> key값에 맞는 value값이 3인 경우(즉, 세 주사위의 나온 숫자가 모두 같은 것)
                if(map.get(num) == 3) {
                    find = true;    // 2번 경우 만족
                    p = num;
                }
                // 2번 조건 -> 그 이외의 경우 (세 주사위의 나온 숫자가 모두 같으면서 나머지 하나는 다른 경우)
                else if(map.get(num) == 1){
                    q = num;
                }
                // 3번 조건 -> 주사위 두개씩 짝지어지면서 같은 것
                else {
                    break; // 반복문 빠져나옴
                }
            }
            // 2번 조건인 경우
            if(find) {
                // (10 * p + q)의 제곱 -> (10 * p + q) * (10 * p + q) 와 같은 의미
                // Math.pow() 메서드는 return 값이 dobule 이므로 int형으로 강제 형변환 해줘야 한다!
                answer = (int) Math.pow(10 * p + q, 2);    
            }
            // 3번 조건인 경우
            else {
                // arr 위에서 오름차순 정렬했으므로
                // 두개씩 짝지어지면 ex) [2, 2, 3, 3] 이런식으로 정렬되므로
                answer = (arr[0] + arr[2]) * Math.abs(arr[0] - arr[2]);
            }
             
        }
        // 4. 두 주사위에서 나온 숫자가 서로 같고(p) 나머지 두 주사위는 서로 각각 다른 숫자인 경우(q, r)
        else if(map.size() == 3) {
            List<Integer> list = new ArrayList<>();
            for(int num: map.keySet()) {
                if(map.get(num) == 1) {
                    list.add(num);
                }
            }
            answer = list.get(0) * list.get(1); // q * r
        }
        // 5. 네 주사위에 적힌 숫자가 모두 다른 경우
        else if(map.size() == 4) {
            answer = arr[0];
        }
        
        return answer;
    }
}