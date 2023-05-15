import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
			
			for(int i=0; i<N; i++) {
				String input = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = input.charAt(j) - '0';
				}
			}
			
			int sum = 0;
			int start = N/2;
			int end = N/2;
			
			for(int i=0; i<N; i++) {
				for(int j=start; j<=end; j++) {
					sum += map[i][j];
				}
				
				// 행(행의 인덱스 번호)이 절반 전인 경우
				if(i < N/2) {
					// 위로 갈수록 열의 범위를 늘림
					start -= 1;
					end += 1;
				}
				// 행(행의 인덱스 번호)이 절반 이후인 경우
				else if(i >= N/2){
					// 아래로 갈수록 열의 범위를 좁힌다
					start += 1;	// 시작 인덱스 증가
					end -=1;	// 끝 인덱스 감소
				}
			}
			sb.append("#" + tc + " " + sum).append("\n");
		}
		System.out.print(sb);
	}

}
