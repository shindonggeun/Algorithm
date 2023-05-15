import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		int start = 1;	// 시작순서 1번부터
		sb.append("<");
		while(!queue.isEmpty()) {
			if(start == K) {
				if(queue.size() == 1) {
					sb.append(queue.poll() + ">");
				}
				else {
					sb.append(queue.poll() + ", ");
				}
				start = 0;	// 순서 다시 초기화
			}
			// 삭제할 순서가 아닌 경우
			else {
				// 큐에 빼낸 숫자(사람) 다시 큐에 넣어준다 (원을 만들기 위해)
				int number = queue.poll();	
				queue.add(number);
			}
			start++;	// 순서 증가
		}
		System.out.println(sb);

	}

}
