import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static int[] paperCount;
	static int necessaryPaperCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		map = new int[10][10];	// 10 * 10 크기인 색종이 생성
		
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		necessaryPaperCount = Integer.MAX_VALUE;
		paperCount = new int[6];	// [1] ~ [5]까지 사용, 해당 원소는 각 색종이 크기로 이루어진 해당 크기 종류의 색종이 개수
		for(int i=1; i<=5; i++) {
			paperCount[i] = 5;	// 각 크기에 해당하는 색종이 개수 5개로 세팅 
		}
		
		dfs(0, 0, 0);	// [0][0]부터 탐색 시작, 쓴 색종이의 개수는 일단 0개부터로 시작
		
		// 모든 1을 덮는 것 불가능한 경우
		if(necessaryPaperCount == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(necessaryPaperCount);
		}
	}
	
	public static void dfs(int x, int y, int useCount) {
		// [9][9] 즉, 해당 마지막 줄의 좌표 끝까지 탐색한 경우 (종료조건)
		if(x == 9 && y > 9) {
			necessaryPaperCount = Math.min(necessaryPaperCount, useCount);	// 색종이 사용 개수 최소값 갱신
			return;	// 메서드 종료
		}
		
		// 색종이 사용한 개수가 모든 칸 붙이는데 필요한 색종이의 최소 개수보다 많은 경우는 더이상 탐색할 필요 없음 (가지치기)
		if(useCount >= necessaryPaperCount) {
			return;	// 메서드 종료
		}
		
		// 오른쪽으로 이동하다가 범위 넘어가면 아래로 이동 (즉, 가로 전부다 체크한 경우)
		if(y > 9) {
			dfs(x+1, 0, useCount);	// 아래로 이동해주면서 다시 열은 0번째부터
			return;
		}
		
		// 색종이 칸이 1로 표시된 경우는 색종이 붙일 수 있는 경우임
		if(map[x][y] == 1) {
			// 제일 큰 색종이부터 붙일 수 있는지 확인하는 작업
			for(int i=5; i>=1; i--) {
				// 해당 크기의 색종이를 다 쓰지 않았으면서 붙일 수 있는 경우 확인하는 작업
				if(paperCount[i] > 0 && isAttachCheck(x, y, i)) {
					paperAttach(x, y, i);	// 현재 위치에 크기 i에 해당하는 색종이를 붙임
					paperCount[i]--;	// 해당 크기의 색종이를 사용하였으므로 해당 크기의 색종이 개수 줄여줌
					dfs(x, y+1, useCount+1);	// 다음 오른쪽 탐색하기 (색종이 사용 개수도 증가시켜줌)
					paperDetach(x, y, i);	// 해당 크기의 색종이를 떼줌 (다시 원복 -> 백트래킹)
					paperCount[i]++;	// 해당 크기의 색종이를 뗴어냈으므로 해당 크기의 색종이 개수 늘려줌 (다시 원복)
				}
			}
		}
		// 색종이 칸이 0으로 표시된 경우는 색종이를 붙일 수 없는 경우임
		else {
			dfs(x, y+1, useCount);	// 오른쪽으로 넘어가서 탐색 실시
		}
	}
	
	// 색종이 붙일 수 있는지 체크해주는 메서드 
	public static boolean isAttachCheck(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				// 범위 벗어난 경우
				if(i>=10 || j>=10) {
					return false;	// 색종이 붙일 수 없음
				}
				
				// 색종이의 해당 칸이 1이 아닌 경우
				if(map[i][j] != 1) {
					return false;	// 색종이 붙일 수 없음
				}
			}
		}
		// 위의 경우 탐색 다 해서 이상 없으면 붙일 수 있는 경우임
		return true;
	}
	
	// [x][y] 위치에서부터 해당 크기만큼의 색종이를 붙이는 메서드
	public static void paperAttach(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				map[i][j] = 2;	// 색종이 붙인 표시 해주기
			}
		}
	}
	
	// [x][y] 위치에서부터 해당 크기만큼의 색종이를 떼는 메서드
	public static void paperDetach(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				map[i][j] = 1;	// 색종이 떼었다는 표시 해주기
			}
		}
	}

}
