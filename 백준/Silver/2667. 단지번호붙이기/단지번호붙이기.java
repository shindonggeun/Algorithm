import java.util.*;
import java.io.*;

public class Main {
	
	// 맵의 좌표 정보를 저장할 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 지도 크기
	static int[][] map;	// 지도
	static boolean[][] visited;	// 지도 해당 좌표 방문 여부를 나타낼 배열
	static int houseCount;	// 총 단지수
	static List<Integer> houseNumList;	// 단지내 집의 수를 저장할 리스트
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];	// [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N];
		houseNumList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					int houseNum = bfs(i, j);	// 각 단지내 집의 수 반환
					houseCount++;	// 총 단지수 증가
					houseNumList.add(houseNum);
				}
			}
		}
		
		// 단지내 집의 수 오름차순 정렬
		Collections.sort(houseNumList);
		
		StringBuilder sb = new StringBuilder();
		sb.append(houseCount).append("\n");
		
		for (int houseNum: houseNumList) {
			sb.append(houseNum).append("\n");
		}
		System.out.print(sb);
		
	}
	
	// 각 단지 내 집의 수를 찾기 위한 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		// 너비우선탐색 메서드를 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));	// 시작 좌표 정보 큐에 저장
		visited[startX][startY] = true;	// 시작 좌표 방문처리
		int houseCount = 1;	// 단지내 집의 수 1로 초기화
		
		// 너비우선탐색 메서드 시작
		while (!queue.isEmpty()) {
			// 해당 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY <0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 집이 없는 곳(0)이거나 또는 탐색한 좌표가 이미 방문처리 된 경우
				if (map[nextX][nextY] == 0 || visited[nextX][nextY]) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표 정보 큐에 저장
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;	// 탐색한 좌표 정보 방문처리
				houseCount++;	// 단지내 집의 수 증가
			}
		}
		
		// 너비우선탐색 끝났으면 단지내 집의 수 반환
		return houseCount;
	}

}
