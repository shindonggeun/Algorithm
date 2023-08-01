import java.util.*;
import java.io.*;

public class Solution {
	
	// 방향 탐색하는 배열 (좌표평면상에서 표현할 때) (하, 좌, 우) -> 배열에서는 하(아래)가 상이다
	static int[] dx = {-1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열	
	static int[] dy = {0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 10번 돌리기
		for(int tc=1; tc<=10; tc++) {
			int num = Integer.parseInt(br.readLine());
			int[][] map = new int[100][100];	// (0, 0) ~ (99, 99)
			// 열번호 출력하면 됨
			int resultCol = 0;	// 결과값을 출력할 열번호
			int startX = 0;	// 시작 행
			int startY = 0;	// 시작 열
			
			// 100 * 100 배열에 해당 사다리 이어진지 여부 저장할 수 있게끔
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 맵에서 2인 좌표는 도착지
					// 역으로 도착지에서 시작지로 올라가는 방향을 생각하자
					if(map[i][j] == 2) {
						// 시작 좌표설정
						startX = i;	
						startY = j;	
					}
				}
			}
			
			resultCol = move(startX, startY, map);
			
			sb.append("#").append(num).append(" ").append(resultCol).append("\n");
		}
		System.out.print(sb);
		
	}
	
	// 이동하는 메서드
	public static int move(int startX, int startY, int[][] map) {
		// 무한반복
		while(true) {
			// 시작 행이 0인 경우 (도착한 경우임)
			if(startX == 0) {
				return startY;	// 도착한 열을 리턴해준다
			}
			
			// 3가지 방향 탐색 (하 -> 배열에서는 상, 좌, 우)
			for(int i=0; i<3; i++) {
				int nextX = startX + dx[i];
				int nextY = startY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (99, 99) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= 100 || nextY >= 100) {
					continue;
				}
				
				// 탐색한 좌표가 사다리에 이어지지 않은 경우(0)
				if(map[nextX][nextY] == 0) {
					continue;
				}
				
				// 탐색한 좌표가 사다리로 이어진 경우(1)
				if(map[nextX][nextY] == 1) {
					startX = nextX;
					startY = nextY;
					map[nextX][nextY] = 3;	// 탐색하지 못하도록 맵에서 3으로 설정
				}
			}
		}
	}

}
