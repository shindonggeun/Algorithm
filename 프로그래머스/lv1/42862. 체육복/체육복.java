import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        answer = n - lost.length;   // 전체 학생 수에서 체육복 도난당한 학생수 뺀 다음
        Arrays.sort(lost);      // 체육복 잃어버린 학생 배열 오름차순 정렬
        Arrays.sort(reserve);   // 체육복 여분 가지고있는 학생 배열 오름차순 정렬
        
         // 여벌 체육복을 가져온 학생이 도난당한 경우
        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                // 도난당한 체육복이 여벌 체육복인 경우(남에게 못빌려주고 당사자만 입어야함)
                if(lost[i] == reserve[j]){
                    answer++;   // 당사자 착용
                    lost[i] = -1;   // 도난당한 사람의 값 번호 -1로
                    reserve[j] = -1;    // 빌려준사람의 값 번호 -1로
                    break; 
                }
            }
        }
        // 도난당한 학생에게 체육복 빌려주는 경우
        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                // 체육복을 바로 앞번호 학생이나 뒷번호 학생에게 빌려줄 수 있음
                if((lost[i]-1 == reserve[j]) || (lost[i]+1 == reserve[j])){
                    answer++;   // 착용한 사람 수 증가
                    reserve[j] = -1; // 빌려준사람의 번호 값 -1로
                    break; 
                }
            }
        }
        
        return answer;
    }
}