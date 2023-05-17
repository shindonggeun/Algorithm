import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			String input = br.readLine();
			int[] arr = new int[10];	// 0 ~ 9까지 수의 개수 저장해주는 배열
			
			for(int i=0; i<input.length(); i++) {
				int temp = input.charAt(i) - '0';
				// 해당 글자가 안적혀 있으면
				if(arr[temp] == 0) {
					arr[temp] = 1;	// 노트에 적음
				}
				// 해당 글자가 적혀있으면
				else {
					arr[temp] = 0;	// 노트에서 지움
				}
			}
			
			int count = 0;
			for(int i=0; i<arr.length; i++) {
				// 노트에 적힌 글자인 경우
				if(arr[i] == 1) {
					count++;
				}
			}
			sb.append("#" + tc + " " + count).append("\n");
		}
		System.out.print(sb);

	}

}