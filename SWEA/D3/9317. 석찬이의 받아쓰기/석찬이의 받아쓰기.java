import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			String result = br.readLine();
			int count = 0;	
			
			// 문자열 길이만큼 한글자씩 탐색
			for(int i=0; i<N; i++) {
				if(input.charAt(i) == result.charAt(i)) {
					count++;
				}
			}
			
			sb.append("#" + tc + " " + count).append("\n");
		}
		System.out.print(sb);
	}

}
