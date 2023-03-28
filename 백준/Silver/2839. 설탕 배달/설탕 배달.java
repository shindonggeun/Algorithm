import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int fiveLimit = N / 5;
		int threeLimit = N / 3;
		int count = 0;
		
		// 브루트포스 알고리즘 이용
		// 3킬로그램과 5킬로그램 봉지를 쓰는데 두 봉지 쓸때 두 봉지를 합한 최소 봉지수를 구해야하므로
		// 3킬로그램을 탐색하는 것은 이중for문에서 바깥, 5킬로그램 탐색하는 것은 이중for문의 안쪽
		for(int i=0; i<=threeLimit; i++) {
			// 5키로그램 설탕부터 먼저 써야 된다!!!
			for(int j=0; j<=fiveLimit; j++) {
				if((i*3) + (j*5) == N) {
					count = i+j;
					System.out.println(count);
					return;
				}
			}
		}
		
		// 위의 이중 for문 다 탐색했는데 정확하게 N킬로그램 배달 못한 경우 -1 출력
		System.out.println(-1);

	}

}