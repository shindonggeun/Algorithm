import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 메시지 길이 (숫자 개수)
	static int C;
	static Map<Integer, Integer> map; // 각 숫자마다 빈도수를 저장할 map (key: 해당 숫자, value: 빈도수)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new LinkedHashMap<>(); // 순서가 보장된 HashMap 생성
		
		st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			// 해당 숫자에 대한 빈도수 저장
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		// map에 저장된 key 값들을 리스트화
		List<Integer> list = new ArrayList<>(map.keySet());
		
		// 리스트에 저장된 key값들 커스텀 정렬
		Collections.sort(list, (key1, key2) -> {
			// 맵에 저장된 빈도수를 기준으로 내림차순 정렬 (빈도수가 높은 순으로 key값 정렬)
			return map.get(key2) - map.get(key1);
		});
		
		StringBuilder sb = new StringBuilder();
		
		// key값들이 저장된 리스트 탐색
		for (int num: list) {
			// 해당 key값의 빈도수만큼 반복
			for (int i=0; i<map.get(num); i++) {
				// 해당 key값 출력할 수 있게끔 StringBuilder에 저장
				sb.append(num).append(" ");
			}
		}
		
		System.out.println(sb);

	}

}