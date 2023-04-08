import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static int[] paperCount;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];	// 10 * 10크기인 색종이 생성
		
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		paperCount = new int[5+1];
		for(int i=1; i<=5; i++) {
			paperCount[i] = 5;
		}
		
		dfs(0, 0, 0);	// (0,0)부터 시작, 색종이 쓴 개수는 일단 0개부터 시작
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
	}
	
	public static void dfs(int x, int y, int count) {
		// (x,y) 좌표 끝까지 다 탐색한 경우 최소값 뽑아내고 종료
		if(x >= 9 && y > 9) {	
			min = Math.min(min, count);
			return;
		}
		// count가 min보다 큰 경우는 더이상 계산할 필요 없으므로 종료
		if(count >= min) {
			return;
		}
		// 오른쪽으로 이동하다가 범위 넘어가면 아래로 이동한다 (즉 가로줄 전부다 본 경우임)
		if(y > 9) {
			dfs(x + 1, 0, count);
			return;
		}
		
		// 색종이 칸이 1로 표시된 경우는 색종이 붙일 수 있는 경우이다
		if(map[x][y] == 1) {
			// 제일 큰 색종이부터 붙일 수 있는지 확인하기
			for(int i=5; i>=1; i--) {
				// 해당 크기의 색종이를 다 쓰지 않았으면서 붙일 수 있는 경우
				if(paperCount[i] > 0 && isAttachCheck(x, y, i)) {
					paperAttach(x, y, i);	// 현재 위치에 크기 i에 해당하는 색종이를 붙임
					paperCount[i]--;	// 해당 크기의 색종이 사용하였으므로 감소함
					dfs(x, y+1, count+1);	// 다음 오른쪽 탐색하기
					paperDetach(x, y, i);	// 해당 색종이 뗌
					paperCount[i]++;	// 해당 색종이를 떼어냈으므로 다시 증가함
				}
			}
		}
		// 색종이 칸이 0으로 표시된 경우는 색종이를 붙일 수 없는 경우임
		else {
			dfs(x, y+1, count);	// 오른쪽으로 넘어가서 탐색한다
		}
	}
	
	// 색종이 붙일 수 있는지 체크해주는 메서드
	public static boolean isAttachCheck(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				// 범위 벗어나면 못붙임
				if(i>=10 || j>=10) {
					return false;
				}
				
				// 색종이의 해당 칸이 1인 아닌 경우 붙일 수 없음
				if(map[i][j] != 1) {
					return false;
				}
			}
		}
		// 위의 경우 다 탐색 다 해서 이상없으면 붙일 수 있음
		return true;
	}
	
	// (x, y) 위치에서부터 해당 크기만큼의 색종이를 붙이는 함수
	public static void paperAttach(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				map[i][j] = 2;	// 색종이 붙였다는 표시
			}
		}
	}
	// (x, y) 위치에서부터 해당 크기만큼의 색종이를 떼는 함수
	public static void paperDetach(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				map[i][j] = 1;	// 색종이 뗴었다는 표시
			}
		}
	}

}
