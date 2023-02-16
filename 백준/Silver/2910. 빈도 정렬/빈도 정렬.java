import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		// LinkedHashMap을 이용해서 입력된 순서 보장되게끔!
		Map<Integer, Integer> map = new LinkedHashMap<>();	// key: 숫자, value: 숫자가 등장한 횟수
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		List<Integer> list = new ArrayList<>(map.keySet());	
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer i1, Integer i2) {
				// 두 숫자의 등장한 횟수(빈도수)가 같은 경우 먼저 나온 숫자가 나오도록
				if(map.get(i1) == map.get(i2)) {
					return 0;	// 변화없음
				}
				// 두 숫자의 빈도수가 다른 경우 
				else {
					return map.get(i2) - map.get(i1); // 빈도수 높은순으로 정렬(내림차순 정렬)
				}
			}
			
		});
		
		StringBuilder sb = new StringBuilder();
		for(int key: list) {
			for(int i=0; i<map.get(key); i++) {
				sb.append(key).append(" ");
			}
		}
		System.out.print(sb);

	}

}