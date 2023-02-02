import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());	// testCase 입력값 저장
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스수만큼 순회
		for(int i=0; i<testCase; i++) {
			Set<Integer> set = new HashSet<>();	// 중복값 허용 안하도록 자료구조 set 이용
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			// 수첩1에 적힌 숫자들 set에 저장
			for(int j=0; j<N; j++) {
				int input = Integer.parseInt(st.nextToken());
				set.add(input);
			}
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			// 수첩2 부분
			for(int j=0; j<M; j++) {
				int input = Integer.parseInt(st.nextToken());
				// 입력값이 수첩1에 있는 경우 1표시
				if(set.contains(input)) {
					sb.append("1").append("\n");
				}
				// 입력값이 수첩1에 없는 경우
				else {
					sb.append("0").append("\n");
				}
			}
		}
		System.out.print(sb);
	}

}