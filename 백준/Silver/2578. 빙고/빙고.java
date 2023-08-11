import java.util.*;
import java.io.*;

public class Main {
	
	static boolean[][] visited;
	static int bingoCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[5][5];	// 5 * 5 2차원 배열 (빙고판)
		List<Integer> list = new ArrayList<>();	// 사회자가 부른 숫자들을 저장하는 리스트
		visited = new boolean[5][5];	// 빙고판 체크해주는 방문배열
		for(int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				int num = Integer.parseInt(st.nextToken());
				list.add(num);
			}
		}
		
		int count = 0;	// 사회자가 수 몇개 불렀는지를 저장하는 변수
		// 5 * 5 * 5 = 125밖에 안되므로 3중 for문 괜찮다
		for(int num: list) {
			count++;	// 사회자가 부른 수 증가
			bingoCount = 0;	// 빙고 몇개 나왔는지 판단해주는 변수 (0으로 초기화)
			// 완전탐색 이용
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					if(map[i][j] == num) {
						visited[i][j] = true;
					}
				}
			}
			
			// 사회자가 부른 수의 개수가 최소 12개 이상이 돼야 빙고 체크 
			// 5*5 빙고판에서 사회자가 부른 수가 최소 12개 돼야 빙고 3개가 완성 될 수 있음
			if(count >= 12) {
				rowCheck();	// 행체크
				columnCheck();	// 열체크 
				diagonalCheck();	// 대각선 두방향 체크
				
				// 빙고 3개 이상 완성된 경우
				if(bingoCount >= 3) {
					break;	// 반복문 빠져나옴
				}
			}
		}
		System.out.println(count);

	}
	
	// 5*5 빙고판에서 행 체크해주는 메서드
	public static void rowCheck() {
		
		for(int i=0; i<5; i++) {
			int checkCount = 0;	// 빙고 체크된거 확인해주는 임시변수
			for(int j=0; j<5; j++) {
				// 빙고판에 체크한 수인 경우
				if(visited[i][j]) {
					checkCount++;
				}
			}
			// 빙고 하나 완성됐으면
			if(checkCount == 5) {
				bingoCount++;	// 빙고 완성개수 늘려줌
			}
		}
	}
	
	// 5*5 빙고판에서 열 체크해주는 메서드
	public static void columnCheck() {
		for(int i=0; i<5; i++) {
			int checkCount = 0;	// 빙고 체크된거 확인해주는 임시변수
			for(int j=0; j<5; j++) {
				// 빙고판에 체크한 수인 경우
				if(visited[j][i]) {
					checkCount++;
				}
			}
			// 빙고 하나 완성됐으면
			if(checkCount == 5) {
				bingoCount++;	// 빙고 완성개수 늘려줌
			}
		}
	}
	
	// 5*5 빙고판에서 대각선 체크해주는 메서드
	public static void diagonalCheck() {
		// 왼쪽위에서부터 오른쪽 아래 대각선 방향으로 체크해주는 과정
		int checkCount = 0;
		for(int i=0; i<5; i++) {
			// 빙고판에서 체크한 수인 경우
			if(visited[i][i]) {
				checkCount++;
			}
		}
		
		// 빙고 하나 완성됐으면
		if(checkCount == 5) {
			bingoCount++;	// 빙고 완성개수 늘려줌
		}
		
		// 오른쪽위부터 왼쪽 아래 대각선방향으로 체크해주는 과정
		checkCount = 0;
		for(int i=0; i<5; i++) {
			if(visited[i][4-i]) {
				checkCount++;
			}
		}
		
		// 빙고 하나 완성됐으면
		if(checkCount == 5) {
			bingoCount++;	// 빙고 완성개수 늘려줌
		}
	}	

}
