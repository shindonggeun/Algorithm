import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		backTracking(0, 0);
		System.out.print(sb);
	}
	
	public static boolean isPrime(int num) {
		// 숫자 2 미만인거는 소수 아님
		if(num < 2) {
			return false;
		}
		// 소수인지 아닌지 판별하는 과정
		for(int i=2; i*i<=num; i++) {
			if(num % i == 0) {
				return false;	// num이 i의 배수인 경우 소수 아니므로 false
			}
		}
		return true;
	}
	
	// 백트래킹 
	public static void backTracking(int num, int idx) {
		// N자리수인 경우 (해당 깊이까지 도달한 경우)
		if(idx == N) {
			sb.append(num).append("\n");
		}
		
		// 각 자리수마다 1부터 9까지 올수 있으므로 반복문 1 ~ 9까지 설정
		for(int i=1; i<10; i++) {
			int temp = 10 * num + i;
			// 소수인 경우
			if(idx >= 0 && isPrime(temp)) {
				backTracking(temp, idx+1);	// 재귀함수 호출 (해당 깊이 1 증가)
			}
		}
	}

}
