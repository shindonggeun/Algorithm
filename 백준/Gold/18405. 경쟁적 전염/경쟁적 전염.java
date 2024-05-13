import java.util.*;
import java.io.*;

public class Main {
	
	// 바이러스 정보를 담고있는 내부 클래스
	static class Virus {
		int x;
		int y;
		int type;	// 바이러스 종류
		int time;	// 바이러스 퍼트리는데 걸리는 시간
		
		public Virus(int x, int y, int type, int time) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.time = time;
		}
	}
	
	static int N;
	static int K;	// 1번부터 K번까지의 바이러스
	static int[][] map;
	static boolean[][] visited;	// 방문배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int S;	// 해당 시간
	static List<Virus> virusList;	// 바이러스들의 정보를 담고있는 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N];
		virusList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					virusList.add(new Virus(i, j, map[i][j], 0));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		
		// 바이러스들을 담고있는 리스트 바이러스 종류(번호) 낮은 순으로 오름차순 정렬
		Collections.sort(virusList, (a, b) -> a.type - b.type);
		 
		bfs();	// 바이러스 퍼트리기 위해 너비우선탐색 실시
		
		System.out.println(map[x][y]);	// 해당 위치에 존재하는 바이러스 종류 출력
	}
	
	// 바이러스를 퍼트리기 위한 너비우선탐색 메서드
	public static void bfs() {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Virus> queue = new LinkedList<>();
		
		// 바이러스가 담긴 리스트 순회
		for (Virus virus: virusList) {
			queue.add(virus);	// 해당 바이러스 큐에 저장
			visited[virus.x][virus.y] = true;	// 바이러스가 있는 초기 좌표 방문 처리
		}
		
		while (!queue.isEmpty()) {
			// 현재 바이러스 정보 큐에서 뽑아냄
			Virus now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowType = now.type;	// 바이러스 종류
			int nowTime = now.time;	// 현재 시간
			
			// 현재 시간이 S초가 된 경우
			if (nowTime == S) {
				return;	// 너비우선탐색 종료
			}
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 바이러스가 존재하는 좌표인 경우 -> 즉, 바이러스 퍼진 좌표
				// 바이러스가 존재하지 않는 경우는 0임
				if (visited[nextX][nextY] || map[nextX][nextY] != 0) {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 큐에 탐색한 좌표에 퍼진 바이러스 정보 저장
				queue.add(new Virus(nextX, nextY, nowType, nowTime + 1));
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문 처리
				map[nextX][nextY] = nowType;	// 탐색한 좌표에 해당 바이러스 퍼트리기
			}
		}
	}

}