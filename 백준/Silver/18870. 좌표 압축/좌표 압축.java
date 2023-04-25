import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] originalArr = new int[N];
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();	// key: 해당 숫자, value: 순위(즉 인덱스)
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			originalArr[i] = num;
			list.add(num);
		}
		
		Collections.sort(list);		// 오름차순 정렬
		
		int rank = 0;
		for(int i: list) {
			if(!map.containsKey(i)) {
				map.put(i, rank);
				rank++;	// map에 넣고나면 다음 순위를 가리킬 수 있도록 +1 더해줌
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i: originalArr) {
			sb.append(map.get(i) + " ");
		}
		System.out.println(sb);
		
	}

}