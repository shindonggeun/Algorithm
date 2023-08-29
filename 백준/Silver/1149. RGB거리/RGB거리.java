import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] houseRGB = new int[N][3];	// 각각의 집들을  rgb로 칠할 때 드는 비용을 저장한 배열
		int[][] dp = new int[N][3];	// 집을 칠할 때 드는 비용의 최소값들을 저장할 배열 (각 집마다 규칙 지키면서 색칠한 것들 최소비용 저장할 배열)
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			houseRGB[i][0] = Integer.parseInt(st.nextToken());	// 해당 집을 빨강으로 칠할 때 드는 비용
			houseRGB[i][1] = Integer.parseInt(st.nextToken());	// 해당 집을 초록으로 칠할 때 드는 비용
			houseRGB[i][2] = Integer.parseInt(st.nextToken());	// 해당 집을 파랑으로 칠할 때 드는 비용
			// dp배열 초기화 작업
			dp[i][0] = houseRGB[i][0];	
			dp[i][1] = houseRGB[i][1];
			dp[i][2] = houseRGB[i][2];
		}
		
		// 다이나믹프로그래밍 상향식(bottom-up) 풀이
		for(int i=1; i<N; i++) {
			// 각 집마다 규칙 지키면서 색칠
			// i(2<=i<=N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않게끔
			dp[i][0] += Math.min(dp[i-1][1], dp[i-1][2]);	// 빨간색을 칠할 집의 최소 비용은 전까지 색칠한 최소 비용에서 추가로 전의 집에서 초록색과 파란색 둘중 작은 비용인 것을 더해줘야 한다 
			dp[i][1] += Math.min(dp[i-1][0], dp[i-1][2]);	// 초록색을 칠할 집의 최소 비용은 전까지 색칠한 최소 비용에서 추가로 전의 집에서 빨간색과 파란색 둘중 작은 비용인 것을 더해줘야 한다
			dp[i][2] += Math.min(dp[i-1][0], dp[i-1][1]);	// 파란색을 칠할 집의 최소 비용은 전까지 색칠한 최소 비용에서 추가로 전의 집에서 빨간색과 초록색 둘중 작은 비용인 것을 더해줘야 한다
		}
		
		// 모든 집을 칠할 때 최소 비용 뽑는 작업
		int minCostRGB = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])); 
		System.out.println(minCostRGB);
	}

}
