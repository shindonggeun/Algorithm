import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			int num = Integer.parseInt(br.readLine());
			int[][] arr = new int[num][num];
			
			for(int i=0; i<num; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<num; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] rotation_90 = rotation(arr);
			int[][] rotation_180 = rotation(rotation_90);
			int[][] rotation_270 = rotation(rotation_180);
			
			sb.append("#" + tc).append("\n");
			for(int i=0; i<num; i++) {
				for(int j=0; j<num; j++) {
					sb.append(rotation_90[i][j]);
				}
				sb.append(" ");
				
				for(int j=0; j<num; j++) {
					sb.append(rotation_180[i][j]);
				}
				sb.append(" ");
				
				for(int j=0; j<num; j++) {
					sb.append(rotation_270[i][j]);
				}
				sb.append("\n");
			}
			
		}
		System.out.print(sb);

	}
	
	// 배열 시계방향으로 90도 회전시키는 메서드
	public static int[][] rotation(int[][] arr) {
		int[][] result = new int[arr.length][arr.length];
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length; j++) {
				result[i][j] = arr[arr.length-j-1][i];
			}
		}
		
		return result;
	}

}