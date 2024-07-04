import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 학생의 수
	static int[] diffArr; // 차분 배열 (누적합 알고리즘 응용)
	static int[] resultArr; // 차분배열을 누적합한 결과 배열 (즉, 특정 시간대에 선택할 수 없는 좌석 수를 저장한 배열)
	static int Q; // 특정한 시각의 개수
	static final int MAX = 1000000; // 문제에서 주어진 최대 시간 상수로 설정

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		diffArr = new int[MAX+2]; // 차분배열의 크기는 MAX + 2 ([1] ~ [MAX+1]
		resultArr = new int[MAX+1]; // [1] ~ [MAX]
		
		for (int i=0; i<N; i++) {
			st =  new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken()); // 시작 시각 입력
			int E = Integer.parseInt(st.nextToken()); // 종료 시각 입력
			
			diffArr[S] += 1; // 해당 시작 시각에 좌석을 사용
			diffArr[E+1] -= 1; // 해당 종료 시각 다음 시각에 좌석 사용 종료를 표시 (차분 배열을 이용한 누적합에 사용하기 위해)
		}
		
		
		int sum = 0; // 누적합 계산을 위한 변수
		// 1부터 최대 시간까지 순회하여 누적합 계산
		for (int i=1; i<=MAX; i++) {
			sum += diffArr[i]; // 현재 시각까지의 누적 좌석 사용 수 계산
			resultArr[i] = sum; // 해당 결과 배열에 저장
		}
		
		Q = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<Q; i++) {
			int time = Integer.parseInt(st.nextToken());
			// 특정 시각에 선택할 수 없는 좌석 수 저장
			sb.append(resultArr[time]).append("\n");
		}
		
		// 결과 출력
		System.out.print(sb);
	}

}