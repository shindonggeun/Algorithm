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
			// cnt가 1인 경우는 유전자에서 맨 왼쪽 알파벳이 자기 자신밖에 없는 경우이다
			if(cnt == 1) {
				for(int i=0; i<N; i++) {
					// 자기 자신과 유전자 표현형을 조합해서 비교할 필요가 없으므로 건너뜀
					if(genes[i].charAt(0) == ch) {
						continue;
					}
					
					// 유전자의 두 형질 비교해서 알파벳 큰 경우 유전자 표현형에 담음
					if(ch <= genes[i].charAt(1)) {
						set.add(genes[i].charAt(1));
					}
					else {
						set.add(ch);
					}
				}
			}
			// cnt > 1인 경우는 유전자에서 맨 왼쪽 알파벳이 중복되는 경우이다. 
			// ex) AA, AB 이렇게 각 유전자 맨 왼쪽 알파벳 A 2개
			else if(cnt > 1) {
				for(int i=0; i<N; i++) {
					// 유전자의 두 형질 비교해서 알파벳 큰 경우 유전자 표현형에 담음
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