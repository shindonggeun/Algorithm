import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        char ch = 'A';
        HashMap<String, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        for(int i=1; i<27; i++) {
            // char 타입인 ch을 String으로 변환해서 저장
            map.put(ch + "", i);
            ch++;
        }
        
        int idx = 27;   // 다음 인덱스를 나타냄
        boolean isEnd = false;  // 끝을 나타내는 변수
        
        for(int i=0; i<msg.length(); i++) {
            char msgCh = msg.charAt(i);
            String word = msgCh + "";
            
            // 사전에 해당 단어가 있으면 반복문 시행 (없는 경우 while문 빠져나옴)
            while(map.containsKey(word)) {
                i++;    
                // i가 해당 단어의 인덱스를 넘어가면 while문 빠져나옴
                if(i == msg.length()) {
                	isEnd = true;
                	break;
                }
                word+=msg.charAt(i);    // 단어 이어붙이기
                //System.out.println(word);
            }
            // 해당 단어의 끝인 경우
            if(isEnd) {
            	list.add(map.get(word));    // 해당 단어의 색인번호 추가해줌
            	break;                      // 반복문 종료
            }
            
            list.add(map.get(word.substring(0, word.length() - 1)));    // 해당 단어 잘라 붙인거 색인번호 등록
            map.put(word, idx++);   // 해당 단어와 색인번호 사전에 등록
            
            i--;
        }
        //System.out.println(map);
        
        answer = new int[list.size()];
        
        // list에 저장된 값 배열로
        for(int i=0; i<answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}