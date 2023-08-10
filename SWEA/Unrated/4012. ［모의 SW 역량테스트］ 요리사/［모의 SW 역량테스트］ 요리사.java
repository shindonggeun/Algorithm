import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;				// 식재료 수
	static int[][] map;			// 각 시너지를 저장할 2차원 배열
	static boolean[] visited;	// 방문배열 (해당 식재료 선택 여부)
	static int tasteMinResult;	// 맛의 차이 최소값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N];	
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			tasteMinResult = Integer.MAX_VALUE;
			backTracking(0, 0);
			sb.append("#").append(tc).append(" ").append(tasteMinResult).append("\n");
		}
		System.out.print(sb);
	}
	
	// 백트래킹 메서드 (조합)
	public static void backTracking(int depth, int idx) {
		// 식재료 선택한 수(깊이)가 N/2가 되는 경우 (종료 조건)
		if(depth == N/2) {
			synergySum();	// 식재료 시너지 메서드 호출
			return;	// 메서드 종료
		}
		
		
		for(int i=idx; i<N; i++) {
			visited[i] = true;	// 해당 식재료 선택
			backTracking(depth+1, i+1);	// 자신의 이전 식재료를 중복선택하지 않도록 i+1 해줌
			visited[i] = false;	// 해당 식재료 선택 해제
		}
	}
	
	public static void synergySum() {
		int foodA = 0;	// 음식 A
		int foodB = 0;	// 음식 B
		int tasteTempResult = 0;	// 음식 A와 음식 B 맛의 차이 (절대값)
		
		// 완전탐색 이용
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				// 해당 식재료를 둘 다 선택한 경우 (음식 A에 각 시너지를 더해줌)
				if(visited[i] && visited[j]) {
					foodA += (map[i][j] + map[j][i]);
				}
				// 해당 식재료를 둘다 선택안한 경우 (음식 B에 각 시너지를 더해줌)
				else if(!visited[i] && !visited[j]) {
					foodB += (map[i][j] + map[j][i]); 
				}
			}
		}
		
		tasteTempResult = Math.abs(foodA - foodB);	// 맛의 차이를 계산함(절대값)
		tasteMinResult = Math.min(tasteMinResult, tasteTempResult);	// 맛의 차이 최소값 갱신해줌
	}
}