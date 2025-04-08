import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 정점 개수
	static int M; // 간선 개수
	static int K; // 가중치가 1인 간선의 번호
	static int[] parents; // 유니온 파인드 알고리즘을 사용하기 위한 각 정점의 부모정점을 저장하는 배열
	static int[] sizes; // 각 집합의 정점 개수
	// k번째 간선의 두 정점 (가중치 1인 간선)
	static int uK;
	static int vK;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1]; // [1] ~ [N]
		sizes = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			parents[i] = i; // 초기 모든 정점의 부모 정점은 자기 자신
			sizes[i] = 1; // 각 정점이 속한 집합의 초기 크기는 1
		}
		
		for (int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			// k번째 간선이 입력된 경우
			if (i == K) {
				// k번째 간선은 따로 저장
				uK = fromVertex;
				vK = toVertex;
				continue; // 다음 간선 입력 받게끔 넘어감
			}
			
			// 나머지 간선들은 모두 가중치 0 -> 바로 유니온 연산을 이용해 연결
			union(fromVertex, toVertex);
		}
		
		// 가중치 1인 간선이 이미 같은 그룹에 속해 있는 경우 -> 사이클이 존재 -> 모든 쌍의 거리는 0
		if (find(uK) == find(vK)) {
			System.out.println(0);
		}
		// 가중치 1인 간선이 두 다른 트리를 연결하는 경우 (즉, 사이클 존재 X)
		else {
			// 해당 간선을 통해 거리 1을 가지는 정점 쌍은 A 트리 크기 * B 트리 크기
			long a = sizes[find(uK)];
			long b = sizes[find(vK)];
			
			System.out.println(a * b);
		}
		
	}
	
	// 유니온 파인드에서 파인드를 담당하는 메서드 (경로 압축)
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	// 유니온 파인드에서 유니온을 담당하는 메서드 (사이즈 기준으로 병합)
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 이미 같은 집합인 경우
		if (aRoot == bRoot) {
			return; // 메서드 종료
		}
		
		// 더 작은 집합을 큰 집합에 합침 (사이즈 기준으로 유니온)
		if (sizes[aRoot] < sizes[bRoot]) {
			parents[aRoot] = bRoot;
			sizes[bRoot] += sizes[aRoot];
		}
		else {
			parents[bRoot] = aRoot;
			sizes[aRoot] += sizes[bRoot];
		}
	}
	

}