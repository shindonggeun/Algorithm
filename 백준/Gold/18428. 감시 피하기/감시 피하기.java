import java.util.*;
import java.io.*;

public class Main {
	
	// 선생님의 위치 정보를 담고 있는 내부 클래스
	static class Teacher {
		int x;
		int y;
		
		public Teacher(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N; // 복도의 크기
	static char[][] map; // 복도를 나타내는 2차원 배열
	static List<Teacher> teacherList; // 선생님들의 좌표 정보를 저장하는 리스트
	static boolean resultAvoid; // 모든 학생이 감시를 피할 수 있는지 여부를 나타내는 변수
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N]; // [0][0] ~ [N-1][N-1]
		teacherList = new ArrayList<>(); // 리스트 초기화
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0); // 각 칸의 정보 입력
				// 해당 칸이 선생님('T')인 경우
				if (map[i][j] == 'T') {
					// 선생님의 좌표 정보를 리스트에 저장
					teacherList.add(new Teacher(i, j));
				}
			}
		}
		
		resultAvoid = false; // 결과 변수 초기화 (모든 학생이 아직 피할 수 있다고 확정되지 않았으므로 false로 초기화)
		buildWall(0); // 장애물을 설치하는 메서드 호출
		
		System.out.println(resultAvoid ? "YES" : "NO");

	}
	
	// 장애물을 설치하는 메서드 (백트래킹)
	// depth: 장애물을 설치한 개수
	public static void buildWall(int depth) {
		// 장애물을 설치한 개수가 3개인 경우 (즉, 장애물을 모두 설치한 경우) (기저 조건1)
		if (depth == 3) {
			watchCheck(); // 감시 여부를 체크하는 메서드 호출
			return; // 메서드 종료
		}
		
		// 이미 감시를 피할 수 있는 방법을 찾은 경우 백트래킹 메서드 더이상 진행할 필요 없음 (기저 조건2)
		if (resultAvoid) {
			return; // 메서드 종료
		}
		
		// 복도의 모든 칸을 순회
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 해당 칸이 빈칸('X')인 경우
				if (map[i][j] == 'X') {
					map[i][j] = 'O'; // 해당 칸에 장애물을 설치
					buildWall(depth + 1); // 재귀 호출을 통해 다음 장애물 설치
					map[i][j] = 'X'; // 해당 칸 다시 원복시킴, 즉 빈칸으로 만들어줌 (백트래킹)
				}
			}
		}
	}
	
	// 설치된 장애물들을 이용하여 선생님의 감시를 피할 수 있는지 확인하는 메서드
	public static void watchCheck() {
		boolean avoidCheck = true; // 설치된 장애물을 이용하여 모든 학생들이 감시를 피할 수 있다고 가정 (true)
		
		// 모든 선생님들의 좌표들을 탐색
		for (Teacher teacher: teacherList) {
			// 현재 선생님의 좌표
			int nowX = teacher.x;
			int nowY = teacher.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX; // 탐색한 좌표를 현재 선생님의 좌표로 설정
				int nextY = nowY;  // 탐색한 좌표를 현재 선생님의 좌표로 설정
				
				// 해당 방향으로 끝까지 탐색하게끔 무한반복 실시
				while (true) {
					nextX += dx[i]; // 다음 x좌표로 이동 (해당 방향의 다음 좌표 탐색하게끔 누적)
					nextY += dy[i]; // 다음 y좌표로 이동 (해당 방향의 다음 좌표 탐색하게끔 누적)
					
					// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
					if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
						break; // 다음 방향 탐색하도록 무한 반복 빠져나옴 (해당 방향 감시 종료)
					}
					
					// 탐색한 좌표가 장애물('O')인 경우
					if (map[nextX][nextY] == 'O') {
						break; // 다음 방향 탐색하도록 무한 반복 빠져나옴 (해당 방향 감시 종료)
					}
					
					// 탐색한 좌표가 학생('S')인 경우
					if (map[nextX][nextY] == 'S') {
						avoidCheck = false; // 감시를 피할 수 없으므로 false로 설정
						break; // 더이상 확인할 필요 없으므로 무한 반복 빠져나옴
					}
				}
			}
			
			// 이미 학생이 감시에 걸린 경우
			if (!avoidCheck) {
				break; // 더이상 다른 선생님 확인할 필요 없으므로 선생님들의 좌표 탐색 종료
			}
		}
		
		// 모든 학생들이 감시를 피한 경우
		if (avoidCheck) {
			resultAvoid = true; // 최종 결과 true로 설정 (즉, 해당 설치된 장애물들을 이용하여 모든 학생들이 감시 피할 수 있음)
		}
	}

}