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
			char[] arr = input.toCharArray();	// 문자열을 char 배열로 쪼개서 저장
			Arrays.sort(arr);	// 문자 오름차순 정렬
			boolean find = false;
			if(arr[0] == arr[1]) {
				if(arr[0] != arr[2] && arr[2] == arr[3]) {
					find = true;
				}
			}
			
			if(find) {
				sb.append("#" + tc + " Yes").append("\n");
			}
			else {
				sb.append("#" + tc + " No").append("\n");
			}
		}
		System.out.print(sb);

	}

}
