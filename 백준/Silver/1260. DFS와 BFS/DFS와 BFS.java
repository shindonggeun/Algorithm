import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 정점의 개수
	static int M; // 간선의 개수
	static int V; // 탐색을 시작할 정점의 번호
	static boolean[] visited; // 각 정점마다 방문 여부를 체크할 배열
	static ArrayList<ArrayList<Integer>> graph; // 각 정점마다 연결된 간선들을 저장할 그래프 (인접 리스트 이용)
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1]; // [1] ~ [N]
		graph = new ArrayList<>(); // 그래프 초기화
		
		for (int i=0; i<=N; i++) {
			// 각 정점마다 리스트 생성
			graph.add(new ArrayList<>());
		}
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken()); // 시작 정점
			int toVertex = Integer.parseInt(st.nextToken()); // 도착 정점
			
			// 양방향 간선 연결
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		
		// 1번 정점에서부터 N번 정점까지 연결된 간선들 (정점 번호) 정렬 -> 오름차순 정렬
		for (int i=1; i<=N; i++) {
			Collections.sort(graph.get(i));
		}
		
		sb = new StringBuilder(); // StringBuilder 생성
		dfs(V); // 시작 정점에서부터 깊이 우선 탐색 실시
		sb.append("\n"); // 출력결과에 줄바꿈 추가
		
		visited = new boolean[N+1]; // 방문배열 다시 초기화
		bfs(V); // 시작 정점에서부터 너비 우선 탐색 실시
		
		System.out.println(sb);
	}
	
	// 깊이 우선 탐색 메서드
	public static void dfs(int vertex) {
		// 현재 해당 정점이 방문 처리 안된 경우
		if (!visited[vertex]) {
			visited[vertex] = true; // 해당 정점 방문 처리
			sb.append(vertex).append(" ");
			
			// 해당 정점과 연결된 간선 탐색
			for (int next: graph.get(vertex)) {
				dfs(next); // 다음 정점 깊이 우선 탐색 실시
			}
		}
	}
	
	// 너비 우선 탐색 메서드
	public static void bfs(int start) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start); // 시작 정점 큐에 저장
		visited[start] = true; // 시작 정점 방문 처리
		sb.append(start).append(" ");
		
		while (!queue.isEmpty()) {
			// 현재 큐에 저장된 정점 뽑아냄
			int now = queue.poll();
			
			// 해당 정점과 연결된 간선들 탐색
			for (int next: graph.get(now)) {
				// 탐색한 정점이 방문 처리 안된 경우
				if (!visited[next]) {
					visited[next] = true; // 탐색한 정점 방문 처리
					sb.append(next).append(" ");
					queue.add(next); // 큐에 탐색한 정점 저장
				}
			}
		}
	}

}