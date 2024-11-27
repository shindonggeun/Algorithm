import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 식재료의 개수
	static int[][] ingredient; // 각 식재료마다 영양 성분들의 값 및 식재료 비용을 저장한 배열
	static int[] minValue; // 각 영양 성분의 최소값들을 저장하는 배열 (단백질, 지방, 탄수화물, 비타민)
	static int minCost; // 해당 식재료들의 최소 비용의 합
	static List<Integer> combination; // 식재료들의 인덱스의 조합을 저장할 리스트
	static List<Integer> resultCombination; // 최소 비용의 식재료의 조합을 나타낼 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		minValue = new int[4]; // [0] ~ [3], (단백질, 지방, 탄수화물, 비타민)
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			minValue[i] = Integer.parseInt(st.nextToken());
		}
		
		ingredient = new int[N][5]; // [0][0] ~ [N-1][4]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			// 단백질, 지방, 탄수화물, 비타민, 해당 식재료의 가격들 각각 입력 받는 과정
			for (int j=0; j<5; j++) {
				ingredient[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		minCost = Integer.MAX_VALUE; // 해당 식재료들의 최소 비용의 합 최대값으로 초기화
		combination = new ArrayList<>(); // 식재료들의 인덱스의 조합을 저장할 리스트 초기화
		resultCombination = new ArrayList<>(); // 최소 비용의 식재료의 조합을 나타낼 리스트 초기화
		
		// 최소 비용의 합 및 해당 비용의 식재료의 조합 구하는 메서드 호출
		calculateMinCost(0, 0, 0, 0, 0, 0);
		
		// 최소 비용의 식재료의 합이 최대값인 경우 (즉, 문제의 조건을 만족하는 답이 없는 경우)
		if (minCost == Integer.MAX_VALUE) {
			System.out.println(-1);
			return; // 메인 메서드 종료
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(minCost).append("\n");
		
		for (int idx: resultCombination) {
			sb.append(idx+1).append(" "); // 최소 비용의 식재료의 조합들의 식재료 번호(인덱스+1) 출력하게끔
		}
		
		System.out.println(sb);
	}
	
	// 백트래킹 알고리즘을 이용하여 최소 비용의 합 및 해당 비용의 식재료의 조합 구하는 메서드
	// depth: 현재 식재료의 인덱스 (깊이 즉, 단계)
	// sumP: 현재 식재료 조합의 단백질 합, sumF: 현재 식재료 조합의 지방 합, sumS: 현재 식재료 조합의 탄수화물 합
	// sumV: 현재 식재료 조합의 비타민 합, sumCost: 현재 식재료 조합의 비용의 합
	public static void calculateMinCost(int depth, int sumP, int sumF, 
			int sumS, int sumV, int sumCost) {
		// 현재까지 식재료 조합의 비용의 합이 최소 비용보다 큰 경우 (기저 조건 1)
		if (sumCost > minCost) {
			return; // 더이상 탐색할 필요없이 메서드 종료
		}
		
		// 현재 식재료 조합이 최소 영양 성분 조건을 만족하는 경우 (기저 조건 2)
		if (sumP >= minValue[0] && sumF >= minValue[1] && 
				sumS >= minValue[2] && sumV >= minValue[3]) {
			// 현재 식재료 조합의 비용의 합이 최소 비용보다 작은 경우
			if (sumCost < minCost) {
				minCost = sumCost; // 최소 비용 갱신
				// 현재 식재료 조합의 결과를 결과 리스트에 반영 (깊은 복사)
				resultCombination = new ArrayList<>(combination);
			}
			return; // 메서드 종료
		}
		
		// 현재까지 모든 식재료들을 탐색한 경우 (기저 조건 3)
		if (depth == N) {
			return; // 메서드 종료
		}
		
		// 현재 식재료를 선택한 경우
		combination.add(depth); // 현재 식재료의 인덱스를 조합에 추가
		// 현재 식재료를 추가한 상태로 다음 식재료 탐색하게끔 메서드 재귀 호출
		calculateMinCost(depth+1, 
				sumP + ingredient[depth][0], 
				sumF + ingredient[depth][1],
				sumS + ingredient[depth][2],
				sumV + ingredient[depth][3],
				sumCost + ingredient[depth][4]);
		combination.remove(Integer.valueOf(depth)); // 현재 식재료의 인덱스 제거 (백트래킹)
		
		// 현재 식재료를 선택 안한 경우 
		// 현재 식재료를 추가하지 않은 상태로 다음 식재료 탐색하게끔 메서드 재귀 호출
		calculateMinCost(depth+1, sumP, sumF, sumS, sumV, sumCost);
	}

}