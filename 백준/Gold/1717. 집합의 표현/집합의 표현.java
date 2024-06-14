import java.util.*;
import java.io.*;

public class Main {

	static int N; // 집합의 개수 (원소 개수)
	static int M; // 연산의 개수
	static int[] parents; // 유니온-파인드 알고리즘을 사용하기 위한 각 원소의 부모 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1]; // [1] ~ [N+1]
		
		// 부모 배열 초기화 과정
		for (int i=1; i<=N; i++) {
			parents[i] = i; // 초기 부모는 자기 자신으로 설정
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken()); // 연산
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken());
			
			// 해당 연산이 합집합(0)인 경우
			if (command == 0) {
				// 두 원소 합집합으로 (합치기)
				union(a, b);
			}
			// 해당 연산이 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산(1)인 경우
			else {
				// 두 원소의 부모가 같은 경우
				if (find(a) == find(b)) {
					sb.append("YES").append("\n");
				}
				// 두 원소의 부모가 같지 않은 경우
				else {
					sb.append("NO").append("\n");
				}
			}
		}

		System.out.print(sb);

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