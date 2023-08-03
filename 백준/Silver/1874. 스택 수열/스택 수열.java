import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		
		int start = 0;	// 시작변수 지정 
		StringBuilder sb = new StringBuilder();
		boolean find = false;	// 스택수열 만들 수 있는지 없는지 판단해주는 변수
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());	// 입력받은 숫자
			
			// 입력받은 숫자가 시작변수보다 큰 경우
			if(num > start) {
				// 시작변수+1부터 시작해서 입력받은 숫자까지 stack에 push해줌
				for(int k=start+1; k<=num; k++) {
					stack.push(k);
					sb.append("+").append("\n");	// 스택 push 표시 StringBuilder에 저장
				}
				// 위의 과정을 거쳐 스택에 다 저장했으면 입력받은 수를 시작변수로 초기화해줌
				start = num;	
			}
			// 스택의 최상단 수가 입력 받은 수가 아닌 경우 (입력된 수열을 만들 수 없는 경우)
			else if(stack.peek() != num){
				find = true;	// 스택수열 만들 수 없음
			}
			
			stack.pop();	// 스택에 최상단에 저장된 숫자 빼냄
			sb.append("-").append("\n");
		}
		
		// 스택수열 만들 수 없는 경우
		if(find) {
			System.out.println("NO");	// 입력된 수열 만들 수 없으므로 "NO" 출력한 뒤
			return;	// 메인 메서드 종료
		}
		
		// 스택수열 만들 수 있으면 StringBuilder 출력
		System.out.print(sb);

	}

}
