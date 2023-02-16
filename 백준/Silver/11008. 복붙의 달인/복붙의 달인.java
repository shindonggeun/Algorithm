import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();	// 입력값 문자열 s
			String compare = st.nextToken();	// 복붙할 문자열 p
			int count = 0;	// 타이핑하는데 걸리는 최소 시간 변수
			
			// 입력값 문자열 s에서 더이상 복붙할 문자열이 없을때까지 반복
			while(input.indexOf(compare) != -1) {
				// 입력값 문자열에서 복붙할 문자열을 빈 문자열로 대체 (복붙했으니 타이핑에서 없애야하므로)
				input = input.replaceFirst(compare, "");	
				count++;	// 타이핑 증가
			}
			// 복붙할 문자열 위에서 다 없앴으므로 이제 한글자씩 타이핑 해주기 위해 문자열 한글자씩 탐색
			for(int j=0; j<input.length(); j++) {
				count++;
			}
			
			sb.append(count).append("\n");
		}
		System.out.print(sb);
		

	}

}