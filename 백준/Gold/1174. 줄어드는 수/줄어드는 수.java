import java.util.*;
import java.io.*;

public class Main {

	static int N; // N번째 줄어드는 수를 저장하는 변수
	static int[] numArr; // 0부터 9까지의 숫자를 내림차순으로 저장한 배열
	static Set<Long> set; // 줄어드는 수를 저장할 Set (중복 방지)
	static List<Long> list; // 생성된 줄어드는 수를 저장할 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 줄어드는 수를 만들기 위해 9부터 0까지 내림차순으로 배열에 저장
		numArr = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}; // 배열 초기화 (9 ~ 0)
		set = new HashSet<>(); // 해시셋 초기화
		list = new ArrayList<>(); // 리스트 초기화 
		
		makeDecreaseNumber(0, 0); // 줄어드는 수를 생성하기 위한 백트래킹 메서드 호출
		
		Collections.sort(list); // 생성된 줄어드는 수들을 저장한 리스트 오름차순 정렬
		
		// 줄어드는 수들을 저장한 리스트의 크기가 N이상인 경우 N번째 줄어드는 수 출력, 그렇지 않은 경우 -1 출력
		System.out.println(list.size() >= N ? list.get(N-1) : -1);
	}
	
	// 깊이우선탐색 기반 백트래킹 알고리즘을 이용하여 줄어드는 수를 생성하는 함수
	// depth: 현재 탐색하는 자리 수 (깊이, 최대 10자리까지만 가능)
	// num: 현재까지 만든 줄어드는 수
	public static void makeDecreaseNumber(int depth, long num) {
		// 현재까지 만든 줄어드는 수가 해시셋에 포함되어 있지 않은 경우 -> 해시셋 검색 시간복잡도 O(1)
		if (!set.contains(num)) {
			set.add(num); // 해시셋에 해당 줄어드는 수 저장
			list.add(num); // 리스트에 해당 줄어드는 수 저장
		}
		
		// 현재까지 탐색한 자리수가 10자리가 넘은 경우 (기저 조건)
		if (depth == 10) {
			return; // 메서드 종료
		}
		
		// 현재 자리에서 선택하지 않고 다음 자리로 넘어가는 경우 (즉, 현재 자릿수에 아무 숫자도 추가하지 않고 넘어감)
		makeDecreaseNumber(depth + 1, num);
		
		// 현재 자리의 숫자를 선택하여 줄어드는 수를 만든 경우 다음 자릿수로 넘어가게끔 재귀 호출
		makeDecreaseNumber(depth + 1, num * 10 + numArr[depth]);
	}

}