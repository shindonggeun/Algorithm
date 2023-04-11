import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        HashMap<String, Integer> map = new HashMap<>(); // key: 사진 속 인물, value: 인물에 해당하는 추억점수
        
        // 사진 속 인물에 따른 추억점수 저장하기
        for(int i=0; i<name.length; i++) {
            map.put(name[i], yearning[i]);
        }
        
        for(int i=0; i<photo.length; i++) {
            for(int j=0; j<photo[i].length; j++) {
                // 사진속에 인물이 존재하면
                if(map.containsKey(photo[i][j])) {
                    answer[i]+=map.get(photo[i][j]);    // 그 인물에 해당하는 추억점수 더해주기
                }
            }
        }
        
        return answer;
    }
}