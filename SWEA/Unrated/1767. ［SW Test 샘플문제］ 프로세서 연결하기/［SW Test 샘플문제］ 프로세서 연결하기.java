import java.util.*;
import java.io.*;

public class Solution {

	// 코어들이 연결된 좌표 정보를 저장해주는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] map;
	static List<Position> coreList;	// 코어들이 연결된 좌표 정보들을 저장한 리스트
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	static int minLineLength;	// 전선 길이의 합의 최소값
	static int coreMaxCount;	// 최대 core를 전원에 연결했을 때 개수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreList = new ArrayList<>();	// 코어들의 좌표 정보를 담을 리스트 생성
			minLineLength = Integer.MAX_VALUE;	// 전선 길이 합의 최소값을 저장할 변수 일단 최대값으로 설정
			coreMaxCount = Integer.MIN_VALUE;	// 코어 최대로 연결했을 때 개수 일단 최소값으로 설정
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// core가 가장자리에 위치해 있는 경우는 list에 추가하지 않도록 제외
					if(i == 0 || i == N-1 || j == 0 || j == N-1) {
						continue;
					}
					
					// 해당 좌표가 core(1) 인 경우
					if(map[i][j] == 1) {
						coreList.add(new Position(i, j));	// 해당 좌표정보 list에 저장
					}
					
				}
			}
			
			dfs(0, 0, 0);	// 깊이우선탐색 실시
			
			sb.append("#").append(tc).append(" ").append(minLineLength).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int depth, int coreCount, int lineLength) {
		// 해당 깊이가 코어들이 저장된 리스트 사이즈만큼 된 경우 (종료 조건)
		if(depth == coreList.size()) {
			// 최대한 많은 코어를 전원에 연결한 개수보다 현재 코어 연결한 개수가 많아진 경우 (갱신 필요)
			if(coreMaxCount < coreCount) {
				coreMaxCount = coreCount;	// 현재 코어개수가 최대값이 됨
				minLineLength = lineLength;	// 전선 길이의 합(최소) 갱신해줌
			}
			// 최대한 많은 코어를 전원에 연결한 개수가 현재 코어 연결한 개수랑 같은 경우는 길이만 비교해준다
			else if(coreMaxCount == coreCount) {
				if(minLineLength > lineLength) {	
					minLineLength = lineLength;	// 전선 길이의 합(최소) 갱신
				}
			}
			return;	// 메서드 종료
		}
		
		Position now = coreList.get(depth);	// 해당 코어 좌표값 정보 뽑음
		int nowX = now.x;
		int nowY = now.y;
		
		// 4가지 방향 탐색 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
		for(int i=0; i<4; i++) {
			int count = 0;
			int originalX = nowX;
			int originalY = nowY;
			int nextX = nowX;
			int nextY = nowY;
			
			while(true) {
				// 해당 방향으로 계속해서 이동해서 탐색
				nextX += dx[i];	
				nextY += dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, N-1) 이외인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					break;	// 빠져나옴
				}
				
				// 탐색한 좌표가 core(1) 또는 전선(-1)인 경우
				if(map[nextX][nextY] == 1 || map[nextX][nextY] == -1) {
					count = 0;	// 전선 연결 불가
					break;	// 더이상 방향 탐색할 필요 없이 빠져나옴
				}
				
				count++;	// 전선 연결가능한 칸 수 증가
			}
			
			// 전선 연결 가능한 칸수만큼 원래 시작좌표에서 전선 연결해주는 작업
			for(int k=0; k<count; k++) {
				originalX += dx[i];
				originalY += dy[i];
				
				map[originalX][originalY] = -1;	// 전선 연결해줌
			}
			
			// 전선 연결가능한 칸 수가 0인 경우 (즉, 전선 연결 불가능한 경우)
			if(count == 0) {	
				dfs(depth+1, coreCount, lineLength);	// 코어 개수 증가시키지 않고 depth만 증가시켜서 재귀 호출
			}
			else {
				dfs(depth+1, coreCount+1, lineLength+count);	// depth와 코어 개수 증가시킨 뒤 전선 길이에 전선 연결 가능한 칸수 증가시켜서 재귀 호출
				originalX = nowX;
				originalY = nowY;
				
				// 전선 연결해준거 (전선 연결해준 칸들) 다시 해제해주는 작업 (원복시키는 작업) => 백트래킹
				for(int k=0; k<count; k++) {
					originalX += dx[i];
					originalY += dy[i];
					
					map[originalX][originalY] = 0;	// 전선 연결 해제 (빈칸은 0)
				}
			}
		}
	}

}
