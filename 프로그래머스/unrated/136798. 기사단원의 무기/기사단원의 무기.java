import java.util.*;

class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        ArrayList<Integer> ironList = new ArrayList<>();    // 각 기사의 무기를 만들때 소모되는 철의 무게를 담을 배열
        
        for(int i=1; i<=number; i++) {
            ironList.add(divisorCount(i, limit, power));    // 철의 무게를 담을 배열에 값들 추가해줌
        }
        // 필요한 철의 무게 계산
        for(int iron: ironList) {
            answer+=iron;   
        }
        
        return answer;
    }
    
    // 약수 개수 구하는 메서드 
    public static int divisorCount(int num, int limit, int power) {
        int count = 0;
        
        // 제곱근수까지만 반복문 돌림
        for(int i=1; i<=Math.sqrt(num); i++) {
            if(num % i == 0) {
                if(num / i == i) {
                    count++;
                }
                else {
                    count+=2;
                }
            }
            // 약수개수가 제한수치를 넘어버린 경우
            if(limit < count) {
                count = power;  // 약수개수를 공격력으로 치환시킴
                break;          // 그리고 반복문 빠져나옴
            }
        }
        
        return count;
    }
}