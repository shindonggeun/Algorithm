import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();	// key: 학생이름, value: 학생 인기도
		st = new StringTokenizer(br.readLine());
		
		// 학생들 이름 담기(일단 인기도 0으로 초기화)
		for(int i=0; i<N; i++) {
			String name = st.nextToken();
			map.put(name, 0);
		}
		
		// 각 라인마다 학생들 이름 나오면 인기도 1씩 증가
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), "\n");
			String str = st.nextToken();
			String[] popular = str.split(" ");
			
			for(String s: popular) {
				map.put(s, map.getOrDefault(s, 0) + 1);
			}
		}
		
		List<String> list = new ArrayList<>(map.keySet());	// 학생이름 담은 리스트
		
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String key1, String key2) {
				// 인기도 같지 않은 경우
				if(map.get(key1)!=map.get(key2)) {
					return map.get(key2) - map.get(key1);	// 인기도 높은순으로 정렬(내림차순 정렬)
				}
				// 인기도 같은 경우
				else {
					return key1.compareTo(key2);	// 이름 기준 오름차순 정렬
				}
			}
			
		});
		
		StringBuilder sb = new StringBuilder();
		for(String key: list) {
			sb.append(key + " " + map.get(key)).append("\n");
		}
		System.out.print(sb);
	}

}