import java.util.*;
import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= testCase; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N]; // 주어진 수열
            int[] dp = new int[N];  // 각 위치에서의 최장 부분 수열 길이
            int maxLength = 1;     // 최장 증가 부분 수열 길이 초기값은 1

            // 주어진 수열을 배열에 저장
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                dp[i] = 1; // 초기값은 자기 자신만 포함한 부분 수열
                for (int j = 0; j < i; j++) {
                    // 현재 위치의 수가 이전 위치의 수보다 큰 경우
                    if (arr[j] < arr[i]) {
                    	// 최장 증가 부분 수열 길이 갱신해서 해당 위치에 해당하는 값을 배열에 저장
                        // dp 테이블에 저장된 최장 증가 부분 수열 길이를 바탕으로 가장 큰 값을 dp[i]의 값으로 갱신
                    	dp[i] = Math.max(dp[i], dp[j] + 1); 
                    }
                }

                // 현재 위치에서의 최장 증가 부분 수열 길이를 갱신하면서 최대값 계산
                maxLength = Math.max(maxLength, dp[i]);
            }

            sb.append("#").append(tc).append(" ").append(maxLength).append("\n");
        }
        System.out.print(sb);
    }
}