import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			set.add(st.nextToken());
		}
		
		List<String> list = new ArrayList<>(set);	// set을 정렬하기 위해서는 ArrayList로 변환해줘야 함
		
		// 사용자 정의에 의해서 정렬 
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				// 두 단어 비교했을때 단어 길이가 같은 경우
				if(s1.length() == s2.length()) {
					return s1.compareTo(s2);	// 단어 오름차순 정렬 되게끔 값 return
				}
				// 단어 길이 같지 않은 경우
				else {
					// 양수이면 위치 바뀜(정렬해줌 -> 단어 맞바꿈)
					// 0이나 음수인 경우는 위치 안바뀜(정렬안하고 그대로 -> 그대로)
					return s1.length() - s2.length();	// 단어 길이 오름차순으로 정렬(짧은거순으로)
				}
			}
			
		});
		
		StringBuilder sb = new StringBuilder();
		for(String s: list) {
			sb.append(s).append("\n");
		}
		System.out.print(sb);

	}

}