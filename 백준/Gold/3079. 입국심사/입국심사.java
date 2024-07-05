import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 심사대의 수
	static int M; // 상근이와 친구들의 수
	static long[] timeArr; // 각 심사대의 심사 시간을 담은 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		timeArr = new long[N]; // [0] ~ [N-1]
		long maxTime = 0; // 최대 심사 시간

		for (int i=0; i<N; i++) {
			timeArr[i] = Long.parseLong(br.readLine());
			maxTime = Math.max(maxTime, timeArr[i]); // 최대 심사 시간 갱신 
		}
		
		// 이분 탐색 알고리즘 이용
		long start = 0; // 이분 탐색의 시작 범위
		long end = maxTime * M; // 이분 탐색의 끝 범위 (최대 가능한 시간)
		long result = end; // 최종 결과를 저장할 변수 (초기에 끝 범위로 저장)
		
		while (start <= end) {
			long mid = (start + end) / 2; // 중간값 계산
			long count = 0; // 현재 중간값 시간 내에 심사대에서 처리할 수 있는 사람의 수
			
			// 각 심사대 순회
			for (int i=0; i<N; i++) {
				count += mid / timeArr[i]; // 중간값 시간 내에 해당 심사대에서 처리할 수 있는 사람 수를 계산하여 카운트에 누적
				// 상근이와 친구들의 수인 M명을 다 처리할 수 있는 경우
				if (count >= M) {
					break; // 더이상 탐색할 필요 없이 반복문 빠져나옴
				}
			}
			
			// 각 심사대 순회과정 거친 뒤 상근이와 친구들의 수인 M명을 다 처리할 수 있는 경우 
			if (count >= M) {
				end = mid - 1; // 이분 탐색의 끝 범위 줄여줌
				result = mid; // 해당 중간값(심사대에서 처리 가능한 시간)을 result에 저장 
			}
			// 각 심사대 순회 과정 거친 뒤 상근이와 친구들의 수인 M명 미만 처리 가능한 경우 (즉, 다 처리 못함)
			else {
				start = mid + 1; // 이분 탐색의 시작 범위 늘려줌
			}
		}
		
		System.out.println(result);
	}

}