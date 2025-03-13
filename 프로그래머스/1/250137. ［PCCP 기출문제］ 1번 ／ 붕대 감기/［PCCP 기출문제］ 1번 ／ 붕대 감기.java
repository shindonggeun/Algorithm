import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        // key: 공격 시간, value: 피해량
        Map<Integer, Integer> attackMap = new HashMap<>();
        
        // 해시맵에 공격시간 및 피해량 값들 넣어주는 과정
        for (int[] attack: attacks) {
            int time = attack[0];   // 공격 시간
            int damage = attack[1]; // 피해량
            
            attackMap.put(time, damage);
        }
        
        int maxHealth = health; // 최대 체력
        int lastAttackTime = attacks[attacks.length-1][0];
        int nowTime = 0;    // 현재 시간 0초부터 시작
        int continueTime = 0;   // 연속 체력 회복 성공 시간 0초부터 시작
        
        
        while (true) {
            nowTime++;  // 현재 시간 증가
            
            // 현재 시간이 마지막 공격 시간을 넘은 경우
            if (nowTime > lastAttackTime) {
                break;  // 무한반복 빠져나옴
            }
            
            // 현재 시간이 공격 당하는 시간인 경우
            if (attackMap.containsKey(nowTime)) {
                health -= attackMap.get(nowTime);   // 공격 시간에 해당하는 피해량 입힘
                continueTime = 0;   // 연속 체력 회복 성공 시간 0으로 초기화
            }
            else {
                health += bandage[1];   // 초당 회복량 더해줌
                continueTime++; // 연속 체력 회복 성공 시간 증가
                
                // 연속 체력 회복 성공 시간이 연속 체력 회복 시전시간이 된 경우
                if (continueTime == bandage[0]) {
                    health += bandage[2];   // 추가 회복량 더해줌
                    continueTime = 0;   // 연속 체력 회복 성공 시간 0으로 초기화
                }
            }
            
            // 현재 체력이 최대 체력보다 큰 경우
            if (health > maxHealth) {
                health = maxHealth; // 현재 체력 최대 체력으로
            }
            
            // 현재 체력이 0보다 작거나 같은 경우
            if (health <= 0) {
                health = -1;    // 현재 체력 -1로 만들어줌
                break;  // 무한반복 빠져나옴
            }
            
        }
        
        answer = health; // 결과값 저장
        
        return answer;
    }
}