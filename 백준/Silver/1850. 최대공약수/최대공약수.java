import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 문제에서 A, B는 최대 2^63 - 1 범위를 가질 수 있으므로 long 타입으로 처리
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		long gcd = gcd(A, B); // A와 B의 최대공약수를 구함
		
		StringBuilder sb = new StringBuilder();
		// 최대공약수(gcd)만큼 '1'을 반복하여 결과 문자열 만들기
		// ex. gcd가 3이면 "111", gcd가 5면 "11111" 형태
		for (int i=0; i<gcd; i++) {
			sb.append("1");
		}
		
		System.out.println(sb);
	}
	
	// 유클리드 호제법을 이용한 두 수의 최대공약수 구하는 메서드
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

}