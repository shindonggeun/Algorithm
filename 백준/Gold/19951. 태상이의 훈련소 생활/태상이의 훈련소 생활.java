import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] heightArr;
	static int[] diffArr; // 차분 배열 이용 (누적합 응용)
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		heightArr = new int[N+1];
		diffArr = new int[N+2];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			heightArr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			diffArr[a] += k;
			diffArr[b+1] -= k;
		}
		
		result = new int[N+1];
		
		// 차분배열에서 누적합을 이용하여 최종 높이 계산
		int sum = 0;
		for (int i=1; i<=N; i++) {
			sum += diffArr[i];
			result[i] = heightArr[i] + sum;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.println(sb);
	}

}