import java.util.*;
import java.io.*;

public class Main {
	
	static class Subject {
		int importance;
		int time;
		
		public Subject(int importance, int time) {
			this.importance = importance;
			this.time = time;
		}
	}
	
	static int N;
	static int K;
	static Subject[] subjectArr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		subjectArr = new Subject[K];
		dp = new int[N+1];
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int importance = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			subjectArr[i] = new Subject(importance, time);
		}
		
		for (int i=0; i<K; i++) {
			int importance = subjectArr[i].importance;
			int time = subjectArr[i].time;
			
			for (int j=N; j>=1; j--) {
				if (time <= j) {
					dp[j] = Math.max(dp[j], dp[j - time] + importance);
				}
			}
		}
		
		System.out.println(dp[N]);
	}

}