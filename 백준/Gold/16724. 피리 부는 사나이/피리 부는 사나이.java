import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static char[][] map;
	static int[] parents;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		parents = new int[N * M];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		for(int i=0; i<N * M; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				char dir = map[i][j];
				int d = 0;
				// 하
				if(dir == 'D') {
					d = 0;
				}
				// 상
				else if(dir == 'U') {
					d = 1;
				}
				// 좌
				else if(dir == 'L' ) {
					d = 2;
				}
				// 우
				else {
					d = 3;
				}
				
				int nextX = i + dx[d];
				int nextY = j + dy[d];
				
				// 유니온 연산 수행
				int root1 = find(i * M + j);	// 현재 칸
				int root2 = find(nextX * M + nextY);	// 현재 칸이 다음 방향을 가리키는 칸
				
				union(root1, root2);
			}
		}
		
		int safeZone = 0;
		for(int i=0; i<N * M; i++) {
			if(find(i) == i) {
				safeZone++;
			}
		}
		
		System.out.println(safeZone);

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
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
