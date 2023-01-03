import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        // 참가자 map에 집어넣기
        for (String player : participant) {
            map.put(player, map.getOrDefault(player, 0) + 1);
        }
        //System.out.println(map);
        // 완주자 map에 집어넣기 (value값 1씩 줄어듬) -> 완주자는 value값이 0임
        for (String player : completion) {
            map.put(player, map.get(player) - 1);
        }
        //System.out.println(map);
        // map에 저장된 key값들 뽑기(반복문 이용)
        for (String key : map.keySet()) {
            // key값을 이용해서 value값을 뽑는데 0이 아닌경우(완주 못한 경우)
            if (map.get(key) != 0){
                answer = key;
            }
        }
        return answer;
    }
}