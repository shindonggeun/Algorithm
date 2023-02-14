import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		// key: 단어의 첫번째 글자 + 단어의 두번째 글자부터 마지막 글자를 정렬해서 저장한 값, value: 해당 단어
		Map<String, String> map = new HashMap<>();	
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String word = st.nextToken();
			// 단어 길이가 3 이상인것만 map에 저장함
			if(word.length() > 3) {
				char[] arr = word.toCharArray();
				Arrays.sort(arr, 1, arr.length-1);
				map.put(new String(arr), word);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<M; i++) {
			String word = st.nextToken();
			if(word.length() > 3) {
				char[] arr = word.toCharArray();
				Arrays.sort(arr, 1, arr.length-1);	// 해당 단어 두번째 글자부터 맨 마지막글자를 정렬
				String result = map.get(new String(arr));	// 단어의 첫 글자 + 두번째 ~ 맨마지막 글자 정렬한것
				sb.append(result).append(" ");
			}
			// 단어 길이가 3이하인 것들은 그대로 출력 (단어의 맨 처음 글자와 맨 마지막글자는 고정이므로)
			else {
				sb.append(word).append(" ");
			}
		}
		System.out.print(sb);

	}

}