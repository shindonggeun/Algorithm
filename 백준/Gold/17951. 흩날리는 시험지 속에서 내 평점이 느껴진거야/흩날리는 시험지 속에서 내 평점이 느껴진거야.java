import java.util.*;
import java.io.*;

public class Main {

	static int N;	// 시험지의 개수
	static int K;	// 나눌 그룹의 수
	static int[] scoreArr;	// 각 시험지의 점수를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		scoreArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			scoreArr[i] = Integer.parseInt(st.nextToken());
		}
		
		int maxScore = maxScoreCalculate();	// 최대 점수 계산
		System.out.println(maxScore);	
	}
	
	// 시험에서 받을 수 있는 최대 점수를 계산하는 메서드 (이분탐색 알고리즘 이용)
	public static int maxScoreCalculate() {
		int low = 0;	// 이분 탐색의 최소 범위
		int high = 0;	// 이분 탐색의 최대 범위
		
		for(int score: scoreArr) {
			high += score;	// 모든 점수의 합을 최대 범위로 설정해줌
		}
		
		while(low <= high) {
			int mid = (low + high) / 2;	// 중간값 계산
			
			// 중간값으로 그롭화 가능한 경우
			if(possibleDivide(mid)) {
				low = mid + 1;	// low값을 증가시켜 범위를 좁힘
			}
			// 중간값으로 그룹화 가능하지 못한 경우
			else {
				high = mid - 1;	// high값을 감소시켜 범위를 좁힘
			}
			
		}
		
		return high;	// 가능한 최대 점수 반환
	}
	
	public static boolean possibleDivide(int minScore) {
		int groupCount = 0;	// 현재까지 만든 그룹의 수
		int sum = 0;	// 현재 그룹의 점수 합계
		
		for(int score: scoreArr) {
			sum += score;	// 점수 누적시킴
			// 중간값인 최소 점수가 누적시킨 점수보다 작거나 같은 경우
			if(sum >= minScore) {
				sum = 0;	// 그룹 점수 초기화
				groupCount++;	// 현재까지 만든 그룹의 수 증가
				
				// K개의 그룹을 만들 수 있는 경우
				if(groupCount == K) {
					return true;	// true 반환해서 중간값으로 그룹화 가능
				}
			}
		}
		
		return false;	// 모든 시험지 검사한 후에도 K개의 그룹 만들 수 없으면 false 반환
	}

}
