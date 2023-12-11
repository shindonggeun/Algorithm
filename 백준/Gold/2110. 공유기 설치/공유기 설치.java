import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 집의 개수
	static int C;	// 설치해야 할 공유기의 개수
	static int[] houseArr;	// 각 집에 위치를 저장할 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		houseArr = new int[N];	
		
		for(int i=0; i<N; i++) {
			houseArr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(houseArr);	// 집의 위치 x 좌표 오름차순 정렬
		
		// 이분탐색 알고리즘 이용
		int low = 1;	// 가능한 최소 거리
		int high = houseArr[N-1] - houseArr[0];	// 가능한 최대 거리
		int result = 0;	// 결과값
		
		// 이분탐색 실시
		while(low <= high) {
			int mid = (low + high) / 2;	// 중간 거리 계산
			int install = 1;	// 첫 번째 집에 공유기 설치
			int lastInstall = houseArr[0];	// 마지막으로 설치한 집의 위치
			
			// 공유기 설치 가능 여부를 탐색 (두번재 집부터 탐색)
			for(int i=1; i<N; i++) {
				// 현재 집과 마지막으로 설치한 집 사이의 거리가 mid 이상인 경우
				if(houseArr[i] - lastInstall >= mid) {
					install++;	// 공유기 설치
					lastInstall = houseArr[i];	// 마지막으로 설치한 위치 업데이트
				}
			}
			
			// 필요한 개수 이상의 공유기를 설치할 수 있는 경우
			if(install >= C) {
				result = mid;	// 결과값 갱신
				low = mid + 1;	// 탐색 범위 좁히기 (하한 증가)
			}
			// 필요한 개수 이상의 공유기 설치할 수 없는 경우
			else {
				high = mid - 1;	// 탐색 범위 좁히기 (상한 감소)
			}
		}
		
		System.out.println(result);

	}

}
