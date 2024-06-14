import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 도시의 개수
	static int M; // 여행 계획에 속한 도시들의 수
	static int[] parents; // 유니온-파인드 알고리즘을 사용하기 위한 각 도시들의 부모 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1]; // [1] ~ [N]
		
		for (int i=1; i<=N; i++) {
			parents[i] = i; // 각 도시들의 초기 부모는 자기 자신으로 설정
		}
		
		// 1번 도시부터 N번 도시까지 탐색
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				int connect = Integer.parseInt(st.nextToken()); // 연결 여부 입력
				// 해당 도시끼리 연결된 경우
				if (connect == 1) {
					// 연결하기 위해 유니온 메서드 호출
					union(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); // 여행 계획 시작 도시 입력
		
		boolean plan = true; // 여행 계획대로 갈 시 해당 여행 경로를 통해 목적 달성 가능 여부
		int root = find(start); // 시작 도시의 부모 도시 뽑아내기
		
		// 시작 도시 다음부터 여행 계획 속한 도시들까지 탐색
		for (int i=1; i<M; i++) {
			int city = Integer.parseInt(st.nextToken()); // 해당 도시 번호
			// 해당 도시의 부모 도시가 시작 도시의 부모 도시와 같지 않은 경우
			if (root != find(city)) {
				plan = false; // 해당 여행 경로 목적 달성 불가능
			}
		}
		
		// 여행 경로 목적 달성 여부에 따른 출력값 설정
		System.out.println(plan ? "YES" : "NO");

	}
	
	// 유니온-파인드에서 파인드 연산을 담당하는 메서드
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	// 유니온-파인드에서 유니온 연산을 담당하는 메서드
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot != bRoot) {
			parents[aRoot] = bRoot;
		}
	}

}