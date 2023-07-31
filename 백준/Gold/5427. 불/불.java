import java.util.*;
import java.io.*;

public class Main {

	static class Position {
		int x, y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int W;	// 너비 (x 좌표)
	static int H;	// 높이 (y 좌표)
	static char[][] map;
	static boolean[][] visited;
	static Queue<Position> fireQueue;
	static Queue<Position> sanggeunQueue;
	static int escapeTime;
	// 방향 배열(상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];	// (0, 0) ~ (W-1, H-1)
			visited = new boolean[H][W];
			fireQueue = new LinkedList<>();
			sanggeunQueue = new LinkedList<>();
			
			for(int i=0; i<H; i++) {
				String input = br.readLine();
				for(int j=0; j<W; j++) {
					map[i][j] = input.charAt(j);
					// 해당 좌표가 불(*)인 경우
					if(map[i][j] == '*') {
						fireQueue.add(new Position(i, j));	// 불의 좌표를 담은 Position을 큐에 저장함
						visited[i][j] = true;				// 해당 좌표 방문처리
					}
					// 해당 좌표가 상근이(@)가 있는 좌표인 경우
					else if(map[i][j] == '@') {
						sanggeunQueue.add(new Position(i, j));	// 상근이의 좌표를 담은 Position을 큐에 저장함
						visited[i][j] = true;	// 해당 좌표 방문처리
					}
				}
			}
			escapeTime = Integer.MIN_VALUE;	// 탈출 시간
			bfs();	// 너비우선탐색 실시
			
			// 탈출하지 못하는 경우
			if(escapeTime == Integer.MIN_VALUE) {
				sb.append("IMPOSSIBLE").append("\n");
			}
			// 탈출 가능한 경우
			else {
				sb.append(escapeTime).append("\n");
			}
		}
		System.out.print(sb);
		
	}
	
	public static void bfs() {
		int time = 1;	// 걸리는 시간 1초로 초기화
		
		while(!sanggeunQueue.isEmpty()) {
			// 먼저 불부터 옮겨붙도록 하기
			int fireSize = fireQueue.size();
			
			for(int i=0; i<fireSize; i++) {
				Position fireNow = fireQueue.poll();
				int fireNowX = fireNow.x;
				int fireNowY = fireNow.y;
				
				// 4가지 방향 탐색 (상, 하, 좌, 우)
				for(int k=0; k<4; k++) {
					int fireNextX = fireNowX + dx[k];
					int fireNextY = fireNowY + dy[k];
					
					// (0, 0) ~ (W-1, H-1) 이외의 좌표인 경우 넘어감
					if(fireNextX < 0 || fireNextY < 0 || fireNextX >= H || fireNextY >= W) {
						continue;
					}
					
					// 해당 좌표가 방문했던 좌표거나 또는 벽(#)이거나 또는 상근이가 위치한 좌표(@)인 경우 넘어감
					if(visited[fireNextX][fireNextY] || map[fireNextX][fireNextY] == '#' || map[fireNextX][fireNextY] == '@') {
						continue;
					}
					
					fireQueue.add(new Position(fireNextX, fireNextY));
					visited[fireNextX][fireNextY] = true;
					map[fireNextX][fireNextY] = '*';
				}
			}
			
			// 그다음 상근이가 이동할 차례
			int sanggeunSize = sanggeunQueue.size();
			
			for(int i=0; i<sanggeunSize; i++) {
				Position sanggeunNow = sanggeunQueue.poll();
				int sanggeunNowX = sanggeunNow.x;
				int sanggeunNowY = sanggeunNow.y;
			
				// 상근이의 해당 좌표가 가장자리인 경우(탈출 가능한 좌표들)
				if(sanggeunNowX == 0 || sanggeunNowY == 0 || sanggeunNowX == H-1 || sanggeunNowY == W-1) {
					escapeTime = Math.max(escapeTime, time);	// 탈출시간 갱신
					return;	// 메서드 종료함
				}
				
				// 4가지 방향 탐색 (상, 하, 좌, 우)
				for(int k=0; k<4; k++) {
					int sanggeunNextX = sanggeunNowX + dx[k];
					int sanggeunNextY = sanggeunNowY + dy[k];
					
					// (0, 0) ~ (W-1, H-1) 이외의 좌표 탐색한 경우 넘어감
					if(sanggeunNextX < 0 || sanggeunNextY < 0 || sanggeunNextX >= H || sanggeunNextY >= W) {
						continue;
					}
					
					// 해당 좌표가 방문했던 좌표거나 또는 벽(#)이거나 또는 불(*)인 경우 넘어감
					if(visited[sanggeunNextX][sanggeunNextY] || map[sanggeunNextX][sanggeunNextY] == '#' || map[sanggeunNextX][sanggeunNextY] == '*') {
						continue;
					}
					
					sanggeunQueue.add(new Position(sanggeunNextX, sanggeunNextY));
					visited[sanggeunNextX][sanggeunNextY] = true;
				}
			}
			
			time++;	// 탈출하는데 걸리는 시간 증가
		}
	}

}
