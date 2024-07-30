import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int jump;
		
		public Position(int x, int jump) {
			this.x = x;
			this.jump = jump;
		}
	}

	static int N;
	static int[] map;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int jumpCount = bfs();
		
		System.out.println(jumpCount);
	}
	
	public static int bfs() {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(0, 0));
		visited[0] = true;
		
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowJump = now.jump;
			
			
			if (nowX == N-1) {
				return nowJump;
			}
			
			for (int i=1; i<=map[nowX]; i++) {
				int nextX = nowX + i;
				
				if (nextX >= N) {
					continue;
				}
				
				if (visited[nextX]) {
					continue;
				}
				
				queue.add(new Position(nextX, nowJump + 1));
				visited[nextX] = true;
			}
			
		}
		
		return -1;
	}

}