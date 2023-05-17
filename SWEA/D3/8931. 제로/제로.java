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
			Stack<Integer> stack = new Stack<>();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				if(!stack.isEmpty()) {
					if(num == 0) {
						stack.pop();
						continue;
					}
				}
				
				stack.push(num);
			}
			
			int sum = 0;
			while(!stack.isEmpty()) {
				sum += stack.pop();
			}
			
			sb.append("#" + tc + " " + sum).append("\n");
		}
		System.out.print(sb);

	}

}