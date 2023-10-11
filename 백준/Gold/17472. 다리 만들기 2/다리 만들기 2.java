import java.util.*;
import java.io.*;

public class Main {
	
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
		int fromVertex;
		int toVertex;
		int weight;
		
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
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	static List<Edge> edgeList;
	
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
		
		int islandNum = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 해당 좌표가 육지(1)면서 동시에 방문하지 않은 좌표인 경우
				if(map[i][j] == 1 && !visited[i][j]) {
					labelIsland(i, j, islandNum);
					islandNum++;
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
	
	public static void labelIsland(int startX, int startY, int islandNum) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		map[startX][startY] = islandNum;
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
				map[nextX][nextY] = islandNum;
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
				// 즉, 다른 섬 찾은 경우임 (다리 잇기 가능)
				if(map[nowX][nowY] != 0 && map[nowX][nowY] != fromIsland) {
					// 현재 다리 길이가 2초과 인 경우
					// 다리 길이에 탐색한 섬까지 다리길이로 치기 때문에 2 이상이 아닌 2초과로 해줘야한다
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
		boolean[] islandVisited = new boolean[islandCount+1];
		islandVisited[1] = true;
		
		for(Edge edge: edgeList) {
			if(edge.fromVertex == 1) {
				pq.add(edge);
			}
		}
		
		int bridgeTotalLength = 0;
		int connectedIsland = 1;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int from = edge.fromVertex;
			int to = edge.toVertex;
			int weight = edge.weight;
			
			if(!islandVisited[to]) {
				islandVisited[to] = true;
				bridgeTotalLength += weight;
				connectedIsland++;
				
				for(Edge nextEdge: edgeList) {
					if(!islandVisited[nextEdge.toVertex] && nextEdge.fromVertex == to) {
						pq.add(nextEdge);
					}
				}
			}
		}
		
		if(connectedIsland == islandCount) {
			return bridgeTotalLength;
		}
		else {
			return -1;
		}
	}

}
