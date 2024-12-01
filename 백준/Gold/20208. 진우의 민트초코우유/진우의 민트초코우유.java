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
	
	static int N;
	static int M;
	static int H;
	static int[][] map;
	static List<Position> milkPositionList;
	static boolean[] visited;
	static int[] distToHome;
	static int startX;
	static int startY;
	static int milkCount;
	static int maxMilkDrunk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		milkPositionList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					startX = i;
					startY = j;
				}
				else if (map[i][j] == 2) {
					milkPositionList.add(new Position(i, j));
				}
			}
		}
		
		milkCount = milkPositionList.size();
		visited = new boolean[milkCount];
		distToHome = new int[milkCount];
		
		for (int i=0; i<milkCount; i++) {
			Position milk = milkPositionList.get(i);
			distToHome[i] = calculateDistance(startX, startY, milk.x, milk.y);
		}
		
		maxMilkDrunk = 0;
		
		milkDrinking(startX, startY, M, 0);
		
		System.out.println(maxMilkDrunk);
	}
	
	public static int calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	public static void milkDrinking(int x, int y, int hp, int milkDrunk) {
		if (maxMilkDrunk == milkCount) {
			return;
		}
		
		for (int i=0; i<milkCount; i++) {
			if (visited[i]) {
				continue;
			}
			
			Position milk = milkPositionList.get(i);
			int distance = calculateDistance(x, y, milk.x, milk.y);
			
			if (distance > hp) {
				continue;
			}
			
			int newHp = hp - distance + H;
			if (newHp >= distToHome[i]) {
				// 마신 우유 개수가 최대값을 갱신하면 업데이트
				maxMilkDrunk = Math.max(maxMilkDrunk, milkDrunk + 1);
			}
			
			visited[i] = true;
			milkDrinking(milk.x, milk.y, newHp, milkDrunk + 1);
			visited[i] = false;
		}
	}
	
}