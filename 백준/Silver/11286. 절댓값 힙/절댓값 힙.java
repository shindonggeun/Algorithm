import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 우선순위큐는 낮은값이 우선순위 높음(최소힙) -> 이것을 커스텀 정렬해줄거임
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				int absX = Math.abs(x);
				int absY = Math.abs(y);
				
				// 두 절대값이 같은 경우
				if(absX == absY) {
					// 문제 조건에서 절대값이 같은게 여러개 있는 경우 가장 작은 수를 출력하라고 했으므로
					return x - y;	// 오름차순 정렬 
				}
				// 위의 경우가 아닌 경우 (두 절대값이 같지 않은 경우)
				return absX - absY;	// 절대값 기준으로 오름차순 정렬
			}
			
		});	// 우선순위 큐 생성
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());	// 정수 입력
			// 입력값이 0이 아닌 경우 정수를 우선순위큐에 집어넣어줌
			if(num != 0) {
				pq.add(num);
			}
			// 입력값이 0인 경우
			else {
				// 우선순위 큐 사이즈가 0이 아닌 경우
				if(pq.size() != 0) {
					// StringBuilder에 우선순위큐에서 정수 빼낸 것 저장
					sb.append(pq.poll()).append("\n");
				}
				// 우선순위 큐 사이즈가 0인 경우(뽑을 숫자 없는 경우)
				else {
					// StringBuilder에 0 저장해줌
					sb.append(0).append("\n");
				}
			}
		}
		System.out.print(sb);
		
		

	}

}
