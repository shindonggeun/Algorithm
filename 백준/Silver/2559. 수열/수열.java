import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;
	static int[] temperature;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		temperature = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			temperature[i] = Integer.parseInt(st.nextToken());
		}
		
		// 처음 K일 동안의 합을 미리 계산
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += temperature[i];
        }

        int max = sum;

        // 슬라이딩 윈도우 적용
        for (int i = K; i < N; i++) {
        	sum += temperature[i] - temperature[i-K];
            max = Math.max(max, sum);
        }

        System.out.println(max);
	}

}