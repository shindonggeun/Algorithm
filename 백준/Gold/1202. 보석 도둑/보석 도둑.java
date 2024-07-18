import java.util.*;
import java.io.*;

public class Main {

	static class Jewelry {
		int weight;
		int cost;
		
		public Jewelry(int weight, int cost) {
			this.weight = weight;
			this.cost = cost;
		}
	}
	
	static int N; // 보석의 개수
	static int K; // 가방의 개수
	static List<Jewelry> jewelryList;
	static int[] bagArr; // 가방의 무게를 담은 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewelryList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			jewelryList.add(new Jewelry(weight, cost));
		}
		
		bagArr = new int[K];
		for (int i=0; i<K; i++) {
			bagArr[i] = Integer.parseInt(br.readLine());
		}

		Collections.sort(jewelryList, (a, b) -> {
			if (a.weight == b.weight) {
				return b.cost - a.cost;
			}
			return a.weight - b.weight;
		});
		Arrays.sort(bagArr);
		
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		long totalCost = 0;
		int idx = 0;
		
		for (int i=0; i<K; i++) {
			while (true) {
				if (idx >= N) {
					break;
				}
				
				Jewelry jewelry = jewelryList.get(idx);
				
				if (bagArr[i] < jewelry.weight) {
					break;
				}
				
				pq.add(jewelry.cost);
				idx++;
			}
			
			if (!pq.isEmpty()) {
				totalCost += pq.poll();
			}
		}
		
		System.out.println(totalCost);
	}

}
