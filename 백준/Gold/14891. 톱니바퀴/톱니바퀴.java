import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] wheelArr;
	static int K; // 회전 횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		wheelArr = new int[4][8];
		
		for (int i=0; i<4; i++) {
			String wheel = br.readLine();
			for (int j=0; j<8; j++) {
				wheelArr[i][j] = wheel.charAt(j) - '0';
			}
		}
		
		K = Integer.parseInt(br.readLine());
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			
			executeOperation(num, dir);
		}
		
		int result = calculateWheelScore();
		
		System.out.println(result);
	}
	
	// 해당 톱니바퀴와 방향에 따른 명령을 실행하는 메서드
	public static void executeOperation(int num, int dir) {
		checkLeftSide(num-1, -dir);
		checkRightSide(num+1, -dir);
		rotateWheel(num, dir);
	}
	
	public static void checkLeftSide(int num, int dir) {
		if (num < 0) {
			return;
		}
		
		if (wheelArr[num][2] == wheelArr[num+1][6]) {
			return;
		}
		
		checkLeftSide(num-1, -dir);
		rotateWheel(num, dir);
	}
	
	public static void checkRightSide(int num, int dir) {
		if (num > 3) {
			return;
		}
		
		if (wheelArr[num][6] == wheelArr[num-1][2]) {
			return;
		}
		
		checkRightSide(num+1, -dir);
		rotateWheel(num, dir);
	}
	
	public static void rotateWheel(int num, int dir) {
		// 해당 톱니바퀴의 회전 방향이 1 (시계 방향)인 경우
		if (dir == 1) {
			int temp = wheelArr[num][7];
			
			// 시계 방향으로 회전하므로 [0] -> [1] 이런식으로 작은 값에서 큰 값으로
			for (int i=7; i>0; i--) {
				// 해당 톱니바퀴의 작은 값(방향)에서 큰 값(방향)으로 저장
				wheelArr[num][i] = wheelArr[num][i-1];
			}
			
			wheelArr[num][0] = temp;
		}
		// 해당 톱니바퀴의 회전 방향이 -1 (반시게 방향)인 경우
		else {
			int temp = wheelArr[num][0];
			
			// 반시계 방향으로 회전하므로 [1] -> [0] 이런식으로 큰 값에서 작은 값으로
			for (int i=0; i<7; i++) {
				// 해당 톱니바퀴의 큰 값(방향)에서 작은 값(방향)으로 저장
				wheelArr[num][i] = wheelArr[num][i+1];
			}
			
			wheelArr[num][7] = temp;
		}
	}
	
	public static int calculateWheelScore() {
		int total = 0;
		int score = 1;
		
		for (int i=0; i<4; i++) {	
			if (wheelArr[i][0] == 1) {
				total += score;
			}
			score *= 2;
		}
		
		return total;
	}

}
