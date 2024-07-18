import java.util.*;
import java.io.*;

public class Main {

	// 보석의 정보를 담고 있는 내부 클래스
	static class Jewelry {
		int weight; // 무게
		int cost; // 가격
		
		public Jewelry(int weight, int cost) {
			this.weight = weight;
			this.cost = cost;
		}
	}
	
	static int N; // 보석의 개수
	static int K; // 가방의 개수
	static Jewelry[] jewelryArr; // 보석들의 정보를 저장한 배열
	static int[] bagArr; // 가방의 무게를 담은 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewelryArr = new Jewelry[N]; // [0] ~ [N-1]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			jewelryArr[i] = new Jewelry(weight, cost);
		}
		
		bagArr = new int[K]; // [0] ~ [K-1]
		
		for (int i=0; i<K; i++) {
			bagArr[i] = Integer.parseInt(br.readLine());
		}
		
		// 보석들의 정보를 담은 배열 커스텀 정렬
		Arrays.sort(jewelryArr, (a, b) -> {
			// 보석들의 무게가 같은 경우
			if (a.weight == b.weight) {
				// 2순위인 보석의 가격을 기준으로 내림차순 정렬 (가격이 높은 순으로)
				return b.cost - a.cost;
			}
			// 1순위인 보석의 무게를 기준으로 오름차순 정렬 (무게가 낮은 순으로)
			return a.weight - b.weight;
		});
		
		Arrays.sort(bagArr); // 가방의 최대 무게순으로 오름차순 정렬 (무게가 낮은 순으로)
		
		// 보석의 가격을 저장하기 위해 우선순위 큐 생성 및 선언 (가격 내림차순)
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		long totalCost = 0; // 총 가격
		int idx = 0; // 보석 배열의 인덱스를 관리할 변수
		
		// 각 가방에 대해 반복
		for (int i=0; i<K; i++) {
			// 보석의 정보를 담은 해당 배열의 인덱스가 N보다 작으면서 동시에 해당 보석의 무게가 현재 가방의 무게보다 작거나 같은 경우
			// 즉, 현재 가방의 무게 한도 내에서 담을 수 있는 보석인 경우
			while (idx < N && jewelryArr[idx].weight <= bagArr[i]) {
				pq.add(jewelryArr[idx].cost); // 우선순위 큐에 해당 보석의 가격 저장
				idx++; // 해당 인덱스 증가
			}
            
            // 우선순위 큐가 비어있지 않은 경우
            if (!pq.isEmpty()) {
            	// 우선순위 큐에서 뽑아낸 보석의 가격을 총 가격에 누적
            	// 즉, 가장 비싼 보석의 가격을 뽑아냄
                totalCost += pq.poll();
            }
		}
		
		System.out.println(totalCost);
	}

}