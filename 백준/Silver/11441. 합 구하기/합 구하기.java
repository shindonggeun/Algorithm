import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 누적합 알고리즘 이용
		int[] sumArr = new int[N+1];	
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			sumArr[i] = sumArr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int startIdx = Integer.parseInt(st.nextToken());	// 시작구간 입력
			int endIdx = Integer.parseInt(st.nextToken());		// 끝 구간 입력
			
			// 누적합 배열에서 끝 구간에서 시작구간-1 한것을 빼주면 된다
			int result = sumArr[endIdx] - sumArr[startIdx-1];
			sb.append(result).append("\n");
		}
		System.out.print(sb);

	}

}
