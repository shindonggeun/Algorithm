import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static int[] parentNode;	// 해당 노드의 부모노드 저장해주는 배열 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		parentNode = new int[N+1];
		
		// 정점 생성하기
		for(int i=0; i<=N; i++) {
			tree.add(new ArrayList<>());
		}
		
		// 각 정점에 맞는 노드 연결해주기
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			// 양방향간선 연결
			tree.get(fromVertex).add(toVertex);
			tree.get(toVertex).add(fromVertex);
		}
		
		
		dfs(1);	// 깊이우선탐색 실시
		
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<=N; i++) {
			sb.append(parentNode[i]).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int vertex) {
		visited[vertex] = true;	// 해당 정점 방문 체크
		
		// 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들 하나씩 뽑아내기
		for(int i: tree.get(vertex)) {
			// 방문안한 노드인 경우
			if(!visited[i]) {
				parentNode[i] = vertex;	// 부모노드 저장해주기
				dfs(i);	// 그 노드에 해당하는 리스트들 탐색할 수 있도록 재귀 호출
			}
		}
	}

}
