import java.util.*;
import java.io.*;

public class Main {
	
	static int A1;
	static int B1;
	static int A2;
	static int B2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A1 = Integer.parseInt(st.nextToken());
		B1 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		A2 = Integer.parseInt(st.nextToken());
		B2 = Integer.parseInt(st.nextToken());
		
		int lcm = lcm(B1, B2);
		
		int sumNumerator = A1 * (lcm / B1) + A2 * (lcm / B2);
		int sumDenominator = lcm;
		
		int gcd = gcd(sumNumerator, sumDenominator);
		sumNumerator /= gcd;
		sumDenominator /= gcd;
		
		System.out.println(sumNumerator + " " + sumDenominator);
	}
	
	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

}