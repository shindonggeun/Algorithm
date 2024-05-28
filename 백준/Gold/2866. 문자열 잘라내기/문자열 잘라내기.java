import java.util.*;
import java.io.*;

public class Main {
	
	static int R;	// 테이블 행의 수
	static int C;	// 테이블 열의 수
	static char[][] table;	// 테이블

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		table = new char[R][C];	// [0][0] ~ [R-1][C-1]
		
		// 각 행에 대해서 반복
		for (int i=0; i<R; i++) {
			String input = br.readLine();
			// 각 열에 대해서 반복
			for (int j=0; j<C; j++) {
				table[i][j] = input.charAt(j);
			}
		}
		
		int count = binarySearch();	// 중복 없는 최대 행 제거 수 이진 탐색 메서드로 찾기
		
		System.out.println(count);
	}
	
	// 이진 탐색 알고리즘을 이용하여 중복 없는 최대 행 제거 수를 찾아주는 메서드
	public static int binarySearch() {
		int start = 0;	// 이진 탐색 시작점
		int end = R - 1;	// 이진 탐색 끝점
		
		// 이진탐색 알고리즘 시작
		while (start <= end) {
			int mid = (start + end) / 2;	// 중간 지점 계산
			
			// 중간 지점에서 행을 제거한 경우 문자열 중복이 없는지 확인하기
			if (check(mid)) {
				// 중복이 없으면 더 많은 행을 제거할 수 있는지 확인하기 위해 시작점을 중간 지점 + 1로 갱신
				start = mid + 1;
			}
			else {
				// 중복이 있으면 제거할 행 수를 줄이기 위해 끝점을 중간 지점 - 1로 이동
				end = mid - 1;
			}
		}
		
		// 중복이 없었던 최대 행 제거 수를 반환
		return start;
	}
	
	// 특정 개수의 행을 제거했을 때 중보깅 없는지 확인하는 메서드
	public static boolean check(int mid) {
		// 중복여부를 확인하기 위해 HashSet 이용
		Set<String> set = new HashSet<>();
		
		// 각 열에 대해서 반복
		for (int i=0; i<C; i++) {
			// 현재 열의 생성한 문자열을 저장할 StringBuilder 선언 및 생성
			StringBuilder sb = new StringBuilder();
			// 중간 지점 이후의 행부터 문자열을 만듬
			for (int j=mid+1; j<R; j++) {
				sb.append(table[j][i]);	// 현재 행의 문자 추가 (문자열 만들기)
			}
			
			// HashSet에 이미 존재하는 문자열인 경우
			if (set.contains(sb.toString())) {
				return false;	// 중복이 있다는 표시 반환
			}
			
			// 중복이 없는 경우 HashSet에 만든 문자열 추가
			set.add(sb.toString());
		}
		
		// 동일한 문자열 찾지 못한 경우 중복된 문자열 없다는 표시 반환
		return true;
	}

}