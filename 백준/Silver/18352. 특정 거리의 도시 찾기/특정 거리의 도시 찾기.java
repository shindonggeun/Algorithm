import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 도시의 개수 (정점의 개수)
	static int M;	// 도로의 개수 (간선의 개수)
	static int K;	// 거리 정보
	static int X;	// 출발 도시 번호
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static final int INF = -1;	// 최단거리 초기화 값
	static int[] dist;	// 각 정점들의 최단거리를 저장한 배열
	static List<Integer> resultList;	// 최단거리가 K인 도시들(정점)을 저장한 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];	// 각 정점들마다 최단거리 저장해주는 배열 초기화
		Arrays.fill(dist, INF);	// 최단거리 정보를 저장한 배열 INF(-1) 값으로 초기화
		resultList = new ArrayList<>();	// 최단거리가 K인 도시들(정점)을 저장한 리스트
		
		// 각 정점들 생성해주기
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 간선들 연결해주는 작업
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
		
			// 단방향 간선 열결하기
			graph.get(fromVertex).add(toVertex);
		}
		
		dist[X] = 0;	// 시작 정점의 최단거리 값 0으로 초기화
		bfs(X);	// 시작정점부터 너비우선탐색 시작
		StringBuilder sb = new StringBuilder();
		
		// 결과리스트의 사이즈가 0인 경우 (즉, X로부터 출발하여 도시의 최단거리가 K인 도시(정점)이 없는 경우) 
		if(resultList.size() == 0) {
			sb.append(-1).append("\n");
		}
		// 결과리스트의 사이즈가 0이 아닌 경우 (즉, X로부터 출발하여 도시의 최단거리가 K인 도시(정점)이 있는 경우)
		else {
			Collections.sort(resultList);	// 결과 리스트(도시들의 정점번호) 오름차순 정렬
			for(int vertex: resultList) {
				sb.append(vertex).append("\n");
			}
		}
		
		System.out.print(sb);
		
	}
	
	// 너비우선탐색 + 다익스트라 알고리즘 이용한 메서드
	public static void bfs(int vertex) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(vertex);
		
		while(!queue.isEmpty()) {
			int nowVertex = queue.poll();
			
			// 도시의 최단거리가 K를 넘어가는 경우 
			if(dist[nowVertex] > K) {
				break;	// 반복문 빠져나옴
			}
			
			// 도시의 최단거리가 K인 경우
			if(dist[nowVertex] == K) {
				resultList.add(nowVertex);	// 결과 리스트에 해당 정점 넣어줌
			}
			
			for(int nextVertex: graph.get(nowVertex)) {
				// 최단거리가 INF(-1)인 경우
				if(dist[nextVertex] == INF) {
					// 도로의 거리가 1이기 때문에 이전 최단거리에 +1한 값 저장해준다
					dist[nextVertex] = dist[nowVertex] + 1;
					queue.add(nextVertex);
				}
			}
		}
	}

}
