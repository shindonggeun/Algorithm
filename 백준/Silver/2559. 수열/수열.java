import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 온도를 측정한 전체 날짜의 수
	static int K; // 합을 구하기 위한 연속적인 날짜의 수
	static int[] temperatureArr; // 각 날짜별로 온도를 저장한 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		temperatureArr = new int[N]; // [0] ~ [N-1]
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			temperatureArr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 슬라이딩 윈도우 이용
		int continuousSum = 0; // 연속적인 온도의 합 0으로 초기화
		// 초반 K 날짜까지 연속적인 온도의 합 구하는 과정 (0 ~ K-1)
		for (int i=0; i<K; i++) {
			continuousSum += temperatureArr[i];
		}
		
		int maxContinuousSum = continuousSum; // 연속적인 K일의 온도의 합의 최대값 설정
		
		// K일부터 N까지 슬라이딩 윈도우 작업을 통해 연속적인 K일의 온도의 합 최대 구하는 과정
		for (int i=K; i<N; i++) {
			// 연속적인 온도의 합 갱신 (슬라이딩 윈도우 적용)
			continuousSum += temperatureArr[i] - temperatureArr[i-K];
			// 연속적인 K일의 온도의 합 최대값 갱신
			maxContinuousSum = Math.max(maxContinuousSum, continuousSum);
		}
		
		System.out.println(maxContinuousSum);
	}

}