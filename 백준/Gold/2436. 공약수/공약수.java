import java.util.*;
import java.io.*;

public class Main {
	
	static long G; // 최대공약수
	static long L; // 최소공배수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		G = Long.parseLong(st.nextToken());
		L = Long.parseLong(st.nextToken());
		
		// 찾고자 하는 두 수 A, B는 다음 조건을 만족해야함
		// 1. GCD(A, B) = G
		// 2. LCM(A, B) = L
		// 이를 A = G * a, B = G * b 라고 했을 때
		// a * b = L / G 이면서, gcd(a, b) = 1인 (서로소) 쌍을 찾는 문제로 변환됨
		long target = L / G; // a * b = L / G가 되는 서로소 (a, b) 쌍을 찾아야함
		
		// 결과값으로 출력할 A, B
		long resultA = 0;
		long resultB = 0;
		
		// 반복문을 이용한 target의 약수 쌍을 탐색하는 과정
		// i * i <= target인 이유: 약수는 대칭적이기 때문에, i <= sqrt(target) 까지만 확인하면 (i, target/i) 쌍을 모두 찾을 수 있음
		for (long i=1; i*i<=target; i++) {
			// i가 target의 약수인 경우
			if (target % i == 0) {
				long aa = i;
				long bb = target / i;
				
				// a와 b가 서로소 일때만 유효한 쌍이므로 해당 조건이 있어야 GCD(G*a, G*b) = G가 보장
				if (gcd(aa, bb) == 1) {
					// 두 수 A, B를 원래 값으로 복원하기
					// 문제 조건상 마지막으로 갱신된 쌍이 합이 가장 작은쌍이 보장되므로 합이 최소가 되는 쌍을 선택하지 않아도 됨
					resultA = aa * G;
					resultB = bb * G;
				}
			}
		}
		
		System.out.println(resultA + " " + resultB);
	}
	
	// 유클리드 호제법을 이용한 최대공약수를 구하는 메서드
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

}