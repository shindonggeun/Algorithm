import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 강아지 수
	static int M;	// 닫힌 구간의 수
	static int A;	// A 생성 마법시 강아지 생성하는 수
	static int B;	// B 생성 마법시 강아지 생성하는 수
	static int[] left;	// 왼쪽 닫힌 구간을 저장하는 배열
	static int[] right;	// 오른쪽 닫힌 구간을 저장하는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		left = new int[M];
		right = new int[M];
		
		// 닫힌 구간 입력하는 과정
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			left[i] = Integer.parseInt(st.nextToken());	// 시작점
			right[i] = Integer.parseInt(st.nextToken());	// 끝점
		}
		
		
		int[] dp = new int[N+1];	// [1] ~ [N] 까지의 최소 행동 횟수를 저장할 배열
		Arrays.fill(dp, 100000);	// 초기값 100000 값으로 설정 (문제 조건 100000까지)
		dp[0] = 0;	// 0개의 강아지를 가질 때 행동 횟수는 0으로 초기화
		
		for(int k=1; k<=N; k++) {
			boolean valid = true;	// 현재 강아지 수 k가 제약 사항 구간에 포함되는지 여부
			
			// 제약 사항 구간에 포함되는지 확인하는 과정
			for(int i=0; i<M; i++) {
				// 현재 강아지 수 k가 닫힌 구간에 있는 경우(포함되어 있는 경우)
				if(k >= left[i] && k <= right[i]) {
					valid = false;	// 제약 사항 구간에 포함되어 있음
		            break;	// 반복문 빠져나옴
				}
			}
			
			// 제약 사항 구간(즉, 닫힌 구간)에 포함되어 있는 경우
			if(valid) {
				// 현재 강아지 수 k에서 A와 B 마법을 사용하여 최소 행동 횟수 갱신하는 과정
				// 현재 강아지 수 k가 A보다 큰 경우
				if(k >= A) {
					// 현재 강아지 수 k에서의 최소 행동과 k-A개의 강아지를 가진 상태에서 A 마법을 사용(+1번의 행동 추가) 한 것들 중 최소 행동 횟수 갱신
					dp[k] = Math.min(dp[k], dp[k-A] + 1);
				}
				// 현재 강아지 수 k가 B보다 큰 경우
				if (k >= B) {
					// 현재 강아지 수 k에서의 최소 행동과 k-B개의 강아지를 가진 상태에서 B 마법을 사용(+1번의 행동 추가) 한 것들 중 최소 행동 횟수 갱신
					dp[k] = Math.min(dp[k], dp[k - B] + 1);
	            }
			}
			
		}
		// 결과가 초기값과 같은 경우
		if(dp[N] == 100000) {
			dp[N] = -1;	// 불가능한 경우이므로 -1으로 설정
		}
		
		System.out.println(dp[N]);
	}
	
	

}