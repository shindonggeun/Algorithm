import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 자연수 N
	static boolean[] isPrime; // 소수 여부를 판별하는 배열
	static List<Integer> primeList; // 자연수 N 범위 내 소수들을 저장하는 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		isPrime = new boolean[N+1]; // [0] ~ [N]
		primeList = new ArrayList<>(); // 리스트 초기화
		
		sieveOfEratosthenes(); // 에라토스테네스의 체 메서드 호출
		
		// 숫자 2부터 N까지 탐색
		for (int i=2; i<=N; i++) {
			// 해당 숫자가 소수인 경우
			if (isPrime[i]) {
				primeList.add(i); // 리스트에 추가
			}
		}
		
		// 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하기 위한 메서드 호출
		int count = calculateContinuousPrimeSumCount();
		
		System.out.println(count);
	}
	
	// 에라토스테네스의 체를 이용하여 소수들을 판별하는 메서드
	public static void sieveOfEratosthenes() {
		Arrays.fill(isPrime, true); // 소수 여부를 판별하는 배열 각 원소들 true로 초기화
		isPrime[0] = false; // 숫자 0은 소수가 아니므로 false
		isPrime[1] = false; // 숫자 1은 소수가 아니므로 false
		
		// 에라토스테네스의 체 실행
		// true = 소수, false = 소수 아님
		for (int i=2; i*i<=N; i++) {
			// 해당 수가 소수인 경우
			if (isPrime[i]) {
				// 해당하는 수의 배수는 모두 소수가 아니므로 false로 바꿔주도록 함
				for (int j=i*i; j<=N; j+=i) {
					isPrime[j] = false;
				}
			}
		}
	}
	
	// 에라토스테네스의 체를 이용해서 소수가 판별된 숫자들을 이용해서 N 범위내의 연속된 소수들의 합의 경우의 수를 구하는 메서드  
	public static int calculateContinuousPrimeSumCount() {
		int count = 0; // 연속된 소수의 합으로 나타낼 수 있는 경우의 수
		// 투포인터 알고리즘 이용
		int left = 0; // 왼쪽 포인터 초기화
		int right = 0; // 오른쪽 포인터 초기화
		int sum = 0; // 연속된 소수의 합 초기화
		
		// 오른쪽 포인터가 소수 리스트 범위 내 인덱스를 가리킬 때까지 반복
		while (right < primeList.size()) {
			// 연속된 소수의 합이 N보다 작은 경우
			if (sum < N) {
				// 오른쪽 포인터가 가리키는 소수를 더해준 뒤 오른쪽 포인터 증가
				sum += primeList.get(right++);
			}
			// 연속된 소수의 합이 N보다 큰 경우
			else if (sum > N) {
				// 왼쪽 포인터가 가리키는 소수를 빼준 뒤 왼쪽 보인터 증가
				sum -= primeList.get(left++);
			}
			// 연속된 소수의 합이 N인 경우
			else {
				count++; // 경우의 수 증가
				// 다음 합을 구하기 위해 오른쪽 포인터가 가리키는 소수를 더해준 뒤 왼쪽 포인터가 가리키는 소수를 빼줌
				// 그 다음 오른쪽 포인터, 왼쪽 포인터 둘다 증가
				sum += (primeList.get(right++) - primeList.get(left++));
			}
		}
		
		// 마지막 구간 체크
		// 남은 합이 N보다 크거나 같은 경우이면서 동시에 왼쪽 포인터가 소수 리스트 범위 내 인덱스를 가리킬 때까지 반복
		while (sum >= N && left < primeList.size()) {
			// 연속된 소수의 합이 N이 된 경우
			if (sum == N) {
				count++;  // 경우의 수 증가
			}
			// 왼쪽 포인터가 가리키는 소수를 합에서 빼준 뒤 왼쪽 포인터 증가
			sum -= primeList.get(left++);
		}
		
		// 해당 경우의 수 반환
		return count;
	}

}