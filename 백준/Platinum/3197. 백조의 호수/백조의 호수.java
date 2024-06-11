import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int R;
	static int C;
	static char[][] map;
	static boolean[][] visited;
	static Position[] swanArr;
	static Queue<Position> waterQueue;
	static Queue<Position> swanQueue;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C]; // [0][0] ~ [R-1][C-1]
		visited = new boolean[R][C];
		swanArr = new Position[2];
		int swanIdx = 0;
		
		waterQueue = new LinkedList<>();
		swanQueue = new LinkedList<>();
		
		for (int i=0; i<R; i++) {
			String input = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
				
				if (map[i][j] == 'L') {
					waterQueue.add(new Position(i, j)); // 백조가 있는 좌표도 물이 있는 곳으로 간주
					swanArr[swanIdx] = new Position(i, j);
					swanIdx++;
				}
				else if (map[i][j] == '.') {
					waterQueue.add(new Position(i, j));
				}
			}
		}
		
		swanQueue.add(new Position(swanArr[0].x, swanArr[0].y));
		visited[swanArr[0].x][swanArr[0].y] = true;
		
		int meetDay = 0;
		while (!swanMeet()) {
			meltByWater();
			meetDay++;
		}
		
		System.out.println(meetDay);
		
	}
	
	public static boolean swanMeet() {
		Queue<Position> nextSwanQueue = new LinkedList<>();
		
		while (!swanQueue.isEmpty()) {
			Position now = swanQueue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
					continue;
				}
				
				if (visited[nextX][nextY]) {
					continue;
				}
				
				if (nextX == swanArr[1].x && nextY == swanArr[1].y) {
					return true;
				}
				
				if (map[nextX][nextY] == 'X') {
					nextSwanQueue.add(new Position(nextX, nextY));
				}
				else {
					swanQueue.add(new Position(nextX, nextY));
				}
				
				visited[nextX][nextY] = true;
			}
		}
		
		swanQueue = nextSwanQueue;
		return false;
	}
	
	public static void meltByWater() {
		int size = waterQueue.size();
		
		while (size-- > 0) {
			Position now = waterQueue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
					continue;
				}
				
				if (map[nextX][nextY] == 'X') {
					map[nextX][nextY] = '.';
					waterQueue.add(new Position(nextX, nextY));
				}
			}
		}
	}
	
	

}