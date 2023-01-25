import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] numArr = new int[9][9];
		
		for(int i=0; i<9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				numArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 0;	// 찾으려는 최대값
		int col = 0;	// 열
		int row = 0;	// 행
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(max < numArr[i][j]) {
					max = numArr[i][j];
					row = i;
					col = j;
				}
			}
		}
		System.out.println(max);
		System.out.println((row+1) + " " + (col+1));
		
 
	}
	

}