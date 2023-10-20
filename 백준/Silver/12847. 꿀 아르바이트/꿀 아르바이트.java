import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		// 슬라이딩 윈도우 알고리즘 이용
		long sum = 0;
		for(int i=0; i<M; i++) {
			sum += arr[i];
		}
		
		long max = sum;
		for(int i=0; i+M<N; i++) {
			sum-=arr[i];
			sum+=arr[i+M];
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

}
