import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] wheelArr; // 톱니바퀴 배열 (4개의 톱니바퀴 각각 8개의 극)
	static int K; // 회전 횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		wheelArr = new int[4][8]; // [0][0] ~ [3][7]
		
		// 4개의 톱니바퀴의 8개의 극 입력받기
		for (int i=0; i<4; i++) {
			String wheel = br.readLine(); // 해당 톱니바퀴 상태 입력
			for (int j=0; j<8; j++) {
				// 해당 톱니바퀴의 8개의 극 상태 저장
				wheelArr[i][j] = wheel.charAt(j) - '0';
			}
		}
		
		K = Integer.parseInt(br.readLine());
		
		// 회전 횟수 K만큼 반복
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1; // 해당 톱니바퀴 번호 (0번부터 시작하므로 -1 해줌) 입력
			int dir = Integer.parseInt(st.nextToken()); // 해당 톱니바퀴의 회전 방향 입력
			
			// 해당 톱니바퀴와 방향에 따른 명령을 실행
			executeOperation(num, dir);
		}
		
		int result = calculateWheelScore(); // 회전시키고 난 뒤 최종 톱니바귀의 점수 합 계산
		
		System.out.println(result);
	}
	
	// 해당 톱니바퀴와 방향에 따른 명령을 실행하는 메서드
	public static void executeOperation(int num, int dir) {
		// 해당 톱니바퀴의 왼쪽 톱니바퀴 확인하기
		checkLeftSide(num-1, -dir);
		// 해당 톱니바퀴의 오른쪽 톱니바퀴 확인하긴
		checkRightSide(num+1, -dir);
		// 해당 톱니바퀴 회전시키기
		rotateWheel(num, dir);
	}
	
	// 왼쪽 톱니바퀴를 재귀적으로 확인 및 회전시키는 메서드
	// num: 현재 확인하려는 톱니바퀴 번호
	// dir: 현재 톱니바퀴를 회전시키는 방향
	public static void checkLeftSide(int num, int dir) {
		// 해당 톱니바퀴의 번호가 0보다 작은 경우
		// 즉, 왼쪽 범위 벗어난 경우 (기저 조건)
		if (num < 0) {
			return; // 메서드 종료
		}
		
		// 현재 톱니바퀴의 2번 극성과 오른쪽 톱니바퀴의 6번 극성과 같은 경우 (기저 조건)
		if (wheelArr[num][2] == wheelArr[num+1][6]) {
			return; // 회전시키면 안되므로 메서드 종료
		}
		
		// 왼쪽 톱니바퀴를 계속해서 확인하게끔 재귀 호출
		// 해당 톱니바퀴의 왼쪽이므로 num-1, 해당 톱니바퀴의 회전방향과 반대로 되게끔 (-dir)
		checkLeftSide(num-1, -dir);
		// 현재 톱니바퀴 회전 시키기
		rotateWheel(num, dir);
	}
	
	// 오른쪽 톱니바퀴를 재귀적으로 확인 및 회전시키는 메서드
	// num: 현재 확인하려는 톱니바퀴 번호
	// dir: 현재 톱니바퀴를 회전시키는 방향
	public static void checkRightSide(int num, int dir) {
		// 해당 톱니바퀴의 번호가 3보다 큰 경우
		// 즉, 오른쪽 범위를 벗어난 경우 (기저 조건)
		if (num > 3) {
			return; // 메서드 종료
		}
		
		// 현재 톱니바퀴의 6번 극성과 왼쪽 톱니바퀴의 2번 극성이 같은 경우 (기저 조건)
		if (wheelArr[num][6] == wheelArr[num-1][2]) {
			return; // 회전시키면 안되므로 메서드 종료
		}
		
		// 오른쪽 톱니바퀴를 계속해서 회전하게끔 재귀 호출
		// 해당 톱니바퀴의 오른쪽이므로 num+1, 해당 톱니바퀴의 회전방향과 반대로 되게끔 (-dir)
		checkRightSide(num+1, -dir);
		// 현재 톱니바퀴 회전 시키기
		rotateWheel(num, dir);
	}
	
	// 현재 톱니바퀴를 회전시키는 메서드
	public static void rotateWheel(int num, int dir) {
		// 해당 톱니바퀴의 회전 방향이 1 (시계 방향)인 경우
		if (dir == 1) {
			int temp = wheelArr[num][7]; // 해당 톱니바퀴의 마지막 극성
			
			// 시계 방향으로 회전하므로 [0] -> [1] 이런식으로 작은 값에서 큰 값으로
			// 즉, 시계 방향으로 회전: 배열의 요소들을 오른쪽으로 한 칸씩 이동
			for (int i=7; i>0; i--) {
				// 해당 톱니바퀴의 작은 값(방향)에서 큰 값(방향)으로 저장
				wheelArr[num][i] = wheelArr[num][i-1];
			}
			
			// 해당 톱니바퀴의 마지막 요소를 첫번째 극성 위치에 저장
			wheelArr[num][0] = temp;
		}
		// 해당 톱니바퀴의 회전 방향이 -1 (반시게 방향)인 경우
		else {
			int temp = wheelArr[num][0]; // 해당 톱니바퀴의 첫 번째 극성
			
			// 반시계 방향으로 회전하므로 [1] -> [0] 이런식으로 큰 값에서 작은 값으로
			// 즉, 반시계 방향으로 회전: 배열의 요소들을 왼쪽으로 한 칸씩 이동
			for (int i=0; i<7; i++) {
				// 해당 톱니바퀴의 큰 값(방향)에서 작은 값(방향)으로 저장
				wheelArr[num][i] = wheelArr[num][i+1];
			}
			
			// 해당 톱니바퀴의 첫번째 요소를 마지막 극성 위치에 저장
			wheelArr[num][7] = temp;
		}
	}
	
	// 회전시키고 난 뒤 최종 톱니바퀴의 점수의 합을 계산하는 메서드
	public static int calculateWheelScore() {
		int total = 0; // 총 점수
		int score = 1; // 점수 (1, 2, 4, 8)
		
		// 4개의 톱니바퀴 탐색
		for (int i=0; i<4; i++) {
			// 해당 톱니바퀴의 0번 극성 (12시 방향)이 S극 (1)인 경우
			if (wheelArr[i][0] == 1) {
				total += score; // 총 점수에 누적
			}
			score *= 2; // 해당 점수에 *2 해줌
		}
		
		return total; // 총 점수 반환
	}

}