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
    static Skill[] skillArr; // 스킬 정보 배열
    static boolean[] visited;
    static Skill[] permutationSkill;
    static int minTime; // 몬스터를 처치하는 최소 시간

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 스킬 개수
        int hp = Integer.parseInt(st.nextToken()); // 몬스터 체력

        skillArr = new Skill[N];
        visited = new boolean[N];
        permutationSkill = new Skill[N];
        
        int totalTime = 0;
        int totalDamage = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int coolTime = Integer.parseInt(st.nextToken());
            int damage = Integer.parseInt(st.nextToken());
            skillArr[i] = new Skill(coolTime, damage);
            
            totalTime += coolTime;
            totalDamage += damage;
        }

        minTime = Integer.MAX_VALUE; // 최소 시간 초기화
        
        int limit = totalTime * ((hp / totalDamage) + 1);
        generatePermutation(0, hp, limit);

        System.out.println(minTime); // 결과 출력
    }
    
    public static void generatePermutation(int depth, int hp, int limit) {
    	if (depth == N) {
    		int[] tempDamageArr = new int[limit];
    		monsterDamageCalculate(tempDamageArr);
    		
    		minTime = Math.min(minTime, calculateTime(tempDamageArr, hp));
    		return;
    	}
    	
    	for (int i=0; i<N; i++) {
    		if (!visited[i]) {
    			visited[i] = true;
    			permutationSkill[depth] = skillArr[i];
    			generatePermutation(depth + 1, hp, limit);
    			visited[i] = false;
    		}
    	}
    }
    
    public static void monsterDamageCalculate(int[] tempDamageArr) {
    	for (Skill skill: permutationSkill) {
    		int idx = 0;
    		while (idx < tempDamageArr.length) {
    			if (tempDamageArr[idx] != 0) {
    				idx++;
    				continue;
    			}
    			tempDamageArr[idx] = skill.damage;
    			idx += skill.coolTime;
    		}
    	}
    }
    
    public static int calculateTime(int[] tempDamageArr, int hp) {
    	int time = 0;
    	for (int damage: tempDamageArr) {
    		if (hp <= 0) {
    			break;
    		}
    		hp -= damage;
    		time++;
    	}
    	return time;
    }
}