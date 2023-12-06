import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 전체 집합의 원소 수
	static int M;	// 연산의 개수
	static int[] parents;	// 각 원소의 부모 원소를 저장하는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];	// [1] ~ [N]
		for(int i=0; i<=N; i++) {
			parents[i] = i;	// 초기 부모는 자기 자신으로 설정
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());	// 연산의 종류 입력 (0: 합집합, 1: 같은 집합에 속하는지 확인)
			int aNode = Integer.parseInt(st.nextToken());	
			int bNode = Integer.parseInt(st.nextToken());
			
			// 합집합 연산인 경우
			if(command == 0) {
				union(aNode, bNode);	// 두 원소를 합치는 유니온 연산 수행
			}
			// 같은 집합에 속하는지 확인하는 연산인 경우
			else {
				// 각 원소들의 루트를 찾음
				int aRoot = find(aNode);
				int bRoot = find(bNode);
				
				// 두 원소의 루트가 같으면 같은 집합
				if(aRoot == bRoot) {
					sb.append("YES").append("\n");
				}
				// 두 원소의 루트가 다르면 다른 집합
				else {
					sb.append("NO").append("\n");
				}
			}
		}
		
		System.out.print(sb);
	}
	
	// 유니온파인드의 find 연산 (재귀적으로 부모를 찾아감)
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
