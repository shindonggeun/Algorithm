import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 배열의 크기
	static int M; // 구간의 최대 개수
	static int[] numArr; // 입력된 수들을 저장할 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numArr = new int[N]; // [0] ~ [N-1]
		int maxScore = Integer.MIN_VALUE; // 배열 내 최대값을 저장할 변수 최소값으로 초기화
		int minScore = Integer.MAX_VALUE; // 배열 내 최소값을 저장할 변수 최대값으로 초기화
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
			maxScore = Math.max(maxScore, numArr[i]); // 배열 내 최대값 갱신
			minScore = Math.min(minScore, numArr[i]); // 배열 내 최소값 갱신
		}
		
		// 이분 탐색 알고리즘 시작
		int start = 0; // 시작점 설정 (최소값: 0, 최소 구간 점수 차이는 0)
		int end = maxScore - minScore; // 끝점 설정 (최대값: 배열 내 최대값과 최소값의 차이)
		int result = end; // 최종 결과값을 저장할 변수
		
		while (start <= end) {
			int mid = (start + end) / 2; // 중간값 계산
			
			// 중간값을 기준으로 배열을 M개 이하의 구간으로 나눌 수 있는지 검사
			// 나눌 수 있는 경우
			if (divideCheck(mid)) {
				end = mid - 1; // 끝점 갱신 (상한 조정하여 범위 줄임)
				result = mid; // 가능한 값이므로 결과값 갱신
			}
			// 중간값을 기준으로 배열을 M개 이하의 구간으로 나눌 수 없느 경우
			else {
				start = mid + 1; // 시작점 갱신 (하한 조정 범위 줄임)
			}
		}
		
		System.out.println(result);
	}
	
	// 구간 점수의 최대값이 maxDiff (이분 탐색에서 중간값) 이하가 되도록 배열을 M개 이하의 구간으로 나눌 수 있는지 확인하는 메서드
	public static boolean divideCheck(int maxDiff) {
		int divideSectionCount = 1; // 구간의 개수를 세기 위한 변수 초기화 (초기값 1은 첫 번째 구간)
		int maxScore = numArr[0]; // 현재 구간의 최대값 초기화 (배열 내 첫 번째 원소)
		int minScore = numArr[0]; // 현재 구간의 최소값 초기화 (배열 내 첫 번째 원소)
		
		// 배열에서 첫 번째 원소를 제외한 나머지 요소를 순차적으로 탐색
		for (int i=1; i<N; i++) {
			maxScore = Math.max(maxScore, numArr[i]); // 현재 구간 내 최대값 갱신
			minScore = Math.min(minScore, numArr[i]); // 현재 구간 내 최소값 갱신
			
			// 현재 구간의 점수 (최대값 - 최소값)이 maxDiff (이분 탐색에서 중간값)을 초과하면 구간을 나눔
			if (maxScore - minScore > maxDiff) {
				divideSectionCount++; // 구간 수 증가
				maxScore = numArr[i]; // 새로운 구간 시작점의 첫 원소로 최대값 초기화 (해당 배열의 원소)
				minScore = numArr[i]; // 새로운 구간 시작점의 첫 원소로 최소값 초기화 (해당 배열의 원소)
			}
			
			// 구간의 수가 M을 초과한 경우 이분 탐색의 중간값으로는 나눌 수 없으므로
			if (divideSectionCount > M) {
				return false; // 더 큰 중간값이 필요하므로 false를 반환 (해당 중간값으로는 구간 나눌 수 없음)
			}
		}
		
		// M개 이하의 구간으로 나눌 수 있다는 의미의 true 반환
		return true;
	}

}