import java.util.*;
import java.io.*;

public class Main {
	
	// 과목의 정보를 담고 있는 내부 클래스
	static class Subject {
		int importance; // 해당 과목의 중요도
		int time; // 해당 과목을 듣기 위해 필요한 공부 시간
		
		public Subject(int importance, int time) {
			this.importance = importance; 
			this.time = time;
		}
	}
	
	static int N; // 최대 공부 시간
	static int K; // 과목 수
	static Subject[] subjectArr; // 각 과목의 중요도와 공부 시간을 저장하는 배열
	static int[] dp; // dp[i] = 해당 시간의 최대 중요도

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		subjectArr = new Subject[K]; // [0] ~ [K-1]
		dp = new int[N+1]; // [0] ~ [N]
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int importance = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			// 해당 과목의 중요도와 필요한 공부 시간 저장
			subjectArr[i] = new Subject(importance, time);
		}
		
		// 모든 과목에 대해서 반복
		for (int i=0; i<K; i++) {
			int importance = subjectArr[i].importance; // 현재 과목의 중요도
			int time = subjectArr[i].time; // 현재 과목의 필요한 공부 시간
			
			// 현재 남은 시간을 거꾸로 순회 (최대 N시간부터 1시간까지 줄여가며 반복)
			for (int j=N; j>=1; j--) {
				// 현재 과목의 필요한 공부 시간이 탐색한 j시간 이하인 경우
				if (time <= j) {
					// 해당 탐색한 j시간으로 얻을 수 있는 최대 중요도 갱신
					dp[j] = Math.max(dp[j], dp[j - time] + importance);
				}
			}
		}
		
		// 최대 공부 시간 N에서 얻을 수 있는 최대 중요도 출력
		System.out.println(dp[N]);
	}

}