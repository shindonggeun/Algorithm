import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 도시의 수
	static int M;	// 여행 계획의 속한 도시의 수
	static int[] parents;	// 각 도시들의 부모 루트를 저장하는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];	// [1] ~ [N]
		for(int i=0; i<=N; i++) {
			parents[i] = i;	// 초기 부모는 자기 자신으로 설정
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int command = Integer.parseInt(st.nextToken());	// 연결 정보 입력
				// 연결 정보가 1인 경우 연결된 것 (유니온 연산)
				if(command == 1) {
					union(i, j);	// 두 도시를 합치는 유니온 연산 수행
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());	
		int start = Integer.parseInt(st.nextToken());	// 시작 도시 번호 입력
		int startRoot = find(start);	// 시작 도시 번호의 루트 찾기
		boolean possible = true;	// 여행 경로 목적 달성 여부
		
		for(int i=1; i<M; i++) {
			int path = Integer.parseInt(st.nextToken());	// 도시 경로 입력 받기
			
			// 시작 도시의 루트가 해당 도시 경로의 루트와 같지 않은 경우
			if(startRoot != find(path)) {
				possible = false;	// 여행 경로 목적 달성 실패
			}
		}
		
		// 여행 경로 목적이 달성 된 경우
		if(possible) {
			System.out.println("YES");
		}
		// 여행 경로 목적이 달성되지 못한 경우
		else {
			System.out.println("NO");
		}
	}
	
	// 유니온파인드의 find 연산 (재귀적으로 부모를 찾아감)
	public static int find(int a) {
		// 자기 자신의 부모인 경우, 즉 루트인 경우
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
