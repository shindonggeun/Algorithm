import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] A_arr= new int[N];
			
			for(int i=0; i<N; i++) {
				A_arr[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			int[] B_arr = new int[M];
			
			for(int i=0; i<M; i++) {
				B_arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int result = 0;	// 최대값
			
			// N이 큰 경우 B를 움직이면서 A의 배열 요소와 곱해주면 된다
			if(N > M) {
				for(int i=0; i<=N-M; i++) {
					int temp = 0;
					for(int j=0; j<M; j++) {
						temp += A_arr[i+j] * B_arr[j];
					}
					result = Math.max(result, temp);
				}
			}
			// M이 큰 경우 A를 움직이면서 B의 배열 요소와 곱해주면 된다
			else if(N < M) {
				for(int i=0; i<=M-N; i++) {
					int temp = 0;
					for(int j=0; j<N; j++) {
						temp += A_arr[j] * B_arr[i+j];
					}
					result = Math.max(result, temp);
				}
			}
			// 둘다 같은 경우 A의 배열요소와 B의 배열요소 각각 곱해주면 된다
			else {
				int temp = 0;
				for(int i=0; i<N; i++) {
					temp += A_arr[i] * B_arr[i];
				}
				result = Math.max(result, temp);
			}
			
			sb.append("#" + tc + " " + result).append("\n");
		}
		System.out.print(sb);

	}

}
