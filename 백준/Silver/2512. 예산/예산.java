import java.util.*;
import java.io.*;

public class Main {

	static int N; // 지방의 수
	static int M; // 총 예산
	static int[] budgetArr; // 각 지방의 예산을 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		budgetArr = new int[N]; // [0] ~ [N-1]
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			budgetArr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		
		// 이분탐색 알고리즘을 이용하기 위해 각 지방의 예산들을 저장한 배열 오름차순 정렬
		Arrays.sort(budgetArr);
		
		// 이분탐색 알고리즘 이용
		int start = 0; // 시작점
		int end = budgetArr[N-1]; // 끝점
		int maxBudget = 0; // 배정된 예산들 중 최대값
		
		while (start <= end) {
			int mid = (start + end) / 2; // 중간점 설정
			
			// 해당 중간점 (설정한 예산)으로 조건을 모두 만족하게끔 예산 배정이 되는지 확인
			// 가능한 경우
			if (checkBudget(mid)) {
				maxBudget = mid; // 배정된 예산들 중 최대값 갱신
				start = mid + 1; // 이분탐색 범위 줄임 (하한 조정)
			}
			// 불가능한 경우
			else {
				end = mid - 1; // 이분탐색 범위 줄임 (상한 조정)
			}
		}
		
		System.out.println(maxBudget);
	}
	
	// 해당 설정한 예산으로 조건을 모두 만족하게끔 예산 배정이 되는지 확인하는 메서드
	public static boolean checkBudget(int settingBudget) {
		int sum = 0; // 설정한 예산으로 상한액 이하의 예산들을 모두 더한 값
		
		// 각 지방별 예산 순회
		for (int i=0; i<N; i++) {
			// 각 지방의 예산 요청 금액과 설정한 예산 중 작은값을 선택하여 누적
			// 요청 금액이 상한액 이하일 경우 요청 금액(budget[i]) 그대로
			// 요청 금액이 상한액보다 크면 상한액(settingBudget)만큼 배정
			sum += Math.min(budgetArr[i], settingBudget);
		}
		
		// 배정된 예산들의 총합이 국가 예산 M 이하인 경우 true 반환, 아닌 경우 false 반환
		return sum <= M;
	}

}