import java.util.*;
import java.io.*;

public class Main {
	
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
	
	static int N;
	static int M;
	static int[][] map;
	static List<Virus> virusList;
	static Virus[] selectedVirus;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int minSpreadTime;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		virusList = new ArrayList<>();
		selectedVirus = new Virus[M];
		int emptyCount = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virusList.add(new Virus(i, j, 0));
				}
				else if (map[i][j] == 0) {
					emptyCount++;
				}
			}
		}
		
		if (emptyCount == 0) {
			System.out.println(0);
		}
		else {
			minSpreadTime = Integer.MAX_VALUE;
			makeCombinationVirus(0, 0, emptyCount);
			System.out.println(minSpreadTime == Integer.MAX_VALUE ? -1 : minSpreadTime);
		}
		
	}
	
	public static void makeCombinationVirus(int depth, int idx, int emptyCount) {
		if (depth == M) {
			virusSpread(emptyCount);
			return;
		}
		
		for (int i=idx; i<virusList.size(); i++) {
			selectedVirus[depth] = virusList.get(i);
			makeCombinationVirus(depth + 1, i + 1, emptyCount);
		}
	}
	
	public static void virusSpread(int emptyCount) {
		Queue<Virus> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N]; // [0][0] ~ [N-1][N-1]
		
		for (Virus virus: selectedVirus) {
			queue.add(virus);
			visited[virus.x][virus.y] = true;
		}
		
		while (!queue.isEmpty()) {
			Virus now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowTime = now.time;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] == 1) {
					continue;
				}
				
				if (map[nextX][nextY] == 0) {
					emptyCount--;
				}
				
				// 이부분 중요!
				// 큐 while문에 해당 조건을 걸면 답이 이상하게 나옴
				// 이유: while문 안에서 빈칸의 개수가 0이 되는 시점(즉, 바이러스가 다 퍼진 시점)을 정확히 판단하지 못하기 때문
				// 빈칸의 개수가 0이 되는 즉시 (즉, 바이러스가 다 퍼진 경우) 즉시 최소 확산 시간을 갱신하고 함수를 종료해야함
				
				// 빈칸의 개수가 0이 된 경우 (즉, 바이러스 다 퍼트린 경우)
				if (emptyCount == 0) {
					minSpreadTime = Math.min(minSpreadTime, nowTime + 1);
					return;
				}
				
				visited[nextX][nextY] = true;
				queue.add(new Virus(nextX, nextY, nowTime + 1));
			}
		}
		
	}

}