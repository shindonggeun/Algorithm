import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];	// 신호등 배열 (고장난건 1, 정상 신호등 0)
		
		for(int i=1; i<=B; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());	// 고장난 신호등 번호 입력
			arr[num] = 1;	// 고장난 신호등은 배열에서 1값 저장
		}
		
		// 슬라이딩 윈도우 알고리즘 이용
		int sum = 0;
		for(int i=1; i<=K; i++) {
			sum+=arr[i];
		}
		int min = sum;
		for(int i=K+1; i<=N; i++) {
			sum+=arr[i];
			sum-=arr[i-K];	// 슬라이딩 윈도우에서 핵심코드(구간 한칸씩 밀어주기)
			min = Math.min(min, sum);	
		}
		System.out.println(min);

	}

}
