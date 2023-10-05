import java.util.*;
import java.io.*;

public class Solution {
	
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int W;
	static int H;
	static int[][] map;
	static int[] output;	// 대상 숫자를 담아올 배열 (정수값들을 순열로 나타낼 배열)
	static int minRemainBlock;
	
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];	// [0][0] ~ [H-1][W-1]
			minRemainBlock = Integer.MAX_VALUE;
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			output = new int[N];
			
			backTracking(0);
			sb.append("#").append(tc).append(" ").append(minRemainBlock).append("\n");
		}
		System.out.print(sb);

	}
	
	// 백트래킹 메서드 (중복순열 메서드)
	public static void backTracking(int depth) {
		// 해당 깊이가 선택 횟수 (즉, 수열 길이 N)와 같아지면 (종료 조건) 
		if(depth == N) {
			int[][] copyMap = new int[H][W];
			// 2차원 배열 깊은복사 실시
			for(int i=0; i<H; i++) {
				copyMap[i] = map[i].clone();
			}
			
			for(int i=0; i<N; i++) {
				bfs(copyMap, output[i]);
			}
			
			int resultBlockCount = countBlock(copyMap);
			minRemainBlock = Math.min(minRemainBlock, resultBlockCount);
			return;
		}
		
		for(int i=0; i<W; i++) {
			output[depth] = i;
			backTracking(depth + 1);
		}
	}
	
	public static void bfs(int[][] copyMap, int startY) {
		int startX = -1;
		
		for(int i=0; i<H; i++) {
			if(copyMap[i][startY] != 0) {
				startX = i;
				break;
			}
		}
		
		// 해당 열에 벽돌이 없으면 종료
		if(startX == -1) {
			return;
		}
		
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int range = copyMap[nowX][nowY] - 1;
			copyMap[nowX][nowY] = 0;	// 벽돌 제거
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				for(int j=1; j<=range; j++) {
					int nextX = nowX + dx[i] * j;
					int nextY = nowY + dy[i] * j;
					
					// 탐색한 좌표가 [0][0] ~ [H-1][W-1] 이외의 좌표를 탐색한 경우 (즉, 범위를 벗어난 경우)
					if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
						continue;	// 넘어감
					}
					
					// 탐색한 좌표가 빈칸(0)이 아닌 경우 (즉, 벽돌인 경우)
					if(copyMap[nextX][nextY] != 0) {
						queue.add(new Position(nextX, nextY));
					}
				}
			}
			
		}
		
		// 빈 공간을 채우는 작업 수행
		// 위의 너비우선탐색 다 실시했으면 빈공간 채워주는 작업 실시 (벽돌 밑으로 떨어뜨리는 작업)
	    blockDown(copyMap);
        
	}
	
	// 벽돌 밑으로 떨어뜨리는 작업을 해주는 메서드
	public static void blockDown(int[][] copyMap) {
		Stack<Integer> stack = new Stack<>();
		
		// 각 열 탐색하기
		for(int col=0; col<W; col++) {
			// 각 행 탐색하기
			for(int row=0; row<H; row++) {
				// 탐색한 좌표가 빈칸(0)이 아닌 경우 (즉, 벽돌인 경우)
				if(copyMap[row][col] != 0) {
					stack.push(copyMap[row][col]);	// 벽돌의 값을 스택에 저장
				}
			}
			
			// 맨 아래부터 위로 올라가며 해당 열에 있는 모든 행 탐색
			for(int row=H-1; row>=0; row--) {
				// 스택이 비어있는 경우 
				if(stack.isEmpty()) {
					copyMap[row][col] = 0;	// 빈칸으로 채워줌
				}
				else {
					// 빈칸 채우는 작업
					copyMap[row][col] = stack.pop();	// 스택의 값을 복사한 맵에 저장해줌
				}
			}
		}
	}
	
	// 벽돌의 개수를 세주는 메서드 
	public static int countBlock(int[][] copyMap) {
		int blockCount = 0;
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(copyMap[i][j] != 0) {
					blockCount++;
				}
			}
		}
		
		return blockCount;
	}

}
