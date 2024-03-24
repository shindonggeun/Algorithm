import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 강의 수
	static int M;	// 블루레이의 수
	static int[] lectures;	// 각 강의의 시간(분)을 저장할 배열
	static int start;	// 블루레이의 최소 크기
	static int end;	// 블루레이의 최대 크기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 이진탐색(이분탐색) 이용
		start = 0;	// 시작 위치 (블루레이의 최소 크기)
		end = 0;	// 끝 위치 (블루레이의 최대 크기)
		
		lectures = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			lectures[i] = Integer.parseInt(st.nextToken());
			end += lectures[i];	// 코든 강의를 담을 수 있는 블루레이의 최대 크기
			start = Math.max(start, lectures[i]);	// 블루레이의 최소 크기 (강의 시간 중 가장 큰 시간으로 최소 크기 잡음)
		}
		
		// 이분탐색 알고리즘 시작
		while (start <= end) {
			int mid = (start + end) / 2;	// 현재 가정한 블루레이의 크기 설정
			// 현재 가정한 블루레이 크기로 모든 강의를 M개의 블루레이로 담을 수 있는지 확인하기
			if (isPossible(mid)) {
				end = mid - 1;	// 가능한 경우 더 작은 크기로도 가능한지 확인하기 위해 탐색 범위의 끝을 줄여줌
			} 
			// 현재 가정한 블루레이 크기로는 모든 강의를 M개의 블루레이에 담을 수 없는 경우
			else {
				start = mid + 1;	// 가능하지 않은 경우 더 큰 크기로 탐색 범위 조정
			}
		}
		
		System.out.println(start);	// 시작 인덱스 출력 (가능한 블루레이 크기 출력)

	}
	
	public static boolean isPossible(int size) {
		int blueRayCount = 0;
		int sum = 0;	// 강의 시간 합
		
		for (int lecture: lectures) {
			// 현재 블루레이에 더이상 강의를 담을 수 없는 경우
			if (sum + lecture > size) {
				blueRayCount++;	// 블루레이 개수 증가
				sum = 0;	// 다음 블루레이로 넘어갈 수 있게끔 강의 시간 합 0으로 초기화
			}
			sum += lecture;	// 현재 블루레이에 해당 강의 시간 추가
		}
		
		// 해당 블루레이의 강의시간이 0보다 큰 경우 (즉, 남은 강의가 있는 경우)
		 if (sum > 0) {
			 blueRayCount++;	// 블루레이 개수 추가
		 }
		// 블루레이 개수가 M 이하인지 검사 (M개 이하인 경우 true, 미만인 경우 false 반환)
		 return blueRayCount <= M;
	}

}
