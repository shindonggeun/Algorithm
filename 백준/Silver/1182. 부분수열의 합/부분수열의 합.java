import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 수열의 크기
	static int S; // 목표 합
	static int[] nums; // 입력받은 수열
	static int resultCount; // 조건을 만족하는 부분 수열의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		nums = new int[N]; // [0] ~ [N-1]
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		generatePartialSequence(0, 0); // 부분수열을 탐색하는 백트래킹 메서드 호출
		
		// S가 0인 경우 공집합(즉, 아무것도 선택하지 않은 경우)이 포함되므로 부분 수열의 개수에서 해당 경우 빼줌
		// 문제에서 크기가 양수인 부분 수열의 개수를 구하는 것이므로
		if (S == 0) {
			resultCount--;
		}
		
		System.out.println(resultCount);

	}
	
	// 백트래킹 알고리즘을 이용한 부분수열 탐색 메서드
	// depth: 현재 탐색 중인 원소의 인덱스
	// sum: 현재까지 선택한 원소들의 합 (즉, 부분 수열의 합)
	public static void generatePartialSequence(int depth, int sum) {
		// 현재까지 탐색한 원소가 N개를 다 충족한 경우 (기저 조건)
		if (depth == N) {
			// 선택한 부분수열의 합이 S인 경우
			if (sum == S) {
				resultCount++; // 결과 개수 증가
			}
			return; // 메서드 종료
		}
		
		// 1. 현재 원소를 선택하는 경우 부분 수열의 합에 해당 원소를 더해서 재귀 호출
		generatePartialSequence(depth + 1, sum + nums[depth]);
		
		// 2. 현재 원소를 선택하지 않은 경우 현재까지 부분 수열의 합 그대로 재귀 호출
		generatePartialSequence(depth + 1, sum);
	}

}