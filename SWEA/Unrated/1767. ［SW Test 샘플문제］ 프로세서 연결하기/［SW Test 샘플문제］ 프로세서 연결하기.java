import java.util.*;
import java.io.*;

public class Solution {
	
	// 코어들의 좌표 정보를 저장하는 내부 클래스
	static class Core {
		int x;
		int y;
		
		public Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 가로 세로 크기
	static int[][] map;
	static List<Core> coreList;	// 코어들의 좌표정보를 저장한 리스트
	static int maxCoreCount;	// 최대 코어 개수
	static int minLineLength;	// 최소 전선 길이
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];	// [0][0] ~ [N-1][N-1]
			coreList = new ArrayList<>();
			maxCoreCount = Integer.MIN_VALUE;
			minLineLength = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					// 맵에서 가장자리인 경우 코어들을 저장한 리스트에 추가하지 않도록
					if(i == 0 || i == N-1 || j == 0 || j == N-1) {
						continue;	// 넘어감
					}
					// 해당 좌표에 코어(1)가 있는 경우
					if(map[i][j] == 1) {
						coreList.add(new Core(i, j));	// 코어 리스트에 추가
					}
				}
			}
			
			lineConnectCheck(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(minLineLength).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 전선 연결하는 메서드 (깊이우선탐색 + 백트래킹 알고리즘 이용)
	public static void lineConnectCheck(int depth, int connectedCoreCount, int lineLength) {
		// 추가된 가지치기
		// 코어 리스트의 사이즈 - 현재 진행한 선택 수 (깊이) + 현재 연결한 코어 개수
		// 위의 값이 최대 코어 개수보다 작은 경우 더이상 진행 할 필요 없음 (종료조건)
		if(coreList.size() - depth + connectedCoreCount < maxCoreCount) {
			return;	// 메서드 종료
		}
		
		// 해당 깊이(선택 횟수)가 코어들이 저장된 리스트 사이즈만큼 된 경우 (종료 조건)
		if(depth == coreList.size()) {
			// 최대 코어 개수보다 현재 연결한 코어 개수가 큰 경우
			// 최대한 많은 코어를 전원에 연결했을 때 전선 길이의 합 최소가 도니느 값을 구하는 것이므로
			if(maxCoreCount < connectedCoreCount) {
				maxCoreCount = connectedCoreCount;	// 최대 코어 개수 갱신
				minLineLength = lineLength;	// 최소 전선 길이의 합 갱신
			}
			// 최대 코어 개수가 현재 연결한 코어 개수와 같은 경우
			else if(maxCoreCount == connectedCoreCount) {
				// 최소 전선 길이의 합이 현재 전선 길이의 합보다 큰 경우
				if(minLineLength > lineLength) {
					minLineLength = lineLength;	// 최소 전선 길이의 합만 갱신하도록 하기
				}
			}
			return;	// 메서드 종료
		}
		
		// 코어 리스트에 저장된 코어들에서 depth(idx) (선택한 것)에 해당하는 코어 정보 뽑기
		Core now = coreList.get(depth);
		int nowX = now.x;
		int nowY = now.y;
		
		// 4가지 방향 탐색 (하, 상, 좌, 우)
		for(int i=0; i<4; i++) {
			int connectedBlockCount = 0;	// 전선 연결 가능한 칸 수
			int originalX = nowX;
			int originalY = nowY;
			int nextX = nowX;
			int nextY = nowY;
			
			// 선택된 한가지 방향으로 끝까지 탐색
			while(true) {
				nextX += dx[i];
				nextY += dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					break;	// 다른 방향 탐색하도록 무한반복 빠져나옴
				}
				
				// 탐색한 좌표가 코어(1)이거나 또는 전선(-1)인 경우
				if(map[nextX][nextY] == 1 || map[nextX][nextY] == -1) {
					connectedBlockCount = 0;	// 전선 연결 불가하므로 다시 전선 연결 가능한 칸 수 0으로 만들기
					break;	// 다른 방향 탐색하도록 무한반복 빠져나옴
				}
				
				// 그 이외의 경우는 전선 연결이 가능하다
				connectedBlockCount++;	// 전선 연결 가능한 칸 수 증가
			}
			
			// 전선 연결 가능한 칸 수만큼 원래 시작좌표에서 전선 연결해주는 작업
			for(int k=0; k<connectedBlockCount; k++) {
				originalX += dx[i];
				originalY += dy[i];
				
				map[originalX][originalY] = -1;	// 전선 연결해줬다는 표시 (-1)
			}
			
			// 전선 연결 가능한 칸 수가 0인 경우 (즉, 전선 연결 불가능한 경우)
			if(connectedBlockCount == 0) {
				// 현재 연결된 코어 개수와 전선 연결길이 증가시키지 않고 depth(선택횟수)만 증가시켜서 재귀 호출
				lineConnectCheck(depth+1, connectedCoreCount, lineLength);
			}
			else {
				// 현재 연결된 코어 개수 증가, 원래 전선 연결 길이에 위에 과정에서 전선 연결 가능한 칸 수만큼 더해준 뒤
				// 그것을 parameter로 같이 넘겨줘서 재귀 호출
				lineConnectCheck(depth+1, connectedCoreCount+1, lineLength + connectedBlockCount);			
				
				// 전선 연결했던것들 다시 해제해주기 위해 원래 좌표 정보 불러오기
				originalX = nowX;
				originalY = nowY;
				
				// 위에서 전선 연결했던 작업 다시 해제해주기 (백트래킹)
				for(int k=0; k<connectedBlockCount; k++) {
					originalX += dx[i];
					originalY += dy[i];
					
					map[originalX][originalY] = 0;	// 전선 연결 해제 (0)
					
				}
			}
		}
		
	}

}
