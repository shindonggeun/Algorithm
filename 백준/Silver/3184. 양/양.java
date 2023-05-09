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
	
	static int R;	// 행의 수(즉 y축) (세로 길이)
	static int C;	// 열의 수(즉 x축) (가로 길이)
	static char[][] map;
	static boolean[][] visited;
	static int[] animalCount = new int[2];	// [0] -> 살아남은 양의 수, [1] -> 살아남은 늑대의 수
	// 4가지 방향 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 움직이는 y좌표 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 움직이는 x좌표 방향 배열 (좌, 우)
	
	
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
				// 해당 좌표가 방문하지 않은 상태면서 늑대('v')가 있는 영역이거나 양('o')이 있는 영역인 경우
				if(!visited[i][j] && (map[i][j] == 'v' || map[i][j] == 'o')) {
					bfs(i, j);	// 너비우선탐색 실시
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
		
		// 해당 시작 좌표가 늑대인 경우
		if(map[startX][startY] == 'v') {
			wolfCount++;
		}
		// 해당 시작 좌표가 양인 경우
		else if(map[startX][startY] == 'o'){
			sheepCount++;
		}
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (C-1, R-1) 이외의 좌표인 경우 넘어감
				if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문했거나 울타리인 경우 
				if(visited[nextX][nextY] || map[nextX][nextY] == '#') {
					continue;
				}
				
				// 탐색한 좌표가 양인 경우 양의 수 증가
				if(map[nextX][nextY] == 'o') {
					sheepCount++;
				}
				// 탐색한 좌표가 늑대인 경우 늑대의 수 증가
				if(map[nextX][nextY] == 'v') {
					wolfCount++;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
		
		// 위의 과정을 다 탐색하고 마친경우(울타리로 둘러쌓인 영역 다 탐색한 경우임)
		// 양과 늑대의 수를 비교해서 배열에 저장해줌
		if(sheepCount > wolfCount) {
			wolfCount = 0;
		}
		else {
			sheepCount = 0;
		}
		// 살아남은 동물의 수들 각각 더해줌
		animalCount[0] += sheepCount;
		animalCount[1] += wolfCount;
	}

}
