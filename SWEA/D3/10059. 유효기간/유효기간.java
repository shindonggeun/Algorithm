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
			
			String str1 = input.substring(0, 2);	// 0번 인덱스부터 2번 인덱스 전 까지
			String str2 = input.substring(2, input.length());	// 2번 인덱스부터 입력값의 길이 인덱스 전 까지
			
			int num1 = Integer.parseInt(str1);
			int num2 = Integer.parseInt(str2);
			String result = "";
			boolean flag1 = false;
			boolean flag2 = false;
			
			if(num1 > 12 || num1 == 0) {
				flag1 = true;
			}
			if(num2 > 12 || num2 == 0) {
				flag2 = true;
			}
			
			if(flag1 && flag2) {
				result = "NA";
			}
			else if(flag1) {
				result = "YYMM";
			}
			else if(flag2) {
				result = "MMYY";
			}
			else if(!flag1 && !flag2){
				result = "AMBIGUOUS";
			}
			
			
			sb.append("#" + tc + " " + result).append("\n");
		}
		System.out.print(sb);

	}

}
