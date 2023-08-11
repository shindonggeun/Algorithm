import java.util.*;
import java.io.*;

public class Main {
	
	// 치킨의 좌표를 저장할 수 있도록 만들어준 클래스
	static class Chicken {
		int x;
		int y;
		
		public Chicken(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 도시 크기
	static int M;	// 도시에 있는 치킨집 최대 M개
	static int[][] map;
	static List<Chicken> chickenList;	// 치킨들의 좌표를 저장한 리스트
	static Chicken[] output;	// 치킨들의 좌표들을 조합으로 구성할 배열
	static int chickenMinDistance;	// 도시의 치킨거리 최소값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 도시 크기 입력
		M = Integer.parseInt(st.nextToken());	// 도시에 있는 최대 치킨집 개수 입력받기
		map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
		chickenList = new ArrayList<>();	// 치킨들의 좌표를 저장할 리스트 생성
		output = new Chicken[M];	// 치킨들의 좌표들을 조합으로 구성할 배열 생성
		chickenMinDistance = Integer.MAX_VALUE;	// 도시의 치킨거리 일단 최대값으로 설정

		// 입력받기
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
		backTracking(0, 0);	// 백트래킹 (조합) 실시
		System.out.println(chickenMinDistance);
		
	}
	
	// 백트래킹 메서드 (조합)
	public static void backTracking(int depth, int idx) {
		// 해당 깊이(선택한 것이) 도시에 있는 치킨집 최대 개수와 같아지는 경우 (종료조건)
		if(depth == M) {
			int minDistance = 0;	// 도시의 치킨거리(모든 집에서 치킨집까지의 거리) 최소값
			// 완전탐색 이용
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 해당 좌표가 집(1)인 경우
					if(map[i][j] == 1) {
						int tempDistance = distanceCalculate(output, i + 1, j + 1);	// 집과 가장 가까운 치킨거리 구하기
						minDistance += tempDistance; 
					}
				}
			}
			// 도시의 치킨거리 최소값 갱신해주기
			chickenMinDistance = Math.min(chickenMinDistance, minDistance);
			return;	// 메서드 종료
		}
		
		// 치킨집들의 좌표 조합 뽑아낼 수 있도록
		for(int i=idx; i<chickenList.size(); i++) {
			output[depth] = chickenList.get(i);
			backTracking(depth+1, i+1);
		}
	}
	
	// 집과 가장 가까운 치킨거리 구하는 메서드
	public static int distanceCalculate(Chicken[] chickenArr, int houseX, int houseY) {
		
		int distance = Integer.MAX_VALUE;
		// 조합으로 뽑아낸 치킨집 배열 탐색
		for(Chicken chicken: chickenArr) {
			// 치킨집들의 좌표 뽑아내기 (+1씩 해줘야함 -> 문제에서 (1, 1) 부터 시작이므로)
			int chickenX = chicken.x + 1;	
			int chickenY = chicken.y + 1;
			
			// 치킨거리 구하기
			int tempDistance = Math.abs(chickenX - houseX) + Math.abs(chickenY - houseY);
			// 집과 가장 가까운 치킨 거리 갱신해주기
			distance = Math.min(distance, tempDistance);
		}
		return distance;	// 집과 가장 가까운 치킨거리 반환
	}

}
