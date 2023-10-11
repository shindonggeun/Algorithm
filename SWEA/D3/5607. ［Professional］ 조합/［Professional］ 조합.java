import java.util.*;
import java.io.*;

public class Solution {
	
	static final long MOD = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			// 팩토리얼 미리 구해놓기 (1! ~ N!)
			long[] factorial = new long[N+1];	// [1] ~ [N]
			factorial[1] = 1;	// 1! = 1
			
			// 팩토리얼 구하는 과정 (2! ~ N! 까지 구하는 과정)
			for(int i=2; i<=N; i++) {
				factorial[i] = (factorial[i-1] * i) % MOD;
			}
			// nCr = n! / r!(n-r)!
			// r!(n-r)!를 a라고 하면, a^(p-1) = 1 (mod p) 라는 페르마 소정리에 의해
			// 1 / r!(n-r)! = a^-1
			// 따라서 n! / r!(n-r)!은 n!*a^(p-2) (mod p)
			
			// nCr에서 분모 r!(n-r)!에 해당하는 값 계산
			// 즉, 페르마 소정리에서 1/a의 a에 해당하는 값
			long bottom = (factorial[R] * factorial[N-R]) % MOD;	
			bottom = pow(bottom, MOD-2);	// 페르마 소정리를 사용하여 분모의 역수 계산
			
			sb.append("#").append(tc).append(" ").append((factorial[N] * bottom) % MOD).append("\n");
		}
		System.out.print(sb);

	}
	
	// 지수 연산을 수행하는 메서드 (a^b, 즉 a의 b승)
	public static long pow(long a, long b) {
		// 지수 b가 0인 경우
		if(b == 0) {
			return 1;	// 1 반환
		}
		// 지수 b가 1인 경우
		else if(b == 1) {
			return a;	
		}
		
		// 지수 b가 짝수인 경우
		if(b % 2 == 0) {
			long temp = pow(a, b/2);	// 지수를 절반으로 나눈 값을 재귀호출해서 다시 계산
			return (temp * temp) % MOD;	
		}
		
		// 지수가 홀수인 경우 재귀적으로 짝수 만들어서 계산
		long temp = pow(a, b-1) % MOD;	
		return (temp * a) % MOD;
	}

}
