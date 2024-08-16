import java.util.*;
import java.io.*;

public class Main {
	
	// 해당 단원의 문제 정보를 담고 있는 내부 클래스
	static class Question {
		int time; // 해당 단원 예상 공부 시간
		int score; // 해당 단원 문제의 배점
		
		public Question(int time, int score) {
			this.time = time;
			this.score = score;
		}
	}
	
	static int N; // 시험 단원의 개수
	static int T; // 공부할 수 있는 총 시간
	static Question[] questionArr; // 각 단원의 문제들을 담고 있는 배열
	static int[] dp; // dp[i] = 해당 공부할 수 있는 시간에 준석이가 얻을 수 있는 최대 점수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		questionArr = new Question[N]; // [0] ~ [N-1]
		dp = new int[T+1]; // [0] ~ [T]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			
			// 각 단원의 문제정보들을 배열에 저장
			questionArr[i] = new Question(time, score);
		}
		
		// 모든 단원에 대해서 탐색
		for (int i=0; i<N; i++) {
			int time = questionArr[i].time; // 해당 단원의 예상 공부 시간
			int score = questionArr[i].score; // 해당 단원 문제의 배점
			
			// 최대 공부 시간부터 거꾸로 탐색
			for (int j=T; j>=1; j--) {
				// 해당 단원의 예상 공부 시간이 탐색한 공부 시간 이하인 경우
				// 즉, 공부해서 해당 단원의 문제를 맞출 수 있는 경우
				if (time <= j) {
					// 해당 공부 시간에 따른 얻을 수 있는 최대 점수 갱신
					dp[j] = Math.max(dp[j], dp[j - time] + score);
				}
			}
		}
		
		// 공부해서 얻을 수 있는 최대 점수 출력
		System.out.println(dp[T]);

	}

}