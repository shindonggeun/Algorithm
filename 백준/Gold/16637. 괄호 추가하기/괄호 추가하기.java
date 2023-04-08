import java.util.*;
import java.io.*;

public class Main {
	
	static int answer;
	static ArrayList<Character> operationList;
	static ArrayList<Integer> numList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		String input = br.readLine();
		operationList = new ArrayList<>();
		numList = new ArrayList<>();
		
		// 입력값 한글자씩 탐색
		for(int i=0; i<N; i++) {
			char ch = input.charAt(i);	// 한글자씩 추출
			// 연산자인경우
			if(ch == '+' || ch == '-' || ch == '*') {
				operationList.add(ch);
			}
			// 숫자인 경우
			else {
				int num = ch - '0';
				numList.add(num);
			}
		}
		
		answer = Integer.MIN_VALUE;
		dfs(numList.get(0), 0);
		System.out.println(answer);

	}
	
	public static int calculate(char op, int num1, int num2) {
		int result = 0;
		
		// 스위치문 이용해서 연산자 경우 나눠주기 (연산자는 +, -, * 이렇게 3개만 있음)
		switch(op) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
		}
		
		return result;
	}
	
	public static void dfs(int result, int opIdx) {
		// 주어진 연산자의 개수가 초과한 경우
		if(opIdx >= operationList.size()) {
			answer = Math.max(answer, result);
			return;
		}
		// 괄호가 없는 경우
		int temp1 = calculate(operationList.get(opIdx), result, numList.get(opIdx + 1));
		dfs(temp1, opIdx + 1);
		
		// 괄호가 있는 경우
		if(opIdx + 1 < operationList.size()) {
			// result의 오른쪽에 있는 값을 연산함
			int temp2 = calculate(operationList.get(opIdx + 1), numList.get(opIdx + 1), numList.get(opIdx + 2));
			// 현재 result와 방금구한 오른쪽 괄호 연산한 값을 다시 연산자와 넘겨서 게산함
			dfs(calculate(operationList.get(opIdx), result, temp2), opIdx + 2);
		}
	}

}
