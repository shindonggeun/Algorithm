import java.util.*;

class Solution {
    public List<String> solution(String[] record) {
        //String[] answer = {};
        List<String> answer = new ArrayList<>();
        Map<String, String> map = new LinkedHashMap<>();    // 저장순서까지 보장되게끔
        
        for(int i=0; i<record.length; i++) {
            String[] log = record[i].split(" ");
            // 입장한 경우
            if(log[0].equals("Enter")) {
                map.put(log[1], log[2]);    // key: 유저 아이디, value: 닉네임
            }
            // 닉네임 변경한 경우 (해당 key에 해당하는 값 다시 변경)
            else if(log[0].equals("Change")) {
                map.put(log[1], log[2]);    
            }
        }
        
        //System.out.println(map);
        for(int i=0; i<record.length; i++) {
            String[] log = record[i].split(" ");
            if(log[0].equals("Enter")) {
                //System.out.println(map.get(log[1]));
                answer.add(map.get(log[1]) + "님이 들어왔습니다.");
            }
            else if(log[0].equals("Leave")) {
                //System.out.println(map.get(log[1]));
                answer.add(map.get(log[1]) + "님이 나갔습니다.");
            }
         }
        
        return answer;
    }
}