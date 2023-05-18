import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		String input = br.readLine();
		double[] arr = new double[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Double.parseDouble(st.nextToken());
		}
		
		double result = 0;
		// 후위표기식은 스택을 이용한다
		Stack<Double> stack = new Stack<>();
		for(int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			if('A' <= ch && ch <= 'Z') {
				stack.push(arr[ch - 'A']);
			}
			else {
				if(!stack.isEmpty()) {
					double firstNum = stack.pop();
					double secondNum = stack.pop();
					
					if(ch == '+') {
						result = secondNum + firstNum;
						stack.push(result);
					}
					else if(ch == '-') {
						result = secondNum - firstNum;
						stack.push(result);
					}
					else if(ch == '*') {
						result = secondNum * firstNum;
						stack.push(result);
					}
					else if(ch == '/') {
						result = secondNum / firstNum;
						stack.push(result);
					}
				}
			}
		}
		
		double answer = stack.pop();
		// 소수점 둘쨰자리까지만 출력이므로 printf() 메서드를 이용하여 "%.2f"를 매개 변수로 넣어서 출력
		// 단순 출력이다 (소수점 올림, 버림, 반올림 이런거 아니다!)
		System.out.printf("%.2f" , answer);
	}

}
