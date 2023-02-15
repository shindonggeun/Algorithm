import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			// 입력값 0이 아닌 경우 Stack에 입력값 집어넣음
			if(num!=0) {
				stack.push(num);
			}
			// 입력값이 0인 경우 Stack에서 최상단값 빼냄
			else {
				stack.pop();
			}
		}
		
		int sum = 0;
		for(int i: stack) {
			sum+=i;
		}
		System.out.println(sum);
	}

}