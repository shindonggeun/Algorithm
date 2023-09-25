import java.util.*;
import java.io.*;

public class Main {
	
	static class Virus {
		int x;
		int y;
		int num;
		int time;
		
		public Virus(int x, int y, int num, int time) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.time = time;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static int[][] infectedTimeMap;
	static boolean[][] visited;
	static Queue<Virus> queue = new LinkedList<>();
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		infectedTimeMap = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 해당 좌표가 1번 바이러스에 감염된 마을인 경우
				if(map[i][j] == 1) {
					queue.add(new Virus(i, j, 1, 0));
				}
				// 해당 좌표가 2번 바이러스에 감염된 마을인 경우
				else if(map[i][j] == 2) {
					queue.add(new Virus(i, j, 2, 0));
				}
			}
		}
		
		bfs();
		int infectedTown1 = 0;
		int infectedTown2 = 0;
		int infectedTown3 = 0;
		
		// 바이러스 감염된 마을 숫자 탐색하기 (완전탐색)
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					infectedTown1++;
				}
				else if(map[i][j] == 2) {
					infectedTown2++;
				}
				else if(map[i][j] == 3) {
					infectedTown3++;
				}
			}
		}
		
		System.out.println(infectedTown1 + " " + infectedTown2 + " " + infectedTown3);
	}
	
	public static void bfs() {
		while(!queue.isEmpty()) {
			Virus now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowNum = now.num;
			int nowTime = now.time;
			visited[nowX][nowY] = true;
			
			// 3번 바이러스인 경우
			if(map[nowX][nowY] == 3) {
				continue;	// 퍼트리지 않음
			}
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 치료제를 가진(-1) 좌표인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == -1) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 아직 감염되지 않은(0) 경우
				if(map[nextX][nextY] == 0) {
					map[nextX][nextY] = nowNum;	// 해당 바이러스로 감염시킴
					infectedTimeMap[nextX][nextY] = nowTime + 1;
					queue.add(new Virus(nextX, nextY, nowNum, nowTime+1));
				}
				// 탐색한 좌표가 지금 해당 바이러스 번호와 다르면서 동시에 3번 바이러스에 감염되지 않은 경우
				else if(map[nextX][nextY] != nowNum && map[nextX][nextY] != 3) {
					// 탐색한 좌표의 바이러스 감염 시간이 현재 해당 바이러스 감염 시간보다 큰 경우면서 동시에
					// 탐색한 좌표의 바이러스 감염 시간에서 현재 해당 바이러스 감염 시간을 뺀게 1(시간)인 경우 
					if(infectedTimeMap[nextX][nextY] > nowTime && infectedTimeMap[nextX][nextY] - nowTime == 1) {
						map[nextX][nextY] = 3;	// 3번 바이러스로 감염시킴
					}
				}
			}
		}
	}

}
