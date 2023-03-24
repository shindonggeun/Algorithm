import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());	// K번째 사람 제거
		
		Queue<Integer> queue = new LinkedList<>();	// 사람들이 원을 이루면서 앉아있으므로 큐 이용(FIFO)
		
		// 1번부터 N번까지 큐 집어넣기
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		int start = 1;	// 시작순서 (1번부터 시작)
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		// 큐가 비어있지 않을때까지 반복
		while(!queue.isEmpty()) {
			// K번째 순서가 온 경우	그 순서의 사람 제거
			if(start == K) {
				if(queue.size() == 1) {
					sb.append(queue.poll() + ">");
				}
				else {
					sb.append(queue.poll() + ", ");
				}
				start = 0;	// 순서 초기화
			}
			// K번째 아닌 경우 맨 뒤로 보내줌 (원형 만들기 위해)
			else {
				int num = queue.poll();
				queue.add(num);
			}
			start++;
		}
		System.out.println(sb);
		
	}

}