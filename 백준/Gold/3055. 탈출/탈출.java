import java.util.*;
import java.io.*;

public class Main {

	static class Position {
		int x;
		int y;
		int time;
		
		public Position(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	static int R;
	static int C;
	static char[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	static Queue<Position> waterQueue = new LinkedList<>();	// 물 웅덩이의 이동 좌표 정보를 저장하는 큐
	static Queue<Position> hedgehogQueue = new LinkedList<>();	// 고슴도치의 이동 좌표 정보를 저장하는 큐
	static boolean find;	// 비버의 굴 이동 가능한지 여부를 나타내는 변수
	static int minTime = Integer.MAX_VALUE;	// 비버의 굴로 이동하는데 걸리는 시간
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];	// [0][0] ~ [R-1][C-1]
		visited = new boolean[R][C];
		find = false;
		
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
				
				// 해당 좌표가 고슴도치('S')인 경우
				if(map[i][j] == 'S') {
					hedgehogQueue.add(new Position(i, j, 0));
				}
				// 해당 좌표가 물('*')인 경우
				else if(map[i][j] == '*') {
					waterQueue.add(new Position(i, j, 0));
				}
			}
		}
		
		bfs();
		// 비버의 굴로 이동 가능한 경우
		if(find) {
			System.out.println(minTime);
		}
		// 비버의 굴로 이동 불가능한 경우
		else {
			System.out.println("KAKTUS");
		}
		
	}
	
	public static void bfs() {
		// 고슴도치의 좌표 정보를 담은 큐가 빌때까지 반복
		while(!hedgehogQueue.isEmpty()) {
			// 먼저 물부터 퍼트리기
			int waterLen = waterQueue.size();
			for(int i=0; i<waterLen; i++) {
				Position waterNow = waterQueue.poll();
				int wNowX = waterNow.x;
				int wNowY = waterNow.y;
				
				// 4가지 방향 탐색 (하, 상, 좌, 우)
				for(int d=0; d<4; d++) {
					int wNextX = wNowX + dx[d];
					int wNextY = wNowY + dy[d];
					
					// 탐색한 좌표가 [0][0] ~ [R-1][C-1] 이외의 좌표인 경우
					if(wNextX < 0 || wNextY < 0 || wNextX >= R || wNextY >= C) {
						continue;	// 넘어감
					}
					
					char nextCell = map[wNextX][wNextY];
					// 탐색한 좌표가 비버의 굴('D')이거나 또는 돌로 되어있거나 또는 이미 방문한 좌표인 경우
					if(nextCell == 'D' || nextCell == 'X' || visited[wNextX][wNextY]) {
						continue;	// 넘어감
					}
					
					// 탐색한 좌표가 비어있는 칸('.')이면서 방문하지 않은 좌표인 경우
					if(nextCell == '.' && !visited[wNextX][wNextY]) {
						waterQueue.add(new Position(wNextX, wNextY, 0));
						visited[wNextX][wNextY] = true;
					}
				}
			}
			
			// 고슴도치 이동시키기
			int hedgehogLen = hedgehogQueue.size();
			for(int i=0; i<hedgehogLen; i++) {
				Position hedgehogNow = hedgehogQueue.poll();
				int hNowX = hedgehogNow.x;
				int hNowY = hedgehogNow.y;
				int hTime = hedgehogNow.time;
				
				// 4가지 방향 탐색 (하, 상, 좌, 우)
				for(int d=0; d<4; d++) {
					int hNextX = hNowX + dx[d];
					int hNextY = hNowY + dy[d];
					
					// 탐색한 좌표가 [0][0] ~ [R-1][C-1] 이외의 좌표인 경우
					if(hNextX < 0 || hNextY < 0 || hNextX >= R || hNextY >= C) {
						continue;	// 넘어감
					}
					
					char nextCell = map[hNextX][hNextY];
					// 탐색한 좌표가 물 웅덩이('*')이거나 또는 돌('X)로 되어 있거나 또는 이미 방문한 좌표인 경우
					if(nextCell == '*' || nextCell == 'X' || visited[hNextX][hNextY]) {
						continue;	// 넘어감
					}
					
					// 탐색한 좌표가 비어있는 칸('.')이면서 방문하지 않은 좌표인 경우
					if(nextCell == '.' && !visited[hNextX][hNextY]) {
						hedgehogQueue.add(new Position(hNextX, hNextY, hTime + 1));
						visited[hNextX][hNextY] = true;
					}
					
					// 탐색한 좌표가 비버의 굴('D')이면서 동시에 방문하지 않은 좌표인 경우
					if(map[hNextX][hNextY] == 'D' && !visited[hNextX][hNextY]) {
						find = true;	// 비버의 굴로 이동 가능
						minTime = hTime + 1;	
						return;	// 메서드 종료
					}
				}
			}
		}
	}

}
