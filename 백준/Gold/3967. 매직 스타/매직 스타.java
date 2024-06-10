import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static char[][] map;
	static List<Position> emptyCellList;
	static boolean[] alphabet;
	static boolean completeMagicStar;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][9];
		emptyCellList = new ArrayList<>();
		alphabet = new boolean[13]; // [1] ~ [12] ('A' ~ 'L')
		
		for (int i=0; i<5; i++) {
			String input = br.readLine();
			for (int j=0; j<9; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'x') {
					emptyCellList.add(new Position(i, j));
				}
				else if (map[i][j] >= 'A' && map[i][j] <= 'L') {
					alphabet[map[i][j] - 'A' + 1] = true;
				}
			}
		}
		
		completeMagicStar = false;
		sb = new StringBuilder();
		makeMagicStar(0);
		System.out.print(sb);
	}
	
	public static void makeMagicStar(int depth) {
		if (depth == emptyCellList.size()) {
			if (checkMagicStar()) {
				printMagicStar();
			}
			return;
		}
		
		if (completeMagicStar) {
			return;
		}
		
		Position now = emptyCellList.get(depth);
		int nowX = now.x;
		int nowY = now.y;
		
		for (int i=1; i<=12; i++) {
			if (!alphabet[i]) {
				alphabet[i] = true;
				map[nowX][nowY] = (char) (i + 'A' - 1);
				makeMagicStar(depth + 1);
				map[nowX][nowY] = 'x';
				alphabet[i] = false;
			}
		}
		
	}
	
	public static boolean checkMagicStar() {
		int sum1 = (map[0][4] - 'A' + 1) + (map[1][3] - 'A' + 1) + (map[2][2] - 'A' + 1) + (map[3][1] - 'A' + 1);
		int sum2 = (map[3][1] - 'A' + 1) + (map[3][3] - 'A' + 1) + (map[3][5] - 'A' + 1) + (map[3][7] - 'A' + 1);
		int sum3 = (map[0][4] - 'A' + 1) + (map[1][5] - 'A' + 1) + (map[2][6] - 'A' + 1) + (map[3][7] - 'A' + 1);
		int sum4 = (map[1][1] - 'A' + 1) + (map[1][3] - 'A' + 1) + (map[1][5] - 'A' + 1) + (map[1][7] - 'A' + 1);
		int sum5 = (map[1][1] - 'A' + 1) + (map[2][2] - 'A' + 1) + (map[3][3] - 'A' + 1) + (map[4][4] - 'A' + 1);
		int sum6 = (map[1][7] - 'A' + 1) + (map[2][6] - 'A' + 1) + (map[3][5] - 'A' + 1) + (map[4][4] - 'A' + 1);
		
		if (sum1 == 26 && sum2 == 26 && sum3 == 26 && sum4 == 26 && sum5 == 26 && sum6 == 26) {
			completeMagicStar = true;
			return true;
		}
		return false;
	}
	
	public static void printMagicStar() {
		for (int i=0; i<5; i++) {
			for (int j=0; j<9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
	}

}