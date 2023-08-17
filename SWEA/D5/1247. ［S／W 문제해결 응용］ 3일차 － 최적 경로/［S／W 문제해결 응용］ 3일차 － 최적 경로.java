import java.util.*;
import java.io.*;

public class Solution {
	
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static Position companyInfo;
	static Position homeInfo;
	static Position[] customerInfo;
	static boolean[] visited;	// 방문배열 (순열 만들거기때문에)
	static int minDistance;	// 최단 경로 이동거리
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			minDistance = Integer.MAX_VALUE;	// 일단 최단 경로 이동거리 최대값으로 설정
			visited = new boolean[N];	// 방문배열 설정
			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int companyX = Integer.parseInt(st.nextToken());	// 회사의 x 좌표
			int companyY = Integer.parseInt(st.nextToken());	// 회사의 y 좌표
			companyInfo = new Position(companyX, companyY);	// 회사의 좌표값 저장
			int homeX = Integer.parseInt(st.nextToken());	// 집의 x 좌표
			int homeY = Integer.parseInt(st.nextToken());	// 집의 y 좌표
			homeInfo = new Position(homeX, homeY);	// 집의 좌표값 저장
			
			customerInfo = new Position[N];
			// N명의 고객들의 좌표들 저장하기
			for(int i=0; i<N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				customerInfo[i] = new Position(x, y);
			}
			
			// 회사에서 출발
			backTracking(0, companyInfo, 0);	// 백트래킹 메서드 호출
			sb.append("#").append(tc).append(" ").append(minDistance).append("\n");
		}
		System.out.print(sb);

	}
	
	// 백트래킹 (순열 메서드)
	public static void backTracking(int depth, Position now, int distance) {
		// 해당 깊이가 N인 경우 (즉, 순열 완성된 경우) (종료조건)
		if(depth == N) {
			distance += (Math.abs(now.x - homeInfo.x) + Math.abs(now.y - homeInfo.y));	// 마지막으로 집으로 돌아가는 경로(거리) 계산해주기
			minDistance = Math.min(minDistance, distance);	// 최단 경로의 이동거리 최소값 갱신
			return;	// 메서드 종료
		}
		
		// 재귀를 통해서 지금까지 구한 거리가 최단 경로의 이동거리보다 큰 경우
		// 더이상 탐색할 필요 없음 (가지치기)
		if(distance > minDistance) {
			return;	// 메서드 종료
		}
		
		
		for(int i=0; i<N; i++) {
			// 해당 고객의 집을 방문하지 않은 경우
			if(!visited[i]) {
				visited[i] = true;	// 해당 고객의 집 방문처리
				// 회사에서 출발해서 모든 고객의 집들 방문할 수 있도록 거리 계산
				int tempDistance = Math.abs(now.x - customerInfo[i].x) + Math.abs(now.y - customerInfo[i].y);	
				backTracking(depth+1, customerInfo[i], tempDistance + distance);	// 백트래킹 메서드 호출
				visited[i] = false;	// 해당 고객의 집 방문처리 해제
			}
		}
	}

}
