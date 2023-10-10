import java.io.*;
import java.util.*;

public class Solution {

	static int K;
	static int totalScore; // 회전이 끝난 후 획득 점수
	static int magnet[][];
	static int direction[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			totalScore = 0;
			K = Integer.parseInt(br.readLine());
			magnet = new int[5][8]; // 1~4번 자석의 n극, s극 저장하는 배열
			direction = new int[5]; // 1~4번의 회전 방향을 저장하는 배열 (0: 회전 안함, -1: 반시계, 1: 시계)
			for (int i = 1; i < 5; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 명령어 입력
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				direction[num] = dir; // 해당 자석번호의 회전방향 저장
				setDirLeft(num - 1); // num 기준 좌측로 회전방향 세팅
				setDirRight(num + 1); // num 기준 우측으로 회전방향 세팅
				moveMagnet(); // 각 톱니바퀴들을 회전방향에 맞게 회전
			}
			for (int i = 1; i <= 4; i++) {
				// 해당 번호의 자석의 맨위[0]가 S극인 경우
				if (magnet[i][0] == 1) {
					totalScore += Math.pow(2, i - 1);	// 점수 합산해서 계산 (1, 2, 4, 8)
				}
					
			}
			sb.append("#").append(tc).append(" ").append(totalScore).append("\n");
		}
		System.out.print(sb);
	}

	// 자석들 회전시키는 메서드
	public static void moveMagnet() {
		for (int i = 1; i <= 4; i++) {
			int dir = direction[i];
			// 해당 번호의 자석이 회전하지 않는 경우
			if (dir == 0) {
				continue; // 다음 번호의 자석 탐색
			}
			// 해당 번호의 자석이 시계방향(1)으로 회전하는 경우
			else if (dir == 1) {
				rotate(i); // i번째 톱니바퀴 시계방향 회전
			}
			// 해당 번호의 자석이 반시계방향(-1)으로 회전하는 경우
			else if (dir == -1) {
				reverseRotate(i); // i번째 톱니바퀴 반시계방향 회전
			}
		}

	}

	// 해당 번호의 자석 시계방향으로 회전하는 메서드
	public static void rotate(int num) {
		int[] temp = new int[8];
		// 배열 왼쪽으로 shift
		for (int i = 0; i < 8; i++) {
			temp[(i + 1) % 8] = magnet[num][i];
		}

		// 회전한 임시 자석 배열을 다시 자석으로 옮겨줌
		for (int i = 0; i < 8; i++) {
			magnet[num][i] = temp[i];
		}
	}

	// 해당 번호의 자석 반시계방향으로 회전하는 메서드
	public static void reverseRotate(int num) {
		int[] temp = new int[8];
		// 배열 왼쪽으로 shift
		for (int i = 0; i < 8; i++) {
			temp[i] = magnet[num][(i + 1) % 8];
		}

		// 회전한 임시 자석 배열을 다시 자석으로 옮겨줌
		for (int i = 0; i < 8; i++) {
			magnet[num][i] = temp[i];
		}
	}

	// 해당 자석 번호의 왼쪽 자석의 회전방향을 설정하는 메서드
	public static void setDirLeft(int num) {
		// 자석 번호가 0이 되는 경우 (종료 조건)
		if (num == 0) {
			return;	// 메서드 종료
		}
		// 이전 자석이 회전 안하는 경우면 자신도 회전 안하게끔 설정
		if (direction[num + 1] == 0) {
			direction[num] = 0;	// 회전 안함
		} 
		// 만약 자석의 날이 맞물리는 부분의 극성이 서로 같은 경우
		else if (magnet[num + 1][6] == magnet[num][2]) {
			direction[num] = 0;	// 회전 안함
		} 
		// 자석의 날이 맞물리는 부분의 극성이 서로 다른 경우
		else {
			direction[num] = direction[num + 1] == -1 ? 1 : -1; // 반대 방향 회전
		}

		setDirLeft(num - 1);	// 다음 왼쪽 부분 탐색
	}

	// 해당 자석 번호의 오른쪽 자석의 회전방향을 설정하는 메서드
	public static void setDirRight(int num) {
		// 자석 번호가 4가 넘어가는 경우 => 종료조건
		if (num > 4) {
			return;	// 메서드 종료
		}
		// 이전 자석이 회전 안하는 경우면 자신도 회전 안하게끔 설정
		if (direction[num - 1] == 0) {
			direction[num] = 0;	// 회전 안함
		} 
		// 만약 자석의 날이 맞물리는 부분의 극성이 서로 같은 경우
		else if (magnet[num - 1][2] == magnet[num][6]) { 
			direction[num] = 0;	// 회전 안함
		} 
		// 자석의 날이 맞물리는 부분의 극성이 서로 다른 경우
		else {
			direction[num] = direction[num - 1] == -1 ? 1 : -1; // 이전 자석의 반대 방향으로 회전할 수 있게끔 설정
		}
		setDirRight(num + 1);	// 다음 오른쪽 부분 탐색
	}
}
