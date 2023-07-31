import java.util.*;

class Solution {
    public String[] solution(String my_str, int n) {
        String[] answer = {};
        List<String> list = new ArrayList<>();  // 해당 문자열을 길이만큼 잘라서 저장할 리스트
        
        // 문자열 잘라서 넣을 수 있도록 반복문 돌려주기 (증감 i+=n 인거 조심)
        for(int i=0; i<my_str.length(); i+=n) {
            String makeStr = "";    // 만들 문자열
            // i+n이 주어진 문자열의 길이보다 작은 경우
            if(i + n < my_str.length()) {
                // substring(시작인덱스, 끝인덱스) 이용해서 문자열 잘라주기
                // 문자열의 시작인덱스부터 끝 인덱스 전! 까지 문자열 잘라서 저장
                makeStr = my_str.substring(i, i+n);  
            }
            // i+n이 주어진 문자열의 길이보다 길거나 같은 경우
            else {
                // substring(시작인덱스) 이용해서 문자열 잘라주기
                // 문자열의 시작인덱스부터 해당 문자열의 끝까지 문자열 잘라서 저장 
                makeStr = my_str.substring(i);  
            }
            list.add(makeStr);  // 위의 문자열 자른것들 리스트에 저장
        }
        
        answer = new String[list.size()];
        // 리스트에 저장된 문자열 String 배열에 저장할 수 있게끔 반복문 돌리기
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}