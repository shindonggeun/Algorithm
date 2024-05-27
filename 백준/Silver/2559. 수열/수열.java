import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 전체 날짜 수
	static int K;	// 연속적인 날짜의 수
	static int[] temperature;	// 매일 측정한 온도를 저장할 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		temperature = new int[N];	// [0] ~ [N]
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			temperature[i] = Integer.parseInt(st.nextToken());
		}
		
		// 처음 K일 동안의 온도의 합을 미리 계산
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += temperature[i];
        }

        int max = sum;	// 연속적인 K일의 온도의 합이 최대가 되는 값

        // 슬라이딩 윈도우 적용
        for (int i = K; i < N; i++) {
        	sum += temperature[i] - temperature[i-K];
            max = Math.max(max, sum);
        }

        System.out.println(max);
	}

}