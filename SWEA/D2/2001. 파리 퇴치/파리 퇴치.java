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
			int M = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxKill = 0;
			
			// 완전탐색(브루트포스 알고리즘) 이용 -> N과 M의 범위가 작기 때문에
			for(int i=0; i<=N-M; i++) {
				for(int j=0; j<=N-M; j++) {
					int sum = 0;
					for(int k=0; k<M; k++) {
						for(int s=0; s<M; s++) {
							sum += map[i+k][j+s];
						}
					}
					
					maxKill = Math.max(maxKill, sum);
				}
			}
			
			sb.append("#" + tc + " " + maxKill).append("\n");
		}
		System.out.print(sb);

	}

}