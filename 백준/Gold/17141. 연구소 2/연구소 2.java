import java.util.*;
import java.io.*;

public class Main {
	
	// 바이러스의 좌표 정보를 저장해주는 내부 클래스
	static class Virus {
		int x;
		int y;
		
		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int N;	// 맵의 크기
	static int M;	// 바이러스 놓을 개수
	static int[][] map;
	static List<Virus> virusList;	// 바이러스들의 정보를 가지고 있는 리스트
	static Virus[] virusOutput;	// 조합으로 뽑아낸 바이러스의 좌표정보들을 가지고 있는 배열
	static int zeroCount;	// 원본맵에서 빈칸의 개수
	static int virusSpreadMinTime;	// 모든 빈칸에 바이러스를 퍼뜨리는 최소 시간
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];	// [0][0] ~ [N-1][N-1]
		virusList= new ArrayList<>();
		virusOutput = new Virus[M];
		zeroCount = 0;
		virusSpreadMinTime = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 맵에서 해당 좌표가 바이러스(2)인 경우
				if(map[i][j] == 2) {
					virusList.add(new Virus(i, j));
				}
				// 맵에서 해당 좌표가 빈칸(0)인 경우
				else if(map[i][j] == 0) {
					zeroCount++;	// 빈칸의 개수 증가
				}
			}
		}
		
		// 바이러스들 중 M개 선택했으므로 선택 안한것들은 0(빈칸)으로 만들어줘야 하므로 
		// 빈칸의 개수 = 원래 빈칸의 개수 + (바이러스개수 - M)
		zeroCount += virusList.size() - M;	
		// 맵에서 빈칸의 개수가 0인 경우
		if(zeroCount == 0) {
			System.out.println(0);	// 탐색할 필요없이 바이러스 퍼트리는데 최소시간은 0임
		}
		else {
			combination(0, 0);	// 바이러스 좌표들의 조합 뽑기 실시
			// 모든 빈칸에 바이러스를 퍼트릴 수 없는 경우
			if(virusSpreadMinTime == Integer.MAX_VALUE) {
				System.out.println(-1);	// -1 출력
			}
			// 모든 빈칸에 바이러스 퍼트릴 수 있는 경우
			else {
				System.out.println(virusSpreadMinTime);
			}
		}
		
	}
	
	// 백트래킹 (조합 메서드)
	public static void combination(int depth, int idx) {
		// 깊이(바이러스 선택 횟수)가 M이 된 경우(즉, 바이러스 M개 놓을 위치 다 정해진 경우)
		if(depth == M) {
			int[][] copyMap = copyMap();
			bfs(zeroCount, copyMap);
			return;	// 메서드 종료
		}
		
		// 바이러스가 어디 놓여질지 조합 뽑는 과정
		for(int i=idx; i<virusList.size(); i++) {
			virusOutput[depth] = virusList.get(i);
			combination(depth+1, i+1);
		}
	}
	
	// 원본 맵 복사해주는 메서드
	public static int[][] copyMap() {
		int[][] copyMap = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 원본 맵의 해당 좌표가 바이러스(2)인 경우
				if (map[i][j] == 2) {
					copyMap[i][j] = 0; // 복사한 맵에서는 빈칸(0)으로 만들어줌
				}
				// 그이외의 좌표는 원본맵과 똑같이
				else {
					copyMap[i][j] = map[i][j];
				}
			}
		}
		

		return copyMap; // 복사한 맵 반환
	}
	
	public static void bfs(int tempZeroCount, int[][] copyMap) {
		Queue<Virus> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		for(int i=0; i<M; i++) {
			queue.add(virusOutput[i]);	// 바이러스 놓여지는 곳들 큐에 저장
			copyMap[virusOutput[i].x][virusOutput[i].y] = 2;	// 바이러스가 놓인 좌표들 바이러스(2) 처리
			visited[virusOutput[i].x][virusOutput[i].y] = true;	// 해당 바이러스 좌표 방문처리
		}
		
		int virusSpreadTime = 0;	// 바이러스 퍼트린 시간
		
		while(!queue.isEmpty()) {
			// 바이러스 퍼트린 시간이 바이러스 퍼트린 최소 시간보다 크거나 같은 경우
			if(virusSpreadTime >= virusSpreadMinTime) {
				return;	// 더이상 탐색할 필요 없이 메서드 종료시킴 (가지치기)
			}
			
			int virusCount = queue.size();
			
			for(int i=0; i<virusCount; i++) {
				Virus virusNow = queue.poll();
				int nowX = virusNow.x;
				int nowY = virusNow.y;
				
				// 4가지 방향 탐색 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
				for(int d=0; d<4; d++) {
					int nextX = nowX + dx[d];
					int nextY = nowY + dy[d];
					
					// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
					if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
						continue;	// 넘어감
					}
					
					// 탐색한 좌표가 이미 방문했거나 또는 복사한맵에서 빈칸(0)이 아닌 경우
					if(visited[nextX][nextY] || copyMap[nextX][nextY] != 0) {
						continue;	// 넘어감
					}
					
					copyMap[nextX][nextY] = 2;	// 탐색한 좌표 바이러스 퍼트림
					queue.add(new Virus(nextX, nextY));	// 탐색한 좌표 큐에 저장
					visited[nextX][nextY] = true;
					tempZeroCount--;	// 원본맵에서 가져온 빈칸(0)의 개수 감소 (바이러스 퍼트렸으므로)
				}
			}
			
			virusSpreadTime++;	// 바이러스 퍼트린 시간 증가
			// 빈칸의 개수가 0이 된 경우 (즉, 바이러스 다 퍼트려서 더이상 빈칸이 없는 경우)
			if(tempZeroCount == 0) {
				virusSpreadMinTime = Math.min(virusSpreadMinTime, virusSpreadTime);	// 바이러스 퍼트린 최소 시간 갱신
				return;	// 메서드 종료
			}
		}
		

	}

}
