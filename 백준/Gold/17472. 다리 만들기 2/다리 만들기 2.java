import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 저장하는 내부 클래스
	static class Position {
		int x;
		int y;
		int distance;	
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	// 섬과 섬 사이를 연결할 때 이용할 내부 클래스 (프림 알고리즘에 이용)
	static class Edge {
		int fromVertex;	// 다리를 연결할때의 시작 섬 번호
		int toVertex;	// 다리를 연결할때의 도착 섬 번호
		int weight;	// 다리 길이 (가중치)
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static List<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		edgeList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		
		int islandNum = 1;	// 섬의 번호 (1번부터 시작)
		int islandCount = 0;	// 섬의 개수
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 탐색한 좌표가 방문하지 않았으면서 동시에 육지(1)인 경우
				if(!visited[i][j] && map[i][j] == 1) {
					islandLabeling(i, j, islandNum);	// 각 육지마다 섬의 번호 매기게끔 메서드 호출
					islandNum++;	// 섬의 번호 증가
					islandCount++;	// 섬의 개수 증가
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 현재 좌표가 바다(0)가 아닌 경우
				if(map[i][j] != 0) {
					// 섬과 섬 사이의 다리 정보를 찾아 리스트에 저장하는 메서드 호출
					findEdge(i, j, map[i][j]);	
				}
			}
		}
		
		// 프림 알고리즘을 이용한 메서드를 호출하여 모든 섬을 연결하는 다리 길이 최소값 저장
		int minBridgeLength = minBridgeCalculate(islandCount);	
		System.out.println(minBridgeLength);
	}
	
	// 섬 번호를 매기는 메서드 (너비우선탐색 알고리즘 이용)
	public static void islandLabeling(int startX, int startY, int islandNum) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));	// 시작 좌표 정보 큐에 저장
		map[startX][startY] = islandNum;	// 해당 섬의 시작 좌표 섬 번호 넣어주기 (라벨링)
		visited[startX][startY] = true;	// 시작 좌표 방문 처리
		
		// 너비우선탐색 알고리즘 이용
		while(!queue.isEmpty()) {
			// 현재 좌표 정보 뽑아내기
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 바다(0)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;	// 넘어감
				}
				
				queue.add(new Position(nextX, nextY));	// 탐색한 좌표 정보 큐에 저장
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문 처리
				map[nextX][nextY] = islandNum;	// 탐색한 좌표 해당 섬의 번호로 매겨줌 (라벨링)
			}
		}
	}
	
	public static void findEdge(int startX, int startY, int nowIsland) {
		// 4가지 방향 탐색 (하, 상, 좌, 우)
		for(int i=0; i<4; i++) {
			int nowX = startX;	// 시작 좌표 설정
			int nowY = startY;	// 시작 좌표 설정
			int nowBridgeLength = 0;	// 현재 다리 길이
			
			// 무한 반복 이용
			while(true) {
				// 한 방향으로 좌표 증가
				nowX += dx[i];	
				nowY += dy[i];
				nowBridgeLength++;	// 현재 다리 길이 증가
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if(nowX < 0 || nowY < 0 || nowX >= N || nowY >= M) {
					break;	// 무한반복 빠져나옴 (다음 방향 탐색하게끔)
				}
				
				// 탐색한 좌표의 섬번호가 현재 섬번호와 같은 경우
				if(map[nowX][nowY] == nowIsland) {
					break;	// 무한반복 빠져나옴 (다음 방향 탐색하게끔)
				}
				
				// 탐색한 좌표가 바다(0)가 아니면서 동시에 탐색한 좌표의 섬번호가 현재 섬번호와 같지 않은 경우
				// 즉, 다른 섬 찾은 경우임 (다리 연결 가능)
				if(map[nowX][nowY] != 0 && map[nowX][nowY] != nowIsland) {
					// 현재 다리 길이 2 초과인 경우
					// 다리 길이에 탐색한 섬까지의 거리가 포함되므로 2 이상이 아닌 2 보다 큰 경우만 고려해줘야 한다
					// 그래서 엣지 리스트에 넣을 때도 다리길이 - 1해서 넣어줘야함
					if(nowBridgeLength > 2) {
						edgeList.add(new Edge(nowIsland, map[nowX][nowY], nowBridgeLength-1));
					}
					
					break;	// 무한 반복 빠져나옴 (다리 연결했으므로 다음 방향 탐색하게끔)
				}
			}
		}
	}
	
	// 최소 다리 길이 계산하는 메서드 (프림 알고리즘 이용 -> 최소 스패닝 트리 (최소신장트리))
	public static int minBridgeCalculate(int islandCount) {
		// 프림 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
		// 가중치 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		boolean[] islandVisited = new boolean[islandCount+1];	// [1] ~ [섬 개수]
		islandVisited[1] = true;	// 1번 섬 방문처리
		
		// 출발 섬(1번 섬)에서 나가는 다리 정보를 큐에 추가 -> 최소신장트리는 어느 정점에 시작하던 상관없다
		for(Edge edge: edgeList) {
			if(edge.fromVertex == 1) {
				pq.add(edge);
			}
		}
		
		int bridgeTotalLength = 0;	// 다리의 총 길이
		int connectedIsland = 1;	// 연결된 섬의 수
		
		// 프림 알고리즘 수행하는 과정
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int to = edge.toVertex;
			int weight = edge.weight;
			
			// 아직 방문하지 않은 섬 (도착섬)인 경우
			if(!islandVisited[to]) {
				islandVisited[to] = true;	// 해당 섬 방문 처리
				bridgeTotalLength += weight;	// 다리 총 길이 누적
				connectedIsland++;	// 연결된 섬의 수 증가
				
				for(Edge next: edgeList) {
					// 현재 다리의 도착섬이 방문하지 않았으면서 동시에 현재 섬(to, 즉 현재 도착섬)이 다리의 출발섬인 경우
					if(!islandVisited[next.toVertex] && next.fromVertex == to) {
						pq.add(next);	// 해당 다리(다음 다리)를 선택할 수 있게끔 우선순위 큐에 넣어줌
					}
				}
			}
		}
		
		// 연결된 섬의 수가 섬의 개수와 같아지는 경우
		if(connectedIsland == islandCount) {
			return bridgeTotalLength;	// 섬의 연결된 다리의 총 길이 반환
		}
		// 연결된 섬의 수가 섬의 개수와 같지 않은 경우 (즉, 모든 섬을 연결할 수 없는 경우)
		else {
			return -1;	// -1 반환
		}
		
		
	}

}
