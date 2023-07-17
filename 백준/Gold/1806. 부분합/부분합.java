import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] numArr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투포인터 알고리즘 이용
		int minLength = Integer.MAX_VALUE;
		int left = 0;
		int right = 0;
		int sum = 0;
		int tempLength = 0;
		
		while(right <= N) {
			// 합이 S 이상인 경우
			if(sum >= S) {
				sum -= numArr[left++]; 
				tempLength = right - left + 1;	// 길이를 구하기
				minLength = Math.min(minLength, tempLength);	// 길이 최소값 갱신
			}
			else if(sum < S) {
				sum += numArr[right++];
			}
		}
		if(minLength == Integer.MAX_VALUE) {
			System.out.println(0);
		}
		else {
			System.out.println(minLength);
		}

	}

}
