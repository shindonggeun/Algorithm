import java.util.*;
import java.io.*;

public class Main {

	// 간선 정보를 담고 있는 내부 클래스
	static class Edge {
		int toVertex; // 도착지 그루터기
		int weight; // 길이 (가중치)
		int state; // 늑대 상태 (0: 달리는 상태, 1: 걷는 상태)

		// 여우가 사용할 생성자
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}

		// 늑대가 사용할 생성자
		public Edge(int toVertex, int weight, int state) {
			this.toVertex = toVertex;
			this.weight = weight;
			this.state = state;
		}
	}

	static int N; // 그루터기(정점) 개수
	static int M; // 오솔길(간선) 개수
	static int[] distByFox; // 여우가 출발지에서 각 그루터기(정점)까지의 최단 거리를 저장한 배열
	// 늑대가 출발지에서 각 그루터기(정점)까지의 최단 거리를 저장한 배열
	static int[][] distByWolf; // 상태에 따라 다르기 때문에 2차원 배열 [0]: 달리는 상태 (속도 두배), [1]: 걷는 상태 (속도 절반)
	static final int INF = Integer.MAX_VALUE; // 다익스트라 알고리즘에서 사용할 무한대를 나타내는 상수
	static boolean[] visitedByFox; // 여우입장에서 각 그루터기(정점)들의 방문 여부를 나타내는 배열
	// 늑대입장에서 각 그루터기(정점)들의 방문 여부를 나타내는 배열
	static boolean[][] visitedByWolf; // 상태에 따라 방문 여부가 다르기 떄문에 2차원 배열
	static ArrayList<ArrayList<Edge>> graph; // 각 정점마다 간선이 연결된 걸 표현한 그래프 (인접 리스트)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		distByFox = new int[N + 1];
		distByWolf = new int[2][N + 1]; // [0]: 달리는 상태, [1]: 걷는 상태
		visitedByFox = new boolean[N + 1];
		visitedByWolf = new boolean[2][N + 1];
		graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			// 양방향 간선 연결
			// 늑내의 걷는 상태 (속도 / 2)일때 소수점 방지를 위해 가중치에 * 2를 해준다
			graph.get(fromVertex).add(new Edge(toVertex, weight * 2));
			graph.get(toVertex).add(new Edge(fromVertex, weight * 2));
		}

		// 여우, 늑대 다익스트라 알고리즘 실행
		dijkstraByFox(1);
		dijkstraByWolf(1);

		int count = 0; // 먼저 도착한 그루터기의 개수
		// 출발지(1)부터가 아닌 2번 정점(그루터기)부터 순회
		for (int i = 2; i <= N; i++) {
			// 여우의 최단 거리가 늑대의 최단 거리보다 작은 경우 (즉, 여우가 해당 그루터기(정점)에 먼저 도착한 경우)
			if (distByFox[i] < Math.min(distByWolf[0][i], distByWolf[1][i])) {
				count++; // 먼저 도착한 그루터기의 개수 증가
			}
		}

		System.out.println(count);
	}

	// 여우가 출발지에서부터 해당 정점들까지의 최소 거리를 계산해줄 다익스트라 메서드
	public static void dijkstraByFox(int vertex) {
		// 다익스트라 알고리즘을 사용하기 위해 우선운위 큐 선언 및 생성
		// 각 간선들 가중치를 기준으로 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0));

		Arrays.fill(distByFox, INF); // 여우가 출발지에서 각 그루터기(정점)까지의 최단 거리를 저장한 배열 초기화
		Arrays.fill(visitedByFox, false); // 여우입장에서 각 그루터기(정점)들의 방문 여부를 나타내는 배열 초기화

		distByFox[vertex] = 0; // 출발 정점 최소 거리 0으로 초기화

		while (!pq.isEmpty()) {
			// 우선순위 큐에서 해당 오솔길(간선) 정보 뽑아냄
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;

			// 해당 정점이 방문하지 않은 경우
			if (!visitedByFox[nowVertex]) {
				visitedByFox[nowVertex] = true; // 해당 정점 방문 표시

				// 해당 정점(그루터기)와 연결된 오솔길(간선들) 탐색
				for (Edge next : graph.get(nowVertex)) {
					int cost = nowWeight + next.weight; // 해당 정점과 연결된 정점까지로 가는데 드는 거리 계산

					// 해당 거리가 해당 정점의 최소 거리보다 작은 경우
					if (cost < distByFox[next.toVertex]) {
						distByFox[next.toVertex] = cost; // 해당 정점의 최소 거리 갱신
						// 우선순위 큐에 해당 정점 정보 저장
						pq.add(new Edge(next.toVertex, cost));
					}
				}
			}
		}
	}

	// 늑대가 출발지에서 도착지까지 최소 거리를 계산해줄 다익스트라 메서드
	public static void dijkstraByWolf(int vertex) {
		// 다익스트라 알고리즘을 사용하기 위해 우선순위 큐 선언 및 생성
		// 각 간선들 가중치를 기준으로 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0, 0));

		// 늑대의 각 상태에 (0: 달리는 상태, 1: 걷는 상태) 따른 최단거리 및 방문 배열 초기화
		for (int i = 0; i < 2; i++) {
			Arrays.fill(distByWolf[i], INF);
			Arrays.fill(visitedByWolf[i], false);
		}

		distByWolf[0][vertex] = 0; // 늑대가 달리는 상태의 해당 출발지 최단 거리 0으로 초기화

		while (!pq.isEmpty()) {
			// 우선순위 큐에서 해당 오솔길(간선) 정보 뽑아냄
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			int nowState = now.state;

			// 늑대의 현재 상태에 해당하는 정점이 방문하지 않은 경우
			if (!visitedByWolf[nowState][nowVertex]) {
				visitedByWolf[nowState][nowVertex] = true; // 방문 처리

				// 해당 정점(그루터기)와 연결된 오솔길(간선들) 탐색
				for (Edge next : graph.get(nowVertex)) {
					int nextState = -1; // 다음 상태 초기화
					int nextWeight = 0; // 다음 거리 (가중치) 초기화

					// 현재 달리는 상태인 경우
					if (nowState == 0) {
						nextWeight = next.weight / 2; // 여우의 두배의 속도로 달려가는 것이므로 현재 거리 / 2 해줌
						nextState = 1; // 상태 변경 (걷는 상태)
					}
					// 현재 걷는 상태인 경우
					else {
						nextWeight = next.weight * 2; // 여우의 절반의 속도로 가는 것이므로 현재 거리 * 2 해줌
						nextState = 0; // 상태 변경 (달리는 상태)
					}

					int cost = nowWeight + nextWeight; // 해당 정점과 연결된 정점까지로 가는데 드는 거리 계산

					// 해당 거리가 해당 정점의 다음 상태의 최소 거리보다 작은 경우
					// 즉, 더 짧은 경로 발견 시
					if (cost < distByWolf[nextState][next.toVertex]) {
						distByWolf[nextState][next.toVertex] = cost; // 최소 거리 갱신
						// 우선순위 큐에 해당 정점 정보 저장
						pq.add(new Edge(next.toVertex, cost, nextState));
					}
				}
			}

		}
	}

}