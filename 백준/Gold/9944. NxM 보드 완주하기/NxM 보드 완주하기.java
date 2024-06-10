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
	
	static int N;
	static int M;
	static char[][] map;
	static boolean[][] visited;
	static int emptyCount;
	static int minTurnCount;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = 1;
		
		while (true) {
			String str = br.readLine();
			if (str == null) {
				break;
			}
			
			st = new StringTokenizer(str);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			emptyCount = 0;
			
			for (int i=0; i<N; i++) {
				String input = br.readLine();
				for (int j=0; j<M; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == '.') {
						emptyCount++;
					}
				}
			}
			
			minTurnCount = Integer.MAX_VALUE;
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (map[i][j] == '.') {
						visited = new boolean[N][M];
						visited[i][j] = true;
						moveBall(i, j, 1, 0);
						visited[i][j] = false;
					}
				}
			}
			
			sb.append("Case ").append(testCase);
			
			if (minTurnCount == Integer.MAX_VALUE) {
				sb.append(": -1");
			}
			else {
				sb.append(": ").append(minTurnCount);
			}
			sb.append("\n");
			
			testCase++;
		}
		
		System.out.print(sb);
	}
	
	public static void moveBall(int nowX, int nowY, int depth, int turnCount) {
		if (depth == emptyCount) {
			minTurnCount  = Math.min(minTurnCount, turnCount);
			return;
		}
		
		if (minTurnCount <= turnCount) {
			return;
		}
		
		for (int i=0; i<4; i++) {
			int nextX = nowX;
			int nextY = nowY;
			int moveCount = 0;
			List<Position> pathList = new ArrayList<>();
			
			while (true) {
				nextX += dx[i];
				nextY += dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					break;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] == '*') {
					break;
				}
				
				visited[nextX][nextY] = true;
				pathList.add(new Position(nextX, nextY));
				moveCount++;
			}
			
			if (moveCount > 0) {
				moveBall(nextX - dx[i], nextY - dy[i], depth + moveCount, turnCount + 1);
				
				for (Position path: pathList) {
					visited[path.x][path.y] = false;
				}
			}
		}
		
	}
	
	

}