import java.util.*;
import java.io.*;

public class Main {

	static class Teacher {
		int x;
		int y;
		
		public Teacher(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static char[][] map;
	static boolean resultAvoid;
	static List<Teacher> teacherList;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N]; // [0][0] ~ [N-1][N-1]
		resultAvoid = false;
		teacherList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == 'T') {
					teacherList.add(new Teacher(i, j));
				}
			}
		}
		
		buildWall(0);

		if (resultAvoid) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}
	
	public static void buildWall(int depth) {
		if (depth == 3) {
			teacherWatch();
			return;
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] == 'X') {
					map[i][j] = 'O';
					buildWall(depth + 1);
					map[i][j] = 'X';
				}
			}
		}
	}
	
	public static void teacherWatch() {
		boolean avoidCheck = true;
		
		for (Teacher now: teacherList) {
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX;
				int nextY = nowY;
				while (true) {
					nextX += dx[i];
					nextY += dy[i];
					
					if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
						break;
					}
					
					if (map[nextX][nextY] == 'O') {
						break;
					}
					
					if (map[nextX][nextY] == 'S') {
						avoidCheck = false;
						break;
					}
				}
			}
		}
		
		if (avoidCheck) {
			resultAvoid = true;
		}
	}

}