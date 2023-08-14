import java.util.*;
import java.io.*;

public class Main {
	
	// 바이러스의 정보들을 저장할 내부 클래스
	static class Position {
		int x;	// x좌표
		int y;	// y좌표
		int virusNum;	// 해당 바이러스의 번호
		int time;	// 바이러스 증식 시간
		
		public Position(int x, int y, int virusNum, int time) {
			this.x = x;
			this.y = y;
			this.virusNum = virusNum;
			this.time = time;
		}
	}
	
	static int N;	// 배열의 크기
	static int K;	// 바이러스 1번부터 K번까지
	static int[][] map;
	static boolean[][] visited;
	static List<Position> virusList = new ArrayList<>();	// 바이러스들의 정보를 담은 리스트
	static Queue<Position> queue = new LinkedList<>();	
	static int S;	// 시간(S초)
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 맵에서 바이러스가 1번부터 K번 이하의 바이러스인 경우
				if(map[i][j] <= K && map[i][j] > 0) {
					virusList.add(new Position(i, j, map[i][j], 0));
				}
			}
		}
		
		// 바이러스들의 정보를 담은 리스트 정렬(커스텀 정렬)
		Collections.sort(virusList, new Comparator<Position>() {

			@Override
			public int compare(Position p1, Position p2) {
				return p1.virusNum - p2.virusNum;	// 바이러스 번호가 낮은순으로 정렬(오름차순 정렬)
			}
		});
		
		// 바이러스 번호 오름차순 정렬한 순으로 바이러스 정보들 큐에 저장
		for(Position info: virusList) {
			queue.add(info);
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());	// 해당 시간 초 저장
		int X = Integer.parseInt(st.nextToken());	// X좌표 저장
		int Y = Integer.parseInt(st.nextToken());	// Y좌표 저장
		
		bfs();	// 너비우선탐색 실시
		System.out.println(map[X-1][Y-1]);
	}
	
	// 너비우선탐색 메서드
	public static void bfs() {
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowVirusNum = now.virusNum;
			int nowTime = now.time;
			
			// 현재 시간이 S초가 됐으면
			if(nowTime == S) {
				return;	// 너비우선탐색 종료
			}
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문한 좌표인 경우
				if(visited[nextX][nextY]) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 바이러스가 없는 좌표(0)인 경우
				if(map[nextX][nextY] == 0) {
					map[nextX][nextY] = nowVirusNum;	// 탐색한 좌표에 해당 바이러스 번호 증식해줌
					queue.add(new Position(nextX, nextY, nowVirusNum, nowTime + 1));	// 바이러스 정보들 큐에 저장
					visited[nextX][nextY] = true;	// 해당 좌표 방문처리
				}
			}
		}
	}

}
