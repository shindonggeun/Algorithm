import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			String input1 = st.nextToken();
			String input2 = st.nextToken();
			boolean find = false;
			String result = "";
			
			if(input1.length() == input2.length()) {
				input1 = input1.replace("B", "1");
				input2 = input2.replace("B", "1");
				
				input1 = input1.replaceAll("[ADOPQR]", "0");
				input2 = input2.replaceAll("[ADOPQR]", "0");
				
				input1 = input1.replaceAll("[CEFGHIJKLMNSTUVWXYZ]", "2");
				input2 = input2.replaceAll("[CEFGHIJKLMNSTUVWXYZ]", "2");
				
				if(input1.equals(input2)) {
					find = true;
				}
			}
			
			if(find) {
				result = "SAME";
			}
			else {
				result = "DIFF";
			}
			
			sb.append("#" + tc + " " + result).append("\n");
		}
		System.out.print(sb);

	}

}
