import java.util.*;
import java.io.*;

public class Solution {

	static int N;	// 벌통들의 크기
	static int M;	// 선택할 수 있는 벌통의 개수
	static int C;	// 꿀을 채취할 수 있는 최대 양
	static int[][] map;	// 각 벌통에 있는 꿀의 양을 저장하는 배열
	static int[][] maxMap;	// 부분 집합에 대한 최대 수익을 저장하는 배열
	// 두 일꾼이 선택한 벌통을 저장하는 배열
	// ex) [0, 3]인 경우
	// 1번 일꾼이 선택한 벌통 -> 0, 1, 2
	// 2번 일꾼이 선택한 벌통 -> 3, 4, 5
	static int[] honeySelected;	
	static int maxProfit;	// 두명읭 일꾼이 꿀을 채취하여 얻을 수 있는 최대 수익량
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];	// [0][0] ~ [N-1][N-1]
			maxMap = new int[N][N];
			
			honeySelected = new int[2];	// 두 일꾼이 선택한 벌통을 저장할 배열 방 2개로 초기화 (2명의 일꾼이므로)
			maxProfit = Integer.MIN_VALUE;	// 최대 수익량 일단 최소값으로 초기화
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 모든 벌통에 대해 가능한 부분 집합을 생성하고 최대 수익 계산해서 maxMap에 저장하는 과정
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
	
	/**
	 * 
	 * @param x: x좌표
	 * @param y: y좌표
	 * @param depth: 해당 깊이 (선택 횟수)
	 * @param honeySum: 선택한 꿀의 양 (합)
	 * @param profit: 각 꿀통에서 얻은 수익 (선택한 꿀의 양의 제곱한 값을 더해주는 것)
	 */
	// 부분 집합 생성 및 최대 수익 계산하는 함수 (벌통 가로로 연속되게끔 선택하는 부분집합)
	public static void powerSet(int x, int y, int depth, int honeySum, int profit) {
		// 가지치기
		// 현재까지 선택한 꿀의 양(합)이 C를 초과하는 경우 (종료조건)
		if(honeySum > C) {
			return;	// 메서드 종료
		}
		
		// 해당 깊이(선택 횟수)가 M이 된 경우 (종료조건)
		if(depth == M) {
			// 현재 벌통 조합에서 얻을 수 있는 수익을 계산
			// 현재 수익이 해당 위치의 최대 수익보다 큰 경우
			if(profit > maxMap[x][y-M]) {
				maxMap[x][y-M] = profit;	// 최대 수익 갱신
			}
			return;	// 메서드 종료
		}
		
		// 꿀을 선택하는 모든 가능한 경우에 대해 계산
		// 1. 현재 벌통의 꿀을 선택한 경우
		powerSet(x, y+1, depth+1, honeySum+map[x][y], profit+(map[x][y] * map[x][y]));
		// 2. 현재 벌통의 꿀을 선택하지 않은 경우
		powerSet(x, y+1, depth+1, honeySum, profit);
	}
	
	// 두 일꾼이 선택한 벌통 조합을 구하고 최대 수익 계산하는 메서드
	public static void combination(int depth, int idx) {
		// 해당 깊이(선택 횟수)가 2인 경우 (종료 조건)
		// 즉, 두 일꾼이 꿀 채취할 준비가 된 경우
		if(depth == 2) {
			int tempProfit = 0;	// 꿀을 채취하여 얻을 수 있는 수익
			for(int i=0; i<2; i++) {
				// 해당 벌통의 위치 (2차원 배열에서 0번부터 시작해서 (N*N)-1까지)
				int row = honeySelected[i]/N;	// 해당 인덱스의 행 계산
				int col = honeySelected[i]%N;	// 해당 인덱스의 열 계산
				
				tempProfit += maxMap[row][col];	
			}	
			maxProfit = Math.max(maxProfit, tempProfit);
			return;	// 메서드 종료
		}
		
		// 각 일꾼의 벌통 선택 조합 생성
		for(int i=idx; i<(N*N)-M; i++) {
			// 해당 깊이(선택횟수)가 0인 경우 
			// 즉, 첫번째 일꾼이 벌통 선택하게끔
			if(depth == 0) {
				honeySelected[depth] = i;	// 첫번째 일꾼이 선택한 벌통의 시작 인덱스를 저장
			}
			// 해당 깊이(선택횟수)가 1인 경우
			// 즉, 두번째 일꾼이 벌통 선택하게끔
			else if(depth == 1) {
				honeySelected[depth] = i + M;	
			}
			combination(depth+1, i);	// 다음 벌통 선택하게끔 조합 메서드 재귀 호출
		}
		
	}

}
