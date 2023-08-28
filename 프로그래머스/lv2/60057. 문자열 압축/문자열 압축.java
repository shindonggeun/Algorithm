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
            int duplicateCount = 1; // 문자열 압축했을 때 문자 반복해서 나타난 횟수 
            
            String compressStr1 = s.substring(leftIdx, rightIdx);   // 왼쪽부분 문자열 압축
            sb.append(compressStr1);    // StringBuilder에 왼쪽부분 문자열 압축한것 저장
            
            // 오른쪽부분 인덱스 문자열 s 길이까지 탐색할 수 있도록
            while(rightIdx + i <= s.length()) {
                String compressStr2 = s.substring(rightIdx, rightIdx+i);    // 오른쪽부분 문자열 압축
                
                // 왼쪽부분과 오른쪽 부분 문자열 압축한게 서로 같지 않은 경우
                if(!compressStr1.equals(compressStr2)) {
                    compressStr1 = compressStr2;    // 왼쪽부분 문자열 압축을 오른쪽 부분 문자열 압축한걸로 바꿔줌
                    
                    // 문자열 반복해서 나타난 수가 2개 이상인 경우(즉, 문자열 압축했을 때 반복해서 나타난 문자개수가 2개 이상인 경우)
                    if(duplicateCount >= 2) {
                        sb.insert(sb.length()-i, duplicateCount);   // StringBuilder를 이용해서 문자열 압축했을 떄 반복하는 문자 앞에 반복해서 나타난 수 집어넣어줌
                    }
                    
                    sb.append(compressStr2);    // 그다음 오른쪽 부분 문자열 압축 StringBuilder에 넣어줌
                    duplicateCount = 1; // 문자열 압축했을 때 반복해서 나타난 문자개수 1로 초기화
                }
                // 왼쪽부분과 오른쪽 부분 문자열 압축한게 서로 같은 경우 (즉, 문자 반복해서 나타남)
                else {
                    duplicateCount++;   // 문자열 압축했을 때 반복해서 나타난 문자개수 증가
                }
                
                rightIdx += i;  // 오른쪽 부분 인덱스 증가
            }
            
            // 마지막으로 다시 한번 비교해주기
            // 문자열 압축했을 때 반복해서 나타난 문자개수가 2개 이상인 경우
            if(duplicateCount >= 2) {
                sb.insert(sb.length() - i, duplicateCount); // StringBuilder를 이용해서 문자열 압축했을 떄 반복하는 문자 앞에 반복해서 나타난 수 집어넣어줌
            }
            sb.append(s.substring(rightIdx)); 
            
            // 문자열 압축했을 때 최소 길이 갱신
            compressMinLength = Math.min(compressMinLength, sb.length());
            // System.out.println(sb);
        }
        answer = compressMinLength;
        
        return answer;
    }
}