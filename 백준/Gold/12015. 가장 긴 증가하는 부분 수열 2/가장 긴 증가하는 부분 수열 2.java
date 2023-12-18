import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 수열의 크기
	static int[] numArr;	// 수열을 이루고 있는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		numArr = new int[N];	// [0] ~ [N]
		// Longest Increasing Subsequence, LIS
		// 이분탐색 알고리즘 이용
		List<Integer> list = new ArrayList<>();	// 가장 긴 증가하는 부분 수열을 저장할 리스트
		
		list.add(0);	// 0 추가 (0부터 수열 시작) (이분탐색을 이용하기 위해 초기값 세팅해줌)
		st = new StringTokenizer(br.readLine());
		
		// 수열의 각 요소에 대해 반복
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			numArr[i] = num;
			
			// 현재 요소가 리스트의 마지막 요소보다 큰 경우 리스트에 추가
			if(num > list.get(list.size() - 1)) {
				list.add(num);
			}
			// 현재 요소가 리스트의 마지막 요소보다 작거나 같은 경우
			else {
				// 이분탐색을 위한 low와 high 인덱스 설정
				int low = 0;
				int high = list.size() - 1;
				
				// 이분탐색 알고리즘 실행
				while(low <= high) {
					int mid = (low + high) / 2;
					// 리스트의 중간값 인덱스의 값이 현재 요소보다 크거나 같은 경우
					if(list.get(mid) >= num) {
						high = mid - 1;	// 상한을 중간값 - 1로 조정하여 탐색범위 줄임
					}
					// 리스트의 중간값 인덱스의 값이 현재 요소보다 작은 경우
					else {
						low = mid + 1;	// 하한을 중간값 + 1로 조정하여 탐색범위 줄임
					}
				}
				// 이분탐색으로 찾은 위치에 현재 요소를 덮어 씌움
				list.set(low, num);
			}
		}
		// 가장 긴 증가하는 부분 수열의 길이 출력 (0을 포함하고 있으므로 -1 해야 함)
		System.out.println(list.size() - 1);

	}

}
