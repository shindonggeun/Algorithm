import java.util.*;
import java.io.*;

public class Main {
	
	// 두 분수 A/B + C/D에서 각각의 분자 (A, C)와 분모(B, D)를 저장
	static int A;
	static int B;
	static int C;
	static int D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		// 분수의 합을 구할 때: A/B + C/D = (A*D + B*C) / (B*D)
		int numerator = A * D + B * C; // 분자 계산
		int denominator = B * D; // 분모 계산
		
		int gcd = gcd(numerator, denominator); // 분자와 분모의 최대공약수를 구해서 약분
		
		// 최대공약수로 분자와 분모를 나눠서 기약분수로 변환
		numerator /= gcd;
		denominator /= gcd;
		
		System.out.println(numerator + " " + denominator);
	}
	
	// 유클리드 호제법을 이용한 두 수의 최대공약수 구하는 메서드
	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}