import java.util.*;
import java.io.*;

public class Main {

	// 적군의 정보를 저장하는 내부 클래스
	static class Enemy {
		int x;	// x좌표
		int y;	// y좌표
		int r;	// 통신 범위
		
		public Enemy(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
	}
	
	static int N;	// 적군 진영의 수
	static Enemy[] enemyArr;	// 적군의 정보를 담은 배열
	static int[] parents;	// 유니온-파인드를 위한 부모 배열
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			parents = new int[N+1];	// [1] ~ [N], 부모 배열 초기화
			enemyArr = new Enemy[N+1];	
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				enemyArr[i] = new Enemy(x, y, r);	// 적군의 정보 저장
				parents[i] = i;	// 부모 배열 초기화 (자기 자신을 부모로 설정)
			}
			
			// 모든 적군 진영 쌍에 대하여 통신 가능 여부 확인 및 그룹화 작업
			for(int i=1; i<=N-1; i++) {
				for(int j=i+1; j<=N; j++) {
					Enemy e1 = enemyArr[i];
					Enemy e2 = enemyArr[j];
					
					// 두 적군 진영이 통신 가능한 경우
					if(isPossibleCommunication(e1, e2)) {
						union(i, j);	// 두 적군을 합치는 유니온 연산 수행
					}
				}
			}
			
			int count = 0;	// 적군 그룹의 개수 세기
			for(int i=1; i<=N; i++) {
				// 각 그룹의 대표자인 경우
				if(find(i) == i) {
					count++;	// 카운트 증가
				}
			}
			
			sb.append(count).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 두 적군 진영이 통신이 가능한지 여부를 결정하는 메서드
	public static boolean isPossibleCommunication(Enemy e1, Enemy e2) {
		// 유클리디안 거리 공식 이용
		// 유클라디안 거리의 제곱 계산
		int distanceSquared = (e1.x - e2.x) * (e1.x - e2.x) + (e1.y - e2.y) * (e1.y - e2.y);
		// 통신 범위의 합의 제곱 계산
		int rangeSquared = (e1.r + e2.r) * (e1.r + e2.r);
		
		// 두 진영이 통신 가능한 경우 (즉, 두 진영의 좌표가 유클리디안 거리 공식에 의해 통신 범위 안에 들어온 경우)
		// true 반환 그렇지 않은 경우 false 반환 (통신 가능 여부 반환)
		return distanceSquared <= rangeSquared;
	}
	
	// 유니온-파인드의 find 연산 (재귀적으로 부모를 찾아감)
	public static int find(int a) {
		// 자기 자신이 부모인 경우, 즉 루트인 경우
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);	// 경로 압축을 위해 부모를 루트로 설정
	}
	
	// 유니온파인드의 union 연산 (두 원소의 집합을 합침)
	public static void union(int a, int b) {
		// 각 원소들의 루트를 찾음
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 두 원소가 같은 집합에 속해 있는 경우
		if(aRoot == bRoot) {
			return;
		}
		// 작은 번호의 원소가 루트가 되도록 합침
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
