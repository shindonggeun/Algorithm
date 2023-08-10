import java.util.*;
import java.io.*;

public class Solution {
	
	static class Information {
		int score;	// 대상 재료의 맛에 대한 점수
		int calorie;	// 제한 칼로리
		
		public Information(int score, int calorie) {
			this.score = score;
			this.calorie = calorie;
		}
	}
	
	static int N;	// 재료의 수
	static int L;	// 제한 칼로리
	static Information[] arr;	// 선택하고자 하는 대상 재료의 맛과 칼로리 정보를 저장한 배열
	static boolean[] visited;	// 부분집합에 포함 or 비포함 여부를 저장한 방문배열
	static int maxScore;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 재료의 수 입력
			L = Integer.parseInt(st.nextToken());	// 제한 칼로리 입력
			
			arr = new Information[N];
			visited = new boolean[N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());	// 해당 재료의 맛에 대한 점수
				int calorie = Integer.parseInt(st.nextToken());	// 해당 재료의 칼로리
				
				arr[i] = new Information(score, calorie);	// 해당 재료의 맛에대한 점수와 칼로리를 배열에 저장
			}

			maxScore = Integer.MIN_VALUE;	
			backTracking(0, 0, 0);	// 해당 재료들의 부분집합 구하기 위해 백트래킹 메서드 호출
			sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
		}
		System.out.print(sb);

	}
	
	
	// 부분집합 메서드
	public static void backTracking(int sum, int cal, int idx) {
		// 해당 재료의 조합이 제한 칼로리를 초과하면 메서드 종료 (종료조건1)
		if(cal > L) {
			return;
		}
		// 해당 재료의 조합이 제한 칼로리보다 작거나 같은 경우
		else {
			maxScore = Math.max(maxScore, sum);	// 가장 맛있는 조합의 점수를 갱신해줌
		}
		
		// 모든 조합을 확인한 경우 메서드 종료 (종료조건2)
		if(idx == N) {
			return;	
		}
		
		visited[idx] = true;	// 해당 재료 사용 여부 true
		backTracking(sum+arr[idx].score , cal+arr[idx].calorie, idx+1);	// 해당 재료 사용해서 재귀돌려줌
		visited[idx] = false;	// 해당 재료 사용여부 다시 false
		backTracking(sum, cal, idx+1);	// 이 재료는 사용 안함
	}

}
