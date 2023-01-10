import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int day = Integer.parseInt(st.nextToken());
		int[] t = new int[day];
		int[] p = new int[day];
		
		for(int i=0; i<day; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		
		
		int[] dp = new int[day+1];	// dp : day일에 얻을 수 있는 최대 수익
		for (int i=0; i<day; i++) {
			// 날짜가 범위를 넘어가지 않는 경우
			if (i + t[i] <= day) {
				dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
			}
			// 현재 경우의 수가 0일 수 있기 때문에 이전의 최대값을 넣어줌.
			// 해당 날짜에 일할 수 없다면, 이전까지 일한 최대 수당을 넣어주어야 한다.
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		//System.out.println(Arrays.toString(dp));
		System.out.println(dp[day]);
	}

}