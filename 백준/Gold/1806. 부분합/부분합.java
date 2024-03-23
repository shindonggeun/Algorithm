import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int S;
	static int[] numArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		numArr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투포인터 알고리즘 이용
		int leftIdx = 0;	// 왼쪽 인덱스
		int rightIdx = 0;	// 오른쪽 인덱스
		// 연속된 수들의 부분합 중 그 합이 S 이상이 될 때의 가장 짧은 길이 (결과값)
		int minLength = Integer.MAX_VALUE;	
		int sum = 0;	// 연속된 수들의 부분합 (누적합)
		int tempLength = 0;	 // 연속된 수들의 부분합 중 그 합이 S 이상이 될 때의 길이
		
		// 오른쪽 인덱스가 끝까지 탐색할 수 있도록
		while (rightIdx <= N) {
			// 연속된 수들의 부분합이 S 미만인 경우
			if (sum < S) {
				sum += numArr[rightIdx];	// 누적합
				rightIdx++;	// 오른쪽 인덱스 증가
			}
			// 연속된 수들의 부분합이 S 이상인 경우
			else {
				sum -= numArr[leftIdx];	// 누적합에서 왼쪽 인덱스가 가리키는 값 빼줌
				leftIdx++;	// 왼쪽 인덱스 증가
				tempLength = rightIdx - leftIdx + 1;	// 길이 구하기
				minLength = Math.min(minLength, tempLength);	// 가장 짧은 길이 갱신
			}
		}
		
		// 연속된 수들의 부분합 중 그 합이 S 이상이 되는거 못만드는 경우
		if (minLength == Integer.MAX_VALUE) {
			System.out.println(0);
		}
		else {
			System.out.println(minLength);
		}
	}

}
