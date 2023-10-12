import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[][] map;
	static int[][] maxMap;
	static int M;
	static int C;
	static int[] honeySelected;
	static boolean[] isSelected;
	static int maxProfit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];	// [0][0] ~ [N-1][N-1]
			maxMap = new int[N][N];
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			honeySelected = new int[2];
			maxProfit = Integer.MIN_VALUE;
			isSelected = new boolean[N*N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<=N-M; j++) {
					powerSet(i, j, 0, 0, 0);
				}
			}
			
			combination(0, 0);
			
			sb.append("#").append(tc).append(" ").append(maxProfit).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void combination(int depth, int idx) {
		// 깊이(선택횟수)가 2가 된 경우 (종료조건)
		// 즉, 두 일꾼이 꿀 채취할 준비가 된 경우
		if(depth == 2) {
			int tempProfit = 0;
			for(int i=0; i<2; i++) {
				int row = honeySelected[i]/N;
				int col = honeySelected[i]%N;
				
				tempProfit += maxMap[row][col];
			}
			
//			System.out.println(Arrays.toString(honeySel));
			maxProfit = Math.max(maxProfit, tempProfit);
			return;	// 메서드 종료
		}
		
		for(int i=idx; i<(N*N) - M; i++) {
			if(depth == 0) {
				honeySelected[depth] = i;
			}
			else if(depth == 1) {
				honeySelected[depth] = i+M;
			}
			combination(depth+1, i);
		}
		
	}
	
	// 부분 집합 생성 및 최대 수익 계산하는 함수 (벌통 가로로 연속되게끔 선택하는 부분집합)
	public static void powerSet(int x, int y, int depth, int sum, int pow) {
		// 가지치기
		if(sum > C) {
			return;
		}
		
		// 해당 깊이(선택횟수)가 M이 된 경우 (종료조건)
		if(depth == M) {
			// 
			if(pow > maxMap[x][y-M]) {
				maxMap[x][y-M] = pow;
			}
			return;	// 메서드 종료
		}
		
		// 꿀을 선택하는 모든 가능한 경우에 대해 계산
		powerSet(x, y+1, depth+1, sum+map[x][y], pow+(map[x][y] * map[x][y]));
		powerSet(x, y+1, depth+1, sum, pow);
	}
	
	
	

}
