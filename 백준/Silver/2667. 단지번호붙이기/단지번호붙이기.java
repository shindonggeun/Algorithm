import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};    // x방향 배열(상, 하)
    static int[] dy = {0, 0, -1, 1};    // y방향 배열(좌, 우)
    static int apartNum = 0;			// 아파트 단지 번호
    static int[] aparts;	// 각 아파트 단지의 집 수를 저장한 배열
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		aparts = new int[N*N];	// 각 아파트 단지의 집 수를 저장한 배열 초기화 (1<=N<=25)
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 집이 있는곳이면서 방문처리가 안됐으면
				if(map[i][j] == 1 && !visited[i][j]) {
					apartNum++;	// 아파트 단지번호 증가
					bfs(i, j);	// 너비우선탐색 실행
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(apartNum).append("\n");
		Arrays.sort(aparts);	// 각 아파트 단지의 집 수를 저장한 배열을 오름차순 정렬
		
		for(int i=0; i<aparts.length; i++) {
			if(aparts[i] != 0) {
				sb.append(aparts[i]).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY));
        visited[startX][startY] = true;	// 시작 위치 방문처리
        aparts[apartNum]++;				// 해당 아파트 단지의 집 수 증가
        
        while(!queue.isEmpty()) {
        	Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            // 상, 하, 좌, 우 4가지 방향 탐색
            for(int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
             // 이동한 x, y좌표가 범위를 벗어난 경우 (음수좌표 또는 (N-1, N-1) 좌표 넘어간 경우)
             // 맵의 좌표는 (0,0) ~ (N-1, M-1) 까지이다
                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
                    continue;
                }
                
                // 방문처리가 됐거나 또는 집이 없는 곳이면 넘어감
                if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
                    continue;
                }
                
                queue.add(new Position(nextX, nextY));
                visited[nextX][nextY] = true;   // 해당좌표 방문처리
                aparts[apartNum]++;				// 해당 아파트 단지의 집 수 증가
            }
        }
	}

}
