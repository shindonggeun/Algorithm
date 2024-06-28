import java.util.*;
import java.io.*;

public class Main {

	static class House {
		int x;
		int y;
		
		public House(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static List<Integer> countList;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		countList = new ArrayList<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					int count = bfs(i, j);
					countList.add(count);
				}
			}
		}
		
		Collections.sort(countList);
		
		StringBuilder sb = new StringBuilder();
		sb.append(countList.size()).append("\n");
		
		for (int count: countList) {
			sb.append(count).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static int bfs(int startX, int startY) {
		Queue<House> queue = new LinkedList<>();
		queue.add(new House(startX, startY));
		visited[startX][startY] = true;
		int totalCount = 1;
		
		while (!queue.isEmpty()) {
			House now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new House(nextX, nextY));
				visited[nextX][nextY] = true;
				totalCount++;
			}
		}
		
		return totalCount;
	}

}