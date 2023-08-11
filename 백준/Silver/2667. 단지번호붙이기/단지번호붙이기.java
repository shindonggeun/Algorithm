import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;	// 지도의 크기 N 
	static char[][] map;
	static boolean[][] visited;
	static List<Integer> homeNumberCountList;	// 단지내 집의 수를 저장한 리스트
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];	// (0, 0) ~ (N-1, N-1)
		visited = new boolean[N][N];
		homeNumberCountList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		int areaCount = 0;	// 총 단지수
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 방문안한 좌표면서 동시에 집이 있는 좌표('1')인 경우
				if(!visited[i][j] && map[i][j] == '1') {
					int houseCount = bfs(i, j);	// 너비우선탐색 실시
					homeNumberCountList.add(houseCount);	// 단지내 집의 수를 저장
					areaCount++;	// 총 단지 수 증가
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Collections.sort(homeNumberCountList);	// 단지내 집의 수를 저장한 리스트 오름차순 정렬
		
		sb.append(areaCount).append("\n");	// 총 단지수 StringBuilder에 저장
		// 단지내 집의 수를 오름차순 순으로 출력할 수 있게끔 리스트 순회
		for(int homeNumberCount: homeNumberCountList) {
			sb.append(homeNumberCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();	// 너비우선탐색을 실시할 수 있도록 큐 선언 및 생성
		queue.add(new Position(startX, startY));	// 해당 시작좌표 큐에 집어넣음
		visited[startX][startY] = true;	// 해당 시작좌표 방문처리
		int homeCount = 1;	// 단지 내 집의 수
		
		// 큐가 빌 때 까지 반복
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 집이 없는 경우('0')
				if(visited[nextX][nextY] || map[nextX][nextY] == '0') {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
				homeCount++;	// 단지 내 집의 수 증가
			}
		}
		
		// 위의 너비우선탐색 다 실시했으면 단지 내 집의 수 반환해줌
		return homeCount;
	}

}
