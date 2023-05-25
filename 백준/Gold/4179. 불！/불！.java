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
	
	static int R;	// 행의 개수(세로) (y 좌표)
	static int C;	// 열의 개수(가로) (x 좌표)
	static boolean[][] visited;
	static char[][] map;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 떄 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	static Queue<Position> jihoonQueue = new LinkedList<>();
	static Queue<Position> fireQueue = new LinkedList<>();
	static int escapeTime = Integer.MIN_VALUE;
	
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
				if(map[i][j] == 'J') {
					jihoonQueue.add(new Position(i, j));
					visited[i][j] = true;
				}
				else if(map[i][j] == 'F') {
					fireQueue.add(new Position(i, j));
					visited[i][j] = true;
				}
			}
		}
		
		bfs();
		
		// 지훈이가 미로에 탈출할 수 없는 경우
		if(escapeTime == Integer.MIN_VALUE) {
			System.out.println("IMPOSSIBLE");
		}
		else {
			System.out.println(escapeTime);
		}
		
	}
	
	public static void bfs() {
		int time = 0;
		
		while(!jihoonQueue.isEmpty()) {
			// 먼저 불부터 퍼뜨리기
			int fireCount = fireQueue.size();	// 불의 개수
			for(int i=0; i<fireCount; i++) {
				Position fireNow = fireQueue.poll();
				// 4가지 방향 탐색 (상, 하, 좌, 우)
				for(int j=0; j<4; j++) {
					int fireNextX = fireNow.x + dx[j];
					int fireNextY = fireNow.y + dy[j];
					
					// 탐색한 좌표가 (0, 0) ~ (C-1, R-1) 이외의 좌표인 경우
					if(fireNextX < 0 || fireNextY < 0 || fireNextX >= R || fireNextY >= C) {
						continue;
					}
					
					if(visited[fireNextX][fireNextY] || map[fireNextX][fireNextY] == '#') {
						continue;
					}
					
					map[fireNextX][fireNextY] = 'F';
					visited[fireNextX][fireNextY] = true;
					fireQueue.add(new Position(fireNextX, fireNextY));
					
				}
			}
			
			// 지훈이가 움직일 차례
			int jihoonCount = jihoonQueue.size();	// 지훈이의 개수
			for(int i=0; i<jihoonCount; i++) {
				Position jihoonNow = jihoonQueue.poll();
				// 4가지 방향 탐색 (상, 하, 좌, 우)
				for(int j = 0; j<4; j++) {
					int jihoonNextX = jihoonNow.x + dx[j];
					int jihoonNextY = jihoonNow.y + dy[j];
					
					// 탐색한 좌표가 (0, 0) ~ (C-1, R-1) 이외의 좌표인 경우 (가장자리이다)
					if(jihoonNextX < 0 || jihoonNextY < 0 || jihoonNextX >= R || jihoonNextY >= C) {
						escapeTime = time + 1;	// 탈출시간 저장한 뒤
						return;	// 해당 bfs() 메서드 종료
					}
					
					if(visited[jihoonNextX][jihoonNextY] || map[jihoonNextX][jihoonNextY] != '.') {
						continue;
					}
					
					visited[jihoonNextX][jihoonNextY] = true;
					jihoonQueue.add(new Position(jihoonNextX, jihoonNextY));
					//map[jihoonNextX][jihoonNextY] = 'J';
				}
			}
			time++;
		}
	}

}
