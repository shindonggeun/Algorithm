import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표정보를 담은 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 맵의 크기
	static char[][] colorMap;	// 각 칸의 컬러비트를 담은 맵
	static boolean[][] visited;	// 각 좌표 방문여부 배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		colorMap = new char[N][N];	// [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				colorMap[i][j] = input.charAt(j);
			}
		}
		
		int normalAreaCount = 0;	// 적록색약이 아닌 정상인 사람이 봤을 때의 구역의 개수
		
		// 적록색약이 아닌 정상인 사람이 그리드를 봤을 때의 구역의 개수 찾는 과정
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 해당 좌표가 방문처리 안된 경우
				if(!visited[i][j]) {
					colorBfs(i, j, colorMap[i][j]);	// 해당 구역의 색상 찾는 너비우선탐색 메서드 호출
					normalAreaCount++;	// 정상인 사람이 봤을 때의 구역의 개수 증가
				}
			}
		}
		
		// 적록색약이 구별할 수 없게끔 초록 색상을 빨강색상으로 만드는 과정
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(colorMap[i][j] == 'G') {
					colorMap[i][j] = 'R';
				}
			}
		}
		
		visited = new boolean[N][N];	// 다시 방문배열 초기화
		int abnormalAreaCount = 0;	// 적록색약인 사람이 봤을 때의 구역의 개수
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 해당 좌표가 방문처리 안된 경우
				if(!visited[i][j]) {
					colorBfs(i, j, colorMap[i][j]);	// 해당 구역의 색상 찾는 너비우선탐색 메서드 호출
					abnormalAreaCount++;	// 적록색약인 사람이 봤을 때의 구역의 개수 증가
				}
			}
		}
		
		System.out.println(normalAreaCount + " " + abnormalAreaCount);
		
	}
	
	// 해당 구역의 색상 찾는 메서드 (너비우선탐색 알고리즘 이용)
	public static void colorBfs(int startX, int startY, char colorBit) {
		// 너비우선탐색을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));	// 해당 시작좌표의 정보 큐에 저장
		visited[startX][startY] = true;	// 해당 시작 좌표 방문처리
		
		// 너비우선탐색 알고리즘 이용
		while(!queue.isEmpty()) {
			// 현재 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (하 ,상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 방문처리 됐거나 또는 탐색한 좌표의 색상이 시작했던 컬러 색상 비트와 다른 경우
				if(visited[nextX][nextY] || colorMap[nextX][nextY] != colorBit) {
					continue;	// 넘어감
				}
				
				// 위의것들의 경우가 아닌 경우 탐색한 좌표 정보 큐에 저장
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문 처리
			}
		}
	}

}
