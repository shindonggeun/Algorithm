import java.util.*;
import java.io.*;

public class Main {
	
	static class Chicken {
		int x;
		int y;
		
		public Chicken(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int M;	// 도시에 있는 치킨집 최대 M개
	static int[][] map;
	static List<Chicken> chickenList;
	static Chicken[] output;
	static int chickenMinDistance;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
		chickenList = new ArrayList<>();
		output = new Chicken[M];
		chickenMinDistance = Integer.MAX_VALUE;

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 맵에서 치킨집(2)인 경우 
				if(map[i][j] == 2) {
					chickenList.add(new Chicken(i, j));	// 치킨집 리스트에 해당 좌표 저장
				}
			}
		}
		backTracking(0, 0);
		System.out.print(chickenMinDistance);
		
		
	}
	
	// 백트래킹 메서드 (조합)
	public static void backTracking(int depth, int idx) {
		// 해당 깊이(선택한 것이) 도시에 있는 치킨집 최대 개수와 같아지는 경우
		if(depth == M) {
//			for(int i=0; i<M; i++) {
//				int tempDistance = distanceCalculate(output[i]);
//				chickenMinDistance = Math.min(chickenMinDistance, tempDistance);
//			}
			int minDistance = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 해당 좌표가 집인 경우
					if(map[i][j] == 1) {
						int tempDistance = distanceCalculate(output, i + 1, j + 1);	// 집과 가장 가까운 치킨거리 구하기
						minDistance += tempDistance;
					}
				}
			}
			chickenMinDistance = Math.min(chickenMinDistance, minDistance);
			return;
		}
		
		for(int i=idx; i<chickenList.size(); i++) {
			output[depth] = chickenList.get(i);
			backTracking(depth+1, i+1);
		}
	}
	
	public static int distanceCalculate(Chicken[] list, int houseX, int houseY) {
		
		int distance = Integer.MAX_VALUE;
		for(Chicken chicken: list) {
			int chickenX = chicken.x + 1;
			int chickenY = chicken.y + 1;
			
			int tempDistance = Math.abs(chickenX - houseX) + Math.abs(chickenY - houseY);
			distance = Math.min(distance, tempDistance);
		}
		return distance;
	}

}
