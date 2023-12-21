import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 지도의 행 개수
	static int M;	// 지도의 열 개수
	static char[][] map;	// 지도
	static boolean[][] visited;	// 방문배열
	static boolean[][] isCycle;	// 사이클 여부 체크할 배열
	static int safeZone;	// 안전구역 개수
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];	// [0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		isCycle = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		safeZone = 0;	// 안전구역 개수 초기화
		// 지도의 모든 칸에 대해 탐색
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 방문하지 않은 좌표인 경우
				if(!visited[i][j]) {
					dfs(i, j);	// 해당 좌표 깊이우선탐색 실시
				}
			}
		}
		
		System.out.println(safeZone);

	}
	
	// 안전구역을 찾는 메서드 (깊이우선탐색 알고리즘 이용)
	public static void dfs(int nowX, int nowY) {
		visited[nowX][nowY] = true;	// 현재 해당 좌표 방문처리
		char dir = map[nowX][nowY];	// 현재 위치에서의 방향 추출
		int dIdx = 0;	// 방향 인덱스
		// 하
		if(dir == 'D') {
			dIdx = 0;
		}
		// 상
		else if(dir == 'U') {
			dIdx = 1;
		}
		// 좌
		else if(dir == 'L') {
			dIdx = 2;
		}
		// 우
		else {
			dIdx = 3;
		}
		
		// 현재 위치에서의 방향에 따른 다음 위치(탐색할 위치) 계산
		int nextX = nowX + dx[dIdx];
		int nextY = nowY + dy[dIdx];
		
		// 다음 위치(탐색할 위치)가 방문되지 않은 경우
		if(!visited[nextX][nextY]) {
			dfs(nextX, nextY);// 다음 위치의 깊이우선탐색 실시
		}
		
		// 다음 위치가 아직 사이클에 포함되지 않은 경우
		if(!isCycle[nextX][nextY]) {
			safeZone++;	// 안전 구역 개수 증가
		}
		
		// 현재 위치를 사이클에 포함
		// 추후에 같은 위치에 다시 도달했을 떄, 새로운 안전 구역으로 카운트 하지 않도록 하기 위해
		isCycle[nowX][nowY] = true;	
	}

}
