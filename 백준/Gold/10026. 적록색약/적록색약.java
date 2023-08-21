import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표들의 정보를 저장할 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static char[][] map;
	static boolean[][] visited;	// 2차원 방문 배열
	// 4가지 방향 배열 (상, 하, 좌, 우) -> 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	static int normalAreaCount;	// 적록색약이 아닌 사람이 봤을 때 구역의 수
	static int abnormalAreaCount;	// 적록색약인 사람이 봤을 때 구역의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	
		
		map = new char[N][N];	// (0, 0) ~ (N-1, N-1) => [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N];
		normalAreaCount = 0;
		abnormalAreaCount = 0;
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		
		
		// 적록색약이 아닌 사람이 봤을 때 구역의 수 찾는 과정
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 방문하지 않은 좌표 인 경우
				if(!visited[i][j]) {
					bfs(i, j);	// 너비우선탐색 실시
					normalAreaCount++;	// 구역의 수 증가
				}
			}
		}
		
		// 적록색약인 사람이 RGB 영역 봤을 때
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 초록('G')인 구역을
				if(map[i][j] == 'G') {
					map[i][j] = 'R';	// 빨강('R') 구역으로 바꿔줌
				}
			}
		}
		
		visited = new boolean[N][N];	// 2차원 방문 배열 다시 초기화
		
		// 적록색약인 사람이 봤을 때 구역의 수 찾는 과정
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 방문하지 않은 좌표 인 경우
				if(!visited[i][j]) {
					bfs(i, j);	// 너비우선탐색 실시
					abnormalAreaCount++;	// 구역의 수 증가
				}
			}
		}
		
		System.out.println(normalAreaCount + " " + abnormalAreaCount);
	}
	
	// 너비우선탐색메서드
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));	// 해당 시작좌표 정보 큐에 저장
		visited[startX][startY] = true;	// 해당 시작좌표 방문처리
		char tempColor = map[startX][startY];	// 해당 시작좌표의 색상
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// (0, 0) ~ (N-1, N-1) => [0][0] ~ [N-1][N-1] 이외의 좌표를 탐색한 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				// 이미 방문한 좌표거나 또는 해당 시작좌표의 컬러와 탐색한 좌표의 컬러값이 다른 경우
				if(visited[nextX][nextY] || map[nextX][nextY] != tempColor) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));	// 탐색한 좌표정보 큐에 저장
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문처리
			}
		}
	}

}
