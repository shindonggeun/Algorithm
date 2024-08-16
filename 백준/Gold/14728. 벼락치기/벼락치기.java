import java.util.*;
import java.io.*;

public class Main {
	
	static class Question {
		int time;
		int score;
		
		public Question(int time, int score) {
			this.time = time;
			this.score = score;
		}
	}
	
	static int N;
	static int T;
	static Question[] questionArr;
	static int[] dp; // dp[i] = 해당 공부할 수 있는 시간에 준석이가 얻을 수 있는 최대 점수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		questionArr = new Question[N];
		dp = new int[T+1];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			
			questionArr[i] = new Question(time, score);
		}
		
		for (int i=0; i<N; i++) {
			int time = questionArr[i].time;
			int score = questionArr[i].score;
			
			for (int j=T; j>=1; j--) {
				if (time <= j) {
					dp[j] = Math.max(dp[j], dp[j - time] + score);
				}
			}
		}
		
		System.out.println(dp[T]);

	}

}