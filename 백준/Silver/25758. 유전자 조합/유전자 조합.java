import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String[] genes = new String[N];
		
		for(int i=0; i<N; i++) {
			genes[i] = st.nextToken();
		}
		
		Set<Character> set = new HashSet<>();	// 유전자 표현형을 담은 set (중복허용 X)
		StringBuilder sb = new StringBuilder();
		
		// 유전자들을 조합해서 나올수 있는 모든 유전자 표현형을 담기위해 브루트포스 알고리즘 사용(알파벳 모든 경우 탐색)
		for(char ch='A'; ch<='Z'; ch++) {
			int cnt = 0;
			
			for(int i=0; i<N; i++) {
				if(genes[i].charAt(0) == ch) {
					cnt++;
				}
			}
			
			if(cnt == 1) {
				for(int i=0; i<N; i++) {
					if(genes[i].charAt(0) == ch) {
						continue;
					}
					
					if(ch <= genes[i].charAt(1)) {
						set.add(genes[i].charAt(1));
					}
					else {
						set.add(ch);
					}
				}
			}
			else if(cnt > 1) {
				for(int i=0; i<N; i++) {
					
					if(ch <= genes[i].charAt(1)) {
						set.add(genes[i].charAt(1));
					}
					else {
						set.add(ch);
					}
				}
			}
		}
		
		
		List<Character> list = new ArrayList<>(set);	// 유전자 표현형 정렬하기 위해 list 사용
		Collections.sort(list);		// 오름차순 정렬
		sb.append(set.size()).append("\n");
		
		for(char ch: list) {
			sb.append(ch).append(" ");
		}
		System.out.print(sb);

	}

}