import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 정점의 개수
	static int M;	// 간선의 개수
	static int V;	// 탐색을 시작할 정점의 시작 번호
	static boolean[] visited;	// 정점 방문여부를 나타내는 배열
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();	// 간선간 연결상태를 나타내는 graph
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 정점의 개수 입력받기
		M = Integer.parseInt(st.nextToken());	// 간선의 개수 입력받기
		V = Integer.parseInt(st.nextToken());	// 시작정점 번호 입력받기

		visited = new boolean[N+1];	// 방문배열 선언 (1번정점부터 N번 정점까지 이용할거므로 N+1)
		sb = new StringBuilder();	
		
		// 그래프에 정점(노드) 생성해주기
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());	
			int toVertex = Integer.parseInt(st.nextToken());
			
			// 간선은 양방향이므로 정점마다 서로 연결해주기
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		
		// 1번정점부터 N번 정점까지 
		for(int i=1; i<=N; i++) {
			Collections.sort(graph.get(i));	// 각 정점에 연결된 간선을 오름차순 정렬
		}
		
		dfs(V);	// 시작정점부터 깊이우선탐색 실시
		sb.append("\n");
		visited = new boolean[N+1];	// 다시 방문배열 초기화
		bfs(V);	// 시작정점부터 너비우선탐색 실시
		System.out.println(sb);
	}
	
	// 깊이우선탐색 메서드
	public static void dfs(int vertex) {
		visited[vertex] = true;
		sb.append(vertex).append(" ");	// 현재 방문한 정점 StringBuilder에 저장
		
		// 현재 방문한 정점(노드)에 연결된 리스트(정점들) 하나씩 뽑기
		for(int i: graph.get(vertex)) {
			// 방문하지 않은 정점인 경우
			if(!visited[i]) {
				dfs(i);	// 깊이우선탐색 다시 실시
			}
		}
	}
	
	// 너비우선탐색 메서드
	public static void bfs(int vertex) {
		visited[vertex] = true;
		Queue<Integer> queue = new LinkedList<>();	// 너비우선탐색시 필요한 큐 선언
		queue.add(vertex);	// 큐에 정점을 넣어줌
		
		// 큐가 비어질때까지 반복
		while(!queue.isEmpty()) {
			int node = queue.poll();
			sb.append(node).append(" ");
			// 현재 방문한 정점(노드)에 연결된 리스트(정점들) 하나씩 뽑기
			for(int i: graph.get(node)) {
				// 방문하지 않은 정점인 경우
				if(!visited[i]) {
					queue.add(i);	// 큐에 해당 정점 집어넣음
					visited[i] = true;	// 해당 정점 방문처리
				}
			}
		}
	}

}
