import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int numA = Integer.parseInt(st.nextToken());
		int numB = Integer.parseInt(st.nextToken());
		
		int gcd = gcd(numA, numB); // 두 수의 최대공약수 구하기
		
		// 두 수를 최대공약수로 나눠줌
		numA /= gcd;
		numB /= gcd;
		
		int m = numA * numB * gcd; // 최소공배수 구하기
		
		System.out.println(gcd);
		System.out.println(m);
	}
	
	// 유클리드 호제법을 이용한 두 수의 최대공약수 구하는 메서드
	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

}