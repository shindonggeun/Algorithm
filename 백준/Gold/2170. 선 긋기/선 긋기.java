import java.util.*;
import java.io.*;

public class Main {
	
	static class Line {
		int x;
		int y;
		
		public Line(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Line> pq = new PriorityQueue<>((a, b) -> {
			if (a.x == b.x) {
				return a.y - b.y;
			}
			return a.x - b.x;
		});
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			pq.add(new Line(x, y));
		}
		
		Line first = pq.poll();
		
		int start = first.x;
		int end = first.y;
		int total = 0;
		
		while (!pq.isEmpty()) {
			Line now = pq.poll();
			
			if (now.x > end) {
				total += end - start;
				start = now.x;
				end = now.y;
				continue;
			}
			
			if (now.y > end) {
				end = now.y;
			}
		}
		
		total += end - start;
		
		System.out.println(total);
	}

}