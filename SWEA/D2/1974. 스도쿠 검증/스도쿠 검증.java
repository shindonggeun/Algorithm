import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			int[][] map = new int[9][9];
			boolean find = true;	// 행, 열, 3*3 격자 체크해서 중복되는 값이 있는 지 여부 체크
			
			for(int i=0; i<9; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<9; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 행과 열 체크
			for(int i=0; i<9; i++) {
				int rowSum = 0;
				int colSum = 0;
				for(int j=0; j<9; j++) {
					rowSum += map[i][j];
					colSum += map[j][i];
				}
				
				// 1 ~ 9까지 더하면 45
				// 즉 45가 아닌 경우는 문제에서 원하는 경우가 아님
				if(rowSum != 45 || colSum != 45) {
					find = false;	
					break;
				}
			}
			
			// 중복되는 값이 있는 경우 0 출력하고 뒤의 3*3 격자 체크 넘어감
			if(!find) {
				sb.append("#" + tc + " " + 0).append("\n");
				continue;
			}
			
			// 3*3 격자 체크 
			for(int i=0; i<9; i+=3) {
				for(int j=0; j<9; j+=3) {
					int sum = 0;
					for(int x=0; x<3; x++) {
						for(int y=0; y<3; y++) {
							sum += map[i+x][j+y];
						}
					}
					
					if(sum != 45) {
						find = false;
						break;
					}
				}
				
				if(!find) {
					break;
				}
			}
			
			// 위의 과정을 거쳐서 중복되는 값이 없는 경우 1 출력
			if(find) {
				sb.append("#" + tc + " " + 1).append("\n");
			}
			// 중복되는 값이 있는 경우 0 출력
			else {
				sb.append("#" + tc + " " + 0).append("\n");
			}
			
		}
		System.out.print(sb);

	}

}