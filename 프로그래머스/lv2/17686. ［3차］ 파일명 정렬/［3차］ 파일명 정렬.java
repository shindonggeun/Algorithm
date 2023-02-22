import java.util.*;

class Solution {
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        Arrays.sort(files, new Comparator<String>() {
           @Override
            public int compare(String s1, String s2) {
                String head1 = s1.split("[0-9]")[0];
                String head2 = s2.split("[0-9]")[0];
                String number1 = "";
                String number2 = "";
                
                int headCompare = head1.toUpperCase().compareTo(head2.toUpperCase());   // 두 파일의 head 대문자 처리하고 나서 비교
                // 두 파일의 head명이 같은 경우
                if(headCompare == 0) {
                    // 각 파일명에서 head명 이후부터 잘라내서 추출(head명 제외하고 파일명 추출)
                    s1 = s1.substring(head1.length());  
                    s2 = s2.substring(head2.length());  
                    
                    // 문자열 s1을 char[] 배열로 처리해서 한글자씩 뽑아내기
                    for(char c: s1.toCharArray()) {
                        // 뽑아낸 글자가 숫자이면서 이어붙인 숫자의 길이가 5이하여야함 
                        // NUMBER는 0 ~ 99999까지의 연속된 숫자로 이루어짐
                        if(Character.isDigit(c) && number1.length() <= 5) {
                            number1+=c; // 숫자 이어붙이기
                        }
                        else {
                            break;
                        }
                    }
                    
                    for(char c: s2.toCharArray()) {
                        if(Character.isDigit(c) && number2.length() <= 5) {
                            number2+=c;
                        }
                        else {
                            break;
                        }
                    }
                    
                    return Integer.parseInt(number1) - Integer.parseInt(number2);   // 파일명을 숫자순으로 정렬
                }
                
                // 위의 경우가 아닌 경우(두 파일명의 head부분이 같지 않은 경우)
                // 파일명을 head 부분을 기준으로 사전순으로 정렬
                return headCompare;
            } 
        });
        
        for(int i=0; i<files.length; i++) {
            answer[i] = files[i];
        }
        
        return answer;
    }
}