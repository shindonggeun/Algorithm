import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 정점의 수
	static int M; // 간선의 수
	static int R; // 시작 정점 번호
	static boolean[] visited; // 각 정점들 방문 여부를 나타낸 배열
	static ArrayList<ArrayList<Integer>> graph; // 각 정점마다 연결된 간선 정보를 저장한 그래프 (인접 리스트)
	static int[] sequenceArr; // 각 정점마다 방문 순서를 저장한 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>(); // 그래프 초기화
		visited = new boolean[N+1]; // [1] ~ [N]
		sequenceArr = new int[N+1]; // [1] ~ [N]
		
		for (int i=0; i<=N; i++) {
			// 각 정점마다 연결된 인접 리스트 초기화
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
		
		for (int i=1; i<=N; i++) {
			// 각 정점마다 연결된 간선들 내림차순 정렬 (인접 정점 내림차순 정렬)
			Collections.sort(graph.get(i), Collections.reverseOrder());
		}
		
		bfs(); // 너비 우선 탐색 실시
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			// 각 정점마다 방문 순서 출력 할 수 있게끔 StringBuilder에 저장
			sb.append(sequenceArr[i]).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 너비 우선 탐색 메서드
	public static void bfs() {
		// 너비 우선 탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Integer> queue = new LinkedList<>();
		queue.add(R); // 시작 정점 큐에 저장
		visited[R] = true; // 시작 정점 방문 처리
		int order = 1; // 방문 순서 1로 초기화
		sequenceArr[R] = order; // 해당 시작 정점의 방문 순서 저장
		
		while (!queue.isEmpty()) {
			// 현재 큐에 저장된 정점 번호 뽑아냄
			int nowVertex = queue.poll();
			
			// 해당 정점과 연결된 간선들 탐색
			for (int next: graph.get(nowVertex)) {
				// 탐색한 정점이 방문 하지 않은 경우
				if (!visited[next]) {
					visited[next] = true; // 탐색한 정점 방문 처리
					queue.add(next); // 탐색한 정점 번호 큐에 저장
					order++; // 방문 순서 증가
					sequenceArr[next] = order; // 탐색한 정점의 방문 순서 저장
				}
			}
		}
	}

}