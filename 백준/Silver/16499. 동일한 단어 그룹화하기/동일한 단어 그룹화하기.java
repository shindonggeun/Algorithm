import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();		// 중복허용 X
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			
			char[] alpabet = input.toCharArray();	// 입력값 char형 배열로 쪼개서 넣어줌
			Arrays.sort(alpabet);	// 입력값 각각 알파벳으로 쪼갠거 오름차순 정렬
			input = new String(alpabet);	// 각각 알파벳으로 쪼갠거 다시 문자열로 합쳐줌
			set.add(input);
		}
		System.out.println(set.size());

	}

}