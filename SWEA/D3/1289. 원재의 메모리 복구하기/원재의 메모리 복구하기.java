import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			String memory = br.readLine();
			char beforeBit = '0';
			int count = 0;
			
			for(int i=0; i<memory.length(); i++) {
				if(beforeBit != memory.charAt(i)) {
					beforeBit = memory.charAt(i);
					count++;
				}
			}
			
			sb.append("#" + tc + " " + count).append("\n");
		}
		System.out.print(sb);
	}

}
