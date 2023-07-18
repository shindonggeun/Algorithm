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
		
		// 투포인터 알고리즘 이용
		int left = 0;
		int right = 0;
		int[] count = new int[100001];	// 0 ~ 100000 숫자들의 나온 횟수를 저장해주는 배열
		int maxLength = 0;
		
		while(left <= right) {
			if(right <= N-1 && count[arr[right]] < K) {
				count[arr[right]]++;	// 해당 오른쪽 포인터가 가리키는 수의 횟수를 증가
				right++;				// 오른쪽 포인터 증가 (다음 수를 가리키도록)
			}
			// 해당 오른쪽 포인터가 가리키는 수의 횟수가 K인 경우
			else if(count[arr[right]] == K) {
				count[arr[left]]--;	//
				left++;
			}
			
			maxLength = Math.max(maxLength, right - left);	// 최장연속부분수열 길이 최신화
			// 오른쪽 포인터가 N인 경우(즉 가리키는 곳(인덱스)이 넘어버리면)
			if(right == N) {
				break;	// 빠져나옴
			}
		}
		System.out.println(maxLength);
	}

}
