import java.util.*;
import java.io.*;

public class Main {

	static class Hotel {
		int cost;
		int peopleCount;
		
		public Hotel(int cost, int peopleCount) {
			this.cost = cost;
			this.peopleCount = peopleCount;
		}
	}
	
	static int C;
	static int N;
	static Hotel[] hotelArr;
	static int[] dp;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		hotelArr = new Hotel[N];
		dp = new int[C+101];
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cost = Integer.parseInt(st.nextToken());
			int peopleCount = Integer.parseInt(st.nextToken());
			
			hotelArr[i] = new Hotel(cost, peopleCount);
		}
		
		for (int i=0; i<N; i++) {
			int cost = hotelArr[i].cost;
			int peopleCount = hotelArr[i].peopleCount;
			
			for (int j=peopleCount; j<=C+100; j++) {
				if (dp[j - peopleCount] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], dp[j - peopleCount] + cost);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		
		for (int i=C; i<=C+100; i++) {
			result = Math.min(result, dp[i]);
		}
		
		System.out.println(result);
	}

}