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
		
		long target = L / G; // a * b = L / G 조건을 만족하는 값
		
		long resultA = 0;
		long resultB = 0;
		for (long i=1; i*i<=target; i++) {
			if (target % i == 0) {
				long aa = i;
				long bb = target / i;
				
				if (gcd(aa, bb) == 1) {
					resultA = aa * G;
					resultB = bb * G;
				}
			}
		}
		
		System.out.println(resultA + " " + resultB);
	}
	
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

}