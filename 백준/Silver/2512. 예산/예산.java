import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 지방의 수
	static int[] budgetArr;	// 각 지방의 예산 요청을 저장할 배열
	static int M;	// 총 예산
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		budgetArr = new int[N];	// [0] ~ [N]
		int maxBudget = 0;	// 가장 큰 예산 요청을 저장할 변수
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			budgetArr[i] = Integer.parseInt(st.nextToken());
			maxBudget = Math.max(maxBudget, budgetArr[i]);	// 가장 큰 예산 요청 갱신
		}
		
		M = Integer.parseInt(br.readLine());	
		
		int resultBudget = binarySearch(0, maxBudget);	// 이분탐색 알고리즘을 이용하여 최적의 예산 저장
		System.out.println(resultBudget);
		
	}
	
	// 배정된 예산들 중 최대값을 찾는 메서드 (즉, 최적의 예산을 찾는 메서드) (이분탐색 알고리즘 이용)
	public static int binarySearch(int low, int high) {
		int result = 0;	// 최적의 예산 상한액을 저장할 변수
		
		// 이분탐색 알고리즘 이용
		while(low <= high) {
			int mid = (low + high) / 2;	// 예산 상한액을 중간값으로 설정
			int sum = 0;	// 각 지방에 배정된 예산의 합을 저장할 변수
			
			// 예산 요청 배열 탐색
			for(int budget: budgetArr) {
				sum += Math.min(budget, mid);	// 각 지방의 예산 요청과 상한액 중 가장 작은 값을 합산
			}
			
			// 합산된 예산이 총 예산을 초과하는 경우
			if(sum > M) {
				high = mid - 1;	// 상한액 낮춤 (상한을 중간값 - 1로 조정하여 탐색 범위 줄임)
			}
			// 합산된 예산이 총 예산 이하인 경우
			else {
				low = mid + 1;	// 상한액 높임 (하한을 중간값 + 1로 조정하여 탐색 범위 줄임)
				result = mid;	// 현재의 상한액을 결과로 저장
			}
			
		}
		
		return result;	// 최적의 예산 상한액 반환
	}

}
