import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int count;
		
		public Position(int x, int count) {
			this.x = x;
			this.count = count;
		}
	}

	static int N;
	static int M;
	static int[] map;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[100+1];
		visited = new boolean[100+1];
		
		for (int i=1; i<=100; i++) {
			map[i] = i;
		}
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[x] = y;
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			map[u] = v;
		}
		
		int result = bfs();
		System.out.println(result);
	}
	
	public static int bfs() {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(map[1], 0));
		visited[map[1]] = true;
		
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowCount = now.count;
			
			if (nowX == 100) {
				return nowCount;
			}
			
			// 주사위 1부터 6까지 던지기
			for (int i=1; i<=6; i++) {
				int nextX = nowX + i;
				
				if (nextX > 100 || visited[nextX]) {
					continue;
				}
				
				int moveX = map[nextX];
				queue.add(new Position(moveX, nowCount + 1));
				visited[moveX] = true;
			}
		}
		
		return -1;
	}

}