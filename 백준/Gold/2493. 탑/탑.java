import java.util.*;
import java.io.*;

public class Main {
	
	// 탑의 정보를 담고있는 내부 클래스
	static class Top {
		int idx;	// 탑의 번호 (인덱스)
		int height;	// 탑의 높이
		
		public Top(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}
	
	static int N;
	static Stack<Top> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i=1; i<=N; i++) {
			int height = Integer.parseInt(st.nextToken());	// 현재 탑의 높이 입력받기
			
			// 스택이 비어있는 경우
			if (stack.isEmpty()) {
				sb.append("0 ");	// 0번 출력 (레이저 신호를 수신한 탑 존재 X)
				stack.push(new Top(i, height));	// 현재 탑의 정보 스택에 저장
			}
			// 스택이 비어있지 않은 경우
			else {
				while (true) {
					// 스택이 비어있는 경우
					if (stack.isEmpty()) {
						sb.append("0 ");	// 0번 출력 (레이저 신호를 수신한 탑 존재 X)
						stack.push(new Top(i, height));	// 현재 탑의 정보 스택에 저장
						break;	// 무한반복 빠져나옴
					}
					
					Top top = stack.peek();
					
					// 스택 최상단에서 뽑은 탑의 높이가 현재 높이보다 큰 경우
					if (top.height > height) {
						sb.append(top.idx).append(" ");	// 스택 최상단의 인덱스 (탑의 번호) 출력
						stack.push(new Top(i, height));	// 현재 탑의 정보 스택에 저장
						break;	// 무한반복 빠져나옴 (다음 탑 탐색)
					}
					// 스택 최상단에서 뽑은 탑의 높이가 현재 높이보다 낮거나 같은 경우
					else {
						stack.pop();	// 스택에서 최상단 값 뽑아내고 다시 반복문으로
					}
				}
			}
		}
		
		System.out.print(sb);
	}

}
