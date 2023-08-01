import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
			
			for(int i=0; i<N; i++) {
				String input = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = input.charAt(j) - '0';
				}
			}
			
			int start = N/2;	// 시작 인덱스
			int end = N/2;		// 끝 인덱스
			int sum = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=start; j<=end; j++) {
					sum += map[i][j];
				}
				
				// 행(행의 인덱스 번호)이 절반 미만인 경우
				if(i < N/2) {
					// 아래로 갈수록(즉 행의 번호가 증가할 수록) 열의 범위를 늘려줌
					start -= 1;
					end += 1;
				}
				// 행(행의 인덱스 번호)이 절반 이상인 경우
				else if(i >= N/2) {
					// 아래로 갈수록 (즉 행의 번호가 증가할 수록) 열의 범위를 줄여줌
					start += 1;
					end -= 1;
				}
			}
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		
		System.out.print(sb);
	}

}
