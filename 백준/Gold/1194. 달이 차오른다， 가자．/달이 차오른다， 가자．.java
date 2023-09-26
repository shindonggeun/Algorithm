import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		int key; // 열쇠 비트마스크
		int distance;	// 이동 거리
		
		public Position(int x, int y, int key, int distance) {
			this.x = x;
			this.y = y;
			this.key = key;
			this.distance = distance;
		}
	}

	
	static int N;
	static int M;
	static char[][] map;
	static boolean[][][] visited; // 열쇠 상태에 따른 방문 여부
	static int minDistance = Integer.MAX_VALUE;	// 미로 탈출하는데 드는 이동 횟수의 최소값 일단 최대값으로 설정
	
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M][64]; // 2^6 = 64가지 열쇠 조합
		
		int startX = 0;
		int startY = 0;
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '0') {
					startX = i;
					startY = j;
				}
			}
		}
		
		bfs(startX, startY);
		System.out.println(minDistance == Integer.MAX_VALUE ? -1 : minDistance);
	}
	
	// 너비우선탐색 메서드
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0, 0)); // 초기 열쇠 상태는 0
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowKey = now.key;
			int nowDistance = now.distance;
			
			// 해당 좌표가 출구('1')에 도달한 경우
			if(map[nowX][nowY] == '1') {
				minDistance = Math.min(minDistance, nowDistance);	// 미로 탈출하는데 드는 이동 횟수 최소값 갱신
				return; // 출구를 찾았으면 미로 탐색 종료
			}
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우 
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				char nextCell = map[nextX][nextY];	// 탐색한 좌표의 해당 칸

				// 탐색한 좌표가 벽('#')이거나 또는 이미 방문한 경우
				if(nextCell == '#' || visited[nextX][nextY][nowKey]) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 열쇠('a' ~ 'f')인 경우
				if (nextCell >= 'a' && nextCell <= 'f') {
					// 열쇠를 얻은 경우, 해당 열쇠 비트를 업데이트
					int newKey = nowKey | (1 << (nextCell - 'a'));
					visited[nextX][nextY][newKey] = true;	// 해당 열쇠에 해당하는 좌표 방문처리
					queue.add(new Position(nextX, nextY, newKey, nowDistance + 1));	// 큐에 해당 정보 저장
				} 
				// 탐색한 좌표가 문('A' ~ 'F')인 경우
				else if (nextCell >= 'A' && nextCell <= 'F') {
					// 문을 만난 경우, 해당 문과 대응하는 열쇠가 있는지 확인
					if ((nowKey & (1 << (nextCell - 'A'))) != 0) {
						visited[nextX][nextY][nowKey] = true;	// 해당 열쇠에 해당하는 좌표 방문처리
						queue.add(new Position(nextX, nextY, nowKey, nowDistance + 1));	// 큐에 해당 정보 저장
					}
				} 
				// 위의 경우들 이외의 경우는
				else {
					visited[nextX][nextY][nowKey] = true;	// 해당 열쇠에 해당하는 좌표 방문처리
					queue.add(new Position(nextX, nextY, nowKey, nowDistance + 1));	// 큐에 해당 정보 저장
				}
			}
		}
	}
}