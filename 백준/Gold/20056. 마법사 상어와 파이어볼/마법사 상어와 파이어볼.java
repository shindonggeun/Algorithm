import java.util.*;
import java.io.*;

public class Main {
	
	// 파이어볼의 정보를 담고 있는 내부 클래스
	static class FireBall {
		// 좌표
		int x;
		int y;
		int m; // 질량
		int speed; // 속력
		int dir; // 방향
		
		public FireBall(int x, int y, int m, int speed, int dir) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.speed = speed;
			this.dir = dir;
		}
	}
	
	static int N; // 격자 크기
	static int M; // 초기 파이어볼 개수
	static int K; // 마법사 상어 이동 횟수
	static List<FireBall>[][] map; // 격자 지도 2차원 리스트 배열 선언
	// 8가지 방향 배열 (상, 상 + 우, 우, 하 + 우, 하, 하 + 좌, 좌, 상 + 좌)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static List<FireBall> fireballList; // 파이어볼들을 저장하고 있는 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N]; // 해당 격자(지도)의 크기 만큼 2차원 리스트 배열 초기화 [0][0] ~ [N-1][N-1]
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = new ArrayList<>(); // 각 좌표에 대한 리스트 초기화
			}
		}
		
		fireballList = new ArrayList<>(); // 파이어볼들을 저장할 리스트 초기화
		
		// 초기 파이어볼 개수만큼 입력 및 저장
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1; // x 좌표 입력 (0부터 시작하기 위해 -1)
			int y = Integer.parseInt(st.nextToken()) - 1; // y 좌표 입력 (0부터 시작하기 위해 -1)
			int m = Integer.parseInt(st.nextToken()); // 질량 입력
			int speed = Integer.parseInt(st.nextToken()); // 속력 입력
			int dir = Integer.parseInt(st.nextToken()); // 방향 입력
			
			// 해당 파이어볼의 정보를 리스트에 저장
			fireballList.add(new FireBall(x, y, m, speed, dir));
		}
		
		// 마법사 상어 이동 횟수만큼 반복
		for (int i=0; i<K; i++) {
			moveFireBall(); // 파이어볼 움직이기
			checkFireBall(); // 파이어볼 상태 체크 및 분열 (4개의 파이어볼로 나누기)ㄴ
		}
		
		int result = calculateFireball(); // 남아있는 파이어볼 질량의 합 구하기
		
		System.out.println(result);
	}
	
	// 파이어볼들을 움직이게 하는 메서드
	public static void moveFireBall() {
		// 파이어볼들이 저장되어 있는 리스트 탐색
		for (FireBall fireball: fireballList) {
			// 파이어볼 이동시켜 x, y 좌표 계산하는 과정
			// 격자의 경계를 넘어갈 경우를 처리하기 위해 % N을 사용
			int nextX = (fireball.x + dx[fireball.dir] * fireball.speed) % N;
			int nextY = (fireball.y + dy[fireball.dir] * fireball.speed) % N;
			
			// 이동시킨 x 좌표가 음수가 될 경우
			if (nextX < 0) {
				nextX += N; // N을 더해 양수로 변환
			}
			
			// 이동시킨 y 좌표가 음수가 될 경우
			if (nextY < 0) {
				nextY += N; // N을 더해 양수로 변환
			}
			
			fireball.x = nextX; // 해당 파이어볼의 x 좌표를 이동시킨 x 좌표로 저장 (갱신)
			fireball.y = nextY; // 해당 파이어볼의 y 좌표를 이동시킨 y 좌표로 저장 (갱신)
			map[nextX][nextY].add(fireball); // 이동한 좌표에 해당 파이어볼 추가
		}
		
	}
	
	// 파이어볼의 상태 체크 및 4개의 파이어볼로 나누어주는 메서드
	public static void checkFireBall() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 해당 좌표에서 파이어볼의 개수가 2개 미만인 경우
				if (map[i][j].size() < 2) {
					map[i][j].clear(); // 해당 좌표에 위치한 파이어볼들 제거 (리스트 초기화)
					continue; // 다음 좌표로 넘어감
				}
				
				int totalMass = 0; // 총 질량
				int totalSpeed = 0; // 총 속력
				int oddCount = 0; // 홀수 방향 개수
				int evenCount = 0; // 짝수 방향 개수
				int fireballCount = map[i][j].size(); // 현재 좌표에 위치한 파이어볼의 개수

				// 현재 좌표에 위치한 파이어볼 탐색
				for (FireBall fireball : map[i][j]) {
					totalMass += fireball.m; // 총 질량 누적
					totalSpeed += fireball.speed; // 총 속력 누적

					// 해당 파이어볼의 방향이 홀수 방향(홀수 인덱스)인 경우
					if (fireball.dir % 2 == 1) {
						oddCount++; // 홀수 방향 개수 증가
					} 
					// 해당 파이어볼의 방향이 짝수 방향(짝수 인덱스)인 경우
					else {
						evenCount++; // 짝수 방향 개수 증가
					}
					
					fireballList.remove(fireball); // 파이어볼이 저장된 리스트에서 해당 파이어볼 제거
				}

				map[i][j].clear(); // 현재 좌표에 위치한 파이어볼들 제거 (리스트 초기화)
				int splitMass = totalMass / 5; // 나누어진 파이어볼의 질량 계산
				int splitSpeed = totalSpeed / fireballCount; // 나누어진 파이어볼의 속력 계산
				
				// 나누어진 파이어볼의 질량이 0인 경우
				if (splitMass == 0) {
					continue; // 파이어볼들을 4개로 나누지 않고 (즉, 분열하지 않고) 다음 좌표로 넘어감
				}

				// 홀수 개수가 파이어볼의 개수와 같거나 또는 짝수 개수가 파이어볼의 개수와 같은 경우
				// 즉, 해당 좌표의 파이어볼들이 모두 홀수 방향이거나 또는 모두 짝수 방향인 경우
				if (oddCount == fireballCount || evenCount == fireballCount) {
					// 방향 0, 2, 4, 6 -> 분열 과정
					for (int dir = 0; dir < 8; dir += 2) {
						// 해당 방향들에 따른 파이어볼들 리스트에 저장
						fireballList.add(new FireBall(i, j, splitMass, splitSpeed, dir));
					}
				} 
				// 해당 좌표의 파이어볼들이 홀수 방향들과 짝수 방향들이 섞여 있는 경우
				else {
					// 방향 1, 3, 5, 7 -> 분열 과정
					for (int dir = 1; dir < 8; dir += 2) {
						// 해당 방향들에 따른 파이어볼들 리스트에 저장
						fireballList.add(new FireBall(i, j, splitMass, splitSpeed, dir));
					}
				}

			}
		}
		
	}
	
	// 남아있는 파이어볼들의 질량을 계산하는 메서드
	public static int calculateFireball() {
		int totalMass = 0; // 총 질량
		
		// 파이어볼 리스트 탐색
		for (FireBall fireball: fireballList) {
			totalMass += fireball.m; // 해당 파이어볼의 질량을 총 질량에 누적
		}
		
		return totalMass; // 남아있는 파이어볼들의 총 질량 반환
	}

}