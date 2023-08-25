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
        
        int compressMinLength = Integer.MAX_VALUE;
        StringBuilder sb;
        for(int i=1; i<=length; i++) {
            sb = new StringBuilder();
            int leftIdx = 0;
            int rightIdx = leftIdx + i;
            int duplicateCount = 1;
            
            String compressStr1 = s.substring(leftIdx, rightIdx);
            sb.append(compressStr1);
            
            while(rightIdx + i <= s.length()) {
                String compressStr2 = s.substring(rightIdx, rightIdx+i);
                
                if(!compressStr1.equals(compressStr2)) {
                    leftIdx = rightIdx;
                    compressStr1 = compressStr2;
                    
                    if(duplicateCount > 1) {
                        sb.insert(sb.length()-i, duplicateCount);
                    }
                    
                    sb.append(compressStr1);
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