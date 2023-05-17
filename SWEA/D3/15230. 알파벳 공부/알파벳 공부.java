import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			String input = br.readLine();
			int result = 0;
			
			// 문자열 맨 앞부터 탐색 시작
			for(int i=0; i<input.length(); i++) {
				// 알파벳 순서대로 앞에서부터 연속적으로 몇개의 알파벳이 순서대로 써져있는지 확인하는 작업
				int idx = input.charAt(i) - 'a';
				// 알파벳 순서가 인덱스랑 안맞으면 바로 탐색 종료
				if(idx != i) {
					break;
				}
				result = idx+1;
			}
			
			sb.append("#" + tc + " " + result).append("\n");
		}
		System.out.print(sb);

	}

}
