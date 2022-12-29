import java.util.*;

class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        ArrayList<Integer> ironList = new ArrayList<>();
        
        for(int i=1; i<=number; i++) {
            ironList.add(divisorCount(i, limit, power));
        }
        
        for(int iron: ironList) {
            answer+=iron;
        }
        
        return answer;
    }
    
    public static int divisorCount(int num, int limit, int power) {
        int count = 0;
        
        for(int i=1; i<=Math.sqrt(num); i++) {
            if(num % i == 0) {
                if(num / i == i) {
                    count++;
                }
                else {
                    count+=2;
                }
            }
            
            if(limit < count) {
                count = power;
                break;
            }
        }
        
        return count;
    }
}