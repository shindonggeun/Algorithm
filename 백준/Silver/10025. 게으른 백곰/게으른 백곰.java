import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 얼응 양동이 개수
	static int K;	// 최적 위치로 부터 K만큼 떨어진 거리
	static int[] iceArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		iceArr = new int[1000001];	// [0] ~ [1000000]
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			iceArr[x] = g;
		}
		
		int sum = 0;
		int max = 0;
		int range = 2 * K + 1;
		
		for (int i=0; i<=1000000; i++) {
			sum += iceArr[i];
			if (i - range >= 0) {
				sum -= iceArr[i-range];
			}
			
			max = Math.max(max, sum);
		}
		System.out.println(max);

	}

}