import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = 1;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) {
				break;
			}
			
			parents = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				parents[i] = i;
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				
				union(fromVertex, toVertex);
			}
			
			Set<Integer> set = new HashSet<>();
			
			for(int i=1; i<=N; i++) {
				int rootNode = find(i);
				if(rootNode > 0) {
					set.add(rootNode);
				}
			}
			
			int treeCount = set.size();
			sb.append("Case ").append(testCase).append(": ");
			
			if(treeCount == 0) {
				sb.append("No trees.");
			}
			else if(treeCount == 1) {
				sb.append("There is one tree.");
			}
			else {
				sb.append("A forest of ").append(treeCount).append(" trees.");
			}
			sb.append("\n");
			
			testCase++;
		}

		System.out.print(sb);
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		// aRoot의 크기와 bRoot의 크기가 같은 경우
		if(aRoot == bRoot) {
			parents[aRoot] = 0;	// 사이클을 발견하면 해당 루트를 0으로 설정
		}
		// 가지치기 부분 (최적화) -> 이 조건들을 해줘야 stack overflow 방지
		// aRoot의 크기가 bRoot의 크기보다 작은 경우
		else if(aRoot < bRoot){
			parents[bRoot] = aRoot;	// bRoot의 부모는 aRoot가 된다
		}
		// aRoot의 크기가 bRoot의 크기보다 큰 경우
		else {
			parents[aRoot] = bRoot;	// aRoot의 부모는 bRoot가 된다
		}
		
	}

}
