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
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Set<String> set = new HashSet<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				set.add(st.nextToken());
			}
			
			int count = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				String input = st.nextToken();
				
				if(set.contains(input)) {
					count++;
				}
			}
			
			sb.append("#" + tc + " "+ count).append("\n");
		}
		System.out.print(sb);
	}

}
