import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		// 방법 1. list를 이용해서 내림차순 정렬해서 출력
		/*List<Integer> list = new ArrayList<>();	
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		Collections.sort(list, Collections.reverseOrder());	// list 내림차순 정렬
		System.out.println(list.get(N-1));*/
		
		// 방법 2. 우선순위 큐 이용
		// 우선순위 큐는 default로 우선순위가 높은것(숫자가 낮은것)부터 출력하는것이 디폴트이기때문에
		// Collections.reverseOrder()를 이용해서 우선순위가 낮은것(숫자가 높은것)부터 출력하도록 선언해줌
		// 아이패드 메서드 참고용에 정리했음
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// N-1개의 노드 삭제
		for(int i=0; i<N-1; i++) {
			pq.poll();
		}
		// 그다음으로 삭제한(N번째) 노드 출력
		System.out.println(pq.poll());
	}

}