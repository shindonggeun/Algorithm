import java.util.*;
import java.io.*;

public class Main {

	static int N; // 입력으로 주어진 수열의 길이 (1부터 N까지의 수)
	static Stack<Integer> stack; // 스택 수열을 만들기위해 스택 선언
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		stack = new Stack<>(); // 스택 생성
		
		StringBuilder sb = new StringBuilder();
		boolean stackCheck = true; // 스택수열을 만들 수 있는지 체크해주는 변수 (true: 가능, false: 불가능)
		int topNum = 0; // 현재 스택에 push된 가장 큰 수를 추적하기 위한 변수
		
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 해당 입력 받은 수가 스택의 top보다 큰 경우
			if (num > topNum) {
				// top보다 큰 값들을 스택에 저장하면서 해당 입력값까지 쌓는 과정
				for (int j=topNum+1; j<=num; j++) {
					stack.push(j); // 해당 값 스택에 저장
					sb.append("+").append("\n"); // push 연산 기록
				}
				topNum = num; // 가장 최근에 push된 값을 top 변수에 갱신
			}
			// 스택의 가장 최상단 값이 현재 입력된 값과 다른 경우 (스택 수열 만들 수 없음)
			else if (stack.peek() != num) {
				stackCheck = false; // 스택 수열 만들 수 없다고 표시
			}
			
			stack.pop(); // 스택에서 최상단 값 빼내기
			sb.append("-").append("\n"); // pop 연산 기록
		}
		
		// 스택수열을 만들 수 있는 경우 스택 수열 출력, 아닌 경우 "NO" 출력
		System.out.println(stackCheck == true ? sb : "NO");

	}

}