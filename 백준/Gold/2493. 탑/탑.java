import java.util.*;
import java.io.*;

public class Main {
	
	static class Top {
		int height;	// 탑의 높이
		int idx;	// 탑의 번호(인덱스)
		
		public Top(int height, int idx) {
			this.height = height;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Top> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			int height = Integer.parseInt(st.nextToken());	// 높이 입력받기
			// 스택이 비어있지 않을때까지 반복
			while(!stack.isEmpty()) {
				Top top = stack.peek();		// stack에 저장된 최상단 값(탑의 정보) 불러오기
				// 스택에 저장된 탑의 높이가 입력된 높이보다 크거나 같은 경우
				if(top.height >= height) {	
					sb.append(top.idx).append(" ");	// 스택에 저장된 탑의 인덱스(번호)를 출력할 수 있게끔 StringBuilder에 저장
					break;	// 반복문 빠져나옴
				}
				stack.pop();	// 스택에 저장된 최상단 값(탑의 정보) 빼내기
			}
			// 스택이 비어있는 경우
			if(stack.isEmpty()) {
				stack.push(new Top(height, i));	// 스택에 탑의 정보 저장해주기(높이, 인덱스(번호))
				sb.append("0").append(" ");	
			}
			stack.push(new Top(height, i));	// 스택에 탑의 정보 집어넣기
		}
		System.out.println(sb);

	}

}
