import java.util.*;
import java.io.*;

public class Main {
	
	static class Process {
		int id;
		int currentTime;
		int priority;
		
		public Process(int id, int currentTime, int priority) {
			this.id = id;
			this.currentTime = currentTime;
			this.priority = priority;
		}
	}
	
	static int T;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Process> pq = new PriorityQueue<>((a, b) -> {
			if (a.priority == b.priority) {
				return a.id - b.id;
			}
			return b.priority - a.priority;
		});
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int priority = Integer.parseInt(st.nextToken());
			
			pq.add(new Process(id, time, priority));
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<T; i++) {
			Process now = pq.poll();
			now.priority--;
			now.currentTime--;
			
			sb.append(now.id).append("\n");
			
			if (now.currentTime == 0) {
				continue;
			}
			
			pq.add(now);
		}
		
		System.out.print(sb);
	}

}