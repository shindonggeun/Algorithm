import java.util.*;
import java.io.*;

public class Main {
	
	static class Quiz {
		int day;
		int cost;
		
		public Quiz(int day, int cost) {
			this.day = day;
			this.cost = cost;
		}
	}
	
	static int N;
	static int T;
	static Quiz[] quizArr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		quizArr = new Quiz[N];
		dp = new int[T+1];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			quizArr[i] = new Quiz(day, cost);
		}
		
		int totalCost = 0;
		for (int i=0; i<N; i++) {
			int day = quizArr[i].day;
			int cost = quizArr[i].cost;
			totalCost += cost;
			
			for (int j=T; j>=1; j--) {
				if (day <= j) {
					dp[j] = Math.max(dp[j], dp[j - day] + cost);
				}
			}
		}
		
		System.out.println(totalCost - dp[T]);

	}

}