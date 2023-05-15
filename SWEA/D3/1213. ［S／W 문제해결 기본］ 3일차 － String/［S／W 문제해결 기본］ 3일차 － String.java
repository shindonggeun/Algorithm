import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String str = br.readLine();
			String input = br.readLine();
			int count = 0;
			
			while(input.indexOf(str) != -1) {
				input = input.replaceFirst(str, "");
				count++;
			}
			
			sb.append("#" + tc + " " + count).append("\n");
		}
		System.out.print(sb);

	}

}
