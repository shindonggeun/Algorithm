import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int limit = n+1;
        
        Set<String> set = new LinkedHashSet<>(Arrays.asList(words));
        System.out.println(set);
        
        int number = 1;  // 처음 시작하는 사람의 번호
        int count = 1;  // 자신의 차례를 나타내는 변수
        
        char lastCh = words[0].charAt(words[0].length() - 1);   // 맨 마지막 글자
        for(int i=0; i<words.length; i++) {
            
            if(number == limit) {
                number = 1;
                count++;
            }
            
            
            if(set.contains(words[i])) {
                set.remove(words[i]);
            }
            else {
                answer[0] = number;
                answer[1] = count;
                break;
            }
            
            if(i != 0) {
                if(lastCh != words[i].charAt(0)) {
                    answer[0] = number;
                    answer[1] = count;
                    break;
                }
            }
            
            lastCh = words[i].charAt(words[i].length() - 1);
            number++;
        }
        
        if(set.size() == words.length) {
            answer[0] = 0;
            answer[1] = 0;
        }
        

        return answer;
    }
}