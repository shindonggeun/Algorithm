import java.util.*;
import java.io.*;

public class Main {
	
	// 우유의 좌표를 나타내는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N; // 마을 크기 (N X N)
	static int M; // 초기 체력
	static int H; // 민트 초코 우유를 마실 때 회복하는 체력량
	static int[][] map; // 마을 지도 정보를 나타내는 2차원 배열
	static List<Position> milkPositionList; // 민트 초코 우유의 위치들을 저장하는 리스트
	static boolean[] visited; // 해당 민트 초코 우유를 방문했는지 여부를 나타내는 배열
	static int[] distToHome; // 각 우유 위치에서 집까지의 거리
	static int startX; // 집의 x 좌표 (시작 좌표)
	static int startY; // 집의 y 좌표 (시작 좌표)
	static int milkCount; // 민트 초코 우유의 총 개수
	static int maxMilkDrunk; // 마신 민트 초코 우유의 최대 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		milkPositionList = new ArrayList<>(); // 민트 초코 우유의 위치들을 저장하는 리스트 초기화
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 해당 좌표가 집(1)인 경우
				if (map[i][j] == 1) {
					// 집의 위치 초기화
					startX = i;
					startY = j;
				}
				// 해당 좌표가 민트 초코 우유(2)인 경우
				else if (map[i][j] == 2) {
					milkPositionList.add(new Position(i, j)); // 민트 초코 우유의 위치 정보 리스트에 저장
				}
			}
		}
		
		milkCount = milkPositionList.size(); // 민트 초코 우유의 개수 저장
		visited = new boolean[milkCount];
		distToHome = new int[milkCount];
		
		// 각 민트 초코 우유 위치에서 집까지의 거리 계산하는 과정
		for (int i=0; i<milkCount; i++) {
			Position milk = milkPositionList.get(i); // 해당 민트 초코 우유 위치 정보 추출
			// 집에서부터 해당 민트 초코 우유의 위치까지의 거리 계산
			distToHome[i] = calculateDistance(startX, startY, milk.x, milk.y);
		}
		
		maxMilkDrunk = 0; // 최대 마신 민트 초코 우유 개수 초기화
		
		milkDrinking(startX, startY, M, 0); // 백트래킹 알고리즘을 이용하여 민트 초코 우유 마시는 과정 탐색
		
		System.out.println(maxMilkDrunk);
	}
	
	// 두 좌표 간의 맨해튼 거리 계산해주는 메서드
	public static int calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	// 백트래킹 알고리즘을 이용하여 집에서부터 시작하여 민트 초코 우유를 마시는 과정을 탐색하는 메서드
	// x: 현재 x 좌표, y: 현재 y 좌표
	// hp: 현재 남은 체력
	// milkDrunk: 현재까지 마신 민트 초코 우유 개수
	public static void milkDrinking(int x, int y, int hp, int milkDrunk) {
		// 마신 민트 초코 우유의 최대 개수가 현재까지 마신 민트 초코 우유 개수와 같은 경우
		// 즉, 이미 모든 민트초코 우유를 마신 경우 (기저 조건)
		if (maxMilkDrunk == milkCount) {
			return; // 메서드 종료
		}
		
		// 모든 민트 초코 우유들 탐색
		for (int i=0; i<milkCount; i++) {
			// 해당 민트 초코 우유를 방문한 경우 (즉, 마신 우유인 경우)
			if (visited[i]) {
				continue; // 다음 민트 초코 우유 탐색하게끔 넘어감
			}
			
			Position milk = milkPositionList.get(i); // 해당 민트 초코 우유 위치 추출
			int distance = calculateDistance(x, y, milk.x, milk.y); // 현재 위치에서 해당 민트 초코 우유까지의 거리 계산
			
			// 탐색한 거리가 현재 남아있는 체력보다 큰 경우
			// 즉, 체력이 부족하여 해당 우유에 도달할 수 없는 경우
			if (distance > hp) {
				continue; // 다음 민트 초코 우유 탐색하게끔 넘어감
			}
			
			int newHp = hp - distance + H; // 우유를 마신 후 체력 계산 (현재 체력에서 거리 빼준 뒤 체력 회복)
			
			// 우유를 마신 후 체력이 집까지 도달 가능한 경우
			if (newHp >= distToHome[i]) {
				maxMilkDrunk = Math.max(maxMilkDrunk, milkDrunk + 1); // 최대로 마신 우유 개수 갱신
			}
			
			visited[i] = true; // 해당 민트 초코 우유 마셨으므로 방문 처리
			// 해당 민트 초코 우유 위치에서부터 다음 민트 초코 우유를 마시기 위해 해당 메서드 재귀 호출
			milkDrinking(milk.x, milk.y, newHp, milkDrunk + 1); 
			visited[i] = false; // 해당 민트 초코 우유 방문 처리 해제 (백트래킹)
		}
	}
	
}