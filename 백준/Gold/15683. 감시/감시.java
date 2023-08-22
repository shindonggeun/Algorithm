import java.util.*;
import java.io.*;

public class Main {
	
	// CCTV의 좌표 정보를 담은 내부 클래스
	static class Position {
		int x;
		int y;
		int cctvNum;
		
		public Position(int x, int y, int cctvNum) {
			this.x = x;
			this.y = y;
			this.cctvNum = cctvNum;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static List<Position> cctvList;
	static int[] output;	// cctv의 방향값을 중복 순열로 저장할 때 사용할 배열
	// 4가지 방향 배열 (cctv 90도씩 회전 시킬 수 있으므로 시계방향으로)
	// 상, 우, 하, 좌
	static int[] dx = {-1, 0, 1, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 1, 0, -1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	static int minBlindSpotSize;	// 사각지대의 최소 크기	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1) => [0][0] ~ [N-1][M-1]
		cctvList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				int value = map[i][j];
				if(value == 1 || value == 2 || value == 3 || value == 4 || value == 5) {
					cctvList.add(new Position(i, j, value));
				}
			}
		}
		
		output = new int[cctvList.size()];
		minBlindSpotSize = Integer.MAX_VALUE;
		backTracking(0, cctvList.size());
		
		System.out.println(minBlindSpotSize);
	}
	
	// 백트래킹 메서드 (중복 순열)
	public static void backTracking(int depth, int r) {
		// 해당 깊이(선택 횟수)가 r(cctv 개수)이 된 경우 (종료 조건)
		if(depth == r) {
			int[][] copyMap = new int[N][M];	// 원본맵을 복사해서 사용할 copyMap
			// 깊은복사 실시
			for(int i=0; i<N; i++) {
				copyMap[i] = map[i].clone();
			}
			
			// CCTV 번호와 중복순열로 뽑은 번호에 따라 방향을 정함
			for(int i=0; i<r; i++) {
				directionFix(cctvList.get(i), output[i], copyMap);
			}
			
			// 위의 중복순열로 뽑은 방향 탐색 다 했으면
			getBlindSpot(copyMap);	// 사각지대 구하기
			return;	// 메서드 종료
		}
		
		// 4가지 방향 배열을 cctv의 방향값을 중복 순열로 저장할 수 있도록 하는 과정
		for(int i=0; i<4; i++) {
			output[depth] = i;
			backTracking(depth+1, r);
		}
	}
	
