import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};	// x 방향배열 (상하)
	static int[] dy = {0, 0, -1, 1};	// y 방향배열 (좌우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		bfs(0, 0);	// 좌표 (0, 0)에서부터 탐색 시작
		System.out.println(map[N-1][M-1]);	// 끝 좌표에 갈 수 있는 최단거리 수
	}
	
	// 너비우선탐색 알고리즘 이용(깊이우선탐색 알고리즘 이용시 Stack Overflow 발생)
	public static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowX = now[0];
			int nowY = now[1];
			
			// 상, 하, 좌, 우 이렇게 네가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 이동한 x, y좌표가 범위를 벗어난 경우 (음수좌표 또는 (N-1, M-1) 좌표 넘어간 경우)
				// 맵의 좌표는 (0,0) ~ (N-1, M-1) 까지이다
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 방향 탐색한 좌표가 이미 방문했거나 또는 벽으로 막힌 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new int[] {nextX, nextY});
				// 원래 좌표값((0,0) 좌표에서 탐색한 좌표까지 거리)에서 +1 하여 방향 탐색한 좌표에 저장
				map[nextX][nextY] = map[nowX][nowY] + 1;	
				visited[nextX][nextY] = true;	// 해당 좌표 방문처리
			}
		}
	}

}
