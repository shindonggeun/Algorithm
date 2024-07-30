import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] map;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N];
		visited = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			visited[i] = -1;
		}
		
		int jumpCount = bfs();
		
		System.out.println(jumpCount);
	}
	
	public static int bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		visited[0] = 0;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			if (now == N-1) {
				return visited[now];
			}
			
			for (int i=1; i<=map[now]; i++) {
				int next = now + i;
				
				if (next >= N) {
					break;
				}
				
				if (visited[next] == -1) {
					visited[next] = visited[now] + 1;
					queue.add(next);
				}
			}
		}
		
		return -1;
	}

}