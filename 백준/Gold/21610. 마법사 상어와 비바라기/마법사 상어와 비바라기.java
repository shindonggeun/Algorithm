import java.util.*;
import java.io.*;

public class Main {
	
	static class Cloud {
		int x;
		int y;
		
		public Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	// 8가지 방향 배열 (방향없음, 좌, 좌 + 상, 상, 우 + 상, 우, 우 + 하, 하, 좌 + 하) -> 1번 인덱스부터 시작
	static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static List<Cloud> cloudList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloudList = new ArrayList<>();
		
		cloudList.add(new Cloud(N-1, 0));
		cloudList.add(new Cloud(N-1, 1));
		cloudList.add(new Cloud(N-2, 0));
		cloudList.add(new Cloud(N-2, 1));
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int move = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][N];
			
			moveCloud(dir, move);
			raining();
			waterCopyBug();
			generateCloud();
		}
		
		int result = getTotalWater();
		
		System.out.println(result);
	}
	
	// 구름을 움직이게 하는 메서드
	public static void moveCloud(int dir, int move) {
		List<Cloud> newCloudList = new ArrayList<>();
		
		for (Cloud cloud: cloudList) {
			int nextX = (cloud.x + dx[dir] * move) % N;
			int nextY = (cloud.y + dy[dir] * move) % N;
			
			if (nextX < 0) {
				nextX += N;
			}
			
			if (nextY < 0) {
				nextY += N;
			}
			
			newCloudList.add(new Cloud(nextX, nextY));
		}
		
		cloudList = newCloudList;
	}
	
	// 비를 내리게 하는 메서드
	public static void raining() {
		for (Cloud cloud: cloudList) {
			map[cloud.x][cloud.y]++;
			visited[cloud.x][cloud.y] = true;
		}
	}
	
	// 물복사 버그 마법을 실행하는 메서드
	public static void waterCopyBug() {
		for (Cloud cloud: cloudList) {
			int waterCount = 0;
			for (int i=2; i<=8; i+=2) {
				int nextX = cloud.x + dx[i];
				int nextY = cloud.y + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				if (map[nextX][nextY] > 0) {
					waterCount++;
				}
			}
			
			map[cloud.x][cloud.y] += waterCount;
		}
	}
	
	// 물의 양이 2 이상인 칸에 구름을 생기게 하는 메서드
	public static void generateCloud() {
		List<Cloud> newCloudList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] >= 2 && !visited[i][j]) {
					map[i][j] -= 2; // 구름이 생겼으므로 해당 칸의 물의 양 2만큼 줄여줌
					newCloudList.add(new Cloud(i, j));
				}
			}
		}
		
		cloudList = newCloudList;
	}
	
	public static int getTotalWater() {
		int total = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				total += map[i][j];
			}
		}
		
		return total;
	}

}
