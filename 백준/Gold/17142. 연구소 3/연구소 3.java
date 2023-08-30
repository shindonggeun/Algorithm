import java.util.*;
import java.io.*;

public class Main {
	
	// 바이러스의 좌표 정보 및 해당 바이러스가 퍼지는 시간을 담은 내부 클래스
	static class Virus {
		int x;
		int y;
		int time;
		
		public Virus(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}
	
	static int N;	// 연구소의 크기
	static int M;	// 바이러스의 개수
	static int[][] map;
	static List<Virus> virusList;	// 맵에서 바이러스들의 좌표정보를 담은 리스트
	static Virus[] virusOutput;	// 바이러스의 좌표정보를 담은 객체들을 조합으로 나타낼 배열
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	static int zeroCount;	// 맵에서 빈칸(0)이 몇개 있는지는 나타낸 변수
	static int virusSpreadMinTime;	// 모든 빈칸에 바이러스를 퍼뜨리는 최소 시간
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 방 크기 입력
		M = Integer.parseInt(st.nextToken());	// 바이러스 개수 입력
		
		map = new int[N][N];	// [0][0] ~ [N-1][N-1]
		virusList = new ArrayList<>();
		virusOutput = new Virus[M];
		zeroCount = 0;
		virusSpreadMinTime = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++ ) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 맵에서 해당 좌표가 바이러스(2)인 경우
				if(map[i][j] == 2) {
					virusList.add(new Virus(i, j, 0));
				}
				// 맵에서 해당 좌표가 빈칸(0)인 경우
				else if(map[i][j] == 0) {
					zeroCount++;	
				}
			}
		}
		
		
		// 맵에서 빈칸(0)인 좌표가 없는 경우
		if(zeroCount == 0) {
			System.out.println(0);
		}
		// 맵에서 빈칸(0)인 좌표의 개수가 0이 아닌 경우 (즉, 빈칸 하나라도 있는 경우)
		else {
			combination(0, 0);	// 바이러스 좌표들 조합 뽑아냄
			if(virusSpreadMinTime == Integer.MAX_VALUE) {
				System.out.println(-1);
			}
			else {
				System.out.println(virusSpreadMinTime);
			}
		}
	}
	
	// 백트래킹 (조합 메서드)
	public static void combination(int depth, int idx) {
		// 깊이(선택횟수)가 M이 된 경우 (즉, 선택횟수가 바이러스 개수와 같아진 경우) => 종료조건
		if(depth == M) {
			bfs(zeroCount);	// 너비우선탐색 시작
			return;	// 메서드 종료
		}
		
		
		for(int i=idx; i<virusList.size(); i++) {
			virusOutput[depth] = virusList.get(i);
			combination(depth+1, i+1);
		}
	}
	
	// 너비우선탐색 메서드
	public static void bfs(int tempZeroCount) {
		Queue<Virus> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];	// [0][0] ~ [N-1][N-1]
		// 선택된 바이러스 개수(M)까지 조합으로 뽑아낸 바이러스들을 큐에 집어넣음
		for(int i=0; i<M; i++) {
			queue.add(virusOutput[i]);
			visited[virusOutput[i].x][virusOutput[i].y] = true;	// 해당 바이러스들의 좌표들 방문처리
		}
		
		while(!queue.isEmpty()) {
			Virus nowVirus = queue.poll();
			int nowX = nowVirus.x;
			int nowY = nowVirus.y;
			int nowTime = nowVirus.time;
			
			// 4가지 방향 탐색 => 하, 상, 좌, 우
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 벽(1)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 1) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 빈칸(0)인 경우 => 바이러스가 감염시켜버리므로 빈칸의 개수 줄여줌
				if(map[nextX][nextY] == 0) {
					tempZeroCount--;	// 빈칸의 개수 줄여줌
				}
				
				// 맵에서 빈칸의 개수가 0이 된 경우 => 바이러스에 의해 모두 감염됨
				if(tempZeroCount == 0) {
					virusSpreadMinTime = Math.min(virusSpreadMinTime, nowTime + 1);
					return;	// 메서드 종료
				}
				
				visited[nextX][nextY] = true;
				queue.add(new Virus(nextX, nextY, nowTime + 1));
			}
		}
	}
	
}
