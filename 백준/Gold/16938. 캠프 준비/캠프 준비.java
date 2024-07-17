import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 문제 개수
	static int L; // 문제 난이도 합의 하한선
	static int R; // 문제 난이도 합의 상한선
	static int X; // 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이 비교값 
	static int[] questionArr; // 문제 난이도들을 저장할 배열
	static int[] combination; // 문제들 중 2문제 이상 선택하여 조합으로 나타낼 배열
	static int count; // 캠프에 사용할 문제 고르는 방법의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		questionArr = new int[N]; // [0] ~ [N-1]
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			questionArr[i] = Integer.parseInt(st.nextToken());
		}
		
		count = 0; // 캠프에 사용할 문제 고르는 방법의 수 0으로 초기화
		
		// 2문제 이상 선택할 수 있게끔 조합의 크기를 2부터 N까지 시도
		for (int i=2; i<=N; i++) {
			combination = new int[i]; // 현재 선택할 문제의 수에 맞게 배열 크기 초기화
			// 조합 생성 및 조건 체크를 위한 조합 메서드 (백트래킹) 호출
			generateCombination(0, 0, i, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
		
		System.out.println(count);
	}
	
	// 백트래킹 알고리즘을 사용하여 문제의 조합을 생성해주는 메서드 (조합 메서드)
	// depth: 현재 선택된 문제의 수
	// idx: 현재 탐색할 문제의 인덱스
	// target: 선택할 문제의 총 개수
	// sum: 현재 선택된 문제의 난이도 합
	// min: 선택된 문제 중 최소 난이도
	// max: 선택된 문제 중 최대 난이도
	public static void generateCombination(int depth, int idx, int target, int sum, int min, int max) {
		// 현재 선택된 문제의 수가 선택할 문제의 총 개수와 같아지는 경우 (기저 조건)
		// 즉, 문제 선택하여 조합 완성된 경우
		if (depth == target) {
			// 현재 선택된 문제의 난이도의 합이 L 이상 R 이하이면서 동시에 
			// 가장 선택된 문제 중 가장 어려운 문제 난이도와 가장 쉬운 문제 난이도의 차가 X보다 크거나 같은 경우
			if (sum >= L && sum <= R && (max - min) >= X) {
				count++; // 캠프에 사용할 문제 고르는 방법의 수 증가
			}
			return; // 메서드 종료
		}
		
		// 현재 탐색할 문제 인덱스부터 N-1까지 탐색하며 조합 생성
		for (int i=idx; i<N; i++) {
			// 현재 선택된 문제를 조합 배열에 저장
			combination[depth] = questionArr[i];
			// 선택된 문제들 중 새로운 최소값과 최대값을 계산
			int newMin = Math.min(min, questionArr[i]);
            int newMax = Math.max(max, questionArr[i]);
            // 다음 문제 선택하기 위해 조합 메서드 (백트래킹) 호출
			generateCombination(depth+1, i+1, target, sum + combination[depth], newMin, newMax);
		}
	}

}