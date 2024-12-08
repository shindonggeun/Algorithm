import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 전자기기의 개수
	static int M; // 콘센트의 개수
	static int[] times; // 각 전자기기의 충전 시간을 저장할 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		times = new int[N]; // [0] ~ [N-1]
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(times); // 충전 시간 오름차순 정렬 (충전 시간 낮은순으로 정렬)
		
		// 최소힙을 가지는 우선순위 큐 선언 및 생성
		// 현재 충전이 끝나는 시간을 관리해주도록 함
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int resultTime = 0; // 작업 완료까지의 최소 시간을 저장할 변수
		
		// 충전 시간이 가장 큰 작업부터 처리하게끔 배열 뒤에서부터 탐색
		for (int i=times.length-1; i>=0; i--) {
			
			// 우선순위 큐의 크기가 콘센트의 개수(M) 이상인 경우
			if (pq.size() >= M) {
				resultTime = pq.poll(); // 가장 빨리 끝나는 작업을 꺼내 결과 시간에 저장
			}
			
			pq.add(resultTime + times[i]); // 현재 작업을 결과 시간에 더해 우선순위 큐에 저장
		}
		
		// 우선순위 큐 빌때까지 반복
		// 우선순위 큐에 남아있는 작업 중 가장 큰 값이 최종 완료 시간 
		while (!pq.isEmpty()) {
			resultTime = pq.poll();
		}
		
		System.out.println(resultTime);
	}

}