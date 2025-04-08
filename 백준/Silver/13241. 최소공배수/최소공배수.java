import java.util.*;
import java.io.*;

public class Main {
	
	static long A;
	static long B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		System.out.println(lcm(A, B));
	}
	
	// 유클리드 호제법을 이용한 두 수의 최대공약수 구하는 메서드
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	// 유클리드 호제법을 이용한 두 수의 최대공배수를 구하는 메서드
	public static long lcm(long a, long b) {
		return (a / gcd(a, b)) * b; // 오버플로우를 방지하기 위해 나눗셈 먼저 함
	}

}