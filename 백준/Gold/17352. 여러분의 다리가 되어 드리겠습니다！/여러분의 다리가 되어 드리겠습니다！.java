import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 섬의 개수
	static int[] parents;	// 각 원소의 부모 원소를 저장하는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];	// [1] ~ [N]
		for(int i=1; i<=N; i++) {
			parents[i] = i;	// 초기 부모는 자기 자신으로 설정
		}
		
		for(int i=0; i<N-2; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());	// 섬 시작지
			int toVertex = Integer.parseInt(st.nextToken());	// 섬 도착지
			
			union(fromVertex, toVertex);	// 해당 섬끼리 다리 연결해주기 (유니온 연산 수행)
		}
		
		// 각 섬의 루트 섬을 찾아서 중복 없이 Set에 저장
		Set<Integer> set = new HashSet<>();	
		for(int i=1; i<=N; i++) {
			int root = find(i);
			set.add(root);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int islandNum: set) {
			sb.append(islandNum).append(" ");
		}
		System.out.println(sb);
	}
	
	// 유니온파인드의 find 연산 (재귀적으로 부모를 찾아감)
	// 해당 섬의 루트 섬을 찾는 메서드
	public static int find(int a) {
		// 자기 자신이 부모인 경우, 즉 루트인 경우
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);	// 경로 압축을 위해 부모를 루트로 설정
	}
	
	// 유니온파인드의 union 연산 (두 원소의 집합을 합침)
	// 두 섬을 연결하는 메서드
	public static void union(int a, int b) {
		// 각 원소들의 루트를 찾음
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 두 원소가 같은 집합에 속해 있는 경우
		// 즉, // 이미 연결되어 있으면 아무 작업도 수행하지 않음
		if(aRoot == bRoot) {
			return;
		}
		// 작은 번호의 원소가 루트가 되도록 합침
		// 즉, 루트가 다르면 두 섬을 연결해줌
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
