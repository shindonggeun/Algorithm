import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;
	static int[] visited;
	static final int END_POINT = 100_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if (N == K) {
			System.out.println(0);
			return;
		}
		
		visited = new int[END_POINT+1];
		
		bfs(N, K);
		
		System.out.println(visited[K] - 1);
	}
	
	public static void bfs(int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = 1;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			if (now == end) {
				break;
			}
			
			for (int i=0; i<3; i++) {
				int next = 0;
				
				if (i == 0) {
					next = now + 1;
				}
				else if (i == 1) {
					next = now - 1;
				}
				else {
					next = now * 2;
				}
				
				if (next < 0 || next > END_POINT) {
					continue;
				}
				
				if (visited[next] == 0) {
					queue.add(next);
					visited[next] = visited[now] + 1;
				}
			}
		}
	}

}