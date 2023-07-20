import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = 1;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());	// 캠핑장 L일 동안만 사용 가능
			int P = Integer.parseInt(st.nextToken());	// 캠핑장 연속하는 P일 중
			int V = Integer.parseInt(st.nextToken());	// 휴가 V일동안 주어짐
			
			// 모두다 0 입력되면 종료
			if(L == 0 && P == 0 && V == 0) {
				break;
			}
			
			int totalDay = L * (V / P) + Math.min(L, V % P);	// 공식화
			sb.append("Case " + testCase).append(": ").append(totalDay).append("\n");
			testCase++;	// 다음 테스트케이스로
		}
		System.out.print(sb);

	}

}
