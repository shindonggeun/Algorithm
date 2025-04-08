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
	
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	public static long lcm(long a, long b) {
		return (a / gcd(a, b)) * b;
	}

}