import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 담은 내부 클래스
	static class Position {
		int x;
		int y;
		int distance;
		
		public Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	// 프림 알고리즘에 사용할 내부 클래스
	static class Edge {
		int fromVertex;	// 다리를 연결한 것의 시작 섬
		int toVertex;	// 다리를 연결한 것의 도착 섬
		int weight;	// 다리의 길이 (가중치)
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 지도의 행 수
	static int M;	// 지도의 열 수
	static int[][] map;	
	static boolean[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	static List<Edge> edgeList;	// 섬 사이의 다리 정보를 저장하는 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int islandNum = 1;	// 섬 번호를 나타내는 변수
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 해당 좌표가 육지(1)면서 동시에 방문하지 않은 좌표인 경우
				if(map[i][j] == 1 && !visited[i][j]) {
					labelIsland(i, j, islandNum);	// 섬의 번호를 매기는 메서드 호출
					islandNum++;	// 섬 번호 증가
				}
			}
		}
		
		edgeList = new ArrayList<>();
		
		// 섬과 섬 사이의 다리를 이은 뒤 다리 정보를 탐색하여 edge 리스트에 추가하는 과정
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 현재 좌표가 바다(0)가 아닌 경우
				if(map[i][j] != 0) {
					findEdges(i, j, map[i][j]);
				}
			}
		}
		
		// 프림 알고리즘을 이용하여 최소 다리 길이 계산
		int minBridgeLength = prim(islandNum - 1);	// islandNum에서 1을 뺀 값은 섬의 개수이다
		
		System.out.println(minBridgeLength);
	}
	
	// 섬 번호를 매기는 ㅔㅁ서드
	public static void labelIsland(int startX, int startY, int islandNum) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		map[startX][startY] = islandNum;	// 시작 좌표 섬의 번호로 표시
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
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
				
				// 탐색한 좌표가 바다(0)이거나 또는 이미 방문한 좌표인 경우
				if(map[nextX][nextY] == 0 || visited[nextX][nextY]) {
					continue;	// 넘어감
				}
				
				queue.add(new Position(nextX, nextY, 0));
				visited[nextX][nextY] = true;
				map[nextX][nextY] = islandNum;	// 탐색한 좌표 섬의 번호로 표시
			}
		}
	}
	
	// 섬 사이의 다리 정보를 찾아 edge 리스트에 추가하는 메서드
	public static void findEdges(int startX, int startY, int fromIsland) {
		for(int i=0; i<4; i++) {
			int nowX = startX;
			int nowY = startY;
			int nowBridgeLength = 0;	// 현재 다리 길이
			
			while(true) {
				nowX += dx[i];
				nowY += dy[i];
				nowBridgeLength++;
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if(nowX < 0 || nowY < 0 || nowX >= N || nowY >= M) {
					break;	// 무한반복 빠져나옴
				}
				
				// 탐색한 좌표의 섬번호가 현재 섬번호가 같은 경우
				if(map[nowX][nowY] == fromIsland) {
					break;	// 무한반복 빠져나옴
				}
				
				// 탐색한 좌표가 바다(0)가 아니면서 동시에 탐색한 좌표의 섬번호가 현재 섬번호와 같지 않은 경우
				// 즉, 다른 섬 찾은 경우임 (다리 연결 가능)
				if(map[nowX][nowY] != 0 && map[nowX][nowY] != fromIsland) {
					// 현재 다리 길이가 2초과 인 경우
					// 다리 길이에 탐색한 섬까지의 거리가 포함되므로 2 이상이 아닌 2 보다 큰 경우만 고려해줘야 한다
					// 그래서 엣지 리스트에 넣을 때도 다리길이 - 1해서 넣어줘야함
					if(nowBridgeLength > 2) {
//						System.out.println("좌표 정보 확인하기: " + nowX + " " + nowY);
//						System.out.println("다리 길이 확인하기: " + (nowBridgeLength-1) + " 현재 섬번호: " + fromIsland);
						// edge 리스트에 현재 edge정보 추가해줌 (현재 섬 번호(from), 탐색한 섬 번호(to), 다리 길이)
						edgeList.add(new Edge(fromIsland, map[nowX][nowY], nowBridgeLength-1));	
					}
					break;	// 무한반복 빠져나옴
				}
			}
		}
	}
	
	// 프림 알고리즘을 이용한 최소 다리 길이 계산하는 메서드
	public static int prim(int islandCount) {
		// 프림 알고리즘을 사용하기 위한 우선순위 큐 (가중치 오름차순 정렬)
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		boolean[] islandVisited = new boolean[islandCount+1];	// 섬(노드)의 방문 여부를 나타내는 배열 (섬의 번호 1번부터 시작)
		islandVisited[1] = true;	// 1번 섬 방문 처리
		
		// 출발 섬(1번 섬)에서 나가는 다리 정보를 큐에 추가
		for(Edge edge: edgeList) {
			if(edge.fromVertex == 1) {
				pq.add(edge);
			}
		}
		
		int bridgeTotalLength = 0;	// 다리의 총 길이
		int connectedIsland = 1;	// 연결된 섬의 수
		
		// 프림 알고리즘 수행하는 과정
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();	// 엣지 정보 우선순위큐에서 뽑아내기 (가장 다리 길이가 짧은 것 뽑아냄)
			int to = edge.toVertex;	// 다리를 연결한 것의 도착 섬
			int weight = edge.weight;	// 다리의 길이 (가중치)
			
			// 아직 방문하지 않은 섬(도착섬)인 경우
			if(!islandVisited[to]) {
				islandVisited[to] = true;	// 해당 섬 방문 처리
				bridgeTotalLength += weight;	// 다리 총 길이 누적
				connectedIsland++;	// 연결된 섬의 수 증가
				
				// 현재 연결된 섬에서 나가는 다리 정보를 큐에 추가해주는 과정
				for(Edge nextEdge: edgeList) {
					// 현재 다리의 도착섬이 방문하지 않았으면서 동시에 현재 섬(to, 즉 현재 도착섬)이 다리의 출발섬인 경우
					if(!islandVisited[nextEdge.toVertex] && nextEdge.fromVertex == to) {
						pq.add(nextEdge);	// 해당 다리를 선택할 수 있게끔 우선순위 큐에 넣어줌
					}
				}
			}
		}
		// 연결된 섬의 수가 섬의 개수와 같아지는 경우
		if(connectedIsland == islandCount) {
			return bridgeTotalLength;	// 섬의 연결된 다리의 총 길이 반환해줌
		}
		// 연결된 섬의 수가 섬의 개수와 같지 않은 경우 (즉, 모든 섬을 연결할 수 없는 경우)
		else {
			return -1;	// -1 반환
		}
	}

}
