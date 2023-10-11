import java.util.*;
import java.io.*;

public class Solution {
	
	// 코어의 좌표 정보를 담은 내부 클래스
	static class Core {
		int x;
		int y;
		
		public Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	
	static int N;
	static int[][] map;
	static List<Core> coreList;	// 코어들을 저장할 리스트
	// 4가지 방향 배열 (상, 하 ,좌, 우) => 배열에서는 하, 상, 좌, 우 
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	static int minLineLength;	// 최소 전선 길이의 합
	static int maxCoreCount;	// 최대 코어 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];	// [0][0] ~ [N-1][N-1]
			coreList = new ArrayList<>();
			minLineLength = Integer.MAX_VALUE;	// 전선 길이의 합 일단 최대값으로 초기화
			maxCoreCount = Integer.MIN_VALUE;	// 최대 코어 개수 일단 최소값으로 초기화
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 가장 자리에 위치한 것들은 코어 리스트에 추가해주지 못하도록
					if(i == 0 || i == N-1 || j == 0 || j == N-1) {
						continue;	// 넘어감
					}
					// 해당 좌표가 코어(1)인 경우
					if(map[i][j] == 1) {
						coreList.add(new Core(i, j));	// 코어 리스트에 해당 좌표정보 추가
					}
				}
			}
			
			dfs(0, 0, 0);	// 깊이우선탐색 실시
			sb.append("#").append(tc).append(" ").append(minLineLength).append("\n");
		}
		System.out.print(sb);
		
	}
	
	// 깊이우선탐색 메서드
	public static void dfs(int depth, int coreCount, int lineLength) {
		// 추가된 가지치기
		// 코어 리스트의 사이즈 - 현재 진행한 선택 수 (깊이) + 현재 연결한 코어 개수가 최대 코어 개수보다 작은 경우
		// 더이상 진행할 필요 없음 (종료조건)
		if(coreList.size() - depth + coreCount < maxCoreCount) {
			return;	// 메서드 종료
		}
		
		// 해당 깊이가 코어들이 저장된 리스트 사이즈만큼 된 경우 (종료 조건)
		if(depth == coreList.size()) {
			// 최대 코어 개수보다 현재 연결한 코어 개수가 큰 경우
			// 최대한 많은 코어를 전원에 연결했을 때 전선 길이의 합 최소가 되는 값 구하는 것이므로
			if(maxCoreCount < coreCount) {
				maxCoreCount = coreCount;	// 최대 코어 개수 갱신
				minLineLength = lineLength;	// 최소 전선 길이의 합 갱신 
			}
			// 최대 코어 개수와 현재 연결한 코어 개수가 같은 경우
			else if(maxCoreCount == coreCount) {
				// 최소 전선 길이의 합이 현재 전선 길이의 합보다 큰 경우
				if(minLineLength > lineLength) {
					minLineLength = lineLength;	// 최소 전선 길이의 합만 갱신
				}
			}
			return;	// 메서드 종료
		}
		
		// 코어 리스트에 저장된 코어중 depth(idx)에 해당하는 코어 정보 뽑기
		Core now = coreList.get(depth);	
		int nowX = now.x;
		int nowY = now.y;
		
		// 4가지 방향 탐색 (하, 상, 좌, 우)
		for(int i=0; i<4; i++) {
			int connectionCount = 0;	// 전선 연결 가능한 칸수
			int originalX = nowX;
			int originalY = nowY;
			int nextX = nowX;
			int nextY = nowY;
			
			// 한 방향으로 쭉 탐색
			while(true) {
				nextX += dx[i];
				nextY += dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					break;	// 무한반복 빠져나옴
				}
				
				// 탐색한 좌표가 코어(1) 또는 전선(-1)인 경우
				if(map[nextX][nextY] == 1 || map[nextX][nextY] == -1) {
					connectionCount = 0;	// 전선 연결 불가
					break;	// 	더이상 방향 탐색할 필요 없으므로 무한반복 빠져나옴
				}
				
				connectionCount++;	// 전선 연결 가능한 칸 수 증가
			}
			
			// 전선 연결 가능한 칸 수만큼 원래 시작좌표에서 전선 연결해주는 작업
			for(int k=0; k<connectionCount; k++) {
				originalX += dx[i];
				originalY += dy[i];
				
				map[originalX][originalY] = -1;	// 전선 연결해줬다는 표시 (-1)
			}
			
			// 전선 연결 가능한 칸 수가 0인 경우 (즉, 전선 연결 불가능한 경우)
			if(connectionCount == 0) {
				// 코어 개수와 전선 연결길이 증가시키지 않고 depth만 증가시켜서 재귀 호출
				dfs(depth+1, coreCount, lineLength);	
			}
			else {
				// depth 증가 및 코어 개수 증가, 원래 전선 연결 길이에 위에서 전선 연결 가능한 칸수 더해준 뒤
				// 그것을 토대로 재귀 호출 
				dfs(depth+1, coreCount+1, lineLength + connectionCount);
				
				originalX = nowX;
				originalY = nowY;
				
				// 위에서 전선 연결했던 작업 다시 해제해주기 
				for(int k=0; k<connectionCount; k++) {
					originalX += dx[i];
					originalY += dy[i];
					
					map[originalX][originalY] = 0;	// 전선 연결 해제
				}
			}
			
		}
	}

}
