import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();	
	static int[] indegree;	// 해당 학생 번호에 따른 진입차수
	static Queue<Integer> queue = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 위상정렬 알고리즘 이용하기
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		indegree = new int[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			// 단방향 그래프 (간선 연결해주기) (fromVertex -> toVertex)
			graph.get(fromVertex).add(toVertex);
			indegree[toVertex]++;	// 해당 학생 번호의 진입차수 증가시켜주기
		}
		
		bfs();	// 너비우선탐색 시작
		
		System.out.println(sb);
	}
	
	// 너비우선탐색 메서드
	public static void bfs() {
		for(int i=1; i<=N; i++) {
			// 진입차수가 0인 학생번호(정점, 즉 노드)를 큐에 집어넣음
			if(indegree[i] == 0) {
				queue.add(i);
				sb.append(i).append(" ");
			}
		}
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			int currentVertex = queue.poll();	// 큐에 넣은 학생번호 뽑기 (정점, 즉 노드 뽑기)
			
			// 해당 학생번호에 연결된 정점들 확인하기
			for(int vertex: graph.get(currentVertex)) {
				indegree[vertex]--;	// 해당 학생번호(정점, 즉 노드)의 진입 차수를 1 감소시킴
				
				// 진입차수가 0이 된 학생번호(정점, 즉 노드)를 큐에 집에 넣음
				if(indegree[vertex] == 0) {
					queue.add(vertex);
					sb.append(vertex).append(" ");
				}
			}
		}
	}

}
