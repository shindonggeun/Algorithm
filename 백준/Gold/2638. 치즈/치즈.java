import java.util.*;
import java.io.*;

public class Main {
	
	// 치즈의 좌표 정보를 저장하는 내부 클래스
	static class Cheese {
		int x;
		int y;
		
		public Cheese(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 세로 크기
	static int M;	// 가로 크기
	static int[][] map;	
	static boolean[][] visited;	// 방문배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int meltTime = 0;	// 치즈가 모두 녹는데 걸리는 시간
		// 모든 치즈가 녹을 때까지 반복
        while(hasCheese()) {
            visited = new boolean[N][M]; // 방문 배열 초기화
            cheeseMelt();	// 치즈 녹이는 작업 실시
            meltTime++; // 치즈 모두 녹이는데 걸리는 시간 증가
        }
        
        System.out.println(meltTime);
	}
	
	// 현재 모눈종이 위에 치즈가 있는지 확인하는 메서드
    public static boolean hasCheese() {
    	for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
            	// 해당 좌표가 치즈(1)인 경우
                if(map[i][j] == 1) {
                    return true; // 치즈가 하나라도 있으면 true 반환
                }
            }
        }
        return false; // 치즈가 없으면 false 반환
    }
	
	// 현재 위치에서 치즈가 녹을지 여부를 판단해서 녹이는 메서드
    public static void cheeseMelt() {
    	// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
        Queue<Cheese> queue = new LinkedList<>();
        queue.add(new Cheese(0, 0)); // 외부 공기에서 시작 ([0][0]에서부터 시작)
        visited[0][0] = true;	// [0][0] 좌표 방문처리
        
        // 큐 빌때까지 반복 (너비우선탐색 이용)
        while(!queue.isEmpty()) {
        	// 현재 치즈의 좌표 정보 뽑아내기
            Cheese now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            // 4가지 방향 탐색 (하, 상, 좌, 우)
            for(int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표이거나 또는 이미 방문한 좌표인 경우
                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || visited[nextX][nextY]) {
                    continue;	// 넘어감
                }
                
                // 탐색한 좌표가 치즈가 없는 부분(0)인 경우
                // 즉, 외부 공기와 접촉한 치즈면을 찾으면 해당 위치를 큐에 추가
                if(map[nextX][nextY] == 0) {
                    visited[nextX][nextY] = true;	// 탐색한 좌표 방문처리
                    queue.add(new Cheese(nextX, nextY));	// 탐색한 좌표 정보 큐에 추가
                }
            }
        }
        
        // 치즈 녹이는 작업 실시
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
            	// 해당 좌표가 치즈(1)이면서 동시에 녹일 수 있는 경우
                if(map[i][j] == 1 && isMelted(i, j)) {
                    map[i][j] = 0; // 치즈 녹임
                }
            }
        }
    }
	
	// 주어진 위치의 치즈가 녹을지 여부를 반환하는 메소드
	public static boolean isMelted(int x, int y) {
	    int count = 0;	// 치즈 녹이기 위한 외부 공기의 개수
	    // 4가지 방향 탐색 (하, 상, 좌, 우)
	    for(int i=0; i<4; i++) {
	        int nextX = x + dx[i];
	        int nextY = y + dy[i];
	        
	        // 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
	        if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                continue;	// 넘어감
            }
            
	        // 탐색한 좌표가 이미 방문한 경우 (즉, 외부 공기인 경우)
            if(visited[nextX][nextY]) {
                count++;	// 외부 공기의 개수 증가
            }
	    }
	    
	    // 외부 공기의 개수가 2개 이상인 경우 true 반환 (녹일 수 있음)
	    // 2개 미만인 경우는 false 반환 (녹일 수 없음)
	    return count >= 2;
	}

}
