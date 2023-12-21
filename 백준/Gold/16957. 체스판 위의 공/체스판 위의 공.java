import java.util.*;
import java.io.*;

public class Main {
	
	static int R;
	static int C;
	static int[][] map;
	static int[][] ballCount;
	static int[] parents;
	// 8가지 방향 배열 (상, 상 + 우, 우, 하 + 우, 하, 하 + 좌, 좌, 상 + 좌)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		ballCount = new int[R][C];
		parents = new int[R * C];
		
		for(int i=0; i<R*C; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		moveBall();	// 각 칸에 대해 공을 이동시켜 최종 목적지 찾기
		calculateBall();	// 각 칸에 도달하는 공의 수 계산
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(ballCount[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void moveBall() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				int minVal = map[i][j];
				int minIdx = i * C + j;
				
				// 8가지 방향 탐색
				for(int d=0; d<8; d++) {
					int nextX = i + dx[d];
					int nextY = j + dy[d];
					
					if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
						continue;
					}
					
					if(map[nextX][nextY] < minVal) {
						minVal = map[nextX][nextY];
						minIdx = nextX * C + nextY;
					}
				}
				
				// 시작좌표를 1차원으로 표현한 인덱스와 
				// 8가지 방향 탐색한 좌표 중 가장 작은 값을 가지는 좌표의 인덱스와 유니온 연산 수행 
				union(i * C + j, minIdx);
			}
		}
	}
	
	public static void calculateBall() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				int root = find(i * C + j);
				ballCount[root / C][root % C]++;
			}
		}
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return;
		}
		else if(map[aRoot / C][aRoot % C] < map[bRoot / C][bRoot % C]) {
			parents[bRoot] = aRoot;
		}
		else {
			parents[aRoot] = bRoot;
		}
	}

}
