import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		Stack<Integer> stack = new Stack<>();	
		StringBuilder sb = new StringBuilder();
		int start = 0;	// 시작변수
		
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());	// 입력 받은 숫자
			
			// 입력 받은 수가 시작변수보다 큰 경우
			if(num > start) {
				// 시작변수+1부터 입력 받은 수까지 stack에 집어넣음
				for(int i=start+1; i<=num; i++) {
					stack.push(i);
					sb.append("+").append("\n");	// push() 표시
				}
				start = num;	// 입력 받은 수를 시작변수로 초기화
			}
			// stack의 최상단 수가 입력 받은 수가 아닌 경우 (입력된  수열을 만들 수 없는 경우임)
			else if(stack.peek() != num) {
				System.out.println("NO");	
				return;		// 프로그램 종료
			}
			
			stack.pop();	
			sb.append("-").append("\n");	// 스택에서 pop() 표시
		}
		System.out.print(sb);

	}

}