	public static void directionFix(Position cctvInfo, int directionNum, int[][] copyMap) {
		int cctvNum = cctvInfo.cctvNum;
		
		// cctv 번호가 1번인 경우 (한쪽 방향만 감시)
		if(cctvNum == 1) {
			observeBFS(cctvInfo, directionNum, copyMap);	// 해당 방향번호에 따른 한쪽방향만 감시
		}
		// cctv 번호가 2번인 경우 (두방향 감시 가능)
		else if(cctvNum == 2) {
			// 방향 번호가 0 또는 2 인 경우 (즉,  상, 하 => 위 아래 양쪽 방향)
			if(directionNum == 0 || directionNum == 2) {
				observeBFS(cctvInfo, 0, copyMap);	// 위쪽 방향 탐색
				observeBFS(cctvInfo, 2, copyMap);	// 아래쪽 방향 탐색
			}
			// 방향 번호가 1 또는 3인 경우 (즉, 우, 좌 => 오른쪽 왼쪽 양쪽 방향)
			else if(directionNum == 1 || directionNum == 3){
				observeBFS(cctvInfo, 1, copyMap);	// 오른쪽 방향 탐색
				observeBFS(cctvInfo, 3, copyMap);	// 왼쪽 방향 탐색
			}
		}
		// cctv 번호가 3번인 경우 (직각방향 감시 가능)
		else if(cctvNum == 3) {
			// 방향 번호가 0인 경우 (위쪽을 가리키므로 직각방향이면 상, 우)
			if(directionNum == 0) {
				observeBFS(cctvInfo, 0, copyMap);	// 위쪽 방향 탐색
				observeBFS(cctvInfo, 1, copyMap);	// 오른쪽 방향 탐색
			}
			// 방향 번호가 1인 경우 (오른쪽을 가리키므로 직각방향이면 우, 하)
			else if(directionNum == 1) {
				observeBFS(cctvInfo, 1, copyMap);	// 오른쪽 방향 탐색
				observeBFS(cctvInfo, 2, copyMap);	// 아래쪽 방향 탐색
			}
			// 방향 번호가 2인 경우 (아래쪽을 가리키므로 직각방향이면 하, 좌)
			else if(directionNum == 2) {
				observeBFS(cctvInfo, 2, copyMap);	// 아래쪽 방향 탐색
				observeBFS(cctvInfo, 3, copyMap);	// 왼쪽 방향 탐색
			}
			// 방향 번호가 3인 경우 (왼쪽을 가리키므로 직각방향이면 좌, 상)
			else if(directionNum == 3) {
				observeBFS(cctvInfo, 3, copyMap);	// 왼쪽 방향 탐색
				observeBFS(cctvInfo, 0, copyMap);	// 위쪽 방향 탐색
			}
		}
		// cctv 번호가 4번인 경우 (세방향 감시 가능)
		else if(cctvNum == 4) {
			// 방향 번호가 0번 인 경우 (위쪽을 가리키므로 세방향이면 좌, 상, 우)
			if(directionNum == 0) {
				observeBFS(cctvInfo, 0, copyMap);	// 위쪽 방향 탐색
				observeBFS(cctvInfo, 1, copyMap);	// 오른쪽 방향 탐색
				observeBFS(cctvInfo, 3, copyMap);	// 왼쪽 방향 탐색
			}
			// 방향 번호가 1번 인 경우 (오른쪽을 가리키므로 세방향이면 상, 우, 하)
			else if(directionNum == 1) {
				observeBFS(cctvInfo, 0, copyMap);	// 위쪽 방향 탐색
				observeBFS(cctvInfo, 1, copyMap);	// 오른쪽 방향 탐색
				observeBFS(cctvInfo, 2, copyMap);	// 아래쪽 방향 탐색
			}
			// 방향 번호가 2번 인 경우 (아래쪽을 가리키므로 세방향이면 우, 하, 좌)
			else if(directionNum == 2) {
				observeBFS(cctvInfo, 1, copyMap);	// 오른쪽 방향 탐색
				observeBFS(cctvInfo, 2, copyMap);	// 아래쪽 방향 탐색
				observeBFS(cctvInfo, 3, copyMap);	// 왼쪽 방향 탐색
			}
			// 방향 번호가 3번 인 경우 (왼쪽을 가리키므로 세방향이면 하, 좌, 상)
			else if(directionNum == 3) {
				observeBFS(cctvInfo, 2, copyMap);	// 아래쪽 방향 탐색
				observeBFS(cctvInfo, 3, copyMap);	// 왼쪽 방향 탐색
				observeBFS(cctvInfo, 0, copyMap);	// 위쪽 방향 탐색
			}
		}
		// cctv 번호가 5번인 경우 (네방향 감시 가능)
		else if(cctvNum == 5) {
			observeBFS(cctvInfo, 0, copyMap);	// 위쪽 방향 탐색
			observeBFS(cctvInfo, 1, copyMap);	// 오른쪽 방향 탐색
			observeBFS(cctvInfo, 2, copyMap);	// 아래쪽 방향 탐색
			observeBFS(cctvInfo, 3, copyMap);	// 왼쪽 방향 탐색
		}
	}
	
	// 너비우선탐색 메서드
	public static void observeBFS(Position cctvInfo, int d, int[][] copyMap) {
		Queue<Position> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];	// [0][0] ~ [N-1][M-1]
		queue.add(cctvInfo);
		visited[cctvInfo.x][cctvInfo.y] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 방향번호에 따른 방향들 탐색
			int nextX = nowX + dx[d];
			int nextY = nowY + dy[d];
			
			// [0][0] ~ [N-1][M-1] 이외의 좌표를 탐색한 경우
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
				break;	// 탐색 종료
			}
			
			// 탐색한 좌표가 벽(6)인 경우
			if(copyMap[nextX][nextY] == 6) {
				break;	// 탐색 종료
			}
			
			// 이미 방문한 좌표인 경우
			if(visited[nextX][nextY]) {
				continue;	// 넘어감
			}
			
			
			// 탐색한 좌표가 빈칸(0)인 경우
			if(copyMap[nextX][nextY] == 0) {
				copyMap[nextX][nextY] = -1;	// 해당 좌표 -1(이미 감시했다는 표시)로 표시한 뒤
				visited[nextX][nextY] = true;	// 해당 좌표 방문처리
				queue.add(new Position(nextX, nextY, cctvInfo.cctvNum));	// 탐색한 좌표정보 큐에 저장
			}
			// 다른 CCTV가 있거나 또는 이미 감시된 칸(-1)인 경우
			else {
				visited[nextX][nextY] = true;	// 해당 좌표 방문처리 해준 뒤
				queue.add(new Position(nextX, nextY, cctvInfo.cctvNum));	// 탐색한 좌표정보 큐에 저장
			}
		}
	}
	
	// 사각지대 구하는 메서드
	public static void getBlindSpot(int[][] copyMap) {
		int zeroCount = 0;	// 사각지대 크기 구할 때 사용하는 변수
		
		// 완전탐색 이용
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 해당 카피맵에서 빈칸(0)인 경우
				if(copyMap[i][j] == 0) {
					zeroCount++;	// 사각지대 크기 증가
				}
			}
		}
		
		// 사각지대 최소 크기 갱신하기
		minBlindSpotSize = Math.min(minBlindSpotSize, zeroCount);
	}

}
