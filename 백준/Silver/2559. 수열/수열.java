import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		int sum = 0;
	
		// 슬라이딩 윈도우 알고리즘 방식 이용
		for(int i=0; i<N; i++) {
			sum+=arr[i];
			
			// 최초에 나온 합을 최대값으로 잡아놓음
			if(i == K - 1) {
				max = sum;
			}
			
			// 처음 길이 벗어났을 때 부터 한칸씩 밀어주면서 최대값 비교
			if(i >= K) {
				sum-=arr[i-K];
				max = Math.max(max, sum);
			}
		}
		System.out.println(max);
		

	}

}