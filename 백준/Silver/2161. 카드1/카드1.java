import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 카드 N장 입력
		
		Queue<Integer> queue = new LinkedList<>();	// 카드들을 저장할 큐 (제일 위에서 제일 아래로)
		
		// 1번카드부터 N번 카드까지 큐에 저장
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			int cardNum = queue.poll();	// 제일 위에 있는 카드 번호 뽑음 
			
			sb.append(cardNum).append(" ");
			
			// 저장된 카드들이 없으면 (카드 다 뽑은 경우)
			if(queue.size() == 0) {
				break;	// while문 빠져나옴
			}
			// 저장된 카드들이 있는 경우(즉, 빼낼 카드 아직 있음)
			else {
				int cardNum2 = queue.poll();	// 제일 위에 있는 카드 뽑은 뒤
				queue.add(cardNum2);	// 제일 아래로 옮김
			}
		}
		System.out.println(sb);

	}

}
