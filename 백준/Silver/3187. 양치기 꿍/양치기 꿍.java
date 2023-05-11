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
	
	static int R;	// 세로 길이 (y 좌표)
	static int C;	// 가로 길이 (x 좌표)
	static char[][] map;
	static boolean[][] visited;
	static int[] animalCount = new int[2];	// [0] -> 살아남게 되는 양의 수, [1] -> 살아남게 되는 늑대의 수
	// 4가지 방향 배열(상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열(좌, 우)
	
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
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 방문안한 좌표이면서 늑대(v)이거나 양(k)인 좌표 탐색하기
				if(!visited[i][j] && (map[i][j] == 'v' || map[i][j] == 'k')) {
					bfs(i, j);
				}
			}
		}
		System.out.println(animalCount[0] + " " + animalCount[1]);

	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		int sheepCount = 0;	// 울타리 안에서의 양의 수
		int wolfCount = 0;	// 울타리 안에서의 늑대의 수
		
		// 시작한 좌표가 늑대(v)인 경우 
		if(map[startX][startY] == 'v') {
			wolfCount++;
		}
		// 시작한 좌표가 양(k)인 경우
		if(map[startX][startY] == 'k') {
			sheepCount++;
		}
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색(상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (C-1, R-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
					continue;
				}
				// 탐색한 좌표가 이미 방문했거나 울타리인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == '#') {
					continue;
				}
				// 탐색한 좌표가 늑대인 경우
				if(map[nextX][nextY] == 'v') {
					wolfCount++;
				}
				// 탐색한 좌표가 양인 경우
				if(map[nextX][nextY] == 'k') {
					sheepCount++;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
		
		// 위의 탐색 과정 다 마친 뒤 울타리안에서의 양과 늑대의 수 비교하기
		// 울타리안에서 양의 수가 늑대의 수보다 많은 경우
		if(sheepCount > wolfCount) {
			wolfCount = 0;	// 늑대 다 잡아먹힘
		}
		// 울타리안에서 늑대 수가 양의 수보다 많거나 같은 경우
		else if(sheepCount <= wolfCount){
			sheepCount = 0;	// 양 다 잡아먹힘
		}
		animalCount[0] += sheepCount;
		animalCount[1] += wolfCount;
	}

}
