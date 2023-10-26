import java.util.*;
import java.io.*;

public class Main {

	static int V;
	static int E;
	static int[] parents;
	static int[] degree;	// 각 정점의 차수를 저장하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parents = new int[V+1];
		degree = new int[V+1];
		
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			degree[fromVertex]++;
			degree[toVertex]++;
			
			union(fromVertex, toVertex);
		}
		
		int root = find(1);	// 첫번째 정점의 루트 노드를 찾음
		boolean isConnected = true;
		
		// 2번 정점(노드)부터 탐색 시작
		for(int i=2; i<=V; i++) {
			// 그래프가 연결되어 있지 않은 경우
			if(root != find(i)) {
				isConnected = false;
				break;
			}
		}
		
		int oddDegreeVertexCount = 0;	// 차수가 홀수인 정점의 개수
		for(int i=1; i<=V; i++) {
			// 차수가 홀수인 정점이 짝수가 아닌 경우
			// 차수가 홀수인 정점이 0개인 경우 오일러 회로를 이용해 그래프가 모두 연결되어 있는지 확인 가능
			// 차수가 홀수인 정점이 2개인 경우 오일러 경로를 이용해 그래프가 모두 연결되어 있는지 확인 가능
			if(degree[i] % 2 != 0) {
				oddDegreeVertexCount++;
			}
		}
		
		if(isConnected && (oddDegreeVertexCount == 0 || oddDegreeVertexCount == 2)) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
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
		
		if(aRoot == bRoot) {
			return;
		}
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
