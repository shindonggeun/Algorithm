import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 10개 돌리기
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());
			int result = 1;	// 계산 가능하면 1, 불가능하면 0
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();	// 처음 토큰값은 제껴버림(필요없음)
				char operation = st.nextToken().charAt(0);	// 연산자(토큰값) 입력 받기 (부모노드)
				
				// 뒤의 토큰값이 더 있는 경우(토큰값 공백단위로 끊음) -> 즉, 자식노드가 있는 경우
				if(st.hasMoreTokens()) {
					// 부모노드가 연산자가 아닌 숫자인 경우(0 ~ 9) -> 계산 불가능 (result = 0)
					if(operation >= '0' && operation <= '9') {
						result = 0;
					}
				}
				// 뒤의 토큰값이 없는 경우(공백단위로 더 입력된 게 없음) -> 즉, 자식노드 없는 경우
				else {
					// 부모노드가 연산자인 경우 (+, -, *, /) -> 계산 불가능 (result = 0)
					if(operation == '+' || operation == '-' || operation == '*' || operation == '/') {
						result = 0;	
					}
				}
			}
			
			// 위의 경우 이외는 계산이 가능한 경우이다 -> result값이 1임
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb);

	}

}
