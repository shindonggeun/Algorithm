import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new HashMap<>();	// key: 단어, value: 단어 나온 빈도수
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String word = st.nextToken();
			// 단어의 길이가 M 미만인 경우는 건너뜀(map에 추가 안함)
			if(word.length() < M) {
				continue;
			}
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		
		List<String> list = new ArrayList<>(map.keySet());
		
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				int c1 = map.get(o1);
				int c2 = map.get(o2);
				
				// 단어 빈도수 같은 경우
				if(c1 == c2) {
					// 단어의 길이가 같은 경우
					if(o1.length() == o2.length()) {
						return o1.compareTo(o2);	// 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치 (오름차순)
					}
					return o2.length() - o1.length();	// 해당 단어의 길이가 길수록 앞에 배치(내림차순 정렬)
				}
				// 자주 나오는 단어일수록(단어 빈도수 높을수록) 앞에 배치(단어 빈도수 내림차순 정렬)
				return c2 - c1;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(String s: list) {
			sb.append(s).append("\n");
		}
		System.out.print(sb);
		

	}

}