import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 누적합 알고리즘 이용
		int[] sumArr = new int[N+1];
		for(int i=1; i<=N; i++) {
			sumArr[i] = sumArr[i-1] + i;
		}
		
		// 투포인터 알고리즘 이용
		int startIdx = 0;	// 시작 인덱스
		int endIdx = 0;	// 끝 인덱스
		int count = 0;
		
		// 끝 인덱스가 N이하일때까지 반복
		while(endIdx <= N) {
			int num = sumArr[endIdx] - sumArr[startIdx];
			// 해당 숫자(자연수의 합)가 N인 경우
			if(num == N) {
				count++;	// 자연수의 합으로 나타내는 가지수 증가
				endIdx++;	// 끝 인덱스 증가
			}
			
			// 해당 숫자(자연수의 합)이 N보다 작은 경우 
			if(num < N) {
				endIdx++;	// 끝 인덱스 증가
			}
			// 해당 숫자(자연수의 합)이 N보다 큰 경우
			else if(num > N) {
				startIdx++;	// 시작 인덱스 증가
			}
		}
		System.out.println(count);

	}

}
