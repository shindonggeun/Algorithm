import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;
	static int[] arr;
	static String num;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		num = st.nextToken();
		N = Integer.parseInt(num);
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[K];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		System.out.println(max);
	}
	
	public static void dfs(int result) {
		// 중복순열을 통해서 만든 결과값이 N보다 큰 경우 종료
		if(result > N) {
			return;
		}
		// 중복순열을 통해서 만든 값이 최대값보다 큰 경우
		if(max < result) {
			max = result;	// 최대값 할당
		}
		
		// 중복순열 과정
		for(int i=0; i<K; i++) {
			dfs(result*10+arr[i]);
		}
	}

}