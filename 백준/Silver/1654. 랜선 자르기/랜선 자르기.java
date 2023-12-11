import java.util.*;
import java.io.*;

public class Main {
	
	static int K;	// 이미 가지고 있는 랜선의 개수
	static int N;	// 필요한 랜선의 개수
	static int[] lanArr;	// 가지고 있는 랜선의 길이들을 저장할 배열
	static long maxLength;	// 가지고 있는 랜선 중 가장 긴 길이 저장할 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		lanArr = new int[K];
		
		for(int i=0; i<K; i++) {
			lanArr[i] = Integer.parseInt(br.readLine());
			maxLength = Math.max(maxLength, lanArr[i]);	// 가지고 있는 랜선 중 가장 긴 길이 갱신
		}
		
		// 이분탐색 알고리즘 이용
		// 각 변수들 오버플로우 방지 위해 long
		long low = 1;	// 이분 탐색에서 하한 나타내는 변수 초기화
		long high = maxLength;	// 이분 탐색에서 상한 나타내는 변수 초기화
		long result = 0;	// 최종 결과값
		
		// 이분탐색 알고리즘 실행
		while(low <= high) {
			long mid = (low + high) / 2;	// 중간값 계산
			long count = 0;	// 중간값으로 랜선을 잘랐을 때 만들 수 있는 랜선의 개수
			
			// 각 랜선에 대해 반복
			for(int lan: lanArr) {
				count += (lan / mid);	// 현재 중간값으로 랜선 잘랐을 때 만들 수 있는 개수 누적
			}
			
			// 만들 수 있는 랜선의 개수가 필요한 개수 이상인 경우
			if(count >= N) {
				result = mid;	// 현재 중간값을 결과값으로 저장
				low = mid + 1;	// 하한을 중간값 + 1로 조정하여 탐색 범위 줄임
			}
			// 만들 수 있는 랜선의 개수가 필요한 개수 미만인 경우
			else {
				high = mid - 1;	// 상한을 중간값 - 1로 조정하여 탐색 범위 줄임
			}
 		}
		
		System.out.println(result);
		
	}

}
