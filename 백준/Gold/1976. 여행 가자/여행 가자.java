import java.util.*;
import java.io.*;

public class Main {

	static int N;	// 도시 개수
	static int M;	// 여행 계획에 속한 도시들의 수
	static int[] parents;	// 각 도시들의 부모 루트를 저장하는 배열 (유니온 파인드 용도)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];	// [1] ~ [N]
		for (int i=1; i<=N; i++) {
			parents[i] = i;	// 초기 부모는 자기 자신으로 설정
		}
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				int isConnect = Integer.parseInt(st.nextToken());	// 연결 정보 입력
				// 연결되어 있는 경우(1)
				if (isConnect == 1) {
					union(i, j);	// 해당 도시들 합치기 위해 유니온 연산 수행
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());	// 시작 도시 번호
		int startRoot = find(start);	// 시작 도시 번호의 루트 찾기
		boolean possible = true;	// 여행경로 가능한지 여부
		
		for (int i=0; i<M-1; i++) {
			int path = Integer.parseInt(st.nextToken());	// 해당 도시 여행 경로 번호
			// 시작 도시 번호의 루트와 입력받은 해당 도시 여행 경로 번호의 루트가 다른 경우
			if (startRoot != find(path)) {
				possible = false;	// 여행 경로 가능하지 않음
			}
		}
		
		// 여행 경로 가능한 경우
		if (possible) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}

	}
	
	// 유니온 파인드에서 파인드 연산을 담당하는 메서드
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	// 유니온 파인드에서 유니온 연산을 담당하는 메서드
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) {
			return;
		}
		else if (aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}