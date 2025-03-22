import java.util.*;
import java.io.*;

public class Main {
	
	static int T; // 테스트 케이스 수
	static int N; // 현재 테스트 케이스의 숫자 개수
	static int[] nums; // 숫자들을 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase=0; testCase<T; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			nums = new int[N]; // [0] ~ [N-1]
			
			for (int i=0; i<N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			// N은 최대 100개이므로 가능한 모든 쌍의 수 100C2 = 4950
			// 각 수는 1,000,000 (백만)
			// 1,000,000 쌍이 4950쌍 존재할 경우 -> GCD 합 = 1,000,000 × 4950 = 약 49억
			long gcdSum = 0; // 모든 가능한 쌍의 GCD를 합산할 변수 (오버플로우 방지를 위해 long)
			for (int i=0; i<N-1; i++) {
				for (int j=i+1; j<N; j++) {
					gcdSum += gcd(nums[i], nums[j]);
				}
			}
			
			sb.append(gcdSum).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 유클리드 호제법을 이용한 두 수의 최대공배수(gcd)를 구하는 메서드
	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

}