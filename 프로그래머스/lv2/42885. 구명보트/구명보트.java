import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);    // 오름차순 정렬
        
        // 투포인터 알고리즘 이용
        int left = 0;
        int right = people.length-1;
        
        while(left<=right) {
            // 가리키는 사람이 같은 경우 (사람수가 홀수인 경우 발생)
            if(left == right) {
                answer++;   // 보트 수 증가
                break;
            }
            
            if(people[left] + people[right] <= limit) {
                answer++;   // 보트 수 증가  
                // 두명 다 태움
                left++;
                right--;    
            }
            else {
                answer++;   
                right--;    // 제일 무거운애 한명만 태움
            }
        }
        
        return answer;
    }
}