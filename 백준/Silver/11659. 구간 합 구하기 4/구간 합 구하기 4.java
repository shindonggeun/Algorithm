import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 수의 개수
		int M = Integer.parseInt(st.nextToken());	// 합을 구해야하는 힛수
		int[] numArr = new int[N];					// 수들을 저장하는 배열
		int[] sumArr = new int[N+1];				// 구간합을 저장한 배열
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;
		sumArr[0] = sum;
		// 구간합 저장하기
		for(int i=1; i<=N; i++) {
			sum+=numArr[i-1];
			sumArr[i] = sum;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());	// 구간 시작점
			int end = Integer.parseInt(st.nextToken());		// 구간 끝점
			int result = sumArr[end] - sumArr[start - 1];
			sb.append(result).append("\n");
		}
		System.out.print(sb);

	}

}