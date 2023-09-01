import java.util.*;
import java.io.*;

public class Main {
	
	// 해당 좌표의 정보 및 탈출 시간을 담은 내부 클래스
	static class Position {
		int z;
		int x;
		int y;
		int time;
		
		public Position(int z, int x, int y, int time) {
			this.z = z;
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	static int L;	// 층수 (z 축)
	static int R;	// 행의 개수 
	static int C;	// 열의 개수
	static char[][][] map;
	static boolean[][][] visited;
	// 6가지 방향 배열 => 배열에서의 동, 서, 남, 북, 상, 하
	static int[] dx = {0, 0, 1, -1, 0, 0};	// x축과 z축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (남, 북)
	static int[] dy = {1, -1, 0, 0, 0, 0};	// y축과 z축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (동, 서)
	static int[] dz = {0, 0, 0, 0, -1, 1};	// x축과 y축이 고정되어 있을 때 z좌표가 움직이는 방향 배열 (상, 하)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			// 입력이 모두 0이 입력된 경우
			if(L == 0 && R == 0 && C == 0) {
				break;
			}
			
			map = new char[L][R][C];	// [0][0][0] ~ [L-1][R-1][C-1]
			visited = new boolean[L][R][C];
			
			// 출발할 좌표 설정
			int startZ = 0;
			int startX = 0;
			int startY = 0;
			
			// 탈출할 좌표 설정
			int endZ = 0;
			int endX = 0;
			int endY = 0;
			
			for(int i=0; i<L; i++) {
				for(int j=0; j<R; j++) {
					String input = br.readLine();
					for(int k=0; k<C; k++) {
						map[i][j][k] = input.charAt(k);
						// 맵에서 출발할 좌표('S')인 경우
						if(map[i][j][k] == 'S') {
							startZ = i;
							startX = j;
							startY = k;
						}
						// 맵에서 탈출할 좌표('E')인 경우
						else if(map[i][j][k] == 'E') {
							endZ = i;
							endX = j;
							endY = k;
						}
					}
				}
				br.readLine();
			}
			
			int escapeTime = bfs(startZ, startX, startY, endZ, endX, endY);
			// 탈출하지 못한 경우
			if(escapeTime == -1) {
				sb.append("Trapped!").append("\n");
			}
			// 탈출한 경우
			else {
				sb.append("Escaped in ").append(escapeTime).append(" ").append("minute(s).").append("\n");
			}
		}
		System.out.print(sb);
		
	}
	
	// 너비우선탐색 메서드
	public static int bfs(int startZ, int startX, int startY, int endZ, int endX, int endY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startZ, startX, startY, 0));	// 시작좌표의 정보 큐에 저장
		visited[startZ][startX][startY] = true;	// 시작좌표 방문처리
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowZ = now.z;
			int nowX = now.x;
			int nowY = now.y;
			int nowTime = now.time;
			
			// 해당 좌표가 탈출 지점에 도달한 경우
			if(nowZ == endZ && nowX == endX && nowY == endY) {
				return nowTime;	// 해당 탈출시간 반환 
			}
			
			// 6가지 방향 탐색 => 배열에서의 동, 서, 남, 북, 상, 하
			for(int i=0; i<6; i++) {
				int nextZ = nowZ + dz[i];
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0][0] ~ [L-1][R-1][C-1] 이외의 좌표인 경우
				if(nextZ < 0 || nextX < 0 || nextY < 0 || nextZ >= L || nextX >= R || nextY >= C) {
					continue;
				}
				
				// 탐색한 좌표가 방문한 좌표거나 또는 벽('#')인 경우
				if(visited[nextZ][nextX][nextY] || map[nextZ][nextX][nextY] == '#') {
					continue;
				}
				
				queue.add(new Position(nextZ, nextX, nextY, nowTime + 1));	// 탐색한 좌표 정보 큐에 저장
				visited[nextZ][nextX][nextY] = true;	// 탐색한 좌표 방문처리
			}
		}
		
		// 위의 너비우선탐색을 다 했는데도 탈출하지 못한 경우 -1 반환
		return -1;
	}

}
