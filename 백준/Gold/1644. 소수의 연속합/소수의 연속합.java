import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static boolean[] isPrime;
	static List<Integer> primeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		isPrime = new boolean[N+1];
		primeList = new ArrayList<>();
		
		sieveOfEratosthenes();
		
		for (int i=2; i<=N; i++) {
			if (isPrime[i]) {
				primeList.add(i);
			}
		}
		
		int count = calculateContinuousPrimeSumCount();
		
		System.out.println(count);
	}
	
	public static void sieveOfEratosthenes() {
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		
		for (int i=2; i*i<=N; i++) {
			if (isPrime[i]) {
				for (int j=i*i; j<=N; j+=i) {
					isPrime[j] = false;
				}
			}
		}
	}
	
	public static int calculateContinuousPrimeSumCount() {
		int count = 0;
		int left = 0;
		int right = 0;
		int sum = 0;
		
		while (right < primeList.size()) {
			if (sum < N) {
				sum += primeList.get(right++);
			}
			else if (sum > N) {
				sum -= primeList.get(left++);
			}
			else {
				count++;
				sum += (primeList.get(right++) - primeList.get(left++));
			}
		}
		
		while (sum >= N && left < primeList.size()) {
			if (sum == N) {
				count++;
			}
			sum -= primeList.get(left++);
		}
		
		return count;
	}

}
