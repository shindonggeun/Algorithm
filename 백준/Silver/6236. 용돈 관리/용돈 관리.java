import java.util.*;
import java.io.*;

public class Main {

	static int N;	// 사용할 일 수
	static int M;	// 인출 횟수
	static int[] useMoneyArr;	// 매일 사용할 금액들을 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		useMoneyArr = new int[N];	// [0] ~ [N]
		int maxUseMoney = 0;	// 하루 최대 사용 금액 (하한)
		int sumUseMoney = 0;	// 모든 날의 사용 금액 합계를 저장할 변수 (상한)
		
		for(int i=0; i<N; i++) {
			useMoneyArr[i] = Integer.parseInt(br.readLine());
			maxUseMoney = Math.max(maxUseMoney, useMoneyArr[i]);	// 하루 최대 사용 금액 갱신
			sumUseMoney += useMoneyArr[i];	// 모든 날의 사용 금액 합계에 현재 사용할 금액 추가
		}
		
		int resultMoney = binarySearch(maxUseMoney, sumUseMoney);	// 이분탐색 알고리즘을 이용하여 인출해야 할 최소 금액 저장
		System.out.println(resultMoney);
		
	}
	
	// 인출해야할 최소 금액을 구하는 메서드 (이분탐색 알고리즘 이용)
	public static int binarySearch(int low, int high) {
		int result = low;	// 인출해야할 최소 금액 (즉, 결과값)을 저장할 변수
		
		// 이분탐색 알고리즘 이용
		while(low <= high) {
			int mid = (low + high) / 2;	// 중간값 계산
			
			// 인출 횟수가 M보다 크거나 크거나 같은 경우
			if(M < getWithdrawCount(mid)) {
				low = mid + 1;	// 최소 금액 증가 (하한을 중간값 + 1로 조정하여 탐색 범위 줄임)
			}
			// 인출 횟수가 M보다 크거나 같은 경우
			else {
				result = mid;	// 현재의 상한액(최대금액)을 결과로 저장
				high = mid - 1;	// 최대 금액 감소 (상한을 중간값 - 1로 조정하여 탐색 범위 줄임)
			}
		}
		
		return result;	// 계산된 인출해야할 최소 금액 반환
	}
	
	// 인출 횟수를 계산하는 메서드
	public static int getWithdrawCount(int currentAmount) {
		int count = 1;	// 인출 횟수를 저장하는 변수
		int amount = currentAmount;	// 현재 현금 인출 금액을 저장
		
		// 매일 사용한 금액들을 탐색
		for(int useMoney: useMoneyArr) {
			amount -= useMoney;	// 현재 현금 인출 금액에서 사용 금액 차감
			// 돈이 모자란 경우 현금을 다시 인출해서 사용
			if(amount < 0) {
				count++;	// 인출 횟수 증가
				amount = currentAmount - useMoney;	// 새로운 인출 금액 설정
			}
		}
		
		return count;	// 계산된 인출 횟수 반환
	}

}
