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
			String num = st.nextToken();
			
			Stack<Character> stack = new Stack<>();
			
			// 문자열 길이만큼 탐색
			for(int i=0; i<N; i++) {
				char ch = num.charAt(i); // 문자열에서 한글자씩 뽑아냄
				
				// 스택이 비어있지 않은 경우
				if(!stack.isEmpty()) {
					// 스택 최상단에서 뽑아낸 글자가 문자열에서 같은 글자면 (소거해야함)
					if(ch == stack.peek()) {
						stack.pop();	// 스택에서 뽑아냄(제거)
					}
					else {
						stack.push(ch);	// 스택에 집어넣음
					}
				}
				// 스택이 비어있으면
				else {
					stack.push(ch);	// 글자 집어넣음
				}
			}
			
			String result = "";
			while(!stack.isEmpty()) {
				result+=stack.pop();
			}
			
			StringBuilder passwordSb = new StringBuilder(result);
			sb.append("#" + tc + " " + passwordSb.reverse().toString()).append("\n");
		}
		
		System.out.print(sb);

	}

}