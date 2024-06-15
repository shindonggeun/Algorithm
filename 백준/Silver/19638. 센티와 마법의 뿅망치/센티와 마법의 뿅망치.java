import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 인구수
	static int H; // 센티의 키
	static int T; // 뿅망치 횟수 제한
	static PriorityQueue<Integer> pq; // 우선순위 큐 선언
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 우선순위 큐 생성 (값이 높은게 우선순위 높음)
		pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i=0; i<N; i++) {
			// 해당 키 입력받아서 우선순위 큐에 저장
			int height = Integer.parseInt(br.readLine());
			pq.add(height);
		}
		
		int count = 0; // 뿅망치 사용 횟수
		
		// 뿅망치 횟수 제한까지 탐색
		for (int i=0; i<T; i++) {
			// 우선순위 큐에 저장된 최대 키 뽑아냄
			int maxHeight = pq.poll();
			// 최대 키가 센티의 키보다 작은 경우
			if (maxHeight < H) {
				// 해당 키 우선순위 큐에 다시 저장
				pq.add(maxHeight);
				break; // 반복문 빠져나옴
			}
			
			int changeHeight = maxHeight / 2; // 뿅망치 사용해서 해당 최대 키 절반으로 줄임 
			count++; // 뿅망치 사용 횟수 증가
			
			// 뿅망치 사용해서 줄인 키가 0인 경우
			if (changeHeight == 0) {
				// 우선순위 큐에 1 저장 (뿅망치 사용하기 전 키가 1인 경우므로 더 줄어들 수 없음)
				pq.add(1);
				break; // 반복문 빠져나옴
			}
			
			// 우선순위 큐에 뿅망치 사용한 키 저장
			pq.add(changeHeight);
		}
		
		// 우선순위 큐에서 저장된 최대 키 뽑아냄
		int maxHeight = pq.poll();
		
		// 해당 최대 키가 센티의 키보다 작은 경우
		if (maxHeight < H) {
			// 뿅망치 사용 횟수 출력
			System.out.println("YES");
			System.out.println(count);
		}
		// 해당 최대 키가 센티의 키보다 크거나 같은 경우
		else {
			// 해당 최대 키 출력
			System.out.println("NO");
			System.out.println(maxHeight);
		}
	}

}