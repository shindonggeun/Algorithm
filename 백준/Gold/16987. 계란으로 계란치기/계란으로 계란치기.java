import java.util.*;
import java.io.*;

public class Main {
	
	// 계란 정보를 저장하는 클래스
	static class Egg {
		int durability; // 계란의 내구도
		int weight; // 계란의 무게
		
		public Egg(int durability, int weight) {
			this.durability = durability;
			this.weight = weight;
		}
	}
	
	static int N; // 계란의 개수
	static Egg[] eggs; // 계란들의 정보를 저장할 배열
	static int maxBrokenCount; // 최대 깬 계란 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N]; // [0] ~ [N-1]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int durability = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			eggs[i] = new Egg(durability, weight);
		}
		
		maxBrokenCount = 0; // 최대 깬 계란 개수 초기화
		eggHit(0); // 첫 번째 계란부터 탐색 시작
		
		System.out.println(maxBrokenCount);
	}
	
	// 현재 단계에서 가능한 모든 경우를 탐색하여 최대 깬 계란 개수를 구하는 메서드 (백트래킹 알고리즘 이용)
	// depth: 현재 손에 든 계란의 인덱스 
	public static void eggHit(int depth) {
		// 모든 계란들을 탐색한 경우 (기저 조건 1)
		if (depth == N) {
			int brokenCount = calculateBrokenEggCount(); // 현재 상태에서 깬 계란의 개수 계산
			maxBrokenCount = Math.max(maxBrokenCount, brokenCount); // 최대 깬 계란 수 갱신
			return; // 메서드 종료
		}
		
		// 현재 계란의 내구도가 0보다 작거나 같은 경우 (즉, 현재 계란이 깨진 경우) (기저 조건 2)
		if (eggs[depth].durability <= 0) {
			eggHit(depth + 1); // 다음 계란 탐색
			return; // 메서드 종료
		}
		
		boolean isHit = false; // 다음 계란과 부딪침이 발생했는지 여부를 나타내는 변수 초기값 false로 저장
		
		// 다른 모든 계란과 부딪치는 과정
		for (int i=0; i<N; i++) {
			// 자기 자신이거나 깨진 계란인 경우
			if (i == depth || eggs[i].durability <= 0) {
				continue; // 다음 계란 탐색하도록 넘어감
			}
			
			// 현재 계란과 i번째 계란 부딪치기
			eggs[depth].durability -= eggs[i].weight; // i번째 계란의 무게만큼 현재 계란의 내구도 감소
			eggs[i].durability -= eggs[depth].weight; // 현재 계란의 무게만큼 i번째 계란의 내구도 감소
			
			isHit = true; // 다른 계란과 부딪힘 발생
			
			eggHit(depth + 1); // 다음 계란으로 넘어가서 탐색 시작
			
			// 백트래킹: 상태 복원
			eggs[depth].durability += eggs[i].weight; // i번째 계란의 무게만큼 현재 계란의 내구도 복구
			eggs[i].durability += eggs[depth].weight; // 현재 계란의 무게만큼 i번째 계란의 내구도 복구
		}
		
		// 현재 계란이 다른 계란과 부딪치지 않은 경우 (즉, 부딪칠 수 없는 경우)
		if (!isHit) {
			eggHit(depth + 1); // 다음 계란으로 넘어감
		}
	}
	
	// 현재 상태에서 꺤 계란의 개수를 계산하는 메서드
	public static int calculateBrokenEggCount() {
		int count = 0; // 깬 계란의 개수
		
		// 모든 계란 탐색
		for (Egg egg: eggs) {
			// 해당 계란의 내구도가 0 이하인 경우
			if (egg.durability <= 0) {
				count++; // 깬 계란 개수 증가
			}
		}
		
		return count;
	}

}