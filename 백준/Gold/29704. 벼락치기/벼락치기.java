import java.util.*;
import java.io.*;

public class Main {
	
	// 과제의 정보를 담고 있는 내부 클래스
	static class Quiz {
		int day; // 과제를 해결하는데 소요되는 일수
		int cost; // 과제를 기한내에 해결하지 못한 경우 내야하는 벌금
		
		public Quiz(int day, int cost) {
			this.day = day;
			this.cost = cost;
		}
	}
	
	static int N; // 
	static int T;
	static Quiz[] quizArr; // 각 과제의 정보들을 저장한 배열
	static int[] dp; // dp[i] = 해당 일수의 최대 절감할 수 있는 벌금 (즉, 해결한 과제들의 최대 벌금 합)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		quizArr = new Quiz[N]; // [0] ~ [N-1]
		dp = new int[T+1]; // [0] ~ [T]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			// 해당 과제의 소요되는 일수 및 벌금 저장
			quizArr[i] = new Quiz(day, cost);
		}
		
		int totalCost = 0; // 모든 과제의 벌금 합을 저장하는 변수
		
		// 모든 과제에 대해서 반복
		for (int i=0; i<N; i++) {
			int day = quizArr[i].day; // 해당 과제의 소요 일수
			int cost = quizArr[i].cost; // 해당 과제의 벌금
			totalCost += cost; // 벌금 합에 해당 과제에 벌금을 누적
			
			// 남은 제출 기한을 거꾸로 탐색
			// 0/1 냅색 이용
			for (int j=T; j>=1; j--) {
				// 현재 과제를 해결할 수 있는 시간이 충분한 경우
				if (day <= j) {
					// 현재 과제를 해결했을 떄와 해결하지 못했을 때 중 더 큰 벌금 절감액을 선택 (더 큰 벌금)
					dp[j] = Math.max(dp[j], dp[j - day] + cost);
				}
			}
		}
		
		// 최종적으로 내야 하는 최소 벌금 = 총 벌금 합 - 절감 가능한 최대 벌금
		System.out.println(totalCost - dp[T]);

	}

}