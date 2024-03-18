import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// N개의 정수
	static int M;	// M개의 수
	static int[] numArr;	// N개의 정수를 담은 배열
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[N];	// [0] ~ [N-1]
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numArr);	// N개의 정수를 담은 배열 오름차순 정렬
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<M; i++) {
			boolean find = false;	// 해당 정수 찾았는지 여부를 판단해주는 변수
			int target = Integer.parseInt(st.nextToken());	// 탐색할 대상 정수
			
			// 이진탐색 시작
			int start = 0;	// 시작 인덱스
			int end = numArr.length - 1;	// 끝 인덱스
			
			while (start <= end) {
				int mid = (start + end) / 2;	// 중간값 인덱스
				
				// 탐색한 수가 중간점 값보다 작은경우
				if (target < numArr[mid]) {
					end = mid - 1;	// 끝점을 중간값 - 1로 조정하여 탐색범위 줄임
				} 
				// 탐색한 수가 중간점 값보다 큰 경우
				else if (target > numArr[mid]) {
					start = mid + 1;	// 시작점을 중간값 + 1로 조정하여 탐색범위 줄임
				}
				// 탐색한 수가 중간점 값과 같은 경우
				else {
					find = true;	// 탐색할 대상 변수 찾음
					break;	// 이진탐색 빠져나옴
				}
			}
			
			if (find) {
				sb.append(1).append("\n");
			}
			else {
				sb.append(0).append("\n");
			}
		}
		System.out.print(sb);

	}

}
