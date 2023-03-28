import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;	// L
		int right = 0;	// R
		int sum = 0;	// 부분배열에서의 합
		int count = 0;	// 원하는 합의 개수
		
		// 투 포인터 알고리즘 이용
		// left가 N보다 작을때까지 반복(시작점)
		while(left < N) {
			
			// L ~ R 까지의 부분배열의 합이 M보다 크거나 right가 배열의 끝부분에 도달하면 Left 증가
			// M보다 sum이 크거나 right가 배열 끝부분에 도달하면 
			// 값을 줄여서 M을 맞춰야 하므로 현재 right 인덱스에 해당하는 배열 값을 빼고 한칸 앞으로 이동한다
			if(sum > M || right == N) {
				sum-=arr[left++];
			}
			// 위의 경우를 제외한 나머지 경우는 끝점(right) 증가시킴
			else {
				sum+=arr[right++];
			}
			
			// 수열에서 합이 찾아야 하는 값과 같은경우 count 증가(경우의 수)
			if(sum == M) {
				count++;
			}
		}
		
		System.out.println(count);
	}

}