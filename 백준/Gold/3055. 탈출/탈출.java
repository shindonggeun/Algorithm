import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y;
		int minTime;
		
		public Position(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.minTime = time;
		}
	}

	static int R;	// 세로 길이 (y좌표)
	static int C;	// 가로 길이 (x좌표)
	static char[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	static Queue<Position> hedgehogQueue = new LinkedList<>();	// 고슴도치 저장하는 큐
	static Queue<Position> waterQueue = new LinkedList<>();	// 물 저장하는 큐
	static int time = 0;	// 비버의 굴로 이동할 수 있는 빠른 시간 
	static boolean find = false;	// 비버의 굴로 이동 가능한 여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];	// (0, 0) ~ (C-1, R-1)
		visited = new boolean[R][C];

				
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
				// 해당 좌표가 고슴도치(S)인 경우
				if(map[i][j] == 'S') {
					hedgehogQueue.add(new Position(i, j, 0));
				}
				// 해당 좌표가 물 웅덩이(*)인 경우
				else if(map[i][j] == '*') {
					waterQueue.add(new Position(i, j, 0));
				}
			}
		}
		
		bfs();	// 너비우선탐색 실시
		if(find) {
			System.out.println(time+1);
		}
		else {
			System.out.println("KAKTUS");
		}
		
		
	}
	
	public static void bfs() {
		while(!hedgehogQueue.isEmpty()) {
			// 물을 퍼트리는 과정
			int waterLen = waterQueue.size();
			for(int i=0; i<waterLen; i++) {
				Position waterNow = waterQueue.poll();
				int wNowX = waterNow.x;
				int wNowY = waterNow.y;
				
				// 4가지 방향 탐색(상, 하, 좌, 우)
				for(int j=0; j<4; j++) {
					int wNextX = wNowX + dx[j];
					int wNextY = wNowY + dy[j];
					
					// (0, 0) ~ (C-1, R-1) 이외의 좌표를 탐색한 경우
					if(wNextX < 0 || wNextY < 0 || wNextX >= R || wNextY >= C) {
						continue;
					}
					// 비버의 굴인 좌표(D)인 경우 또는 돌로 되어있는 좌표(X) 또는  이미 방문한 좌표인 경우
					if(map[wNextX][wNextY] == 'D' || map[wNextX][wNextY] == 'X' || visited[wNextX][wNextY]) {
						continue;
					}
					
					// 비어있는 칸(.)이면서 방문하지 않은 좌표인 경우 
					if(map[wNextX][wNextY] == '.' && !visited[wNextX][wNextY]) {
						waterQueue.add(new Position(wNextX, wNextY, 0));
						visited[wNextX][wNextY] = true;
					}
				}
			
			}
			
			// 고슴도치가 이동하는 과정
			int hedgehogLen = hedgehogQueue.size();
			for(int i=0; i<hedgehogLen; i++) {
				Position hedgehogNow = hedgehogQueue.poll();
				int hNowX = hedgehogNow.x;
				int hNowY = hedgehogNow.y;
				time = hedgehogNow.minTime;
				
				// 4가지 방향 탐색(상, 하, 좌, 우)
				for(int j=0; j<4; j++) {
					int hNextX = hNowX + dx[j];
					int hNextY = hNowY + dy[j];
					
					// (0, 0) ~ (C-1, R-1) 이외의 좌표를 탐색한 경우
					if(hNextX < 0 || hNextY < 0 || hNextX >= R || hNextY >= C) {
						continue;
					}
					// 물 웅덩이 좌표(*)인 경우 또는 돌로 되어있는 좌표(X) 또는  이미 방문한 좌표인 경우
					if(map[hNextX][hNextY] == '*' || map[hNextX][hNextY] == 'X' || visited[hNextX][hNextY]) {
						continue;
					}
					// 해당 좌표가 빈칸(.)이면서 방문하지 않은 좌표인 경우
					if(map[hNextX][hNextY] == '.' && !visited[hNextX][hNextY]) {
						visited[hNextX][hNextY] = true;	// 해당 좌표 방문처리
						hedgehogQueue.add(new Position(hNextX, hNextY, time+1));
					}
					// 비버의 굴인 좌표(D)이면서 이미 방문하지 않은 좌표인 경우
					else if(map[hNextX][hNextY] == 'D' && !visited[hNextX][hNextY]) {
						find = true;	// 비버의 굴 이동 가능
						return;	// 메서드 종료
					}
				}
			}
		}
	}

}
