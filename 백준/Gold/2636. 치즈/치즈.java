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

	static int N;	// 세로 길이 (y 좌표)
	static int M;	// 가로 길이 (x 좌표)
	static int[][] map;
	static boolean[][] visited;
	static List<Integer> cheeseCountList;	// 치즈조각의 개수를 저장해주는 리스트
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		cheeseCountList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean melt = false;	// 모든 치즈조각이 녹아 없어진 경우를 판단해주는 변수
		int cheeseFirstCount = getCheeseCount();	// 초기 치즈조각의 개수
		int time = 0;	// 치즈가 모두 녹기까지 걸리는 시간
		
		while(true) {
			// 모든 치즈조각이 녹아 없어진 경우(true)
			if(melt) {
				break;	// 무한반복 빠져나옴
			}
			
			visited = new boolean[N][M];	// 방문배열 초기화 및 생성
			bfs(0, 0);	// 너비우선탐색 실시 (시작좌표는 (0, 0))
			int cheeseCount = getCheeseCount();	// 현재 남아있는 치즈조각의 개수 반환
			time++;	// 치즈 녹이는 시간 증가해줌
			
			// 치즈조각의 개수가 0인 경우(즉, 모두 녹아 없어진 경우)
			if(cheeseCount == 0) {
				melt = true;	// 모든 치즈조각이 녹아 없어졌다고 설정
			}
			// 치즈조각의 개수가 0이 아닌 경우(아직 녹일 치즈가 남아있음)
			else {
				cheeseCountList.add(cheeseCount);	// 치즈조각의 개수를 리스트에 저장
			}
		}
		
		System.out.println(time);	// 치즈가 모두 녹기까지 걸리는 시간 출력
		
		// 치즈조각의 개수를 저장한 리스트의 사이즈가 0보다 큰 경우
		// 즉, 치즈조각들이 모두 녹는데 걸리는 시간이 2시간 이상 걸리 경우
		if(cheeseCountList.size() > 0) {
			// 치즈조각이 모두 녹기 한 시간 전에 남아있는 치즈조각 개수 출력
			System.out.println(cheeseCountList.get(cheeseCountList.size() - 1));	
		}
		// 치즈조각의 개수를 저장한 리스트의 사이즈가 0보다 작거나 같은 경우
		// 즉, 1시간만에 치즈가 다 녹는 경우거나 또는 치즈가 하나도 없는 경우
		else {
			System.out.println(cheeseFirstCount);	// 초기 치즈조각의 개수 출력
		}
		
	}
	
	// 치즈 조각의 개수를 반환해주는 메서드
	public static int getCheeseCount() {
		int cheeseCount = 0;	// 치즈조각의 개수
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 치즈조각(1)인 경우
				if(map[i][j] == 1) {
					cheeseCount++;	// 치즈조각 개수 증가
				}
			}
		}
		
		return cheeseCount;	
	}
	
	// 너비우선탐색 메서드
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();	// 너비우선탐색을 위해 큐 선언 및 생성
		queue.add(new Position(startX, startY));	// 큐에 해당 시작좌표 정보 집어넣음
		visited[startX][startY] = true;	// 해당 시작좌표 방문 처리
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문한 좌표인 경우
				if(visited[nextX][nextY]) {
					continue;
				}
				
				// 탐색한 좌표가 치즈조각(1) (즉, 치즈 모서리)인 경우
				if(map[nextX][nextY] == 1) {
					map[nextX][nextY] = 2;	// 치즈조각을 2로 바꾸고
					visited[nextX][nextY] = true;	// 해당 좌표를 방문 처리
				}
				
				// 탐색한 좌표가 치즈가 없는 칸(0)인 경우
				if(map[nextX][nextY] == 0) {
					queue.add(new Position(nextX, nextY));	// 해당 좌표 정보 큐에 저장
					visited[nextX][nextY] = true; // 해당 좌표를 방문 처리
				}
			}
		}
		
		// 위의 너비우선탐색이 끝나면
		// 치즈조각이 1에서 2로 변경된것들을 녹여줘야 한다 (즉, 0으로 바꿔서 녹여줘야함)
		removeCheese();
	}
	
	public static void removeCheese() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 치즈 모서리(2)인 경우 0으로 바꿔서 치즈조각 녹여줌
				if(map[i][j] == 2) {
					map[i][j] = 0;
				}
			}
		}
	}
	
	

}
