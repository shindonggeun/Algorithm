import java.util.*;
import java.io.*;

public class Main {

    static class Skill {
        int coolTime; // 스킬의 대기 시간
        int damage;   // 스킬의 대미지

        public Skill(int coolTime, int damage) {
            this.coolTime = coolTime;
            this.damage = damage;
        }
    }

    static int N; // 스킬 개수
    static Skill[] skillArr; // 스킬 정보를 담은 배열
    static int[] coolTimeArr; // 각 스킬의 다음 사용 가능 시간을 저장하는 배열
    static int minTime; // 몬스터를 처치하는 최소 시간

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int hp = Integer.parseInt(st.nextToken()); // 몬스터 체력 입력

        skillArr = new Skill[N]; // [0] ~ [N-1]
        coolTimeArr = new int[N]; // [0] ~ [N-1]
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int coolTime = Integer.parseInt(st.nextToken());
            int damage = Integer.parseInt(st.nextToken());
            skillArr[i] = new Skill(coolTime, damage);
        }

        minTime = Integer.MAX_VALUE; // 몬스터를 처치하는 최소 시간 최대값을 초기화
        
        calculateMonsterDamageTime(0, hp); // 백트래킹 탐색 시작 (현재 시간 0초, 현재 몬스터 체력 hp)
        
        System.out.println(minTime); // 결과 출력
    }
    
    // 백트래킹 알고리즘을 이용하여 시간당 몬스터에게 데미지를 입히는 메서드
    // nowTime: 현재 시간
    // hp: 현재 몬스터의 체력
    public static void calculateMonsterDamageTime(int nowTime, int hp) {
    	// 현재 몬스터의 체력이 0보다 작거나 같은 경우 (즉, 몬스터를 처치한 경우 - 기저 조건)
    	if (hp <= 0) {
    		minTime = Math.min(minTime, nowTime); // 몬스터를 처치하는데 걸린 최소 시간 갱신
    		return; // 메서드 종료
    	}
    	
    	boolean isSkill = false; // 현재 시간에 사용할 수 있는 스킬이 있는지 확인하는 플래그 변수
    	
    	// 모든 스킬 탐색
    	for (int i=0; i<N; i++) {
    		// 현재 시간이 스킬 i의 대기 시간 이후인 경우
    		// 현재 스킬의 대기시간이 현재 시간보다 작거나 같은 경우 (즉, 현재 스킬 사용 가능한 경우)
    		if (coolTimeArr[i] <= nowTime) {
    			isSkill = true; // 스킬 사용했다고 처리
    			int temp = coolTimeArr[i]; // 현재 스킬의 쿨타임 상태를 임시 저장
    			coolTimeArr[i] = nowTime + skillArr[i].coolTime; // 스킬 사용 후 해당 시간으로 쿨타임 갱신
    			// 현재 시간 1초 증가시키면서 몬스터 체력을 현재 스킬의 데미지를 입힌채로 해당 메서드 재귀 호출
    			calculateMonsterDamageTime(nowTime + 1, hp - skillArr[i].damage);
    			coolTimeArr[i] = temp; // 스킬의 쿨타임 상태 복구 (백트래킹) (모든 탐색 경로 독립성 유지)
    		}
    	}
    	
    	// 현재 시간에 사용할 수 있는 스킬이 없는 경우
    	if (!isSkill) {
    		// 스킬을 사용하지 않았으므로 현재 시간을 1초 증가시키면서 몬스터 체력은 그대로 해당 메서드 재귀 호출
    		calculateMonsterDamageTime(nowTime + 1, hp);
    	}
    }

}