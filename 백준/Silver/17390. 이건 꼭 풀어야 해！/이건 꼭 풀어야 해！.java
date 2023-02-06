import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] num = new int[N];
		int[] sum = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num);	// num배열 오름차순 정렬
		
		// 각각의 구간합 구하기 (1 ~ N번 인덱스에 저장된 수)
		// ex) sum[1] -> num 배열의 0번 인덱스까지의 합
		// sum[5] -> num 배열의 4번 인덱스까지의 합
		for(int i=1;i<=N; i++) {
			sum[i] = sum[i-1] + num[i-1];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=Q; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int result = sum[R] - sum[L-1];
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}

}