import java.util.*;
import java.io.*;

public class Main {
	
	static class FireBall {
		int x;
		int y;
		int m;
		int speed;
		int dir;
		
		public FireBall(int x, int y, int m, int speed, int dir) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.speed = speed;
			this.dir = dir;
		}
	}
	
	static int N;
	static int M;
	static int K;
	static List<FireBall>[][] map;
	// 8가지 방향 배열 (상, 상 + 우, 우, 하 + 우, 하, 하 + 좌, 좌, 상 + 좌)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static List<FireBall> fireballList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		fireballList = new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken()); // 질량 입력
			int speed = Integer.parseInt(st.nextToken()); // 속력 입력
			int dir = Integer.parseInt(st.nextToken()); // 방향 입력
			
			fireballList.add(new FireBall(x, y, m, speed, dir));
		}
		
		for (int i=0; i<K; i++) {
			moveFireBall();
			checkFireBall();
		}
		
		int result = calculateFireball();
		
		System.out.println(result);
	}
	
	public static void moveFireBall() {
		for (FireBall fireball: fireballList) {
			int nextX = (fireball.x + dx[fireball.dir] * fireball.speed) % N;
			int nextY = (fireball.y + dy[fireball.dir] * fireball.speed) % N;
			
			if (nextX < 0) {
				nextX += N;
			}
			
			if (nextY < 0) {
				nextY += N;
			}
			
			fireball.x = nextX;
			fireball.y = nextY;
			map[nextX][nextY].add(fireball);
		}
		
	}
	
	public static void checkFireBall() {
//		List<FireBall> newFireballList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j].size() < 2) {
					map[i][j].clear();
					continue;
				}
				
				int totalMass = 0;
				int totalSpeed = 0;
				int oddCount = 0;
				int evenCount = 0;
				int fireballCount = map[i][j].size();

				for (FireBall fireball : map[i][j]) {
					totalMass += fireball.m;
					totalSpeed += fireball.speed;

					if (fireball.dir % 2 == 1) {
						oddCount++;
					} else {
						evenCount++;
					}
					
					fireballList.remove(fireball);
				}

				map[i][j].clear();
				int splitMass = totalMass / 5;
				int splitSpeed = totalSpeed / fireballCount;

				if (splitMass == 0) {
					continue;
				}

				if (oddCount == fireballCount || evenCount == fireballCount) {
					for (int dir = 0; dir < 8; dir += 2) {
						fireballList.add(new FireBall(i, j, splitMass, splitSpeed, dir));
					}
				} else {
					for (int dir = 1; dir < 8; dir += 2) {
						fireballList.add(new FireBall(i, j, splitMass, splitSpeed, dir));
					}
				}

			}
		}
		
//		fireballList = newFireballList;
	}
	
	public static int calculateFireball() {
		int totalMass = 0;
		
		for (FireBall fireball: fireballList) {
			totalMass += fireball.m;
		}
		
		return totalMass;
	}

}
