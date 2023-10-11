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

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int islandCount;	// 각 섬에 고유한 번호 부여해주는 변수
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];	// [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N];
		islandCount = 1;	// 각 섬에 고유한 번호 부여
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int islandNum = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					labelIsland(i, j, islandNum);
					islandNum++;
				}
			}
		}
		
		int minDistance = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] > 0) {
					visited = new boolean[N][N];
					int bridgeDistance = findBridge(i, j);
					
					if(bridgeDistance == -1) {
						continue;
					}
					
					if(minDistance > bridgeDistance) {
						minDistance = bridgeDistance;
					}
				}
			}
		}
		
		System.out.println(minDistance-1);
	}
	
	// 섬의 육지에 고유한 번호를 부여하는 메서드
	public static void labelIsland(int startX, int startY, int islandNumber) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		visited[startX][startY] = true;
		map[startX][startY] = islandNumber;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 바다(0)거나 또는 이미 방문한 좌표인 경우
				if(map[nextX][nextY] == 0 || visited[nextX][nextY]) {
					continue;	// 넘어감
				}
				
				queue.add(new Position(nextX, nextY, 0));
				visited[nextX][nextY] = true;
				map[nextX][nextY] = islandNumber;
			}
		}
	}
	
	public static int findBridge(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		int islandNum = map[startX][startY];
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			if(map[nowX][nowY] != 0 && map[nowX][nowY] != islandNum) {
				return nowDistance;
			}
			
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				if(visited[nextX][nextY] || map[nextX][nextY] == islandNum) {
					continue;
				}
				
				visited[nextX][nextY] = true;
				queue.add(new Position(nextX, nextY, nowDistance + 1));
			}
		}
		return -1;
	}
	
	
	
	
	// 섬의 테두리인지 여부를 판단해주는 메서드
	public static boolean isEdge(int startX, int startY) {
		for(int i=0; i<4; i++) {
			int nextX = startX + dx[i];
			int nextY = startY + dy[i];
			
			// 탐색한 좌표가 바다(0)와 인접한 육지(1)인 경우 
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || map[nextX][nextY] == 0) {
				return true;	// 섬의 테두리임을 나타내는 true 반환
			}
		}
		// 위에 다 탐색했는데 그 이외의 경우는 섬의 테두리가 아님
		return false;
	}

}
