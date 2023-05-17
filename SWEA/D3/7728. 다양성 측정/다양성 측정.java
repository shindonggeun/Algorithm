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
			Set<Character> set = new HashSet<>();
			
			for(int i=0; i<input.length(); i++) {
				set.add(input.charAt(i));
			}
			
			sb.append("#" + tc + " " + set.size()).append("\n");
		}
		System.out.print(sb);
	}

}