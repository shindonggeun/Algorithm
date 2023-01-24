import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();	// key: 사람 이름, value: 명단에 몇번 불렸는지(듣도 못한 사람 명단, 보도 못한 사람)
		List<String> list = new ArrayList<>();	// 명단에 2번이상 불린 사람 리스트
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			map.put(s, map.getOrDefault(s, 0) + 1);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			map.put(s, map.getOrDefault(s, 0) + 1);
			if(map.get(s) == 2) {
				list.add(s);
			}
		}
		
		Collections.sort(list);	// 명단 2번이상 불린사람 리스트 오름차순 정렬
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		
		for(String str : list) {
			sb.append(str).append("\n");
		}
		
		System.out.println(sb);
		
		/*List<String> keySet = new ArrayList<>(map.keySet());
		Collections.sort(keySet);	// ArrayList 오름차순 정렬
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		for(String key: keySet) {
			if(map.get(key) == 2) {
				sb.append(key).append("\n");
				count++;
			}
		}
		System.out.println(count);
		System.out.println(sb);*/
		
		//System.out.println(map);
		
	}

}