import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), "\n");
			String input = st.nextToken();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		int heightCnt = 0;	// 세로로 누울 수 있는 자리 수
		int widthCnt = 0;	// 가로로 누울 수 있는 자리 수
		
		for(int i=0; i<N; i++) {
			int checkHeight = 0;	// 세로로 누울 수 있는 자리의 길이(.의 개수)
			int checkWidth = 0;		// 가로로 누울 수 있는 자리의 길이(.의 개수)
			
			for(int j=0; j<N; j++) {
				// 가로로 누울 수 있는 자리 탐색하기
				if(map[i][j] == '.') {
					checkWidth++;
				}
				
				if(map[i][j] == 'X' || (j == N-1)) {
					// 가로로 누울 수 있는 자리의 길이가 2 이상이면 
					// 가로 누울 수 있는 자리 증가
					if(checkWidth >= 2) {
						widthCnt++;
					}
					
					checkWidth = 0;	// 다시 길이 0으로 초기화
				}
				
				// 세로로 누울 수 있는 자리 탐색하기
				if(map[j][i] == '.') {
					checkHeight++;
				}
				
				if(map[j][i] == 'X' || (j == N-1)) {
					// 세로로 누울 수 있는 자리의 길이가 2 이상이면 
					// 세로 누울 수 있는 자리 증가
					if(checkHeight >= 2) {
						heightCnt++;
					}
					checkHeight = 0;	// 다시 길이 0으로 초기화
				}
			}
			
		}
		System.out.println(widthCnt + " " + heightCnt);
		

	}

}