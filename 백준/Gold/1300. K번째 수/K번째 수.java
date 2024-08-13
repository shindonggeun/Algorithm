import java.io.*;

public class Main {

	static long N; // 배열의 크기
	static int K; // 찾고자하는 K 번째 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Long.parseLong(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		// 이분 탐색 알고리즘 시작 
		long start = 1; // 이분 탐색의 시작점 (배열 1부터 시작)
		long end = N * N; // 이분 탐색의 끝점 (N * N 값이 배열에 가질 수 있는 최대값)
		long result = 0; // K번째 수를 저장할 결과값 변수
		
		while (start <= end) {
			long mid = (start + end) / 2; // 중간값 설정 (현재 탐색 중인 범위의 중간 지점)
			long count = 0; // 중간값 이하의 수가 몇 개인지 세기 위한 변수
			
			// 배열에서 i부터 N까지 반복 (행 반복)
			for (int i=1; i<=N; i++) {
				// i행에서 mid 이하의 값의 개수를 구하여 count에 누적
				// i행의 값은 1, 2*i, ... , N*i가 있으므로 mid / i가 그 행에서의 개수를 의미함
				// 다만 이 값이 N을 넘을 수 없으므로 mid / i와 N중 최소값을 누적
				count += Math.min(mid / i, N);
			}
			
			// 해당 중간값 이하의 개수가 K보다 작은 경우
			if (count < K) {
				start = mid + 1; // 더 큰 값을 찾기 위해 시작점 조정 (하한 조정)
			}
			// 해당 중간값 이하의 개수가 K보다 크거나 같은 경우
			else {
				end = mid - 1; // 더 작은 값을 찾기 위해 끝점 조정 (상한 조정)
				result = mid; // 결과값 mid로 갱신
			}
		}
		
		System.out.println(result);
	}

}