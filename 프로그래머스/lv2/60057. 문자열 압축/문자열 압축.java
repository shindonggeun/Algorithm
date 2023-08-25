import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        int length = 0;
        
        // 문자열의 길이가 짝수인 경우 (즉, 각각 절반으로 나눠 지는경우)
        // 최대 압축가능한 길이는 문자열 s 길이의 절반임
        if(s.length() % 2 == 0) {
            length = s.length() / 2;
        }
        // 문자열의 길이가 홀수인 경우 (즉, 각각 절반으로 나눠지지 않는 경우)
        else {
            length = s.length() / 2 + 1;
        }
        
        int compressMinLength = Integer.MAX_VALUE;  // 문자열 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이
        StringBuilder sb;   // 문자열에서 해당 인덱스에 반복되어 나타난 문자열의 개수를 쉽게 넣기 위해 StringBuilder 이용
        
        // 문자열 최대 압축 길이까지 반복문 돌림
        for(int i=1; i<=length; i++) {
            sb = new StringBuilder();   
            int leftIdx = 0;    // 왼쪽 부분 인덱스
            int rightIdx = leftIdx + i; // 오른쪽 부분 인덱스
            int duplicateCount = 1; // 문자열 반복해서 나타난 횟수 
            
            String compressStr1 = s.substring(leftIdx, rightIdx);   // 왼쪽부분 문자열 압축
            sb.append(compressStr1);    // StringBuilder에 왼쪽부분 문자열 압축한것 저장
            
            while(rightIdx + i <= s.length()) {
                String compressStr2 = s.substring(rightIdx, rightIdx+i);
                if(!compressStr1.equals(compressStr2)) {
                    compressStr1 = compressStr2;
                    
                    if(duplicateCount > 1) {
                        sb.insert(sb.length()-i, duplicateCount);
                    }
                    
                    sb.append(compressStr2);
                    duplicateCount = 1;
                }
                else {
                    duplicateCount++;
                }
                
                rightIdx += i;
            }
            
            if(duplicateCount > 1) {
                sb.insert(sb.length() - i, duplicateCount);
            }
            sb.append(s.substring(rightIdx));
            
            compressMinLength = Math.min(compressMinLength, sb.length());
            // System.out.println(sb);
        }
        answer = compressMinLength;
        
        return answer;
    }
}