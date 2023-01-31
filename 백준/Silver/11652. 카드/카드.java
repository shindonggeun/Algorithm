import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Map<Long, Integer> map = new HashMap<>();	// key: 숫자카드, value: 가지고있는 숫자카드 수
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			long card = Long.parseLong(st.nextToken());
			
			map.put(card, map.getOrDefault(card, 0) + 1);
		}
		
		List<Long> key_list = new ArrayList<>(map.keySet());	// key값들 list화
		Collections.sort(key_list);		// 오름차순 정렬
		
		List<Integer> value_list = new ArrayList<>(map.values());	// value값들 list화
		int max = Collections.max(value_list);	// max값 뽑기
		StringBuilder sb = new StringBuilder();
		
		// 가장 많이 가지고있는 숫자카드 뽑아내기
		for(long key: key_list) {
			if(map.get(key) == max) {
				sb.append(key);
				break;
			}
		}
		
		System.out.println(sb);

	}

}