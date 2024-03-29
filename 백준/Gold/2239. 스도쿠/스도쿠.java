import java.io.*;

public class Main {

	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		
		for(int i=0; i<9; i++) {
			String input = br.readLine();
			for(int j=0; j<9; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		
		sudokuSolve();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);

	}
	
	public static boolean sudokuSolve() {
		// 완전탐색 이용
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				// 게임맵에서 아직 숫자 채우지 못한 경우
				if(map[i][j] == 0) {
					for (int num = 1; num <= 9; num++) {
                        if (isValid(i, j, num)) {
                            map[i][j] = num;
                            if (sudokuSolve()) {  // 재귀 호출
                                return true;
                            }
                            map[i][j] = 0;  // 해결할 수 없으면 다시 0으로 초기화
                        }
                    }
                    return false;
				}
			}
		}
		
		return true;
	}
	
	public static boolean isValid(int row, int col, int num) {
		// 1. 행에 같은 숫자가 있는지 확인
		for(int i=0; i<9; i++) {
			// 게임맵에서 같은 숫자가 있는 경우
			if(map[row][i] == num) {
				return false;
			}
		}
		
		// 2. 열에 같은 숫자가 있는지 확인
		for(int i=0; i<9; i++) {
			// 게임맵에서 같은 숫자가 있는 경우
			if(map[i][col] == num) {
				return false;
			}
		}
		
		// 3. 3x3짜리 배열에 같은 숫자가 있는지 확인
		int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                // 게임맵에서 같은 숫자가 있는 경우
            	if (map[i][j] == num) {
                    return false;
                }
            }
        }
		
        // 위 과정 다 거쳐서 같은 숫자 없는 경우는 true 반환
		return true;
	}

}
