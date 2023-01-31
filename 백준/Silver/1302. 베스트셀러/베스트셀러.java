import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();	// key: 책 이름, value: 책 팔린 수
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String book = st.nextToken();
			
			map.put(book, map.getOrDefault(book, 0) + 1);
		}
		
		List<Integer> value_list = new ArrayList<>(map.values());		// value값들 리스트에 저장
		List<String> book_list = new ArrayList<>(map.keySet());			// key값들을 리스트에 저장
		Collections.sort(book_list);	// 오름차순 정렬
		
		StringBuilder sb = new StringBuilder();
		
		for(String key: book_list) {
			// value값이 max값인 경우(제일 책 많이 팔린 경우)
			if(map.get(key) == Collections.max(value_list)) {
				sb.append(key);
				break;
			}
		}
		System.out.println(sb);
		

	}

}